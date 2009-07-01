package org.openiaml.model.tests.inference.model0_3;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.3.
 * 
 * @author jmwright
 *
 */
public class AllTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.3");
		
		suite.addTestSuite(NewInstanceWireMapping.class);
		
		return suite;
	}

}
