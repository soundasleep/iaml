/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public final class MaxDegreeAttributes extends IterateOverAll {
	private int max = -1;

	/**
	 * @param name
	 */
	private MaxDegreeAttributes(String name) {
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
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		int thisValue = 0;
		for (EAttribute attr : attributes) {
			Object r = obj.eGet(attr);
			if (r != null)
				thisValue++;
		}
		if (thisValue > max)
			max = thisValue;	// set max
		return 0;	// ignored
	}
}