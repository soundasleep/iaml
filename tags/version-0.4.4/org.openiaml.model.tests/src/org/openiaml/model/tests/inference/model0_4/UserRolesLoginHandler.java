/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;
import java.util.Set;

import org.openiaml.model.model.Property;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of access control handlers.
 *
 * @author jmwright
 *
 */
public class UserRolesLoginHandler extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return UserRolesLoginHandler.class;
	}
	
	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserRolesLoginHandler.class);

		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);
		LoginHandler handler = assertHasLoginHandler(session, "user login handler");
		assertNotGenerated(handler);
		
		// no user instances
		assertHasNone(session, "iaml:children[iaml:name='current instance']");
		
	}
	
	/**
	 * There should only be one select wire created.
	 * 
	 * @throws Exception
	 */
	public void testSelectWires() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class);

		Session session = assertHasSession(root, "my session");
		
		// generated
		UserInstance ui = assertHasUserInstance(session, "current instance");
		assertGenerated(ui);
		
		// there should only be one incoming select wire
		Set<Wire> wires = getWiresTo(session, ui, SelectWire.class);
		assertEquals(wires.toString(), 1, wires.size());		

	}
	
	/**
	 * The "generated primary key" of the Registered User/User should
	 * not be a parameter in the input form.
	 * 
	 * @throws Exception
	 */
	public void testPrimaryKeyNotInputField() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class);
		
		Frame login = assertHasFrame(root, "login");
		InputForm form = assertHasInputForm(login, "login form");
		
		InputTextField email = assertHasInputTextField(form, "email");
		assertGenerated(email);
		InputTextField password = assertHasInputTextField(form, "password");
		assertGenerated(password);
		assertHasNoInputTextField(form, "generated primary key");
		assertHasNoInputTextField(form, "User.generated primary key");
		
	}
	
	/**
	 * There should only be 'current password' and 'current email'
	 * stored in the session.
	 * 
	 * @throws Exception
	 */
	public void testStoredSessionProperties() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class);
		
		Session session = assertHasSession(root, "my session");
		
		assertGenerated(assertHasProperty(session, "current email"));
		assertGenerated(assertHasProperty(session, "current password"));
		assertHasNoProperty(session, "current generated primary key");
		assertHasNoProperty(session, "current User.generated primary key");
		assertHasNoProperty(session, "current generated_primary_key");
		assertHasNoProperty(session, "current User.generated_primary_key");
		assertHasNoProperty(session, "current User_generated_primary_key");
		
	}
		
	/**
	 * The UserInstance select query should not contain anything
	 * about generated primary keys.
	 * 
	 * @throws Exception
	 */
	public void testUserInstanceSelectWire() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class);
		
		Session session = assertHasSession(root, "my session");
		UserInstance user = assertHasUserInstance(session, "current instance");
		
		SelectWire select = (SelectWire) getWiresTo(session, user, SelectWire.class).iterator().next(); 
		assertEqualsOneOf(new String[] {
				"password = :password and email = :email",
				"email = :email and password = :password"
			}, select.getQuery());
		
		// there should only be two incoming parameter wires
		List<ParameterEdge> params = select.getInParameterEdges();
		assertEquals(params.toString(), 2, params.size());
		
		// one from password
		Property password = assertHasProperty(session, "current password");
		assertGenerated(password);
		ParameterEdge pw = getParameterEdgeFromTo(session, password, select);
		assertGenerated(pw);
		assertEquals("password", pw.getName());

		// one from email
		Property email = assertHasProperty(session, "current email");
		assertGenerated(email);
		ParameterEdge pw2 = getParameterEdgeFromTo(session, email, select);
		assertGenerated(pw2);
		assertEquals("email", pw2.getName());

	}

}
