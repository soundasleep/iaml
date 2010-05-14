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
import org.openiaml.model.model.wires.SetWire;
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
		
		// connected as a set wire
		SetWire set2 = assertHasSetWire(root, field, openid);
		assertGenerated(set2);
		
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
	 * The SetWire from the Provide page only Sets if the value can actually be cast.
	 * 
	 * @throws Exception
	 */
	public void testSetWireHasCanCastCondition() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		
		// connected as a set wire
		SetWire set = assertHasSetWire(root, field, openid);
		assertGenerated(set);
		
		// a ConditionEdge is created
		// from the *server* side
		Condition canCast = assertHasCondition(openid, "can cast?");
		assertGenerated(canCast);
		
		ConditionEdge edge = assertHasConditionEdge(root, canCast, set);
		assertGenerated(edge);

		// with a Parameter from the *client-side* field (this is important!)
		Property fieldValue = assertHasFieldValue(field);
		assertGenerated(fieldValue);
		assertHasParameterEdge(root, fieldValue, edge);

	}
	
	
	/**
	 * The Provide.text.onChange should call the Session.label.update
	 * but with the same ConditionEdge as the SetWire.
	 * 
	 * @throws Exception
	 */
	public void testSourceEditHasCondition() throws Exception {
		
		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Label openid = assertHasLabel(session, "Current OpenID");
		
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		
		// a ConditionEdge is created
		// from the *server* side
		Condition canCast = assertHasCondition(openid, "can cast?");
		
		EventTrigger onChange = field.getOnChange();
		assertGenerated(onChange);
		
		Operation update = assertHasOperation(openid, "update");
		assertGenerated(update);
		
		RunAction run = assertHasRunAction(root, onChange, update);
		assertGenerated(run);
		
		// with a Parameter from the *client-side* field (this is important!)
		Property fieldValue = assertHasFieldValue(field);
		assertGenerated(fieldValue);
		assertHasParameterEdge(root, fieldValue, run);
		
		assertGenerated(assertHasParameterEdge(root, fieldValue, run));
		
		ConditionEdge edge = assertHasConditionEdge(root, canCast, run);
		assertGenerated(edge);
		
		// with a Parameter from the *client-side* field (this is important!)
		assertGenerated(fieldValue);
		assertHasParameterEdge(root, fieldValue, edge);

	}
	

}
