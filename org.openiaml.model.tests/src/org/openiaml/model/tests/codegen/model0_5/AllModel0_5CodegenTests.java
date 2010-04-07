package org.openiaml.model.tests.codegen.model0_5;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.5
 * 
 * @author jmwright
 *
 */
public class AllModel0_5CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5");

		suite.addTestSuite(ExplicitFieldValue.class);
		suite.addTestSuite(EmailExplicit.class);
		suite.addTestSuite(EmailEvents.class);
		suite.addTestSuite(EmailChangeProperties.class);
		suite.addTestSuite(EmailInSession.class);
		suite.addTestSuite(EmailSetWire.class);
		suite.addTestSuite(ActuallySendingEmail.class);
		suite.addTestSuite(MapPointUpdateExplicitly.class);
		suite.addTestSuite(MapPointUpdateExplicitlyGoogleMaps.class);
		suite.addTestSuite(MapPointTextFieldInput.class);
		suite.addTestSuite(MapPointTextFieldInputGoogleMaps.class);
		suite.addTestSuite(MapContainingMapPoints.class);
		suite.addTestSuite(MapContainingMapPointsGoogleMaps.class);
		suite.addTestSuite(MapTextFieldInput.class);
		suite.addTestSuite(MapFormInput.class);

		return suite;
	}

}
