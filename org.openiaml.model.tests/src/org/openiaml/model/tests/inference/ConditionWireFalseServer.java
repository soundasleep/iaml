/**
 *
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of ConditionWires when attached to SyncWires, and
 * the conditions are server-side.
 *
 * @author jmwright
 *
 */
public class ConditionWireFalseServer extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireFalseServer.class);
	}

	public void testInference() throws JaxenException {
		Page page1 = (Page) queryOne(root, "//iaml:children[iaml:name='page1']");
		Page page2 = (Page) queryOne(root, "//iaml:children[iaml:name='page2']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, page2);

		InputTextField field1 = assertHasInputTextField(page1, "field");
		InputTextField field2 = assertHasInputTextField(page2, "field");
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
		EventTrigger srcEdit = assertHasEventTrigger(field1, "edit");
		Operation srcOp = assertHasOperation(field1, "update");
		EventTrigger targetEdit = assertHasEventTrigger(field2, "edit");
		Operation targetOp = assertHasOperation(field2, "update");
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
