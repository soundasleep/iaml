/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;
import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test case for inference of login handler[type=domain object]
 *
 * @author jmwright
 *
 */
public class LoginHandlerInstance extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(LoginHandlerInstance.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);
		DomainStore store = assertHasDomainStore(root, "Users");
		assertNotGenerated(store);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		DomainObject obj = assertHasDomainObject(store, "User");
		assertNotGenerated(obj);

		DomainAttribute password = assertHasDomainAttribute(obj, "password");
		assertNotGenerated(password);

		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.DOMAIN_OBJECT);
		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");
		assertNotGenerated(instance);

		// only one attribute
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(instance, "name");
		assertNotGenerated(aname);
		assertHasNone(instance, "iaml:attributes[iaml:name='password']");

		// no pages have been created yet
		assertHasNone(root, "iaml:children[iaml:name='Login Successful']");
		assertHasNone(session, "iaml:children[iaml:name='Logout Successful']");

	}

	/**
	 * Test the generated navigate wires.
	 *
	 * @throws Exception
	 */
	public void testInferredWires() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");
		LoginHandler handler = assertHasLoginHandler(session, "login handler");

		// there should be new pages created
		Frame login = assertHasFrame(session, "Login Successful");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, handler, login);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("login", wire.getName());
			assertGenerated(wire);
		}

		Frame logout = assertHasFrame(root, "Logout Successful");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, handler, logout);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("logout", wire.getName());
			assertGenerated(wire);
		}

	}

	/**
	 * Test the attributes inference.
	 *
	 * @throws Exception
	 */
	public void testInferredAttributes() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");

		// the domain instance should contain all attributes
		DomainAttributeInstance apassword = assertHasDomainAttributeInstance(instance, "password");
		assertGenerated(apassword);
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(instance, "name");
		assertNotGenerated(aname);

		// the instance should also contain an 'exists?' operation
		Operation exists = assertHasOperation(instance, "exists?");
		assertGenerated(exists);

	}

	/**
	 * Test the generation of the select wire.
	 *
	 * @throws Exception
	 */
	public void testInferredSelect() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		DomainStore store = assertHasDomainStore(root, "Users");
		Session session = assertHasSession(root, "my session");

		DomainObject obj = assertHasDomainObject(store, "User");

		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");

		// there should be a select wire from the object to the instance
		Set<WireEdge> wires = assertHasWiresFromTo(1, root, obj, instance);
		SelectWire select = (SelectWire) wires.iterator().next();
		assertEquals("password = :password", select.getQuery());
		assertGenerated(select);

		// the login handler should have generated a key store
		ApplicationElementProperty currentPassword = assertHasApplicationElementProperty(session, "current password");
		assertGenerated(currentPassword);

		// thuis key must have a default value set
		assertNotNull(currentPassword.getDefaultValue());
		assertEquals("", currentPassword.getDefaultValue());


		// each key should be connected to the select
		{
			Set<WireEdge> wires2 = assertHasWiresFromTo(1, handler, currentPassword, select);
			ParameterWire param = (ParameterWire) wires2.iterator().next();
			assertGenerated(param);
		}

		// there should be a set wire connecting to the generated property
		{
			Set<WireEdge> wires2 = assertHasWiresFromTo(1, handler, handler, currentPassword);
			SetWire set = (SetWire) wires2.iterator().next();
			assertGenerated(set);
		}

	}


	/**
	 * A default logout page should be created; this will
	 * redirect to the actual logout page in our case.
	 *
	 * @throws Exception
	 */
	public void testGeneratedLogoutPage() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		// a generated 'logout' page
		Frame page = assertHasFrame(session, "logout");
		assertGenerated(page);

		// this page will have been generated
		Frame target = assertHasFrame(root, "Logout Successful");
		assertGenerated(target);

		EventTrigger access = assertHasEventTrigger(page, "access");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, target);
		{
			NavigateWire nav = (NavigateWire) w.iterator().next();
			assertGenerated(nav);
		}
	}

	/**
	 * Test the generation of event triggers and check operations.
	 *
	 * @throws Exception
	 */
	public void testChecks() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		Frame dest = assertHasFrame(session, "current user");
		assertNotGenerated(dest);

		Operation check = assertHasOperation(session, "check instance");
		EventTrigger access = assertHasEventTrigger(dest, "access");
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
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		Frame logout = assertHasFrame(root, "Logout Successful");
		assertGenerated(logout);

		Operation op = assertHasOperation(session, "do logout");
		EventTrigger access = assertHasEventTrigger(logout, "access");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, op);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
			assertGenerated(run);
		}

	}

	/**
	 * A default login page should be created.
	 *
	 * @throws Exception
	 */
	public void testLoginPage() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		Frame login = assertHasFrame(root, "login");
		assertGenerated(login);

		// it should contain a form
		InputForm form = assertHasInputForm(login, "login form");
		assertGenerated(form);

		// with a password field
		InputTextField field = assertHasInputTextField(form, "password");
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
	 * Check that the 'check instance' operation actually contains
	 * some nodes.
	 *
	 * @throws Exception
	 */
	public void testCheckInstanceOperation() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		CompositeOperation check = assertHasCompositeOperation(session, "check instance");
		assertGenerated(check);

		List<?> nodes = query(check, "iaml:nodes");
		assertNotEqual("'check instance' operation should have been generated", 0, nodes.size());

		// at least two nodes
		assertGreaterEq(2, nodes.size());

	}

	/**
	 * Helper method - the given operation should use an operation
	 * call node to call the 'exists?' operation
	 *
	 * @throws Exception
	 */
	private void checkOperationCallsExists(Session session, Operation check) throws Exception {
		// find 'exists?'
		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");
		assertNotGenerated(instance);
		Operation exists = assertHasOperation(instance, "exists?");
		assertGenerated(exists);

		OperationCallNode call = null;
		List<?> nodes = query(check, "iaml:nodes");
		for (Object o : nodes) {
			if (o instanceof OperationCallNode) {
				call = (OperationCallNode) o;
			}
		}

		assertNotNull(call);
		assertGenerated(call);

		// should have a RunInstanceWire to the 'exists?' operation
		Set<WireEdge> w = assertHasWiresFromTo(1, session, call, exists);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
			assertGenerated(run);
		}
	}

	/**
	 * The 'check key' operation should use an operation call node
	 * to call the 'exists?' operation
	 *
	 * @throws Exception
	 */
	public void testCheckInstanceOperationCall() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		// find 'check instance'
		CompositeOperation check = assertHasCompositeOperation(session, "check instance");
		assertGenerated(check);

		// check
		checkOperationCallsExists(session, check);
	}

	/**
	 * The 'do login' operation should use an operation call node
	 * to call the 'exists?' operation
	 *
	 * @throws Exception
	 */
	public void testDoLoginOperationCall() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		// find 'do login'
		Operation op = assertHasOperation(session, "do login");
		assertGenerated(op);

		// check
		checkOperationCallsExists(session, op);
	}

	/**
	 * The 'check instance' operation should have a fail wire that
	 * navigates to the login page.
	 *
	 * @throws Exception
	 */
	public void testCheckInstanceFailWire() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		CompositeOperation check = assertHasCompositeOperation(session, "check instance");
		assertGenerated(check);

		// destination page
		Frame login = assertHasFrame(root, "login");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, check, login);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("fail", wire.getName());
			assertGenerated(wire);
		}

	}

	/**
	 * There should not be a 'check key' operation generated in
	 * the session, since we are a LoginHandler[type=instance].
	 *
	 * @throws Exception
	 */
	public void testNoCheckKeyOperation() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		List<?> nodes = query(session, "iaml:operations[iaml:name='check key']");
		assertEquals(0, nodes.size());

	}


}
