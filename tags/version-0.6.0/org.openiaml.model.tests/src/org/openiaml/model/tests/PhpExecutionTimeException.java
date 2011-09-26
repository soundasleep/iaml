/**
 * 
 */
package org.openiaml.model.tests;


/**
 * This exception is thrown if the testing framework
 * identifies that the script execution failed due to
 * PHP running out of max_execution_time.
 * 
 * @author jmwright
 */
public class PhpExecutionTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PhpExecutionTimeException(String message) {
		super(message);
	}
	
	public PhpExecutionTimeException(Throwable t) {
		super(t.getMessage(), t);
	}
	
	public PhpExecutionTimeException(String message, Throwable t) {
		super(message, t);
	}
	
}
