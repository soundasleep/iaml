/**
 * 
 */
package org.openiaml.model.inference;


/**
 * @author jmwright
 *
 */
public class InferenceException extends Exception {

	/**
	 * @param e
	 */
	public InferenceException(Exception e) {
		super(e);
	}
	
	public InferenceException(String message) {
		super(message);
	}

}
