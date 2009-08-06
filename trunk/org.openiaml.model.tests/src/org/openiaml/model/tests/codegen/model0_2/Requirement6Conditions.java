/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;
import java.util.Random;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 6: simple conditions
 * 
 * @model ../examples/requirements/4-dynamic_sources.iaml
 * @author jmwright
 *
 */
public class Requirement6Conditions extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ROOT + "../examples/requirements/6-conditions.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("container");
		
		String test1 = "for field1 " + new Date().toString();		
		String test2 = "for field2 " + new Random().nextDouble();
		{
			// there should be an element called value
			String field1 = getLabelIDForText("field1");
			assertNotNull(field1);
			assertLabeledFieldEquals(field1, "");
			
			// set the field
			setLabeledFormElementField(field1, test1);
			
			// it should consult the condition and not change field2
			String field2 = getLabelIDForText("field2");
			assertNotNull(field2);
			assertLabeledFieldNotEquals(field2, test1);
			assertLabeledFieldEquals(field2, "");	// initially empty
			
			// set another field
			assertLabeledFieldEquals(field2, "");
			setLabeledFormElementField(field2, test2);
			
			// they should remain set
			assertLabeledFieldEquals(field1, test1);
			assertLabeledFieldEquals(field2, test2);
		}
		
	}

}
