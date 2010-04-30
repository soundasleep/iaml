<?php

// this one doesn't autosave, but is a new instance
class DomainIterator_News_rbtbtr22d extends DomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = "";
		$this->order_ascending = true;
		$this->query = "";
		$this->autosave = false;
		$this->is_new = true;
	}
	
	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_rbtbtr22d();
		}
		return self::$instance;
	}
	
	public function constructArgs() {
		return array(
			// no args
		);
	}
	
	public function getOffset() {
		throw new IamlDomainException("Cannot get the offset for a new object: " . get_class($this));
	}
	
	public function setOffset($value) {
		throw new IamlDomainException("Cannot set the offset for a new object: " . get_class($this));
	}
	
	public function getNewInstanceID() {
		if (!isset($_SESSION["newid"]) || $_SESSION["newid"] === null) {
			return null;
		}
		
		return $_SESSION["newid"];
	}
	
	public function setNewInstanceID($id) {
		$_SESSION["newid"] = $id;
	}
	
}

ob_start();
{
	// get the current instance
	$instance = DomainIterator_News_rbtbtr22d::getInstance();
	
	// print it out
	echo "1:\n";
	printit($instance->toArray());
	
	// set some values
	$instance->getAttribute('title')->setValue("new title");
	echo "2:\n";
	printit($instance->toArray());
	
	// don't save it
	$instance->reload();
	echo "3:\n";
	printit($instance->toArray());
	
	// set it again
	$instance->getAttribute('title')->setValue("another title");
	echo "4:\n";
	printit($instance->toArray());
	
	// save it
	$instance->save();
	echo "5:\n";
	printit($instance->toArray());

	// and reload it
	$instance->reload();
	echo "6:\n";
	printit($instance->toArray());
	
	// create a new one
	$instance->createNew();
	echo "7:\n";
	printit($instance->toArray());
	
	// set it
	$instance->getAttribute('title')->setValue("a new title");
	echo "8:\n";
	printit($instance->toArray());
	
	// save it
	$instance->save();
	echo "9:\n";
	printit($instance->toArray());		
		
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected4.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
