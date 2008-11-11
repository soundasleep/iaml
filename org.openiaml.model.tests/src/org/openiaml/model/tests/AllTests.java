package org.openiaml.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.CodegenTestsSuite;
import org.openiaml.model.tests.eclipse.EclipseTestsSuite;
import org.openiaml.model.tests.inference.InferenceTestsSuite;

/**
 * All tests.
 * 
 * @see InferenceTestsSuite
 * @see CodegenTestsSuite
 * @see EclipseTestsSuite
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

        suite.addTest(InferenceTestsSuite.suite());
        suite.addTest(CodegenTestsSuite.suite());
        // currently the Eclipse tests fail with an out of memory/exception_access_violation fatal error
        //suite.addTest(EclipseTestsSuite.suite());

        return suite; 
   }

}
