/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The total number of direct attributes in the metamodel.
 * (TNoA/TNA)
 * 
 * @author jmwright
 *
 */
public class MMAttributesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMAttributesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMAttributesCount(IEMFElementSelector selector) {
		this(MMAttributesCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		result += cls.getEAttributes().size();
	}

	@Override
	public Object getResult() {
		return result;
	}

}
