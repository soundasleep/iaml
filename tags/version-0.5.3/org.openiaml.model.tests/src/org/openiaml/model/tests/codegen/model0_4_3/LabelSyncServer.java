/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Demonstrates using SyncWires connected to Labels across multiple pages.
 * 
 * @author jmwright
 */
public class LabelSyncServer extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelSyncServer.class);
	}
	
	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	public void testSync() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();
	
		// the value to change to
		String value = "hello, world!";
		
		// a text field to change
		{
			String target = getLabelIDForText("Source Text Field");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, value);
		}
		
		// go to Page 2
		gotoSitemapThenPage(sitemap, "Page 2");
		assertNoProblem();
		
		// the value should be on here
		assertLabelTextPresent(value);
		assertLabelTextNotPresent("initial");
		
		// go to Page 3
		gotoSitemapThenPage(sitemap, "Page 3");
		assertNoProblem();
		
		String value2 = "goodbye, world!";
		{
			// the text field has been changed
			String target = getLabelIDForText("Final Text Field");
			assertLabeledFieldEquals(target, value);
			
			// if we change Page 3.text...
			setLabeledFormElementField(target, value2);
		}
		
		// and go to Page 1...
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
		// it will have changed
		{
			String target = getLabelIDForText("Source Text Field");
			assertLabeledFieldEquals(target, value2);
		}
		
	}

}
