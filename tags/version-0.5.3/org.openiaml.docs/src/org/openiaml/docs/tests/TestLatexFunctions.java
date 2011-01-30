/**
 * 
 */
package org.openiaml.docs.tests;

import junit.framework.TestCase;

import org.openiaml.docs.generation.codegen.LatexCodegenFunctions;

/**
 * Tests the functionality in {@link LatexCodegenFunctions}.
 * 
 * @author jmwright
 *
 */
public class TestLatexFunctions extends TestCase {

	/**
	 * Tests {@link LatexCodegenFunctions#humanise(String)}.
	 * 
	 * @throws Exception
	 */
	public void testHumanise() throws Exception {
		assertEquals("Input Text Field", LatexCodegenFunctions.humanise("InputTextField"));
	}
	
	/**
	 * Tests {@link LatexCodegenFunctions#convertHTMLIntoLatex(String)}.
	 * 
	 * @throws Exception
	 */
	public void testConvertHTMLIntoLatex() throws Exception {
		assertEquals("test", LatexCodegenFunctions.convertHTMLIntoLatex("test"));
		assertEquals("test\\_test", LatexCodegenFunctions.convertHTMLIntoLatex("test_test"));
		assertEquals("test \\& test", LatexCodegenFunctions.convertHTMLIntoLatex("test &amp; test"));
		assertEquals("<hello>", LatexCodegenFunctions.convertHTMLIntoLatex("&lt;hello&gt;"));
		assertEquals("hello \\code{world} world", LatexCodegenFunctions.convertHTMLIntoLatex("hello <code>world</code> world"));
	}
	
}
