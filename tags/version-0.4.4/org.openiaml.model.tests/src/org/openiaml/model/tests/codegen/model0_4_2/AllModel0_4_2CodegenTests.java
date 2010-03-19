package org.openiaml.model.tests.codegen.model0_4_2;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.4.2
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_2CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.2");
		
		suite.addTestSuite(GateDisclaimer.class);
		suite.addTestSuite(GateRequiredPage.class);
		suite.addTestSuite(BasicDomainInheritanceAutosave.class);
		suite.addTestSuite(NavigateConditionWire.class);
		suite.addTestSuite(NavigateConditionWireServer.class);
		suite.addTestSuite(ExitGateAd.class);
		suite.addTestSuite(ExitGateAdSimple.class);
		suite.addTestSuite(GateMultipleSessions.class);

		return suite;
	}

}
