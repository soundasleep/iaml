/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * A page will fail within a session, but the page does not have
 * a fail handler. Only the containing session does, and we expect
 * this session will handle the problem.
 * 
 * @author jmwright
 *
 */
public class FailingOperationHandlerSessionContained extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperationHandlerSessionContained.class);
	}
	
	/**
	 * If a Session fails on access, this is caught in the Session. 
	 */
	public void testFailingOperation() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "session page", "expected failure page");
		
		// a problem should instantly occur, but we will be
		// redirected
		assertTitleEquals("expected failure page");
		assertTextPresent("this failure occured in the page");
		assertProblem();
	}
	
}
