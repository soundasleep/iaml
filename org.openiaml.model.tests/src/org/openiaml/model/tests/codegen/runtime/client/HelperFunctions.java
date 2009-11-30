/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;

/**
 * Checks helper functions.
 * 
 * @author jmwright
 *
 */
public class HelperFunctions extends JavascriptCodegenTestCase {
	
	/**
	 * tests createCookie(), readCookie()
	 */
	public void testStoreCookie() throws Exception {
		executeJavascript("createCookie('test', 'bar', 1);");
		assertJavascriptResult("bar", "readCookie('test')");
	}
	
	public void testTrim() throws Exception {
		assertJavascriptResult("test", "trim('test')");
		assertJavascriptResult("test", "trim('test ')");
		assertJavascriptResult("test", "trim('  test')");
		assertJavascriptResult("te st", "trim('\\tte st\\n\\n\\t')");
		assertJavascriptResult("te\n \nst", "trim(' te\\n \\nst ')");
	}

	public void testArrayEqual() throws Exception {
		assertJavascriptResult(true, "is_array_equal(new Array())");
		assertJavascriptResult(true, "is_array_equal(new Array(1))");
		assertJavascriptResult(false, "is_array_equal(new Array(1, 2))");
		assertJavascriptResult(true, "is_array_equal(new Array(1, 1))");
		assertJavascriptResult(true, "is_array_equal(new Array('a', 'a', 'a', 'a'))");
		assertJavascriptResult(false, "is_array_equal(new Array('a', 'a', 'b', 'a'))");
	}
	
	public void testParseFloatOrZero() throws Exception {
		// first check JS
		assertJavascriptResult(true, "parseFloat('1') == parseFloat('1')");
		assertJavascriptResult(true, "parseFloat('1') == parseFloat('1.0')");
		assertJavascriptResult(true, "parseFloat('1.0') == parseFloat('1.0')");
		assertJavascriptResult(false, "parseFloat('1.0') == parseFloat('1.1')");
		assertJavascriptResult(false, "parseFloat('1') == parseFloat('1.1')");
		assertJavascriptResult(false, "parseFloat('2') == parseFloat('1')");
		
		// now check the helper function
		assertJavascriptResult(true, "parseFloat('1') == parse_float_or_zero('1')");
		assertJavascriptResult(true, "parseFloat('1.0') == parse_float_or_zero('1')");
		assertJavascriptResult(true, "parseFloat('1') == parse_float_or_zero('1.0')");
		assertJavascriptResult(true, "parseFloat('1.0') == parse_float_or_zero('1.0')");
		
		assertJavascriptResult(false, "parseFloat('1.1') == parse_float_or_zero('1.0')");
		assertJavascriptResult(false, "parseFloat('1') == parse_float_or_zero('1.1')");

		assertJavascriptResult(true, "parseFloat('0') == parse_float_or_zero('0')");
		
		// parsing '' should return 0
		assertJavascriptResult(false, "parseFloat('') == parse_float_or_zero('')");
		assertJavascriptResult(true, "parseFloat('0') == parse_float_or_zero('')");
		
		// identical results
		assertJavascriptResult(true, "parse_float_or_zero('1') == parse_float_or_zero('1')");
		assertJavascriptResult(true, "parse_float_or_zero('1.1') == parse_float_or_zero('1.1')");
		assertJavascriptResult(true, "parse_float_or_zero('') == parse_float_or_zero('')");
		
		// all other values should return NaN
		// but we can't compare Nan
		assertJavascriptResult(true, "isNaN(parseFloat('invalid'))");
		assertJavascriptResult(true, "isNaN(parse_float_or_zero('invalid'))");
		assertJavascriptResult(true, "isNaN(parseFloat(''))");
		assertJavascriptResult(false, "isNaN(parse_float_or_zero(''))");

	}
	
	public void testConvertToNan() throws Exception {
		assertJavascriptResult(false, "isNaN(3)");
		assertJavascriptResult(false, "isNaN(3/0)");
		assertJavascriptResult(true, "isNaN(0/0)");
		
		assertJavascriptResult(false, "isNaN(convert_to_nan(3))");
		assertJavascriptResult(true, "isNaN(convert_to_nan(3/0))");
		assertJavascriptResult(true, "isNaN(convert_to_nan(0/0))");		

	}

}
