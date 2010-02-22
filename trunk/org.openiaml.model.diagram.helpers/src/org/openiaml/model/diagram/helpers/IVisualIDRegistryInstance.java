/**
 * 
 */
package org.openiaml.model.diagram.helpers;

import org.eclipse.emf.ecore.EObject;

/**
 * Wraps the generated GMF VisualIDRegistry with an interface for common
 * generated methods (issue 151).
 * 
 * @author jmwright
 *
 */
public interface IVisualIDRegistryInstance {

	/**
	 * Get the visual ID (integer) of the given domain element in the given
	 * editor, or -1 if the given element cannot be rendered in this editor.
	 * 
	 * @param domainElement
	 * @return
	 */
	public int getLinkWithClassVisualID(EObject domainElement);

}
