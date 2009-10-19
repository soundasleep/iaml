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
	 * Returns a new object of class '<em>Path To</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path To</em>'.
	 * @generated
	 */
	PathTo createPathTo();

	/**
	 * Returns a new object of class '<em>Should Be Secure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Should Be Secure</em>'.
	 * @generated
	 */
	ShouldBeSecure createShouldBeSecure();

	/**
	 * Returns a new object of class '<em>Is Secure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Is Secure</em>'.
	 * @generated
	 */
	IsSecure createIsSecure();

	/**
	 * Returns a new object of class '<em>Secure Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secure Page</em>'.
	 * @generated
	 */
	SecurePage createSecurePage();

	/**
	 * Returns a new object of class '<em>Secure Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secure Operation</em>'.
	 * @generated
	 */
	SecureOperation createSecureOperation();

	/**
	 * Returns a new object of class '<em>Execution Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Path</em>'.
	 * @generated
	 */
	ExecutionPath createExecutionPath();

	/**
	 * Returns a new object of class '<em>Secure Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secure Path</em>'.
	 * @generated
	 */
	SecurePath createSecurePath();

	/**
	 * Returns a new object of class '<em>Secure Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secure Node</em>'.
	 * @generated
	 */
	SecureNode createSecureNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ValidationPackage getValidationPackage();

} //ValidationFactory
