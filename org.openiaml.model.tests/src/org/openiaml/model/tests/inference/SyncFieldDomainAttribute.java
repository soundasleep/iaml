/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of sync wires between a domain attribute
 * and a single text field.
 * 
 * @model SyncFieldDomainAttribute.iaml
 * @author jmwright
 *
 */
public class SyncFieldDomainAttribute extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ROOT + "inference/SyncFieldDomainAttribute.iaml");
	}
	
	public void testInference() throws JaxenException {
		Page page = (Page) queryOne(root, "//iaml:children[iaml:name='page']");
		InputTextField field = (InputTextField) queryOne(page, "//iaml:children[iaml:name='single-text-field']");
		SyncWire wire = (SyncWire) queryOne(page, "//iaml:wires[iaml:name='syncField']");
		DomainStore store = (DomainStore) queryOne(root, "//iaml:domainStores[iaml:name='store']");
		DomainObject obj = (DomainObject) queryOne(store, "//iaml:children[iaml:name='domain']");
		DomainAttribute attribute = (DomainAttribute) queryOne(obj, "//iaml:attributes[iaml:name='attribute']");
		
		// field should now have an edit event
		EventTrigger editEvent = (EventTrigger) queryOne(field, "iaml:eventTriggers[iaml:name='edit']");
		
		// this event should have a run wire
		RunInstanceWire runWire = (RunInstanceWire) getWireFrom(page, editEvent, "run");
		
		// the attribute should have an operation 'update'
		Operation opUpdate = (Operation) queryOne(attribute, "iaml:operations[iaml:name='update']");
		
		// the run wire should go to the operation
		assertEquals(runWire.getFrom(), editEvent);
		assertEquals(runWire.getTo(), opUpdate);
		
		// there should be a parameter on the field
		ApplicationElementProperty fieldValue = (ApplicationElementProperty) queryOne(field, "iaml:properties[iaml:name='fieldValue']");
		
		// there should be a parameter wire from fieldValue to the operation
		ParameterWire paramWire = (ParameterWire) getWireFromTo(page, fieldValue, runWire);
		assertEquals(paramWire.getFrom(), fieldValue);
		assertEquals(paramWire.getTo(), runWire);
	}
	
}
