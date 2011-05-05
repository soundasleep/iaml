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
import org.openiaml.model.model.components.Gate;
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
 * Inference of the EntryGate model completion rules.
 *
 * @author jmwright
 *
 */
public class GateRequiredPage extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return GateRequiredPage.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(GateRequiredPage.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);

		Session session = assertHasSession(root, "Session");
		assertNotGenerated(session);

		Frame required = assertHasFrame(root, "Required Page");
		assertNotGenerated(required);

		Gate gate = session.getEntryGate();
		assertEquals("requires a page is viewed first", gate.getName());
		assertNotGenerated(gate);

		ActionEdge nav = assertHasNavigateAction(session, gate, required, "first");
		assertNotGenerated(nav);

	}

	/**
	 * A button "Continue" will be created in the Required Page.
	 * This will navigate back to the Gate.
	 *
	 * @throws Exception
	 */
	public void testRequiredPageHasContinueButton() throws Exception {
		root = loadAndInfer(GateRequiredPage.class);

		Frame required = assertHasFrame(root, "Required Page");

		Button button = assertHasButton(required, "Continue");
		assertGenerated(button);

		Session session = assertHasSession(root, "Session");
		Gate gate = session.getEntryGate();
		assertEquals("requires a page is viewed first", gate.getName());

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
	public void testRequiredPageSetsProperty() throws Exception {
		root = loadAndInfer(GateRequiredPage.class);

		Session session = assertHasSession(root, "Session");

		// generated property
		Property property = assertHasProperty(session, "requires a page is viewed first flag");
		assertGenerated(property);
		assertEquals(property.getDefaultValue(), "false");

		// required page
		Frame required = assertHasFrame(root, "Required Page");
		EventTrigger access = required.getOnAccess();
		assertGenerated(access);

		// set operation
		CompositeOperation set = assertHasCompositeOperation(required, "Set gate flag");
		assertGenerated(set);

		// run wire
		ActionEdge run = assertHasRunAction(required, access, set);
		assertGenerated(run);

	}

	/**
	 * Check the contents of the generated 'Set gate flag' operation.
	 */
	public void testSetOperationContents() throws Exception {
		root = loadAndInfer(GateRequiredPage.class);

		Session session = assertHasSession(root, "Session");

		// generated property
		Property property = assertHasProperty(session, "requires a page is viewed first flag");

		// required page
		Frame required = assertHasFrame(root, "Required Page");

		// set operation
		CompositeOperation set = assertHasCompositeOperation(required, "Set gate flag");

		// start node
		StartNode start = assertHasStartNode(set);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(set);
		assertGenerated(finish);
		PrimitiveOperation s = assertHasPrimitiveOperation(set, "set");
		assertGenerated(s);
		Property value = assertHasProperty(set, "true");
		assertTrue(value.isReadOnly());
		assertGenerated(value);
		assertEquals("true", value.getDefaultValue());

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
		root = loadAndInfer(GateRequiredPage.class);

		Session session = assertHasSession(root, "Session");
		Gate gate = session.getEntryGate();
		assertEquals("requires a page is viewed first", gate.getName());

		// generated condition
		CompositeCondition condition = assertHasCompositeCondition(session, "check requires a page is viewed first");
		assertGenerated(condition);

		ConditionEdge wire = assertHasConditionEdge(session, condition, gate, "condition");
		assertGenerated(wire);

	}

	/**
	 * Check the contents of the generated condition.
	 */
	public void testConditionContents() throws Exception {
		root = loadAndInfer(GateRequiredPage.class);

		Session session = assertHasSession(root, "Session");

		// generated property
		Property property = assertHasProperty(session, "requires a page is viewed first flag");

		// generated condition
		CompositeCondition condition = assertHasCompositeCondition(session, "check requires a page is viewed first");

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
