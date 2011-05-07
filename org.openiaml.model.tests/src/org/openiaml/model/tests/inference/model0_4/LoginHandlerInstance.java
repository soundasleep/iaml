/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;
import java.util.Set;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.SimpleCondition;
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
		DomainSource store = assertHasDomainSource(root, "domain source");
		assertNotGenerated(store);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		DomainSchema obj = assertHasDomainSchema(root, "User");
		assertNotGenerated(obj);

		DomainAttribute password = assertHasDomainAttribute(obj, "password");
		assertNotGenerated(password);

		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.DOMAIN_OBJECT);
		DomainIterator iterator = assertHasDomainIterator(session, "logged in user");
		assertNotGenerated(iterator);
		DomainInstance instance = iterator.getCurrentInstance();
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
			assertHasNoWiresFromTo(root, handler, login);
			ECARule wire = assertHasNavigateAction(root, handler, login, "success");
			assertGenerated(wire);
		}

		Frame logout = assertHasFrame(root, "Logout Successful");
		{
			assertHasNoWiresFromTo(root, handler, logout);
			ECARule wire = assertHasNavigateAction(root, handler, logout, "logout");
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

		DomainIterator iterator = assertHasDomainIterator(session, "logged in user");
		DomainInstance instance = iterator.getCurrentInstance();
		assertNotGenerated(instance);

		// the domain instance should contain all attributes
		DomainAttributeInstance apassword = assertHasDomainAttributeInstance(instance, "password");
		assertGenerated(apassword);
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(instance, "name");
		assertNotGenerated(aname);

		// the instance should also contain an 'empty' PrimitiveCondition
		PrimitiveCondition exists = (PrimitiveCondition) iterator.getEmpty();
		assertGenerated(exists);

	}

	/**
	 * Test the generation of the select wire.
	 *
	 * @throws Exception
	 */
	public void testInferredSelect() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");
		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		DomainIterator instance = assertHasDomainIterator(session, "logged in user");

		// check the query on the instance
		assertEquals("password = :password", instance.getQuery());

		// the login handler should have generated a key store
		Value currentPassword = assertHasValue(session, "current password");
		assertGenerated(currentPassword);

		// thuis key must have a default value set
		assertNotNull(currentPassword.getDefaultValue());
		assertEquals("", currentPassword.getDefaultValue());

		// each key should be connected to the select
		assertGenerated(getParameterFromTo(handler, currentPassword, instance));

		// there should be a set wire connecting to the generated property
		{
			Set<Wire> wires2 = assertHasWiresFromTo(1, handler, handler, currentPassword);
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

		Event access = page.getOnAccess();
		ECARule nav = assertHasNavigateAction(session, access, target);
		assertGenerated(nav);
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
		Event access = dest.getOnAccess();
		{
			// a run action
			ECARule run = assertHasRunAction(session, access, check);
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
		Event access = logout.getOnAccess();
		// no wires
		{
			ECARule run = assertHasRunAction(session, access, op);
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

		Session loginSession = assertHasSession(root, "login handler login");
		assertGenerated(loginSession);

		Frame login = assertHasFrame(loginSession, "login");
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
		Operation op = assertHasOperation(loginSession, "do login");
		assertGenerated(op);

		// button has an 'onClick' run wire
		assertHasWiresFromTo(0, root, button, op);
		ECARule run = assertHasRunAction(root, button, op);
		assertGenerated(run);
		assertEquals("onClick", run.getName());

		// the text field has a parameter
		Value prop = assertHasFieldValue(field);
		assertGenerated(prop);

		// connecting to the run wire
		assertGenerated(assertHasParameter(root, prop, run));

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
	 * call node to call the 'empty' condition
	 *
	 * @throws Exception
	 */
	private void checkOperationCallsExists(Session session, CompositeOperation check) throws Exception {
		// find 'empty'
		DomainIterator instance = assertHasDomainIterator(session, "logged in user");
		assertNotGenerated(instance);
		PrimitiveCondition exists = (PrimitiveCondition) instance.getEmpty();
		assertGenerated(exists);

		// has a DecisionNode
		DecisionNode node = assertHasDecisionNode(check, "true?");
		assertGenerated(node);

		// DecisionNode evaluates the incoming condition
		SimpleCondition edge = assertHasSimpleCondition(session, exists, node);
		assertGenerated(edge);
	}

	/**
	 * The 'check key' operation should use an operation call node
	 * to call the 'empty' operation
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
	 * to call the 'empty' operation
	 *
	 * @throws Exception
	 */
	public void testDoLoginOperationCall() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);

		Session session = assertHasSession(root, "my session");

		// find 'do login'
		Session loginSession = assertHasSession(root, "login handler login");
		CompositeOperation op = (CompositeOperation) assertHasOperation(loginSession, "do login");
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
		Session loginSession = assertHasSession(root, "login handler login");
		Frame login = assertHasFrame(loginSession, "login");
		{
			ECARule wire = assertHasNavigateAction(root, check, login, "fail");
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
