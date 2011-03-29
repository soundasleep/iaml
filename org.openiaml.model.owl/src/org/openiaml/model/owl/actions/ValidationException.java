/**
 * 
 */
package org.openiaml.model.owl.actions;

import com.hp.hpl.jena.reasoner.ValidityReport;


/**
 * An exception to say something wasn't valid in the validation 
 * process, and has information about the validation errors.
 * 
 * @author jmwright
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ValidityReport report;
	
	/**
	 * Get the validity report which caused validation
	 * to fail.
	 * 
	 * @return
	 */
	public ValidityReport getReport() {
		return report;
	}

	public ValidationException(String string, ValidityReport valid) {
		super(string);
		this.report = valid;
	}
	
	public ValidationException(String message, Throwable e) {
		super(message, e);
		this.report = null;
	}
	
	public ValidationException(Throwable e) {
		super(e.getMessage(), e);
		this.report = null;
	}
	
}
