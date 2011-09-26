/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The number of enumerations.
 * (NoE)
 * 
 * @author jmwright
 *
 */
public class MMEnumCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMEnumCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMEnumCount(IEMFElementSelector selector) {
		this(MMEnumCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		// empty
	}

	@Override
	public void evaluateClassifier(EClassifier cls) {
		if (cls instanceof EEnum) {
			result++;
		}

		super.evaluateClassifier(cls);
	}

	@Override
	public Object getResult() {
		return result;
	}

}
