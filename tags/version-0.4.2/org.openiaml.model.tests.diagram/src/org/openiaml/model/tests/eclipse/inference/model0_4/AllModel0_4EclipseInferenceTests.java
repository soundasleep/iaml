package org.openiaml.model.tests.eclipse.inference.model0_4;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.3.
 * 
 * @author jmwright
 *
 */
public class AllModel0_4EclipseInferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4");
		
		suite.addTestSuite(EclipseGeneratedPrimaryKey.class);
		suite.addTestSuite(EclipseSelectWireAttributeInference.class);
		suite.addTestSuite(EclipseDomainInstanceSaveOperation.class);

		return suite;
	}
	
}
