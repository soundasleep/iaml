/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class MaxDegreeReferencesWithoutContainments extends IterateOverAll {
	public int max = -1;

	/**
	 * @param name
	 */
	public MaxDegreeReferencesWithoutContainments(String name, IEMFElementSelector selector) {
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
		List<EReference> refs = obj.eClass().getEAllReferences();
		int thisValue = 0;
		for (EReference ref : refs) {
			if (ignoreReference(ref))
				continue;	// ignore

			Object r = obj.eGet(ref);
			if (r == null)
				continue;
			
			if (!(r instanceof List<?>)) {
				r = Collections.singletonList(r);
			}
			
			thisValue += ((List<?>) r).size();
		}
		if (thisValue > max)
			max = thisValue;	// set max
		return 0;	// ignored
	}
}