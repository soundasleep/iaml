/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;

import ca.ecliptical.emf.xpath.EMFXPath;

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
		CreateMissingElements ce = new CreateMissingElements(handler);
		ce.create(root);
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
	
}
