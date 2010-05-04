<?php

// here we define two schemas with the same attribute name

// Student extends User
class DomainSchema_Users13 extends DomainSchema {

  private function __construct() {
	$this->addAttribute(DomainAttribute_User_Id13::getInstance());
	$this->addAttribute(DomainAttribute_User_Name13::getInstance());
    $this->table_name = "Users";
    $this->source_id = $this->table_name . "_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSchema_Users13();
    }
    return self::$instance;
  }

}

class DomainSchema_DefaultRoles13 extends DomainSchema {

  private function __construct() {
	$this->addAttribute(DomainAttribute_DefaultRole_Id13::getInstance());
	$this->addAttribute(DomainAttribute_DefaultRole_UserId13::getInstance());
    $this->table_name = "DefaultRole";
    $this->source_id = $this->table_name . "_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSchema_DefaultRoles13();
    }
    return self::$instance;
  }

}

// attribute definitions
class DomainAttribute_User_Id13 extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = true;
    $this->type = 'iamlInteger';
    $this->name = "id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_User_Id13();
    }
    return self::$instance;
  }

}

class DomainAttribute_User_Name13 extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlString';
    $this->name = "name";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_User_Name13();
    }
    return self::$instance;
  }

}

class DomainAttribute_DefaultRole_Id13 extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = true;
    $this->type = 'iamlInteger';
    $this->name = "id";		// same name
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_DefaultRole_Id13();
    }
    return self::$instance;
  }

}

class DomainAttribute_DefaultRole_UserId13 extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlInteger';
    $this->name = "User_id";
    $this->extends = DomainAttribute_User_Id13::getInstance();
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_DefaultRole_UserId13();
    }
    return self::$instance;
  }

}

// we use the same database for this contents
class DomainSource_UsersDB13 extends DomainSource {

  private function __construct() {
    $this->schemas = array(
      DomainSchema_Users13::getInstance(),
      DomainSchema_DefaultRoles13::getInstance(),
    );
    $this->type = 'RELATIONAL_DB';
    $this->file = 'sqlite:users.db';
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSource_UsersDB13();
    }
    return self::$instance;
  }

}

// now define an iterator that is new, but doesn't autosave
class DomainIterator_New13 extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = DomainSchema_DefaultRoles13::getInstance();
    $this->source = DomainSource_UsersDB13::getInstance();
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
      self::$instance = new DomainIterator_New13();
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

// insert in a blank User into the database
{
	if (file_exists("users.db")) {
		unlink("users.db");
	}

	$query = new DatabaseQuery("sqlite:users.db");
	$query->execute("CREATE TABLE Users ( id integer primary key autoincrement, name varchar(64) )");
	$query->execute("INSERT INTO Users (id, name) VALUES (?, ?)", array(32, "Initial Data"));

}

echo "[test 13] ";
ob_start();
{
  // get the current instance
  $instance = DomainIterator_New13::getInstance();

  // print it out
  echo "1:\n";
  printit4($instance);

  // set some values
  $instance->getAttribute('name')->setValue("test@openiaml.org");
  echo "2:\n";
  printit4($instance);

  // reload it; should lose the information
  $instance->reload();
  echo "3:\n";
  printit4($instance);

  // set values again
  $instance->getAttribute('name')->setValue("another@openiaml.org");
  echo "4:\n";
  printit4($instance);

  // clear the session and the instance
  DomainIterator_New13::resetInstance();

  // the values should have persisted
  $instance = DomainIterator_New13::getInstance();
  echo "5:\n";
  printit4($instance);

  // now save it
  $instance->save();
  echo "6:\n";
  printit4($instance);

  // reload it
  $instance->reload();
  echo "7:\n";
  printit4($instance);

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected13.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
  echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
  echo "PASS\n";
}
