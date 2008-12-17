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
 * Testing a big complicated sync wires example.
 * 
 * page1 <--> page2 <--> user form <--> signup form <--> last signup user
 * 
 * @author jmwright
 *
 */
public class SyncWiresMultiple extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncWiresMultiple.iaml");
	}
	
	public void testCase() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "page1");
		
		// fill in name and email
		String testName = "name " + new Date().toString();
		String testEmail = "email " + new Random().nextInt(32768);
		String testPassword = "password " + new Date().toString();
		{
			String nameId = getLabelIDForText("name");
			assertNotNull(nameId);
			setLabeledFormElementField(nameId, testName);
			String emailId = getLabelIDForText("email");
			assertNotNull(emailId);
			setLabeledFormElementField(emailId, testEmail);
		}
		
		// go to page2
		beginAtSitemapThenPage(sitemap, "page2");
		
		// should have changed
		{
			String nameId = getLabelIDForText("name");
			assertLabeledFieldEquals(nameId, testName);
			String emailId = getLabelIDForText("email");
			assertLabeledFieldEquals(emailId, testEmail);
		}
		
		// set password
		{
			String passwordId = getLabelIDForText("password");
			setLabeledFormElementField(passwordId, testPassword);
		}
		
		// if we now go to the last signup user page, it should appear there
		beginAtSitemapThenPage(sitemap, "last signup user");
		
		// should have changed
		{
			String nameId = getLabelIDForText("name");
			assertLabeledFieldEquals(nameId, testName);
		}
		
	}

}
