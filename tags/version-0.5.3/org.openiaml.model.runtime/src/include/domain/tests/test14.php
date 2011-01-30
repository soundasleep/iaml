<?php

// allow us to define when objects are reloaded or repopulated

class DomainIterator_Select14 extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = DomainSchema_Users13::getInstance();
    $this->source = DomainSource_UsersDB13::getInstance();
    $this->order_by = null;
    $this->order_ascending = true;
    $this->query = "Users.id = :id";
    $this->autosave = false;
    $this->is_new = false;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainIterator_Select14();
    }
    return self::$instance;
  }

  // reset the current instance
  public static function resetInstance() {
	self::$instance->current_result = null;
    self::$instance = null;
  }

  public function constructArgs() {
	  global $test14_id;
    return array(
      "id" => $test14_id,
    );
  }

	public function getOffset() {
		if (!isset($_SESSION[get_class($this) . "_offset"])) {
			$this->setOffset(0);
		}
		return $_SESSION[get_class($this) . "_offset"];
	}

	public function setOffset($value) {
		$_SESSION[get_class($this) . "_offset"] = $value;
	}

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}

}

echo "[test 14] ";
ob_start();
{
  // to select
  $test14_id = 33;

  // get the current instance
  $instance = DomainIterator_Select14::getInstance();

  // print it out
  echo "1:\n";
  printit4($instance);

  // reload it
  DomainIterator_Select14::resetInstance();
  $instance = DomainIterator_Select14::getInstance();
  echo "2:\n";
  printit4($instance);

  // set a value
  $instance->getAttributeInstance(DomainAttribute_User_Name13::getInstance())->setValue("New Name");
  echo "3:\n";
  printit4($instance);

  // reload it; it should persist
  DomainIterator_Select14::resetInstance();
  $instance = DomainIterator_Select14::getInstance();
  echo "4:\n";
  printit4($instance);

  // but not if we change the query arguments
  $test14_id = 32;
  DomainIterator_Select14::resetInstance();
  $instance = DomainIterator_Select14::getInstance();
  echo "5:\n";
  printit4($instance);

  // set a value
  $instance->getAttributeInstance(DomainAttribute_User_Name13::getInstance())->setValue("Newer Name");
  echo "6:\n";
  printit4($instance);

  // what if we change the query without resetting the instance?
  // we should not have saved the result for id '33' above
  $test14_id = 33;
  DomainIterator_Select14::resetInstance();
  $instance = DomainIterator_Select14::getInstance();
  echo "7:\n";
  printit4($instance);

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected14.txt");
