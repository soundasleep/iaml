/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;


/**
 * @author jmwright
 *
 */
public class DistinctTypes extends IterateOverAll {
	private Set<EClass> types = new HashSet<EClass>();

	/**
	 * @param name
	 */
	public DistinctTypes(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the distinct types
		return types.size();
	}

	@Override
	public int get(EObject obj) {
		EClass type = obj.eClass();
		if (!types.contains(type)) {
			types.add(type);
		}
		return 0;	// ignored
	}
}