/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test case for inference of login handler[type=domain object] when
 * we have multiple parameters. Most of the logic is handled sufficiently
 * in {@link LoginHandlerInstance}, so this test case is checking
 * special cases for multiple parameters.
 *
 * @author jmwright
 *
 */
public class LoginHandlerInstanceMultiple extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(LoginHandlerInstanceMultiple.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);
		DomainSource store = assertHasDomainSource(root, "domain source");
		assertNotGenerated(store);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		DomainSchema obj = assertHasDomainSchema(root, "User");
		assertNotGenerated(obj);

		DomainAttribute password = assertHasDomainAttribute(obj, "password");
		assertNotGenerated(password);

		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.DOMAIN_OBJECT);
		DomainIterator instance = assertHasDomainIterator(session, "logged in user");
		assertNotGenerated(instance);

		// only one attribute
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(instance, "name");
		assertNotGenerated(aname);
		assertHasNone(instance, "iaml:attributes[iaml:name='password']");

		// no pages have been created yet
		assertHasNone(root, "iaml:children[iaml:name='Login Successful']");
		assertHasNone(session, "iaml:children[iaml:name='Logout Successful']");

	}

	/**
	 * Test the contents of the DomainIterator query.
	 *
	 * @throws Exception
	 */
	public void testQueryContents() throws Exception {
		root = loadAndInfer(LoginHandlerInstanceMultiple.class);

		Session session = assertHasSession(root, "my session");
		DomainIterator instance = assertHasDomainIterator(session, "logged in user");

		// the query should contain both :password and :email
		assertTrue("Query '" + instance.getQuery() + "' should contain :password", instance.getQuery().contains(":password"));
		assertTrue("Query '" + instance.getQuery() + "' should contain :email", instance.getQuery().contains(":email"));

	}
	
	/**
	 * The DomainAttributeInstance 'name' should ExtendsEdge the defined DomainAttribute 'name',
	 * even though we haven't specified it manually in the instance.
	 * 
	 * @throws Exception
	 */
	public void testDomainInstanceAttributeNameExtendsDefinedNameAttribute() throws Exception {
		root = loadAndInfer(LoginHandlerInstanceMultiple.class);
		
		Session session = assertHasSession(root, "my session");
		DomainSchema object = assertHasDomainSchema(root, "User");
		DomainIterator instance = assertHasDomainIterator(session, "logged in user");

		DomainAttributeInstance ai = assertHasDomainAttributeInstance(instance, "name");
		DomainAttribute a = assertHasDomainAttribute(object, "name");
		
		ExtendsEdge edge = assertHasExtendsEdge(root, ai, a);
		assertGenerated(edge);
		
	}

}
