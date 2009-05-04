/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * A very simple test case: simply generates the site code, and
 * makes sure a testing sitemap is available.
 * 
 * @author jmwright
 *
 */
public class SimpleTestCase extends CodegenTestCase {

	protected InternetApplication root;

	protected void setUp() throws Exception {
		root = loadAndCodegen(SimpleTestCase.class);
	}
	
	/**
	 * Check that the testing sitemap exists.
	 */
	public void testSitemap() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("untitled");
		assertTitleMatch("sitemap");

	}
	
}
