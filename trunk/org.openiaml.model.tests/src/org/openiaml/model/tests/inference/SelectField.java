/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.RunAction;

/**
 * SyncWires connected to DomainAttributeInstances should call
 * the appropriate operations in the DomainAttributeInstance
 *
 * @author jmwright
 *
 */
public class SelectField extends InferenceTestCase {

	@Override
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
		assertNotGenerated(assertHasSyncWire(root, field, attr));
		assertNotGenerated(assertHasSelectWire(root, user, attr));

		// [inferred elements]
		// edit events and operations on the text field
		CompositeOperation update = assertHasCompositeOperation(field, "update");
		assertGenerated(update);
		EventTrigger edit = field.getOnEdit();
		assertGenerated(edit);
		Property fieldValue = assertHasProperty(field, "fieldValue");
		assertGenerated(fieldValue);

		// [new elements]
		// edit operations on the attribute
		CompositeOperation updateAttr = assertHasCompositeOperation(attr, "update");

		// when the text field is edited, the attr update operation should be called
		RunAction runEdit = assertHasRunAction(root, edit, updateAttr);

		// with a parameter
		assertGenerated(getParameterEdgeFromTo(root, fieldValue, runEdit));


	}

}
