package org.openiaml.model.tests.inference;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class InferenceTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Inference");
		//$JUnit-BEGIN$
        suite.addTestSuite(LoadModelTestCase.class);
        suite.addTestSuite(SyncWireTestCase.class);
        suite.addTestSuite(SyncFieldDomainAttribute.class);
        suite.addTestSuite(SyncWireProperties.class);
		//$JUnit-END$
        return suite; 
   }

}