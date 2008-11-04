/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Random;

import junit.framework.AssertionFailedError;
import junit.framework.ComparisonFailure;
import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test SyncWires across multiple pages
 * 
 * @author jmwright
 *
 */
public class SyncWiresPagesTestCase extends InferenceTestCase {
	
	protected void setUp() throws Exception {
		String modelFile = "models/codegen-sync-pages.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);

		root = (InternetApplication) model;
		
		// we now try to do inference
		EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
		CreateMissingElements ce = new CreateMissingElements(handler);
		ce.create(root);
		
		// write out this inferred model for reference
		String outModel = saveInferredModel().getAbsolutePath();

		super.setUp();		// create project
		doTransform(outModel);	// output to project
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Have we loaded at least one page (so we can find an ajax_monitor if necessary)?
	 */
	private boolean hasLoaded = false;
	protected void goSitemapThenPage(IFile sitemap, String pageText) throws Exception {
		// sleep a little bit first, so ajax calls can continue
		if (hasLoaded) {
			if (getElementById("ajax_monitor") == null) {
				Thread.sleep(2000);	// sleep for way too long, since we don't know when it will finish
			} else {
				int cycles = 0;
				while (cycles < 500) {		// max 15 seconds
					try {
						IElement monitor = getElementById("ajax_monitor");
						String text = monitor.getTextContent();
						if (text != null && new Integer(text) == 0)		// all ajax calls have finished
							break;		// completed; we can carry on the test case
						
						if (text.length() > 6 && text.substring(0, 6).equals("failed"))
							throw new Exception("Ajax loading failed: " + monitor.getTextContent());
					
						// carry on sleeping
						Thread.sleep(30);
					} catch (AssertionFailedError e) {
						// the monitor was not found
						Thread.sleep(300);
					}
					cycles++;
				}
				
			}
		}
		
		beginAt(sitemap.getProjectRelativePath().toString());
		hasLoaded = true;		// we have now loaded a page
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		assertTitleMatch(pageText);
		
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
			goSitemapThenPage(sitemap, "page1");
			
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
			goSitemapThenPage(sitemap, "page2");
	
			// check text1 field has changed
			String label_text1 = getLabelIDForText("text1");
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text1);
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text1, testingText);
		}
		
		{
			// go to page3
			goSitemapThenPage(sitemap, "page3");
			
			// check text1 field has changed
			String label_text1 = getLabelIDForText("text1");
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text1);
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text1, testingText);
		}
			
		{
			// go to page 2
			goSitemapThenPage(sitemap, "page2");
			
			// change field
			String label_text3 = getLabelIDForText("text3");
			
			assertLabelPresent(label_text3);
			setLabeledFormElementField(label_text3, testingText2);
		}

		{
			// go to page 3
			goSitemapThenPage(sitemap, "page3");
			
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
			goSitemapThenPage(sitemap, "page4");
			
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
			goSitemapThenPage(sitemap, "page5");

			String label_text5 = getLabelIDForText("text5");

			assertLabelPresent(label_text5);
			assertLabeledFieldEquals(label_text5, testingText2);
			
			// lets change it, why not
			setLabeledFormElementField(label_text5, testingText3);
		}
		
		{
			// it should change something on page 2
			// if this fails, it is because it cannot chain text5-->newText-->text3
			goSitemapThenPage(sitemap, "page2");

			String label_text3 = getLabelIDForText("text3");

			// check fields have synced
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text3, testingText3);
		}
		} catch (Error e) {
			// print out the source code
			System.out.println(getTester().getPageSource());
			// throw out any response text too
			throw new RuntimeException("Response = '" + getElementById("response").getTextContent() + "' Debug='" + getElementById("debug").getTextContent() + "'", e);
		}
		
	}
	
	
	/**
	 * We need some way of working out the label ID that contains 
	 * a particular string.
	 * 
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		IElement element = getElementByXPath("//*[contains(text(),'" + text + "')]");
		return element.getAttribute("id");
	}

}
