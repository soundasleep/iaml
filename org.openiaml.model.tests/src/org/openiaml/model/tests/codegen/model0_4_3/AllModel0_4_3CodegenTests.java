package org.openiaml.model.tests.codegen.model0_4_3;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.4.3
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_3CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.3");
		
		suite.addTestSuite(LabelSimple.class);
		suite.addTestSuite(LabelSyncWire.class);
		suite.addTestSuite(LabelSyncServer.class);

		return suite;
	}

}
