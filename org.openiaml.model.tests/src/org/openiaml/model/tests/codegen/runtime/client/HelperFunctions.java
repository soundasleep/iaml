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
		assertJavascriptResult(true, "parseFloat('1') == parseFloatOrZero('1')");
		assertJavascriptResult(true, "parseFloat('1.0') == parseFloatOrZero('1')");
		assertJavascriptResult(true, "parseFloat('1') == parseFloatOrZero('1.0')");
		assertJavascriptResult(true, "parseFloat('1.0') == parseFloatOrZero('1.0')");
		
		assertJavascriptResult(false, "parseFloat('1.1') == parseFloatOrZero('1.0')");
		assertJavascriptResult(false, "parseFloat('1') == parseFloatOrZero('1.1')");

		assertJavascriptResult(true, "parseFloat('0') == parseFloatOrZero('0')");
		
		// parsing '' should return 0
		assertJavascriptResult(false, "parseFloat('') == parseFloatOrZero('')");
		assertJavascriptResult(true, "parseFloat('0') == parseFloatOrZero('')");
		
		// all other values should return NaN
		// but we can't compare Nan
		assertJavascriptResult(true, "isNaN(parseFloat('invalid'))");
		assertJavascriptResult(true, "isNaN(parseFloatOrZero('invalid'))");
		assertJavascriptResult(true, "isNaN(parseFloat(''))");
		assertJavascriptResult(false, "isNaN(parseFloatOrZero(''))");

	}

}
