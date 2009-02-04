/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test SyncWires
 * 
 * @author jmwright
 *
 */
public class SyncWiresTestCase extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncWiresTestCase.iaml");
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
		String testingText3 = "random" + new Random().nextInt(32768);
		
		// sanity
		assertNotSame(testingText, testingText2);
		assertNotSame(testingText2, testingText3);
		
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

		assertTextFieldEquals("name1", testingText);
		
		// it should sync over automatically
		assertTextFieldEquals("name2", testingText);
		
		// and with the other elements as well
		assertFormElementPresent("name3");
		assertFormElementPresent("name4");
		assertTextFieldEquals("name3", testingText);	// tests element chaining
		assertTextFieldEquals("name4", testingText);
		
		// edit some other fields on the same page
		setTextField("name3", testingText2);
		assertTextFieldEquals("name3", testingText2);	// tests element chaining + skipping
		assertTextFieldEquals("name1", testingText2);
		
		// one to four
		setTextField("name1", testingText3);
		assertTextFieldEquals("name1", testingText3);
		assertTextFieldEquals("name4", testingText3);
		
		// an unrelated field shouldn't change
		assertTextFieldEquals("unrelated_field", "");

	}
	
	/**
	 * Because all of these fields are on the current page,
	 * we must make sure that no remote functions have been
	 * called.
	 */
	public void testPropertiesStayLocal() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		// no ajax methods should have been called
		assertNoRemoteCalls();
	}
	
}
