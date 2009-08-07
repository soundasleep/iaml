package org.openiaml.model.tests.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.model0_1.LoginHandlerKey;
import org.openiaml.model.tests.codegen.model0_1.SimpleTestCase;
import org.openiaml.model.tests.codegen.model0_1.SyncFieldApplicationElementProperty;
import org.openiaml.model.tests.codegen.model0_1.SyncFieldDomainAttribute;
import org.openiaml.model.tests.codegen.model0_1.SyncFormDomainObject;
import org.openiaml.model.tests.codegen.model0_1.SyncStaticValueField;
import org.openiaml.model.tests.codegen.model0_1.SyncWiresMultiple;
import org.openiaml.model.tests.codegen.model0_1.SyncWiresPagesTestCase;
import org.openiaml.model.tests.codegen.model0_1.SyncWiresProperties;
import org.openiaml.model.tests.codegen.model0_1.SyncWiresTestCase;

/**
 * Code generation tests: model version 0.1
 * 
 * @author jmwright
 *
 */
public class Codegen0_1Suite {

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
