/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of ConditionWires when attached to SyncWires.
 * 
 * @author jmwright
 *
 */
public class ConditionWireFalse extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ROOT + "inference/ConditionWireFalse.iaml");
	}
	
	public void testInference() throws JaxenException {
		Page page = (Page) queryOne(root, "//iaml:children[iaml:name='container']");
		InputTextField source = (InputTextField) queryOne(page, "//iaml:children[iaml:name='source']");
		InputTextField target = (InputTextField) queryOne(page, "//iaml:children[iaml:name='target']");
		SyncWire wire = (SyncWire) queryOne(page, "//iaml:wires[iaml:name='sync']");
		Condition cond = (Condition) queryOne(page, "//iaml:conditions[iaml:name='Always False']");
		ConditionWire cw = (ConditionWire) queryOne(page, "//iaml:wires[iaml:name='condition']");
		
		// [already in model]
		// there should be a condition wire from cond to sync
		assertEquals(cw.getFrom(), cond);
		assertEquals(cw.getTo(), wire);
		
		// [inferred]
		// we should have EventTrigger 'edit' in source
		EventTrigger srcEdit = (EventTrigger) queryOne(source, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(source, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(target, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(target, "iaml:operations[iaml:name='update']");
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
