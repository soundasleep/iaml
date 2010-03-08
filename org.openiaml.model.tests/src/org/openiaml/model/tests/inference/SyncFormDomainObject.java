/**
 *
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of an InputForm with a DomainObject.
 *
 * @author jmwright
 *
 */
public class SyncFormDomainObject extends InferenceTestCase {

	@Override
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
		SyncWire sw1 = assertHasSyncWire(form, field1, attr1);
		SyncWire sw2 = assertHasSyncWire(form, field2, attr2);

		// fields should now have an edit event
		EventTrigger editf1 = field1.getOnEdit();
		EventTrigger editf2 = field2.getOnEdit();

		// attrs should now have an edit event
		EventTrigger edita1 = attr1.getOnEdit();
		EventTrigger edita2 = attr2.getOnEdit();

		// fields should now have update operations
		Operation update1 = assertHasOperation(field1, "update");
		Operation update2 = assertHasOperation(field2, "update");
		Operation updatea1 = assertHasOperation(attr1, "update");
		Operation updatea2 = assertHasOperation(attr2, "update");

		// fields should have fieldValues
		Property value1 = assertHasProperty(field1, "fieldValue");
		Property value2 = assertHasProperty(field2, "fieldValue");
		Property valuea1 = assertHasProperty(attr1, "fieldValue");
		Property valuea2 = assertHasProperty(attr2, "fieldValue");

		// these field values should be parameters
		ParameterEdge pw1 = value1.getOutParameterEdges().get(0);
		assertTrue(pw1.toString(), pw1.getTo() instanceof RunInstanceWire);
		ParameterEdge pw2 = value2.getOutParameterEdges().get(0);
		assertTrue(pw2.toString(), pw2.getTo() instanceof RunInstanceWire);
		ParameterEdge pwa1 = valuea1.getOutParameterEdges().get(0);
		assertTrue(pwa1.toString(), pwa1.getTo() instanceof RunInstanceWire);
		ParameterEdge pwa2 = valuea2.getOutParameterEdges().get(0);
		assertTrue(pwa2.toString(), pwa2.getTo() instanceof RunInstanceWire);
	}

}
