/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Random;

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
		ICreateElements handler = new EcoreInferenceHandler(resource);
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
	
	protected void goSitemapThenPage(IFile sitemap, String pageText) {
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		assertTitleMatch(pageText);
		
	}
	
	/**
	 * tests a single page that have multiple InputTextFields on them
	 * for a sync wire: when one field changes, the others
	 * should as well.
	 * 
	 */
	public void testSyncAcrossPages() {
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
		}
		
		{
			// go to page2
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
			goSitemapThenPage(sitemap, "page2");

			String label_text3 = getLabelIDForText("text3");

			// check fields have synced
			assertLabelPresent(label_text3);
			assertLabeledFieldEquals(label_text3, testingText3);
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

	/**
	 * In our code generation, we don't want to currently have to worry about what the IDs
	 * of the elements are -- only what they are called when displayed to the user
	 * (i.e. their label).
	 * 
	 * @param inputName
	 * @param value
	 */
	protected void xsetTextFieldWithLabel(String inputName, String value) {
		// currently no way to do it in JWebUnit
		setTextField(inputName, value);
	}

	/**
	 * In our code generation, we don't want to currently have to worry about what the IDs
	 * of the elements are -- only what they are called when displayed to the user
	 * (i.e. their label).

	 * @param formElementName
	 * @param expectedValue
	 */
	protected void xassertFormElementWithLabelEquals(String formElementName,
			String expectedValue) {
		// currently no way to do it in JWebUnit
		assertFormElementEquals(formElementName, expectedValue);
		
	}

	/**
	 * In our code generation, we don't want to currently have to worry about what the IDs
	 * of the elements are -- only what they are called when displayed to the user
	 * (i.e. their label).
	 * 
	 * @param formElementName
	 */
	protected void xassertFormElementWithLabelPresent(String formElementName) {
		// currently no way to do it in JWebUnit
		assertFormElementPresent(formElementName);
		
	}
}
