<?php

define("GMF_ROOT", "../../org.openiaml.model/model/");

// get all the test cases that follow a certain file name

$testCases = FileFinder::instance()->match("./", ".php", "case.");
print_r($testCases);

// load each test case
foreach ($testCases as $test => $testFile) {
	try {
		require("case.$test.php");
		$class = new $test();
		if (!($class instanceof GmfTestCase)) {
			throw new Exception("class $test is not a GmfTestCase");
		}
		$class->run();
	} catch (Exception $e) {
		fatal($e);
	}
}

function fatal($msg) {
	echo "ERR: $msg\n";
}
function warn($msg) {
	echo "! $msg\n";
}

$_singleton_instances = array();
class XmlLoader {
	var $_loaded = array();

	public function load($url) {
		if (!isset($this->_loaded[$url])) {
			$this->_loaded[$url] = simplexml_load_file($url);
		}

		return $this->_loaded[$url];
	}

	static public function instance() {
		global $_singleton_instances;
		if (!isset($_singleton_instances["XmlLoader"])) {
			$_singleton_instances["XmlLoader"] = new XmlLoader();
		}
		return $_singleton_instances["XmlLoader"];
	}
}

// TODO: move to PHPUnit?

class GmfTestCase {
	public function run() {
		fatal("no run() specified in " . get_class($this));
	}

	protected function toArray($xml) {
		$r = array();

		if (is_array($xml)) {
			foreach ($xml as $k => $v) {
				$r["$k"] = "$v";
			}
		}

		return $r;
	}

	protected function assert($condition, $message = "expected(true), got(false)") {
		if (!$condition) {
			warn($message);
		}
	}

	protected function assertInArray($needle, $haystack, $message = "") {
		if (!$message) {
			$message = "expected '$needle' in '$haystack'";
		}
		$this->assert(in_array($needle, $haystack), $message);
	}
}

// find all files in a directory that match a certain pattern
class FileFinder {
	var $_loaded = array();

	public function match($dir, $extension, $prefix = "") {
		$result = array();
		if ($handle = opendir($dir)) {
			while (false !== ($file = readdir($handle))) {
				if ($file != "." && $file != ".." && substr(strtolower($file), -strlen($extension)) == $extension && substr(strtolower($file), 0, strlen($prefix)) == $prefix) {
					$className = substr(substr($file, 0, strpos($file, $extension)), strlen($prefix));
					$result[$className] = $dir . $file;
				}
			}
			closedir($handle);
		}

		return $result;
	}

	static public function instance() {
		global $_singleton_instances;
		if (!isset($_singleton_instances["FileFinder"])) {
			$_singleton_instances["FileFinder"] = new FileFinder();
		}
		return $_singleton_instances["FileFinder"];
	}
}