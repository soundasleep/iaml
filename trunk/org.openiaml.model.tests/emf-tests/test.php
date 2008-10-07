<?php

// get all the test cases that follow a certain file name

$testCases = array();
if ($handle = opendir('.')) {
    while (false !== ($file = readdir($handle))) {
        if ($file != "." && $file != ".." && substr(strtolower($file), -4) == ".php" && substr(strtolower($file), 0, 5) == "case.") {
            $className = substr(substr($file, 0, strpos($file, ".php")), 5);
            $testCases[] = $className;
        }
    }
    closedir($handle);
}

// load each test case
foreach ($testCases as $test) {
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

class GmfTestCase {
	public function run() {
		die("no run() specified in " . get_class($this));
	}
}
