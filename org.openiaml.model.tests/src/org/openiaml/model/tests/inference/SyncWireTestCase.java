/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SyncWire;

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

	public void testUpdateContents() throws JaxenException {
		// get all 'update' operations
		List<?> updates = query(root, "//iaml:operations[iaml:name='update']");

		// there are 4 input texts => there should be at least 4 update operations
		assertGreaterEq(4, updates.size());

		int i = 0;
		for (Object obj : updates) {
			i++;
			String prelude = "'update' operation #" + i;
			CompositeOperation update = (CompositeOperation) obj;	// should be a composite operation
			assertEquals(prelude, update.getName(), "update");

			// -- traverse from start node --
			StartNode start = assertHasStartNode(update);
			FinishNode finish = assertHasFinishNode(update);
			CancelNode cancel = assertHasCancelNode(update);

			DecisionOperation check = assertHasDecisionOperation(update, "can cast?");
			CastNode cast = assertHasCastNode(update);

			PrimitiveOperation set = assertHasPrimitiveOperation(update, "setPropertyToValue");

			assertHasExecutionEdge(update, start, check);
			assertHasExecutionEdge(update, check, cancel, "no");
			assertHasExecutionEdge(update, check, set, "yes");
			assertHasExecutionEdge(update, set, finish);

			// data flow edges
			Parameter param = assertHasParameter(update, "setValueTo");
			assertHasDataFlowEdge(update, param, cast);
			assertHasDataFlowEdge(update, cast, check);
			assertHasDataFlowEdge(update, cast, set);

			assertEquals(1, set.getOutFlows().size());
			Property f2 = (Property) set.getOutFlows().get(0).getTo();
			assertEquals("fieldValue", f2.getName());
		}
	}

	public void testSyncWire1() throws JaxenException {
		// get the first sync wire
		List<?> syncWires = query(root, "//iaml:wires[iaml:name='sync1']");
		assertEquals(1, syncWires.size());	// there is only one

		SyncWire wire = (SyncWire) syncWires.get(0);		// get the first one

		// get the referenced operations of sync1
		InputTextField name1 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name1']");
		InputTextField name2 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name2']");

		EventTrigger name1edit = name1.getOnChange();
		EventTrigger name2edit = name2.getOnChange();

		Operation name1update = assertHasOperation(name1, "update");
		Operation name2update = assertHasOperation(name2, "update");

		Property name1value = assertHasProperty(name1, "fieldValue");
		Property name2value = assertHasProperty(name2, "fieldValue");

		// none of these can ever be null because queryOne() also calls assert(size>1)

		// this wire should contain at least 4 wires
		/*
		 *   [name1]         [name2]
		 *  edit ------------> update
		 *  update <---------- edit
		 *
		 * + parameter wires for both
		 */
		//assertGreaterEq(4, wire.getWires().size());

		// run instance wires
		Action name1editRun = null;
		Action name2editRun = null;
		ParameterEdge name1editParam = null;
		ParameterEdge name2editParam = null;
		// get RunActions first
		for (Action w : wire.getActions()) {
			if (w instanceof RunAction) {
				if (w.getFrom().equals(name1edit) && w.getTo().equals(name2update) )
					name1editRun = w;
				if (w.getFrom().equals(name2edit) && w.getTo().equals(name1update) )
					name2editRun = w;
			}
		}
		// then ParameterWires
		for (ParameterEdge w : wire.getParameterEdges()) {
			if (w.getFrom().equals(name1value) && w.getTo().equals(name1editRun) )
				name1editParam = w;
			if (w.getFrom().equals(name2value) && w.getTo().equals(name2editRun) )
				name2editParam = w;
		}

		// make sure we've got all of these
		assertNotNull( "name1editRun not null", name1editRun );
		assertNotNull( "name2editRun not null", name2editRun );
		assertNotNull( "name1editParam not null", name1editParam );
		assertNotNull( "name2editParam not null", name2editParam );

	}

}
