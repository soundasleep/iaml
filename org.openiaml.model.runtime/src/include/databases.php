<?php

/**
 * Helper methods and functions for database access.
 *
 */

class DatabaseQuery {

	var $db;

	/**
	 * Construct a database query with the given database source.
	 * Throws an exception if this source cannot be connected.
	 */
	public function __construct($source) {
		$this->db = new PDO($source) 
			or throw_new_IamlRuntimeException("Could not open database source '$source'");
	}
	
	/**
	 * Execute the given query with the given arguments.
	 *
	 * Returns back either 'null' if there were no results, or
	 * the first result found from the query in an associative array.
	 */
	public function fetchFirst($query, $args) {
		
		log_message("[query] $query");
		log_message("[args] " . $this->debugString($args));
		
		$rs = $this->db->prepare($query) 
			or $this->throwDbError($this->db, "Could not prepare query '$query'");

		$rs->execute($args)
			or $this->throwDbError($this->db, "Could not execute query '$query'");
		
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
	 * Returns a handle to the database.
	 */
	public function execute($query, $args) {
		
		log_message("[query] $query");
		log_message("[args] " . $this->debugString($args));
		
		$rs = $this->db->prepare($query) 
			or $this->throwDbError($this->db, "Could not prepare query '$query'");

		$rs->execute($args)
			or $this->throwDbError($this->db, "Could not execute query '$query'");
		
		return $this->db;
		
	}
	
	private function throwDbError($db, $message) {
		throw new IamlRuntimeException($message . " [error = " . $this->errorInfo($db) . "]"); 
	}
	
	private function errorInfo($db) {
		return $this->debugString($db->errorInfo());
	}
	
	private function debugString($obj) {
		if (is_array($obj)) {
			$r = "";
			foreach ($obj as $key => $value) {
				$r .= "($key => " . $this->debugString($value) . ") ";
			}
			return "[ $r]";
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
 */
function evaluate_select_wire($db_name, $source_id, $source_class, $query, $args) {
	log_message("Evaluate select wire: source_id = $source_id, source_class = $source_class, query = $query"); 

	// get all joins
	global $compose_domain_joins_done_already;
	$compose_domain_joins_done_already = array(); 
	$joins = compose_domain_joins($source_id);
	
	$joined_query = "SELECT * FROM $source_class " 
		. implode(" ", $joins)
		. " WHERE $query";
	
	log_message("Evaluate select wire: Composed query: " . preg_replace("/[ \r\n\t]+/im", " ", $joined_query));
		
	$db_query = new DatabaseQuery($db_name);
	$row = $db_query->fetchFirst($joined_query, $args);
	
	return $row;
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
	foreach ($all_joins[$source_id] as $target_id => $join_query) {
		// don't repeat multiple inheritance joins
		if (!in_array($target_id, $compose_domain_joins_done_already)) {
			$result[] = $join_query;
			$result = array_merge($result, compose_domain_joins($target_id));
		}
	}

	return $result;
}