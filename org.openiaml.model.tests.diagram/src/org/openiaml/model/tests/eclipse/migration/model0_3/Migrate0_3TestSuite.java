package org.openiaml.model.tests.eclipse.migration.model0_3;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the Migration functionality.
 * 
 * @author jmwright
 *
 */
public class Migrate0_3TestSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Model 0.3 Migration");

        suite.addTestSuite(MigrateDomainObjects.class);
        
        return suite; 
   }

}
