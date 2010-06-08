/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.NavigateAction;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * 
 * @author jmwright
 */
public class EntryGateRequiresOpenID extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(EntryGateRequiresOpenID.class);
	}
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		assertNotGenerated(session);
		
		Label openid = assertHasLabel(session, "Current OpenID");
		assertEquals(openid.getType(), BuiltinDataTypes.getTypeOpenIDURL());
		assertNotGenerated(openid);
		
		Frame secure = assertHasFrame(session, "Secure Page");
		assertNotGenerated(secure);
		
		Frame see = assertHasFrame(session, "See Your OpenID");
		assertNotGenerated(see);
		
		EntryGate gate = assertHasEntryGate(session, "Entry Gate");
		assertNotGenerated(gate);
		
		assertNotGenerated(assertHasRequiresEdge(root, gate, openid));
		
		Label viewLabel = assertHasLabel(see, "your current ID");
		assertNotGenerated(viewLabel);
		
		assertNotGenerated(assertHasSetWire(root, openid, viewLabel));
		
	}

	/**
	 * A page to enter in the OpenID details is created.
	 * 
	 * @throws Exception
	 */
	public void testEnterOpenIDPageCreated() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		EntryGate gate = assertHasEntryGate(session, "Entry Gate");
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		assertGenerated(enter);
		
		// it has one text field
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		assertGenerated(field);
		
		// same data type as openid
		assertEquals(openid.getType(), field.getType());
		
		// NOT connected as a set wire
		assertHasNoSetWire(root, field, openid);

		// the Frame is connected as a NavigateAction from the Gate
		NavigateAction nav = assertHasNavigateAction(root, gate, enter);
		assertGenerated(nav);
		
	}
	
	/**
	 * The EntryGate has an incoming ConditionEdge, from the 'is set' Condition
	 * contained within the InputTextField. This is because the value can only be
	 * set by a SetWire if the provided value is valid. 
	 * 
	 * @throws Exception
	 */
	public void testEntryGateHasCondition() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		EntryGate gate = assertHasEntryGate(session, "Entry Gate");
		
		// a condition is set within the openid label, "fieldValue is set"
		Condition cond = assertHasCondition(openid, "fieldValue is set");
		assertGenerated(cond);
		
		// connected to the EventGate
		assertGenerated(assertHasConditionEdge(root, cond, gate));
	}
	
	/**
	 * The 'Provide OpenID' page also has a Button to resume the Gate.
	 * 
	 * @throws Exception
	 */
	public void testProvidePageHasContinueButton() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		EntryGate gate = assertHasEntryGate(session, "Entry Gate");
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		
		// there's a Button named 'resume'
		Button button = assertHasButton(enter, "Continue");
		assertGenerated(button);
		
		// connects to EntryGate
		EventTrigger click = button.getOnClick();
		assertGenerated(click);
		
		NavigateAction nav = assertHasNavigateAction(root, click, gate);
		assertGenerated(nav);
		
	}
		
	/**
	 * Since the target Label is contained within a Session, protected by a
	 * Gate that requires the Label to be valid before it can actually be set,
	 * we can't connect the TextField with a SetWire directly, because
	 * Label.update() will force a redirect to the very same page. 
	 * 
	 * @throws Exception
	 */
	public void testEnterIDNotSetToLabel() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		
		// not connected with a set wire
		assertHasNoSetWire(root, field, openid);
		
	}
	
	/**
	 * The Scope.onAccess event will try to update the Label value
	 * with the provided ID, only if it is valid. This is possible
	 * because onInit/onAccess occurs before Gate events.
	 * 
	 * @throws Exception
	 */
	public void testSessionOnAccessUpdatesLabel() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		EventTrigger onAccess = session.getOnAccess();
		assertGenerated(onAccess);
		
		// the target operation
		//CompositeOperation target = assertHasCompositeOperation(session, "Update Current OpenID");
		Operation target = assertHasOperation(openid, "update");
		assertGenerated(target);
		
		// is run
		RunAction run = assertHasRunAction(root, onAccess, target);
		assertGenerated(run);
		
		// with a value from the text field
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		Property fieldValue = assertHasFieldValue(field);
		assertGenerated(fieldValue);
		assertGenerated(assertHasParameterEdge(root, fieldValue, run));
		
	}
	
	/**
	 * The Scope.Label is only updated if the provided value has actually
	 * been set. 
	 * 
	 * @throws Exception
	 */
	public void testSessionOnAccessUpdatesLabelOnlyIfSet() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		EventTrigger onAccess = session.getOnAccess();
		Operation target = assertHasOperation(openid, "update");
		RunAction run = assertHasRunAction(root, onAccess, target);
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		
		// generated 'is set?' condition
		Condition isSet = assertHasCondition(field, "fieldValue is set");
		assertGenerated(isSet);
		
		// connected to the RunAction
		assertGenerated(assertHasConditionEdge(root, isSet, run));
		
	}
	
	/**
	 * The Scope.Label is only updated if the provided value can actually
	 * be cast. This is necessary because the client-side validation (e.g. OpenID)
	 * is only done at server-side.
	 * 
	 * @throws Exception
	 */
	public void testSessionOnAccessUpdatesLabelOnlyIfCastable() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		EventTrigger onAccess = session.getOnAccess();
		Operation target = assertHasOperation(openid, "update");
		RunAction run = assertHasRunAction(root, onAccess, target);

		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		Property fieldValue = assertHasFieldValue(field);
		
		// generated 'can cast?' condition on the target
		Condition isSet = assertHasCondition(openid, "can cast?");
		assertGenerated(isSet);
		
		// connected to the RunAction
		ConditionEdge edge = assertHasConditionEdge(root, isSet, run);
		assertGenerated(edge);
		
		// connected with a ParameterEdge from the source
		assertGenerated(assertHasParameterEdge(root, fieldValue, edge));
		
	}

}
