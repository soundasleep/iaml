/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * SyncWires connected to DomainAttributeInstances should call
 * the appropriate operations in the DomainAttributeInstance
 *
 * @author jmwright
 *
 */
public class SelectField extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SelectField.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Frame container = assertHasFrame(root, "container");
		InputTextField field = assertHasInputTextField(container, "editname");

		DomainStore store = assertHasDomainStore(root, "DomainStore");
		DomainObject user = assertHasDomainObject(store, "User");

		DomainAttributeInstance attr = assertHasDomainAttributeInstance(container, "name");
		SyncWire sw = (SyncWire) getWireBidirectional(root, field, attr);
		SelectWire select = (SelectWire) getWireFromTo(root, user, attr);

		// [inferred elements]
		// edit events and operations on the text field
		CompositeOperation update = assertHasCompositeOperation(field, "update");
		EventTrigger edit = assertHasEventTrigger(field, "edit");
		ApplicationElementProperty fieldValue = assertHasApplicationElementProperty(field, "fieldValue");

		// [new elements]
		// edit operations on the attribute
		CompositeOperation updateAttr = assertHasCompositeOperation(attr, "update");

		// when the text field is edited, the attr update operation should be called
		RunInstanceWire runEdit = (RunInstanceWire) getWireFromTo(root, edit, updateAttr);
		// with a parameter
		ParameterWire param = (ParameterWire) getWireFromTo(root, fieldValue, runEdit);

		assertNotNull(param);
		assertNotNull(sw);
		assertNotNull(select);
		assertNotNull(update);

	}

}
