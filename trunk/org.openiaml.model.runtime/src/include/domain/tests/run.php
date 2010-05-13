<?php

/**
 * Tests the domain implementation.
 */

require("../domain.php");
require("../../databases.php");
require("../../types/types.php");

abstract class DefaultDomainIterator extends DomainIterator {

	/**
	 * Note that PHP's <code>is_set()</code> returns <code>false</code> for variables that are set to <code>NULL</code>
	 */
	public function getStoredValue($key, $default = null) {
		$target_key = get_class($this) . "_" . $key;
		if (isset($_SESSION[$target_key])) {
			return $_SESSION[$target_key];
		}
		return $default;
	}

	public function setStoredValue($key, $value) {
		$target_key = get_class($this) . "_" . $key;
		$_SESSION[$target_key] = $value;
	}

}

class DomainSchema_News extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"id" => DomainAttribute_News_Id::getInstance(),
			"title" => DomainAttribute_News_Title::getInstance(),
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
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
		$this->name = "id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_News_Id();
		}
		return self::$instance;
	}

}

class DomainAttribute_News_Title extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = false;
		$this->type = 'iamlString';
		$this->name = "title";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_News_Title();
		}
		return self::$instance;
	}
}

class DomainSource_NewsDB extends DomainSource {

	private function __construct() {
		$this->schemas = array(DomainSchema_News::getInstance());
		$this->type = 'RELATIONAL_DB';
		$this->file = 'sqlite:1kg992k6t4.db';
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
$enable = false;
require("test1.php");
require("test2.php");
require("test3.php");
require("test4.php");
require("test5.php");
require("test6.php");
require("test7.php");
require("test8.php");
require("test9.php");
require("test10.php");
require("test11.php");
require("test12.php");
require("test13.php");
require("test14.php");
require("test15.php");
require("test16.php");
require("test17.php");
//$enable = true;	// for debug
require("test18.php");
require("test19.php");

function clean_newlines($s) {
	$s = str_replace("\r\n", "\n", $s);
	$s = str_replace("\r", "\n", $s);

	// trim
	return trim($s);
}

// ---
// various mock methods

/**
 * Hide the log message, unless it contains a warning message 'warning'
 * or error 'error'.
 */
function log_message($message) {
	global $enable;
	if ($enable
		|| strpos(strtolower($message), 'warning') !== false
		|| strpos(strtolower($message), 'error') !== false) echo "[log] $message\n";
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

function compareTestResults($output, $file) {
	// clean out newlines etc
	$output = clean_newlines($output);
	$expected = clean_newlines(file_get_contents($file));

	if ($expected !== $output) {
		echo "ERROR: Results did not match expected:\n";

		$filename = "temp";	// a temporary file
		if (file_exists($filename)) {
			echo "ERROR: Cannot create file $filename\n";
			die(1);
		}
		file_put_contents($filename, str_replace("\n", "\r\n", $output));
		passthru("diff -u -a --label result $filename $file");
		echo "\n";
		unlink($filename);	// then delete it
	} else {
		echo "PASS\n";
	}
}
