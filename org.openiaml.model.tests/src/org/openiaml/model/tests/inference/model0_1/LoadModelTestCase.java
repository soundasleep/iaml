/**
 *
 */
package org.openiaml.model.tests.inference.model0_1;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests loading the model.
 *
 * @author jmwright
 *
 */
public class LoadModelTestCase extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		String modelFile = ROOT + "inference/model0_1/LoadModelTestCase.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);

		root = (InternetApplication) model;

		// write out this inferred model for reference
		saveInferredModel(root.eResource());
	}

	public void testContents() {
		// it should have pages
		assertEquals(root.getScopes().size(), 3);
		assertTrue(root.getScopes().get(0) instanceof Frame);
		assertTrue(root.getScopes().get(1) instanceof Frame);
		assertTrue(root.getScopes().get(2) instanceof Frame);
		
		// but no visible elements
		assertEquals(root.getChildren().size(), 0);
	}

}
