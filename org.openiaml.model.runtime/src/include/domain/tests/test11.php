<?php

// an iterator that has a limit set that is also sorted
class DomainIterator_News_LimitedSorted extends DomainIterator {

  private function __construct() {
    $this->schema = DomainSchema_News::getInstance();
    $this->source = DomainSource_NewsDB::getInstance();
    $this->order_by = DomainAttribute_News_Title::getInstance();
    $this->order_ascending = false;
    $this->query = "1";
    $this->limit = 3;   // a limit of three
    $this->autosave = false;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainIterator_News_LimitedSorted();
    }
    return self::$instance;
  }

  public function constructArgs() {
    return array(
      // no args
    );
  }

  public function getOffset() {
    if (!isset($_SESSION["offset10"])) {
      $this->setOffset(0);
    }
    return $_SESSION["offset10"];
  }

  public function setOffset($value) {
    $_SESSION["offset10"] = $value;
  }

  public function getNewInstanceID($key) {
    throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
  }

  public function setNewInstanceID($key, $id) {
    throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
  }

}

echo "[test 11] ";
ob_start();
{
  // get the current instance
  $instance = DomainIterator_News_LimitedSorted::getInstance();
  printit4($instance);

  while ($instance->hasNext()) {
    echo "next\n";
    $instance->next();
    printit4($instance);
  }

  while ($instance->hasPrevious()) {
	  echo "previous\n";
	  $instance->previous();
	  printit4($instance);
  }

  echo "end\n";
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected11.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
  echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
  echo "PASS\n";
}
