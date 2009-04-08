/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCaseWithConditionWires;

/**
 * Tests inference of the ConditionWires involved with dynamic xpath sets.
 * In particular, this test case looks into when we have an additional page, page3.
 * 
 * TODO reformat source code
 * 
 * @author jmwright
 *
 */
public class ConditionWireXpathThreePages extends InferenceTestCaseWithConditionWires {

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
    
    // condition wires all over!
    ConditionWire cw1_2 = (ConditionWire) getWireFromTo(page1, cond, rw1_2);
    ConditionWire cw2_1 = (ConditionWire) getWireFromTo(page1, cond, rw2_1);
    ConditionWire cw1_3 = (ConditionWire) getWireFromTo(page1, cond, rw1_3);
    ConditionWire cw3_1 = (ConditionWire) getWireFromTo(page1, cond, rw3_1);
    // these elements will have *two* condition wires
    /*
    ConditionWire cw2_3 = (ConditionWire) getWireFromTo(page1, cond, rw2_3);
    ConditionWire cw3_2 = (ConditionWire) getWireFromTo(page1, cond, rw3_2);
    */

    // for p1<-->p2 and p1<-->p3, since page1 is connected directly with
    // the DynamicSet, p1 should not be a condition in any sync wire
    checkNotConditionParameter(root, cond, rw1_2, page1);
    checkNotConditionParameter(root, cond, rw2_1, page1);
    checkNotConditionParameter(root, cond, rw1_3, page1);
    checkNotConditionParameter(root, cond, rw3_1, page1);
    
    // and parameters connecting up the sets...
    ParameterWire pw1_2 = (ParameterWire) getWireFromTo(root, dae, cw1_2);
    ParameterWire pw2_1 = (ParameterWire) getWireFromTo(root, dae, cw2_1);
    ParameterWire pw1_3 = (ParameterWire) getWireFromTo(root, dae, cw1_3);
    ParameterWire pw3_1 = (ParameterWire) getWireFromTo(root, dae, cw3_1);
    /*
    ParameterWire pw2_3 = (ParameterWire) getWireFromTo(root, dae, cw2_3);
    ParameterWire pw3_2 = (ParameterWire) getWireFromTo(root, dae, cw3_2);
    */
    
    // ... and the target elements 
    ParameterWire pw1_2b = (ParameterWire) getWireFromTo(root, page2, cw1_2);
    ParameterWire pw2_1b = (ParameterWire) getWireFromTo(root, page2, cw2_1);
    ParameterWire pw1_3b = (ParameterWire) getWireFromTo(root, page3, cw1_3);
    ParameterWire pw3_1b = (ParameterWire) getWireFromTo(root, page3, cw3_1);
    /*
    ParameterWire pw2_3b = (ParameterWire) getWireFromTo(root, page3, cw2_3);
    ParameterWire pw3_2b = (ParameterWire) getWireFromTo(root, page2, cw3_2);
    */
    
    // there should only be two parameters in these condition wires
    checkParameterCount(2, cw1_2);
    checkParameterCount(2, cw2_1);
    checkParameterCount(2, cw1_3);
    checkParameterCount(2, cw3_1);
      
  }
  
}
