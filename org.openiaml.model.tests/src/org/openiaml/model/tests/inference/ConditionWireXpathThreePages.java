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
import org.openiaml.model.model.wires.RunAction;
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

  @Override
protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ConditionWireXpathThreePages.class, true);
  }

  public void testInference() throws JaxenException {
    Frame page1 = assertHasFrame(root, "page1");
    Frame page2 = assertHasFrame(root, "page2");
    Frame page3 = assertHasFrame(root, "page3");
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
    SyncWire sw1 = assertHasSyncWire(root, page1, page2);
    SyncWire sw2 = assertHasSyncWire(root, page1, page3);

    // there should NOT be a SyncWire between page2 and page3
    assertNoWireBidirectional(root, page2, page3);

    // there should be a ConditionWire between cond and each sync wire
    assertHasConditionEdge(root, cond, sw1);
    assertHasConditionEdge(root, cond, sw2);

    // there should only be two parameters into each condition wire
    ConditionEdge cw_sw1 = assertHasConditionEdge(root, cond, sw1);
    assertEquals(cw_sw1.toString(), 2, cw_sw1.getInParameterEdges().size());
    ConditionEdge cw_sw2 = assertHasConditionEdge(root, cond, sw2);
    assertEquals(cw_sw2.toString(), 2, cw_sw2.getInParameterEdges().size());

    // we can now investigate the SyncWires themselves, and make sure
    // they have the conditions attached too
    EventTrigger f1edit = field1.getOnChange();
    EventTrigger f2edit = field2.getOnChange();
    EventTrigger f3edit = field3.getOnChange();
    Operation f1update = assertHasOperation(field1, "update");
    Operation f2update = assertHasOperation(field2, "update");
    Operation f3update = assertHasOperation(field3, "update");
    assertNotSame(f1edit, f2edit);
    assertNotSame(f3edit, f2edit);
    assertNotSame(f1update, f2update);
    assertNotSame(f3update, f2update);

    // there should be a run wire between these
    RunAction rw1_2 = assertHasRunAction(wire, f1edit, f2update);
    RunAction rw2_1 = assertHasRunAction(wire, f2edit, f1update);
    RunAction rw1_3 = assertHasRunAction(wire, f1edit, f3update);
    RunAction rw3_1 = assertHasRunAction(wire, f3edit, f1update);

    // condition wires all over!
    ConditionEdge cw1_2 = assertHasConditionEdge(page1, cond, rw1_2);
    ConditionEdge cw2_1 = assertHasConditionEdge(page1, cond, rw2_1);
    ConditionEdge cw1_3 = assertHasConditionEdge(page1, cond, rw1_3);
    ConditionEdge cw3_1 = assertHasConditionEdge(page1, cond, rw3_1);
    // these elements will have *two* condition wires
    /*
    ConditionWire cw2_3 = (ConditionWire) getWireFromTo(page1, cond, rw2_3);
    ConditionWire cw3_2 = (ConditionWire) getWireFromTo(page1, cond, rw3_2);
    */

    // for p1<-->p2 and p1<-->p3, since page1 is connected directly with
    // the DynamicSet, p1 should not be a condition in any sync wire
    assertNoParamtersToConditionEdges(root, cond, rw1_2, page1);
    assertNoParamtersToConditionEdges(root, cond, rw2_1, page1);
    assertNoParamtersToConditionEdges(root, cond, rw1_3, page1);
    assertNoParamtersToConditionEdges(root, cond, rw3_1, page1);

    // and parameters connecting up the sets... (only one per dae --> condition wire)
    assertGenerated(getParameterEdgeFromTo(root, dae, cw1_2));
    assertGenerated(getParameterEdgeFromTo(root, dae, cw2_1));
    assertGenerated(getParameterEdgeFromTo(root, dae, cw1_3));
    assertGenerated(getParameterEdgeFromTo(root, dae, cw3_1));
    /*
    ParameterWire pw2_3 = (ParameterWire) getWireFromTo(root, dae, cw2_3);
    ParameterWire pw3_2 = (ParameterWire) getWireFromTo(root, dae, cw3_2);
    */

    // ... and the target elements (only one per page --> condition wire)
    assertGenerated(getParameterEdgeFromTo(root, page2, cw1_2));
    assertGenerated(getParameterEdgeFromTo(root, page2, cw2_1));
    assertGenerated(getParameterEdgeFromTo(root, page3, cw1_3));
    assertGenerated(getParameterEdgeFromTo(root, page3, cw3_1));
    /*
    ParameterWire pw2_3b = (ParameterWire) getWireFromTo(root, page3, cw2_3);
    ParameterWire pw3_2b = (ParameterWire) getWireFromTo(root, page2, cw3_2);
    */

    // there should only be two parameters in these condition wires
    assertEquals(cw1_2.toString(), 2, cw1_2.getInParameterEdges().size());
    assertEquals(cw2_1.toString(), 2, cw2_1.getInParameterEdges().size());
    assertEquals(cw1_3.toString(), 2, cw1_3.getInParameterEdges().size());
    assertEquals(cw3_1.toString(), 2, cw3_1.getInParameterEdges().size());

  }

}
