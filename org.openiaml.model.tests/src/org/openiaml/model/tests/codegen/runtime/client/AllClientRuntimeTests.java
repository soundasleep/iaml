package org.openiaml.model.tests.codegen.runtime.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.runtime.server.AllServerRuntimeTests;

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
		suite.addTestSuite(RuntimeLibraryVersionTest.class);
		suite.addTestSuite(CastFunctions.class);

		return suite;
	}

}
