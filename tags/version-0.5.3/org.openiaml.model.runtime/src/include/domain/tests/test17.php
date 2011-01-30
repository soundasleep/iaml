<?php

// a test to check that if we define a schema with no primary key,
// we get a warning in the log

class DomainSchema_Users17 extends DomainSchema {

  private function __construct() {
	//$this->addAttribute(DomainAttribute_User_Id13::getInstance());
	$this->addAttribute(DomainAttribute_User_Name13::getInstance());
    $this->table_name = "Users";
    $this->source_id = $this->table_name . "_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSchema_Users17();
    }
    return self::$instance;
  }

}

class DomainSource_UsersDB17 extends DomainSource {

  private function __construct() {
    $this->schemas = array(
      DomainSchema_Users17::getInstance(),
    );
    $this->type = 'RELATIONAL_DB';
    $this->file = 'sqlite:users_2.db';
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSource_UsersDB17();
    }
    return self::$instance;
  }

}

// now define an iterator that is new, but doesn't autosave
class DomainIterator_New17 extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = DomainSchema_Users17::getInstance();
    $this->source = DomainSource_UsersDB17::getInstance();
    $this->order_by = null;
    $this->order_ascending = true;
    $this->query = "";
    $this->autosave = false;
    $this->is_new = true;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainIterator_New17();
    }
    return self::$instance;
  }


  // reset the current instance
  public static function resetInstance() {
	self::$instance->current_result = null;
    self::$instance = null;
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

  public function getNewInstanceID($key) {
    if (!isset($_SESSION[get_class($this) . "_$key"]) || $_SESSION[get_class($this) . "_$key"] === null) {
      return null;
    }

    return $_SESSION[get_class($this) . "_$key"];
  }

  public function setNewInstanceID($key, $id) {
    $_SESSION[get_class($this) . "_$key"] = $id;
  }

  public function setStoredValue($key, $value) {
	  parent::setStoredValue($key, $value);
  }

}


echo "[test 17] ";
ob_start();
{
  // get the current instance
  $iterator = DomainIterator_New17::getInstance();
  printit4($iterator);
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected17.txt");
