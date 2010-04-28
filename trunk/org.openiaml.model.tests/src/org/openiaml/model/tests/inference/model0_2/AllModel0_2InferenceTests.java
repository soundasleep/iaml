package org.openiaml.model.tests.inference.model0_2;



import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.2.
 * 
 * @author jmwright
 *
 */
public class AllModel0_2InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.2");

        suite.addTestSuite(ConditionWireFalse.class);
        suite.addTestSuite(ConditionWireFalseServer.class);
        suite.addTestSuite(ConditionWireXpath.class);
        suite.addTestSuite(ConditionWireXpathThreePages.class);
        suite.addTestSuite(Requirement4DynamicSources.class);
        suite.addTestSuite(SavedRuleSources.class);
        suite.addTestSuite(SessionSyncWires.class);
		
		return suite;
	}
	
}
