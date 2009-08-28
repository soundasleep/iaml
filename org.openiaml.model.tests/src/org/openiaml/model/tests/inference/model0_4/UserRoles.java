/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ExtendsWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Inference of access control handlers.
 *
 * @author jmwright
 *
 */
public class UserRoles extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserRoles.class);

		Page page = assertHasPage(root, "Home");
		assertNotGenerated(page);
		UserStore store = assertHasUserStore(root, "user store");
		assertNotGenerated(store);
		Role role = assertHasRole(store, "default role");
		assertNotGenerated(role);
		assertHasNone(role, "iaml:attributes");
		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);
		
		Page target = assertHasPage(session, "target");
		assertNotGenerated(target);
		
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		RequiresWire requires = assertHasRequiresWire(session, ach, role);
		assertNotGenerated(requires);

		// there shouldn't be a login handler on this page
		assertHasNoLoginHandler(session, "role-based login handler");
		
		// or a logout page
		assertHasNoPage(session, "logout");
		
		// or a login page in the root
		assertHasNoPage(root, "login");
		
		// no operations in the ach or session
		assertHasNone(ach, "iaml:operations");
		assertHasNone(session, "iaml:operations");
		
		// or events in the target page
		assertHasNone(target, "iaml:eventTriggers");
	}
	
	/**
	 * There should be a default role 'Guest' generated
	 * in the user store.
	 *
	 * @throws Exception
	 */
	public void testDefaultGuestRole() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");
		
		Role guest = assertHasRole(store, "Guest");
		assertGenerated(guest);
		
	}
	
	/**
	 * Since there is an Access Control in the session, and no login handler,
	 * a login handler for the session should be created.
	 *
	 * @throws Exception
	 */
	public void testGeneratedLoginHandler() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");

		// the session should have a generated login handler
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");
		assertGenerated(handler);
		
		// the login handler should be of type 'user'
		assertEquals(handler.getType(), LoginHandlerTypes.USER);
		
		// so there should be a login page
		Page login = assertHasPage(root, "login");
		assertGenerated(login);
		
		// and a logout page
		Page logout = assertHasPage(session, "logout");
		assertGenerated(logout);
		
	}

	/**
	 * The Login Handler[type=user] should generate a 
	 * UserInstance in the session. 
	 *
	 * @throws Exception
	 */
	public void testHandlerGeneratedUserInstance() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");
		
		// user instance
		UserInstance instance = assertHasUserInstance(session, "current instance");
		assertGenerated(instance);
		
		// a Set wire: [handler] --> [instance]
		SetWire set = assertHasSetWire(session, handler, instance, "set");
		assertGenerated(set);
		
		// this user instance should have an 'exists?' operation
		Operation exists = assertHasOperation(instance, "exists?");
		assertGenerated(exists);
		
	}

	/**
	 * The Login Handler[type=user] should have an incoming
	 * parameter of 'Guest' - the default role/user instance
	 * to generate.
	 *
	 * @throws Exception
	 */
	public void testHandlerGeneratedGuestParameter() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");		
		UserStore store = assertHasUserStore(root, "user store");
		
		Role guest = assertHasRole(store, "Guest");
		assertGenerated(guest);
		
		// a Parameter wire: [guest] --> [handler]
		ParameterWire param = assertHasParameterWire(session, guest, handler);
		assertGenerated(param);
		
	}
	
	/**
	 * The Login Handler[type=user] should generate a 
	 * "check instance" operation 
	 *
	 * @throws Exception
	 */
	public void testHandlerGeneratedCheckInstance() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");
		assertGenerated(handler);
		
		CompositeOperation check = assertHasCompositeOperation(session, "check instance");
		assertGenerated(check);
	}
	
	/**
	 * <p>Since there is an Access Control in the session, the 'access'
	 * event on the target session should connect to a 'check permissions'
	 * operation owned by the access control.</p>
	 * 
	 * <p>TODO Write up semantics: The events/operations are stored as part of the page, instead
	 * of the session, to allow for easy extensibility. For example,
	 * particular pages could redirect to different targets when the
	 * authentication check fails, or we could extend only certain
	 * pages with additional check constraints.</p>
	 *
	 * @throws Exception
	 */
	public void testGeneratedAccessEventSession() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		Page target = assertHasPage(session, "target");
		
		// access event in the session
		EventTrigger event = assertHasEventTrigger(target, "access");
		assertGenerated(event);
		
		// check permissions operation contained in the session, not the page
		CompositeOperation pageOp = assertHasCompositeOperation(target, "permissions check");
		assertGenerated(pageOp);
		
		// connected
		RunInstanceWire run = assertHasRunInstanceWire(target, event, pageOp, "run");
		assertGenerated(run);
		
		// a failure wire connecting the op to the login page
		Page login = assertHasPage(root, "login");
		NavigateWire fail = assertHasNavigateWire(root, pageOp, login, "fail");
		assertGenerated(fail);
		
	}
	
	/**
	 * There should not be a 'permissions check' in the target session;
	 * this is part of the page.
	 *
	 * @throws Exception
	 */
	public void testGeneratedAccessEventNotPage() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		Page target = assertHasPage(session, "target");
		
		// access event in the page
		EventTrigger event = assertHasEventTrigger(session, "access");
		assertGenerated(event);
		
		// check permissions operation contained in the session, not the page
		assertHasNone(session, "iaml:operations[iaml:name='permissions check']");

		CompositeOperation sessionOp = assertHasCompositeOperation(target, "permissions check");
		assertGenerated(sessionOp);

		// the page's event trigger should not connect to the actual permissions check in the session
		assertHasNoWiresFromTo(session, event, sessionOp);
		
	}
	
	/**
	 * Even though the ACH adds a run wire from 'access' to 'check permissions',
	 * there should still be a 'check user' operation as well.
	 * (i.e. an event trigger can have two outgoing run wires.)
	 *
	 * @throws Exception
	 */
	public void testAccessEventHasCheckUserOperation() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		Page target = assertHasPage(session, "target");
		
		// find 'check instance'
		CompositeOperation check = assertHasCompositeOperation(session, "check instance");
		assertGenerated(check);
		
		// access event
		EventTrigger event = assertHasEventTrigger(target, "access");
		assertGenerated(event);
		
		// connected
		RunInstanceWire run = assertHasRunInstanceWire(target, event, check, "run");
		assertGenerated(run);
		
		// a failure wire connecting the op to the login page
		Page login = assertHasPage(root, "login");
		NavigateWire fail = assertHasNavigateWire(root, check, login, "fail");
		assertGenerated(fail);
		
	}
	
	/**
	 * Check the generated contents of the 'permission check' operation
	 * in the target page.
	 *
	 * @throws Exception
	 */
	public void testGeneratedPermissionCheckOperationContents() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		Page target = assertHasPage(session, "target");
		
		// the actual 'check permissions' operation in the ACH
		Operation targetOp = assertHasOperation(ach, "check permissions");
		assertGenerated(targetOp);
		
		// access event
		EventTrigger event = assertHasEventTrigger(session, "access");
		assertGenerated(event);
		
		// check permissions operation contained in the page
		CompositeOperation op = assertHasCompositeOperation(target, "permissions check");
		assertGenerated(op);
		
		StartNode start = assertHasStartNode(op);
		assertGenerated(start);
		FinishNode end = assertHasFinishNode(op);
		assertGenerated(end);
		CancelNode cancel = assertHasCancelNode(op);
		assertGenerated(cancel);
		OperationCallNode virtualOp = assertHasOperationCallNode(op, "call permissions operation");
		assertGenerated(virtualOp);
		RunInstanceWire virtualRun = assertHasRunInstanceWire(op, virtualOp, targetOp, "run");
		assertGenerated(virtualRun);
		
		// execution edges between all the operations
		assertGenerated(assertHasExecutionEdge(op, start, virtualOp));
		assertGenerated(assertHasExecutionEdge(op, virtualOp, end));
		assertGenerated(assertHasExecutionEdge(op, virtualOp, cancel));

	}

	/**
	 * The AccessControlHandler should have an incoming UserInstance
	 * as a parameter.
	 *
	 * @throws Exception
	 */
	public void testHasIncomingUserInstance() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		UserInstance instance = assertHasUserInstance(session, "current instance");
		
		ParameterWire param = assertHasParameterWire(session, instance, ach);
		assertGenerated(param);
		
	}
	
	/**
	 * The 'default role' should extend 'Guest'
	 *
	 * @throws Exception
	 */
	public void testDefaultRoleExtendsGuest() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "Guest");
		assertGenerated(guest);
		Role role = assertHasRole(store, "default role");
		assertNotGenerated(role);
		
		ExtendsWire ext = assertHasExtendsWire(store, role, guest);
		assertGenerated(ext);
		
	}
	
	/**
	 * 'Guest' should have 'email' and 'password' attributes; these
	 * are the default requirements for Users
	 *
	 * @throws Exception
	 */
	public void testGuestAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "Guest");

		DomainAttribute email = assertHasDomainAttribute(guest, "email");
		assertGenerated(email);
		DomainAttribute password = assertHasDomainAttribute(guest, "password");
		assertGenerated(password);
		
	}
	
	/**
	 * Since Default Role extends Guest, the attributes of Guest
	 * will be reproduced in Default Role.
	 * 
	 * @throws Exception
	 */
	public void testInheritanceOfAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "Guest");

		DomainAttribute email = assertHasDomainAttribute(guest, "email");
		assertGenerated(email);
		DomainAttribute password = assertHasDomainAttribute(guest, "password");
		assertGenerated(password);
		
		Role role = assertHasRole(store, "default role");

		DomainAttribute email2 = assertHasDomainAttribute(role, "email");
		assertGenerated(email2);
		DomainAttribute password2 = assertHasDomainAttribute(role, "password");
		assertGenerated(password2);
		
		// there should be extends wires between each attribute
		assertGenerated(assertHasExtendsWire(store, email2, email));
		assertGenerated(assertHasExtendsWire(store, password2, password));
		
		// none the other way around
		assertHasNoWiresFromTo(store, email, email2);
		assertHasNoWiresFromTo(store, password, password2);
		assertHasNoWiresFromTo(store, email, email);
		assertHasNoWiresFromTo(store, email, password);
		assertHasNoWiresFromTo(store, password2, email);
		
	}
	
	/**
	 * The generated login form on the generated 'login' page 
	 * should have 'email' and 'password' fields.
	 *
	 * @throws Exception
	 */
	public void testLoginFormAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Page login = assertHasPage(root, "login");
		assertGenerated(login);
		
		InputForm form = assertHasInputForm(login, "login form");
		assertGenerated(form);
		
		InputTextField email = assertHasInputTextField(form, "email");
		assertGenerated(email);
		InputTextField password = assertHasInputTextField(form, "password");
		assertGenerated(password);
		
	}
	
	/**
	 * The session should have 'email' and 'password' properties.
	 *
	 * @throws Exception
	 */
	public void testSessionProperties() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);
		
		ApplicationElementProperty email = assertHasApplicationElementProperty(session, "current email");
		assertGenerated(email);
		ApplicationElementProperty password = assertHasApplicationElementProperty(session, "current password");
		assertGenerated(password);
		
	}
	
	/**
	 * The UserInstance should have a SelectWire from the given Role
	 * with the current property values as parameters.
	 *
	 * @throws Exception
	 */
	public void testUserInstanceSelectWires() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);
		
		ApplicationElementProperty email = assertHasApplicationElementProperty(session, "current email");
		ApplicationElementProperty password = assertHasApplicationElementProperty(session, "current password");
		
		// user instance
		UserInstance instance = assertHasUserInstance(session, "current instance");
		
		// store
		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "Guest");
		
		// generated select wire		
		SelectWire selectWire = assertHasSelectWire(root, guest, instance, "select");
		assertGenerated(selectWire);
		
		// the query should be one of these values
		assertEqualsOneOf(new String[]{
				"email = :email and password = :password",
				"password = :password and email = :email"
		}, selectWire.getQuery());
		
		// parameters
		assertHasParameterWire(root, email, selectWire);
		assertHasParameterWire(root, password, selectWire);
		
	}
	
}
