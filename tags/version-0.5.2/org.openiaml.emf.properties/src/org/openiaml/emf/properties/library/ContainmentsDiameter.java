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
public class ContainmentsDiameter extends ReferencesDiameter {

	public ContainmentsDiameter(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public ContainmentsDiameter(IEMFElementSelector selector) {
		this(ContainmentsDiameter.class.getSimpleName(), selector);
	}

	@Override
	public boolean ignoreReference(EReference ref) {
		return !ref.isContainment() || super.ignoreReference(ref);
	}

}