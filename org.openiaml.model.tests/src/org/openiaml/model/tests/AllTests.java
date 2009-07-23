package org.openiaml.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.AllCodegenTests;
import org.openiaml.model.tests.drools.DroolsQueueTest;
import org.openiaml.model.tests.eclipse.AllEclipseTests;
import org.openiaml.model.tests.inference.AllInferenceTests;
import org.openiaml.model.tests.model.AllModelTests;
import org.openiaml.model.tests.release.AllReleaseTests;

/**
 * All tests.
 * 
 * @see AllInferenceTests
 * @see AllCodegenTests
 * @see AllEclipseTests
 * @author jmwright
 *
 */
public class AllTests {
	
	/**
	 * Compile all the suites.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("All Tests");

        suite.addTest(AllInferenceTests.suite());
        suite.addTest(AllCodegenTests.suite());
        suite.addTestSuite(DroolsQueueTest.class);
        // currently the Eclipse tests fail with an out of memory/exception_access_violation fatal error
        suite.addTest(AllEclipseTests.suite());
        suite.addTest(AllReleaseTests.suite());
        suite.addTest(AllModelTests.suite());

        return suite; 
   }

}
