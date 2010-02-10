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
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of an InputForm with a DomainObject.
 *
 * @model SyncFormDomainObject.iaml
 * @author jmwright
 *
 */
public class SyncFormDomainObject extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncFormDomainObject.class);
	}

	@SuppressWarnings("unused")
	public void testInference() throws JaxenException {
		Frame page = assertHasFrame(root, "page1");
		InputForm form = assertHasInputForm(page, "form1");
		InputTextField field1 = assertHasInputTextField(form, "field1");
		InputTextField field2 = assertHasInputTextField(form, "field2");

		DomainStore store = assertHasDomainStore(root, "domainStore1");
		DomainObject obj = assertHasDomainObject(store, "form1");
		DomainAttribute attr1 = assertHasDomainAttribute(obj, "field1");
		DomainAttribute attr2 = assertHasDomainAttribute(obj, "field2");

		// there should be a SyncWire from field1 to attr1
		SyncWire sw1 = (SyncWire) getWireBidirectional(form, field1, attr1);
		SyncWire sw2 = (SyncWire) getWireBidirectional(form, field2, attr2);

		// fields should now have an edit event
		EventTrigger editf1 = assertHasEventTrigger(field1, "edit");
		EventTrigger editf2 = assertHasEventTrigger(field2, "edit");

		// attrs should now have an edit event
		EventTrigger edita1 = assertHasEventTrigger(attr1, "edit");
		EventTrigger edita2 = assertHasEventTrigger(attr2, "edit");

		// fields should now have update operations
		Operation update1 = assertHasOperation(field1, "update");
		Operation update2 = assertHasOperation(field2, "update");
		Operation updatea1 = assertHasOperation(attr1, "update");
		Operation updatea2 = assertHasOperation(attr2, "update");

		// fields should have fieldValues
		ApplicationElementProperty value1 = assertHasApplicationElementProperty(field1, "fieldValue");
		ApplicationElementProperty value2 = assertHasApplicationElementProperty(field2, "fieldValue");
		ApplicationElementProperty valuea1 = assertHasApplicationElementProperty(attr1, "fieldValue");
		ApplicationElementProperty valuea2 = assertHasApplicationElementProperty(attr2, "fieldValue");

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
