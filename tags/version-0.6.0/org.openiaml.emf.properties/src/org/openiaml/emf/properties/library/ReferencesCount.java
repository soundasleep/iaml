/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class ReferencesCount extends IterateOverAll {
	
	/**
	 * @param name
	 */
	public ReferencesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public ReferencesCount(IEMFElementSelector selector) {
		this(ReferencesCount.class.getSimpleName(), selector);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EReference> refs = obj.eClass().getEAllReferences();
		for (EReference ref : refs) {
			if (ignoreReference(ref))
				continue;	// ignore
			
			if (obj.eGet(ref) != null) {
				result++;
			}
		}
		return result;
	}
}