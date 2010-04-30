<?php

// this one doesn't autosave, and it has multiple results
class DomainIterator_News_ergqa3bdfbd extends DomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = "";
		$this->order_ascending = true;
		$this->query = "1";
		$this->autosave = false;
	}
	
	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_ergqa3bdfbd();
		}
		return self::$instance;
	}
	
	public function constructArgs() {
		return array(
			// no args
		);
	}
	
	public function getOffset() {
		if (!isset($_SESSION["offset3"])) {
			$this->setOffset(0);
		}
		return $_SESSION["offset3"];
	}
	
	public function setOffset($value) {
		$_SESSION["offset3"] = $value;
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
	$iterator = DomainIterator_News_ergqa3bdfbd::getInstance();
	$iterator->reset();

	$instance = $iterator->toArray();
	printit($instance);
	
	// iterate over all others
	while ($iterator->hasNext()) {
		$instance = $iterator->next();
		
		printit($instance);
	}
	
	// print out the result count
	echo "results = " . $iterator->count();
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected3.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
