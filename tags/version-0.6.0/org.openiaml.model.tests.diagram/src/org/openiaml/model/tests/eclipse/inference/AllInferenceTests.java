package org.openiaml.model.tests.eclipse.inference;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.eclipse.inference.model0_3.AllModel0_3EclipseInferenceTests;
import org.openiaml.model.tests.eclipse.inference.model0_4.AllModel0_4EclipseInferenceTests;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class AllInferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * TODO split all of the inference tests into separate packages
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Inference (Eclipse)");
		//$JUnit-BEGIN$
        
        // suites of model inference tests
        suite.addTest(AllModel0_3EclipseInferenceTests.suite());
        suite.addTest(AllModel0_4EclipseInferenceTests.suite());
        
		//$JUnit-END$
        return suite; 
   }

}
