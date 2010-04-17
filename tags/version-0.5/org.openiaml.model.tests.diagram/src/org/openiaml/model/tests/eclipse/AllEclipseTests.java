package org.openiaml.model.tests.eclipse;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.eclipse.actions.AllActionsTests;
import org.openiaml.model.tests.eclipse.helpers.AllHelpersTests;
import org.openiaml.model.tests.eclipse.migration.AllMigrationTests;
import org.openiaml.model.tests.eclipse.shortcuts.AllShortcutsTests;

/**
 * All tests for the Eclipse editors.
 * 
 * @author jmwright
 *
 */
public class AllEclipseTests {

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
        suite.addTestSuite(ParentNameTestCase.class);
        suite.addTestSuite(DerivedPropertyMarker.class);
        suite.addTestSuite(MarkContainersAsOverridden.class);
        suite.addTestSuite(OverriddenPropertyMarker.class);
		//$JUnit-END$
        
        // add the helpers tests
        suite.addTest(AllHelpersTests.suite());

        // add the shortcut tests
        suite.addTest(AllShortcutsTests.suite());

        // add the action tests
        suite.addTest(AllActionsTests.suite());

        // add the migration tests
        suite.addTest(AllMigrationTests.suite());

        return suite;
   }

}
