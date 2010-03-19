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
 * Like {@link DistinctAttributeValues}, but 
 * only considers the string values of attributes.
 * 
 * @author jmwright
 *
 */
public class DistinctAttributeValueStrings extends IterateOverAll {
	private Set<String> values = new HashSet<String>();

	/**
	 * @param name
	 */
	public DistinctAttributeValueStrings(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public DistinctAttributeValueStrings(IEMFElementSelector selector) {
		this(DistinctAttributeValueStrings.class.getSimpleName(), selector);
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
					if (r2 != null && !values.contains(obj.toString())) {
						values.add(r2.toString());
					}
				}
			} else {
				if (r != null && !values.contains(obj.toString())) {
					values.add(r.toString());
				}
			}
		}
		return 0;	// ignored
	}
}