package org.openiaml.model.tests.codegen.model0_5_2;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.5.2
 * 
 * @author jmwright
 *
 */
public class AllModel0_5_2CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5.2");

		suite.addTestSuite(SyncWireInstant.class);
		suite.addTestSuite(SetWireInstant.class);
		suite.addTestSuite(IteratorListSetWire.class);
		suite.addTestSuite(IteratorListSetWireHideShow.class);
		suite.addTestSuite(IteratorListSetWireSearch.class);
		suite.addTestSuite(IteratorListSetClickResults.class);
		suite.addTestSuite(IteratorListSearchInstant.class);
		suite.addTestSuite(AutocompleteWireSimple.class);

		return suite;
	}

}
