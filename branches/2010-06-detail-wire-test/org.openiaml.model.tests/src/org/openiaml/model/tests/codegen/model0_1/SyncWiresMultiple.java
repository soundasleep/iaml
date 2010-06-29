/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
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

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SyncWiresMultiple.class);
	}
	
	public void testCase() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("page1");
		
		// fill in name and email
		String testName = "name " + new Date().toString();
		String testEmail = "email " + new Random().nextInt(32768);
		String testPassword = "password " + new Date().toString();
		{
			String nameId = getLabelIDForText("name");
			setLabeledFormElementField(nameId, testName);
			String emailId = getLabelIDForText("email");
			setLabeledFormElementField(emailId, testEmail);
		}
		
		// go to page2
		gotoSitemapThenPage(sitemap, "page2");
		
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
		
		// in 0.5.1, DomainSchemas can no longer be the target
		// of SyncWires; thus the Synchronisation will not be applied
		// to a DomainSchema, and not applied to the target form
		
		// if we now go to the last signup user page, it should appear there
		gotoSitemapThenPage(sitemap, "last signup user");
		
		// should have changed
		{
			String nameId = getLabelIDForText("name");
			assertLabeledFieldEquals(nameId, "");
		}
		
	}

}
