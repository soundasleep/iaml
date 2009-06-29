/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests that inference can keep track of the rule source of generated
 * elements.
 * 
 * Based on ConditionWireXpath.
 * 
 * @see ConditionWireXpath
 * @author jmwright
 *
 */
public class SavedRuleSources extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		// the second parameter makes the inference keep track of rule sources (just names for now)
		root = loadAndInfer(ROOT + "inference/ConditionWireXpath.iaml", true);
	}

	public void testInference() throws JaxenException {
		// initial elements
		Page page1 = (Page) queryOne(root, "//iaml:children[iaml:name='page1']");
		Page page2 = (Page) queryOne(root, "//iaml:children[iaml:name='page2']");
		DynamicApplicationElementSet dae = (DynamicApplicationElementSet) queryOne(root, "//iaml:children[iaml:name='xpath']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

		InputTextField field1 = (InputTextField) queryOne(page1, "iaml:children[iaml:name='target']");
		InputTextField field2 = (InputTextField) queryOne(page2, "iaml:children[iaml:name='target']");
		assertNotSame(field1, field2);
		
		// none of these elements are generated
		assertFalse(page1.isIsGenerated());
		assertFalse(page2.isIsGenerated());
		assertFalse(dae.isIsGenerated());
		assertFalse(wire.isIsGenerated());
		assertFalse(field1.isIsGenerated());
		assertFalse(field2.isIsGenerated());
		
		// so none of these elements can have rule sources
		assertNull(page1.getGeneratedRule());
		assertNull(page2.getGeneratedRule());
		assertNull(dae.getGeneratedRule());
		assertNull(wire.getGeneratedRule());
		assertNull(field1.getGeneratedRule());
		assertNull(field2.getGeneratedRule());
		
		// [generated elements]
		// the XPathSet should create an XPath condition
		CompositeCondition cond = (CompositeCondition) queryOne(dae, "iaml:conditions[iaml:name='xpath']");
		assertTrue(cond.isIsGenerated());
		assertEquals("Create the Condition for an XPath set", cond.getGeneratedRule());
		
		// the XPathSet should create SyncWire matches between all pages
		SyncWire wireGen = (SyncWire) getWireBidirectional(root, page1, page2);
		assertTrue(wireGen.isIsGenerated());
		assertEquals("Connect SyncWires for a dynamic source, referenced by XPath", wireGen.getGeneratedRule());
		
		// this SyncWire should have a ConditionWire connected to this condition
		ConditionWire cw = (ConditionWire) getWireFromTo(root, cond, wireGen);
		assertTrue(cw.isIsGenerated());
		assertEquals("Create the XPath ConditionWire for generated SyncWires", cw.getGeneratedRule());
		
		// we can now investigate the SyncWires themselves, and make sure
		// they have the conditions attached too
		EventTrigger srcEdit = (EventTrigger) queryOne(field1, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(field1, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(field2, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(field2, "iaml:operations[iaml:name='update']");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);
		
		assertTrue(srcEdit.isIsGenerated());
		assertEquals("Create 'edit' event trigger for input text field", srcEdit.getGeneratedRule());
		assertTrue(targetEdit.isIsGenerated());
		assertEquals("Create 'edit' event trigger for input text field", targetEdit.getGeneratedRule());
		assertTrue(srcOp.isIsGenerated());
		assertEquals("Create 'update' operation for input text field", srcOp.getGeneratedRule());
		assertTrue(targetOp.isIsGenerated());
		assertEquals("Create 'update' operation for input text field", targetOp.getGeneratedRule());
		
		// there should only be one run wire between these two
		assertHasWiresFromTo(1, wire, srcEdit, targetOp);
		assertHasWiresFromTo(1, wire, targetEdit, srcOp);
		
		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);
		assertTrue(srcRw.isIsGenerated());
		assertEquals("Run instance wire from edit to update", srcRw.getGeneratedRule());
		assertTrue(targetRw.isIsGenerated());
		assertEquals("Run instance wire from edit to update", targetRw.getGeneratedRule());
			
		// there should be additional ConditionWires to these RunInstanceWires
		ConditionWire srcCw = (ConditionWire) getWireFromTo(root, cond, srcRw); 
		assertTrue(srcCw.isIsGenerated());
		assertEquals("Connect ConditionWires to RunInstanceWires created by SyncWires", srcCw.getGeneratedRule());
		ConditionWire targetCw = (ConditionWire) getWireFromTo(root, cond, targetRw);
		assertTrue(targetCw.isIsGenerated());
		assertEquals("Connect ConditionWires to RunInstanceWires created by SyncWires", targetCw.getGeneratedRule());
		
		// all the ConditionWires need parameters: the XPath source, and the element to evaluate
		ParameterWire param1 = (ParameterWire) getWireFromTo(root, dae, srcCw);
		assertTrue(param1.isIsGenerated());
		assertEquals("Connect ParameterWires to ConditionWires connected to RunInstanceWires created by SyncWires", param1.getGeneratedRule());
		ParameterWire param2 = (ParameterWire) getWireFromTo(root, dae, targetCw);
		assertTrue(param2.isIsGenerated());
		assertEquals("Connect ParameterWires to ConditionWires connected to RunInstanceWires created by SyncWires", param2.getGeneratedRule());
		

	}
	
}
