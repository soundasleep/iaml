/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;

import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the "user modify roles" codegen test case.
 *
 * @author jmwright
 *
 */
public class UserModifyRoles extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return UserModifyRoles.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserModifyRoles.class);

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
		root = loadAndInfer(UserModifyRoles.class);

		Session session = assertHasSession(root, "target session");
		Session loginSession = assertHasSession(root, "current user login");
		assertGenerated(loginSession);
		CompositeOperation doLogin = assertHasCompositeOperation(loginSession, "do login");

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
		Property email = assertHasProperty(session, "current email");
		assertGenerated(email);
		Property password = assertHasProperty(session, "current password");
		assertGenerated(password);

		// the operation has parameters that are populated
		Parameter pemail = assertHasParameter(doLogin, "email");
		Parameter ppassword = assertHasParameter(doLogin, "password");

		// there will be many operations called 'set'
		List<?> sets = query(doLogin, "iaml:operations[iaml:name='set']");
		assertEquals(2, sets.size());

		boolean emailSet = false;
		boolean passwordSet = false;

		for (Object o : sets) {
			PrimitiveOperation set = (PrimitiveOperation) o;
			assertEquals("set", set.getName());

			assertHasExecutionEdge(doLogin, split, set);
			assertHasExecutionEdge(doLogin, set, join);

			if (hasDataFlowEdge(doLogin, pemail, set)) {
				assertGenerated(assertHasDataFlowEdge(doLogin, set, email));
				assertFalse(emailSet);
				emailSet = true;
			} else if (hasDataFlowEdge(doLogin, ppassword, set)) {
				assertGenerated(assertHasDataFlowEdge(doLogin, set, password));
				assertFalse(passwordSet);
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
		root = loadAndInfer(UserModifyRoles.class);

		Session loginSession = assertHasSession(root, "current user login");

		// get the keys in the input form
		Frame login = assertHasFrame(loginSession, "login");
		InputForm form = assertHasInputForm(login, "login form");
		InputTextField temail = assertHasInputTextField(form, "email");
		InputTextField tpass = assertHasInputTextField(form, "password");
		Property femail = assertHasFieldValue(temail);
		Property fpassword = assertHasFieldValue(tpass);
		Button button = assertHasButton(form, "Login");

		// get the operation
		Operation doLogin = assertHasOperation(loginSession, "do login");

		// get the run instance wire
		RunAction run = assertHasRunAction(login, button, doLogin, "onClick");

		// assert parameter wires
		ParameterEdge pe = assertHasParameterEdge(root, femail, run);
		assertGenerated(pe);
		assertEquals("email", pe.getName());
		ParameterEdge pp = assertHasParameterEdge(root, fpassword, run);
		assertGenerated(pp);
		assertEquals("password", pp.getName());

	}

	/**
	 * Before r1110, the 'email' text field and 'current email' session
	 * properties were set with SetWires (a lazy approach). This should
	 * not be the case; they should be set with the 'do login' operation.
	 *
	 * @throws Exception
	 */
	public void testNoSetWireBetweenProperties() throws Exception {
		root = loadAndInfer(UserModifyRoles.class);

		Session session = assertHasSession(root, "target session");

		// get the keys in the session
		Property email = assertHasProperty(session, "current email");
		Property password = assertHasProperty(session, "current password");

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

		Property femail = assertHasFieldValue(temail);
		Property fpassword = assertHasFieldValue(tpass);

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
		root = loadAndInfer(UserModifyRoles.class);

		Session session = assertHasSession(root, "target session");
		CompositeOperation doLogout = assertHasCompositeOperation(session, "do logout");

		assertGenerated(assertHasStartNode(doLogout));
		assertGenerated(assertHasFinishNode(doLogout));
		assertHasNoCancelNode(doLogout);
		assertGenerated(assertHasSplitNode(doLogout));
		assertGenerated(assertHasJoinNode(doLogout));

		// get the keys in the session
		Property email = assertHasProperty(session, "current email");
		assertGenerated(email);
		Property password = assertHasProperty(session, "current password");
		assertGenerated(password);

		StaticValue myNull = assertHasStaticValue(doLogout, "reset value");
		assertEquals("null", myNull.getValue());

		// there will be many operations called 'set'
		List<?> sets = query(doLogout, "iaml:operations[iaml:name='set']");
		assertEquals(2, sets.size());

		boolean emailReset = false;
		boolean passwordReset = false;

		for (Object o : sets) {
			Operation set = (Operation) o;
			assertEquals("set", set.getName());

			DataFlowEdge data = assertHasDataFlowEdge(doLogout, myNull, set);
			assertGenerated(data);

			if (hasDataFlowEdge(doLogout, set, email)) {
				assertFalse(emailReset);
				emailReset = true;
			} else if (hasDataFlowEdge(doLogout, set, password)) {
				assertFalse(passwordReset);
				passwordReset = true;
			} else {
				fail("Data edge '" + data + "' did not go anywhere interesting.");
			}
		}

		assertTrue("'current email' was never reset", emailReset);
		assertTrue("'current password' was never reset", passwordReset);

	}

}
