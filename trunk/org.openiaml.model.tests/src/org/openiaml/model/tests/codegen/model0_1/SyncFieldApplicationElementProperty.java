/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test SyncWires when they connect an InputTextField to an ApplicationElementProperty
 * (stored on the same Page, thus permanent)
 * 
 * @author jmwright
 *
 */
public class SyncFieldApplicationElementProperty extends CodegenTestCase {

	/**
	 * Allows this class to be extended for test methods, yet allow
	 * each super class to run outside the cache. 
	 * 
	 * Classes extending this test case should also re-implement this
	 * method to return a new class.
	 * 
	 * @return
	 */
	protected Class<?> getRuntimeClass() {
		return SyncFieldApplicationElementProperty.class;
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getRuntimeClass(), getModelFileForClass(SyncFieldApplicationElementProperty.class));
	}
	
	public void testSyncOnPage() throws Exception {
		String testingText = new Date().toString();
		String testingText2 = this.toString();
		String testingText3 = "random" + new Random().nextInt(32768);
		
		// sanity
		assertNotSame(testingText, testingText2);
		assertNotSame(testingText2, testingText3);
		
		IFile sitemap = beginAtSitemapThenPage("container");
		assertNoProblem();
		
		{
			// fill in a field
			String fieldId = getLabelIDForText("field1");
			setLabeledFormElementField(fieldId, testingText);
	
			assertLabeledFieldEquals(fieldId, testingText);
		}
		
		// reload the page and make sure it's still there
		{
			gotoSitemapThenPage(sitemap, "container");
			
			// check the field
			String fieldId = getLabelIDForText("field1");
			assertLabeledFieldEquals(fieldId, testingText);
		}

	}
}
