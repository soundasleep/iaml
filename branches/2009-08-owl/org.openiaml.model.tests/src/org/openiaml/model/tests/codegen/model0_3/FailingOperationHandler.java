/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests a failing operation that is contained with a
 * navigate wire handler.
 * 
 * @author jmwright
 *
 */
public class FailingOperationHandler extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperationHandler.class);
	}
	
	public void testFailingOperation() throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "target page", "expected failure page");
		
		// a problem should instantly occur, but we will be
		// redirected
		assertTitleEquals("expected failure page");
		assertTextPresent("an expected failure message");
		assertProblem();
	}
	
}
