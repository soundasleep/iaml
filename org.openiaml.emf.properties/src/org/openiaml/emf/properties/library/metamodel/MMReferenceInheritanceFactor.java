/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Reference Inheritance Factor: "the ratio of the sum of inherited references 
 * in all classes of the system under consideration to the total number of 
 * available references (locally defined plus inherited) for all classes."
 * 
 * @author jmwright
 *
 */
public class MMReferenceInheritanceFactor extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMReferenceInheritanceFactor(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMReferenceInheritanceFactor(IEMFElementSelector selector) {
		this(MMReferenceInheritanceFactor.class.getSimpleName(), selector);
	}
	
	private int inheritedReferencesCount;
	private int totalReferencesCount;

	@Override
	public void initialise() {
		inheritedReferencesCount = 0;
		totalReferencesCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		inheritedReferencesCount += (cls.getEAllReferences().size() - cls.getEReferences().size());
		totalReferencesCount += cls.getEAllReferences().size();
	}

	@Override
	public Object getResult() {
		if (totalReferencesCount == 0)
			return 0;
		
		return ((double) inheritedReferencesCount) / ((double) totalReferencesCount);
	}

}
