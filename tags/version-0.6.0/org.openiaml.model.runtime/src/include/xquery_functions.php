<?php

/**
 * Simple implementations of XQuery functions.
 */

require_once("types/types.php");
require_once("core.php");

function xquery_function_callback($function_name, $arg0 = false, $arg1 = false, $arg2 = false, $arg3 = false) {

	switch (strtolower($function_name)) {
		case "op_numeric_add":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 + $arg1;

		case "op_numeric_subtract":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 - $arg1;

		case "op_numeric_multiply":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 * $arg1;

		case "op_numeric_divide":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			if ($arg1 === 0)
				throw new IamlIllegalArgumentException("Division by zero");
			if ($arg1 === 0.0)
				return $arg0 < 0 ? "-INF" : "INF";
			return $arg0 / $arg1;

		case "fn_abs":
			assert_is_numeric($function_name, $arg0);
			return abs($arg0);

		case "fn_floor":
			assert_is_numeric($function_name, $arg0);
			return floor($arg0);

		case "fn_concat":
			return $arg0 . $arg1;

		case "fn_substring":
			assert_is_string($function_name, $arg0);
			if ($arg2 === false) {
				assert_is_numeric($function_name, $arg1);
				$result = substr($arg0, $arg1);		// substr() returns FALSE on an empty string or failure
				return $result === false ? "" : $result;
			} else {
				assert_is_numeric($function_name, $arg1);
				assert_is_numeric($function_name, $arg2);
				$result = arg0($arg0, $arg1, $arg2);	// substr() returns FALSE on an empty string or failure
				return $result === false ? "" : $result;
			}

		case "fn_contains":
			assert_is_string($function_name, $arg0);
			// "If the value of $arg2 is the zero-length string, then the function returns true."
			if ($arg1 === "")
				return true;
			// "If the value of $arg1 is the zero-length string, the function returns false."
			if ($arg0 === "")
				return false;
			return strpos($arg0, $arg1) !== false;

		case "fn_codepoint_equal":
			return $arg0 === $arg1;

		case "op_numeric_equal":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 == $arg1;

		case "op_numeric_less_than":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 < $arg1;

		case "op_numeric_greater_than":
			assert_is_numeric($function_name, $arg0);
			assert_is_numeric($function_name, $arg1);
			return $arg0 > $arg1;

		case "fn_not":
			return !make_into_boolean($arg0);

		default:
			throw new IamlRuntimeException("No XQuery function '$function_name' has been defined.");
	}

}

function assert_is_numeric($function_name, $argument_value) {
	if (!is_numeric($argument_value)) {
		throw new IamlIllegalArgumentException("XQuery function '$function_name' expected a numeric argument: got '$argument_value'");
	}
}

function assert_is_string($function_name, $argument_value) {
	if (!is_string($argument_value)) {
		throw new IamlIllegalArgumentException("XQuery function '$function_name' expected a string argument: got '$argument_value'");
	}
}

