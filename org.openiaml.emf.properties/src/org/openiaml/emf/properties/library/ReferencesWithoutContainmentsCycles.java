/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Cycles in references (excluding containments).
 * 
 * @author jmwright
 *
 */
public class ReferencesWithoutContainmentsCycles extends ReferencesCycles {

	public ReferencesWithoutContainmentsCycles(String name,
			IEMFElementSelector selector) {
		super(name, selector);
	}

	public ReferencesWithoutContainmentsCycles(IEMFElementSelector selector) {
		this(ReferencesWithoutContainmentsCycles.class.getSimpleName(), selector);
	}
	@Override
	public boolean ignoreReference(EReference ref) {
		return ref.isContainment() || super.ignoreReference(ref);
	}

}