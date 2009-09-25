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
public final class AttributesCountIgnoreDefault extends IterateOverAll {
	/**
	 * @param name
	 */
	private AttributesCountIgnoreDefault(String name) {
		super(name);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		for (EAttribute attr : attributes) {
			if (obj.eGet(attr) != null) {
				if (!obj.eGet(attr).equals( attr.getDefaultValue() )) { 
					result++;
				}
			}
		}
		return result;
	}
}
