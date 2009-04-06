/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.Set;

import junit.framework.AssertionFailedError;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of the ConditionWires involved with dynamic xpath sets.
 * In particular, this test case looks into when we have an additional page, page3.
 * 
 * @author jmwright
 *
 */
public class ConditionWireXpath2 extends InferenceTestCase {

  protected InternetApplication root;
  
  protected void setUp() throws Exception {
    root = loadAndInfer(ROOT + "inference/ConditionWireXpath2.iaml");
  }
  
  public void testInference() throws JaxenException {
    Page page1 = (Page) queryOne(root, "//iaml:children[iaml:name='page1']");
    Page page2 = (Page) queryOne(root, "//iaml:children[iaml:name='page2']");
    Page page3 = (Page) queryOne(root, "//iaml:children[iaml:name='page3']");
    DynamicApplicationElementSet dae = (DynamicApplicationElementSet) queryOne(root, "//iaml:children[iaml:name='xpath']");
    SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

    InputTextField field1 = (InputTextField) queryOne(page1, "iaml:children[iaml:name='target']");
    InputTextField field2 = (InputTextField) queryOne(page2, "iaml:children[iaml:name='target']");
    InputTextField field3 = (InputTextField) queryOne(page3, "iaml:children[iaml:name='target']");
    assertNotSame(field1, field2);
    assertNotSame(field2, field3);
    
    // this is all that is initially in there
    
    // the XPathSet should create an XPath condition
    CompositeCondition cond = (CompositeCondition) queryOne(dae, "iaml:conditions[iaml:name='xpath']");
     
    // the XPathSet should create SyncWire matches between all pages
    SyncWire sw1 = (SyncWire) getWireBidirectional(root, page1, page2);
    SyncWire sw2 = (SyncWire) getWireBidirectional(root, page1, page3);
    SyncWire sw_chained = (SyncWire) getWireBidirectional(root, page2, page3);
    
    // we can now investigate the SyncWires themselves, and make sure
    // they have the conditions attached too
    EventTrigger f1edit = (EventTrigger) queryOne(field1, "iaml:eventTriggers[iaml:name='edit']");
    EventTrigger f2edit = (EventTrigger) queryOne(field2, "iaml:eventTriggers[iaml:name='edit']");
    EventTrigger f3edit = (EventTrigger) queryOne(field3, "iaml:eventTriggers[iaml:name='edit']");
    Operation f1update = (Operation) queryOne(field1, "iaml:operations[iaml:name='update']");
    Operation f2update = (Operation) queryOne(field2, "iaml:operations[iaml:name='update']");
    Operation f3update = (Operation) queryOne(field3, "iaml:operations[iaml:name='update']");
    assertNotSame(f1edit, f2edit);
    assertNotSame(f3edit, f2edit);
    assertNotSame(f1update, f2update);
    assertNotSame(f3update, f2update);
    
    // there should be a run wire between these
    RunInstanceWire rw1_2 = (RunInstanceWire) getWireFromTo(wire, f1edit, f2update);
    RunInstanceWire rw2_1 = (RunInstanceWire) getWireFromTo(wire, f2edit, f1update);
    RunInstanceWire rw1_3 = (RunInstanceWire) getWireFromTo(wire, f1edit, f3update);
    RunInstanceWire rw3_1 = (RunInstanceWire) getWireFromTo(wire, f3edit, f1update);
    RunInstanceWire rw2_3 = (RunInstanceWire) getWireFromTo(wire, f2edit, f3update);
    RunInstanceWire rw3_2 = (RunInstanceWire) getWireFromTo(wire, f3edit, f2update);
    
    // condition wires all over!
    ConditionWire cw1_2 = (ConditionWire) getWireFromTo(page1, cond, rw1_2);
    ConditionWire cw2_1 = (ConditionWire) getWireFromTo(page1, cond, rw2_1);
    ConditionWire cw1_3 = (ConditionWire) getWireFromTo(page1, cond, rw1_3);
    ConditionWire cw3_1 = (ConditionWire) getWireFromTo(page1, cond, rw3_1);
    // these elements will have *two* condition wires
    //ConditionWire cw2_3 = (ConditionWire) getWireFromTo(page1, cond, rw2_3);
    //ConditionWire cw3_2 = (ConditionWire) getWireFromTo(page1, cond, rw3_2);
   
    // and parameters connecting up the sets...
    ParameterWire pw1_2 = (ParameterWire) getWireFromTo(root, dae, cw1_2);
    ParameterWire pw2_1 = (ParameterWire) getWireFromTo(root, dae, cw2_1);
    ParameterWire pw1_3 = (ParameterWire) getWireFromTo(root, dae, cw1_3);
    ParameterWire pw3_1 = (ParameterWire) getWireFromTo(root, dae, cw3_1);
    //ParameterWire pw2_3 = (ParameterWire) getWireFromTo(root, dae, cw2_3);
    //ParameterWire pw3_2 = (ParameterWire) getWireFromTo(root, dae, cw3_2);
    
    // ... and the source elements 
    ParameterWire pw1_2b = (ParameterWire) getWireFromTo(root, page2, cw1_2);
    ParameterWire pw2_1b = (ParameterWire) getWireFromTo(root, page1, cw2_1);
    ParameterWire pw1_3b = (ParameterWire) getWireFromTo(root, page3, cw1_3);
    ParameterWire pw3_1b = (ParameterWire) getWireFromTo(root, page1, cw3_1);
    //ParameterWire pw2_3b = (ParameterWire) getWireFromTo(root, page3, cw2_3);
    //ParameterWire pw3_2b = (ParameterWire) getWireFromTo(root, page2, cw3_2);
    
    // at the very least, the chained sync wire (page2<--sync-->page3)
    // should have two condition wires incoming
    getConditionWireFromToWithParameters(root, cond, sw_chained, dae, page2, page3);

    //getConditionWireFromToWithParameters(root, cond, rw1_2, dae, page1, page2);
    //getConditionWireFromToWithParameters(root, cond, rw2_1, dae, page2, page1);
    //getConditionWireFromToWithParameters(root, cond, rw1_3, dae, page1, page3);
    //getConditionWireFromToWithParameters(root, cond, rw3_1, dae, page3, page1);
    getConditionWireFromToWithParameters(root, cond, rw2_3, dae, page2, page3);
    getConditionWireFromToWithParameters(root, cond, rw3_2, dae, page3, page2);
        
  }

