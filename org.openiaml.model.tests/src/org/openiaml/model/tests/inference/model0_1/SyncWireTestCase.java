/**
 *
 */
package org.openiaml.model.tests.inference.model0_1;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests inference of sync wires.
 * The model test case is of name1<-->name2<-->name3<-->name4.
 *
 * @author jmwright
 *
 */
public class SyncWireTestCase extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncWireTestCase.class);
	}

	public void testName1toName2() throws JaxenException {
		// name1 should have a sync wire to name2
		Frame page = assertHasFrame(root, "on-page");
		assertNotGenerated(page);
		InputTextField name1 = assertHasInputTextField(page, "name1");
		assertNotGenerated(name1);
		InputTextField name2 = assertHasInputTextField(page, "name2");
		assertNotGenerated(name2);

		// these elements should now have generated elements that match
		// the semantics specified in our .vsd file
		assertHasOperation(name1, "update");
	}

	public void testSyncWire1() throws JaxenException {
		// get the first sync wire
		List<?> syncWires = query(root, "//iaml:wires[iaml:name='sync1']");
		assertEquals(1, syncWires.size());	// there is only one

		SyncWire wire = (SyncWire) syncWires.get(0);		// get the first one

		// get the referenced operations of sync1
		InputTextField name1 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name1']");
		InputTextField name2 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name2']");

		Event name1edit = name1.getOnChange();
		Event name2edit = name2.getOnChange();

		Operation name1update = assertHasOperation(name1, "update");
		Operation name2update = assertHasOperation(name2, "update");

		Value name1value = assertHasFieldValue(name1);
		Value name2value = assertHasFieldValue(name2);

		// none of these can ever be null because queryOne() also calls assert(size>1)

		// this wire should contain at least 4 wires
		/*
		 *   [name1]         [name2]
		 *  edit ------------> update
		 *  update <---------- edit
		 *
		 * + ActivityParameter wires for both
		 */
		//assertGreaterEq(4, wire.getWires().size());

		// run instance wires
		ECARule name1editRun = null;
		ECARule name2editRun = null;
		Parameter name1editParam = null;
		Parameter name2editParam = null;
		// get RunActions first
		for (ECARule w : wire.getEcaRules()) {
			if (w.getTrigger().equals(name1edit) && w.getTarget().equals(name2update) )
				name1editRun = w;
			if (w.getTrigger().equals(name2edit) && w.getTarget().equals(name1update) )
				name2editRun = w;
		}
		// then ParameterWires
		for (Parameter w : wire.getParameterEdges()) {
			if (w.getTerm().equals(name1value) && w.getValue().equals(name1editRun) )
				name1editParam = w;
			if (w.getTerm().equals(name2value) && w.getValue().equals(name2editRun) )
				name2editParam = w;
		}

		// make sure we've got all of these
		assertNotNull( "name1editRun not null", name1editRun );
		assertNotNull( "name2editRun not null", name2editRun );
		assertNotNull( "name1editParam not null", name1editParam );
		assertNotNull( "name2editParam not null", name2editParam );

	}

}
