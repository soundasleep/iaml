/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;


/**
 * @author jmwright
 *
 */
public class DistinctContainments extends IterateOverAll {
	private Set<Object> values = new HashSet<Object>();

	/**
	 * @param name
	 */
	public DistinctContainments(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the distinct types
		return values.size();
	}

	@Override
	public int get(EObject obj) {
		List<EReference> refs = obj.eClass().getEAllContainments();
		for (EReference ref : refs) {
			if (ignoreReference(ref))
				continue;	// ignore
			
			Object r = obj.eGet(ref);
			if (r == null)
				continue;
			
			if (!(r instanceof List<?>)) {
				// turn single value into singleton list
				r = Collections.singletonList(r);
			}
			List<?> rl = (List<?>) r;
			for (Object o : rl) {
				if (!values.contains(o)) {
					values.add(o);
				}
			}
		}
		return 0;	// ignored
	}
}