/**
 *
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
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
		Frame page1 = assertHasFrame(root, "page1");
		Frame page2 = assertHasFrame(root, "page2");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, page2);

		InputTextField field1 = assertHasInputTextField(page1, "field");
		InputTextField field2 = assertHasInputTextField(page2, "field");
		assertNotSame(field1, field2);

		Condition cond = assertHasCondition(root, "Always False");
		ConditionWire cw = (ConditionWire) getWireFromTo(root, cond, wire);

		// [already in model]
		// there should be a condition wire from cond to sync
		assertEquals(cw.getFrom(), cond);
		assertEquals(cw.getTo(), wire);

		// [inferred]
		// field1 and field2 should be connected by SyncWires		
		SyncWire sw = assertHasSyncWire(root, field1, field2);

		// we should have EventTrigger 'edit' in source
		EventTrigger srcEdit = assertHasEventTrigger(field1, "edit");
		Operation srcOp = assertHasOperation(field1, "update");
		EventTrigger targetEdit = assertHasEventTrigger(field2, "edit");
		Operation targetOp = assertHasOperation(field2, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		RunInstanceWire srcRw = assertHasRunInstanceWire(wire, srcEdit, targetOp, "run");
		RunInstanceWire targetRw = assertHasRunInstanceWire(wire, targetEdit, srcOp, "run");

		// [new]
		// there should be a condition wire to the new SyncWire
		ConditionWire cw2 = assertHasConditionWire(root, cond, sw);

		// there should be additional ConditionWires to these RunInstanceWires
		ConditionWire srcCw = assertHasConditionWire(page1, cond, srcRw);
		ConditionWire targetCw = assertHasConditionWire(page1, cond, targetRw);

		// there doesn't need to be any parameters to these ConditionWires

		// we should also have condition wires copied to the 'init' operations
		EventTrigger srcAccess = assertHasEventTrigger(field1, "access");
		Operation srcInit = assertHasOperation(field1, "init");
		EventTrigger targetAccess = assertHasEventTrigger(field2, "access");
		Operation targetInit = assertHasOperation(field2, "init");
		assertNotSame(srcAccess, targetAccess);
		assertNotSame(srcInit, targetInit);
		
		// execution wires
		RunInstanceWire srcInitRun = assertHasRunInstanceWire(wire, srcAccess, srcInit, "run");
		RunInstanceWire targetInitRun = assertHasRunInstanceWire(wire, targetAccess, targetInit, "run");
		
		// they should have incoming parameters
		ApplicationElementProperty field1value = assertHasApplicationElementProperty(field1, "fieldValue");
		ApplicationElementProperty field2value = assertHasApplicationElementProperty(field2, "fieldValue");
		assertNotSame(field1value, field2value);
		
		assertHasParameterEdge(root, field2value, srcInitRun);
		assertHasParameterEdge(root, field1value, targetInitRun);
		
		// but they should also have condition wires
		assertHasConditionWire(page1, cond, srcInitRun);
		assertHasConditionWire(page1, cond, targetInitRun);
		
		assertNotNull(cw2);
		assertNotNull(srcCw);
		assertNotNull(targetCw);

	}

}
