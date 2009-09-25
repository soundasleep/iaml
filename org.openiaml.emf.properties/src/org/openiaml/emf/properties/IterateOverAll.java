/**
 * 
 */
package org.openiaml.emf.properties;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

/**
 * Iterates over the root element and eAllContents and accumulates
 * a value. 
 * 
 * @author jmwright
 *
 */
public abstract class IterateOverAll extends DefaultPropertyInvestigator {

	public IterateOverAll(String name) {
		super(name);
	}

	public Object evaluate(EObject root) {
		int result = get(root);
		TreeIterator<EObject> it = root.eAllContents();
		while (it.hasNext()) {
			result += get(it.next());
		}
		return result;
	}
	
	/**
	 * Get the property value for just the current object.
	 */
	public abstract int get(EObject obj);
	
}