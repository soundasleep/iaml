/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of sync wires.
 * The model test case is of name1<-->name2<-->name3<-->name4.
 * 
 * @model models/test-sync.iaml
 * @author jmwright
 *
 */
public class SyncWireTestCase extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(SyncWireTestCase.class);
	}
	
	public void testName1toName2() throws JaxenException {
		// name1 should have a sync wire to name2
		EObject name1 = queryOne(root, "iaml:children[iaml:name='on-page']/iaml:children[iaml:name='name1']");
		assertTrue(name1 instanceof InputTextField);
		EObject name2 = queryOne(root, "iaml:children[iaml:name='on-page']/iaml:children[iaml:name='name2']");
		assertTrue(name2 instanceof InputTextField);
		
		// these elements should now have generated elements that match
		// the semantics specified in our .vsd file
		EObject update = queryOne(name1, "iaml:operations[iaml:name='update']");
		assertTrue(update instanceof CompositeOperation);
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
			ChainedOperation setProp = (ChainedOperation) start.getOutExecutions().get(0).getTo();
			assertEquals(prelude, setProp.getName(), "setPropertyToValue");
			
			// setProperty should have one node: a parameter
			assertEquals(prelude, setProp.getInFlows().size(), 1);
			assertTrue(prelude, setProp.getInFlows().get(0).getFrom() instanceof Parameter);
			
			// setProperty should flow out to ApplicationElementProperty
			assertEquals(prelude, setProp.getOutFlows().size(), 1);
			ApplicationElementProperty outProp = (ApplicationElementProperty) setProp.getOutFlows().get(0).getTo();
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
		InputTextField name1 = (InputTextField) queryOne(root, "//iaml:children[iaml:name='name1']");
		InputTextField name2 = (InputTextField) queryOne(root, "//iaml:children[iaml:name='name2']");
		
		EventTrigger name1edit = (EventTrigger) queryOne(name1, "iaml:eventTriggers[iaml:name='edit']");
		EventTrigger name2edit = (EventTrigger) queryOne(name2, "iaml:eventTriggers[iaml:name='edit']");
		
		Operation name1update = (Operation) queryOne(name1, "iaml:operations[iaml:name='update']");
		Operation name2update = (Operation) queryOne(name2, "iaml:operations[iaml:name='update']");
		
		ApplicationElementProperty name1value = (ApplicationElementProperty) queryOne(name1, "iaml:properties[iaml:name='fieldValue']");
		ApplicationElementProperty name2value = (ApplicationElementProperty) queryOne(name2, "iaml:properties[iaml:name='fieldValue']");
		
		// none of these can ever be null because queryOne() also calls assert(size>1)

		// this wire should contain at least 4 wires
		/*
		 *   [name1]         [name2]
		 *  edit ------------> update
		 *  update <---------- edit
		 * 
		 * + parameter wires for both
		 */
		assertGreaterEq(4, wire.getWires().size());
		
		// run instance wires
		WireEdge name1editRun = null;
		WireEdge name2editRun = null;
		WireEdge name1editParam = null;
		WireEdge name2editParam = null;
		// get RunInstanceWires first
		for (WireEdge w : wire.getWires()) {
			if (w instanceof RunInstanceWire) {
				if (w.getFrom().equals(name1edit) && w.getTo().equals(name2update) )
					name1editRun = w;
				if (w.getFrom().equals(name2edit) && w.getTo().equals(name1update) )
					name2editRun = w;
			}
		}
		// then ParameterWires
		for (WireEdge w : wire.getWires()) {
			if (w instanceof ParameterWire) {
				if (w.getFrom().equals(name1value) && w.getTo().equals(name1editRun) )
					name1editParam = w;
				if (w.getFrom().equals(name2value) && w.getTo().equals(name2editRun) )
					name2editParam = w;
			}
		}
		
		// make sure we've got all of these
		assertNotNull( "name1editRun not null", name1editRun );
		assertNotNull( "name2editRun not null", name2editRun );
		assertNotNull( "name1editParam not null", name1editParam );
		assertNotNull( "name2editParam not null", name2editParam );
		
	}

}
