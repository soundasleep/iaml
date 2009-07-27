/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime;

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
		assertJavascriptResult("te st", "trim('te st')");
	}

	public void testArrayEqual() throws Exception {
		assertJavascriptResult(true, "is_array_equal(new Array())");
		assertJavascriptResult(true, "is_array_equal(new Array(1))");
		assertJavascriptResult(false, "is_array_equal(new Array(1, 2))");
		assertJavascriptResult(true, "is_array_equal(new Array(1, 1))");
		assertJavascriptResult(true, "is_array_equal(new Array('a', 'a', 'a', 'a'))");
		assertJavascriptResult(false, "is_array_equal(new Array('a', 'a', 'b', 'a'))");
	}

}
