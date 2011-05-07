/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.EXSDDataType;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.components.Gate;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.SimpleCondition;
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
		assertEqualType(openid.getType(), BuiltinDataTypes.getTypeOpenIDURL());
		assertNotGenerated(openid);

		Frame secure = assertHasFrame(session, "Secure Page");
		assertNotGenerated(secure);

		Frame see = assertHasFrame(session, "See Your OpenID");
		assertNotGenerated(see);

		Gate gate = session.getEntryGate();
		assertEquals("Entry Gate", gate.getName());
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
		Gate gate = session.getEntryGate();
		assertEquals("Entry Gate", gate.getName());

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
		ECARule nav = assertHasNavigateAction(root, gate, enter);
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
		Gate gate = session.getEntryGate();
		assertEquals("Entry Gate", gate.getName());

		// a Function is set within the openid label, "fieldValue is set"
		Function cond = assertHasFunction(openid, "fieldValue is set");
		assertGenerated(cond);

		// connected to the EventGate
		assertGenerated(assertHasSimpleCondition(root, cond, gate));
	}

	/**
	 * The 'Provide OpenID' page also has a Button to resume the Gate.
	 *
	 * @throws Exception
	 */
	public void testProvidePageHasContinueButton() throws Exception {

		Session container = assertHasSession(root, "Containing Session");
		Session session = assertHasSession(container, "Protected Session");
		Gate gate = session.getEntryGate();
		assertEquals("Entry Gate", gate.getName());

		Frame enter = assertHasFrame(container, "Provide Current OpenID");

		// there's a Button named 'resume'
		Button button = assertHasButton(enter, "Continue");
		assertGenerated(button);

		// connects to EntryGate
		Event click = button.getOnClick();
		assertGenerated(click);

		ECARule nav = assertHasNavigateAction(root, click, gate);
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

		Event onAccess = session.getOnAccess();
		assertGenerated(onAccess);

		// the target operation
		//CompositeOperation target = assertHasCompositeOperation(session, "Update Current OpenID");
		Operation target = assertHasOperation(openid, "update");
		assertGenerated(target);

		// is run
		ECARule run = assertHasRunAction(root, onAccess, target);
		assertGenerated(run);

		// with a value from the text field
		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		Value fieldValue = assertHasFieldValue(field);
		assertGenerated(fieldValue);
		assertGenerated(assertHasParameter(root, fieldValue, run));

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

		Event onAccess = session.getOnAccess();
		Operation target = assertHasOperation(openid, "update");
		ECARule run = assertHasRunAction(root, onAccess, target);

		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");

		// generated 'is set?' condition
		Function isSet = assertHasFunction(field, "fieldValue is set");
		assertGenerated(isSet);

		// connected to the RunAction
		assertGenerated(assertHasSimpleCondition(root, isSet, run));

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

		Event onAccess = session.getOnAccess();
		Operation target = assertHasOperation(openid, "update");
		ECARule run = assertHasRunAction(root, onAccess, target);

		Frame enter = assertHasFrame(container, "Provide Current OpenID");
		InputTextField field = assertHasInputTextField(enter, "Current OpenID");
		Value fieldValue = assertHasFieldValue(field);

		// generated 'can cast?' Function on the target
		Function isSet = assertHasFunction(openid, "can cast?");
		assertGenerated(isSet);

		// connected to the RunAction
		SimpleCondition edge = assertHasSimpleCondition(root, isSet, run);
		assertGenerated(edge);

		// connected with a Parameter from the source
		assertGenerated(assertHasParameter(root, fieldValue, edge));

	}

}
