<?php

// this one doesn't autosave
class DomainIterator_News_bdb2t211 extends DomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = "";
		$this->order_ascending = true;
		$this->query = "id = :id";
		$this->autosave = false;
	}
	
	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_bdb2t211();
		}
		return self::$instance;
	}
	
	public function constructArgs() {
		return array(
			"id" => "4",
		);
	}
	
	public function getOffset() {
		if (!isset($_SESSION["offset2"])) {
			$this->setOffset(0);
		}
		return $_SESSION["offset2"];
	}
	
	public function setOffset($value) {
		$_SESSION["offset2"] = $value;
	}
	
	public function getNewInstanceID() {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}
	
	public function setNewInstanceID($id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}
	
}

ob_start();
{
	// get the current instance
	$instance = DomainIterator_News_bdb2t211::getInstance();
	// then the attribute
	$title = $instance->getAttribute('title')->getValue();
	echo "1: $title\n"; 
	
	// set the attribute
	$instance->getAttribute('title')->setValue('new title');
	
	// get it back
	$title = $instance->getAttribute('title')->getValue();
	echo "2: $title\n";
	
	// reload it
	$instance->reload();
	$title = $instance->getAttribute('title')->getValue();
	echo "3: $title\n";
	
	// set it again
	$instance->getAttribute('title')->setValue('another title');
	// save it now
	$instance->save();
	
	$title = $instance->getAttribute('title')->getValue();
	echo "4: $title\n";
	
	// reload it
	$instance->reload();
	$title = $instance->getAttribute('title')->getValue();
	echo "5: $title\n";
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected2.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
