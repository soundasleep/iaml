/**
 *
 */
package org.openiaml.model.tests.inference;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Page;

/**
 * Tests loading the model.
 *
 * @author jmwright
 *
 */
public class LoadModelTestCase extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();

		String modelFile = ROOT + "inference/LoadModelTestCase.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);

		root = (InternetApplication) model;

		// write out this inferred model for reference
		saveInferredModel(root.eResource());
	}

	public void testContents() {
		// it should have pages
		assertEquals(root.getChildren().size(), 3);
		assertTrue(root.getChildren().get(0) instanceof Page);
		assertTrue(root.getChildren().get(1) instanceof Page);
		assertTrue(root.getChildren().get(2) instanceof Page);
	}

}
