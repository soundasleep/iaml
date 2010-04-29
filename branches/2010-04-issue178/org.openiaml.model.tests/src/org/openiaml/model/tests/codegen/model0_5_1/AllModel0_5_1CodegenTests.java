package org.openiaml.model.tests.codegen.model0_5_1;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Code generation tests: model version 0.5.1
 * 
 * @author jmwright
 *
 */
public class AllModel0_5_1CodegenTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() { 
		TestSuite suite = new TestSuite("Model 0.5.1");

		suite.addTestSuite(NavigateWithParameter.class);
		suite.addTestSuite(ReadRSSFeeds.class);
		suite.addTestSuite(ViewDatabaseTypes.class);
		suite.addTestSuite(FeedProducerComplete.class);
		suite.addTestSuite(ImplicitParameterValues.class);
		suite.addTestSuite(FeedProducerCompleteWithoutLink.class);
		suite.addTestSuite(FeedProducerSimple.class);
		suite.addTestSuite(RemoteRSSFeed.class);
		suite.addTestSuite(RemoteRSSFeedPiped.class);
		suite.addTestSuite(OpenIDAsAuthor.class);
		suite.addTestSuite(OperationalExample.class);

		return suite;
	}

}
