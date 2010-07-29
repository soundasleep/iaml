/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The total number of abstract classes in the metamodel.
 * (NoAC)
 * 
 * @author jmwright
 *
 */
public class MMAbstractClassesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMAbstractClassesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMAbstractClassesCount(IEMFElementSelector selector) {
		this(MMAbstractClassesCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		if (cls.isAbstract()) {
			result++;
		}
	}

	@Override
	public Object getResult() {
		return result;
	}

}
