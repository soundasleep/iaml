/**
 * 
 */
package org.openiaml.verification.nusmv.rules;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.openiaml.verification.nusmv.VerificationException;

/**
 * Abstract class hiding the verification rule implementation for
 * an Eclipse-based rule implementation.
 * 
 * @author jmwright
 *
 */
public abstract class EclipseVerificationRule extends EMFVerificationRule {

	/**
	 * Get the verification rule file (e.g. "rules/loop.smv") in the current bundle.
	 * 
	 * @return the current verification rule file
	 */
	public abstract String getVerificationRuleFile();
	
	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.VerificationRule#getVerificationRule()
	 */
	public InputStream getVerificationRule() throws VerificationException {
		try {
			URL resolved = getResolvedFile(getVerificationRuleFile());

			// now put into an InputStream
			return resolved.openStream();
			
		} catch (IOException e) {
			throw new VerificationException(e);
		}

	}

}
