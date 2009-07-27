/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime;


/**
 * Allows for the execution of client-side Javascript from the
 * server. (Mostly a big hack.)
 * 
 * @author jmwright
 *
 */
public class StoreCookie extends JavascriptCodegenTestCase {
	
	/**
	 * tests createCookie(), readCookie()
	 */
	public void testStoreCookie() throws Exception {
		executeJavascript("createCookie('test', 'bar', 1);");
		assertJavascriptResult("bar", "readCookie('test')");
	}

}
