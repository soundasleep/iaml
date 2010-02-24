/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 2: static value parameters
 * 
 * @model ../examples/requirements/2-static_params.iaml
 * @author jmwright
 *
 */
public class Requirement2StaticParams extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(Requirement2StaticParams.class, ROOT + "../examples/requirements/2-static_params.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("new");
		
		{
			// there should be an element called value
			String valueID = getLabelIDForText("value");
			assertNotNull(valueID);
			
			// it should be set to a static value as a parameter
			// value.onAccess -> setValue(static_value)
			assertLabeledFieldEquals(valueID, "value goes here");
		}

		// reload, it should still be there
		gotoSitemapThenPage(sitemap, "new");
		waitForAjax();
		
		{
			// there should be an element called value
			String valueID = getLabelIDForText("value");
			assertNotNull(valueID);
			
			// it should be set to a static value as a parameter
			// value.onAccess -> setValue(static_value)
			assertLabeledFieldEquals(valueID, "value goes here");
		}
		
	}

}
