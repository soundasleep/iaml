package org.openiaml.model.tests.codegen.runtime.server;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.runtime.client.AllClientRuntimeTests;

/**
 * All tests for the runtime library (server-side)
 * 
 * @see AllClientRuntimeTests
 * @author jmwright
 *
 */
public class AllServerRuntimeTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Runtime Library (Server-side)");
		
		suite.addTestSuite(PhpMethodsTest.class);
		suite.addTestSuite(ComparisonFunctions.class);
		suite.addTestSuite(HelperFunctions.class);
		suite.addTestSuite(CastFunctions.class);

		return suite;
	}

}
