package org.openiaml.model.tests.codegen.model0_4_1;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.4.1
 * 
 * @author jmwright
 *
 */
public class AllModel0_4_1CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4.1");
		
		suite.addTestSuite(SetValueStatic.class);
		
		// arithmetic
		suite.addTestSuite(ArithmeticAdd.class);
		suite.addTestSuite(ArithmeticSubtract.class);
		suite.addTestSuite(ArithmeticMultiply.class);
		suite.addTestSuite(ArithmeticDivide.class);

		return suite;
	}

}
