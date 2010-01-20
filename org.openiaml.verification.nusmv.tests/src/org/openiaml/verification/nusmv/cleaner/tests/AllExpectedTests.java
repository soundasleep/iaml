/**
 * 
 */
package org.openiaml.verification.nusmv.cleaner.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * These cover all the expected output tests for the IACleaner
 * interface.
 * 
 * @author Jevon
 *
 */
public class AllExpectedTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Expected output tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(Arithmetic_Smv.class);
		suite.addTestSuite(Big_Smv.class);
		suite.addTestSuite(Case_Smv.class);
		//$JUnit-END$
		return suite;
	}

}
