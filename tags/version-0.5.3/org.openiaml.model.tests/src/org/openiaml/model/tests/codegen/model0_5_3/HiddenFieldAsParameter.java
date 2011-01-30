/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_3;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * @example Hidden
 * 		Using a {@model Hidden} element as a {@model ParameterEdge} for 
 * 		{@model ActionEdge executing} an {@model Operation}.
 * 
 */
public class HiddenFieldAsParameter extends CodegenTestCase {

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
	
	/**
	 * The target page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testTargetPage() throws Exception {
		beginAtSitemapThenPage("Target Frame");
		assertNoProblem();
	}
	
	/**
	 * Clicking the button will set the target field to "", since
	 * no parameter has been set.
	 * 
	 * @throws Exception
	 */
	public void testSetEmptyClick() throws Exception {
		beginAtSitemapThenPage("Target Frame");
		
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
		}

		// click the button
		clickButtonWithText("update target");
		
		// still empty
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
		}
		
		assertNoProblem();

	}
	
	/**
	 * Set the target field, but then reset it to empty.
	 * 
	 * @throws Exception
	 */
	public void testSetTargetThenEmptyClick() throws Exception {
		beginAtSitemapThenPage("Target Frame");
		
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "to override");
			assertLabeledFieldEquals(target, "to override");
		}

		// click the button
		clickButtonWithText("update target");
		
		// still empty
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");		// reset to empty
		}
		
		assertNoProblem();

	}
	
	public void testInput() throws Exception {
		beginAtSitemapThenPage("Target Frame");
		
		String input = new Date().toString();
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, input);
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
		}

		// click the button
		clickButtonWithText("update target");
		assertNoProblem();

		// all changed
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, input);
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, input);
			setLabeledFormElementField(target, "should be overridden");
		}
		
		// click the button again
		clickButtonWithText("update target");
		assertNoProblem();

		// all changed
		{
			String target = getLabelIDForText("parameter");
			assertLabeledFieldEquals(target, input);
		}
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, input);
		}
		
	}
	
}
