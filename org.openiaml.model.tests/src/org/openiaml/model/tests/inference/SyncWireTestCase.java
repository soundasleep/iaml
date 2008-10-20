/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Page;

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
	
	public void testContents() {
		// it should have pages
		assertEquals(root.getChildren().size(), 3);
		assertTrue(root.getChildren().get(0) instanceof Page);
		assertTrue(root.getChildren().get(1) instanceof Page);
		assertTrue(root.getChildren().get(2) instanceof Page);
	}
	
}
