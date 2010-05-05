<?php

// this one doesn't autosave, and it has multiple results
class DomainIterator_News_ergqa3bdfbd extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = null;
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

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}

}

echo "[test 3] ";
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

	// if we call next() on an end iterator, we must throw an exception
	echo "\n---\n";
	try {
		$iterator->next();
		echo "next() did not throw an exception\n";
	} catch (IamlDomainException $e) {
		echo "Caught next() exception successfully\n";
	}

	// move iterator back to start
	$iterator->reset();

	// if we call previous(), it should fail as well
	try {
		$iterator->previous();
		echo "previous() did not throw an exception\n";
	} catch (IamlDomainException $e) {
		echo "Caught previous() exception successfully\n";
	}

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected3.txt");
