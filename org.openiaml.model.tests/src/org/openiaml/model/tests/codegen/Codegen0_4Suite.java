package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.model0_4.NewInstanceWithoutId;

/**
 * Code generation tests: model version 0.4
 * 
 * @author jmwright
 *
 */
public class Codegen0_4Suite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4");
		
		suite.addTestSuite(NewInstanceWithoutId.class);

		return suite;
	}

}
