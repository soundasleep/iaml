<?php

// uses the Test6 schema and source, but defines an
// iterator that does not autosave

class DomainIterator_AdminNewIterator2 extends DomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_Admins::getInstance();
		$this->source = DomainSource_AdminsDB::getInstance();
		$this->order_by = "";
		$this->order_ascending = true;
		$this->query = "";
		$this->autosave = false;
		$this->is_new = true;
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_AdminNewIterator2();
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
}

echo "[test 7] ";
ob_start();
{
	// get the current instance
	$instance = DomainIterator_AdminNewIterator2::getInstance();

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

	// reload it; it's not autosaved, so the content is lost
	$instance->reload();
	echo "4:\n";
	printit3($instance->toArray());

	// set it again
	$instance->getAttribute('email')->setValue("another@openiaml.org");
	echo "5:\n";
	printit3($instance->toArray());

	// save it manually; this now populates the PK fields etc
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
$expected = file_get_contents("expected7.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
