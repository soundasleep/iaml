/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Issue 176: A test model demonstrating all of the Operational Modelling concepts. 
 * 
 * @example StartNode,CancelNode,FinishNode,DecisionNode,SplitNode,JoinNode,OperationCallNode,Arithmetic,CastNode,TemporaryVariable,Parameter,StaticValue,DataFlowEdge,ExecutionEdge,ParameterEdge,RunAction,ConditionEdge
 *		A summary of all of the elements used in {@model CompositeOperation Operational Modelling}. 
 */
public class OperationalExample extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(OperationalExample.class);
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
	
	public void testSetNumber() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("Input");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "5");
		}

		// Result starts out empty
		{
			String target = getLabelIDForText("Result");
			assertLabeledFieldEquals(target, "");
		}

		clickButtonWithText("Run");
		
		{
			String target = getLabelIDForText("Result");
			assertLabeledFieldEquals(target, "6");
		}
	}
	
	/**
	 * Should fail since a string can't be cast to a number.
	 * 
	 * @throws Exception
	 */
	public void testSetString() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("Input");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "five");
		}

		setExpectedJavaScriptAlert("Unexpected exception: IamlJavascriptException: Input is not an integer.");
		clickButtonWithText("Run");
	}
	
}