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
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests the inference of chained sync wires. In particular, SyncWires
 * that also have conditions and parameters connected to them.
 * 
 * @author jmwright
 *
 */
public class ChainedSyncWires extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ROOT + "inference/ChainedSyncWires.iaml");
	}
	
	public void testInference() throws JaxenException {
		Page container = (Page) queryOne(root, "//iaml:children[iaml:name='container']");
		
		Page page1 = (Page) queryOne(container, "iaml:children[iaml:name='page1']");
		Page page2 = (Page) queryOne(container, "iaml:children[iaml:name='page2']");
		Page page3 = (Page) queryOne(container, "iaml:children[iaml:name='page3']");
		
		SyncWire sw1 = (SyncWire) getWireBidirectional(container, page1, page2);
		SyncWire sw2 = (SyncWire) getWireBidirectional(container, page2, page3);
		
		Condition cond1 = (Condition) queryOne(container, "iaml:conditions[iaml:name='condition1']");
		Condition cond2 = (Condition) queryOne(container, "iaml:conditions[iaml:name='condition2']");
		
		ConditionWire cw1 = (ConditionWire) getWireFromTo(container, cond1, sw1);
		ConditionWire cw2 = (ConditionWire) getWireFromTo(container, cond2, sw2);
		
		InputTextField param = (InputTextField) queryOne(container, "iaml:children[iaml:name='parameter']");
		ParameterWire pw1 = (ParameterWire) getWireFromTo(container, param, cw2);
		
		// [test inferred elements]
		
		// there should be a sync wire from page1 to page3		
		SyncWire sw3 = (SyncWire) getWireBidirectional(container, page1, page3);
		
		// it should have two condition wires from both conditions
		ConditionWire cw3 = (ConditionWire) getWireFromTo(container, cond1, sw3);
		ConditionWire cw4 = (ConditionWire) getWireFromTo(container, cond2, sw3);
		
		// the second condition wire should have a parameter
		ParameterWire pw2 = (ParameterWire) getWireFromTo(container, param, cw4);

		// perhaps: investigate the RunInstanceWires and make sure
		// the conditions/parameters are also copied onto these 
		// (but probably not necessary)
		
		assertNotNull(cw1);
		assertNotNull(cw2);
		assertNotNull(cw3);
		assertNotNull(cw4);
		assertNotNull(pw1);
		assertNotNull(pw2);
		
	}
	
}
