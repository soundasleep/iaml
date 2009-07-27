package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.oaw.AllOawTests;
import org.openiaml.model.tests.codegen.runtime.client.AllClientRuntimeTests;
import org.openiaml.model.tests.codegen.runtime.server.AllServerRuntimeTests;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class AllCodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Code Generation");
		
		// make sure we can catch exceptions
        suite.addTestSuite(TestExceptionHandling.class);

		// and that the client-side library exists
        suite.addTestSuite(TestClientsideRuntimeLibrary.class);

		// (issue 81) runtime libraries can be included
        suite.addTestSuite(TestIncludeLibraries.class);

		// OAW-specific tests
		suite.addTest(AllOawTests.suite());

		// different model versions
		suite.addTest(Codegen0_1Suite.suite());
		suite.addTest(Codegen0_2Suite.suite());
		suite.addTest(Codegen0_3Suite.suite());
		
		// client-side runtime library tests
		suite.addTest(AllClientRuntimeTests.suite());
		
		// server-side runtime library tests
		suite.addTest(AllServerRuntimeTests.suite());

		return suite;
	}

}
