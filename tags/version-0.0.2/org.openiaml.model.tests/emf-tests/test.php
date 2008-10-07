<?php

require("functions.php");
require("class.XmlLoader.php");
require("class.FileFinder.php");
require("class.GmfTestRunner.php");
require("class.GmfTestCase.php");

define("GMF_ROOT", "../../org.openiaml.model/model/");

// get all the test cases that follow a certain file name
$testCases = FileFinder::instance()->match("./", ".php", "case.");

// load each test case
$runner = new GmfTestRunner();
foreach ($testCases as $test => $testFile) {
	try {
		require("case.$test.php");
		$class = new $test();
		if (!($class instanceof GmfTestCase)) {
			throw new Exception("class $test is not a GmfTestCase");
		}
		$runner->runTest($class);
	} catch (Exception $e) {
		fatal($e);
	}
}
$runner->printStats();

