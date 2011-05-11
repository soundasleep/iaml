/**
 * Test runner script.
 */

function executeTests() {

	var args = ["", "hello", "world", 1, "2", 34, 0];
	var functions = [
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
		];

	// reset status
	document.getElementById('results').value = "";

	for (var i = 0; i < functions.length; i++) {
		var f = functions[i];
		for (var j = 0; j < args.length; j++) {
			for (var k = 0; k < args.length; k++) {
				var arg0 = args[j];
				var arg1 = args[k];

				var result;
				var ex = "";
				try {
					result = xquery_function_callback(f, arg0, arg1);
				} catch (e if e instanceof IamlJavascriptException) {
					result = "exception";
					ex = " : " + e.getMessage();
				}
				var key = f + "(" + arg0 + "," + arg1 + ")";
				var expected = expected_results[key];

				if (typeof result == "boolean")
					result = result ? "true" : "false";

				if (expected != result && !float_equal(expected, result)) {
					document.getElementById('results').value +=
						key + ": Expected " + expected + ", got " + result + ex + "\n";
				}

			}
		}
	}

}

function float_equal(a, b) {
	if ((a * 1) == a && (b * 1) == b) {
		if (a > (b - 1e-5) && a < (b + 1e-5)) {
			return true;
		}
	}
	return false;
}
