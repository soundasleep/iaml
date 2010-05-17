package org.openiaml.model.tests.codegen.model0_3;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.3
 * 
 * @author jmwright
 *
 */
public class AllModel0_3CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.3");
		
		// session tests
		suite.addTestSuite(SessionSinglePage.class);
		suite.addTestSuite(SessionSyncWires.class);
		suite.addTestSuite(SessionInit.class);
		suite.addTestSuite(SessionSyncInSession.class);
		suite.addTestSuite(SessionSyncWiresMultiple.class);
		
		// button tests
		suite.addTestSuite(ButtonNavigate.class);
		suite.addTestSuite(ButtonSetValue.class);
		
		// select/instance tests
		suite.addTestSuite(SelectField.class);
		suite.addTestSuite(SelectFieldFromObject.class);
		suite.addTestSuite(SelectFieldQuery.class);
		suite.addTestSuite(SelectFieldFromObjectQuery.class);
		suite.addTestSuite(SelectFieldFromDynamicQuery.class);
		suite.addTestSuite(SelectFieldFromDynamicObject.class);
		suite.addTestSuite(SelectFieldExplicit.class);
		suite.addTestSuite(SelectMissing.class);
		suite.addTestSuite(SelectMissingExpected.class);
		
		// new instance/autosave tests
		suite.addTestSuite(SelectFieldFromDynamicQueryAutosave.class);
		suite.addTestSuite(NewInstanceObject.class);
		suite.addTestSuite(NewInstanceObjectAutosave.class);
		suite.addTestSuite(NewInstanceAttributeSession.class);
		suite.addTestSuite(NewInstanceAttributeSessionAutosave.class);

		// other model 0.3 tests
		suite.addTestSuite(DataFlowTemporaryVariable.class);
		suite.addTestSuite(FailingOperation.class);
		suite.addTestSuite(FailingOperationHandler.class);
		suite.addTestSuite(FailingOperationHandlerSession.class);
		suite.addTestSuite(FailingOperationHandlerSessionMultiple.class);
		suite.addTestSuite(FailingOperationHandlerSessionContained.class);
		suite.addTestSuite(TestProgressMonitor.class);

		return suite;
	}

}
