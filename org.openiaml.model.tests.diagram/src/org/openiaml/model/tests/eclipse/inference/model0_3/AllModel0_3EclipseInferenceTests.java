package org.openiaml.model.tests.eclipse.inference.model0_3;

import org.openiaml.model.tests.inference.model0_3.InputFormInstanceMapping;
import org.openiaml.model.tests.inference.model0_3.NewInstanceWireMapping;
import org.openiaml.model.tests.inference.model0_3.PropertiesFileMapping;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.3.
 * 
 * @author jmwright
 *
 */
public class AllModel0_3EclipseInferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.3");
		
		suite.addTestSuite(NewInstanceWireMapping.class);
		suite.addTestSuite(InputFormInstanceMapping.class);
		suite.addTestSuite(PropertiesFileMapping.class);
		
		return suite;
	}
	
}
