/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * @author jmwright
 *
 */
public class ReferencesWithoutContainmentsRadius extends ReferencesRadius {
	
	public ReferencesWithoutContainmentsRadius(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public boolean ignoreReference(EReference ref) {
		return ref.isContainment() || super.ignoreReference(ref);
	}

}