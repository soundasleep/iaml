/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Navigability: The proportion of references with a defined EOpposite.
 * (Nav)
 * 
 * @author jmwright
 *
 */
public class MMNavigability extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMNavigability(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMNavigability(IEMFElementSelector selector) {
		this(MMNavigability.class.getSimpleName(), selector);
	}
	
	private int refCount;
	private int oppositeCount;

	@Override
	public void initialise() {
		refCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		for (EReference ref : cls.getEReferences()) {
			refCount++;
			
			if (ref.getEOpposite() != null) {
				oppositeCount++;
			}
		}
	}

	@Override
	public Object getResult() {
		if (refCount == 0)
			return 0;
		
		return ((double) oppositeCount) / ((double) refCount);
	}

}
