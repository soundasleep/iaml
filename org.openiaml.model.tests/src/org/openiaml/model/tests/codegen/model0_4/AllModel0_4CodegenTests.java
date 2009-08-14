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
		
		// SetWire tests
		suite.addTestSuite(SetWireClient.class);
		suite.addTestSuite(SetWireRemote.class);
		suite.addTestSuite(SetWireChained.class);
		suite.addTestSuite(SetWireSync.class);
		suite.addTestSuite(SetWireSyncChained.class);

		return suite;
	}

}
