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
 * Test SyncWires when they connect an InputTextField to an ApplicationElementProperty
 * (stored on the same Page, thus permanent)
 * 
 * @author jmwright
 *
 */
public class SyncFieldApplicationElementProperty extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncFieldApplicationElementProperty.iaml");
	}
	
	public void testSyncOnPage() {
		String testingText = new Date().toString();
		String testingText2 = this.toString();
		String testingText3 = "random" + new Random().nextInt(32768);
		
		// sanity
		assertNotSame(testingText, testingText2);
		assertNotSame(testingText2, testingText3);
		
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		{
			beginAt(sitemap.getProjectRelativePath().toString());
			assertTitleMatch("sitemap");
			
			assertLinkPresentWithText("container");
			clickLinkWithText("container");
			assertTitleMatch("container");
			
			// fill in a field
			String fieldId = getLabelIDForText("field1");
			setLabeledFormElementField(fieldId, testingText);
	
			assertLabeledFieldEquals(fieldId, testingText);
		}
		
		// reload the page and make sure it's still there
		{
			beginAt(sitemap.getProjectRelativePath().toString());
			assertTitleMatch("sitemap");
			
			assertLinkPresentWithText("container");
			clickLinkWithText("container");
			assertTitleMatch("container");
			
			// check the field
			String fieldId = getLabelIDForText("field1");
			assertLabeledFieldEquals(fieldId, testingText);
		}

	}
}
