/**
 * 
 */
package org.openiaml.docs.generation;

/**
 * A wrapper around documentation generation exceptions.
 * 
 * @author jmwright
 *
 */
public class DocumentationGenerationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DocumentationGenerationException(String message) {
		super(message);
	}
	
	public DocumentationGenerationException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public DocumentationGenerationException(String message, Throwable e) {
		super(message, e);
	}

}
