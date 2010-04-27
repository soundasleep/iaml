/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.tests.inference.model0_4.SetWireClient;

/**
 * Tests sessions: sync wires across session boundaries.
 *
 * In particular, the session should create an "init" event, connected to
 * the "update" operation, from the SyncWire that was connected.
 *
 * @author jmwright
 *
 */
public class SessionSyncWires extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SessionSyncWires.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Frame outside = assertHasFrame(root, "outside");
		Session session = assertHasSession(root, "session");
		Frame inside = assertHasFrame(session, "inside");
		assertNotSame(outside, inside);
		InputTextField field1 = assertHasInputTextField(outside, "target");
		InputTextField field2 = assertHasInputTextField(inside, "target");
		assertNotSame(field1, field2);

		// generated events and operations
		// all part of SyncWire elements generation
		EventTrigger edit = field1.getOnChange();
		Operation update = assertHasOperation(field2, "update");
		RunAction rw = assertHasRunAction(root, edit, update);

		Property fieldValue = assertHasFieldValue(field1);
		assertGenerated(getParameterEdgeFromTo(root, fieldValue, rw));

		// session should have an 'init' event
		EventTrigger init = session.getOnInit();

		// it should be connected to 'update'
		RunAction rw2 = assertHasRunAction(root, init, update);
		assertGenerated(rw2);

	}

	/**
	 * In version 0.4.1, all access->init operations that are based
	 * on a session variable should also have a condition to check that
	 * the variable has been set.
	 *
	 * @see SetWireClient#testSetWireCondition()
	 * @throws Exception
	 */
	public void testSessionParamterAddsCheckCondition() throws Exception {
		Frame outside = assertHasFrame(root, "outside");
		Session session = assertHasSession(root, "session");
		Frame inside = assertHasFrame(session, "inside");
		InputTextField field1 = assertHasInputTextField(outside, "target");
		InputTextField field2 = assertHasInputTextField(inside, "target");

		EventTrigger access = field1.getOnAccess();
		assertGenerated(access);
		Operation init = assertHasOperation(field1, "init");
		assertGenerated(init);

		Property value = assertHasFieldValue(field2);
		assertGenerated(value);

		RunAction run = assertHasRunAction(field1, access, init, "run");
		assertGenerated(run);

		ParameterEdge param = assertHasParameterEdge(field1, value, run);
		assertGenerated(param);

		// newly created condition
		CompositeCondition cond = assertHasCompositeCondition(field2, "fieldValue is set");
		assertGenerated(cond);

		ConditionEdge cw = assertHasConditionEdge(root, cond, run);
		assertGenerated(cw);

	}

	/**
	 * Tests the contents of Session.Page.target.[fieldValue is set]
	 *
	 * @throws Exception
	 */
	public void testSessionCheckConditionContents() throws Exception {
		Session session = assertHasSession(root, "session");
		Frame inside = assertHasFrame(session, "inside");
		InputTextField field2 = assertHasInputTextField(inside, "target");

		Property value = assertHasFieldValue(field2);
		CompositeCondition cond = assertHasCompositeCondition(field2, "fieldValue is set");

		StartNode start = assertHasStartNode(cond);
		FinishNode finish = assertHasFinishNode(cond);
		CancelNode cancel = assertHasCancelNode(cond);

		DecisionNode check = assertHasDecisionNode(cond, "is set?");

		assertHasExecutionEdge(cond, start, check);
		assertHasExecutionEdge(cond, check, finish);
		assertHasExecutionEdge(cond, check, cancel);

		assertHasDataFlowEdge(cond, value, check);


	}

}
