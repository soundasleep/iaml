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
public class AttributesCount extends IterateOverAll {
	/**
	 * @param name
	 */
	public AttributesCount(String name, IEMFElementSelector selector) {
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
				result++;
			}
		}
		return result;
	}
}
