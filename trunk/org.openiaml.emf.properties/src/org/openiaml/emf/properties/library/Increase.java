/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;

/**
 * Calculates the increase between two given properties, in %.
 * Assumes both properties can be cast to <b>double</b>.
 * 
 * That is, given source=2, target=4, it will return 1=100% (=4-2/2).
 * 
 * If source=0, returns "inf".
 * 
 * @author jmwright
 *
 */
public class Increase extends DefaultPropertyInvestigator {

	private IPropertyInvestigator source;
	private IPropertyInvestigator target;
	
	public Increase(String name, IEMFElementSelector selector, IPropertyInvestigator source, IPropertyInvestigator target) {
		super(name, selector);
		this.source = source;
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.emf.properties.IPropertyInvestigator#evaluate(org.eclipse.emf.ecore.EObject)
	 */
	public Object evaluate(EObject root) {
		double s = ((Number) source.evaluate(root)).doubleValue();
		double t = ((Number) target.evaluate(root)).doubleValue();
		
		 if (s == 0)
			 return "inf";
		 
		 return (t - s) / s;
	}
	
}
