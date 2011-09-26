/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

/**
 * Checks helper functions.
 * 
 * @author jmwright
 *
 */
public class HelperFunctions extends PhpCodegenTestCase {

	/**
	 * tests make_into_boolean()
	 * @throws Exception
	 */
	public void testMakeIntoBoolean() throws Exception {
		// obvious
		assertPhpResult(true, "make_into_boolean(true)");
		assertPhpResult(false, "make_into_boolean(false)");
		
		// strings
		assertPhpResult(false, "make_into_boolean(\"0\")");
		assertPhpResult(true, "make_into_boolean(\"1\")");
		assertPhpResult(false, "make_into_boolean(\"false\")");
		assertPhpResult(true, "make_into_boolean(\"true\")");
		assertPhpResult(true, "make_into_boolean(\"xyz\")");
		assertPhpResult(false, "make_into_boolean(\"\")");

		// null
		assertPhpResult(false, "make_into_boolean(null)");
		
		// numbers
		assertPhpResult(false, "make_into_boolean(0.0)");
		assertPhpResult(true, "make_into_boolean(1.0)");
		assertPhpResult(true, "make_into_boolean(1.33)");
		assertPhpResult(false, "make_into_boolean(0)");
		assertPhpResult(true, "make_into_boolean(1)");
		assertPhpResult(true, "make_into_boolean(2)");

	}

}
