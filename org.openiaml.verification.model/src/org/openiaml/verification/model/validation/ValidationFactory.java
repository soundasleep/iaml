/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.verification.model.validation.ValidationPackage
 * @generated
 */
public interface ValidationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ValidationFactory eINSTANCE = org.openiaml.verification.model.validation.impl.ValidationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Navigates To</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigates To</em>'.
	 * @generated
	 */
	NavigatesTo createNavigatesTo();

	/**
	 * Returns a new object of class '<em>Violation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Violation</em>'.
	 * @generated
	 */
	Violation createViolation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ValidationPackage getValidationPackage();

} //ValidationFactory
