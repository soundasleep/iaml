/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.drools.DroolsHelperFunctions;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * @author jmwright
 *
 */
public abstract class DroolsHelperFunctionsTestCase extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadDirectly(getTestClass());
	}
	
	/**
	 * Get the current class.
	 */
	protected abstract Class<? extends DroolsHelperFunctionsTestCase> getTestClass();

	/**
	 * Get the helper function wrapper to test with.
	 * 
	 * @return
	 */
	public DroolsHelperFunctions getHelper() {
		return new DroolsHelperFunctions();
	}
	
}
