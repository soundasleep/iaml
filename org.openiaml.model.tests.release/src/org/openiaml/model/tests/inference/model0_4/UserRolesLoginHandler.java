/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
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
		Set<WireEdge> wires = getWiresTo(session, ui, SelectWire.class);
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
		
		assertGenerated(assertHasApplicationElementProperty(session, "current email"));
		assertGenerated(assertHasApplicationElementProperty(session, "current password"));
		assertHasNoApplicationElementProperty(session, "current generated primary key");
		assertHasNoApplicationElementProperty(session, "current User.generated primary key");
		assertHasNoApplicationElementProperty(session, "current generated_primary_key");
		assertHasNoApplicationElementProperty(session, "current User.generated_primary_key");
		assertHasNoApplicationElementProperty(session, "current User_generated_primary_key");
		
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
		Set<WireEdge> params = getWiresTo(session, select, ParameterWire.class);
		assertEquals(params.toString(), 2, params.size());
		
		// one from password
		ApplicationElementProperty password = assertHasApplicationElementProperty(session, "current password");
		assertGenerated(password);
		ParameterWire pw = (ParameterWire) assertHasWireFromTo(session, password, select, ParameterWire.class);
		assertGenerated(pw);
		assertEquals("password", pw.getName());

		// one from email
		ApplicationElementProperty email = assertHasApplicationElementProperty(session, "current email");
		assertGenerated(email);
		ParameterWire pw2 = (ParameterWire) assertHasWireFromTo(session, email, select, ParameterWire.class);
		assertGenerated(pw2);
		assertEquals("email", pw2.getName());

	}

}
