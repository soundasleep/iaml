package org.openiaml.model.tests.eclipse.actions;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests for the Eclipse editors custom actions.
 * 
 * @author jmwright
 *
 */
public class AllActionsTests {

	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("Actions");

        suite.addTestSuite(PartialInference.class);
        suite.addTestSuite(PartialInferenceWithinActivations.class);
        suite.addTestSuite(FormDomainObjectMapping.class);
        suite.addTestSuite(DeleteGeneratedElements.class);
        suite.addTestSuite(NonContainedEdges.class);

        return suite; 
   }

}
