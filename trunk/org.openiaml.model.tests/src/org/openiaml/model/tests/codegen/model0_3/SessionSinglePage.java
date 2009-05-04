/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions: an element on a single page.
 * 
 * @author jmwright
 *
 */
public class SessionSinglePage extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/model0_3/SessionSinglePage.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "outside");
		
		String outside = "outside " + new Date().toString();
		{
			String field1 = getLabelIDForText("field1");
			assertLabeledFieldEquals(field1, "");
			setLabeledFormElementField(field1, outside);
		}
		
		// go to session
		gotoSitemapThenPage(sitemap, "inside");
		
		String inside = "inside " + new Date().toString();
		{
			String field2 = getLabelIDForText("field2");
			assertLabeledFieldEquals(field2, "");
			setLabeledFormElementField(field2, inside);
		}
		
		// reload page
		reloadPage(sitemap, "outside");
		{
			// should be the same
			String field1 = getLabelIDForText("field1");
			assertLabeledFieldEquals(field1, outside);
		}

		// re-enter session
		reloadPage(sitemap, "inside");
		{
			// should be the same
			String field2 = getLabelIDForText("field2");
			assertLabeledFieldEquals(field2, inside);
		}

		// *restart* entire session
		restartSession(sitemap, "outside");
		{
			// should be the same
			String field1 = getLabelIDForText("field1");
			assertLabeledFieldEquals(field1, outside);
		}

		// re-enter session
		reloadPage(sitemap, "inside");
		{
			// should have been lost
			String field2 = getLabelIDForText("field2");
			assertLabeledFieldEquals(field2, "");
		}	
		
	}

}
