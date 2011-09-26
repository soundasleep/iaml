/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;

/**
 * Calculates the increase between two given properties, in absolute terms.
 * Assumes both properties can be cast to <b>long</b>.
 * 
 * That is, given source=2, target=4, it will return 2 (=4-2).
 * 
 * @author jmwright
 *
 */
public class IncreaseAbsolute extends DefaultPropertyInvestigator {

	private IPropertyInvestigator source;
	private IPropertyInvestigator target;
	
	public IncreaseAbsolute(String name, IEMFElementSelector selector, IPropertyInvestigator source, IPropertyInvestigator target) {
		super(name, selector);
		this.source = source;
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.emf.properties.IPropertyInvestigator#evaluate(org.eclipse.emf.ecore.EObject)
	 */
	public Object evaluate(EObject root) {
		long s = ((Number) source.evaluate(root)).longValue();
		long t = ((Number) target.evaluate(root)).longValue();
		
		return t - s;
	}
	
}
