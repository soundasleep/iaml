/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunInstanceWire;
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
		Operation update = assertHasOperation(name1, "update");
		List<?> nodes = query(update, "iaml:nodes");
		assertEquals(nodes.size(), 2);
		assertTrue(nodes.get(0) instanceof StartNode);
		assertTrue(nodes.get(1) instanceof FinishNode);
	}

	public void testAllUpdates() throws JaxenException {
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

			// has a start node
			List<?> nodes = query(update, "iaml:nodes");
			assertEquals(prelude, nodes.size(), 2);
			assertTrue(prelude, nodes.get(0) instanceof StartNode);
			assertTrue(prelude, nodes.get(1) instanceof FinishNode);

			// -- traverse from start node --
			StartNode start = (StartNode) nodes.get(0);
			FinishNode stop = (FinishNode) nodes.get(1);

			// start node should go to 'setPropertyToValue'
			assertEquals(prelude, start.getOutExecutions().size(), 1);
			PrimitiveOperation setProp = (PrimitiveOperation) start.getOutExecutions().get(0).getTo();
			assertEquals(prelude, setProp.getName(), "setPropertyToValue");

			// setProperty should have one node: a parameter
			assertEquals(prelude, setProp.getInFlows().size(), 1);
			assertTrue(prelude, setProp.getInFlows().get(0).getFrom() instanceof Parameter);

			// setProperty should flow out to ApplicationElementProperty
			assertEquals(prelude, setProp.getOutFlows().size(), 1);
			Property outProp = (Property) setProp.getOutFlows().get(0).getTo();
			assertEquals(prelude, outProp.getName(), "fieldValue");

			// this property should belong to an application element with a different name
			assertNotSame(prelude, ((NamedElement) outProp.eContainer()).getName(), "name" + i);

			// finally, the op should go to the stop node above
			assertEquals(prelude, setProp.getOutExecutions().size(), 1);
			FinishNode finalNode = (FinishNode) setProp.getOutExecutions().get(0).getTo();

			assertEquals(prelude, stop, finalNode);
		}
	}

	public void testWires() throws JaxenException {
		// get all 'update' operations
		//List<Object> syncWires = query(root, "//iaml:wires[xsi:type='iaml.wires:SyncWire']");
		List<?> syncWires = query(root, "//iaml:wires[contains(iaml:name, 'sync') and contains(eClass(), 'SyncWire')]");

		// there are exactly three sync wires
		assertEquals(3, syncWires.size());
	}

	public void testSyncWire1() throws JaxenException {
		// get the first sync wire
		List<?> syncWires = query(root, "//iaml:wires[iaml:name='sync1']");
		assertEquals(1, syncWires.size());	// there is only one

		SyncWire wire = (SyncWire) syncWires.get(0);		// get the first one

		// get the referenced operations of sync1
		InputTextField name1 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name1']");
		InputTextField name2 = (InputTextField) queryOne(root, "//iaml.visual:children[iaml:name='name2']");

		EventTrigger name1edit = name1.getOnEdit();
		EventTrigger name2edit = name2.getOnEdit();

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
		// get RunInstanceWires first
		for (Action w : wire.getActions()) {
			if (w instanceof RunInstanceWire) {
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
