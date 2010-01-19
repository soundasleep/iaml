/**
 * 
 */
package org.openiaml.verification.nusmv.rules;

import org.openiaml.verification.nusmv.VerificationNuSMVPlugin;
import org.osgi.framework.Bundle;

/**
 * Searches for instances of infinite operation loops.
 * 
 * TODO move this into a separate plugin?
 * 
 * @author jmwright
 *
 */
public class InfiniteOperationLoop extends EclipseVerificationRule {

	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.rules.EclipseVerificationRule#getBundle()
	 */
	@Override
	public Bundle getBundle() {
		return VerificationNuSMVPlugin.getInstance().getBundle();
	}

	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.rules.EclipseVerificationRule#getOutputFile()
	 */
	@Override
	public String getOutputFile() {
		return "output/check.smv";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.rules.EclipseVerificationRule#getVerificationRuleFile()
	 */
	@Override
	public String getVerificationRuleFile() {
		return "rules/loop.smv";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.rules.EclipseVerificationRule#getWorkflowFile()
	 */
	@Override
	public String getWorkflowFile() {
		return "src/workflow/loop.oaw";
	}

}
