/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunInstanceWire;
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
		EventTrigger edit = field1.getOnEdit();
		Operation update = assertHasOperation(field2, "update");
		RunInstanceWire rw = (RunInstanceWire) getWireFromTo(root, edit, update);

		Property fieldValue = assertHasProperty(field1, "fieldValue");
		assertGenerated(getParameterEdgeFromTo(root, fieldValue, rw));

		// session should have an 'init' event
		EventTrigger init = assertHasEventTrigger(session, "init");

		// it should be connected to 'update'
		RunInstanceWire rw2 = (RunInstanceWire) getWireFromTo(root, init, update);

		// there should only be one RunWire out of 'init'
		assertEquals("There should only be one out edge from init", 1, init.getOutEdges().size());

		// with the source fieldvalue as a parameter
		assertGenerated(getParameterEdgeFromTo(session, fieldValue, rw2));

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
		
		Property value = assertHasProperty(field2, "fieldValue");
		assertGenerated(value);
		
		RunInstanceWire run = assertHasRunInstanceWire(field1, access, init, "run");
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
		
		Property value = assertHasProperty(field2, "fieldValue");
		CompositeCondition cond = assertHasCompositeCondition(field2, "fieldValue is set");
		
		StartNode start = assertHasStartNode(cond);
		FinishNode finish = assertHasFinishNode(cond);
		CancelNode cancel = assertHasCancelNode(cond);
		
		DecisionCondition check = assertHasDecisionCondition(cond, "is set?");
		
		assertHasExecutionEdge(cond, start, check);
		assertHasExecutionEdge(cond, check, finish);
		assertHasExecutionEdge(cond, check, cancel);
		
		assertHasDataFlowEdge(cond, value, check);
		
		
	}

}
