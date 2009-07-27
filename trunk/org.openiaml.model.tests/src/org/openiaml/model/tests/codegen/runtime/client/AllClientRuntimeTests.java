package org.openiaml.model.tests.codegen.runtime.client;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the runtime library (client-side)
 * 
 * @see AllServerRuntimeTests
 * @author jmwright
 *
 */
public class AllClientRuntimeTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Runtime Library");
		
		suite.addTestSuite(JavascriptMethodsTest.class);
		suite.addTestSuite(HelperFunctions.class);
		suite.addTestSuite(ComparisonFunctions.class);

		return suite;
	}

}
