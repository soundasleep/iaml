package org.openiaml.model.tests.codegen.model0_5_3;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.5.3
 * 
 * @author jmwright
 *
 */
public class AllModel0_5_3CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5.3");

		suite.addTestSuite(HiddenFieldSyncWire.class);
		suite.addTestSuite(HiddenFieldAsParameter.class);
		suite.addTestSuite(DetailWireOnSetIteratorList.class);
		suite.addTestSuite(CustomHeaderFooter.class);

		return suite;
	}

}
