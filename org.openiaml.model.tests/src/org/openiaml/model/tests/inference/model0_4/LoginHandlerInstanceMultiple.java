/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Iterator;
import java.util.Set;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Test case for inference of login handler[type=domain object] when
 * we have multiple parameters. Most of the logic is handled sufficiently
 * in {@model LoginHandlerInstance}, so this test case is checking
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
		DomainStore store = assertHasDomainStore(root, "Users");
		assertNotGenerated(store);
		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);

		DomainObject obj = assertHasDomainObject(store, "User");
		assertNotGenerated(obj);

		DomainAttribute password = assertHasDomainAttribute(obj, "password");
		assertNotGenerated(password);

		LoginHandler handler = assertHasLoginHandler(session, "login handler");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.DOMAIN_OBJECT);
		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");
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
	 * Even though we have multiple parameters, there should only be one SelectWire.
	 *
	 * @throws Exception
	 */
	public void testOnlyOneSelectWire() throws Exception {
		root = loadAndInfer(LoginHandlerInstanceMultiple.class);

		Session session = assertHasSession(root, "my session");
		DomainStore store = assertHasDomainStore(root, "Users");
		DomainObject object = assertHasDomainObject(store, "User");
		DomainObjectInstance instance = assertHasDomainObjectInstance(session, "logged in user");

		SelectWire select = null;
		int selectWireCount = 0;
		{
			Set<Wire> wires2 = assertHasWiresFromTo(1, session, object, instance);
			Iterator<Wire> it = wires2.iterator();
			while (it.hasNext()) {
				Wire w = it.next();
				if (w instanceof SelectWire) {
					selectWireCount++;
					select = (SelectWire) w;
				}
			}
		}
		assertEquals("There should only be one SelectWire", 1, selectWireCount);
		assertGenerated(select);

		// the query should contain both :password and :email
		assertTrue("Query '" + select.getQuery() + "' should contain :password", select.getQuery().contains(":password"));
		assertTrue("Query '" + select.getQuery() + "' should contain :email", select.getQuery().contains(":email"));

	}

}
