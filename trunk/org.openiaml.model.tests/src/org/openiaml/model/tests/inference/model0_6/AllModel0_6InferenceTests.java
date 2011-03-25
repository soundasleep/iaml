package org.openiaml.model.tests.inference.model0_6;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.6.
 * 
 * @author jmwright
 *
 */
public class AllModel0_6InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.6");
		
		suite.addTestSuite(VisibleThingVisibilityProperty.class);
		
		return suite;
	}
	
}
