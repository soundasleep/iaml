<?php

require("../xquery_functions.php");

require("test1.php");

function compareTestResults($output, $file) {
	// clean out newlines etc
	$output = clean_newlines($output);
	$expected = clean_newlines(file_get_contents($file));

	if ($expected !== $output) {
		echo "ERROR: Results did not match expected:\n";

		$filename = "temp";	// a temporary file
		if (file_exists($filename)) {
			echo "ERROR: Cannot create file $filename\n";
			die(1);
		}
		file_put_contents($filename, str_replace("\n", "\r\n", $output));
		passthru("diff -u -a --label result $filename $file");
		echo "\n";
		unlink($filename);	// then delete it
	} else {
		echo "PASS\n";
	}
}

function clean_newlines($s) {
	$s = str_replace("\r\n", "\n", $s);
	$s = str_replace("\r", "\n", $s);

	// trim
	return trim($s);
}
