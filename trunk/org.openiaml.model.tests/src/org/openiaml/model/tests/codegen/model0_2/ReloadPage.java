/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * A really simple test case, checking that text field values
 * persist over page reloads. 
 * 
 * @author jmwright
 *
 */
public class ReloadPage extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ReloadPage.class);
	}
	
	public void testServerReload() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		String testString = "value " + new Date().toString();
		{
			String targetId = getLabelIDForText("target");
			assertNotNull(targetId);
			assertLabeledFieldNotEquals(targetId, testString);
			setLabeledFormElementField(targetId, testString);
		}
		
		// reload
		waitForAjax();
		gotoSitemapThenPage(sitemap, "container");
		
		// the value should remain there
		{
			String targetId = getLabelIDForText("target");
			assertNotNull(targetId);
			assertLabeledFieldEquals(targetId, testString);
		}

		// reload
		waitForAjax();
		gotoSitemapThenPage(sitemap, "container");
		
		// the value should remain there
		{
			String targetId = getLabelIDForText("target");
			assertNotNull(targetId);
			assertLabeledFieldEquals(targetId, testString);
		}

	}

}
