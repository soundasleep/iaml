/**
 * 
 */
package org.openiaml.model.diagram.custom.migrate;

import org.w3c.dom.Element;

/**
 * Represents an expected migration exception, that is not fatal.
 * e.g. A new model version can't support a given model element, or
 * an element has been deleted.
 * 
 * @author Jevon
 *
 */
public class ExpectedMigrationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param migrator
	 * @param e the source element
	 * @param reason
	 */
	public ExpectedMigrationException(IamlModelMigrator migrator, Element e,
			String reason) {
		super(migrator.getName() + ": " + reason + " (" + getDebugPath(e) + ")");
	}

	/**
	 * Get a textual representation of an element.
	 * 
	 * @param e
	 * @return
	 */
	protected static String getDebugPath(Element e) {
		return "<" + e.getNodeName() + ">";
	}

}
