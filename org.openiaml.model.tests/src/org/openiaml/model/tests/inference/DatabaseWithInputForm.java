/**
 *
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of database sources.
 *
 * @author jmwright
 *
 */
public class DatabaseWithInputForm extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(DatabaseWithInputForm.class);
	}

	public void testInferenceWithoutForm() throws JaxenException {
		// [already in model]
		DomainStore store = assertHasDomainStore(root, "a database");
		// the store should only have two attributes
		assertEquals(2, store.getAttributes().size());
		DomainAttribute attribute = assertHasDomainAttribute(store, "value1");

		Frame page = assertHasFrame(root, "without form");
		InputTextField source = assertHasInputTextField(page, "target");
		SyncWire wire = (SyncWire) getWireBidirectional(root, source, attribute);

		// [inferred]
		// both text field and attribute should have events/operations
		EventTrigger srcEdit = assertHasEventTrigger(source, "edit");
		Operation srcOp = assertHasOperation(source, "update");
		EventTrigger targetEdit = assertHasEventTrigger(attribute, "edit");
		Operation targetOp = assertHasOperation(attribute, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// both should have fieldValues
		ApplicationElementProperty textValue = assertHasApplicationElementProperty(source, "fieldValue");
		ApplicationElementProperty attrValue = assertHasApplicationElementProperty(attribute, "fieldValue");

		// they should be parameters
		assertGenerated(assertHasParameterEdge(wire, textValue, srcRw));
		assertGenerated(assertHasParameterEdge(wire, attrValue, targetRw));
	}

	public void testInferenceWithForm() throws Exception {
		// [already in model]
		DomainStore store = assertHasDomainStore(root, "a database");
		// the store should only have two attributes
		assertEquals(2, store.getAttributes().size());
		DomainAttribute attribute = assertHasDomainAttribute(store, "value2");

		Frame page = assertHasFrame(root, "with form");
		InputForm form = assertHasInputForm(page, "a form");
		InputTextField source = assertHasInputTextField(form, "target");
		SyncWire wire = (SyncWire) getWireBidirectional(root, source, attribute);

		// [inferred]
		// both text field and attribute should have events/operations
		EventTrigger srcEdit = assertHasEventTrigger(source, "edit");
		Operation srcOp = assertHasOperation(source, "update");
		EventTrigger targetEdit = assertHasEventTrigger(attribute, "edit");
		Operation targetOp = assertHasOperation(attribute, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// both should have fieldValues
		ApplicationElementProperty textValue = assertHasApplicationElementProperty(source, "fieldValue");
		ApplicationElementProperty attrValue = assertHasApplicationElementProperty(attribute, "fieldValue");

		// they should be parameters
		assertGenerated(assertHasParameterEdge(wire, textValue, srcRw));
		assertGenerated(assertHasParameterEdge(wire, attrValue, targetRw));
	}

}
