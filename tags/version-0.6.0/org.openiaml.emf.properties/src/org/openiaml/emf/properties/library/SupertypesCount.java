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
public class SupertypesCount extends IterateOverAll {
	
	/**
	 * @param name
	 */
	public SupertypesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	public SupertypesCount(IEMFElementSelector selector) {
		this(SupertypesCount.class.getSimpleName(), selector);
	}
	
	@Override
	public int get(EObject obj) {
		int thisValue = 0;
		for (EClass c : obj.eClass().getEAllSuperTypes()) {
			if (ignoreClass(c))
				continue;	// ignore
			
			thisValue++;
		}
		return thisValue;
	}
}