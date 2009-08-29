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

