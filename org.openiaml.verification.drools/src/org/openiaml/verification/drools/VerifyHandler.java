/**
 * 
 */
package org.openiaml.verification.drools;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.verification.model.validation.Violation;

/**
 * @author jmwright
 *
 */
public class VerifyHandler {
	
	public static class VerificationException extends RuntimeException {

		public VerificationException(Exception exception) {
			super(exception.getMessage(), exception);
		}
		
		public VerificationException() {
			super();
		}

		private static final long serialVersionUID = 1L;
		
	}

	private List<Violation> violations = new ArrayList<Violation>();

	/**
	 * Print out a debug message.
	 * 
	 * @param message
	 */
	public void debug(String message) {
		System.out.println(message);
	}
	
	/**
	 * Verifiation failed.
	 * 
	 * @param violation
	 */
	public void failed(Violation violation) {
		this.violations.add(violation);
	}

	public List<Violation> getViolations() {
		return violations;
	}
	
	public boolean isFailed() {
		return !violations.isEmpty();
	}
	
}
