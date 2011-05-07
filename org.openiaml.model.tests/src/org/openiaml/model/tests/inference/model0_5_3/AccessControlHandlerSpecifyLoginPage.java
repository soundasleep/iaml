/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Issue 206: Allow AccessControlHandlers to specify target Login pages
 *
 * @author jmwright
 */
public class AccessControlHandlerSpecifyLoginPage extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(getInferenceClass());
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

		// the ACH is connected by an ECARule to the login page
		assertNotGenerated(assertHasECARule(root, ach, login, "login"));

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
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for Session");

		// the LoginHandler will have an outgoing ECARule to denote the login page
		assertGenerated(assertHasECARule(root, handler, login, "login"));

		// there is only three outgoing ActionEdges from the LoginHandler
		List<String> actions = new ArrayList<String>();
		for (ECARule e : handler.getOutActions()) {
			actions.add(e.getName());
		}

		assertCollectionEquals(actions, "login", "logout", "success");
	}

	/**
	 * The targeted Login page is completed as normal, even though
	 * it is empty.
	 *
	 * @throws Exception
	 */
	public void testLoginPage() throws Exception {

		Session target = assertHasSession(root, "Login Session");
		Frame login = assertHasFrame(target, "Login Page");

		// should have an InputForm
		assertGenerated(assertHasInputForm(login, "login form"));

	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}

}
