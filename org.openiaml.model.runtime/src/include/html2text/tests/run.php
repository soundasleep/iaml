<?php

require("../html2text.php");

$tests = array(
	"basic",
	"anchors",
	"test3",
);

$passed = 0;
$failed = 0;
foreach ($tests as $test) {
	echo "[test $test]\n";

	if (!file_exists("$test.html")) {
		echo "FAILED: File '$test.html' did not exist\n\n";
		$failed++;
		continue;
	}
	if (!file_exists("$test.txt")) {
		echo "FAILED: File '$test.txt' did not exist\n\n";
		$failed++;
		continue;
	}
	$input = file_get_contents("$test.html");
	$expected = fix_newlines(file_get_contents("$test.txt"));

	$output = convert_html_to_text($input);

	if ($output != $expected) {
		file_put_contents("$test.output", $output);

		// mark whitespace
		/*
		$output = str_replace(" ", ".", $output);
		$expected = str_replace(" ", ".", $expected);
		$output = str_replace("\t", " -> ", $output);
		$expected = str_replace("\t", " -> ", $expected);
		$output = str_replace("\r", "\\r\r", $output);
		$expected = str_replace("\r", "\\r\r", $expected);
		$output = str_replace("\n", "\\n\n", $output);
		$expected = str_replace("\n", "\\n\n", $expected);
		*/

		echo "FAILED: Expected:\n$expected\n\nGot:\n$output\n\n";
		$failed++;
	} else {
		$passed++;
	}
}

echo "Passed: $passed, Failed: $failed\n";
