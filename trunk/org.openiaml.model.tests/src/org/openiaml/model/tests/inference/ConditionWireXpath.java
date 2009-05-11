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
		Page page1 = (Page) queryOne(root, "//iaml:children[iaml:name='page1']");
		Page page2 = (Page) queryOne(root, "//iaml:children[iaml:name='page2']");
		DynamicApplicationElementSet dae = (DynamicApplicationElementSet) queryOne(root, "//iaml:children[iaml:name='xpath']");
		SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

		InputTextField field1 = (InputTextField) queryOne(page1, "iaml:children[iaml:name='target']");
		InputTextField field2 = (InputTextField) queryOne(page2, "iaml:children[iaml:name='target']");
		assertNotSame(field1, field2);
		
		// this is all that is initially in there
		
		// the XPathSet should create an XPath condition
		CompositeCondition cond = (CompositeCondition) queryOne(dae, "iaml:conditions[iaml:name='xpath']");
		 
		// the XPathSet should create SyncWire matches between all pages
		SyncWire wireGen = (SyncWire) getWireBidirectional(root, page1, page2);
		
		// this SyncWire should have a ConditionWire connected to this condition
		ConditionWire cw = (ConditionWire) getWireFromTo(root, cond, wireGen);
		assertNotNull(cw);
		
		// we can now investigate the SyncWires themselves, and make sure
		// they have the conditions attached too
		EventTrigger srcEdit = (EventTrigger) queryOne(field1, "iaml:eventTriggers[iaml:name='edit']");
		Operation srcOp = (Operation) queryOne(field1, "iaml:operations[iaml:name='update']");
		EventTrigger targetEdit = (EventTrigger) queryOne(field2, "iaml:eventTriggers[iaml:name='edit']");
		Operation targetOp = (Operation) queryOne(field2, "iaml:operations[iaml:name='update']");
		assertNotSame(srcEdit, targetEdit);
		assertNotSame(srcOp, targetOp);
		
		// there should be a run wire between these two
		RunInstanceWire srcRw = (RunInstanceWire) getWireFromTo(wire, srcEdit, targetOp);
		RunInstanceWire targetRw = (RunInstanceWire) getWireFromTo(wire, targetEdit, srcOp);
		
		// there should be additional ConditionWires to these RunInstanceWires
		ConditionWire srcCw = (ConditionWire) getWireFromTo(page1, cond, srcRw); 
		ConditionWire targetCw = (ConditionWire) getWireFromTo(page1, cond, targetRw);
		
		// all the ConditionWires need parameters: the XPath source, and the element to evaluate
		ParameterWire param1 = (ParameterWire) getWireFromTo(root, dae, srcCw);
		ParameterWire param2 = (ParameterWire) getWireFromTo(root, dae, targetCw);
		
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
		
		assertNotNull(srcCw);
		assertNotNull(targetCw);
		assertNotNull(param1);
		assertNotNull(param2);
		/*
		assertNotNull(param3);
		assertNotNull(param4);
		*/
		
		// even though param1 and param2 were generated,
		assertTrue(param1.isIsGenerated());
		assertTrue(param2.isIsGenerated());
		
		// they shouldn't have any generated rule sources connected to them
		assertNull(param1.getGeneratedRule());
		assertNull(param2.getGeneratedRule());

	}
	
}
