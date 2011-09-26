/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;

import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
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
	 * not be a ActivityParameter in the input form.
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

		assertGenerated(assertHasValue(session, "current email"));
		assertGenerated(assertHasValue(session, "current password"));
		assertHasNoValue(session, "current generated primary key");
		assertHasNoValue(session, "current User.generated primary key");
		assertHasNoValue(session, "current generated_primary_key");
		assertHasNoValue(session, "current User.generated_primary_key");
		assertHasNoValue(session, "current User_generated_primary_key");

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
		DomainInstance instance = user.getCurrentInstance();
		assertGenerated(instance);

		Role defaultRole = assertHasRole(root, "User");
		assertGenerated(defaultRole);

		Role registeredUser = assertHasRole(root, "Registered User");
		assertNotGenerated(registeredUser);

		// registered user is extended from default role
		assertGenerated(assertHasExtendsEdge(root, registeredUser, defaultRole));

		{
			DomainAttributeInstance dai = assertHasDomainAttributeInstance(instance, "email");
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
			DomainAttributeInstance dai = assertHasDomainAttributeInstance(instance, "password");
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

		// there should only be two incoming ActivityParameter wires
		List<Parameter> params = user.getInParameterEdges();
		assertEquals(params.toString(), 2, params.size());

		// one from password
		Value password = assertHasValue(session, "current password");
		assertGenerated(password);
		Parameter pw = getParameterFromTo(session, password, user);
		assertGenerated(pw);
		assertEquals("password", pw.getName());

		// one from email
		Value email = assertHasValue(session, "current email");
		assertGenerated(email);
		Parameter pw2 = getParameterFromTo(session, email, user);
		assertGenerated(pw2);
		assertEquals("email", pw2.getName());

	}

}
