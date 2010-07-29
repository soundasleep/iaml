/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The number of primitive data types.
 * (NoDT/NoD)
 * 
 * @author jmwright
 *
 */
public class MMPrimitiveDatatypesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMPrimitiveDatatypesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMPrimitiveDatatypesCount(IEMFElementSelector selector) {
		this(MMPrimitiveDatatypesCount.class.getSimpleName(), selector);
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
		if (cls instanceof EDataType) {
			result++;
		}

		super.evaluateClassifier(cls);
	}

	@Override
	public Object getResult() {
		return result;
	}

}
