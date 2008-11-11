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
	public InferenceException(Exception e) {
		super(e);
	}
	
	public InferenceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public InferenceException(String message, Exception e) {
		super(message, e);
	}

}
