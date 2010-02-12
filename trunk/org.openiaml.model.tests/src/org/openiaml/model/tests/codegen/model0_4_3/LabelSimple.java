/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Demonstrates a static label which does not change.
 * 
 * @author jmwright
 */
public class LabelSimple extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelSimple.class);
	}
	
	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the text "initial value" should be on the page
		assertMatch("initial value");
		
	}
	
}
