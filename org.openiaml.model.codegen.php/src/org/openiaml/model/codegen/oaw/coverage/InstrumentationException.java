/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

/**
 * @author jmwright
 *
 */
public class InstrumentationException extends Exception {

	private static final long serialVersionUID = 1L;

	public InstrumentationException(String message) {
		super(message);
	}
	
	public InstrumentationException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public InstrumentationException(String message, Throwable e) {
		super(message, e);
	}
	
}
