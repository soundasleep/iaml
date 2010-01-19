/**
 * 
 */
package org.openiaml.verification.nusmv;


/**
 * Represents a violation of a verification (in NuSMV).
 * 
 * @author jmwright
 *
 */
public class NuSMVViolation {

	private String message;

	public NuSMVViolation(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Violation: " + message;
	}
	
	
	
}
