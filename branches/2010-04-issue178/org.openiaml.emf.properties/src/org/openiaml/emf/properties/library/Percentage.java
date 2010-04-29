/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;

/**
 * Calculates the % between two given properties, in %.
 * Assumes both properties can be cast to <b>double</b>.
 * 
 * That is, given haystack=4, needle=2, it will return 0.5=50% (=2/4).
 * 
 * If target=0 and source!=0, returns "inf".
 * 
 * @author jmwright
 *
 */
public class Percentage extends DefaultPropertyInvestigator {

	private IPropertyInvestigator needle;
	private IPropertyInvestigator haystack;
	
	public Percentage(String name, IEMFElementSelector selector, IPropertyInvestigator needle, IPropertyInvestigator haystack) {
		super(name, selector);
		this.needle = needle;
		this.haystack = haystack;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.emf.properties.IPropertyInvestigator#evaluate(org.eclipse.emf.ecore.EObject)
	 */
	public Object evaluate(EObject root) {
		double n = ((Number) needle.evaluate(root)).doubleValue();
		double h = ((Number) haystack.evaluate(root)).doubleValue();
		
		if (h == 0) {
			if (n == 0)
				return 0;
			else
				return "inf";
		}
		 
		 return n / h;
	}
	
}
