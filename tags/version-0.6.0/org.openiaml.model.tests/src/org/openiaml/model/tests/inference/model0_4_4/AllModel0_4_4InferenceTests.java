package org.openiaml.model.tests.inference.model0_4_4;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.4.4.
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_4InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.4");
		
		suite.addTestSuite(InputTextFieldDataType.class);
		suite.addTestSuite(InputTextFieldDataTypeSync.class);
		
		return suite;
	}
	
}
