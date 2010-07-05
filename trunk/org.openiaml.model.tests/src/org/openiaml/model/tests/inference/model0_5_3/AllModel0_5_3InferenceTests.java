package org.openiaml.model.tests.inference.model0_5_3;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.5.3.
 * 
 * @author jmwright
 *
 */
public class AllModel0_5_3InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5.3");
		
		suite.addTestSuite(OverriddenNames.class);
		suite.addTestSuite(OverriddenNamesSync.class);
		suite.addTestSuite(DetailWireOnSetIteratorList.class);
		suite.addTestSuite(AccessControlHandlerSpecifyLoginPage.class);
		suite.addTestSuite(AccessControlHandlerSpecifyLogoutPage.class);
		suite.addTestSuite(AccessControlHandlerSpecifySuccessPage.class);
		
		return suite;
	}
	
}
