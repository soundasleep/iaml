/**
 * 
 */
package org.openiaml.docs.generation.semantics;

/**
 * @author jmwright
 *
 */
public class SemanticHandlerException extends Exception {

	private static final long serialVersionUID = 1L;

	public SemanticHandlerException(String message) {
		super(message);
	}
	
	public SemanticHandlerException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public SemanticHandlerException(String message, Throwable e) {
		super(message, e);
	}
	
}
