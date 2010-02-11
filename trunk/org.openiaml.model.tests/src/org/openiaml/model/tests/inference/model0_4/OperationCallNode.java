/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Issue 136: Invalid PrimitiveOperation name for CompositeOperations
 *
 * @author jmwright
 *
 */
public class OperationCallNode extends InferenceTestCase {

	/**
	 * The inferred model should be valid.
	 * 
	 * @throws Exception
	 */
	public void testInferredModelIsValid() throws Exception {
		checkModelIsValid(loadAndInfer(OperationCallNode.class));
	}

}
