<?php

/**
 * Tests the domain implementation.
 */

require("../domain.php");
require("../../databases.php");

function get_all_domain_joins() {
	$direct_joins = array();
	/*
    // Undergraduate
    $direct_joins["model_1236407970c_1d"] = array("model_1236407970c_19" => "JOIN Student ON
						Student.generated_primary_key 
						= Undergraduate.Student_generated_primary_key", );
	*/
	$direct_joins["1kg992k6t4"] = array();
	return $direct_joins;
}

class DomainSchema_News extends DomainSchema {
	
	private function __construct() {
		$this->attributes = array(
			"id" => DomainAttribute_News_Id::getInstance($this),
			"title" => DomainAttribute_News_Title::getInstance($this),
		);
		$this->table_name = "News";
		$this->source_id = '1kg992k6t4';
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSchema_News();
		}
		return self::$instance;
	}
	
}

class DomainAttribute_News_Id extends DomainAttribute {
	private function __construct($schema) {
		$this->schema = $schema;
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
	}
	
	// the current instance
	static $instance = null;
	public static function getInstance($schema) {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_News_Id($schema);
		}
		return self::$instance;
	}
	
}

class DomainAttribute_News_Title extends DomainAttribute {
	private function __construct($schema) {
		$this->schema = $schema;
		$this->isPrimaryKey = false;
		$this->type = 'iamlString';
	}
	
	// the current instance
	static $instance = null;
	public static function getInstance($schema) {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_News_Title($schema);
		}
		return self::$instance;
	}
}

class DomainSource_NewsDB extends DomainSource {
	
	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->type = 'RELATIONAL_DB';
		$this->file = '1kg992k6t4.db';
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSource_NewsDB();
		}
		return self::$instance;
	}

}

// set up the test: create the database
{
	if (file_exists("1kg992k6t4.db")) {
		unlink("1kg992k6t4.db");
	}
	
	$query = new DatabaseQuery("sqlite:1kg992k6t4.db");
	$query->execute("CREATE TABLE News ( id integer primary key autoincrement, title varchar(255))");
	$query->execute("INSERT INTO News (id, title) VALUES (?, ?)", array(1, 'hello'));
	$query->execute("INSERT INTO News (id, title) VALUES (?, ?)", array(2, 'world'));
	$query->execute("INSERT INTO News (id, title) VALUES (?, ?)", array(3, 'alligator'));
	$query->execute("INSERT INTO News (id, title) VALUES (?, ?)", array(4, 'kitten'));

	// init the offset
	$_SESSION["offset"] = 0;
	
}

function printit($instance) {
	$id = $instance["id"]->getValue();
	echo "id: " . ($id === null ? "null" : $id)  . "\n";
	$title = $instance["title"]->getValue();
	echo "title: " . ($title === null ? "null" : $title) . "\n";
}

// list of tests
require("test1.php");
require("test2.php");
require("test3.php");
require("test4.php");

function clean_newlines($s) {
	$s = str_replace("\r\n", "\n", $s);
	$s = str_replace("\r", "\n", $s);

	// trim
	return trim($s);
}

// ---
// various mock methods 

function log_message($message) {
	// echo "[log] $message\n";
}

/**
 * Similar to a Java's RuntimeException.
 * We can also pass along additional information.
 */
class IamlRuntimeException extends Exception {
	var $more_info;

	public function __construct($message = "", $more_info = "") {
		parent::__construct($message);
		$this->more_info = $more_info;
	}
}

/**
 * For some reason, we can't have '$x = $a or throw new Ex()'
 * so we need to wrap it in a function.
 */
function throw_new_IamlRuntimeException($message) {
	/* throw an exception */
	throw new IamlRuntimeException($message);
}


