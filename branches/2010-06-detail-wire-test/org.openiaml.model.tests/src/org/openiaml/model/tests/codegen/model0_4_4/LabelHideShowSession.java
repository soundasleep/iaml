/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * @implementation Label
 * 		If a {@model Label} is contained within a {@model Session}, then the
 * 		visibility of this {@model Label} persists according to the {@model Session}.
 * @see LabelHideShow
 */
public class LabelHideShowSession extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelHideShowSession.class);
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
		
		// it is present now!
		assertLabelTextPresent("test value");
		
	}

}
