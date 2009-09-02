package org.openiaml.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.AllCodegenTests;
import org.openiaml.model.tests.drools.AllDroolsTests;
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
        suite.addTest(AllDroolsTests.suite());
        suite.addTest(AllReleaseTests.suite());
        suite.addTest(AllModelTests.suite());

        return suite; 
   }

}
