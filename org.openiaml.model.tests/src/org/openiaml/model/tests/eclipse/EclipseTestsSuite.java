package org.openiaml.model.tests.eclipse;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.eclipse.migration.MigrateTestSuite;
import org.openiaml.model.tests.eclipse.shortcuts.EclipseShortcutsTestSuite;

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
		//$JUnit-BEGIN$
        suite.addTestSuite(LoadDiagramTestCase.class);
        suite.addTestSuite(OpenSubDiagramTestCase.class);
        suite.addTestSuite(InitializeDiagramTestCase.class);
        suite.addTestSuite(ExportImagesTestCase.class);
        suite.addTestSuite(BreadcrumbTestCase.class);
        suite.addTestSuite(CreateNewDiagramTestCase.class);
		//$JUnit-END$
        
        // add the shortcut tests
        suite.addTest(EclipseShortcutsTestSuite.suite());

        // add the migration tests
        suite.addTest(MigrateTestSuite.suite());

        return suite;
   }

}
