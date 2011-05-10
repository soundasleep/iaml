/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.BuiltinOperation;
import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.operations.ActivityParameter;
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
		BuiltinOperation init1 = assertHasBuiltinOperation(text1, "init");
		BuiltinOperation update1 = assertHasBuiltinOperation(text1, "update");
		BuiltinProperty cast1 = assertHasBuiltinProperty(text1, "can cast?");
		BuiltinProperty set1 = assertHasBuiltinProperty(text1, "fieldValue is set");

		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeOperation init2 = assertHasCompositeOperation(text2, "init");
		CompositeOperation update2 = assertHasCompositeOperation(text2, "update");
		CompositeCondition cast2 = assertHasCompositeCondition(text2, "can cast?");
		CompositeCondition set2 = assertHasCompositeCondition(text2, "fieldValue is set");

		assertNotGenerated(text1, text2, init1, init2, update1, update2, cast1, cast2, set1, set2);

	}

	/**
	 * Primitive operations are not composite operations.
	 *
	 * @throws Exception
	 */
	public void testPrimitiveOperationsAreNotComposite() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField text1 = assertHasInputTextField(home, "text1");
		BuiltinOperation init1 = assertHasBuiltinOperation(text1, "init");
		BuiltinOperation update1 = assertHasBuiltinOperation(text1, "update");

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

		BuiltinOperation set = assertHasBuiltinOperation(update, "set");

		assertHasExecutionEdge(update, start, check);
		assertHasExecutionEdge(update, check, cancel, "no");
		assertHasExecutionEdge(update, check, set, "yes");
		assertHasExecutionEdge(update, set, finish);

		// data flow edges
		ActivityParameter param = assertHasActivityParameter(update, "setValueTo");
		assertHasDataFlowEdge(update, param, cast);
		assertHasDataFlowEdge(update, cast, check);
		assertHasDataFlowEdge(update, cast, set);

		assertEquals(1, set.getOutFlows().size());
		Value f2 = (Value) set.getOutFlows().get(0).getTo();
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

		BuiltinOperation set = assertHasBuiltinOperation(init, "set");

		assertHasExecutionEdge(init, start, check);
		assertHasExecutionEdge(init, check, cancel, "no");
		assertHasExecutionEdge(init, check, set, "yes");
		assertHasExecutionEdge(init, set, finish);

		// data flow edges
		ActivityParameter param = assertHasActivityParameter(init, "setValueTo");
		assertHasDataFlowEdge(init, param, cast);
		assertHasDataFlowEdge(init, cast, check);
		assertHasDataFlowEdge(init, cast, set);

		assertEquals(1, set.getOutFlows().size());
		Value f2 = (Value) set.getOutFlows().get(0).getTo();
		assertEquals("fieldValue", f2.getName());

	}

	/**
	 * Primitive conditions are not composite conditions.
	 *
	 * @throws Exception
	 */
	public void testPrimitiveConditionsAreNotComposite() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField text1 = assertHasInputTextField(home, "text1");
		BuiltinProperty cast1 = assertHasBuiltinProperty(text1, "can cast?");

		assertFalse(cast1 instanceof CompositeCondition);

	}

	public void testContentsOfCanCastCondition() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeCondition canCast = assertHasCompositeCondition(text2, "can cast?");
		Value integerValue = assertHasFieldValue(text2);

		StartNode start = assertHasStartNode(canCast);
		ActivityParameter param = assertHasActivityParameter(canCast, "value");
		// ActivityParameter is of 'any' type
		assertNull(param.getType());

		DecisionNode check = assertHasDecisionNode(canCast, "can cast?");

		CastNode cast = assertHasCastNode(canCast);
		assertHasDataFlowEdge(canCast, param, cast);	// in
		assertHasDataFlowEdge(canCast, cast, integerValue);	// out
		assertHasDataFlowEdge(canCast, cast, check);	// check

		CancelNode cancel = assertHasCancelNode(canCast);
		FinishNode finish = assertHasFinishNode(canCast);

		assertHasExecutionEdge(canCast, start, check);
		assertHasExecutionEdge(canCast, check, cancel, "no");
		assertHasExecutionEdge(canCast, check, finish, "yes");

	}

	public void testContentsOfFieldValueIsSetCondition() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		CompositeCondition cond = assertHasCompositeCondition(text2, "fieldValue is set");
		Value value = assertHasFieldValue(text2);

		StartNode start = assertHasStartNode(cond);
		FinishNode finish = assertHasFinishNode(cond);
		CancelNode cancel = assertHasCancelNode(cond);

		DecisionNode check = assertHasDecisionNode(cond, "is set?");

		assertHasExecutionEdge(cond, start, check);
		assertHasExecutionEdge(cond, check, finish);
		assertHasExecutionEdge(cond, check, cancel);

		assertHasDataFlowEdge(cond, value, check);

	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}

}
