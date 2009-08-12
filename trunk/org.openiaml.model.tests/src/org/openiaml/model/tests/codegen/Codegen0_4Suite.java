package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.model0_4.LoginHandlerInstance;
import org.openiaml.model.tests.codegen.model0_4.LoginHandlerInstanceComplete;
import org.openiaml.model.tests.codegen.model0_4.LoginHandlerInstanceMultiple;
import org.openiaml.model.tests.codegen.model0_4.NewInstanceWithoutId;
import org.openiaml.model.tests.codegen.model0_4.SessionNewDomainInstance;

/**
 * Code generation tests: model version 0.4
 * 
 * @author jmwright
 *
 */
public class Codegen0_4Suite {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4");
		
		suite.addTestSuite(NewInstanceWithoutId.class);
		suite.addTestSuite(LoginHandlerInstance.class);
		suite.addTestSuite(LoginHandlerInstanceMultiple.class);
		suite.addTestSuite(LoginHandlerInstanceComplete.class);
		suite.addTestSuite(SessionNewDomainInstance.class);

		return suite;
	}

}
