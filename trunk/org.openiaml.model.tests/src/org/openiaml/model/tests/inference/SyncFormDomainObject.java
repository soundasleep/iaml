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
		EventTrigger editf1 = field1.getOnChange();
		EventTrigger editf2 = field2.getOnChange();

		// attrs should now have an edit event
		EventTrigger edita1 = attr1.getOnChange();
		EventTrigger edita2 = attr2.getOnChange();

		// fields should now have update operations
		Operation update1 = assertHasOperation(field1, "update");
		Operation update2 = assertHasOperation(field2, "update");
		Operation updatea1 = assertHasOperation(attr1, "update");
		Operation updatea2 = assertHasOperation(attr2, "update");

		// fields should have fieldValues
		Property value1 = assertHasFieldValue(field1);
		Property value2 = assertHasFieldValue(field2);
		Property valuea1 = assertHasFieldValue(attr1);
		Property valuea2 = assertHasFieldValue(attr2);
	}

}
