package org.openiaml.model.tests.inference;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.inference.model0_3.AllModel0_3InferenceTests;
import org.openiaml.model.tests.inference.model0_4.AllModel0_4InferenceTests;
import org.openiaml.model.tests.inference.model0_4_2.AllModel0_4_2InferenceTests;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class AllInferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * TODO split all of the inference tests into separate packages
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Inference");
		//$JUnit-BEGIN$
        suite.addTestSuite(LoadModelTestCase.class);
        suite.addTestSuite(SyncWireTestCase.class);
        suite.addTestSuite(SyncFieldDomainAttribute.class);
        suite.addTestSuite(SyncFormDomainObject.class);
        suite.addTestSuite(SyncWireProperties.class);
        
        // model 0.2
        suite.addTestSuite(ConditionWireFalse.class);
        suite.addTestSuite(ConditionWireFalseServer.class);
        suite.addTestSuite(ConditionWireXpath.class);
        suite.addTestSuite(ConditionWireXpathThreePages.class);
        suite.addTestSuite(Requirement4DynamicSources.class);
        suite.addTestSuite(SavedRuleSources.class);
        suite.addTestSuite(SessionSyncWires.class);
        
        // model 0.3
        suite.addTestSuite(DatabaseWithInputForm.class);
        suite.addTestSuite(PropertiesFileWithInputForm.class);
        suite.addTestSuite(SelectField.class);
        suite.addTestSuite(SelectFieldFromObject.class);
        
        // suites of model inference tests
        suite.addTest(AllModel0_3InferenceTests.suite());
        suite.addTest(AllModel0_4InferenceTests.suite());
        suite.addTest(AllModel0_4_2InferenceTests.suite());
        
		//$JUnit-END$
        return suite; 
   }

}
