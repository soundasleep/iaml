/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Random;

import junit.framework.ComparisonFailure;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test SyncWires across multiple pages
 * 
 * @author jmwright
 *
 */
public class SyncWiresPagesTestCase extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncWiresPagesTestCase.iaml");
	}
	
	/**
	 * tests a single page that have multiple InputTextFields on them
	 * for a sync wire: when one field changes, the others
	 * should as well.
	 * @throws Exception 
	 * 
	 */
	public void testSyncAcrossPages() throws Exception {
		try {
		String testingText = new Date().toString();
		String testingText2 = "random" + new Random().nextInt(32768);
		String testingText3 = "kittens" + new Random().nextInt(32768);
		
		// sanity
		assertNotSame(testingText, testingText2);
		assertNotSame(testingText2, testingText3);
		
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		{
			beginAtSitemapThenPage(sitemap, "page1");
			
			// fill in a field on page 1
			String label_text1 = getLabelIDForText("text1");
			String label_text2 = getLabelIDForText("text2");
			
			assertLabelPresent(label_text1);
			assertLabelPresent(label_text2);
			setLabeledFormElementField(label_text1, testingText);
			assertTextFieldEquals("visual_11d293c2f82_4c", testingText);
			
			// there should be a debug message saying it is saving
			assertTextPresent("store_event called");
		}

		{
			// go to page2
			// page2 has "text1" and should be in sync too
			gotoSitemapThenPage(sitemap, "page2");
	
			// check text1 field has changed
			String label_text1 = getLabelIDForText("text1");
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text1);
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text1, testingText);
		}
		
		{
			// go to page3
			gotoSitemapThenPage(sitemap, "page3");
			
			// check text1 field has changed
			String label_text1 = getLabelIDForText("text1");
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text1);
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text1, testingText);
		}
			
		{
			// go to page 2
			gotoSitemapThenPage(sitemap, "page2");
			
			// change field
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text3);
			setLabeledFormElementField(label_text3, testingText2);
		}

		{
			// go to page 3
			gotoSitemapThenPage(sitemap, "page3");
			
			// check fields have synced
			String label_text3 = getLabelIDForText("text3");
			String label_newText = getLabelIDForText("newText");

			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text3, testingText2);
			assertLabelPresent(label_newText);
			assertLabeledFieldEquals(label_newText, testingText2);
		}
		
		{
			// go to page 4 - it should still sync over even if the fields are only sync'd up on the page itself
			gotoSitemapThenPage(sitemap, "page4");
			
			// check fields have synced
			String label_text5 = getLabelIDForText("text5");
			String label_newText = getLabelIDForText("newText");

			assertLabelPresent(label_newText);
			assertLabeledFieldEquals(label_newText, testingText2);
			assertLabelPresent(label_text5);
			assertLabeledFieldEquals(label_text5, testingText2);
		}
		
		{
			// go to page 5 and check this one
			gotoSitemapThenPage(sitemap, "page5");

			String label_text5 = getLabelIDForText("text5");

			assertLabelPresent(label_text5);
			assertLabeledFieldEquals(label_text5, testingText2);
			
			// lets change it, why not
			setLabeledFormElementField(label_text5, testingText3);
		}
		
		// TODO: this block currently fails, because deep operation chaining is not
		// supported. this needs to be supported, but for version 0.1.0 we will
		// allow the test case to fail.
		try	{
			// it should change something on page 2
			// if this fails, it is because it cannot chain 
			// page5.text5-->page4.text5-->page4.newText-->
			//    page3.newText-->page3.text3-->page2.text3
			// (note the on-page syncs)
			gotoSitemapThenPage(sitemap, "page2");
			waitForAjax();

			String label_text3 = getLabelIDForText("text3");

			// check fields have synced
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text3, testingText3);
			
			fail();
		} catch (ComparisonFailure e) {
			// expected until operation chaining is completed
			System.err.println("[Expected exception caught]: expected until operation chaining is completed: " + e);
		}
		
		} catch (Error e) {
			// print out the source code
			System.out.println(getTester().getPageSource());
			// throw out any response text too
			throw new RuntimeException("Response = '" + getElementById("response").getTextContent() + "' Debug='" + getElementById("debug").getTextContent() + "'", e);
		}
		
	}

}
