package org.openiaml.model.tests.codegen;

import org.openiaml.model.tests.codegen.model0_2.ConditionWireFalse;
import org.openiaml.model.tests.codegen.model0_2.ConditionWireFalseServer;
import org.openiaml.model.tests.codegen.model0_2.ReloadPage;
import org.openiaml.model.tests.codegen.model0_2.Requirement1SyncWires;
import org.openiaml.model.tests.codegen.model0_2.Requirement2StaticParams;
import org.openiaml.model.tests.codegen.model0_2.Requirement3DynamicParams;
import org.openiaml.model.tests.codegen.model0_2.Requirement4DynamicSources;
import org.openiaml.model.tests.codegen.model0_2.Requirement5Operations;
import org.openiaml.model.tests.codegen.model0_2.Requirement6Conditions;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Code generation tests: model version 0.2
 * 
 * @author jmwright
 *
 */
public class Codegen0_2Suite {

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
		suite.addTestSuite(Requirement1SyncWires.class);
		suite.addTestSuite(Requirement2StaticParams.class);
		suite.addTestSuite(Requirement3DynamicParams.class);
		suite.addTestSuite(Requirement4DynamicSources.class);
		suite.addTestSuite(Requirement5Operations.class);
		suite.addTestSuite(Requirement6Conditions.class);

		return suite;
	}

}
