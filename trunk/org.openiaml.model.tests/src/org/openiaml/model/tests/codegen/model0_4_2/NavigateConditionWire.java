/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Check that NavigateWires can be controlled by incoming ConditionEdges.
 * 
 * @author jmwright
 * @example ConditionEdge,NavigateWire
 * 		Using a {@model ConditionEdge} to control redirection access on a {@model NavigateWire}.
 * @implementation NavigateWire
 * 		If a {@model NavigateWire} is restricted by an incoming
 * 		{@model ConditionEdge}, the redirection will be prevented if the
 * 		source {@model Condition} fails. 
 */
public class NavigateConditionWire extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NavigateConditionWire.class);
	}
	
	/**
	 * The home page is empty.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	public void testInitial() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("value");
			assertLabeledFieldEquals(target, "");	// empty
		}
		
		// initially 'value' is undefined, so we cannot navigate
		assertButtonPresentWithText("Button");
		clickButtonWithText("Button");
		
		assertTitleEquals("Home");
		assertNoProblem();
		
	}
	
	
	public void testValue1() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("value");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "1");	// set to '1'
		}
		
		// value has been defined, so we should get to Navigate
		assertButtonPresentWithText("Button");
		clickButtonWithText("Button");
		
		assertTitleEquals("Destination");
		assertNoProblem();
		
	}
	
	public void testValue2() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("value");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "2");	// set to '1'
		}
		
		// value has been defined but it is not '1', so we cannot move
		assertButtonPresentWithText("Button");
		clickButtonWithText("Button");
		
		assertTitleEquals("Home");
		assertNoProblem();
		
	}
	
	
}
