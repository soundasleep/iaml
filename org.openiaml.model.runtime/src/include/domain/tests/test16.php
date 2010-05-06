<?php

// a test to check that when there are no results for a SelectWire, we do
// not persist the empty results

class DomainIterator_News_Test16 extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = null;
		$this->order_ascending = true;
		$this->query = "id = :id";
		$this->autosave = false;
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_Test16();
		}
		return self::$instance;
	}

	public function constructArgs() {
		return array(
			"id" => "664",
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

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}

}

echo "[test 16] ";
ob_start();
{
	// get the current instance
	$iterator = DomainIterator_News_Test16::getInstance();
	try {
		printit4($iterator);
		echo "Failed to catch IamlDomainException\n";
	} catch (IamlDomainException $e) {
		echo "Caught IamlDomainException OK\n";
	}

	// but now insert the new data
	{
		$query = new DatabaseQuery("sqlite:1kg992k6t4.db");
		$query->execute("INSERT INTO News (id, title) VALUES (?, ?)", array(664, 'inserted value'));
	}

	// it should now be refreshed
	printit4($iterator);
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected16.txt");
