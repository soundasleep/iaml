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
	
	/**
	 * A possible Java skeleton for the implementation of 
	 * the composite operation in the given example model.
	 * 
	 * @param input
	 */
	public void compositeOperation(String input) {
		
		try {
			Integer.valueOf(input);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Input is not an integer.");
		}
		
		final IntegerWrapper temp = new IntegerWrapper(Integer.valueOf(input));
		
		BlockingRunnable t1 = new BlockingRunnable() {
			@Override
			public void runActual() {
				// empty
			}			
		};
		BlockingRunnable t2 = new BlockingRunnable() {
			@Override
			public void runActual() {
				temp.setValue( temp.getValue() + 1 );
			}
		};
		
		new Thread(t1).start();
		new Thread(t2).start();
		while (!(t1.isFinished() && t2.isFinished())) {
			Thread.yield();
		}
		
		if (shouldUpdateResult()) {
			getLabel("Target").update(temp.getValue());
			return;
		} else {
			return;
		}
		
	}
	
	/*
	 * Various methods to allow the Java example to compile.
	 */
	
	private interface Label {
		public void update(int value);
	}
	
	private Label getLabel(String string) {
		return null;
	}

	public boolean shouldUpdateResult() {
		return true;
	}
	
	private static class IntegerWrapper {
		private int value;
		
		public IntegerWrapper(int i) {
			this.value = i;
		}
		
		public int getValue() {
			return value;	
		}
		
		public void setValue(int value) {
			this.value = value;
		}
	}
	
	private abstract static class BlockingRunnable implements Runnable {
		private boolean finished = false;
		
		public boolean isFinished() {
			return finished;
		}
		
		public void run() {
			runActual();
			finished = true;
		}
		
		public abstract void runActual();
	}
	
}