package org.openiaml.model.tests.codegen;

import org.openiaml.model.tests.codegen.SimpleTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for code generation.
 * 
 * @author jmwright
 *
 */
public class CodegenTestsSuite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Code Generation");

        suite.addTestSuite(SimpleTestCase.class);
        suite.addTestSuite(SyncWiresTestCase.class);
        suite.addTestSuite(SyncWiresPagesTestCase.class);

        return suite; 
   }

}
