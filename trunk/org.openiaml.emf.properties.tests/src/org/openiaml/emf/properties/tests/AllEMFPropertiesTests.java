/**
 * 
 */
package org.openiaml.emf.properties.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jmwright
 *
 */
public class AllEMFPropertiesTests {
	/**
	 * Get all the tests in this package and return as a test suite.
	 * 
	 * @return
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("EMF properties");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestReferencesCycles.class);
		suite.addTestSuite(TestEMFMetamodelProperties.class);
		//$JUnit-END$
		return suite;
	}
}
