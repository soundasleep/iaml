/**
 * 
 */
package org.openiaml.model.inference;


/**
 * @author jmwright
 *
 */
public class InferenceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 */
	public InferenceException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public InferenceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public InferenceException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
