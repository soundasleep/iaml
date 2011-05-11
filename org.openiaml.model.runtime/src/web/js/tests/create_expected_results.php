var expected_results = {
<?php

$f = file("../../../include/tests/expected1.txt");
foreach ($f as $line) {
	$line = trim($line);
	if ($line === "---" || !$line) {
		echo "// ---\n";
	} else {
		$split = explode(" = ", trim($line));
		$split[1] = substr($split[1], 0, strlen($split[1]) - 1);
		echo "\"" . $split[0] . "\": \"" . $split[1] . "\",\n";
	}
}

?>
};