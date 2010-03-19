/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * Distinct types and supertypes.
 * 
 * @author jmwright
 *
 */
public class DistinctSupertypes extends IterateOverAll {
	private Set<EClass> types = new HashSet<EClass>();

	/**
	 * @param name
	 */
	public DistinctSupertypes(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public DistinctSupertypes(IEMFElementSelector selector) {
		this(DistinctSupertypes.class.getSimpleName(), selector);
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
		if (!types.contains(obj.eClass())) {
			types.add(obj.eClass());
		}
		List<EClass> supertypes = obj.eClass().getEAllSuperTypes();
		for (EClass type : supertypes) {
			if (ignoreClass(type))
				continue;	// ignore
			
			if (!types.contains(type)) {
				types.add(type);
			}
		}
		return 0;	// ignored
	}
}