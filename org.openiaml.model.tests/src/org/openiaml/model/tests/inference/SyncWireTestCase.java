/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;

/**
 * Tests inference of sync wires.
 * 
 * @author jmwright
 *
 */
public class SyncWireTestCase extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		String modelFile = "models/test-sync.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);
		
		root = (InternetApplication) model;
		
		// we now try to do inference
		ICreateElements handler = new EcoreInferenceHandler(resource);
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler);
		ce.create(root);
		
		// write out this inferred model for reference
		saveInferredModel();
	}
	
	protected void tearDown() throws Exception {
		// empty
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
		List<Object> nodes = query(update, "iaml:nodes");
		assertEquals(nodes.size(), 2);
		assertTrue(nodes.get(0) instanceof StartNode);
		assertTrue(nodes.get(1) instanceof StopNode);
	}
	
	public void testAllUpdates() throws JaxenException {
		// get all 'update' operations
		List<Object> updates = query(root, "//iaml:operations[iaml:name='update']");
		
		// there are 4 input texts => there should be 4 update operations
		assertEquals(updates.size(), 4);
		
		int i = 0;
		for (Object obj : updates) {
			i++;
			String prelude = "'update' operation #" + i;
			CompositeOperation update = (CompositeOperation) obj;	// should be a composite operation
			assertEquals(prelude, update.getName(), "update");
			
			// has a start node
			List<Object> nodes = query(update, "iaml:nodes");
			assertEquals(prelude, nodes.size(), 2);
			assertTrue(prelude, nodes.get(0) instanceof StartNode);
			assertTrue(prelude, nodes.get(1) instanceof StopNode);
			
			// -- traverse from start node --
			StartNode start = (StartNode) nodes.get(0);
			StopNode stop = (StopNode) nodes.get(1);
			
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
			StopNode finalNode = (StopNode) setProp.getOutExecutions().get(0).getTo();
			
			assertEquals(prelude, stop, finalNode);
		}
	}
	
}
