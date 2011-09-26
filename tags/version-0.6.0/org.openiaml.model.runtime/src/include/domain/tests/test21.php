<?php

// needs test1.php included

class DomainIterator_News_onIterate extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainType_News::getInstance();
		$this->source = DomainSource_NewsDB::getInstance();
		$this->order_by = null;
		$this->order_ascending = true;
		$this->query = "1";
		$this->autosave = true;
		$this->limit = 7;
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_News_onIterate();
		}
		return self::$instance;
	}

	public function constructArgs() {
		return array();
	}

	public function getOffset() {
		return $_SESSION["offset_onIterate"];
	}

	public function setOffset($value) {
		$_SESSION["offset_onIterate"] = $value;
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

	/**
  	 * Extends onIterate to test that it is called.
	 */
	public function onIterate() {
		echo "onIterate: " . $this->getOffset() . "\n";
	}

}

echo "[test 21] ";
ob_start();

// get an iterator of instances
{
  $iterator = DomainIterator_News_onIterate::getInstance();
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

echo "\n---\n";

// try resetting the iterator lots of times; onIterate will be
// called many times, as reset() reloads the current instance
{
  $iterator = DomainIterator_News_onIterate::getInstance();
  $iterator->reset();
  $iterator->reset();
  $iterator->reset();
  $iterator->reset();
  $iterator->reset();

  // print out the result count
  echo "results = " . $iterator->count();
}
$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected21.txt");
