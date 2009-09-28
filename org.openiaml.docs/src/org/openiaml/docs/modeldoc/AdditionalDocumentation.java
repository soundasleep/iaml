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
 * A representation of the model object '<em><b>Additional Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getReference <em>Reference</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getDescriptionHtml <em>Description Html</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalDocumentation()
 * @model
 * @generated
 */
public interface AdditionalDocumentation extends EObject {
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalDocumentation_Reference()
	 * @model
	 * @generated
	 */
	Reference getReference();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(Reference value);

	/**
	 * Returns the value of the '<em><b>Description Html</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description Html</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description Html</em>' attribute.
	 * @see #setDescriptionHtml(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getAdditionalDocumentation_DescriptionHtml()
	 * @model
	 * @generated
	 */
	String getDescriptionHtml();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getDescriptionHtml <em>Description Html</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description Html</em>' attribute.
	 * @see #getDescriptionHtml()
	 * @generated
	 */
	void setDescriptionHtml(String value);

} // AdditionalDocumentation
