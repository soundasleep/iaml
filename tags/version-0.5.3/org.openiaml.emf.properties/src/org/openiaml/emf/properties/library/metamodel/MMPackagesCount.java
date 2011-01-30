/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The total number of packages in the metamodel.
 * (TNP)
 * 
 * @author jmwright
 *
 */
public class MMPackagesCount extends MetamodelInvestigator {
	/**
	 * @param name
	 */
	public MMPackagesCount(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public MMPackagesCount(IEMFElementSelector selector) {
		this(MMPackagesCount.class.getSimpleName(), selector);
	}
	
	private int result;

	@Override
	public void initialise() {
		result = 0;
	}

	@Override
	public void evaluatePackage(EPackage pkg) {
		super.evaluatePackage(pkg);
		result++;
	}

	@Override
	public Object getResult() {
		return result;
	}

	@Override
	public void evaluateClass(EClass cls) {
		// empty
	}

}
