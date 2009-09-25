/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public final class SupertypesCount extends IterateOverAll {
	/**
	 * @param name
	 */
	private SupertypesCount(String name) {
		super(name);
	}

	@Override
	public int get(EObject obj) {
		return obj.eClass().getEAllSuperTypes().size();
	}
}