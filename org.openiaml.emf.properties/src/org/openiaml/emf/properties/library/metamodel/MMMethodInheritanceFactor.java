/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * Method Inheritance Factor: "the ratio of the sum of inherited operations 
 * in all classes of the system under consideration to the total number of 
 * available operations (locally defined plus inherited) for all classes."
 * 
 * @author jmwright
 *
 */
public class MMMethodInheritanceFactor extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMMethodInheritanceFactor(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMMethodInheritanceFactor(IEMFElementSelector selector) {
		this(MMMethodInheritanceFactor.class.getSimpleName(), selector);
	}
	
	private int inheritedOperationsCount;
	private int totalOperationsCount;

	@Override
	public void initialise() {
		inheritedOperationsCount = 0;
		totalOperationsCount = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		inheritedOperationsCount += (cls.getEAllOperations().size() - cls.getEOperations().size());
		totalOperationsCount += cls.getEAllOperations().size();
	}

	@Override
	public Object getResult() {
		if (totalOperationsCount == 0)
			return 0;
		
		return ((double) inheritedOperationsCount) / ((double) totalOperationsCount);
	}

}
