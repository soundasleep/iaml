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
public class ReferencesWithoutContainmentsCountIgnoreEmpty extends IterateOverAll {
	
	/**
	 * @param name
	 */
	public ReferencesWithoutContainmentsCountIgnoreEmpty(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public ReferencesWithoutContainmentsCountIgnoreEmpty(IEMFElementSelector selector) {
		this(ReferencesWithoutContainmentsCountIgnoreEmpty.class.getSimpleName(), selector);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EReference> refs = obj.eClass().getEAllReferences();
		for (EReference ref : refs) {
			if (ignoreReference(ref))
				continue;	// ignore
			if (ref.isContainment())
				continue;	// ignore

			if (obj.eGet(ref) != null) {
				if (!(ref.isMany() && ((List<?>) obj.eGet(ref)).size() == 0)) {
					result++;
				}
			}
		}
		return result;
	}
}