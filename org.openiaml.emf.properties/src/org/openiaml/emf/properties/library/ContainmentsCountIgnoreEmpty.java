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
public final class ContainmentsCountIgnoreEmpty extends IterateOverAll {
	/**
	 * @param name
	 */
	private ContainmentsCountIgnoreEmpty(String name) {
		super(name);
	}

	@Override
	public int get(EObject obj) {
		int result = 0;
		List<EReference> refs = obj.eClass().getEAllContainments();
		for (EReference ref : refs) {
			if (obj.eGet(ref) != null) {
				if (!(ref.isMany() && ((List<?>) obj.eGet(ref)).size() == 0)) {
					result++;
				}
			}
		}
		return result;
	}
}