/**
 * 
 */
package org.openiaml.model.migrate;

/**
 * Something bad happened when doing migration.
 * 
 * @author jmwright
 *
 */
public class MigrationException extends Exception {

	private static final long serialVersionUID = 1L;

	public MigrationException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public MigrationException(String message, Throwable e) {
		super(message, e);
	}
}