/**
 * 
 */
package org.openiaml.model.drools;

import org.openiaml.model.inference.InferenceException;

/**
 * @author jmwright
 *
 */
public class UnexpectedGeneratorElementException extends InferenceException {
	
	public UnexpectedGeneratorElementException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
