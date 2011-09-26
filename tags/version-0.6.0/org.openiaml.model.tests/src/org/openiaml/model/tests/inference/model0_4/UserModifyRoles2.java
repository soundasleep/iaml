/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.ActivityParameter;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.SetNode;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the "user modify roles" codegen test case.
 *
 * @author jmwright
 *
 */
public class UserModifyRoles2 extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return UserModifyRoles2.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserModifyRoles2.class);

		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);

	}

	/**
	 * Check that the 'do login' operation is actually created
	 * properly.
	 *
	 * @throws Exception
	 */
	public void testContentsOfDoLoginOperation() throws Exception {
		root = loadAndInfer(UserModifyRoles2.class);

		Session session = assertHasSession(root, "target session");
		Session loginSession = assertHasSession(root, "current user login");
		assertGenerated(loginSession);
		ActivityOperation doLogin = assertHasActivityOperation(loginSession, "do login");

		StartNode start = assertHasStartNode(doLogin);
		FinishNode finish = assertHasFinishNode(doLogin);
		CancelNode cancel = assertHasCancelNode(doLogin);
		SplitNode split = assertHasSplitNode(doLogin);
		JoinNode join = assertHasJoinNode(doLogin);

		// there should be a decision node from 'empty?'
		DecisionNode decision = assertHasDecisionNode(doLogin, "true?");
		assertHasExecutionEdge(doLogin, start, split);
		assertHasExecutionEdge(doLogin, join, decision);
		assertHasExecutionEdge(doLogin, decision, cancel);
		assertHasExecutionEdge(doLogin, decision, finish);

		// get the keys in the session
		Value email = assertHasValue(session, "current email");
		assertGenerated(email);
		Value password = assertHasValue(session, "current password");
		assertGenerated(password);

		// the operation has parameters that are populated
		ActivityParameter pemail = assertHasActivityParameter(doLogin, "email");
		ActivityParameter ppassword = assertHasActivityParameter(doLogin, "password");

		// there will be many operations called 'set'
		List<?> sets = typeSelect(doLogin.getNodes(), SetNode.class);
		assertEquals(2, sets.size());

		boolean emailSet = false;
		boolean passwordSet = false;

		for (Object o : sets) {
			SetNode set = (SetNode) o;

			assertHasExecutionEdge(doLogin, split, set);
			assertHasExecutionEdge(doLogin, set, join);

			if (hasDataFlowEdge(doLogin, pemail, set)) {
				assertFalse(emailSet);

				// there should be a reference to an ExternalValue target
				assertEquals(1, set.getOutFlows().size());
				ExternalValue ev = (ExternalValue) set.getOutFlows().get(0).getTo();
				
				assertEquals(ev.getValue(), email);

				emailSet = true;
			} else if (hasDataFlowEdge(doLogin, ppassword, set)) {
				assertFalse(passwordSet);

				// there should be a reference to an ExternalValue target
				assertEquals(1, set.getOutFlows().size());
				ExternalValue ev = (ExternalValue) set.getOutFlows().get(0).getTo();
				
				assertEquals(ev.getValue(), password);

				passwordSet = true;
			} else {
				fail("Operation '" + set + "' did not set any appropriate properties.");
			}
		}

		assertTrue("'current email' was never set", emailSet);
		assertTrue("'current password' was never set", passwordSet);

	}

	/**
	 * The RunAction that runs the 'do login' operation should get the
	 * parameters from the input form.
	 *
	 * @throws Exception
	 */
	public void testDoLoginRunIncomingParameters() throws Exception {
		root = loadAndInfer(UserModifyRoles2.class);

		Session loginSession = assertHasSession(root, "current user login");

		// get the keys in the input form
		Frame login = assertHasFrame(loginSession, "login");
		InputForm form = assertHasInputForm(login, "login form");
		InputTextField temail = assertHasInputTextField(form, "email");
		InputTextField tpass = assertHasInputTextField(form, "password");
		Value femail = assertHasFieldValue(temail);
		Value fpassword = assertHasFieldValue(tpass);
		Button button = assertHasButton(form, "Login");

		// get the operation
		Operation doLogin = assertHasOperation(loginSession, "do login");

		// get the run instance wire
		ECARule run = assertHasRunAction(login, button, doLogin, "onClick");

		// assert ActivityParameter wires
		Parameter pe = assertHasParameter(root, femail, run);
		assertGenerated(pe);
		assertEquals("email", pe.getName());
		Parameter pp = assertHasParameter(root, fpassword, run);
		assertGenerated(pp);
		assertEquals("password", pp.getName());

	}

	/**
	 * On the login form, 'email' should appear before 'password' in
	 * render order.
	 *
	 * @throws Exception
	 */
	public void testRenderOrderOfEmailPassword() throws Exception {
		root = loadAndInfer(UserModifyRoles2.class);

		Session loginSession = assertHasSession(root, "current user login");

		// get the keys in the input form
		Frame login = assertHasFrame(loginSession, "login");
		InputForm form = assertHasInputForm(login, "login form");
		InputTextField temail = assertHasInputTextField(form, "email");
		InputTextField tpass = assertHasInputTextField(form, "password");

		assertGreater(temail.getRenderOrder(), tpass.getRenderOrder());

		// the 'Login' button should be after the password, too
		Button submit = assertHasButton(form, "Login");

		assertGreater(tpass.getRenderOrder(), submit.getRenderOrder());

	}

	/**
	 * Before r1110, the 'email' text field and 'current email' session
	 * properties were set with SetWires (a lazy approach). This should
	 * not be the case; they should be set with the 'do login' operation.
	 *
	 * @throws Exception
	 */
	public void testNoSetWireBetweenProperties() throws Exception {
		root = loadAndInfer(UserModifyRoles2.class);

		Session session = assertHasSession(root, "target session");

		// get the keys in the session
		Value email = assertHasValue(session, "current email");
		Value password = assertHasValue(session, "current password");

		// get the keys in the input form
		Session loginSession = assertHasSession(root, "current user login");
		Frame login = assertHasFrame(loginSession, "login");
		InputForm form = assertHasInputForm(login, "login form");
		InputTextField temail = assertHasInputTextField(form, "email");
		InputTextField tpass = assertHasInputTextField(form, "password");

		assertHasNoSetWire(root, temail, email);
		assertHasNoSetWire(root, tpass, password);
		assertHasNoSetWire(root, email, temail);
		assertHasNoSetWire(root, password, tpass);

		Value femail = assertHasFieldValue(temail);
		Value fpassword = assertHasFieldValue(tpass);

		assertHasNoSetWire(root, femail, email);
		assertHasNoSetWire(root, fpassword, password);
		assertHasNoSetWire(root, email, femail);
		assertHasNoSetWire(root, password, fpassword);

	}

	/**
	 * Check that the 'do logout' operation is actually created
	 * properly.
	 *
	 * @throws Exception
	 */
	public void testContentsOfDoLogoutOperation() throws Exception {
		root = loadAndInfer(UserModifyRoles2.class);

		Session session = assertHasSession(root, "target session");
		ActivityOperation doLogout = assertHasActivityOperation(session, "do logout");

		assertGenerated(assertHasStartNode(doLogout));
		assertGenerated(assertHasFinishNode(doLogout));
		assertHasNoCancelNode(doLogout);
		assertGenerated(assertHasSplitNode(doLogout));
		assertGenerated(assertHasJoinNode(doLogout));

		// get the keys in the session
		Value email = assertHasValue(session, "current email");
		assertGenerated(email);
		Value password = assertHasValue(session, "current password");
		assertGenerated(password);

		Value myNull = assertHasValue(doLogout, "reset value");
		assertTrue(myNull.isReadOnly());
		assertEquals("null", myNull.getDefaultValue());
		
		// there should be three ExternalValues
		List<?> evs = typeSelect(doLogout.getNodes(), ExternalValue.class);
		assertEquals(3, evs.size());
		
		boolean set1 = false;
		boolean set2 = false;
		boolean set3 = false;
		for (Object o : evs) {
			ExternalValue ev = (ExternalValue) o;
			
			assertNotNull(ev.getValue());
			if (ev.getValue().equals(myNull)) {
				// OK; should be the source of two 'set' nodes
				assertFalse(set1);
				
				assertEquals(2, ev.getOutFlows().size());
				assertEquals(0, ev.getInFlows().size());
				
				assertInstanceOf(SetNode.class, ev.getOutFlows().get(0).getTo());
				assertInstanceOf(SetNode.class, ev.getOutFlows().get(1).getTo());
				assertNotSame(ev.getOutFlows().get(0).getTo(), ev.getOutFlows().get(1).getTo());
				set1 = true;
				
			} else if (ev.getValue().equals(email)) {
				// OK; should be the target of a 'set' node
				assertFalse(set2);
				
				assertEquals(1, ev.getInFlows().size());
				assertEquals(0, ev.getOutFlows().size());

				assertInstanceOf(SetNode.class, ev.getInFlows().get(0).getFrom());
				set2 = true;

			} else if (ev.getValue().equals(password)) {
				// OK; should be the target of a 'set' node
				assertFalse(set3);
				
				assertEquals(1, ev.getInFlows().size());
				assertEquals(0, ev.getOutFlows().size());

				assertInstanceOf(SetNode.class, ev.getInFlows().get(0).getFrom());
				set3 = true;

			} else {
				fail("Unknown ExternalValue: " + ev.getValue() + " in ExternalValue: " + ev);
			}
		}
		
		assertTrue("'null' was never retrieved", set1);
		assertTrue("'current email' was never reset", set2);
		assertTrue("'current password' was never reset", set3);

	}

}
