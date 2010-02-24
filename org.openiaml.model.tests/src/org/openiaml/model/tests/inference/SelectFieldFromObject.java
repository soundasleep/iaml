/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
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
public class SelectFieldFromObject extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SelectFieldFromObject.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Frame container = assertHasFrame(root, "container");
		InputTextField field = assertHasInputTextField(container, "textfield");

		DomainStore store = assertHasDomainStore(root, "domain store");
		DomainObject user = assertHasDomainObject(store, "User");

		DomainObjectInstance obj = assertHasDomainObjectInstance(container, "\"my user\"");
		DomainAttributeInstance attr = assertHasDomainAttributeInstance(obj, "name");
		SyncWire sw = (SyncWire) getWireBidirectional(root, field, attr);
		assertNotGenerated(sw);
		SelectWire select = (SelectWire) getWireFromTo(root, user, obj);
		assertNotGenerated(select);
		
		// should have autosave enabled on both fields
		assertNotGenerated(obj);
		assertTrue(obj.isAutosave());
		assertNotGenerated(attr);
		assertTrue(attr.isAutosave());

		// [inferred elements]
		// edit events and operations on the text field
		CompositeOperation update = assertHasCompositeOperation(field, "update");
		assertGenerated(update);
		EventTrigger edit = field.getOnEdit();
		Property fieldValue = assertHasProperty(field, "fieldValue");

		// [new elements]
		// edit operations on the attribute
		CompositeOperation updateAttr = assertHasCompositeOperation(attr, "update");

		// when the text field is edited, the attr update operation should be called
		RunInstanceWire runEdit = (RunInstanceWire) getWireFromTo(root, edit, updateAttr);
		
		// with a parameter
		assertGenerated(getParameterEdgeFromTo(root, fieldValue, runEdit));

	}

}
