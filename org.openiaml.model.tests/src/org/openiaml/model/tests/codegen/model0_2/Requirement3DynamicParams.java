/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 3: dynamic value parameters
 * 
 * @model ../examples/requirements/3-dynamic_params.iaml
 * @author jmwright
 *
 */
public class Requirement3DynamicParams extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "../examples/requirements/3-dynamic_params.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "new container");
		
		String testText = "my text " + new Date().toString();
		{
			// there should be an element called value
			String valueID = getLabelIDForText("field to edit");
			assertNotNull(valueID);
			
			// set the field
			// expect a Javascript alert
			setExpectedJavaScriptAlert(testText);
			setLabeledFormElementField(valueID, testText);
			
		}

		// reload, it should still work the same
		gotoSitemapThenPage(sitemap, "new container");
		waitForAjax();
		
		String testText2 = "some more text " + new Random().nextDouble();
		{
			// there should be an element called value
			String valueID = getLabelIDForText("field to edit");
			assertNotNull(valueID);
			
			// set the field
			// expect a Javascript alert
			setExpectedJavaScriptAlert(testText2);
			setLabeledFormElementField(valueID, testText2);
			
		}

	}

}
