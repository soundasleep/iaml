package org.openiaml.model.tests.codegen.model0_4;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.4
 * 
 * @author jmwright
 *
 */
public class AllModel0_4CodegenTests {

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
		suite.addTestSuite(JoinSplitClientSide.class);
		suite.addTestSuite(JoinSplitServerSide.class);
		suite.addTestSuite(QueryParameterSet.class);
		suite.addTestSuite(QueryParameterAsParameter.class);
		
		// SetWire tests
		suite.addTestSuite(SetWireClient.class);
		suite.addTestSuite(SetWireRemote.class);
		suite.addTestSuite(SetWireChained.class);
		suite.addTestSuite(SetWireSync.class);
		suite.addTestSuite(SetWireSyncChained.class);
		
		// User security tests
		suite.addTestSuite(LoginHandlerUser.class);
		suite.addTestSuite(UserCreateRoles.class);
		suite.addTestSuite(UserModifyRoles.class);
		suite.addTestSuite(UserMultiplePermissions.class);
		suite.addTestSuite(UserMultiplePermissionsOr.class);
		suite.addTestSuite(UserMultiplePermissionsOrRoles.class);
		suite.addTestSuite(UserPermissions.class);
		suite.addTestSuite(UserPermissionsPage.class);
		suite.addTestSuite(UserPermissionsRecursive.class);
		suite.addTestSuite(UserRoleExtends.class);
		suite.addTestSuite(UserRoles.class);
		suite.addTestSuite(UserRolesLoginHandler.class);
		
		// Domain Inheritance tests
		suite.addTestSuite(DomainInheritance.class);
		suite.addTestSuite(DomainInheritanceEditing.class);

		return suite;
	}

}
