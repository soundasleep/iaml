package org.openiaml.model.tests.codegen.model0_6;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.6
 * 
 * @author jmwright
 *
 */
public class AllModel0_6CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.6");

		suite.addTestSuite(VisibleThingVisibility.class);
		suite.addTestSuite(VisibleThingVisibilityProperty.class);

		return suite;
	}

}
