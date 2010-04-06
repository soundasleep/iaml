package org.openiaml.model.tests.inference.model0_5;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.5.
 * 
 * @author jmwright
 *
 */
public class AllModel0_5InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5");
		
		suite.addTestSuite(InputTextFieldDataTypeOverridden.class);
		suite.addTestSuite(SyncWireTestCaseOverridden.class);
		suite.addTestSuite(EmailExplicit.class);
		suite.addTestSuite(FormSynchroniseSyncWire.class);
		suite.addTestSuite(FormSynchroniseSetWire.class);
		suite.addTestSuite(EmailSetWire.class);
		suite.addTestSuite(MapPointTextFieldInput.class);
		
		return suite;
	}
	
}
