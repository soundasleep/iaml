/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class MinDegreeAttributes extends IterateOverAll {
	private int min = -1;

	/**
	 * @param name
	 */
	public MinDegreeAttributes(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the maximum
		return min;
	}

	@Override
	public int get(EObject obj) {
		List<EAttribute> attributes = obj.eClass().getEAllAttributes();
		int thisValue = 0;
		for (EAttribute attr : attributes) {
			if (ignoreAttribute(attr))
				continue;	// ignore

			Object r = obj.eGet(attr);
			if (r != null)
				thisValue++;
		}
		if (thisValue < min || min == -1)
			min = thisValue;	// set min
		return 0;	// ignored
	}
}