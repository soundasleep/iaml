/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * A simple ConditionWire test case, which is a condition that is always false.
 * Except in this case, the condition isn't directly in the containing page. 
 * 
 * @author jmwright
 *
 */
public class ConditionWireFalseServer extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/ConditionWireFalseServer.iaml");
	}
	
	public void testFalseCondition() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page 1
		beginAtSitemapThenPage(sitemap, "page1");
		// print out the page
		{
			IFile page1 = getProject().getFile("page1.html");
			InputStream source = new ByteArrayInputStream(getPageSource().getBytes("UTF-8")); 
			page1.create(source, true, monitor);
		}
		
		String testString = "value " + new Date().toString();
		{
			String fieldId = getLabelIDForText("field");
			assertNotNull(fieldId);
			assertLabeledFieldNotEquals(fieldId, testString);
			setLabeledFormElementField(fieldId, testString);
		}
		
		// page 2; the SyncWire should not continue since it has a false condition
		waitForAjax();
		gotoSitemapThenPage(sitemap, "page2");

		// print out the page
		{
			IFile page2 = getProject().getFile("page2.html");
			InputStream source = new ByteArrayInputStream(getPageSource().getBytes("UTF-8")); 
			page2.create(source, true, monitor);
		}
		
		{
			String fieldId = getLabelIDForText("field");
			assertNotNull(fieldId);
			assertLabeledFieldNotEquals(fieldId, testString);
			assertLabeledFieldEquals(fieldId, "");		// it should actually be empty
		}
				
	}

}
