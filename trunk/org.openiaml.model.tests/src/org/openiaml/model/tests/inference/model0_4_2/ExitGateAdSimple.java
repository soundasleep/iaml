/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_2;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.components.Gate;
import org.openiaml.model.model.operations.ActivityFunction;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.SetNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the ExitGate model completion rules.
 *
 * @author jmwright
 *
 */
public class ExitGateAdSimple extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return ExitGateAdSimple.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(ExitGateAdSimple.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);

		Session session = assertHasSession(root, "Advertising Session");
		assertNotGenerated(session);

		Frame external = assertHasFrame(root, "External Page");
		assertNotGenerated(external);

		Frame page1 = assertHasFrame(session, "Page 1");
		assertNotGenerated(page1);
		Frame page2 = assertHasFrame(session, "Page 2");
		assertNotGenerated(page2);

		Frame ad = assertHasFrame(session, "Advertisement");
		assertNotGenerated(ad);

		Gate gate = session.getExitGate();
		assertEquals("View Ads Exit Gate", gate.getName());
		assertNotGenerated(gate);

		ECARule nav = assertHasNavigateAction(session, gate, ad, "last");
		assertNotGenerated(nav);

	}

	/**
	 * A button "Continue" will be created in the Advertisement.
	 * This will navigate back to the Gate.
	 *
	 * @throws Exception
	 */
	public void testAdPageHasContinueButton() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		Frame ad = assertHasFrame(session, "Advertisement");
		Gate gate = session.getExitGate();
		assertEquals("View Ads Exit Gate", gate.getName());

		Button button = assertHasButton(ad, "Continue");
		assertGenerated(button);

		Event event = button.getOnClick();
		assertGenerated(event);

		ECARule nav = assertHasNavigateAction(root, event, gate, "resume");
		assertGenerated(nav);

	}

	/**
	 * A Value is generated which will store the
	 * value of the visited.
	 *
	 * @throws Exception
	 */
	public void testAdPageSetsProperty() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		Frame ad = assertHasFrame(session, "Advertisement");

		// generated property
		Value property = assertHasValue(session, "View Ads Exit Gate flag");
		assertGenerated(property);
		assertEquals(property.getDefaultValue(), "false");

		// required page
		Event access = ad.getOnAccess();
		assertGenerated(access);

		// set operation
		ActivityOperation set = assertHasActivityOperation(ad, "Set gate flag");
		assertGenerated(set);

		// run wire
		ECARule run = assertHasRunAction(ad, access, set);
		assertGenerated(run);

	}

	/**
	 * Check the contents of the generated 'Set gate flag' operation.
	 */
	public void testSetOperationContents() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		Frame ad = assertHasFrame(session, "Advertisement");

		// generated property
		Value property = assertHasValue(session, "View Ads Exit Gate flag");

		// set operation
		ActivityOperation set = assertHasActivityOperation(ad, "Set gate flag");

		// start node
		StartNode start = assertHasStartNode(set);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(set);
		assertGenerated(finish);
		SetNode s = assertHasSetNode(set);
		assertGenerated(s);
		Value value = assertHasValue(set, "true");
		assertTrue(value.isReadOnly());
		assertGenerated(value);
		assertEquals("true", value.getDefaultValue());

		// flow
		assertGenerated(assertHasExecutionEdge(set, start, s));
		assertGenerated(assertHasExecutionEdge(set, s, finish));
		
		// set <- externalValue <- value
		{
			assertEquals(1, s.getInFlows().size());
			ExternalValue ev = (ExternalValue) s.getInFlows().get(0).getFrom();
			assertEquals(value, ev.getExternalValueEdges().getValue());
		}
		
		// setnode -> externalValue -> property
		{
			assertEquals(1, s.getOutFlows().size());
			ExternalValue ev = (ExternalValue) s.getOutFlows().get(0).getTo();
			assertEquals(property, ev.getExternalValueEdges().getValue());
		}

	}

	/**
	 * A Function will be created in the Session to check the
	 * given property.
	 */
	public void testConditionWire() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		Gate gate = session.getExitGate();
		assertEquals("View Ads Exit Gate", gate.getName());

		// generated condition
		ActivityFunction condition = assertHasActivityFunction(session, "check View Ads Exit Gate");
		assertGenerated(condition);

		SimpleCondition wire = assertHasSimpleCondition(session, condition, gate, "condition");
		assertGenerated(wire);

	}

	/**
	 * Check the contents of the generated condition.
	 */
	public void testConditionContents() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");

		// generated property
		Value property = assertHasValue(session, "View Ads Exit Gate flag");

		// generated condition
		ActivityFunction condition = assertHasActivityFunction(session, "check View Ads Exit Gate");

		// start node
		StartNode start = assertHasStartNode(condition);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(condition);
		assertGenerated(finish);
		CancelNode cancel = assertHasCancelNode(condition);
		assertGenerated(cancel);
		DecisionNode check = assertHasDecisionNode(condition, "true?");
		assertGenerated(check);

		// flow
		assertGenerated(assertHasExecutionEdge(condition, start, check));
		assertGenerated(assertHasExecutionEdge(condition, check, finish, "y"));
		assertGenerated(assertHasExecutionEdge(condition, check, cancel, "n"));
		
		// property -> ExternalValue -> check
		{
			assertEquals(1, check.getInFlows().size());
			ExternalValue ev = (ExternalValue) check.getInFlows().get(0).getFrom();
			assertEquals(property, ev.getExternalValueEdges().getValue());
		}
		
	}

}
