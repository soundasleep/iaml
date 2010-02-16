/**
 *
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

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
		Frame page1 = assertHasFrame(root, "page1");
		Frame page2 = assertHasFrame(root, "page2");
		DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "xpath");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

		InputTextField field1 = assertHasInputTextField(page1, "target");
		InputTextField field2 = assertHasInputTextField(page2, "target");
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
		CompositeCondition cond = assertHasCompositeCondition(dae, "xpath");
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
		EventTrigger srcEdit = assertHasEventTrigger(field1, "edit");
		Operation srcOp = assertHasOperation(field1, "update");
		EventTrigger targetEdit = assertHasEventTrigger(field2, "edit");
		Operation targetOp = assertHasOperation(field2, "update");
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
		assertEquals("Connect ConditionWires to RunInstanceWires created by SyncWires (edit/update)", srcCw.getGeneratedRule());
		ConditionWire targetCw = (ConditionWire) getWireFromTo(root, cond, targetRw);
		assertTrue(targetCw.isIsGenerated());
		assertEquals("Connect ConditionWires to RunInstanceWires created by SyncWires (edit/update)", targetCw.getGeneratedRule());

		// all the ConditionWires need parameters: the XPath source, and the element to evaluate
		ParameterEdge param1 = getParameterEdgeFromTo(root, dae, srcCw);
		assertTrue(param1.isIsGenerated());
		assertEquals("Connect ParameterWires to ConditionWires connected to RunInstanceWires created by SyncWires (edit/update)", param1.getGeneratedRule());
		ParameterEdge param2 = getParameterEdgeFromTo(root, dae, targetCw); 
		assertTrue(param2.isIsGenerated());
		assertEquals("Connect ParameterWires to ConditionWires connected to RunInstanceWires created by SyncWires (edit/update)", param2.getGeneratedRule());


	}

}
