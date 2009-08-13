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

  protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireXpathThreePages.class, true);
  }

  public void testInference() throws JaxenException {
    Page page1 = assertHasPage(root, "page1");
    Page page2 = assertHasPage(root, "page2");
    Page page3 = assertHasPage(root, "page3");
    DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "xpath");
    SyncWire wire = (SyncWire) getWireBidirectional(root, page1, dae);

    InputTextField field1 = assertHasInputTextField(page1, "target");
    InputTextField field2 = assertHasInputTextField(page2, "target");
    InputTextField field3 = assertHasInputTextField(page3, "target");
    assertNotSame(field1, field2);
    assertNotSame(field2, field3);

    // this is all that is initially in there

    // the XPathSet should create an XPath condition
    CompositeCondition cond = assertHasCompositeCondition(dae, "xpath");

    // the XPathSet should create SyncWire matches between all pages
    assertHasWiresBidirectional(1, root, page1, page2);	// only 1 sync wire
    SyncWire sw1 = (SyncWire) getWireBidirectional(root, page1, page2);
    assertHasWiresBidirectional(1, root, page1, page3);	// only 1 sync wire
    SyncWire sw2 = (SyncWire) getWireBidirectional(root, page1, page3);

    // there should NOT be a SyncWire between page2 and page3
    assertNoWireBidirectional(root, page2, page3);

    // there should be a ConditionWire between cond and each sync wire
    assertHasWiresFromTo(1, root, cond, sw1);
    assertHasWiresFromTo(1, root, cond, sw2);

    // there should only be two parameters into each condition wire
    ConditionWire cw_sw1 = (ConditionWire) getWireFromTo(root, cond, sw1);
    assertHasParameterWireCount(2, cw_sw1);
    ConditionWire cw_sw2 = (ConditionWire) getWireFromTo(root, cond, sw2);
    assertHasParameterWireCount(2, cw_sw2);

    // we can now investigate the SyncWires themselves, and make sure
    // they have the conditions attached too
    EventTrigger f1edit = assertHasEventTrigger(field1, "edit");
    EventTrigger f2edit = assertHasEventTrigger(field2, "edit");
    EventTrigger f3edit = assertHasEventTrigger(field3, "edit");
    Operation f1update = assertHasOperation(field1, "update");
    Operation f2update = assertHasOperation(field2, "update");
    Operation f3update = assertHasOperation(field3, "update");
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
    assertNotParameterWire(root, cond, rw1_2, page1);
    assertNotParameterWire(root, cond, rw2_1, page1);
    assertNotParameterWire(root, cond, rw1_3, page1);
    assertNotParameterWire(root, cond, rw3_1, page1);

    // and parameters connecting up the sets... (only one per dae --> condition wire)
    assertHasWiresFromTo(1, root, dae, cw1_2);
    ParameterWire pw1_2 = (ParameterWire) getWireFromTo(root, dae, cw1_2);
    assertHasWiresFromTo(1, root, dae, cw2_1);
    ParameterWire pw2_1 = (ParameterWire) getWireFromTo(root, dae, cw2_1);
    assertHasWiresFromTo(1, root, dae, cw1_3);
    ParameterWire pw1_3 = (ParameterWire) getWireFromTo(root, dae, cw1_3);
    assertHasWiresFromTo(1, root, dae, cw3_1);
    ParameterWire pw3_1 = (ParameterWire) getWireFromTo(root, dae, cw3_1);
    /*
    ParameterWire pw2_3 = (ParameterWire) getWireFromTo(root, dae, cw2_3);
    ParameterWire pw3_2 = (ParameterWire) getWireFromTo(root, dae, cw3_2);
    */

    // ... and the target elements (only one per page --> condition wire)
    assertHasWiresFromTo(1, root, page2, cw1_2);
    ParameterWire pw1_2b = (ParameterWire) getWireFromTo(root, page2, cw1_2);
    assertHasWiresFromTo(1, root, page2, cw2_1);
    ParameterWire pw2_1b = (ParameterWire) getWireFromTo(root, page2, cw2_1);
    assertHasWiresFromTo(1, root, page3, cw1_3);
    ParameterWire pw1_3b = (ParameterWire) getWireFromTo(root, page3, cw1_3);
    assertHasWiresFromTo(1, root, page3, cw3_1);
    ParameterWire pw3_1b = (ParameterWire) getWireFromTo(root, page3, cw3_1);
    /*
    ParameterWire pw2_3b = (ParameterWire) getWireFromTo(root, page3, cw2_3);
    ParameterWire pw3_2b = (ParameterWire) getWireFromTo(root, page2, cw3_2);
    */

    // there should only be two parameters in these condition wires
    assertHasParameterWireCount(2, cw1_2);
    assertHasParameterWireCount(2, cw2_1);
    assertHasParameterWireCount(2, cw1_3);
    assertHasParameterWireCount(2, cw3_1);

  }

}
