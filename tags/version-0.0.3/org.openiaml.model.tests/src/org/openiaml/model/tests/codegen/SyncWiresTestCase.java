/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test SyncWires
 * 
 * @author jmwright
 *
 */
public class SyncWiresTestCase extends InferenceTestCase {
	
	protected void setUp() throws Exception {
		String modelFile = "models/test-sync.iaml";
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

		// why is this deprecated? o_O
		assertFormElementEquals("name1", testingText);
		System.out.println(this.getPageSource());
		
		// it should sync over automatically
		assertFormElementEquals("name2", testingText);
		
		// and with the other elements as well
		assertFormElementPresent("name3");
		assertFormElementPresent("name4");
		assertFormElementEquals("name3", testingText);	// tests element chaining
		assertFormElementEquals("name4", testingText);
		
		// edit some other fields on the same page
		setTextField("name3", testingText2);
		assertFormElementEquals("name3", testingText2);	// tests element chaining + skipping
		assertFormElementEquals("name1", testingText2);
		
		// one to four
		setTextField("name1", testingText3);
		assertFormElementEquals("name1", testingText3);
		assertFormElementEquals("name4", testingText3);
		
		// an unrelated field shouldn't change
		assertFormElementEquals("unrelated-field", "");

	}
}
