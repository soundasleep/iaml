/**
 * 
 */
package org.openiaml.verification.nusmv.rules;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.openiaml.verification.nusmv.VerificationException;
import org.openiaml.verification.nusmv.VerificationNuSMVPlugin;
import org.osgi.framework.Bundle;

/**
 * @author jmwright
 *
 */
public class StringVerificationRule extends EMFVerificationRule {

	private String ltlspec;

	public StringVerificationRule(String ltlspec) {
		this.ltlspec = ltlspec;
	}

	@Override
	public Bundle getBundle() {
		return VerificationNuSMVPlugin.getInstance().getBundle();
	}

	@Override
	public String getOutputFile() {
		return "output/check.smv";
	}

	@Override
	public String getWorkflowFile() {
		return "src/workflow/loop.oaw";
	}

	public InputStream getVerificationRule() throws VerificationException {
		return new ByteArrayInputStream(ltlspec.getBytes());
	}
	
}