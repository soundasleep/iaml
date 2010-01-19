/**
 * 
 */
package org.openiaml.verification.nusmv;

/**
 * Represents a verification exception of some sort.
 * 
 * @author jmwright
 *
 */
public class VerificationException extends Exception {

	private static final long serialVersionUID = 1L;

	public VerificationException(String message, Throwable e) {
		super(message, e);
	}
	
	public VerificationException(String message) {
		super(message);
	}
	
	public VerificationException(Throwable e) {
		super(e.getMessage(), e);
	}
	
}
