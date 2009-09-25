/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;


/**
 * @author jmwright
 *
 */
public final class ElementsCount extends DefaultPropertyInvestigator {
	/**
	 * @param name
	 */
	private ElementsCount(String name) {
		super(name);
	}

	public Object evaluate(EObject root) {
		return 1 + getSize(root.eAllContents());
	}
}