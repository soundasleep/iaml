<?php

// an identical iterator to 18
class RSSIterator19 extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = RSSNews::getInstance();
    $this->source = RSSSource18::getInstance();
    $this->order_by = null;
    $this->order_ascending = true;
    $this->query = "1";
    $this->autosave = false;		// read-only source by default
    $this->is_new = false;
    $this->limit = 10;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new RSSIterator19();
    }
    return self::$instance;
  }

  public function constructArgs() {
    return array(
      // no args
    );
  }

	public function getOffset() {
		if (!isset($_SESSION["offset_".get_class($this)])) {
			$this->setOffset(0);
		}
		return $_SESSION["offset_".get_class($this)];
	}

	public function setOffset($value) {
		$_SESSION["offset_".get_class($this)] = $value;
	}

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}
}

echo "[test 19] ";
ob_start();
{
	// delete the database
	if (file_exists("remote.db")) {
		unlink("remote.db");
	}

	// make sure that we can get the count, and that the count is accurate
	$iterator = RSSIterator19::getInstance();
	echo "count = " . $iterator->count() . "\n";

	// get the current instance
	printit4($iterator);

	// iterate over all others
	while ($iterator->hasNext()) {
		$iterator->next();
		printit4($iterator);
	}

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected19.txt");
