package org.openiaml.model.tests.codegen.model0_4_4;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.4.4
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_4CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.4");
		
		suite.addTestSuite(CanCastCondition.class);

		return suite;
	}

}
