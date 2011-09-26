<?php

/**
 * Helper methods and functions for database access.
 *
 */

class DatabaseQuery {

	var $db;
	var $source;

	/**
	 * Construct a database query with the given database source.
	 * Throws an exception if this source cannot be connected.
	 */
	public function __construct($source) {
		$this->db = new PDO($source)
			or throw_new_IamlRuntimeException("Could not open database source '$source'");
		$this->source = $source;
	}

	/**
	 * Can we prepare the given query?
	 */
	public function tableExists($query) {
		$rs = $this->db->prepare($query);
		return $rs;
	}

	/**
	 * Execute the given query with the given arguments.
	 *
	 * Returns back either 'null' if there were no results, or
	 * the first result found from the query in an associative array.
	 */
	public function fetchFirst($query, $args = array()) {

		log_message("[database query] $query");
		log_message("[database args] " . $this->debugString($args));

		$rs = $this->db->prepare($query)
			or $this->throwDbError($this->db, "Could not prepare fetchFirst query '$query'", $args);

		$rs->execute($args)
			or $this->throwDbError($this->db, "Could not execute fetchFirst query '$query'", $args);

		// get just the first result
		$row = $rs->fetch();
		if (!$row) {
			return null;	// none found; return null
		}

		$obj = array();
		foreach ($row as $key => $value) {
			$obj[$key] = $value;
		}
		return $obj;

	}

	/**
	 * Execute the given query with the given arguments.
	 *
	 * Returns back all arguments into an array. The array
	 * may be empty if there were no results.
	 */
	public function fetch($query, $args = array()) {

		log_message("[database query] $query");
		log_message("[database args] " . $this->debugString($args));

		$rs = $this->db->prepare($query)
			or $this->throwDbError($this->db, "Could not prepare fetch query '$query'", $args);

		$rs->execute($args)
			or $this->throwDbError($this->db, "Could not execute fetch query '$query'", $args);

		$result = array();
		while ($row = $rs->fetch()) {
			$obj = array();
			foreach ($row as $key => $value) {
				$obj[$key] = $value;
			}
			$result[] = $obj;
		}

		return $result;
	}

	/**
	 * Execute the given query with the given arguments.
	 * Returns a handle to the database.
	 */
	public function execute($query, $args = array()) {

		log_message("[database query] $query");
		log_message("[database args] " . $this->debugString($args));

		$rs = $this->db->prepare($query)
			or $this->throwDbError($this->db, "Could not prepare single query '$query'", $args);

		$rs->execute($args)
			or $this->throwDbError($this->db, "Could not execute single query '$query'", $args);

		return $this->db;

	}

	private function throwDbError($db, $message, $args = "<not provided>") {
		throw new IamlRuntimeException($message . " "
			. (!$args ? "(no args)" : "[args = " . $this->debugString($args) . "]")
			. " [source = " . $this->source . "] [error = " . $this->errorInfo($db) . "]");
	}

	private function errorInfo($db) {
		return $this->debugString($db->errorInfo());
	}

	/**
	 * Print out the contents of $obj in a textual format, which is usually an array,
	 * but can also be a string or integer or anything.
	 *
	 * <p>Returns e.g. (a => a, b => null, c => (a => b))
	 */
	private function debugString($obj) {
		if (is_array($obj)) {
			$r = "";
			foreach ($obj as $key => $value) {
				$r .= "($key => " . $this->debugString($value) . ") ";
			}
			return "[ $r]";
		} elseif ($obj === null) {
			return "null";
		} else {
			return print_r($obj, true);
		}
	}

}

/**
 * Select an instance of the given source class with the
 * given query.
 *
 * If the given source class is inherited, all sub-types
 * must also be included in the result (likely through a JOIN).
 *
 * Return the final composed result, or null if
 * none can be found.
 *
 * @param args if an associative array, the keys MUST be cleaned up (i.e. no spaces) - see <code>clean_args()</code>
 * @param offset the offset, or 0 if there is none
 * @param order_by a row to order by, or "" if there is none
 * @param order_ascending if true, order ASC; otherwise order DESC
 * @param select_as a query to put into the SELECT statement, e.g. 'A.id as A_id, B.id as B_id'; otherwise '*'
 */
function evaluate_select_wire($db_name, $source_id, $source_class, $query, $args, $offset = 0, $order_by = "", $order_ascending = true, $select_as = "*") {
	log_message("[select wire] Evaluate: db = $db_name, source_id = $source_id, source_class = $source_class, query = $query, offset = $offset");

	if ($offset < 0) {
		throw new IamlIllegalArgumentException("Offset cannot be negative: $offset");
	}

	// get all joins
	global $compose_domain_joins_done_already;
	$compose_domain_joins_done_already = array();
	$joins = compose_domain_joins($source_id);

	$order_query = "";
	if ($order_by != "") {
		$order_query = "ORDER BY $order_by " . ($order_ascending ? "ASC " : "DESC ");
	}
	$joined_query = "SELECT $select_as FROM $source_class "
		. implode(" ", $joins)
		. " WHERE $query $order_query LIMIT 1"; /* we only ever select one row at a time with a select wire */
	if ($offset !== 0) {
		$joined_query .= " OFFSET " . (int) $offset;
	}

	log_message("[select wire] Evaluate: Composed query: " . preg_replace("/[ \r\n\t]+/im", " ", $joined_query));

	$db_query = new DatabaseQuery($db_name);
	$row = $db_query->fetchFirst($joined_query, $args);

	return $row;
}

/**
 * Return the number of results currently possible for evaluate_select_wire with the
 * given arguments.
 */
function evaluate_select_wire_count($db_name, $source_id, $source_class, $query, $args) {
	log_message("[select wire] Count: db = $db_name, source_id = $source_id, source_class = $source_class, query = $query");

	// get all joins
	global $compose_domain_joins_done_already;
	$compose_domain_joins_done_already = array();
	$joins = compose_domain_joins($source_id);

	$joined_query = "SELECT Count(*) AS c FROM $source_class "
		. implode(" ", $joins)
		. " WHERE $query";

	log_message("[select wire] Count: Composed query: " . preg_replace("/[ \r\n\t]+/im", " ", $joined_query));

	$db_query = new DatabaseQuery($db_name);
	$row = $db_query->fetchFirst($joined_query, $args);

	return $row["c"];
}

$compose_domain_joins_done_already = null;

/**
 * Compose a list of all joins required for the given
 * source object. Uses code generated in get_all_domain_joins().
 *
 * Return a list of SQL string queries to be used as part
 * of the SQL join.
 */
function compose_domain_joins($source_id) {
	global $compose_domain_joins_done_already;

	$result = array();

	$compose_domain_joins_done_already[] = $source_id;

	$all_joins = get_all_domain_joins();
	if (!isset($all_joins[$source_id])) {
		throw new IamlIllegalArgumentException("Could not find source ID '$source_id' in all joins (size = " . count($all_joins) . ")");
	}
	foreach ($all_joins[$source_id] as $target_id => $join_query) {
		// don't repeat multiple inheritance joins
		if (!in_array($target_id, $compose_domain_joins_done_already)) {
			$result[] = $join_query;
			$result = array_merge($result, compose_domain_joins($target_id));
		}
	}

	return $result;
}

/**
 * Clean up the keys of the given array to <code>safeNameString()</code>
 * specifications. Returns the cleaned array.
 */
function clean_args($args) {
	$n = array();
	foreach ($args as $key => $value) {
		$cleaned = preg_replace("#[^A-Za-z0-9]#i", "_", $key);
		$n[$cleaned] = $value;
	}
	return $n;
}
