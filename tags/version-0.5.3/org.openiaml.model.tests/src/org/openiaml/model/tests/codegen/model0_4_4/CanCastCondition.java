/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * When a DomainObjectInstance is connected to a Form by a
 * SetWire, Labels are created, allowing the object to be <em>viewed</em>
 * but not saved.
 * 
 * @author jmwright
 * @example CastNode
 * 		Using a {@model CastNode} to cast a {@model Property} from 
 * 		{@model DataFlowEdgesSource one type} to {@model DataFlowEdgeDestination another type}.
 * @example CastNode
 *		A {@model CastNode} can have an additional outgoing {@model DataFlowEdge} to a 
 *		{@model DecisionOperation} named 'failed?', which will return true if
 *		the {@model DataFlowEdgesSource data source} could not be cast successfully.
 * @implementation DecisionOperation
 * 		A {@model DecisionOperation} named 'failed?' will return true if
 * 		the incoming {@model CastNode} (through a {@model DataFlowEdge}) could
 * 		not be cast correctly.
 * @implementation DecisionCondition
 * 		A {@model DecisionCondition} named 'failed?' will return true if
 * 		the incoming {@model CastNode} (through a {@model DataFlowEdge}) could
 * 		not be cast correctly.
 * @example CastNode,Property,InputTextField
 * 		Manually using a {@model CastNode} to cast a {@model InputTextField String field}
 * 		to an Integer field.
 */
public class CanCastCondition extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(CanCastCondition.class);
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
	 * Two text fields exist, and a button. 
	 * 
	 * @throws Exception
	 */
	public void testHomeInitial() throws Exception {
	
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
		}
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "");	// empty
		}
		
		// and a button
		assertButtonPresentWithText("Run");
		
	}
	
	/**
	 * If we set the String field to '123', nothing will happen.
	 * 
	 * @throws Exception
	 */
	public void testSetStringToNumber() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "123");
		}
		
		// target should still be empty
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "");	// empty
		}
		
		// no problems
		assertNoProblem();
		
	}
	
	/**
	 * If we set the String field to '123', and click
	 * 'Run', the target will be set to 123.
	 * 
	 * @throws Exception
	 */
	public void testSetStringToNumberRun() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "123");
		}
		
		clickButtonWithText("Run");
		
		// target should now have changed
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "123");	// empty
		}
		
		// no problems
		assertNoProblem();
		
	}
	
	/**
	 * If we set the String field to 'abc', and click
	 * 'Run', the target will not change.
	 * 
	 * @throws Exception
	 */
	public void testSetStringToStringRun() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "abc");
		}
		
		clickButtonWithText("Run");
		
		// target should not have changed
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "");	// empty
		}
		
		// no problems
		assertNoProblem();
		
	}
	
	/**
	 * If we set the String field to '123', and click
	 * 'Run', the target will be set to 123. If we then change
	 * String to 'abc', the target will remain 123.
	 * 
	 * @throws Exception
	 */
	public void testSetStringToNumberThenStringRun() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "123");
		}
		
		clickButtonWithText("Run");
		
		// target should now have changed
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "123");	// empty
		}
		
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "123");
			setLabeledFormElementField(target, "abc");
		}
		
		clickButtonWithText("Run");
		
		// target should remain the same
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "123");	// empty
		}
		
		// no problems
		assertNoProblem();
		
	}
	
	/**
	 * Test multiple calls in succession.
	 * 
	 * @throws Exception
	 */
	public void testSetStringMultipleTimes() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "123");
		}
		
		clickButtonWithText("Run");
		
		// target should now have changed
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "123");	// empty
		}
		
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "123");
			setLabeledFormElementField(target, "abc");
		}
		
		clickButtonWithText("Run");
		
		// target should remain the same
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "123");	// empty
		}
		
		// now do it again
		
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "abc");	// empty
			setLabeledFormElementField(target, "456");
		}
		
		clickButtonWithText("Run");
		
		// target should now have changed
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "456");	// empty
		}
		
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "456");
			setLabeledFormElementField(target, "def");
		}
		
		clickButtonWithText("Run");
		
		// target should remain the same
		{
			String target = getLabelIDForText("Target");
			assertLabeledFieldEquals(target, "456");	// empty
		}
		
		// no problems
		assertNoProblem();
		
	}
	
}
