/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The total number of classes in the metamodel.
 * (NoC/TNC)
 * 
 * @author jmwright
 *
 */
public class MMClassesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMClassesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMClassesCount(IEMFElementSelector selector) {
		this(MMClassesCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		result++;
	}

	@Override
	public Object getResult() {
		return result;
	}

}
