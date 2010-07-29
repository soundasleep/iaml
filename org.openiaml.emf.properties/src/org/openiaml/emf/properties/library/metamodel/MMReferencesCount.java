/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The total number of direct references in the metamodel.
 * (TNoR)
 * 
 * @author jmwright
 *
 */
public class MMReferencesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMReferencesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMReferencesCount(IEMFElementSelector selector) {
		this(MMReferencesCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}
	
	@Override
	public void evaluateClass(EClass cls) {
		result += cls.getEReferences().size();
	}

	@Override
	public Object getResult() {
		return result;
	}

}
