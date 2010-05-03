<?php

// here we define one new schema, that isn't manually created
// and a new source for this schema

class DomainSchema_Posts extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"generated_primary_key" => DomainAttribute_Post_Id::getInstance(),
			"content" => DomainAttribute_Post_Content::getInstance(),
		);
		$this->table_name = "Posts";
		$this->source_id = '2orlls14f';
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSchema_Posts();
		}
		return self::$instance;
	}

}

class DomainAttribute_Post_Id extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'iamlInteger';
		$this->name = "generated_primary_key";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_Post_Id();
		}
		return self::$instance;
	}

}

class DomainAttribute_Post_Content extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = false;
		$this->type = 'iamlString';
		$this->name = "content";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainAttribute_Post_Content();
		}
		return self::$instance;
	}
}

// and define the source for it
class DomainSource_PostsDB extends DomainSource {

	private function __construct() {
		$this->schemas = array(DomainSchema_Posts::getInstance());
		$this->type = 'RELATIONAL_DB';
		$this->file = 'sqlite:1kg992k6t4.db';		// it can be in the same DB
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new DomainSource_PostsDB();
		}
		return self::$instance;
	}

}

// now define an iterator that is new, and autosaves
class DomainIterator_Posts_1mkm131xzz extends DefaultDomainIterator {

	private function __construct() {
		$this->schema = DomainSchema_Posts::getInstance();
		$this->source = DomainSource_PostsDB::getInstance();
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
			self::$instance = new DomainIterator_Posts_1mkm131xzz();
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
		if (!isset($_SESSION["newid2_$key"]) || $_SESSION["newid2_$key"] === null) {
			return null;
		}

		return $_SESSION["newid2_$key"];
	}

	public function setNewInstanceID($key, $id) {
		$_SESSION["newid2_$key"] = $id;
	}

}

function printit2($instance) {
	$generated_primary_key = $instance["generated_primary_key"]->getValue();
	echo "generated_primary_key: " . ($generated_primary_key === null ? "null" : $generated_primary_key)  . "\n";
	$content = $instance["content"]->getValue();
	echo "content: " . ($content === null ? "null" : $content) . "\n";
}

echo "[test 5] ";
ob_start();
{
	// get the current instance
	$instance = DomainIterator_Posts_1mkm131xzz::getInstance();

	// print it out
	echo "1:\n";
	printit2($instance->toArray());

	// set some values
	$instance->getAttribute('content')->setValue("new content");
	echo "2:\n";
	printit2($instance->toArray());

	// don't save it manually (but autosave = true, so it's already saved)
	$instance->reload();
	echo "3:\n";
	printit2($instance->toArray());

	// set it again
	$instance->getAttribute('content')->setValue("another content");
	echo "4:\n";
	printit2($instance->toArray());

	// save it manually; makes no difference, already saved
	$instance->save();
	echo "5:\n";
	printit2($instance->toArray());

	// and reload it
	$instance->reload();
	echo "6:\n";
	printit2($instance->toArray());

	// create a new one
	$instance->createNew();
	echo "7:\n";
	printit2($instance->toArray());

	// set it
	$instance->getAttribute('content')->setValue("a new content");
	echo "8:\n";
	printit2($instance->toArray());

	// save it
	$instance->save();
	echo "9:\n";
	printit2($instance->toArray());

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
$expected = file_get_contents("expected5.txt");

// clean out newlines etc
$result = clean_newlines($result);
$expected = clean_newlines($expected);

if ($expected !== $result) {
	echo "ERROR: Results did not match expected:\n\n[expected]\n$expected\n\n[output]\n$result\n";
} else {
	echo "PASS\n";
}
