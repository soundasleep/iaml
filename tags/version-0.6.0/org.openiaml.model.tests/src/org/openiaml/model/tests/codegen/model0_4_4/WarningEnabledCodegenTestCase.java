/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Overrides {@link CodegenTestCase} to also check for 'Warning'
 * as a sign of a problem in {@link #assertNoProblem()}
 * and {@link #assertProblem()}.
 * 
 * @author jmwright
 *
 */
public abstract class WarningEnabledCodegenTestCase extends CodegenTestCase {
	
	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertProblem() {
		resetDebug();
		assertMatch("(Error|error|Exception|exception|Warning|warning)");
	}

	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertNoProblem() {
		resetDebug();
		assertNoMatch("(Error|error|Exception|exception|Warning|warning)");
	}
	
}
