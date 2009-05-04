/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of ConditionWires when attached to SyncWires, and
 * the conditions are server-side.
 * 
 * @author jmwright
 *
 */
public class ConditionWireFalseServer extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ConditionWireFalseServer.class);
	}
	
	public void testInference() throws JaxenException {
		Page page1 = (Page) queryOne(root, "//iaml:children[iaml:name='page1']");
		Page page2 = (Page) queryOne(root, "//iaml:children[iaml:name='page2']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, page2);

		InputTextField field1 = (InputTextField) queryOne(page1, "iaml:children[iaml:name='field']");
		InputTextField field2 = (InputTextField) queryOne(page2, "iaml:children[iaml:name='field']");
		assertNotSame(field1, field2);
		
		Condition cond = (Condition) queryOne(root, "//iaml:conditions[iaml:name='Always False']");
		ConditionWire cw = (ConditionWire) getWireFromTo(root, cond, wire);
		
		// [already in model]
		// there should be a condition wire from cond to sync
		assertEquals(cw.getFrom(), cond);
		assertEquals(cw.getTo(), wire);
		
		// [inferred]
		// field1 and field2 should be connected by SyncWires
		SyncWire sw = (SyncWire) getWireBidirectional(root, field1, field2);
		
		// we should have EventTrigger 'edit' in source
		EventTrigger srcEdit = (EventTrigger) queryOne(field1, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(field1, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(field2, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(field2, "iaml:operations[iaml:name='update']");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);
		
		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);
		
		// [new]
		// there should be a condition wire to the new SyncWire
		ConditionWire cw2 = (ConditionWire) getWireFromTo(root, cond, sw);
		
		// there should be additional ConditionWires to these RunInstanceWires
		ConditionWire srcCw = (ConditionWire) getWireFromTo(page1, cond, srcRw); 
		ConditionWire targetCw = (ConditionWire) getWireFromTo(page1, cond, targetRw);
		
		// there doesn't need to be any parameters to these ConditionWires
		
		assertNotNull(cw2);
		assertNotNull(srcCw);
		assertNotNull(targetCw);

	}
	
}