	/**
	 * @param root2
	 * @param cond
	 * @param rw1_2
	 * @param dae
	 * @param page1
	 * @param page2
	 * @throws JaxenException 
	 */
	private void getConditionWireFromToWithParameters(InternetApplication root,
			CompositeCondition cond, RunInstanceWire rw,
			DynamicApplicationElementSet dae, Page page1, Page page2) throws JaxenException {
		
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		// should only be two conditions: page1 matches xpath, page2 matches xpath
		assertEquals(2, conditions.size());
		
		boolean checksPage1 = false;
		boolean checksPage2 = false;
		
		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;
			
			// all wires should have a parameter from the set (xpath)
			ParameterWire pw_set = (ParameterWire) getWireFromTo(root, dae, cw);
			
			// but one wire should have page1, the other should have page2
			if (hasWireFromTo(root, page1, cw)) {
				checksPage1 = true;
			}
			if (hasWireFromTo(root, page2, cw)) {
				checksPage2 = true;
			}
		}
		
		assertTrue("Page1 should be connected", checksPage1);
		assertTrue("Page2 should be connected", checksPage2);
		
	}


	/**
	 * @param root2
	 * @param cond
	 * @param rw1_2
	 * @param dae
	 * @param page1
	 * @param page2
	 * @throws JaxenException 
	 */
	private void getConditionWireFromToWithParameters(InternetApplication root,
			CompositeCondition cond, SyncWire rw,
			DynamicApplicationElementSet dae, Page page1, Page page2) throws JaxenException {
		
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		// should only be two condition: page1 matches xpath, page2 matches xpath
		assertEquals(2, conditions.size());
		
		boolean checksPage1 = false;
		boolean checksPage2 = false;
		
		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;
			
			// all wires should have a parameter from the set (xpath)
			ParameterWire pw_set = (ParameterWire) getWireFromTo(root, dae, cw);
			
			// but one wire should have page1, the other should have page2
			if (hasWireFromTo(root, page1, cw)) {
				checksPage1 = true;
			}
			if (hasWireFromTo(root, page2, cw)) {
				checksPage2 = true;
			}
		}
		
		assertTrue("Page1 should be connected", checksPage1);
		assertTrue("Page2 should be connected", checksPage2);
		
	}
	/**
	 * @param root2
	 * @param page1
	 * @param cw
	 * @return
	 * @throws JaxenException 
	 */
	private boolean hasWireFromTo(InternetApplication root2, Page page1,
			ConditionWire cw) throws JaxenException {
		try {
			getWireFromTo(root2, page1, cw);
			return true;
		} catch (AssertionFailedError e) {
			return false;
		}
	}
  
  /*
	protected void assertConnections(InternetApplication root, Condition cond, RunInstanceWire rw, DynamicApplicationElementSet dae, Page page1, Page page2) throws JaxenException {
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		assertEquals(2, conditions.size());

		boolean hasPage1 = false;
		boolean hasPage2 = false;
		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;  
			getWireFromTo(root, dae, cw);
			Set<WireEdge> in = getWiresTo(root, cw);
			for (WireEdge w : in) {
				if (w.getFrom().equals(page1)) {
					assertFalse(hasPage1);
					hasPage1 = true;
				}
				if (w.getFrom().equals(page2)) {
					assertFalse(hasPage2);
					hasPage1 = true;
				}
			}
		}
		
		assertTrue(hasPage1);
		assertTrue(hasPage2);
	    
	}
	*/
  	
  
}
