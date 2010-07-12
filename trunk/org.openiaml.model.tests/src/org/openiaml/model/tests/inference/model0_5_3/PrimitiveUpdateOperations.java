/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Allow AccessControlHandlers to specify target Logout pages
 * 
 * @author jmwright
 */
public class PrimitiveUpdateOperations extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(PrimitiveUpdateOperations.class);
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputTextField text1 = assertHasInputTextField(home, "text1");
		PrimitiveOperation init1 = assertHasPrimitiveOperation(text1, "init"); 
		PrimitiveOperation update1 = assertHasPrimitiveOperation(text1, "update"); 
		
		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeOperation init2 = assertHasCompositeOperation(text2, "init"); 
		CompositeOperation update2 = assertHasCompositeOperation(text2, "update"); 

		assertNotGenerated(text1, text2, init1, init2, update1, update2);
		
	}
	
	/**
	 * Primitive operations are not composite operations.
	 * 
	 * @throws Exception
	 */
	public void testPrimitiveOperationsAreNotComposite() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputTextField text1 = assertHasInputTextField(home, "text1");
		PrimitiveOperation init1 = assertHasPrimitiveOperation(text1, "init"); 
		PrimitiveOperation update1 = assertHasPrimitiveOperation(text1, "update"); 
		
		assertFalse(init1 instanceof CompositeOperation);
		assertFalse(update1 instanceof CompositeOperation);

	}
	
public void testContentsOfUpdateOperation() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeOperation update = assertHasCompositeOperation(text2, "update"); 
		
		// -- traverse from start node --
		StartNode start = assertHasStartNode(update);
		FinishNode finish = assertHasFinishNode(update);
		CancelNode cancel = assertHasCancelNode(update);

		DecisionNode check = assertHasDecisionNode(update, "can cast?");
		CastNode cast = assertHasCastNode(update);

		PrimitiveOperation set = assertHasPrimitiveOperation(update, "set");

		assertHasExecutionEdge(update, start, check);
		assertHasExecutionEdge(update, check, cancel, "no");
		assertHasExecutionEdge(update, check, set, "yes");
		assertHasExecutionEdge(update, set, finish);

		// data flow edges
		Parameter param = assertHasParameter(update, "setValueTo");
		assertHasDataFlowEdge(update, param, cast);
		assertHasDataFlowEdge(update, cast, check);
		assertHasDataFlowEdge(update, cast, set);

		assertEquals(1, set.getOutFlows().size());
		Property f2 = (Property) set.getOutFlows().get(0).getTo();
		assertEquals("fieldValue", f2.getName());
		
	}

	public void testContentsOfInitOperation() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeOperation init = assertHasCompositeOperation(text2, "init"); 
		
		// -- traverse from start node --
		StartNode start = assertHasStartNode(init);
		FinishNode finish = assertHasFinishNode(init);
		CancelNode cancel = assertHasCancelNode(init);
	
		DecisionNode check = assertHasDecisionNode(init, "can cast?");
		CastNode cast = assertHasCastNode(init);
	
		PrimitiveOperation set = assertHasPrimitiveOperation(init, "set");
	
		assertHasExecutionEdge(init, start, check);
		assertHasExecutionEdge(init, check, cancel, "no");
		assertHasExecutionEdge(init, check, set, "yes");
		assertHasExecutionEdge(init, set, finish);
	
		// data flow edges
		Parameter param = assertHasParameter(init, "setValueTo");
		assertHasDataFlowEdge(init, param, cast);
		assertHasDataFlowEdge(init, cast, check);
		assertHasDataFlowEdge(init, cast, set);
	
		assertEquals(1, set.getOutFlows().size());
		Property f2 = (Property) set.getOutFlows().get(0).getTo();
		assertEquals("fieldValue", f2.getName());
		
	}
	
	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}
	
}
