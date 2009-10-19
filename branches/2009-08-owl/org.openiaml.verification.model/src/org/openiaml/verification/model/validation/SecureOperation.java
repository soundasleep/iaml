/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation;

import org.openiaml.model.model.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Secure Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.verification.model.validation.SecureOperation#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.verification.model.validation.ValidationPackage#getSecureOperation()
 * @model
 * @generated
 */
public interface SecureOperation extends ValidationRule {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see org.openiaml.verification.model.validation.ValidationPackage#getSecureOperation_Operation()
	 * @model
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.openiaml.verification.model.validation.SecureOperation#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

} // SecureOperation
