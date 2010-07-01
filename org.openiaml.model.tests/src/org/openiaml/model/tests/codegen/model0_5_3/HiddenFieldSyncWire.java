/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_3;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * @example SyncWire,Hidden
 * 		Connecting a {@model Hidden} element with a {@model SyncWire}.
 * 
 */
public class HiddenFieldSyncWire extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	public void testInput() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// both fields are initially empty
		{
			String target = getLabelIDForText("input");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("output");
			assertLabeledFieldEquals(target, "");
		}
		
		// change 'input'
		String input = new Date().toString();
		{
			String target = getLabelIDForText("input");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, input);
		}
		
		// changes output
		{
			String target = getLabelIDForText("output");
			assertLabeledFieldEquals(target, input);
		}
		assertNoProblem();
		
		// there is no label with the text anywhere
		assertLabelTextNotPresent(input);
				
		// change 'output'
		String output = input.substring(5).toLowerCase() + " test";
		assertNotEqual(input, output);
		{
			String target = getLabelIDForText("output");
			assertLabeledFieldEquals(target, input);
			setLabeledFormElementField(target, output);
		}
		
		// changes input
		{
			String target = getLabelIDForText("input");
			assertLabeledFieldEquals(target, output);
		}

		assertNoProblem();
		
	}
	
}
