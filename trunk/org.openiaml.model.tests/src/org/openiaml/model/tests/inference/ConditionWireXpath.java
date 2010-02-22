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
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of the ConditionWires involved with dynamic xpath sets.
 *
 * @author jmwright
 *
 */
public class ConditionWireXpath extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireXpath.class);
	}

	public void testInference() throws JaxenException {
		Frame page1 = assertHasFrame(root, "page1");
		Frame page2 = assertHasFrame(root, "page2");
		DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "xpath");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

		InputTextField field1 = assertHasInputTextField(page1, "target");
		InputTextField field2 = assertHasInputTextField(page2, "target");
		assertNotSame(field1, field2);

		// this is all that is initially in there

		// the XPathSet should create an XPath condition
		CompositeCondition cond = assertHasCompositeCondition(dae, "xpath");

		// the XPathSet should create SyncWire matches between all pages
		SyncWire wireGen = (SyncWire) getWireBidirectional(root, page1, page2);

		// this SyncWire should have a ConditionWire connected to this condition
		assertGenerated(assertHasConditionEdge(root, cond, wireGen));

		// we can now investigate the SyncWires themselves, and make sure
		// they have the conditions attached too
		EventTrigger srcEdit = assertHasEventTrigger(field1, "edit");
		Operation srcOp = assertHasOperation(field1, "update");
		EventTrigger targetEdit = assertHasEventTrigger(field2, "edit");
		Operation targetOp = assertHasOperation(field2, "update");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);

		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);

		// there should be additional ConditionWires to these RunInstanceWires
		ConditionEdge srcCw = assertHasConditionEdge(page1, cond, srcRw);
		ConditionEdge targetCw = assertHasConditionEdge(page1, cond, targetRw);

		// all the ConditionWires need parameters: the XPath source, and the element to evaluate
		ParameterEdge param1 = getParameterEdgeFromTo(root, dae, srcCw);
		ParameterEdge param2 = getParameterEdgeFromTo(root, dae, targetCw);

		/**
		 * cannot test these now, since cond->src will have TWO wires.
		 * @see ConditionWireXpath2
		 */
		// ParameterWire param3 = (ParameterWire) getWireFromTo(root, page2, srcCw);
		// ParameterWire param4 = (ParameterWire) getWireFromTo(root, page2, targetCw);

		/*
		 * An interesting idea is that if we have:
		 *
		 *  p1 <--sync--> dae[//*]
		 *  p2
		 *
		 * Then we will never have a ConditionWire from p2 to p1 which tests
		 * [//*] (since we create p1<--sync-->p2).
		 *
		 * BUT this is OK, because dae[//*] searches for all pages that match
		 * the expression EXCEPT for p1, because this is included already.
		 * (i.e. p1 will match anyway.)
		 */

		// even though param1 and param2 were generated,
		assertTrue(param1.isIsGenerated());
		assertTrue(param2.isIsGenerated());

		// they shouldn't have any generated rule sources connected to them
		assertNull(param1.getGeneratedRule());
		assertNull(param2.getGeneratedRule());

	}

}
