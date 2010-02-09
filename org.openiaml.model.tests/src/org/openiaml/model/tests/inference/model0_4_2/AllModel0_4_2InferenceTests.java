package org.openiaml.model.tests.inference.model0_4_2;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.4.2.
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_2InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.2");
		
		suite.addTestSuite(GateRequiredPage.class);
		suite.addTestSuite(ExitGateAdSimple.class);
		
		return suite;
	}
	
}
