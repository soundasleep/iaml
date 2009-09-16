/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.openiaml.model.tests.CodegenTestCase;
import org.openiaml.model.tests.DijkstraAlgorithm;

/**
 * Tests the navigation of buttons.
 * 
 * @author jmwright
 *
 */
public class ButtonNavigate extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ButtonNavigate.class);
	}
	
	/**
	 * @semantics A {@model Button} renders on a page as a button. 
	 * @semantics test {@link #setUp()}, foo.
	 * @semantics test {@link ButtonNavigate#setUp()}
	 * @semantics test {@link InvalidClass#setUp()}
	 * @another Also see {@link DijkstraAlgorithm}.
	 * @throws Exception
	 */
	public void testPage1to2() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("page1");
		
		// there should be three buttons here
		assertButtonPresentWithText("go to page 2");
		assertButtonPresentWithText("go nowhere");
		assertButtonPresentWithText("go to page 3 implicitly");
		
		// click the first button
		clickButtonWithText("go to page 2");
		
		// we should now be on page 2
		assertTitleMatch("page2");

	}

	public void testPage1toNowhere() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("page1");
		
		// there should be three buttons here
		assertButtonPresentWithText("go to page 2");
		assertButtonPresentWithText("go nowhere");
		assertButtonPresentWithText("go to page 3 implicitly");
		
		// click the second button
		clickButtonWithText("go nowhere");
		
		// we should still be on the same page
		assertTitleMatch("page1");

	}

	public void testPage1to3() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("page1");
		
		// there should be three buttons here
		assertButtonPresentWithText("go to page 2");
		assertButtonPresentWithText("go nowhere");
		assertButtonPresentWithText("go to page 3 implicitly");
		
		// click the first button
		clickButtonWithText("go to page 3 implicitly");
		
		// we should now be on page 3
		assertTitleMatch("page3");

	}
	
}
