/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Containment: The proportion of containment references to all references.
 * (Cont)
 * 
 * @author jmwright
 *
 */
public class MMContainment extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMContainment(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMContainment(IEMFElementSelector selector) {
		this(MMContainment.class.getSimpleName(), selector);
	}
	
	private int refCount;
	private int containmentCount;

	@Override
	public void initialise() {
		refCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		for (EReference ref : cls.getEReferences()) {
			refCount++;
			
			if (ref.isContainment()) {
				containmentCount++;
			}
		}
	}

	@Override
	public Object getResult() {
		if (refCount == 0)
			return 0;
		
		return ((double) containmentCount) / ((double) refCount);
	}

}
