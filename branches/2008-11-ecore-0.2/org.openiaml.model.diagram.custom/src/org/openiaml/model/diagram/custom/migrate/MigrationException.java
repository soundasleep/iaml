/**
 * 
 */
package org.openiaml.model.diagram.custom.migrate;

/**
 * Something bad happened when doing migration.
 * 
 * @author jmwright
 *
 */
public class MigrationException extends Exception {

	private static final long serialVersionUID = 1L;

	public MigrationException(Exception e) {
		super(e);
	}
	
}