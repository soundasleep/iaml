<?php

$arguments = array("", "hello", "world", 1, "2", 34, 0);
$functions = array(
		"op_numeric_add",
		"op_numeric_subtract",
		"op_numeric_multiply",
		"op_numeric_divide",
		"fn_abs",
		"fn_floor",
		"fn_concat",
		"fn_substring",
		"fn_contains",
		"fn_codepoint_equal",
		"op_numeric_equal",
		"op_numeric_less_than",
		"op_numeric_greater_than",
		"fn_not",
	);

echo "[test 1] ";
ob_start();
{
	foreach ($functions as $f) {
		foreach ($arguments as $a) {
			foreach ($arguments as $b) {
				echo $f . "(" . $a . "," . $b . ") = ";
				try {
					$result = xquery_function_callback($f, $a, $b);
					if (is_bool($result)) {
						echo $result ? "true" : "false";
					} else {
						echo $result;
					}
				} catch (IamlIllegalArgumentException $e) {
					echo "exception";
				}
				echo  ".\n";
			}
		}
		echo "---\n";
	}
}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected1.txt");
