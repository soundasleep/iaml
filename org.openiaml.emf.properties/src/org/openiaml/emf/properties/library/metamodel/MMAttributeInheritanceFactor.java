/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Attribute Inheritance Factor: "the ratio of the sum of inherited attributes 
 * in all classes of the system under consideration to the total number of 
 * available attributes (locally defined plus inherited) for all classes."
 * 
 * @author jmwright
 *
 */
public class MMAttributeInheritanceFactor extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMAttributeInheritanceFactor(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMAttributeInheritanceFactor(IEMFElementSelector selector) {
		this(MMAttributeInheritanceFactor.class.getSimpleName(), selector);
	}
	
	private int inheritedAttributesCount;
	private int totalAttributesCount;

	@Override
	public void initialise() {
		inheritedAttributesCount = 0;
		totalAttributesCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		inheritedAttributesCount += (cls.getEAllAttributes().size() - cls.getEAttributes().size());
		totalAttributesCount += cls.getEAllAttributes().size();
	}

	@Override
	public Object getResult() {
		if (totalAttributesCount == 0)
			return 0;
		
		return ((double) inheritedAttributesCount) / ((double) totalAttributesCount);
	}

}
