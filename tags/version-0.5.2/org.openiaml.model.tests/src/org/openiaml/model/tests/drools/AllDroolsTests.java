package org.openiaml.model.tests.drools;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for Drools rules.
 * 
 * @author jmwright
 *
 */
public class AllDroolsTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Drools");

        suite.addTestSuite(DroolsQueueTest.class);
        suite.addTestSuite(DumpDroolsXmlTest.class);
        suite.addTestSuite(DroolsPackageTest.class);
        
        return suite; 
   }

}
