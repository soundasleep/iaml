/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Testing a simple value to field sync wire.
 * 
 * property <--> text field
 * 
 * @author jmwright
 *
 */
public class SyncStaticValueField extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SyncStaticValueField.class);
	}
	
	public void testSync() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to target
		beginAtSitemapThenPage(sitemap, "target");

		// find the value
		String nameId = getLabelIDForText("current value");
		assertLabeledFieldEquals(nameId, "a default value");
		
	}

}
