package org.openiaml.model.tests.codegen.model0_2;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.2
 * 
 * @author jmwright
 *
 */
public class AllModel0_2CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.2");

		suite.addTestSuite(ConditionWireFalse.class);
		suite.addTestSuite(ConditionWireFalseServer.class);
		suite.addTestSuite(ReloadPage.class);
		
		// initial requirements (model 0.2)
		// Requirement1 removed in 0.5.1
		suite.addTestSuite(Requirement2StaticParams.class);
		suite.addTestSuite(Requirement3DynamicParams.class);
		suite.addTestSuite(Requirement4DynamicSources.class);
		suite.addTestSuite(Requirement5Operations.class);
		suite.addTestSuite(Requirement6Conditions.class);

		return suite;
	}

}
