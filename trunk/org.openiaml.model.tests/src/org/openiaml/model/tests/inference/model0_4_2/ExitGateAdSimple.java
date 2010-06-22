/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_2;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.components.ExitGate;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ConditionEdge;
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

		ExitGate gate = assertHasExitGate(session, "View Ads Exit Gate");
		assertNotGenerated(gate);

		ActionEdge nav = assertHasNavigateAction(session, gate, ad, "last");
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
		ExitGate gate = assertHasExitGate(session, "View Ads Exit Gate");

		Button button = assertHasButton(ad, "Continue");
		assertGenerated(button);

		EventTrigger event = button.getOnClick();
		assertGenerated(event);

		ActionEdge nav = assertHasNavigateAction(root, event, gate, "resume");
		assertGenerated(nav);

	}

	/**
	 * A property is generated which will store the
	 * value of the visited.
	 *
	 * @throws Exception
	 */
	public void testAdPageSetsProperty() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		Frame ad = assertHasFrame(session, "Advertisement");

		// generated property
		Property property = assertHasProperty(session, "View Ads Exit Gate flag");
		assertGenerated(property);
		assertEquals(property.getDefaultValue(), "false");

		// required page
		EventTrigger access = ad.getOnAccess();
		assertGenerated(access);

		// set operation
		CompositeOperation set = assertHasCompositeOperation(ad, "Set gate flag");
		assertGenerated(set);

		// run wire
		ActionEdge run = assertHasRunAction(ad, access, set);
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
		Property property = assertHasProperty(session, "View Ads Exit Gate flag");

		// set operation
		CompositeOperation set = assertHasCompositeOperation(ad, "Set gate flag");

		// start node
		StartNode start = assertHasStartNode(set);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(set);
		assertGenerated(finish);
		PrimitiveOperation s = assertHasPrimitiveOperation(set, "set");
		assertGenerated(s);
		StaticValue value = assertHasStaticValue(set, "true");
		assertGenerated(value);
		assertEquals(value.getValue(), "true");

		// flow
		assertGenerated(assertHasExecutionEdge(set, start, s));
		assertGenerated(assertHasExecutionEdge(set, s, finish));
		assertGenerated(assertHasDataFlowEdge(set, value, s));
		assertGenerated(assertHasDataFlowEdge(set, s, property));

	}

	/**
	 * A condition will be created in the Session to check the
	 * given property.
	 */
	public void testConditionWire() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");
		ExitGate gate = assertHasExitGate(session, "View Ads Exit Gate");

		// generated condition
		CompositeCondition condition = assertHasCompositeCondition(session, "check View Ads Exit Gate");
		assertGenerated(condition);

		ConditionEdge wire = assertHasConditionEdge(session, condition, gate, "condition");
		assertGenerated(wire);

	}

	/**
	 * Check the contents of the generated condition.
	 */
	public void testConditionContents() throws Exception {
		root = loadAndInfer(ExitGateAdSimple.class);

		Session session = assertHasSession(root, "Advertising Session");

		// generated property
		Property property = assertHasProperty(session, "View Ads Exit Gate flag");

		// generated condition
		CompositeCondition condition = assertHasCompositeCondition(session, "check View Ads Exit Gate");

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
		assertGenerated(assertHasDataFlowEdge(condition, property, check));

	}

}
