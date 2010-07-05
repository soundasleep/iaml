/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 3: dynamic value parameters
 * 
 * @author jmwright
 *
 */
public class Requirement3DynamicParams extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(Requirement3DynamicParams.class, ROOT + "../examples/requirements/3-dynamic_params.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("new container");
		
		String testText = "my text " + new Date().toString();
		{
			// there should be an element called value
			String valueID = getLabelIDForText("field to edit");
			
			// set the field
			// expect a Javascript alert
			setExpectedJavaScriptAlert(testText);
			setLabeledFormElementField(valueID, testText);
			
		}

		// reload, it should still work the same
		gotoSitemapThenPage(sitemap, "new container");

		String testText2 = "some more text " + new Random().nextDouble();
		{
			// there should be an element called value
			String valueID = getLabelIDForText("field to edit");
			
			// set the field
			// expect a Javascript alert
			setExpectedJavaScriptAlert(testText2);
			setLabeledFormElementField(valueID, testText2);
			
		}

	}

}
