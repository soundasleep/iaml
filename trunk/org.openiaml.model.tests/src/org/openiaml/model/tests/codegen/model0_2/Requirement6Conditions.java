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
 * @author jmwright
 *
 */
public class Requirement6Conditions extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(Requirement6Conditions.class, ROOT + "../examples/requirements/6-conditions.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("container");
		
		String test1 = "for field1 " + new Date().toString();		
		String test2 = "for field2 " + new Random().nextDouble();
		{
			// there should be an element called value
			String field1 = getLabelIDForText("field1");
			assertLabeledFieldEquals(field1, "");
			
			// set the field
			setLabeledFormElementField(field1, test1);
			
			// it should consult the condition and not change field2
			String field2 = getLabelIDForText("field2");
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
