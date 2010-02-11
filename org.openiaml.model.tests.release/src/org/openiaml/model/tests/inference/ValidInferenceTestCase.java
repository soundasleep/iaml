/**
 *
 */
package org.openiaml.model.tests.inference;


/**
 * A valid inference test case also checks that the inferred result
 * is valid according to OAW checks.
 *
 * @author jmwright
 */
public abstract class ValidInferenceTestCase extends InferenceTestCase {
	
	/**
	 * Get the class on which to load and infer against.
	 * 
	 * @see #loadAndInfer(Class)
	 * @return
	 */
	public abstract Class<? extends ValidInferenceTestCase> getInferenceClass();
	
	/**
	 * The inferred model should be valid.
	 * 
	 * @see #getInferenceClass()
	 * @throws Exception
	 */
	public void testInferredModelIsValid() throws Exception {
		checkModelIsValid(loadAndInfer(getInferenceClass()));
	}
	
}
