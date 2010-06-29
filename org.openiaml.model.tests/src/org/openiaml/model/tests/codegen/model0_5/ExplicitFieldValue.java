/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * 		
 * @implementation VisibleThing
 * 		If a {@model Property} named 'fieldValue' is not initially contained within a
 * 		{@model VisibleThing}, the attribute {@model VisibleThing#fieldValue fieldValue} will be 
 * 		used instead.
 */
public class ExplicitFieldValue extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ExplicitFieldValue.class);
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
	 * The text field 'target' exists, and is set to 'initial value'.
	 * 
	 * @throws Exception
	 */
	public void testHomeInitial() throws Exception {
	
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");	// empty
		}
		
	}
	
	/**
	 * 'change target' is told to set 'target'.
	 * 
	 * @throws Exception
	 */
	public void testHomeChange() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");	// empty
		}
		{
			String target = getLabelIDForText("change target");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "new value");
		}
		
		// it should change 'target'
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "new value");	// empty
		}
		
	}

}
