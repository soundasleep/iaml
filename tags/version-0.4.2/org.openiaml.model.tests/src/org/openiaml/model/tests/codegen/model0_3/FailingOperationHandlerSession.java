/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests a failing operation within the 'access' of the session.
 * 
 * @author jmwright
 *
 */
public class FailingOperationHandlerSession extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperationHandlerSession.class);
	}
	
	/**
	 * Normal pages work fine.
	 */
	public void testNormalPage() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("normal page");
		
		assertTitleEquals("normal page");	// no problems
		assertNoProblem();
	}
	
	/**
	 * If a Session fails on access, this is caught in the Session. 
	 */
	public void testFailingOperation() throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "session page", "expected failure page");
		
		// a problem should instantly occur, but we will be
		// redirected
		assertTitleEquals("expected failure page");
		assertTextPresent("an expected failure message");
		assertProblem();
	}
	
}
