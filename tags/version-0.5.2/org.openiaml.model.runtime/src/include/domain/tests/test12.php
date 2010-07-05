<?php

// uses the Test6 schema and source, but defines an
// iterator that does not autosave, yet persists over sessions

class DomainIterator_AdminNewIteratorPersists extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_Admins::getInstance();
		$this->source = DomainSource_AdminsDB::getInstance();
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
			self::$instance = new DomainIterator_AdminNewIteratorPersists();
		}
		return self::$instance;
	}

	// reset the instance
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
		if (!isset($_SESSION["newid4_$key"]) || $_SESSION["newid4_$key"] === null) {
			return null;
		}

		return $_SESSION["newid4_$key"];
	}

	public function setNewInstanceID($key, $id) {
		log_message("[test7] Set new instance ID '$key' to '$id'");
		$_SESSION["newid4_$key"] = $id;
	}

}

{
	// don't reset database!

	// but reset the session
	$_SESSION = array();
}

echo "[test 12] ";
ob_start();
{
	// get the current instance
	$instance = DomainIterator_AdminNewIteratorPersists::getInstance();

	// print it out
	echo "1:\n";
	printit3($instance->toArray());

	// set some values
	$instance->getAttributeInstance(DomainAttribute_User_Email::getInstance())->setValue("test@openiaml.org");
	$instance->getAttributeInstance(DomainAttribute_Admin_Name::getInstance())->setValue("Test User");
	echo "2:\n";
	printit3($instance->toArray());

	// reload it; it's not autosaved, so the content is lost
	$instance->reload();
	echo "3:\n";
	printit3($instance->toArray());

	// set it again
	$instance->getAttributeInstance(DomainAttribute_User_Email::getInstance())->setValue("another@openiaml.org");
	$instance->getAttributeInstance(DomainAttribute_Admin_Name::getInstance())->setValue("Another User");
	echo "4:\n";
	printit3($instance->toArray());

	// reload the entire instance
	$instance = null;
	DomainIterator_AdminNewIteratorPersists::resetInstance();
	$instance = DomainIterator_AdminNewIteratorPersists::getInstance();

	// it should still all be there
	echo "5:\n";
	printit3($instance->toArray());

	// save it manually
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

	// reload the entire instance
	$instance = null;
	DomainIterator_AdminNewIteratorPersists::resetInstance();
	$instance = DomainIterator_AdminNewIteratorPersists::getInstance();

	echo "9:\n";
	printit3($instance->toArray());

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected12.txt");
