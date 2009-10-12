/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation;

import org.openiaml.model.model.visual.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path To</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.verification.model.validation.PathTo#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.verification.model.validation.PathTo#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.verification.model.validation.ValidationPackage#getPathTo()
 * @model
 * @generated
 */
public interface PathTo extends ValidationRule {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(Page)
	 * @see org.openiaml.verification.model.validation.ValidationPackage#getPathTo_From()
	 * @model
	 * @generated
	 */
	Page getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.verification.model.validation.PathTo#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Page value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(Page)
	 * @see org.openiaml.verification.model.validation.ValidationPackage#getPathTo_To()
	 * @model
	 * @generated
	 */
	Page getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.verification.model.validation.PathTo#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Page value);

} // PathTo
