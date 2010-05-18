/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Labels can be shown or hidden.
 * 
 * @implementation Label
 * 		By default, a {@model Label} is visible.
 */
public class LabelHideShow extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelHideShow.class);
	}
	
	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	public void testLabelIsPresent() throws Exception {
		
		beginAtSitemapThenPage("Home");		
		assertLabelTextPresent("test value");
		
	}
	
	/**
	 * @implementation Label
	 * 		If a {@model Label} is shown, calling the {@model PrimitiveOperation 'hide'}
	 * 		operation will hide it from view.
	 */
	public void testHideLabel() throws Exception {
		
		beginAtSitemapThenPage("Home");
		clickButtonWithText("hide");
		assertLabelTextNotPresent("test value");
		
	}

	/**
	 * @implementation Label
	 * 		If a {@model Label} is already shown, calling the {@model PrimitiveOperation 'show'}
	 * 		operation has no effect.
	 */
	public void testShowLabel() throws Exception {
		
		beginAtSitemapThenPage("Home");
		clickButtonWithText("show");
		assertLabelTextPresent("test value");
		
	}
	
	/**
	 * @implementation Label
	 * 		If a {@model Label} is hidden, calling the {@model PrimitiveOperation 'show'}
	 * 		operation will place it back into view.
	 */
	public void testHideThenShow() throws Exception {
		
		beginAtSitemapThenPage("Home");
		clickButtonWithText("hide");
		assertLabelTextNotPresent("test value");
		
		// now click show
		clickButtonWithText("show");
		assertLabelTextPresent("test value");
		
	}

	
	/**
	 * @implementation Label
	 * 		If a {@model Label} is hidden, calling the {@model PrimitiveOperation 'hide'}
	 * 		operation again will have no effect.
	 */
	public void testHideThenHide() throws Exception {
		
		beginAtSitemapThenPage("Home");
		clickButtonWithText("hide");
		assertLabelTextNotPresent("test value");
		
		// click it again; no effect
		clickButtonWithText("hide");
		assertLabelTextNotPresent("test value");
		
		// now click show
		clickButtonWithText("show");
		assertLabelTextPresent("test value");
		
	}
	
	/**
	 * @implementation Label
	 * 		If a {@model Label} is hidden, the visibility of the Label will
	 * 		persist, according to the overall {@model Scope}.
	 * 
	 * @throws Exception
	 */
	public void testHiddenPersists() throws Exception {
	
		IFile sitemap = beginAtSitemapThenPage("Home");
		clickButtonWithText("hide");
		assertLabelTextNotPresent("test value");
		
		// reload the page
		reloadPage(sitemap, "Home");
		
		// still not present
		assertLabelTextNotPresent("test value");
		
		// reload the application
		restartSession(sitemap, "Home");
		
		// still not present
		assertLabelTextNotPresent("test value");
		
	}

}
