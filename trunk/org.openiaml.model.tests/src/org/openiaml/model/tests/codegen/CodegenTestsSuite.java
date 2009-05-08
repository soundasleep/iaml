package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.oaw.ChecksTest;
import org.openiaml.model.tests.codegen.oaw.OawTestsSuite;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class CodegenTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Code Generation");
		
		// make sure we can catch exceptions
        suite.addTestSuite(TestExceptionHandling.class);

		// OAW-specific tests
		suite.addTest(OawTestsSuite.suite());

		// different model versions
		suite.addTest(Codegen0_1Suite.suite());
		suite.addTest(Codegen0_2Suite.suite());
		suite.addTest(Codegen0_3Suite.suite());

		return suite;
	}

}
