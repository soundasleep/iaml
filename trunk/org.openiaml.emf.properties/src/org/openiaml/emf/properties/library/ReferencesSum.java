/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public final class ReferencesSum extends IterateOverAll {
	/**
	 * @param name
	 */
	private ReferencesSum(String name) {
		super(name);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EReference> refs = obj.eClass().getEAllReferences();
		for (EReference ref : refs) {
			if (obj.eGet(ref) != null) {
				if (obj.eGet(ref) instanceof List<?>)
					result += ((List<?>) obj.eGet(ref)).size();
				else
					result ++;
			}
		}
		return result;
	}
}
