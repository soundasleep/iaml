/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.owl.simple.simple;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.owl.simple.simple.SimplePackage
 * @generated
 */
public interface SimpleFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleFactory eINSTANCE = org.openiaml.model.owl.simple.simple.impl.SimpleFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Internet Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Internet Application</em>'.
	 * @generated
	 */
	InternetApplication createInternetApplication();

	/**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
	Page createPage();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimplePackage getSimplePackage();

} //SimpleFactory
