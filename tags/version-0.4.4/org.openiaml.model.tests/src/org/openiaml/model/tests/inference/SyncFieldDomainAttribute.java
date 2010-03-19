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
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.RunAction;

/**
 * Tests inference of sync wires between a domain attribute
 * and a single text field.
 *
 * @author jmwright
 *
 */
public class SyncFieldDomainAttribute extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncFieldDomainAttribute.class);
	}

	public void testInference() throws JaxenException {
		Frame page = assertHasFrame(root, "page");
		InputTextField field = assertHasInputTextField(page, "single-text-field");

		DomainStore store = assertHasDomainStore(root, "store");
		DomainObject obj = assertHasDomainObject(store, "domain");
		DomainAttribute attribute = assertHasDomainAttribute(obj, "attribute");

		// sync wire from text field to attribute
		assertHasSyncWire(page, field, attribute, "syncField");

		// field should now have an edit event
		EventTrigger editEvent = field.getOnEdit();

		// this event should have a run wire
		RunAction runWire = (RunAction) getActionFrom(editEvent, "run");

		// the attribute should have an operation 'update'
		Operation opUpdate = assertHasOperation(attribute, "update");

		// the run wire should go to the operation
		assertEquals(runWire.getFrom(), editEvent);
		assertEquals(runWire.getTo(), opUpdate);

		// there should be a parameter on the field
		Property fieldValue = assertHasProperty(field, "fieldValue");

		// there should be a parameter wire from fieldValue to the operation
		assertGenerated(getParameterEdgeFromTo(page, fieldValue, runWire));
	}

}
