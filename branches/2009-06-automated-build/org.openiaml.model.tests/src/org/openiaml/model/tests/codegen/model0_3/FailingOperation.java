/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Tests a failing operation.
 * 
 * @author jmwright
 *
 */
public class FailingOperation extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperation.class);
	}
	
	public void testFailingOperation() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		try {
			beginAtSitemapThenPage(sitemap, "page");
			fail("We should not be able to load page 'page'");
		} catch (FailingHttpStatusCodeException e) {
			// we should instantly have an exception occur
			// expected
			
			assertTextPresent("an expected failure message");
			assertProblem();
		}
	}
	
}
