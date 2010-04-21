package org.openiaml.model.tests.inference.model0_5_1;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.5.1.
 * 
 * @author jmwright
 *
 */
public class AllModel0_5_1InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5.1");
		
		suite.addTestSuite(ViewDatabaseTypes.class);
		suite.addTestSuite(FeedProducerComplete.class);
		
		return suite;
	}
	
}
