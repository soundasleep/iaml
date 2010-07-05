/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Issue 206: Allow AccessControlHandlers to specify target Login pages
 * 
 * @author jmwright
 */
public class AccessControlHandlerSpecifyLoginPage extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(AccessControlHandlerSpecifyLoginPage.class);
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Session target = assertHasSession(root, "Login Session");
		Frame login = assertHasFrame(target, "Login Page");
		Session session = assertHasSession(root, "Session");
		AccessControlHandler ach = assertHasAccessControlHandler(session, "Access Control");
		
		// the ACH requires a Role
		Role role = assertHasRole(root, "Target Role");
		assertNotGenerated(assertHasRequiresEdge(root, ach, role));
		
		// the ACH is connected by an ActionEdge to the login page
		assertNotGenerated(assertHasActionEdge(root, ach, login, "login"));
		
		assertNotGenerated(target, login, session, ach, role);
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testLoginHandler() throws Exception {
		
		Session target = assertHasSession(root, "Login Session");
		Frame login = assertHasFrame(target, "Login Page");
		Session session = assertHasSession(root, "Session");
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler");
		
		// the handler will have an outgoing ActionEdge
		assertGenerated(assertHasActionEdge(root, handler, login, "login"));
		
		// there is only two outgoing ActionEdges; login and logout
		List<String> actions = new ArrayList<String>();
		for (ActionEdge e : login.getOutActions()) {
			actions.add(e.getName());
		}
			
		assertCollectionEquals(actions, "login", "logout");
	}
	
}
