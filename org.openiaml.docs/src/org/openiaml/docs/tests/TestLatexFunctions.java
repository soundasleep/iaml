/**
 * 
 */
package org.openiaml.docs.tests;

import junit.framework.TestCase;

import org.openiaml.docs.generation.codegen.LatexCodegenFunctions;

/**
 * @author jmwright
 *
 */
public class TestLatexFunctions extends TestCase {

	public void testHumanise() throws Exception {
		
		assertEquals("Input Text Field", LatexCodegenFunctions.humanise("InputTextField"));
		
	}
	
}
