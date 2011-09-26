package org.openiaml.model.tests.codegen.runtime.server.direct;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the runtime library (executed without the codegen framework).
 * 
 * <p>These test cases assume that you have <code>org.openiaml.model.runtime/src/include</code>
 * mapped directly to {@link #INCLUDE_URL} through your web browser; otherwise, we
 * would have to call PHP directly from Java.
 * 
 * @author jmwright
 *
 */
public class AllDirectRuntimeTests {
	
	public static final String INCLUDE_URL = "http://localhost:8080/iaml-runtime-include/";

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Runtime Library (Direct)");

		suite.addTestSuite(DomainTestCases.class);

		return suite;
	}

}
