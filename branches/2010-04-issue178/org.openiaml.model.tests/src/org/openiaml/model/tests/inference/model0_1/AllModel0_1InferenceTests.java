package org.openiaml.model.tests.inference.model0_1;




import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.1.
 * 
 * @author jmwright
 *
 */
public class AllModel0_1InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.1");

        suite.addTestSuite(LoadModelTestCase.class);
        suite.addTestSuite(SyncWireTestCase.class);
        suite.addTestSuite(SyncFieldDomainAttribute.class);
        suite.addTestSuite(SyncFormDomainObject.class);
        suite.addTestSuite(SyncWiresProperties.class);
        
		return suite;
	}
	
}
