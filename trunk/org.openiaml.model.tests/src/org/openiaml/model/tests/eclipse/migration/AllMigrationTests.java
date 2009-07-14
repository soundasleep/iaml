package org.openiaml.model.tests.eclipse.migration;

import org.openiaml.model.tests.eclipse.migration.model0_3.Migrate0_3TestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the Migration functionality.
 * 
 * @author jmwright
 *
 */
public class AllMigrationTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Migration");

        suite.addTestSuite(Migrate0_1SignupForm.class);
        suite.addTestSuite(Migrate0_2SyncPages.class);
        
        // model 0.3
        suite.addTest(Migrate0_3TestSuite.suite());
        
        return suite; 
   }

}
