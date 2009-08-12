package org.openiaml.model.tests.codegen.model0_1;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.1
 * 
 * @author jmwright
 *
 */
public class AllModel0_1CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.1");
		
		suite.addTestSuite(SimpleTestCase.class);
		suite.addTestSuite(SyncFieldDomainAttribute.class);
		suite.addTestSuite(SyncStaticValueField.class);
		suite.addTestSuite(SyncWiresPagesTestCase.class);
		suite.addTestSuite(LoginHandlerKey.class);
		suite.addTestSuite(SyncFormDomainObject.class);
		suite.addTestSuite(SyncFieldApplicationElementProperty.class);
		suite.addTestSuite(SyncWiresMultiple.class);
		suite.addTestSuite(SyncWiresTestCase.class);
		suite.addTestSuite(SyncWiresProperties.class);

		return suite;
	}

}
