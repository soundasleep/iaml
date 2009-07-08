package org.openiaml.model.tests.codegen.oaw;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for openArchitectureWare-style tests.
 * 
 * @author jmwright
 *
 */
public class OawTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("openArchitectureWare");

        suite.addTestSuite(ChecksTest.class);
        
        return suite; 
   }

}
