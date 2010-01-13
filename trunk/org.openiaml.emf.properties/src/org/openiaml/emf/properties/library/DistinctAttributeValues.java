/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;


/**
 * @author jmwright
 *
 */
public class DistinctAttributeValues extends IterateOverAll {
	private Set<Object> values = new HashSet<Object>();

	/**
	 * @param name
	 */
	public DistinctAttributeValues(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public DistinctAttributeValues(IEMFElementSelector selector) {
		this(DistinctAttributeValues.class.getSimpleName(), selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the distinct types
		return values.size();
	}

	@Override
	public int get(EObject obj) {
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		for (EAttribute attr : attributes) {
			if (ignoreAttribute(attr))
				continue;	// ignore
			
			Object r = obj.eGet(attr);
			if (attr.isMany()) {
				List<?> rList = (List<?>) r;
				for (Object r2 : rList) {
					if (r2 != null && !values.contains(obj)) {
						values.add(r2);
					}
				}
			} else {
				if (r != null && !values.contains(obj)) {
					values.add(r);
				}
			}
		}
		return 0;	// ignored
	}
}