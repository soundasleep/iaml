/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IterateOverAll;


/**
 * @author jmwright
 *
 */
public final class DistinctAttributeValues extends IterateOverAll {
	private Set<Object> values = new HashSet<Object>();

	/**
	 * @param name
	 */
	private DistinctAttributeValues(String name) {
		super(name);
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
			Object r = obj.eGet(attr);
			if (r != null && !values.contains(obj)) {
				values.add(r);
			}
		}
		return 0;	// ignored
	}
}