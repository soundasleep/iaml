/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttribute;
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
 * Tests inference of database sources.
 * 
 * @author jmwright
 *
 */
public class DatabaseWithInputForm extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(DatabaseWithInputForm.class);
	}
	
	public void testInferenceWithoutForm() throws JaxenException {
		// [already in model]
		DomainStore store = (DomainStore) queryOne(root, "//iaml:domainStores[iaml:name='a database']");
		// the store should only have two attributes
		assertEquals(2, store.getAttributes().size());
		DomainAttribute attribute = (DomainAttribute) queryOne(store, "iaml.domain:attributes[iaml:name='value1']");
		
		Page page = (Page) queryOne(root, "//iaml:children[iaml:name='without form']");
		InputTextField source = (InputTextField) queryOne(page, "iaml:children[iaml:name='target']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, source, attribute);

		// [inferred]
		// both text field and attribute should have events/operations
		EventTrigger srcEdit = (EventTrigger) queryOne(source, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(source, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(attribute, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(attribute, "iaml:operations[iaml:name='update']");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);
		
		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// both should have fieldValues
		ApplicationElementProperty textValue = (ApplicationElementProperty) queryOne(source, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty attrValue = (ApplicationElementProperty) queryOne(attribute, "iaml:properties[iaml:name='fieldValue']");
		
		// they should be parameters
		ParameterWire textPw = (ParameterWire) getWireFromTo(wire, textValue, srcRw);
		ParameterWire attrPw = (ParameterWire) getWireFromTo(wire, attrValue, targetRw);
		
		// remove warnings
		assertNotNull(textPw);
		assertNotNull(attrPw);
	}
	
	public void testInferenceWithForm() throws Exception {
		// [already in model]
		DomainStore store = (DomainStore) queryOne(root, "//iaml:domainStores[iaml:name='a database']");
		// the store should only have two attributes
		assertEquals(2, store.getAttributes().size());
		DomainAttribute attribute = (DomainAttribute) queryOne(store, "iaml.domain:attributes[iaml:name='value2']");
		
		Page page = (Page) queryOne(root, "//iaml:children[iaml:name='with form']");
		InputForm form = (InputForm) queryOne(page, "iaml:children[iaml:name='a form']");
		InputTextField source = (InputTextField) queryOne(form, "iaml:children[iaml:name='target']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, source, attribute);

		// [inferred]
		// both text field and attribute should have events/operations
		EventTrigger srcEdit = (EventTrigger) queryOne(source, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(source, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(attribute, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(attribute, "iaml:operations[iaml:name='update']");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);
		
		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// both should have fieldValues
		ApplicationElementProperty textValue = (ApplicationElementProperty) queryOne(source, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty attrValue = (ApplicationElementProperty) queryOne(attribute, "iaml:properties[iaml:name='fieldValue']");
		
		// they should be parameters
		ParameterWire textPw = (ParameterWire) getWireFromTo(wire, textValue, srcRw);
		ParameterWire attrPw = (ParameterWire) getWireFromTo(wire, attrValue, targetRw);
		
		// remove warnings
		assertNotNull(textPw);
		assertNotNull(attrPw);
	}
	
}
