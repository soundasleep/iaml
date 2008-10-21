/**
 * 
 */
package org.openiaml.model.tests.codegen;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.JetWebTestCase;

/**
 * @author jmwright
 *
 */
public class SimpleTestCase extends JetWebTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		doTransform("models/simple.iaml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSitemap() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		// test
		
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("untitled");
		assertTitleMatch("sitemap");

	}
	
}
