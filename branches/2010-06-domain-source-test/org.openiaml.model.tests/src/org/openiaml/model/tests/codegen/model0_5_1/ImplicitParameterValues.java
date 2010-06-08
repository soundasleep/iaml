/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * @example ParameterEdge
 * 		A {@model Label} or {@model InputTextField} can be used as a {@model ParameterEdgeSource};
 * 		the value provided is loaded at runtime from the {@model VisibleThing#properties fieldValue}
 * 		of the {@model VisibleThing}. 
 */
public class ImplicitParameterValues extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ImplicitParameterValues.class);
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
	
	/**
	 * Initially, all of the fields are empty.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("text field");
			assertLabeledFieldEquals(target, "");
		}
		
		assertLabelTextExactlyPresent("");

		{
			String target = getLabelIDForText("target field");
			assertLabeledFieldEquals(target, "");
		}

	}
	
	/**
	 * We update the text fields in order.
	 * 
	 * @throws Exception
	 */
	public void testUpdate() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("text field");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new value");
		}
		
		// not present yet
		assertLabelTextNotPresent("new value");
		
		// click the button
		clickButtonWithText("update label");
		assertNoProblem();
		
		// it is present now
		assertLabelTextExactlyPresent("new value");
		
		// check the 'target field' hasn't changed
		{
			String target = getLabelIDForText("target field");
			assertLabeledFieldEquals(target, "");
		}

		// until we click the next button
		clickButtonWithText("update target");
		
		// it has now changed too
		{
			String target = getLabelIDForText("target field");
			assertLabeledFieldEquals(target, "new value");
		}

	}
	
}
