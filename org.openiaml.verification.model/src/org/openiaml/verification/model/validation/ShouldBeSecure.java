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
 * A representation of the model object '<em><b>Should Be Secure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.verification.model.validation.ShouldBeSecure#getPage <em>Page</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.verification.model.validation.ValidationPackage#getShouldBeSecure()
 * @model
 * @generated
 */
public interface ShouldBeSecure extends ValidationRule {
	/**
	 * Returns the value of the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page</em>' reference.
	 * @see #setPage(Page)
	 * @see org.openiaml.verification.model.validation.ValidationPackage#getShouldBeSecure_Page()
	 * @model
	 * @generated
	 */
	Page getPage();

	/**
	 * Sets the value of the '{@link org.openiaml.verification.model.validation.ShouldBeSecure#getPage <em>Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page</em>' reference.
	 * @see #getPage()
	 * @generated
	 */
	void setPage(Page value);

} // ShouldBeSecure
