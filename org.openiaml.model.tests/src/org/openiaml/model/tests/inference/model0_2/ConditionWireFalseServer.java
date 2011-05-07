/**
 *
 */
package org.openiaml.model.tests.inference.model0_2;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests inference of ConditionWires when attached to SyncWires, and
 * the conditions are server-side.
 *
 * @author jmwright
 *
 */
public class ConditionWireFalseServer extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireFalseServer.class);
	}

	public void testInference() throws JaxenException {
		Frame page1 = assertHasFrame(root, "page1");
		Frame page2 = assertHasFrame(root, "page2");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, page2);

		InputTextField field1 = assertHasInputTextField(page1, "field");
		InputTextField field2 = assertHasInputTextField(page2, "field");
		assertNotSame(field1, field2);

		Function cond = assertHasFunction(root, "Always False");
		SimpleCondition cw = assertHasSimpleCondition(root, cond, wire);

		// [already in model]
		// there should be a Function wire from cond to sync
		assertEquals(cw.getFrom(), cond);
		assertEquals(cw.getTo(), wire);

		// [inferred]
		// field1 and field2 should be connected by SyncWires
		SyncWire sw = assertHasSyncWire(root, field1, field2);

		// we should have Event 'edit' in source
		Event srcEdit = field1.getOnChange();
		Operation srcOp = assertHasOperation(field1, "update");
		Event targetEdit = field2.getOnChange();
		Operation targetOp = assertHasOperation(field2, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		ECARule srcRw = assertHasRunAction(wire, srcEdit, targetOp, "run");
		ECARule targetRw = assertHasRunAction(wire, targetEdit, srcOp, "run");

		// [new]
		// there should be a Function wire to the new SyncWire
		assertGenerated(assertHasSimpleCondition(root, cond, sw));

		// there should be additional ConditionWires to these RunActions
		assertGenerated(assertHasSimpleCondition(page1, cond, srcRw));
		assertGenerated(assertHasSimpleCondition(page1, cond, targetRw));

		// there doesn't need to be any parameters to these ConditionWires

		// we should also have Function wires copied to the 'init' operations
		Event srcAccess = field1.getOnAccess();
		Operation srcInit = assertHasOperation(field1, "init");
		Event targetAccess = field2.getOnAccess();
		Operation targetInit = assertHasOperation(field2, "init");
		assertNotSame(srcAccess, targetAccess);
		assertNotSame(srcInit, targetInit);

		// execution wires
		ECARule srcInitRun = assertHasRunAction(wire, srcAccess, srcInit, "run");
		ECARule targetInitRun = assertHasRunAction(wire, targetAccess, targetInit, "run");

		// they should have incoming parameters
		Value field1value = assertHasFieldValue(field1);
		Value field2value = assertHasFieldValue(field2);
		assertNotSame(field1value, field2value);

		assertHasParameter(root, field2value, srcInitRun);
		assertHasParameter(root, field1value, targetInitRun);

		// but they should also have Function wires
		assertHasSimpleCondition(page1, cond, srcInitRun);
		assertHasSimpleCondition(page1, cond, targetInitRun);


	}

}
