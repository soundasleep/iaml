package org.openiaml.model.tests.inference;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.inference.model0_2.AllModel0_2InferenceTests;
import org.openiaml.model.tests.inference.model0_3.AllModel0_3InferenceTests;
import org.openiaml.model.tests.inference.model0_4.AllModel0_4InferenceTests;
import org.openiaml.model.tests.inference.model0_4_2.AllModel0_4_2InferenceTests;
import org.openiaml.model.tests.inference.model0_4_3.AllModel0_4_3InferenceTests;
import org.openiaml.model.tests.inference.model0_4_4.AllModel0_4_4InferenceTests;
import org.openiaml.model.tests.inference.model0_5.AllModel0_5InferenceTests;
import org.openiaml.model.tests.inference.model0_5_1.AllModel0_5_1InferenceTests;

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
        suite.addTestSuite(SyncWiresProperties.class);

        // suites of model inference tests
        suite.addTest(AllModel0_2InferenceTests.suite());
        suite.addTest(AllModel0_3InferenceTests.suite());
        suite.addTest(AllModel0_4InferenceTests.suite());
        suite.addTest(AllModel0_4_2InferenceTests.suite());
        suite.addTest(AllModel0_4_3InferenceTests.suite());
        suite.addTest(AllModel0_4_4InferenceTests.suite());
        suite.addTest(AllModel0_5InferenceTests.suite());
        suite.addTest(AllModel0_5_1InferenceTests.suite());
        
		//$JUnit-END$
        return suite; 
   }

}
