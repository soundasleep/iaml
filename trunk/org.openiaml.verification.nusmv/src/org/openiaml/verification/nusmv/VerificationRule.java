/**
 * 
 */
package org.openiaml.verification.nusmv;

import java.io.InputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

/**
 * Represents a method (templates? QVT? OAW?) of converting an EObject model
 * into a NuSMV model.
 * 
 * Also contains the verification rule itself (in text).
 * 
 * @author jmwright
 *
 */
public interface VerificationRule {
	
	/**
	 * Export the given model to SMV.
	 * 
	 * @param model the model to export
	 * @param monitor monitor to notify of progress
	 * @return the translated model in SMV format, without the SMV rule
	 * @see #getVerificationRule()
	 */
	public InputStream exportToSMV(EObject model, IProgressMonitor monitor) throws VerificationException;

	/**
	 * Get the verification rule, e.g. "<code>LTLSPEC\nG cond</code>"
	 * 
	 * @return The verification rule stream
	 */
	public InputStream getVerificationRule() throws VerificationException;
	
}
