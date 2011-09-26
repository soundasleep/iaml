/**
 * 
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.InternetApplication;

/**
 * A test case that implements this can not only be
 * run as a normal inference test case, but in the
 * Eclipse tests can be used to check models at
 * two stages: 
 * 
 * 1. The initial model is correct
 * 2. The final inferred model is correct
 * 
 * @author jmwright
 *
 */
public abstract class EclipseInheritanceInterface extends InferenceTestCase {

	/**
	 * Get the current class.
	 */
	public abstract Class<? extends EclipseInheritanceInterface> getTestClass();
	
	/**
	 * Make sure the model is loaded properly.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(getTestClass());
		checkNotInferredKnowledge(root);
	}

	/**
	 * Complete model inference.
	 *
	 * @throws Exception
	 */
	public void testDefaultInference() throws Exception {
		root = loadAndInfer(getTestClass());
		checkInferredKnowledge(root);
	}
	
	/**
	 * Test the inference of the initial model.
	 * 
	 * @param root
	 * @throws Exception 
	 */
	public abstract void checkNotInferredKnowledge(InternetApplication root) throws Exception;
	
	
	/**
	 * Test the inference of the final inferred model.
	 * 
	 * @param root
	 * @throws Exception 
	 */
	public abstract void checkInferredKnowledge(InternetApplication root) throws Exception;
	
}
