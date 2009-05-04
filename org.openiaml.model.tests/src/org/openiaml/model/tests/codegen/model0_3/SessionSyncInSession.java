/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions: sync wires within a session
 * 
 * @author jmwright
 *
 */
public class SessionSyncInSession extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(SessionSyncInSession.class);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// page1, page2 and page3 are in the same session
		
		// go to page 1
		beginAtSitemapThenPage(sitemap, "page1");
		
		String text1 = "initial value " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, text1);
		}
		
		// page 2
		gotoSitemapThenPage(sitemap, "page2");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text1);
		}
		
		// page 3
		gotoSitemapThenPage(sitemap, "page3");
		
		String text2 = "a new value " + new Date().toString();
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text1);
			// change it
			setLabeledFormElementField(target, text2);
		}

		// page 1
		gotoSitemapThenPage(sitemap, "page1");
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text2);
		}
		
		// *restart* entire session
		beginAtSitemapThenPage(sitemap, "page1");
		{
			// should have been lost
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
		}
		
		// page 3
		gotoSitemapThenPage(sitemap, "page3");
		{
			// should still be empty
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
		}
		
		// *restart* entire session
		restartSession(sitemap, "page3");
		String text3 = "the final change " + new Date().toString();
		{
			// should have been lost
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, text3);
		}

		// page 1
		gotoSitemapThenPage(sitemap, "page1");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text3);
		}
		
	}

}
