package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.model0_3.ButtonNavigate;
import org.openiaml.model.tests.codegen.model0_3.ButtonSetValue;
import org.openiaml.model.tests.codegen.model0_3.DataFlowTemporaryVariable;
import org.openiaml.model.tests.codegen.model0_3.DatabaseWithInputForm;
import org.openiaml.model.tests.codegen.model0_3.FailingOperation;
import org.openiaml.model.tests.codegen.model0_3.FailingOperationHandler;
import org.openiaml.model.tests.codegen.model0_3.PropertiesFileWithInputForm;
import org.openiaml.model.tests.codegen.model0_3.SelectField;
import org.openiaml.model.tests.codegen.model0_3.SessionInit;
import org.openiaml.model.tests.codegen.model0_3.SessionSinglePage;
import org.openiaml.model.tests.codegen.model0_3.SessionSyncInSession;
import org.openiaml.model.tests.codegen.model0_3.SessionSyncWires;
import org.openiaml.model.tests.codegen.model0_3.SessionSyncWiresMultiple;

/**
 * Code generation tests: model version 0.3
 * 
 * @author jmwright
 *
 */
public class Codegen0_3Suite {

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

		// other model 0.3 tests
		suite.addTestSuite(DataFlowTemporaryVariable.class);
		suite.addTestSuite(DatabaseWithInputForm.class);
		suite.addTestSuite(PropertiesFileWithInputForm.class);
		suite.addTestSuite(FailingOperation.class);
		suite.addTestSuite(FailingOperationHandler.class);

		return suite;
	}

}
