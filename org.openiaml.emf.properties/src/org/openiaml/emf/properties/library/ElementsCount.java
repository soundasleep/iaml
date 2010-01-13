/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;


/**
 * @author jmwright
 *
 */
public class ElementsCount extends DefaultPropertyInvestigator {

	public ElementsCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public ElementsCount(IEMFElementSelector selector) {
		this(ElementsCount.class.getSimpleName(), selector);
	}

	public Object evaluate(EObject root) {
		return 1 + removeIgnoredClasses(toCollection(root.eAllContents())).size();
	}
}