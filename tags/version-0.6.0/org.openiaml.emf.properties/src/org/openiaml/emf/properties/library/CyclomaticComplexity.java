/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;


/**
 * Assumes that the entire graph is connected.
 * 
 * @author jmwright
 *
 */
public class CyclomaticComplexity extends DefaultPropertyInvestigator {

	public CyclomaticComplexity(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public CyclomaticComplexity(IEMFElementSelector selector) {
		this(CyclomaticComplexity.class.getSimpleName(), selector);
	}

	public Object evaluate(EObject root) {
		// nodes
		ElementsCount nodes = new ElementsCount(this.getName(), this.getSelector());
		
		// edges
		ReferencesSum edges = new ReferencesSum(this.getName(), this.getSelector());

		return ((Integer) nodes.evaluate(root)) - ((Integer) edges.evaluate(root)) + (2 * 1);
	}
}