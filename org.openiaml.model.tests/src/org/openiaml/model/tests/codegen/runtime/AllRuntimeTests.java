package org.openiaml.model.tests.codegen.runtime;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the runtime library (both client-side and 
 * server-side at the moment)
 * 
 * @author jmwright
 *
 */
public class AllRuntimeTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Runtime Library");
		
		suite.addTestSuite(JavascriptMethodsTest.class);
		suite.addTestSuite(StoreCookie.class);

		return suite;
	}

}
