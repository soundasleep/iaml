/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public final class MaxInheritanceHeight extends IterateOverAll {
	private int max = -1;

	/**
	 * @param name
	 */
	private MaxInheritanceHeight(String name) {
		super(name);
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
		int thisValue = obj.eClass().getEAllSuperTypes().size();
		if (thisValue > max)
			max = thisValue;	// set max
		return 0;	// ignored
	}
}
