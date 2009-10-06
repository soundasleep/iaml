/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class MaxInheritanceHeight extends IterateOverAll {
	private int max = -1;

	/**
	 * @param name
	 */
	public MaxInheritanceHeight(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the maximum
		return max;
	}

	@Override
	public int get(EObject obj) {
		int thisValue = 0;
		for (EClass c : obj.eClass().getEAllSuperTypes()) {
			if (ignoreClass(c))
				continue;	// ignore
			
			thisValue++;
		}
		if (thisValue > max)
			max = thisValue;	// set max
		return 0;	// ignored
	}
}
