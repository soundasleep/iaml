/**
 * 
 */
package org.openiaml.docs.tests.expected;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openiaml.docs.generation.TranslateHTMLToLatex;

/**
 * Tests the implementation of {@link TranslateHTMLToLatex} converter.
 * This testing structure is copied from the iacleaner project.
 * 
 * @author Jevon
 *
 */
public class AllExpectedTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Expected output tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(Basic.class);
		suite.addTestSuite(Paragraphs.class);
		suite.addTestSuite(Inline.class);
		suite.addTestSuite(DomainIterator.class);
		suite.addTestSuite(NoLaTeX.class);
		suite.addTestSuite(Lists.class);
		//$JUnit-END$
		return suite;
	}

	/**
	 * Get the converter to test.
	 * 
	 * @return
	 */
	public static TranslateHTMLToLatex getConverter() {
		return new TranslateHTMLToLatex();
	}

}
