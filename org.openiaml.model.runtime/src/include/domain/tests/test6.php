<?php

// here we define some inheritance structures, and new schemas

class DomainSchema_Users extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"user_id" => DomainAttribute_User_Id::getInstance(),
			"email" => DomainAttribute_User_Email::getInstance(),
		);
		$this->table_name = "Users";
		$this->source_id = $this->table_name . "_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSchema_Users();
		}
		return self::$instance;
	}

}

// Admin extends User
class DomainSchema_Admins extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"admin_id" => DomainAttribute_Admin_Id::getInstance(),
			"name" => DomainAttribute_Admin_Name::getInstance(),
			"admin_user_id" => DomainAttribute_Admin_User_Id::getInstance(),
		);
		$this->table_name = "Admins";
		$this->source_id = $this->table_name . "_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSchema_Admins();
		}
		return self::$instance;
	}

}

// and SuperAdmin extends Admin, but adds no new data
class DomainSchema_SuperAdmins extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"super_id" => DomainAttribute_SuperAdmin_Id::getInstance(),
		);
		$this->table_name = "SuperAdmins";
		$this->source_id = $this->table_name . "_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSchema_SuperAdmins();
		}
		return self::$instance;
	}

}

class DomainAttribute_User_Id extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
		$this->name = "user_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_User_Id();
		}
		return self::$instance;
	}

}

class DomainAttribute_User_Email extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = false;
		$this->type = 'iamlEmail';
		$this->name = "email";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_User_Email();
		}
		return self::$instance;
	}
}

class DomainAttribute_Admin_Id extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
		$this->name = "admin_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_Admin_Id();
		}
		return self::$instance;
	}

}

class DomainAttribute_Admin_Name extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = false;
		$this->type = 'iamlString';
		$this->name = "name";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_Admin_Name();
		}
		return self::$instance;
	}
}

// this extends an existing attribute
class DomainAttribute_Admin_User_Id extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = false;
		$this->type = 'iamlInteger';
		$this->name = "admin_user_id";

		$this->extends = DomainAttribute_User_Id::getInstance();
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_Admin_User_Id();
		}
		return self::$instance;
	}

}

class DomainAttribute_SuperAdmin_Id extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
		$this->name = "super_id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_SuperAdmin_Id();
		}
		return self::$instance;
	}

}

// and define the source for it
class DomainSource_AdminsDB extends DomainSource {

	private function __construct() {
		$this->schemas = array(
			DomainSchema_Users::getInstance(),
			DomainSchema_Admins::getInstance(),
			DomainSchema_SuperAdmins::getInstance(),
		);
		$this->type = 'RELATIONAL_DB';
		$this->file = 'sqlite:admins.db';
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSource_AdminsDB();
		}
		return self::$instance;
	}

}

// now define an iterator that is new, and autosaves
class DomainIterator_AdminNewIterator extends DomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_Admins::getInstance();
		$this->source = DomainSource_AdminsDB::getInstance();
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
			self::$instance = new DomainIterator_AdminNewIterator();
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
		if (!isset($_SESSION["newid3_$key"]) || $_SESSION["newid3_$key"] === null) {
			return null;
		}

		return $_SESSION["newid3_$key"];
	}

	public function setNewInstanceID($key, $id) {
		log_message("[test6] Set new instance ID '$key' to '$id'");
		$_SESSION["newid3_$key"] = $id;
	}

}

function printit3($instance) {
	foreach ($instance as $key => $value) {
		echo $key . ": " . ($value->getValue() === null ? "null" : $value->getValue()) . "\n";
	}
}

{
	// reset database
	if (file_exists("admins.db")) {
		unlink("admins.db");
	}
}

echo "[test 6] ";
ob_start();
{
	// get the current instance
	$instance = DomainIterator_AdminNewIterator::getInstance();

	// print it out
	echo "1:\n";
	printit3($instance->toArray());

	// set some values
	$instance->getAttribute('email')->setValue("test@openiaml.org");
	echo "2:\n";
	printit3($instance->toArray());

	$instance->getAttribute('name')->setValue("Test User");
	echo "3:\n";
	printit3($instance->toArray());

	// don't save it manually (but autosave = true, so it's already saved)
	// this should just reload the same instance
	$instance->reload();
	echo "4:\n";
	printit3($instance->toArray());

	// set it again
	$instance->getAttribute('email')->setValue("another@openiaml.org");
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
	$instance->getAttribute('name')->setValue("Changed Name");
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
$expected = file_get_contents("expected6.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
