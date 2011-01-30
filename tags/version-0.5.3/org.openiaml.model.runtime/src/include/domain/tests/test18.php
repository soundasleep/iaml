<?php

// define a DomainSchema for RSS news items
class RSSNews extends DomainSchema {

	private function __construct() {
		$this->attributes = array(
			"id" => RSSNews_ID::getInstance(),
			"title" => RSSNews_Title::getInstance(),
			"description" => RSSNews_Description::getInstance(),
			"pubdate" => RSSNews_PubDate::getInstance(),
		);
		$this->table_name = "News";
		$this->source_id = 'rssnews';
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSNews();
		}
		return self::$instance;
	}
}

class RSSNews_ID extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'http://openiaml.org/model/datatypes#iamlInteger';
		$this->name = "id";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSNews_ID();
		}
		return self::$instance;
	}

}

class RSSNews_Title extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'http://openiaml.org/model/datatypes#iamlString';
		$this->name = "title";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSNews_Title();
		}
		return self::$instance;
	}

}

class RSSNews_Description extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'http://openiaml.org/model/datatypes#iamlString';
		$this->name = "description";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSNews_Description();
		}
		return self::$instance;
	}

}

class RSSNews_PubDate extends DomainAttribute {
	private function __construct() {
		$this->isPrimaryKey = true;
		$this->type = 'http://openiaml.org/model/datatypes#iamlDateTime';
		$this->name = "pubdate";
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSNews_PubDate();
		}
		return self::$instance;
	}

}

// define a remote source for this RSS schema (local .xml file)
class RSSSource18 extends DomainSource {

	private function __construct() {
		$this->schemas = array(RSSNews::getInstance());
		$this->type = 'RSS2_0';
		// $this->file = 'sqlite:rss.db'; - file is not used
		$this->url = 'rss_sample.xml';		// source of data: local
		$this->cache = 3600;		// cache for 3600 seconds
	}

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new RSSSource18();
		}
		return self::$instance;
	}

}

// and an iterator
class RSSIterator18 extends DefaultDomainIterator {

  private function __construct() {
    $this->schema = RSSNews::getInstance();
    $this->source = RSSSource18::getInstance();
    $this->order_by = null;
    $this->order_ascending = true;
    $this->query = "1";
    $this->autosave = false;		// read-only source by default
    $this->is_new = false;
    $this->limit = 10;
  }

  // the current instance
  static $instance = null;
  public static function getInstance() {
    if (self::$instance == null) {
      self::$instance = new RSSIterator18();
    }
    return self::$instance;
  }

  public function constructArgs() {
    return array(
      // no args
    );
  }

	public function getOffset() {
		if (!isset($_SESSION["offset_".get_class($this)])) {
			$this->setOffset(0);
		}
		return $_SESSION["offset_".get_class($this)];
	}

	public function setOffset($value) {
		$_SESSION["offset_".get_class($this)] = $value;
	}

	public function getNewInstanceID($key) {
		throw new IamlDomainException("Cannot get the new instance ID for a non-new object: " . get_class($this));
	}

	public function setNewInstanceID($key, $id) {
		throw new IamlDomainException("Cannot set the new instance ID for a non-new object: " . get_class($this));
	}
}

echo "[test 18] ";
ob_start();
{
	// delete the database
	if (file_exists("remote.db")) {
		unlink("remote.db");
	}

	// get the current instance
	$iterator = RSSIterator18::getInstance();
	printit4($iterator);

	// iterate over all others
	while ($iterator->hasNext()) {
		$iterator->next();
		printit4($iterator);
	}

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected18.txt");
