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
 * Tests inference of ConditionWires when attached to SyncWires.
 *
 * @author jmwright
 *
 */
public class ConditionWireFalse extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireFalse.class);
	}

	public void testInference() throws JaxenException {
		Page page = assertHasPage(root, "container");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");
		SyncWire wire = (SyncWire) queryOne(page, "//iaml:wires[iaml:name='sync']");
		Condition cond = (Condition) queryOne(page, "//iaml:conditions[iaml:name='Always False']");
		ConditionWire cw = (ConditionWire) queryOne(page, "//iaml:wires[iaml:name='condition']");

		// [already in model]
		// there should be a condition wire from cond to sync
		assertEquals(cw.getFrom(), cond);
		assertEquals(cw.getTo(), wire);

		// [inferred]
		// we should have EventTrigger 'edit' in source
		EventTrigger srcEdit = assertHasEventTrigger(source, "edit");
		Operation srcOp = assertHasOperation(source, "update");
		EventTrigger targetEdit = assertHasEventTrigger(target, "edit");
		Operation targetOp = assertHasOperation(target, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// [new]
		// there should be additional ConditionWires to these RunInstanceWires
		ConditionWire srcCw = (ConditionWire) getWireFromTo(page, cond, srcRw);
		ConditionWire targetCw = (ConditionWire) getWireFromTo(page, cond, targetRw);
		assertNotNull(srcCw);
		assertNotNull(targetCw);

		// there doesn't need to be any parameters to these ConditionWires

	}

}