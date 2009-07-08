/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions initialisation ("init" event)
 * 
 * @author jmwright
 *
 */
public class SessionInit extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SessionInit.class);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		String newValue = "a new value " + new Date().toString();
		{
			String field = getLabelIDForText("value");
			assertLabeledFieldEquals(field, "initialised by init");
			setLabeledFormElementField(field, newValue);
		}
		
		// reload page
		reloadPage(sitemap, "container");
		
		{
			String field = getLabelIDForText("value");
			assertLabeledFieldEquals(field, newValue);
		}
		
		// *restart* session
		restartSession(sitemap, "container");
		
		String newValue2 = "another new value " + new Date().toString();
		{
			String field = getLabelIDForText("value");
			assertLabeledFieldEquals(field, "initialised by init");
			setLabeledFormElementField(field, newValue2);
		}

		// reload page
		reloadPage(sitemap, "container");
		
		{
			String field = getLabelIDForText("value");
			assertLabeledFieldEquals(field, newValue2);
		}

	}

}
