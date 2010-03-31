package org.openiaml.model.tests.codegen.model0_5;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.5
 * 
 * @author jmwright
 *
 */
public class AllModel0_5CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5");

		suite.addTestSuite(ExplicitFieldValue.class);
		suite.addTestSuite(EmailExplicit.class);
		suite.addTestSuite(EmailEvents.class);

		return suite;
	}

}
