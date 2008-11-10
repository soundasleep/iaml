package org.openiaml.model.tests.codegen;

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
        suite.addTestSuite(SyncFieldDomainAttribute.class);
        suite.addTestSuite(SyncFormDomainObject.class);

        return suite; 
   }

}
