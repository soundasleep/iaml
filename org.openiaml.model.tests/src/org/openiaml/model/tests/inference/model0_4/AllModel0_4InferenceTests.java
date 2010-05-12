package org.openiaml.model.tests.inference.model0_4;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All inference tests for model 0.4.
 * 
 * @author jmwright
 *
 */
public class AllModel0_4InferenceTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.4");
		
		suite.addTestSuite(GeneratedPrimaryKey.class);
		suite.addTestSuite(SelectWireAttributeInference.class);
		suite.addTestSuite(DomainInstanceSaveOperation.class);
		suite.addTestSuite(LoginHandlerInstance.class);
		suite.addTestSuite(LoginHandlerKey.class);
		suite.addTestSuite(LoginHandlerInstanceMultiple.class);
		suite.addTestSuite(SetWireClient.class);
		suite.addTestSuite(UserRoles.class);
		suite.addTestSuite(UserRolesLoginHandler.class);
		suite.addTestSuite(DomainInheritance.class);
		suite.addTestSuite(DomainInheritanceEditing.class);
		suite.addTestSuite(UserModifyRoles.class);
		suite.addTestSuite(OperationCallNode.class);
		suite.addTestSuite(SetWireSyncChained.class);
		suite.addTestSuite(NewInstanceWithoutId.class);
		
		return suite;
	}
	
}
