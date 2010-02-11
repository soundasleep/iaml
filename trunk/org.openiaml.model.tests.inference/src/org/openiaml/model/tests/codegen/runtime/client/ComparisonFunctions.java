/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;

/**
 * Checks comparison functions.
 * 
 * @author jmwright
 *
 */
public class ComparisonFunctions extends JavascriptCodegenTestCase {
	
	/**
	 * tests emailAddressMatch()
	 */
	public void testEmailAddressMatch() throws Exception {
		assertJavascriptResult(true, "emailAddressMatch('test@jevon.org')");
		assertJavascriptResult(true, "emailAddressMatch('long.address@test.example.co.nz')");
		assertJavascriptResult(false, "emailAddressMatch('failure')");
	}

	/**
	 * tests xpathMatch()
	 */
	public void testXpathMatch() throws Exception {
		String init = "var a = new Visual_Frame('a', 'a'); var b = new Visual_Frame('b', 'b'); var c = new Visual_Frame('c', 'c');";
		
		assertJavascriptResult(true, init, "xpathMatch(new Array('a'), a)");
		assertJavascriptResult(true, init, "xpathMatch(new Array('b'), b)");
		assertJavascriptResult(true, init, "xpathMatch(b, new Array('b'))");
		assertJavascriptResult(false, init, "xpathMatch(new Array('a'), b)");
		assertJavascriptResult(true, init, "xpathMatch(new Array('a', 'b'), b)");
		assertJavascriptResult(true, init, "xpathMatch(a, new Array('a', 'b'))");
		assertJavascriptResult(false, init, "xpathMatch(c, new Array('a', 'b'))");
	}

}
