package org.openiaml.model.tests.eclipse.migration;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the Migration functionality.
 * 
 * @author jmwright
 *
 */
public class MigrateTestSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Migration");

        suite.addTestSuite(Migrate0_1SignupForm.class);

        return suite; 
   }

}
