/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * A SetWire operating on a client.
 * 
 * @implementation SetWire 
 * 		If a {@model InputTextField} is connected to another with 
 * 		a {@model SetWire} on the same page, changes in the source
 * 		InputTextField will update the target InputTextField. 
 * 		 
 * @author jmwright
 *
 */
public class SetWireClient extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireClient.class);
	}
	
	/**
	 * Test that setting 'source' will set 'target'.
	 */
	public void testSetSourceTarget() throws Exception {
		beginAtSitemapThenPage("Home");

		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, "");
		
		// set source
		String test1 = "test1 " + new Date();
		setLabeledFormElementField(source, test1);
		assertLabeledFieldEquals(source, test1);
		assertLabeledFieldEquals(target, test1);
		
		// set target; should not change
		String test2 = "test2 " + new Date();
		assertNotEqual(test1, test2);
		setLabeledFormElementField(target, test2);
		assertLabeledFieldEquals(source, test1);
		assertLabeledFieldEquals(target, test2);
		
		// set source again
		String test3 = "test3 " + new Date();
		assertNotEqual(test1, test3);
		setLabeledFormElementField(source, test3);
		assertLabeledFieldEquals(source, test3);
		assertLabeledFieldEquals(target, test3);
	}
		
}
