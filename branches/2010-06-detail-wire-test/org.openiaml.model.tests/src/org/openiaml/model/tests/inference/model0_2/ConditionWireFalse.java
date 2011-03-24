/**
 *
 */
package org.openiaml.model.tests.inference.model0_2;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests inference of ConditionWires when attached to SyncWires.
 *
 * @author jmwright
 *
 */
public class ConditionWireFalse extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireFalse.class);
	}

	public void testInference() throws JaxenException {
		Frame page = assertHasFrame(root, "container");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");
		SyncWire wire = assertHasSyncWire(page, source, target);
		Condition cond = assertHasCondition(page, "Always False");

		// [inferred]
		// we should have EventTrigger 'edit' in source
		EventTrigger srcEdit = source.getOnChange();
		Operation srcOp = assertHasOperation(source, "update");
		EventTrigger targetEdit = target.getOnChange();
		Operation targetOp = assertHasOperation(target, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		ActionEdge srcRw = assertHasRunAction(wire, srcEdit, targetOp);
		ActionEdge targetRw = assertHasRunAction(wire, targetEdit, srcOp);

		// [new]
		// there should be additional ConditionWires to these RunActions
		assertGenerated(assertHasConditionEdge(page, cond, srcRw));
		assertGenerated(assertHasConditionEdge(page, cond, targetRw));

		// there doesn't need to be any parameters to these ConditionWires

	}

}