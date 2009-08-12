/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;
import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test case for inference of login handler[type=secret key]
 *
 * @author jmwright
 *
 */
public class LoginHandlerKey extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(LoginHandlerKey.class);

		Page page = assertHasPage(root, "Home");
		assertNotGenerated(page);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		StaticValue value = assertHasStaticValue(session, "login key");
		assertNotGenerated(value);

		Page viewkey = assertHasPage(session, "viewkey");
		assertNotGenerated(viewkey);

		LoginHandler handler = assertHasLoginHandler(session, "Login Handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.SECRET_KEY);

		// stored key
		ApplicationElementProperty key = assertHasApplicationElementProperty(session, "my login key");
		assertNotGenerated(key);
		// the key must have a default
		assertNotNull(key.getDefaultValue());
		assertEquals("", key.getDefaultValue());

		// handler logout--> home
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, handler, handler, page);
			NavigateWire nav = (NavigateWire) wires.iterator().next();
			assertNotGenerated(nav);
			assertEquals("logout", nav.getName());
		}

		// handler login--> viewkey
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, handler, handler, viewkey);
			NavigateWire nav = (NavigateWire) wires.iterator().next();
			assertNotGenerated(nav);
			assertEquals("login", nav.getName());
		}

		// handler set--> key
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, handler, handler, key);
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

		Page viewkey = assertHasPage(session, "viewkey");
		assertNotGenerated(viewkey);

		Operation check = assertHasOperation(session, "check key");
		EventTrigger access = assertHasEventTrigger(viewkey, "access");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, check);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
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

		Page page = assertHasPage(root, "Home");
		assertNotGenerated(page);

		Operation op = assertHasOperation(session, "do logout");
		EventTrigger access = assertHasEventTrigger(page, "access");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, op);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
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
		Page page = assertHasPage(session, "logout");
		assertGenerated(page);

		Page target = assertHasPage(root, "Home");
		assertNotGenerated(target);

		EventTrigger access = assertHasEventTrigger(page, "access");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, target);
		{
			NavigateWire nav = (NavigateWire) w.iterator().next();
			assertGenerated(nav);
		}
	}

	/**
	 * A default login page should be created.
	 *
	 * @throws Exception
	 */
	public void testLoginPage() throws Exception {
		root = loadAndInfer(LoginHandlerKey.class);

		Session session = assertHasSession(root, "my session");

		Page login = assertHasPage(root, "login");
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
		Operation op = assertHasOperation(session, "do login");
		assertGenerated(op);

		// button has an 'onClick' run wire
		Set<WireEdge> w = assertHasWiresFromTo(1, root, button, op);
		RunInstanceWire run = (RunInstanceWire) w.iterator().next();
		assertGenerated(run);
		assertEquals("onClick", run.getName());

		// the text field has a parameter
		ApplicationElementProperty prop = assertHasApplicationElementProperty(field, "fieldValue");
		assertGenerated(prop);

		// connecting to the run wire
		Set<WireEdge> w2 = assertHasWiresFromTo(1, root, prop, run);
		ParameterWire param = (ParameterWire) w2.iterator().next();
		assertGenerated(param);

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

		// destination page
		Page login = assertHasPage(root, "login");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, check, login);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("fail", wire.getName());
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
