/**
 * 
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Test case for inference of login handler[type=domain object]
 * 
 * @author jmwright
 *
 */
public class LoginHandlerInstance extends InferenceTestCase {

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(LoginHandlerInstance.class);
		
		Page page = (Page) queryOne(root, "iaml:children[iaml:name='Home']");
		assertNotGenerated(page);
		DomainStore store = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='Users']");
		assertNotGenerated(store);
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		assertNotGenerated(session);
		
		DomainObject obj = (DomainObject) queryOne(store, "iaml:children[iaml:name='User']");
		assertNotGenerated(obj);
		
		DomainAttribute password = (DomainAttribute) queryOne(obj, "iaml:attributes[iaml:name='password']");
		assertNotGenerated(password);
		
		LoginHandler handler = (LoginHandler) queryOne(session, "iaml:children[iaml:name='login handler']");
		assertNotGenerated(handler);
		assertEquals(handler.getType(), LoginHandlerTypes.DOMAIN_OBJECT);
		DomainObjectInstance instance = (DomainObjectInstance) queryOne(session, "iaml:children[iaml:name='logged in user']");
		assertNotGenerated(instance);
		
		// only one attribute
		DomainAttributeInstance aname = (DomainAttributeInstance) queryOne(instance, "iaml:attributes[iaml:name='name']");
		assertNotGenerated(aname);
		assertHasNone(instance, "iaml:attributes[iaml:name='password']");
		
		// no pages have been created yet
		assertHasNone(root, "iaml:children[iaml:name='Login Successful']");
		assertHasNone(session, "iaml:children[iaml:name='Logout Successful']");
		
	}
	
	/**
	 * Test the generated navigate wires. 
	 * 
	 * @throws Exception
	 */
	public void testInferredWires() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		LoginHandler handler = (LoginHandler) queryOne(session, "iaml:children[iaml:name='login handler']");
		
		// there should be new pages created
		Page login = (Page) queryOne(session, "iaml:children[iaml:name='Login Successful']");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, handler, login);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("login", wire.getName());
			assertGenerated(wire);
		}
		
		Page logout = (Page) queryOne(root, "iaml:children[iaml:name='Logout Successful']");
		{
			Set<WireEdge> wires = assertHasWiresFromTo(1, root, handler, logout);
			NavigateWire wire = (NavigateWire) wires.iterator().next();
			assertEquals("logout", wire.getName());
			assertGenerated(wire);
		}
		
	}
	
	/**
	 * Test the attributes inference.
	 * 
	 * @throws Exception
	 */
	public void testInferredAttributes() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		
		DomainObjectInstance instance = (DomainObjectInstance) queryOne(session, "iaml:children[iaml:name='logged in user']");
		
		// the domain instance should contain all attributes
		DomainAttributeInstance apassword = (DomainAttributeInstance) queryOne(instance, "iaml:attributes[iaml:name='password']");
		assertGenerated(apassword);
		DomainAttributeInstance aname = (DomainAttributeInstance) queryOne(instance, "iaml:attributes[iaml:name='name']");
		assertNotGenerated(aname);
		
		// the instance should also contain an 'exists?' operation
		Operation exists = (Operation) queryOne(instance, "iaml:operations[iaml:name='exists?']");
		assertGenerated(exists);
		
	}
	
	/**
	 * Test the generation of the select wire.
	 * 
	 * @throws Exception
	 */
	public void testInferredSelect() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		DomainStore store = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='Users']");
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		
		DomainObject obj = (DomainObject) queryOne(store, "iaml:children[iaml:name='User']");
		
		LoginHandler handler = (LoginHandler) queryOne(session, "iaml:children[iaml:name='login handler']");
		DomainObjectInstance instance = (DomainObjectInstance) queryOne(session, "iaml:children[iaml:name='logged in user']");
		
		// there should be a select wire from the object to the instance
		Set<WireEdge> wires = assertHasWiresFromTo(1, root, obj, instance);
		SelectWire select = (SelectWire) wires.iterator().next();
		assertEquals("password = :password", select.getQuery());
		assertGenerated(select);
		
		// the login handler should have generated a key store
		ApplicationElementProperty currentPassword = (ApplicationElementProperty) queryOne(session, "iaml:properties[iaml:name='current password']");
		assertGenerated(currentPassword);
		
		// each key should be connected to the select
		{
			Set<WireEdge> wires2 = assertHasWiresFromTo(1, handler, currentPassword, select);
			ParameterWire param = (ParameterWire) wires2.iterator().next();
			assertGenerated(param);
		}
		
		// there should be a set wire connecting to the generated property
		{
			Set<WireEdge> wires2 = assertHasWiresFromTo(1, handler, handler, currentPassword);
			SetWire set = (SetWire) wires2.iterator().next();
			assertGenerated(set);
		}
		
	}
	
	
	/**
	 * A default logout page should be created; this will
	 * redirect to the actual logout page in our case.
	 * 
	 * @throws Exception
	 */
	public void testGeneratedLogoutPage() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");

		// a generated 'logout' page
		Page page = (Page) queryOne(session, "iaml:children[iaml:name='logout']");
		assertGenerated(page);
		
		// this page will have been generated
		Page target = (Page) queryOne(root, "iaml:children[iaml:name='Logout Successful']");
		assertGenerated(target);
		
		EventTrigger access = (EventTrigger) queryOne(page, "iaml:eventTriggers[iaml:name='access']");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, target);
		{
			NavigateWire nav = (NavigateWire) w.iterator().next();
			assertGenerated(nav);
		}
	}
	
	/**
	 * Test the generation of event triggers and check operations.
	 * 
	 * @throws Exception
	 */
	public void testChecks() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		
		Page dest = (Page) queryOne(session, "iaml:children[iaml:name='current user']");
		assertNotGenerated(dest);
		
		Operation check = (Operation) queryOne(session, "iaml:operations[iaml:name='check instance']");
		EventTrigger access = (EventTrigger) queryOne(dest, "iaml:eventTriggers[iaml:name='access']");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, check);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
			assertGenerated(run);
			assertEquals("run", run.getName());
		}
		
	}
	
	/**
	 * The logout page should execute the 'do logout' operation
	 * 
	 * @throws Exception
	 */
	public void testLogoutOperation() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");

		Page logout = (Page) queryOne(root, "iaml:children[iaml:name='Logout Successful']");
		assertGenerated(logout);
		
		Operation op = (Operation) queryOne(session, "iaml:operations[iaml:name='do logout']");
		EventTrigger access = (EventTrigger) queryOne(logout, "iaml:eventTriggers[iaml:name='access']");
		Set<WireEdge> w = assertHasWiresFromTo(1, session, access, op);
		{
			RunInstanceWire run = (RunInstanceWire) w.iterator().next();
			assertGenerated(run);
		}
		
	}
	
	/**
	 * A default login page should be created.
	 * 
	 * @throws Exception
	 */
	public void testLoginPage() throws Exception {
		root = loadAndInfer(LoginHandlerInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");

		Page login = (Page) queryOne(root, "iaml:children[iaml:name='login']");
		assertGenerated(login);
		
		// it should contain a form
		InputForm form = (InputForm) queryOne(login, "iaml:children[iaml:name='login form']");
		assertGenerated(form);
		
		// with a password field
		InputTextField field = (InputTextField) queryOne(form, "iaml:children[iaml:name='password']");
		assertGenerated(field);
		
		// there should be a login button
		Button button = (Button) queryOne(form, "iaml:children[iaml:name='Login']");
		assertGenerated(button);
		
		// a generated operation will handle the login
		Operation op = (Operation) queryOne(session, "iaml:operations[iaml:name='do login']");
		assertGenerated(op);
		
		// button has an 'onClick' run wire
		Set<WireEdge> w = assertHasWiresFromTo(1, root, button, op);
		RunInstanceWire run = (RunInstanceWire) w.iterator().next();
		assertGenerated(run);
		assertEquals("onClick", run.getName());

		// the text field has a parameter
		ApplicationElementProperty prop = (ApplicationElementProperty) queryOne(field, "iaml:properties[iaml:name='fieldValue']");
		assertGenerated(prop);
		
		// connecting to the run wire
		Set<WireEdge> w2 = assertHasWiresFromTo(1, root, prop, run);
		ParameterWire param = (ParameterWire) w2.iterator().next();
		assertGenerated(param);
		
	}
	
}
