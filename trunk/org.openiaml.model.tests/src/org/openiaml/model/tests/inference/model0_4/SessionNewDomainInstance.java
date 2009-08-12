/**
 * 
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * 
 * @author jmwright
 *
 */
public class SessionNewDomainInstance extends InferenceTestCase {

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(SessionNewDomainInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		assertNotGenerated(session);
		
		Page container = (Page) queryOne(session, "iaml:children[iaml:name='container']");
		assertNotGenerated(container);
		
		InputForm form = (InputForm) queryOne(container, "iaml:children[iaml:name='edit current user']");
		assertNotGenerated(form);
		
		// the form has no children
		assertEquals("InputForm should have no children", 0, form.getChildren().size());
		
		Button button = (Button) queryOne(container, "iaml:children[iaml:name='make a new user']");
		assertNotGenerated(button);

		DomainObjectInstance inst = (DomainObjectInstance) queryOne(session, "iaml:children[iaml:name='session user']");
		assertNotGenerated(inst);
		
		// the domain object instance has no attributes or events
		assertEquals("Instance has no attributes", 0, inst.getAttributes().size());
		assertEquals("Instance has no events", 0, inst.getEventTriggers().size());

		DomainStore store = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='my store']");
		assertNotGenerated(store);
		
		DomainObject user = (DomainObject) queryOne(store, "iaml:children[iaml:name='User']");
		assertNotGenerated(user);
		
	}
	
	/**
	 * The DomainAttributeInstance should have an 'edit' event which 
	 * executes the 'update' operation of the relevant field in the form.
	 * 
	 * @throws Exception
	 */
	public void testAttributeEditEvent() throws Exception {
		root = loadAndInfer(SessionNewDomainInstance.class);
		
		Session session = (Session) queryOne(root, "iaml:sessions[iaml:name='my session']");
		assertNotGenerated(session);
		
		Page container = (Page) queryOne(session, "iaml:children[iaml:name='container']");
		assertNotGenerated(container);
		
		InputForm form = (InputForm) queryOne(container, "iaml:children[iaml:name='edit current user']");
		assertNotGenerated(form);
		
		Button button = (Button) queryOne(container, "iaml:children[iaml:name='make a new user']");
		assertNotGenerated(button);

		DomainObjectInstance inst = (DomainObjectInstance) queryOne(session, "iaml:children[iaml:name='session user']");
		assertNotGenerated(inst);
		
		// instance attribute "email"
		DomainAttributeInstance email = (DomainAttributeInstance) queryOne(inst, "iaml:attributes[iaml:name='email']");
		assertGenerated(email);
		
		// it should have an 'edit' event
		EventTrigger edit = (EventTrigger) queryOne(email, "iaml:eventTriggers[iaml:name='edit']");
		assertGenerated(edit);
		
		// and a property
		ApplicationElementProperty value = (ApplicationElementProperty) queryOne(email, "iaml:properties[iaml:name='fieldValue']");
		assertGenerated(value);
		
		// get the text field 
		InputTextField targetEmail = (InputTextField) queryOne(form, "iaml:children[iaml:name='email']");
		assertGenerated(targetEmail);
		
		// this should have the 'update' operation
		Operation targetUpdate = (Operation) queryOne(targetEmail, "iaml:operations[iaml:name='update']");
		assertGenerated(targetUpdate);
		
		// email.edit --> target.update
		Set<WireEdge> wires = assertHasWiresFromTo(1, session, edit, targetUpdate);
		RunInstanceWire targetRun = (RunInstanceWire) wires.iterator().next();
		assertGenerated(targetRun);
		
		// email.value --> targetRun wire
		Set<WireEdge> wires2 = assertHasWiresFromTo(1, session, value, targetRun);
		ParameterWire targetParam = (ParameterWire) wires2.iterator().next();
		assertGenerated(targetParam);
		
	}
		
}
