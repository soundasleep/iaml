/**
 * 
 */
package org.openiaml.emf.properties.library;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class CountTypes extends IterateOverAll {
	
	private Class<? extends EObject> type;

	/**
	 * @param name
	 * @param type type to search for
	 */
	public CountTypes(String name, IEMFElementSelector selector, Class<? extends EObject> type) {
		super(name, selector);
		this.type = type;
	}
	
	/**
	 * @param type type to search for
	 */
	public CountTypes(IEMFElementSelector selector, Class<? extends EObject> type) {
		this(CountTypes.class.getSimpleName(), selector, type);
	}

	@Override
	public int get(EObject obj) {
		return type.isInstance(obj) ? 1 : 0; 
	}
}