/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.EXSDDataType;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RequiresEdge;
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
		Role role = assertHasRole(root, "default role");
		assertNotGenerated(role);
		assertHasNone(role, "iaml:attributes");
		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);

		Frame target = assertHasFrame(session, "target");
		assertNotGenerated(target);

		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		RequiresEdge requires = assertHasRequiresEdge(session, ach, role);
		assertNotGenerated(requires);

		// there shouldn't be a login handler on this page
		assertHasNoLoginHandler(session, "role-based login handler");

		// or a logout page
		assertHasNoFrame(session, "logout");

		// or a login page in the root
		assertHasNoFrame(root, "login");

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

		Role guest = assertHasRole(root, "User");
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
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");
		assertGenerated(handler);

		// the login handler should be of type 'user'
		assertEquals(handler.getType(), LoginHandlerTypes.USER);

		// so there should be a login page
		Session loginSession = assertHasSession(root, "role-based login handler for target session login");
		assertGenerated(loginSession);
		Frame login = assertHasFrame(loginSession, "login");
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
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");

		// user instance
		DomainIterator instance = assertHasDomainIterator(session, "current instance");
		assertGenerated(instance);

		// a Set wire: [handler] --> [instance]
		SetWire set = assertHasSetWire(session, handler, instance, "set");
		assertGenerated(set);

		// this user instance should have an 'empty' PrimitiveCondition
		PrimitiveCondition exists = (PrimitiveCondition) instance.getEmpty();
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
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");

		Role user = assertHasRole(root, "User");
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
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");
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
		EventTrigger event = target.getOnAccess();
		assertGenerated(event);

		// check permissions operation contained in the session, not the page
		CompositeOperation pageOp = assertHasCompositeOperation(target, "permissions check");
		assertGenerated(pageOp);

		// connected
		ActionEdge run = assertHasRunAction(target, event, pageOp, "run");
		assertGenerated(run);

		// a failure wire connecting the op to the login page
		Session loginSession = assertHasSession(root, "role-based login handler for target session login");
		Frame login = assertHasFrame(loginSession, "login");
		ActionEdge fail = assertHasNavigateAction(root, pageOp, login, "fail");
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
		EventTrigger event = session.getOnAccess();
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
		EventTrigger event = target.getOnAccess();
		assertGenerated(event);

		// connected
		ActionEdge run = assertHasRunAction(target, event, check, "run");
		assertGenerated(run);

		// a failure wire connecting the op to the login page
		Session loginSession = assertHasSession(root, "role-based login handler for target session login");
		Frame login = assertHasFrame(loginSession, "login");
		ActionEdge fail = assertHasNavigateAction(root, check, login, "fail");
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
		EventTrigger event = session.getOnAccess();
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
		ActionEdge virtualRun = assertHasRunAction(op, virtualOp, targetOp, "run");
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
		DomainIterator instance = assertHasDomainIterator(session, "current instance");

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

		Role guest = assertHasRole(root, "User");
		assertGenerated(guest);
		Role role = assertHasRole(root, "default role");
		assertNotGenerated(role);

		ExtendsEdge ext = assertHasExtendsEdge(root, role, guest);
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

		Role guest = assertHasRole(root, "User");

		DomainAttribute email = assertHasDomainAttribute(guest, "email");
		assertGenerated(email);
		DomainAttribute password = assertHasDomainAttribute(guest, "password");
		assertGenerated(password);
		
		// check the types
		assertEqualType(BuiltinDataTypes.getTypeEmail(), ((EXSDDataType) email.getType()).getDefinition());
		// TODO password needs to have iamlPassword data type

	}

	/**
	 * Since Default Role extends User, the attributes of User
	 * will be reproduced in Default Role.
	 *
	 * @throws Exception
	 */
	public void testInheritanceOfAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Role guest = assertHasRole(root, "User");

		DomainAttribute email = assertHasDomainAttribute(guest, "email");
		assertGenerated(email);
		DomainAttribute password = assertHasDomainAttribute(guest, "password");
		assertGenerated(password);

		Role role = assertHasRole(root, "default role");

		DomainAttribute email2 = assertHasDomainAttribute(role, "email");
		assertGenerated(email2);
		DomainAttribute password2 = assertHasDomainAttribute(role, "password");
		assertGenerated(password2);

		// there should be extends wires between each attribute
		assertGenerated(assertHasExtendsEdge(root, email2, email));
		assertGenerated(assertHasExtendsEdge(root, password2, password));

		// none the other way around
		assertHasNoWiresFromTo(root, email, email2);
		assertHasNoWiresFromTo(root, password, password2);
		assertHasNoWiresFromTo(root, email, email);
		assertHasNoWiresFromTo(root, email, password);
		assertHasNoWiresFromTo(root, password2, email);

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

		Role guest = assertHasRole(root, "User");

		DomainAttribute source_id = assertHasDomainAttribute(guest, "generated primary key");
		assertGenerated(source_id);

		Role role = assertHasRole(root, "default role");

		DomainAttribute id = assertHasDomainAttribute(role, "generated primary key");
		assertGenerated(id);
		DomainAttribute fk = assertHasDomainAttribute(role, "User.generated primary key");
		assertGenerated(fk);

		// there should be an extends wire between the PK and FK
		assertGenerated(assertHasExtendsEdge(root, fk, source_id));

		// and none between the PK and PK
		assertHasNoWiresFromTo(root, id, source_id);
		assertHasNoWiresFromTo(root, source_id, id);
		
		// check the types of the keys
		assertEqualType(BuiltinDataTypes.getTypeInteger(), ((EXSDDataType) id.getType()).getDefinition());
		assertEqualType(id, fk);
		assertEqualType(BuiltinDataTypes.getTypeInteger(), ((EXSDDataType) fk.getType()).getDefinition());

	}

	/**
	 * The generated login form on the generated 'login' page
	 * should have 'email' and 'password' fields.
	 *
	 * @throws Exception
	 */
	public void testLoginFormAttributes() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session loginSession = assertHasSession(root, "role-based login handler for target session login");
		Frame login = assertHasFrame(loginSession, "login");
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

		Property email = assertHasProperty(session, "current email");
		assertGenerated(email);
		Property password = assertHasProperty(session, "current password");
		assertGenerated(password);

		// but not an 'generated primary key'
		assertHasNoProperty(session, "current generated primary key");

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

		Property email = assertHasProperty(session, "current email");
		Property password = assertHasProperty(session, "current password");

		// user instance
		DomainIterator instance = assertHasDomainIterator(session, "current instance");

		// the query should be one of these values
		assertEqualsOneOf(new String[]{
				"email = :email and password = :password",
				"password = :password and email = :email"
		}, instance.getQuery());

		// parameters
		ParameterEdge p1 = assertHasParameterEdge(root, email, instance);
		assertGenerated(p1);
		ParameterEdge p2 = assertHasParameterEdge(root, password, instance);
		assertGenerated(p2);

	}

}
