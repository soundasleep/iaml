/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public final class AttributesCount extends IterateOverAll {
	/**
	 * @param name
	 */
	private AttributesCount(String name) {
		super(name);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		for (EAttribute attr : attributes) {
			if (obj.eGet(attr) != null) {
				result++;
			}
		}
		return result;
	}
}
