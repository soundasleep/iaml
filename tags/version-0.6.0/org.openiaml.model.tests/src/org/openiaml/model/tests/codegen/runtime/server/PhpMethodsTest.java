/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;


/**
 * Test the wrapper methods in {@link PhpCodegenTestCase}.
 * 
 * @author jmwright
 *
 */
public class PhpMethodsTest extends PhpCodegenTestCase {

	/**
	 * Make sure that our JS wrapper actually works.
	 * 
	 * @throws Exception
	 */
	public void testPhpWrapper() throws Exception {
		assertPhpResult("one", "'one'");
	}

	/**
	 * Check to see that if we access a function that doesn't exist,
	 * it fails.
	 * 
	 * @throws Exception
	 */
	public void testMissingFunction() throws Exception {
		try {
			executePhp("impossibleFunction();");
			fail("Impossible Javascript should never be called.");
		} catch (RuntimeException e) {
			// expected
		}
	}

}
