/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
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
		DomainIterator ui = assertHasDomainIterator(session, "current instance");
		assertGenerated(ui);

	}
	
	/**
	 * The "generated primary key" of the Registered User/User should
	 * not be a parameter in the input form.
	 * 
	 * @throws Exception
	 */
	public void testPrimaryKeyNotInputField() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class);
		
		Session loginSession = assertHasSession(root, "user login handler login");
		Frame login = assertHasFrame(loginSession, "login");
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
	 * The DomainIterator should contain DomainAttributeInstances.
	 * 
	 * @throws Exception
	 */
	public void testDomainIteratorHasAttributes() throws Exception {

		root = loadAndInfer(UserRolesLoginHandler.class);
		
		Session session = assertHasSession(root, "my session");
		DomainIterator user = assertHasDomainIterator(session, "current instance");

		Role defaultRole = assertHasRole(root, "User");
		assertGenerated(defaultRole);
		
		Role registeredUser = assertHasRole(root, "Registered User");
		assertNotGenerated(registeredUser);
		
		// registered user is extended from default role
		assertGenerated(assertHasExtendsEdge(root, registeredUser, defaultRole));
		
		{
			DomainAttributeInstance dai = assertHasDomainAttributeInstance(user, "email");
			assertGenerated(dai);
			DomainAttribute actual = assertHasDomainAttribute(defaultRole, "email");
			assertGenerated(actual);
			// "Registered User.email" is actually an extension of default role.email
			DomainAttribute attr = assertHasDomainAttribute(registeredUser, "email");
			assertGenerated(attr);
			assertGenerated(assertHasExtendsEdge(root, attr, actual));
			assertGenerated(assertHasExtendsEdge(root, dai, attr));
		}
		
		{
			DomainAttributeInstance dai = assertHasDomainAttributeInstance(user, "password");
			assertGenerated(dai);
			DomainAttribute actual = assertHasDomainAttribute(defaultRole, "password");
			assertGenerated(actual);
			// "Registered User.email" is actually an extension of default role.email
			DomainAttribute attr = assertHasDomainAttribute(registeredUser, "password");
			assertGenerated(attr);
			assertGenerated(assertHasExtendsEdge(root, attr, actual));
			assertGenerated(assertHasExtendsEdge(root, dai, attr));
		}
		
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
		DomainIterator user = assertHasDomainIterator(session, "current instance");
		
		assertEqualsOneOf(new String[] {
				"password = :password and email = :email",
				"email = :email and password = :password"
			}, user.getQuery());
		
		// there should only be two incoming parameter wires
		List<ParameterEdge> params = user.getInParameterEdges();
		assertEquals(params.toString(), 2, params.size());
		
		// one from password
		Property password = assertHasProperty(session, "current password");
		assertGenerated(password);
		ParameterEdge pw = getParameterEdgeFromTo(session, password, user);
		assertGenerated(pw);
		assertEquals("password", pw.getName());

		// one from email
		Property email = assertHasProperty(session, "current email");
		assertGenerated(email);
		ParameterEdge pw2 = getParameterEdgeFromTo(session, email, user);
		assertGenerated(pw2);
		assertEquals("email", pw2.getName());

	}

}
