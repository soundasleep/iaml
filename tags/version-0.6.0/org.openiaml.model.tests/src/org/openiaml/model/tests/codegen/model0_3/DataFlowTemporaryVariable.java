/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests using temporary variables within operation modelling.
 * 
 * @author jmwright
 *
 */
public class DataFlowTemporaryVariable extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(DataFlowTemporaryVariable.class);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("container");
		
		String text1 = "initial text " + new Date().toString();
		String text2 = "the second change " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, text1);
			
			// this should be set in 'destination'
			String destination = getLabelIDForText("destination");
			assertLabeledFieldEquals(destination, text1);
			
			// if we set destination
			setLabeledFormElementField(destination, text2);
			
			// it should not change the target
			assertLabeledFieldEquals(target, text1);
		}
		
	}

}
