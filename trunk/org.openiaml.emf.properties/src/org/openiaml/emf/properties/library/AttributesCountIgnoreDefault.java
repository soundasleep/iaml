/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class AttributesCountIgnoreDefault extends IterateOverAll {
	/**
	 * @param name
	 */
	public AttributesCountIgnoreDefault(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		for (EAttribute attr : attributes) {
			if (ignoreAttribute(attr))
				continue; // ignore
			
			if (obj.eGet(attr) != null) {
				if (!obj.eGet(attr).equals( attr.getDefaultValue() )) { 
					result++;
				}
			}
		}
		return result;
	}
}
