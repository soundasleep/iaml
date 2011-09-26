/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * An abstract investigator for investigating the metamodel.
 * 
 * @author jmwright
 *
 */
public abstract class MetamodelInvestigator extends DefaultPropertyInvestigator {
	/**
	 * @param name
	 */
	public MetamodelInvestigator(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	/**
	 * Evaluate the given EPackage. The object
	 * MUST be an EPackage, or an {@link IllegalArgumentException} will be
	 * thrown.
	 * 
	 * @throws IllegalArgumentException if the EObject is not an {@link EPackage}
	 */
	public Object evaluate(EObject root) {
		if (!(root instanceof EPackage)) {
			throw new IllegalArgumentException("Argument needs to be an EPackage.");
		}
		
		initialise();
		
		// investigate the EPackage of this object, and all contained
		// EPackages
		EPackage pkg = ((EPackage) root);
		evaluatePackage(pkg);
		
		return getResult();
	}
	
	/**
	 * Initialise the result count, etc.
	 */
	public abstract void initialise();

	/**
	 * Evaluate the given package and all sub-packages.
	 * 
	 * @param pkg
	 * @return
	 */
	public void evaluatePackage(EPackage pkg) {
		for (EPackage sub : pkg.getESubpackages()) {
			evaluatePackage(sub);
		}
		
		for (EClassifier cls : pkg.getEClassifiers()) {
			evaluateClassifier(cls);
		}
	}

	/**
	 * Evaluate the given classifier and all of its properties.
	 * 
	 * @param cls
	 */
	public void evaluateClassifier(EClassifier cls) {
		if (cls instanceof EClass) {
			evaluateClass((EClass) cls);		
		}
	}

	/**
	 * Evaluate the given EClass and all of its properties.
	 * 
	 * @param cls
	 */
	public abstract void evaluateClass(EClass cls);

	/**
	 * Return the final metric discovered.
	 * 
	 * @return the final metric to return
	 */
	public abstract Object getResult();

}
