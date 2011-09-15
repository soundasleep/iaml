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
		assertEquals("EObject", LatexCodegenFunctions.humanise("EObject"));
		assertEquals("XQuery Function", LatexCodegenFunctions.humanise("XQueryFunction"));
		assertEquals("ECA Rule", LatexCodegenFunctions.humanise("ECARule"));
		assertEquals("EXSD Data Type", LatexCodegenFunctions.humanise("EXSDDataType"));
	}
	
	/**
	 * Tests {@link LatexCodegenFunctions#humanise(String)}.
	 * 
	 * @throws Exception
	 */
	public void testHumaniseProperty() throws Exception {
		// lowercase first character means everything remains lowercase
		assertEquals("input text field", LatexCodegenFunctions.humaniseProperty("inputTextField"));
		assertEquals("e object", LatexCodegenFunctions.humaniseProperty("eObject"));
		assertEquals("e xsd data type", LatexCodegenFunctions.humaniseProperty("eXSDDataType"));
		
		// events should NOT be modified
		assertEquals("onEvent", LatexCodegenFunctions.humaniseProperty("onEvent"));
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
		assertEquals("hello \\textit{world} world", LatexCodegenFunctions.convertHTMLIntoLatex("hello <em>world</em> world"));
		assertEquals("hello \\textit{world} world", LatexCodegenFunctions.convertHTMLIntoLatex("hello <EM>world</EM> world"));
	}
	
}
