/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * A simple ConditionWire test case, which is a condition that is always false. 
 * 
 * @author jmwright
 *
 */
public class ConditionWireFalse extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ConditionWireFalse.class);
	}
	
	public void testFalseCondition() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("container");
		
		String testString = "value " + new Date().toString();
		{
			String sourceId = getLabelIDForText("source");
			assertLabeledFieldNotEquals(sourceId, testString);
			setLabeledFormElementField(sourceId, testString);
			
			// when we set the field, the SyncWire on the page
			// should stop the Sync'ing from happening.
			
			String targetId = getLabelIDForText("target");
			assertLabeledFieldNotEquals(targetId, testString);
		}
		
		// and also backwards
		String testString2 = "a second value " + new Date().toString();
		{
			// TODO for some reason, this call sometimes throws a NullPointerException. 
			// but it's erratic and is thrown within the Apache XML project.
			// however, generally running the test case again will let it pass.
			// the page source between each page does not change either.
			// plus, it is retrieved successfully above.
			String targetId = getLabelIDForText("target");
			assertLabeledFieldNotEquals(targetId, testString2);
			setLabeledFormElementField(targetId, testString2);
			
			// when we set the field, the SyncWire on the page
			// should stop the Sync'ing from happening.
			
			String sourceId = getLabelIDForText("source");
			assertLabeledFieldNotEquals(sourceId, testString2);
			// it should still be the first text
			assertLabeledFieldEquals(sourceId, testString);
		}
		
	}

}
