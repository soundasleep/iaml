/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.BuiltinOperation;
import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.operations.ActivityFunction;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.ActivityParameter;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.SetNode;
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
		ActivityOperation init2 = assertHasActivityOperation(text2, "init");
		ActivityOperation update2 = assertHasActivityOperation(text2, "update");
		ActivityFunction cast2 = assertHasActivityFunction(text2, "can cast?");
		ActivityFunction set2 = assertHasActivityFunction(text2, "fieldValue is set");

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

		assertFalse(init1 instanceof ActivityOperation);
		assertFalse(update1 instanceof ActivityOperation);

	}

	public void testContentsOfUpdateOperation() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		ActivityOperation update = assertHasActivityOperation(text2, "update");

		// -- traverse from start node --
		StartNode start = assertHasStartNode(update);
		FinishNode finish = assertHasFinishNode(update);
		CancelNode cancel = assertHasCancelNode(update);

		DecisionNode check = assertHasDecisionNode(update, "can cast?");
		CastNode cast = assertHasCastNode(update);

		SetNode set = assertHasSetNode(update);

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
		ExternalValue ev_f2 = (ExternalValue) set.getOutFlows().get(0).getTo();
		Value f2 = ev_f2.getExternalValueEdges().getValue();
		assertEquals("fieldValue", f2.getName());

	}

	public void testContentsOfInitOperation() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		ActivityOperation init = assertHasActivityOperation(text2, "init");

		// -- traverse from start node --
		StartNode start = assertHasStartNode(init);
		FinishNode finish = assertHasFinishNode(init);
		CancelNode cancel = assertHasCancelNode(init);

		DecisionNode check = assertHasDecisionNode(init, "can cast?");
		CastNode cast = assertHasCastNode(init);

		SetNode set = assertHasSetNode(init);

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
		ExternalValue ev_f2 = (ExternalValue) set.getOutFlows().get(0).getTo();
		Value f2 = ev_f2.getExternalValueEdges().getValue();
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

		assertFalse(cast1 instanceof ActivityFunction);

	}

	public void testContentsOfCanCastCondition() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		ActivityFunction canCast = assertHasActivityFunction(text2, "can cast?");
		Value integerValue = assertHasFieldValue(text2);

		StartNode start = assertHasStartNode(canCast);
		ActivityParameter param = assertHasActivityParameter(canCast, "value");
		// ActivityParameter is of 'any' type
		assertNull(param.getType());

		DecisionNode check = assertHasDecisionNode(canCast, "can cast?");

		CastNode cast = assertHasCastNode(canCast);

		assertHasDataFlowEdge(canCast, param, cast);	// in
		assertHasDataFlowEdge(canCast, cast, check);	// check

		// cast -> ExternalValue -> integerValue
		{
			assertEquals(2, cast.getOutFlows().size());
			assertEquals(1, cast.getInFlows().size());
			ExternalValue ev = (ExternalValue) cast.getOutFlows().get(0).getTo();
			assertEquals(integerValue, ev.getExternalValueEdges().getValue());
		}
		
		CancelNode cancel = assertHasCancelNode(canCast);
		FinishNode finish = assertHasFinishNode(canCast);

		assertHasExecutionEdge(canCast, start, check);
		assertHasExecutionEdge(canCast, check, cancel, "no");
		assertHasExecutionEdge(canCast, check, finish, "yes");

	}

	public void testContentsOfFieldValueIsSetCondition() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputTextField text2 = assertHasInputTextField(home, "text2");
		ActivityFunction cond = assertHasActivityFunction(text2, "fieldValue is set");
		Value value = assertHasFieldValue(text2);

		StartNode start = assertHasStartNode(cond);
		FinishNode finish = assertHasFinishNode(cond);
		CancelNode cancel = assertHasCancelNode(cond);

		DecisionNode check = assertHasDecisionNode(cond, "is set?");

		assertHasExecutionEdge(cond, start, check);
		assertHasExecutionEdge(cond, check, finish);
		assertHasExecutionEdge(cond, check, cancel);
		
		// check <- ExternalValue <- value
		{
			assertEquals(1, check.getInFlows().size());
			assertEquals(0, check.getOutFlows().size());
			ExternalValue ev = (ExternalValue) check.getInFlows().get(0).getFrom();
			assertEquals(value, ev.getExternalValueEdges().getValue());
		}

	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}

}
