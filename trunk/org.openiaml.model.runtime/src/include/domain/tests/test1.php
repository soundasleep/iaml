<?php

class DomainIterator_News_5i92kg92 extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = null;
		$this->order_ascending = true;
		$this->query = "id = :id";
		$this->autosave = true;
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_5i92kg92();
		}
		return self::$instance;
	}

	public function constructArgs() {
		return array(
			"id" => "3",
		);
	}

	public function getOffset() {
		return $_SESSION["offset"];
	}

	public function setOffset($value) {
		$_SESSION["offset"] = $value;
	}

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}

	public function getStoredValue($key, $default = false) {
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

echo "[test 1] ";
ob_start();
{
	// get the current instance
	$instance = DomainIterator_News_5i92kg92::getInstance();
	// then the attribute
	$title = $instance->getAttributeInstance(DomainAttribute_News_Title::getInstance())->getValue();

	// set the attribute
	$instance->getAttributeInstance(DomainAttribute_News_Title::getInstance())->setValue('new title');
}

// get an iterator of instances
{
	$iterator = DomainIterator_News_5i92kg92::getInstance();
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
$expected = file_get_contents("expected.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
