<?php

// here we define some inheritance structures, and new schemas

// Student extends User
class DomainSchema_Students extends DomainSchema {

  private function __construct() {
	$this->addAttribute(DomainAttribute_Student_Id::getInstance());
	$this->addAttribute(DomainAttribute_Student_Degree::getInstance());
    $this->addAttribute(DomainAttribute_Student_User_Id::getInstance());
    $this->table_name = "Students";
    $this->source_id = $this->table_name . "_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSchema_Students();
    }
    return self::$instance;
  }

}

// StudentAdmin extends both Student and Admin (multiple inheritance)
class DomainSchema_StudentAdmins extends DomainSchema {

  private function __construct() {
    $this->addAttribute(DomainAttribute_StudentAdmin_Id::getInstance());
    $this->addAttribute(DomainAttribute_StudentAdmin_Student_Id::getInstance());
    $this->addAttribute(DomainAttribute_StudentAdmin_Admin_Id::getInstance());
    $this->table_name = "StudentAdmins";
    $this->source_id = $this->table_name . "_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSchema_StudentAdmins();
    }
    return self::$instance;
  }

}

// attribute definitions
class DomainAttribute_Student_Id extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = true;
    $this->type = 'iamlInteger';
    $this->name = "student_id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_Student_Id();
    }
    return self::$instance;
  }

}

class DomainAttribute_Student_Degree extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlString';
    $this->name = "degree";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_Student_Degree();
    }
    return self::$instance;
  }
}

class DomainAttribute_Student_User_Id extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlInteger';
    $this->name = "student_user_id";

    $this->extends = DomainAttribute_User_Id::getInstance();	// extends
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_Student_User_Id();
    }
    return self::$instance;
  }

}

// StudentAdmin
class DomainAttribute_StudentAdmin_Id extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = true;
    $this->type = 'iamlInteger';
    $this->name = "id";
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_StudentAdmin_Id();
    }
    return self::$instance;
  }

}

class DomainAttribute_StudentAdmin_Student_Id extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlInteger';
    $this->name = "student_id";

    $this->extends = DomainAttribute_Student_Id::getInstance();	// extends
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_StudentAdmin_Student_Id();
    }
    return self::$instance;
  }

}

class DomainAttribute_StudentAdmin_Admin_Id extends DomainAttribute {
  private function __construct() {
    $this->isPrimaryKey = false;
    $this->type = 'iamlInteger';
    $this->name = "admin_id";

    $this->extends = DomainAttribute_Admin_Id::getInstance();	// extends
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainAttribute_StudentAdmin_Admin_Id();
    }
    return self::$instance;
  }

}

// we use the same database for this contents
class DomainSource_AdminsDBWithStudents extends DomainSource {

  private function __construct() {
    $this->schemas = array(
      DomainSchema_Users::getInstance(),
      DomainSchema_Admins::getInstance(),
      DomainSchema_SuperAdmins::getInstance(),
      DomainSchema_Students::getInstance(),
      DomainSchema_StudentAdmins::getInstance(),
    );
    $this->type = 'RELATIONAL_DB';
    $this->file = 'sqlite:admins.db';
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainSource_AdminsDBWithStudents();
    }
    return self::$instance;
  }

}

// now define an iterator that is new, and autosaves
class DomainIterator_StudentAdminNewIterator extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = DomainSchema_StudentAdmins::getInstance();
    $this->source = DomainSource_AdminsDBWithStudents::getInstance();
    $this->order_by = null;
    $this->order_ascending = true;
    $this->query = "";
    $this->autosave = true;
    $this->is_new = true;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new DomainIterator_StudentAdminNewIterator();
    }
    return self::$instance;
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
    if (!isset($_SESSION["newid5_$key"]) || $_SESSION["newid5_$key"] === null) {
      return null;
    }

    return $_SESSION["newid5_$key"];
  }

  public function setNewInstanceID($key, $id) {
    $_SESSION["newid5_$key"] = $id;
  }

}

function printit4($instance) {
	foreach ($instance->getAttributeInstances() as $attribute) {
		$key = $attribute->getName();
		$value = $attribute->getValue();
		echo $attribute->getContainingSchema()->getTableName() . "." . $key . ": " . ($value === null ? "null" : $value) . "\n";
	}
}

echo "[test 8] ";
ob_start();
{
  // get the current instance
  $instance = DomainIterator_StudentAdminNewIterator::getInstance();

  // print it out
  echo "1:\n";
  printit3($instance->toArray());

  // set some values
  $instance->getAttributeInstance(DomainAttribute_User_Email::getInstance())->setValue("test@openiaml.org");
  echo "2:\n";
  printit3($instance->toArray());

  // print out the exact contents of the instance
  echo "2 (full):\n";
  printit4($instance);

  $instance->getAttributeInstance(DomainAttribute_Admin_Name::getInstance())->setValue("Test User");
  $instance->getAttributeInstance(DomainAttribute_Student_Degree::getInstance())->setValue("B.Sc.");
  echo "3:\n";
  printit3($instance->toArray());

  // don't save it manually (but autosave = true, so it's already saved)
  // this should just reload the same instance
  $instance->reload();
  echo "4:\n";
  printit3($instance->toArray());

  // set it again
  $instance->getAttributeInstance(DomainAttribute_User_Email::getInstance())->setValue("another@openiaml.org");
  echo "5:\n";
  printit3($instance->toArray());

  // save it manually; makes no difference, already saved
  $instance->save();
  echo "6:\n";
  printit3($instance->toArray());

  // and reload it
  $instance->reload();
  echo "7:\n";
  printit3($instance->toArray());

  // create a new one
  $instance->createNew();
  echo "8:\n";
  printit3($instance->toArray());

  // set it
  $instance->getAttributeInstance(DomainAttribute_Admin_Name::getInstance())->setValue("Changed Name");
  $instance->getAttributeInstance(DomainAttribute_Student_Degree::getInstance())->setValue("B.A.");
  echo "9:\n";
  printit3($instance->toArray());

  // save it
  $instance->save();
  echo "9:\n";
  printit3($instance->toArray());

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected8.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
  echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
  echo "PASS\n";
}
