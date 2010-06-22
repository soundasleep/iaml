/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;
import java.util.Set;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Test case for inference of login handler[type=secret key]
 *
 * @author jmwright
 *
 */
public class LoginHandlerKey extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return LoginHandlerKey.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(LoginHandlerKey.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		StaticValue value = assertHasStaticValue(session, "login key");
		assertNotGenerated(value);

		Frame viewkey = assertHasFrame(session, "viewkey");
		assertNotGenerated(viewkey);

		LoginHandler handler = assertHasLoginHandler(session, "Login Handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.SECRET_KEY);

		// stored key
		Property key = assertHasProperty(session, "my login key");
		assertNotGenerated(key);
		// the key must have a default
		assertNotNull(key.getDefaultValue());
		assertEquals("", key.getDefaultValue());

		// handler logout--> home
		{
			assertHasNoWiresFromTo(handler, handler, page);
			ActionEdge nav = assertHasNavigateAction(handler, handler, page, "logout");
			assertNotGenerated(nav);
		}

		// handler login--> viewkey
		{
			assertHasNoWiresFromTo(handler, handler, viewkey);
			ActionEdge nav = assertHasNavigateAction(handler, handler, viewkey, "login");
			assertNotGenerated(nav);
		}

		// handler set--> key
		{
			Set<Wire> wires = assertHasWiresFromTo(1, handler, handler, key);
			SetWire nav = (SetWire) wires.iterator().next();
			assertNotGenerated(nav);
			assertEquals("set", nav.getName());
		}

	}

	/**
	 * Test the generation of event triggers and check operations.
	 *
	 * @throws Exception
	 */
	public void testChecks() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		Frame viewkey = assertHasFrame(session, "viewkey");
		assertNotGenerated(viewkey);

		Operation check = assertHasOperation(session, "check key");
		EventTrigger access = viewkey.getOnAccess();
		assertHasWiresFromTo(0, session, access, check);
		{
			ActionEdge run = assertHasRunAction(session, access, check);
			assertGenerated(run);
			assertEquals("run", run.getName());
		}

	}

	/**
	 * The logout page should execute the 'do logout' operation
	 *
	 * @throws Exception
	 */
	public void testLogoutOperation() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);

		Operation op = assertHasOperation(session, "do logout");
		EventTrigger access = page.getOnAccess();
		assertHasWiresFromTo(0, session, access, op);
		{
			ActionEdge run = assertHasRunAction(session, access, op);
			assertGenerated(run);
		}

	}

	/**
	 * A default logout page should be created; this will
	 * only redirect to the actual logout page in our case.
	 *
	 * @throws Exception
	 */
	public void testGeneratedLogoutPage() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		// a generated 'logout' page
		Frame page = assertHasFrame(session, "logout");
		assertGenerated(page);

		Frame target = assertHasFrame(root, "Home");
		assertNotGenerated(target);

		EventTrigger access = page.getOnAccess();
		assertHasNoWiresFromTo(session, access, target);
		{
			ActionEdge nav = assertHasNavigateAction(session, access, target);
			assertGenerated(nav);
		}
	}

	/**
	 * The session should only have one ApplicationElementProperty.
	 *
	 * @throws Exception
	 */
	public void testSessionProperties() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		Property my = assertHasProperty(session, "my login key");
		assertNotGenerated(my);

		// shouldn't be generated
		assertHasNoProperty(session, "current login key");

		// there should only be one
		assertHasOne(session, "iaml:properties", Property.class);

		// there should be a SetWire from the LoginHandler to this
		LoginHandler loginHandler = assertHasLoginHandler(session, "Login Handler");
		assertNotGenerated(loginHandler);

		assertHasSetWire(session, loginHandler, my, "set");
	}

	/**
	 * A default login page should be created.
	 *
	 * @throws Exception
	 */
	public void testLoginPage() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session loginSession = assertHasSession(root, "Login Handler login");
		Frame login = assertHasFrame(loginSession, "login");
		assertGenerated(login);

		// it should contain a form
		InputForm form = assertHasInputForm(login, "login form");
		assertGenerated(form);

		// with a "login key" property
		InputTextField field = assertHasInputTextField(form, "login key");
		assertGenerated(field);

		// there should be a login button
		Button button = assertHasButton(form, "Login");
		assertGenerated(button);

		// a generated operation will handle the login
		Operation op = assertHasOperation(loginSession, "do login");
		assertGenerated(op);

		// button has an 'onClick' run wire
		assertHasWiresFromTo(0, root, button, op);
		ActionEdge run = assertHasRunAction(root, button, op);
		assertGenerated(run);
		assertEquals("onClick", run.getName());

		// the text field has a parameter
		Property prop = assertHasFieldValue(field);
		assertGenerated(prop);

		// connecting to the run wire
		assertGenerated(getParameterEdgeFromTo(root, prop, run));

	}

	/**
	 * The 'check key' operation should have a fail wire that
	 * navigates to the login page.
	 *
	 * @throws Exception
	 */
	public void testCheckInstanceFailWire() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		CompositeOperation check = assertHasCompositeOperation(session, "check key");
		assertGenerated(check);

		Session loginSession = assertHasSession(root, "Login Handler login");
		assertGenerated(loginSession);

		// destination page
		Frame login = assertHasFrame(loginSession, "login");
		{
			assertHasNoWiresFromTo(root, check, login);
			ActionEdge wire = assertHasNavigateAction(root, check, login, "fail");
			assertGenerated(wire);
		}

	}

	/**
	 * There should not be a 'check instance' operation generated in
	 * the session, since we are a LoginHandler[type=key].
	 *
	 * @throws Exception
	 */
	public void testNoCheckInstanceOperation() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		List<?> nodes = query(session, "iaml:operations[iaml:name='check instance']");
		assertEquals(0, nodes.size());

	}

}
