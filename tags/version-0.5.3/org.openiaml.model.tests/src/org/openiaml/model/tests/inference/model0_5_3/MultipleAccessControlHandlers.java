/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * 
 * 
 * @author jmwright
 */
public class MultipleAccessControlHandlers extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(MultipleAccessControlHandlers.class);
	}

	/**
	 * There should only be six sessions (i.e. three created).
	 * 
	 * @throws Exception
	 */
	public void testOnlyThreeSessionsCreated() throws Exception {
		assertEquals(6, typeSelect(root.getScopes(), Session.class).size());
		
		// all the sessions must have unique names
		List<?> children = typeSelect(root.getScopes(), Session.class);
		Set<String> foundNames = new HashSet<String>();
		for (Object obj : children) {
			Session sess = (Session) obj;
			assertFalse(foundNames + " should not contain " + sess.getName(), foundNames.contains(sess.getName()));
			foundNames.add(sess.getName());
		}
		
		assertFalse(foundNames.isEmpty());
	}
	
	/**
	 * Test the contents of the login form for Session A.
	 * 
	 * @throws Exception
	 */
	public void testSessionHasLoginForms() throws Exception {
		for (String sessionName : new String[] {"Session A", "Session B", "Session C"}) {
			Session target = assertHasSession(root, sessionName);
			
			Session loginSession = assertHasSession(root, "role-based login handler for " + target.getName() + " login");
			
			// it has an inputform
			Frame loginFrame = assertHasFrame(loginSession, "login");
			InputForm loginForm = assertHasInputForm(loginFrame, "login form");
			
			// the form should have an 'email' and 'password' field
			InputTextField email = assertHasInputTextField(loginForm, "email");
			InputTextField password = assertHasInputTextField(loginForm, "password");
			
			// and a login button
			Button button = assertHasButton(loginForm, "Login");
			
			// calls 'do login'
			Operation doLogin = assertHasOperation(loginSession, "do login");
			ActionEdge call = assertHasActionEdge(root, button, doLogin, "onClick");
			
			// with parameters
			Property emailValue = assertHasFieldValue(email);
			Property passwordValue = assertHasFieldValue(password);
			
			assertGenerated(assertHasParameterEdge(root, emailValue, call, "email"));
			assertGenerated(assertHasParameterEdge(root, passwordValue, call, "password"));
		}
		
	}
	
	/**
	 * Otherwise, most of this test case is focused on the fact that
	 * the model is valid through the checks.
	 */
	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}
	
}
