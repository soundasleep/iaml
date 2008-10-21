/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;

import org.eclipse.core.resources.IFile;

/**
 * Test SyncWires
 * 
 * @author jmwright
 *
 */
public class SyncWiresTestCase extends CodegenWebTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		doTransform("models/test-sync.iaml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * tests a single page that have multiple InputTextFields on them
	 * for a sync wire: when one field changes, the others
	 * should as well.
	 * 
	 */
	public void testSyncOnPage() {
		String testingText = new Date().toString();
		String testingText2 = this.toString();
		
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText("on-page");
		clickLinkWithText("on-page");
		assertTitleMatch("on-page");
		
		// fill in a field
		assertFormElementPresent("name1");
		assertFormElementPresent("name2");
		setTextField("name1", testingText);

		// why is this deprecated? o_O
		assertFormElementEquals("name1", testingText);
		
		// it should sync over automatically
		assertFormElementEquals("name2", testingText);
		
		// and with the other elements as well
		assertFormElementPresent("name3");
		assertFormElementPresent("name4");
		assertFormElementEquals("name3", testingText);
		assertFormElementEquals("name4", testingText);
		
		// edit some other fields on the same page
		setTextField("name3", testingText2);
		assertFormElementEquals("name3", testingText2);
		assertFormElementEquals("name1", testingText2);
		
		// an unrelated field shouldn't change
		assertFormElementEquals("unrelated-field", "");

	}
}
