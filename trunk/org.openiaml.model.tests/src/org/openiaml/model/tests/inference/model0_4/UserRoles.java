/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.CompositeOperation;
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
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.RunInstanceWire;
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
	 * Since there is an Access Control in the session, the 'access'
	 * event on the target page should connect to a 'check permissions'
	 * operation owned by the access control.
	 *
	 * @throws Exception
	 */
	public void testGeneratedAccessEvent() throws Exception {
		root = loadAndInfer(UserRoles.class);

		Session session = assertHasSession(root, "target session");
		Page target = assertHasPage(session, "target");
		
		// access event
		EventTrigger event = assertHasEventTrigger(target, "access");
		assertGenerated(event);
		
		// check permissions operation contained in the page
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
		
		// find 'check user'
		CompositeOperation check = assertHasCompositeOperation(session, "check user");
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
		Page target = assertHasPage(session, "target");
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		
		// the actual 'check permissions' operation in the ACH
		Operation targetOp = assertHasOperation(ach, "check permissions");
		assertGenerated(targetOp);
		
		// access event
		EventTrigger event = assertHasEventTrigger(target, "access");
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

}
