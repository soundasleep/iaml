/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Additional Latex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.AdditionalLatex#getReference <em>Reference</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.AdditionalLatex#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalLatex()
 * @model
 * @generated
 */
public interface AdditionalLatex extends EObject {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(Reference)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalLatex_Reference()
	 * @model
	 * @generated
	 */
	Reference getReference();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.AdditionalLatex#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(Reference value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' reference.
	 * @see #setDescription(JavadocTextElement)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalLatex_Description()
	 * @model
	 * @generated
	 */
	JavadocTextElement getDescription();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.AdditionalLatex#getDescription <em>Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(JavadocTextElement value);

} // AdditionalLatex
