/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * SyncWires connected to DomainAttributeInstances should call
 * the appropriate operations in the DomainAttributeInstance
 *
 * @author jmwright
 *
 */
public class SelectFieldFromObject extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SelectFieldFromObject.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Page container = (Page) queryOne(root, "//iaml:children[iaml:name='container']");
		InputTextField field = (InputTextField) queryOne(container, "iaml:children[iaml:name='textfield']");

		DomainStore store = (DomainStore) queryOne(root, "//iaml:domainStores[iaml:name='domain store']");
		DomainObject user = (DomainObject) queryOne(store, "iaml:children[iaml:name='User']");

		DomainObjectInstance obj = (DomainObjectInstance)
			queryOne(root, "//iaml:children[iaml:name='\"my user\"']");
		DomainAttributeInstance attr = (DomainAttributeInstance)
			queryOne(obj, "iaml:attributes[iaml:name='name']");
		SyncWire sw = (SyncWire) getWireBidirectional(root, field, attr);
		SelectWire select = (SelectWire) getWireFromTo(root, user, obj);

		// [inferred elements]
		// edit events and operations on the text field
		CompositeOperation update = (CompositeOperation) queryOne(field, "iaml:operations[iaml:name='update']");
		EventTrigger edit = (EventTrigger) queryOne(field, "iaml:eventTriggers[iaml:name='edit']");
		ApplicationElementProperty fieldValue = (ApplicationElementProperty) queryOne(field, "iaml:properties[iaml:name='fieldValue']");

		// [new elements]
		// edit operations on the attribute
		CompositeOperation updateAttr = (CompositeOperation) queryOne(attr, "iaml:operations[iaml:name='update']");

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
