package org.openiaml.model.tests.release;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for release quality.
 * 
 * @author jmwright
 *
 */
public class ReleaseTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Release Quality");

        suite.addTestSuite(PluginsTestCase.class);
        
        return suite; 
   }

}
