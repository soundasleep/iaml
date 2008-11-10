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
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of an InputForm with a DomainObject.
 * 
 * @model SyncFormDomainObject.iaml
 * @author jmwright
 *
 */
public class SyncFormDomainObject extends InferenceTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ROOT + "inference/SyncFormDomainObject.iaml");
	}
	
	public void testInference() throws JaxenException {
		Page page = (Page) queryOne(root, "iaml:children[iaml:name='page1']");
		InputForm form = (InputForm) queryOne(page, "iaml:children[iaml:name='form1']");		
		InputTextField field1 = (InputTextField) queryOne(form, "iaml:children[iaml:name='field1']");
		InputTextField field2 = (InputTextField) queryOne(form, "iaml:children[iaml:name='field2']");
		
		DomainStore store = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='domainStore1']");
		DomainObject obj = (DomainObject) queryOne(store, "iaml:children[iaml:name='form1']");
		DomainAttribute attr1 = (DomainAttribute) queryOne(obj, "iaml:attributes[iaml:name='field1']");
		DomainAttribute attr2 = (DomainAttribute) queryOne(obj, "iaml:attributes[iaml:name='field2']");
		
		// there should be a SyncWire from field1 to attr1
		SyncWire sw1 = (SyncWire) getWireFromTo(form, field1, attr1);
		SyncWire sw2 = (SyncWire) getWireFromTo(form, field2, attr2);
		
		// fields should now have an edit event
		EventTrigger editf1 = (EventTrigger) queryOne(field1, "iaml:eventTriggers[iaml:name='edit']");
		EventTrigger editf2 = (EventTrigger) queryOne(field2, "iaml:eventTriggers[iaml:name='edit']");

		// attrs should now have an edit event
		EventTrigger edita1 = (EventTrigger) queryOne(attr1, "iaml:eventTriggers[iaml:name='edit']");
		EventTrigger edita2 = (EventTrigger) queryOne(attr2, "iaml:eventTriggers[iaml:name='edit']");

		// fields should now have update operations
		Operation update1 = (Operation) queryOne(field1, "iaml:operations[iaml:name='update']");
		Operation update2 = (Operation) queryOne(field2, "iaml:operations[iaml:name='update']");
		Operation updatea1 = (Operation) queryOne(attr1, "iaml:operations[iaml:name='update']");
		Operation updatea2 = (Operation) queryOne(attr2, "iaml:operations[iaml:name='update']");
		
		// fields should have fieldValues
		ApplicationElementProperty value1 = (ApplicationElementProperty) queryOne(field1, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty value2 = (ApplicationElementProperty) queryOne(field2, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty valuea1 = (ApplicationElementProperty) queryOne(attr1, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty valuea2 = (ApplicationElementProperty) queryOne(attr2, "iaml:properties[iaml:name='fieldValue']");
		
		// these field values should be parameters
		ParameterWire pw1 = (ParameterWire) getWireFrom(root, value1);
		assertTrue(pw1.toString(), pw1.getTo() instanceof RunInstanceWire);
		ParameterWire pw2 = (ParameterWire) getWireFrom(root, value2);
		assertTrue(pw2.toString(), pw2.getTo() instanceof RunInstanceWire);
		ParameterWire pwa1 = (ParameterWire) getWireFrom(root, valuea1);
		assertTrue(pwa1.toString(), pwa1.getTo() instanceof RunInstanceWire);
		ParameterWire pwa2 = (ParameterWire) getWireFrom(root, valuea2);
		assertTrue(pwa2.toString(), pwa2.getTo() instanceof RunInstanceWire);
	}
	
}
