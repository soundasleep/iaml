/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Data quantity: The proportion of attributes to overall structural features.
 * (Dat)
 * 
 * @author jmwright
 *
 */
public class MMDataQuantity extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMDataQuantity(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMDataQuantity(IEMFElementSelector selector) {
		this(MMDataQuantity.class.getSimpleName(), selector);
	}
	
	private int refCount;
	private int attributeCount;

	@Override
	public void initialise() {
		refCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		refCount += cls.getEReferences().size();
		attributeCount += cls.getEAttributes().size();
	}

	@Override
	public Object getResult() {
		if (refCount + attributeCount == 0)
			return 0;
		
		return ((double) attributeCount) / ((double) (refCount + attributeCount));
	}

}
