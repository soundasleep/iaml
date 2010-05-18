/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;


/**
 * Test the wrapper methods in {@link JavascriptCodegenTestCase}.
 * 
 * @author jmwright
 *
 */
public class JavascriptMethodsTest extends JavascriptCodegenTestCase {

	/**
	 * Make sure that our JS wrapper actually works.
	 * 
	 * @throws Exception
	 */
	public void testJavascriptWrapper() throws Exception {
		assertJavascriptResult("one", "'one'");
	}

	/**
	 * Check to see that if we access a function that doesn't exist,
	 * it fails.
	 * 
	 * @throws Exception
	 */
	public void testMissingFunction() throws Exception {
		try {
			executeJavascript("impossibleFunction();");
			fail("Impossible Javascript should never be called.");
		} catch (RuntimeException e) {
			// expected
		}
	}

}
