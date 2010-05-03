<?php

// here, we use the same inheritance structures in test8, but we define
// an iterator that we can edit with

class DomainIterator_StudentAdminNewEditor extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_StudentAdmins::getInstance();
		$this->source = DomainSource_AdminsDBWithStudents::getInstance();	// uses the same source
		$this->order_by = null;
		$this->order_ascending = true;
		$this->query = "user_id = :user_id";
		$this->autosave = false;
		$this->is_new = false;
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainIterator_StudentAdminNewEditor();
		}
		return self::$instance;
	}

	public function constructArgs() {
		return array(
			"user_id" => $_SESSION["test9"],
		);
	}

	public function getOffset() {
		if (!isset($_SESSION["DomainIterator_StudentAdminNewEditor"])) {
			return 0;
		}

		return $_SESSION["DomainIterator_StudentAdminNewEditor"];
	}

	public function setOffset($value) {
		$_SESSION["DomainIterator_StudentAdminNewEditor"] = $value;
	}

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

}

echo "[test 9] ";
ob_start();
{
	$_SESSION["test9"] = 5;		// use "user_id = 5"

	// get the current instance
	$instance = DomainIterator_StudentAdminNewEditor::getInstance();

	// print it out
	echo "1:\n";
	printit3($instance->toArray());

	echo "1: (full)\n";
	printit4($instance);

	// edit it
	$instance->getAttribute('email')->setValue("changed@openiaml.org");
	echo "2:\n";
	printit3($instance->toArray());

	$instance->getAttribute('name')->setValue("Changed User");
	$instance->getAttribute('degree')->setValue("B.A.");
	echo "3:\n";
	printit3($instance->toArray());

	// discard our changes
	$instance->reload();
	echo "4:\n";
	printit3($instance->toArray());

	// set it again
	$instance->getAttribute('email')->setValue("changed2@openiaml.org");
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

}

{
	$_SESSION["test9"] = 999;		// use "user_id = 999" (doesn't exist)

	// get the current instance
	$instance = DomainIterator_StudentAdminNewEditor::getInstance();
	$instance->reload();

	echo $instance->hasNext() ? "hasNext\n" : "-\n";
	echo $instance->hasPrevious() ? "hasPrevious\n" : "-\n";
	echo $instance->isEmpty() ? "isEmpty\n" : "-\n";
	echo "count = " . $instance->count() . "\n";

	// print it out
	echo "1:\n";
	try {
		printit3($instance->toArray());
		echo "FAIL: Could get a value from an empty result\n";
	} catch (IamlDomainException $e) {
		// expected
	}

	echo "1: (full)\n";
	try {
		printit4($instance->toArray());
		echo "FAIL: Could get a value from an empty result\n";
	} catch (IamlDomainException $e) {
		// expected
	}

	// we can't save it
	echo "2:\n";
	try {
		$instance->save();
		echo "FAIL: Could save an empty result\n";
	} catch (IamlDomainException $e) {
		// expected
	}

	echo "3:\n";
	// we can't create a new instance
	try {
		$instance->createNew();
		echo "FAIL: Could create a new instance for a non-new iterator\n";
	} catch (IamlDomainException $e) {
		// expected
	}

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected9.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
  echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
  echo "PASS\n";
}
