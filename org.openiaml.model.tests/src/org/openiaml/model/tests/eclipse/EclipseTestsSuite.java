package org.openiaml.model.tests.eclipse;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the Eclipse editors.
 * 
 * @author jmwright
 *
 */
public class EclipseTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Eclipse Editors");

        suite.addTestSuite(LoadDiagramTestCase.class);
        suite.addTestSuite(OpenSubDiagramTestCase.class);
        suite.addTestSuite(InitializeDiagramTestCase.class);

        return suite; 
   }

}
