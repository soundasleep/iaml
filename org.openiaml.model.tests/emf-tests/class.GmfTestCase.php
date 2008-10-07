<?php

// now uses SimpleTest: http://www.lastcraft.com/simple_test.php
// (licenced under LGPL)
class GmfTestCase extends UnitTestCase {
	// some helper functions
	protected function toArray($xml) {
		$r = array();

		if (is_array($xml)) {
			foreach ($xml as $k => $v) {
				$r["$k"] = "$v";
			}
		}

		return $r;
	}

	protected function assertInArray($needle, $haystack, $message = "") {
		if (!$message) {
			$message = "expected '$needle' in '$haystack'";
		}
		$this->assertTrue(in_array($needle, $haystack), $message);
	}
	protected function assertEquals($a, $b, $message = null) {
		return $this->assertEqual($a, $b, $message);
	}

	// this is a really lazy implementation of a .properties/manifest.mf file reader
	function loadProperties($file_name) {
		$this->assertTrue(file_exists($file_name), "'$file_name' does not exist");

		$f = file($file_name);

		$result = array();
		foreach ($f as $line) {
			$line = trim($line);
			$split = explode(": ", $line, 2);

			// NOTE: this will ignore multi-line properties
			if (count($split) == 2) {
				$result[$split[0]] = $split[1];
			}
		}

		return $result;
	}
}
