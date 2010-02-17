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
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterEdge;
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

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);
		UserStore store = assertHasUserStore(root, "user store");
		assertNotGenerated(store);
		Role role = assertHasRole(store, "default role");
		assertNotGenerated(role);
		assertHasNone(role, "iaml:attributes");
		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);
		
		Frame target = assertHasFrame(session, "target");
		assertNotGenerated(target);
		
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		RequiresWire requires = assertHasRequiresWire(session, ach, role);
		assertNotGenerated(requires);

		// there shouldn't be a login handler on this page
		assertHasNoLoginHandler(session, "role-based login handler");
		
		// or a logout page
		assertHasNoFrame(session, "logout");
		
		// or a login page in the root
		assertHasNoPage(root, "login");
		
		// no operations in the ach or session
		assertHasNone(ach, "iaml:operations");
		assertHasNone(session, "iaml:operations");
		
		// or events in the target page
		assertHasNone(target, "iaml:eventTriggers");
	}
	
	/**
	 * There should be a default role 'User' generated
	 * in the user store.
	 *
	 * @throws Exception
	 */
	public void testDefaultUserRole() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");
		
		Role guest = assertHasRole(store, "User");
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
		Frame login = assertHasFrame(root, "login");
		assertGenerated(login);
		
		// and a logout page
		Frame logout = assertHasFrame(session, "logout");
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
	 * parameter of 'User' - the default role/user instance
	 * to generate.
	 *
	 * @throws Exception
	 */
	public void testHandlerGeneratedUserParameter() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");		
		UserStore store = assertHasUserStore(root, "user store");
		
		Role user = assertHasRole(store, "User");
		assertGenerated(user);
		
		// a Parameter wire: [guest] --> [handler]
		ParameterEdge param = assertHasParameterEdge(session, user, handler);
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
		Frame target = assertHasFrame(session, "target");
		
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
		Frame login = assertHasFrame(root, "login");
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
		Frame target = assertHasFrame(session, "target");
		
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
		Frame target = assertHasFrame(session, "target");
		
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
		Frame login = assertHasFrame(root, "login");
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
		Frame target = assertHasFrame(session, "target");
		
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
		
		ParameterEdge param = assertHasParameterEdge(session, instance, ach);
		assertGenerated(param);
		
	}
	
	/**
	 * The 'default role' should extend 'User'
	 *
	 * @throws Exception
	 */
	public void testDefaultRoleExtendsGuest() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "User");
		assertGenerated(guest);
		Role role = assertHasRole(store, "default role");
		assertNotGenerated(role);
		
		ExtendsEdge ext = assertHasExtendsEdge(store, role, guest);
		assertGenerated(ext);
		
	}
	
	/**
	 * 'User' should have 'email' and 'password' attributes; these
	 * are the default requirements for Users
	 *
	 * @throws Exception
	 */
	public void testGuestAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "User");

		DomainAttribute email = assertHasDomainAttribute(guest, "email");
		assertGenerated(email);
		DomainAttribute password = assertHasDomainAttribute(guest, "password");
		assertGenerated(password);
		
	}
	
	/**
	 * Since Default Role extends User, the attributes of User
	 * will be reproduced in Default Role.
	 * 
	 * @throws Exception
	 */
	public void testInheritanceOfAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "User");

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
		assertGenerated(assertHasExtendsEdge(store, email2, email));
		assertGenerated(assertHasExtendsEdge(store, password2, password));
		
		// none the other way around
		assertHasNoWiresFromTo(store, email, email2);
		assertHasNoWiresFromTo(store, password, password2);
		assertHasNoWiresFromTo(store, email, email);
		assertHasNoWiresFromTo(store, email, password);
		assertHasNoWiresFromTo(store, password2, email);
		
	}
	
	/**
	 * Since Default Role extends User, there will be an
	 * ID in Default Role which will be the index/foreign key
	 * of Guest.
	 * 
	 * @throws Exception
	 */
	public void testInheritancePrimaryKeys() throws Exception {
		root = loadAndInfer(UserRoles.class);

		UserStore store = assertHasUserStore(root, "user store");		
		Role guest = assertHasRole(store, "User");
		
		DomainAttribute source_id = assertHasDomainAttribute(guest, "generated primary key");
		assertGenerated(source_id);
		
		Role role = assertHasRole(store, "default role");

		DomainAttribute id = assertHasDomainAttribute(role, "generated primary key");
		assertGenerated(id);
		DomainAttribute fk = assertHasDomainAttribute(role, "User.generated primary key");
		assertGenerated(fk);
		
		// there should be an extends wire between the PK and FK
		assertGenerated(assertHasExtendsEdge(store, fk, source_id));
		
		// and none between the PK and PK
		assertHasNoWiresFromTo(store, id, source_id);
		assertHasNoWiresFromTo(store, source_id, id);
		
	}
	
	/**
	 * The generated login form on the generated 'login' page 
	 * should have 'email' and 'password' fields.
	 *
	 * @throws Exception
	 */
	public void testLoginFormAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Frame login = assertHasFrame(root, "login");
		assertGenerated(login);
		
		InputForm form = assertHasInputForm(login, "login form");
		assertGenerated(form);
		
		InputTextField email = assertHasInputTextField(form, "email");
		assertGenerated(email);
		InputTextField password = assertHasInputTextField(form, "password");
		assertGenerated(password);

		// but not an 'generated primary key'
		assertHasNoInputTextField(form, "generated primary key");

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
		
		// but not an 'generated primary key'
		assertHasNoApplicationElementProperty(session, "current generated primary key");
		
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
		Role user = assertHasRole(store, "User");
		
		// generated select wire		
		SelectWire selectWire = assertHasSelectWire(root, user, instance, "select");
		assertGenerated(selectWire);
		
		// the query should be one of these values
		assertEqualsOneOf(new String[]{
				"email = :email and password = :password",
				"password = :password and email = :email"
		}, selectWire.getQuery());
		
		// parameters
		ParameterEdge p1 = assertHasParameterEdge(root, email, selectWire);
		assertGenerated(p1);
		ParameterEdge p2 = assertHasParameterEdge(root, password, selectWire);
		assertGenerated(p2);
		
	}
	
}
