<?php

/* 
 * this is special code that should be included in instrumented
 * code, which allows us to perform instrumentation of: 
 * 
 * 1. PHP (via inline calls)
 * 2. HTML (via inline calls)
 * 3. Javascript (via AJAX calls) TODO
 */

function php_instrument_oaw($destination, $oaw_file, $oaw_line) {
	$file = $destination . "/" . "php-instrument.dump.raw";
	
	// load
	if (file_exists($file)) {
		$serialized = unserialize(file_get_contents($file));
	} else {
		$serialized = array();
	}
	
	// mark
	if (!isset($serialized[$oaw_file])) {
		$serialized[$oaw_file] = array();
	}
	if (!isset($serialized[$oaw_file][$oaw_line])) {
		$serialized[$oaw_file][$oaw_line] = 0;
	}
	$serialized[$oaw_file][$oaw_line]++;
	
	// save
	file_put_contents($file, serialize($serialized));
	
	// also write an easy-to-read version
	$file = $destination . "/" . "php-instrument.dump";
	$out = "";
	foreach ($serialized as $key => $value) {
		$out .= "$key:\n";
		foreach ($value as $k => $v) {
			$out .= "\t$k: $v\n";
		}
	}
	file_put_contents($file, $out);
	
}  