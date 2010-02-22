/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_2;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.RunInstanceWire;
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
		
		EntryGate gate = assertHasEntryGate(session, "requires a page is viewed first");
		assertNotGenerated(gate);
		
		NavigateWire nav = assertHasNavigateWire(session, gate, required, "first");
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
		EntryGate gate = assertHasEntryGate(session, "requires a page is viewed first");
		
		EventTrigger event = assertHasEventTrigger(button, "click");
		assertGenerated(event);
		
		NavigateWire nav = assertHasNavigateWire(root, event, gate, "resume");
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
		ApplicationElementProperty property = assertHasApplicationElementProperty(session, "requires a page is viewed first flag");
		assertGenerated(property);
		assertEquals(property.getDefaultValue(), "false");

		// required page
		Frame required = assertHasFrame(root, "Required Page");
		EventTrigger access = assertHasEventTrigger(required, "access");
		assertGenerated(access);
		
		// set operation
		CompositeOperation set = assertHasCompositeOperation(required, "Set gate flag");
		assertGenerated(set);
		
		// run wire
		RunInstanceWire run = assertHasRunInstanceWire(required, access, set);
		assertGenerated(run);
		
	}
	
	/**
	 * Check the contents of the generated 'Set gate flag' operation.
	 */
	public void testSetOperationContents() throws Exception {
		root = loadAndInfer(GateRequiredPage.class);
		
		Session session = assertHasSession(root, "Session");
		
		// generated property
		ApplicationElementProperty property = assertHasApplicationElementProperty(session, "requires a page is viewed first flag");

		// required page
		Frame required = assertHasFrame(root, "Required Page");

		// set operation
		CompositeOperation set = assertHasCompositeOperation(required, "Set gate flag");
		
		// start node
		StartNode start = assertHasStartNode(set);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(set);
		assertGenerated(finish);
		PrimitiveOperation s = assertHasPrimitiveOperation(set, "setPropertyToValue");
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
		root = loadAndInfer(GateRequiredPage.class);

		Session session = assertHasSession(root, "Session");
		EntryGate gate = assertHasEntryGate(session, "requires a page is viewed first");
		
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
		ApplicationElementProperty property = assertHasApplicationElementProperty(session, "requires a page is viewed first flag");

		// generated condition
		CompositeCondition condition = assertHasCompositeCondition(session, "check requires a page is viewed first");
		
		// start node
		StartNode start = assertHasStartNode(condition);
		assertGenerated(start);
		FinishNode finish = assertHasFinishNode(condition);
		assertGenerated(finish);
		CancelNode cancel = assertHasCancelNode(condition);
		assertGenerated(cancel);
		DecisionOperation check = assertHasDecisionOperation(condition, "true?");
		assertGenerated(check);
		
		// flow
		assertGenerated(assertHasExecutionEdge(condition, start, check));
		assertGenerated(assertHasExecutionEdge(condition, check, finish, "y"));
		assertGenerated(assertHasExecutionEdge(condition, check, cancel, "n"));
		assertGenerated(assertHasDataFlowEdge(condition, property, check));		
		
	}
	
}
