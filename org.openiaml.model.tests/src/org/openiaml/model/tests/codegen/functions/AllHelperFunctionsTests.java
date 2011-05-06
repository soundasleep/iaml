package org.openiaml.model.tests.codegen.functions;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Issue 123: Test cases for inference helper functions.
 * 
 * @author jmwright
 *
 */
public class AllHelperFunctionsTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Helper Functions");
		
		suite.addTestSuite(Connects.class);
		suite.addTestSuite(ContainingSession.class);
		suite.addTestSuite(GetUserQueryString.class);
		suite.addTestSuite(HasDomainAttribute.class);
		suite.addTestSuite(LastChainedOperation.class);
		suite.addTestSuite(NameMatches.class);
		suite.addTestSuite(OawCodeGeneratorTests.class);

		return suite;
	}

}
