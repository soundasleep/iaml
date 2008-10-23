<?php

// now uses SimpleTest: http://www.lastcraft.com/simple_test.php
// (licenced under LGPL)
require_once('../simpletest/unit_tester.php');
require_once('../simpletest/reporter.php');

require("functions.php");
require("class.XmlLoader.php");
require("class.FileFinder.php");
//require("class.GmfTestRunner.php");
require("class.GmfTestCase.php");

define("GMF_ROOT", "../../org.openiaml.model/model/");

// get all the test cases that follow a certain file name
$testCases = FileFinder::instance()->match("./", ".php", "case.");

// load each test case
$runner =& new GroupTest("All EMF tests");
foreach ($testCases as $test => $testFile) {
	$runner->addTestFile("case.$test.php");
}
exit($runner->run(new TextReporter()) ? 0 : 1);
