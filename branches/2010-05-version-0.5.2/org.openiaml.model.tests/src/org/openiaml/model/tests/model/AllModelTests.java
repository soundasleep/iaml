package org.openiaml.model.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for model quality.
 * 
 * @author jmwright
 *
 */
public class AllModelTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Model Quality");

        suite.addTestSuite(ModelTestCase.class);
        suite.addTestSuite(ContainmentTestCase.class);
        suite.addTestSuite(EdgeTypesTest.class);
        suite.addTestSuite(BuiltinDataTypesTests.class);
        
        return suite; 
   }

}
