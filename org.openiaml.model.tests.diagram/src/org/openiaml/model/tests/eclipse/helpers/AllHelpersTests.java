package org.openiaml.model.tests.eclipse.helpers;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the helper plugin for the Eclipse editors.
 * 
 * @author jmwright
 *
 */
public class AllHelpersTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Helpers");

        suite.addTestSuite(GetShortcutsTest.class);

        return suite; 
   }

}
