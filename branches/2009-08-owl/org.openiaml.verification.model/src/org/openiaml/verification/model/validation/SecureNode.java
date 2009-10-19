/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Secure Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.verification.model.validation.SecureNode#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.verification.model.validation.ValidationPackage#getSecureNode()
 * @model
 * @generated
 */
public interface SecureNode extends ValidationRule {
	/**
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(EObject)
	 * @see org.openiaml.verification.model.validation.ValidationPackage#getSecureNode_Node()
	 * @model
	 * @generated
	 */
	EObject getNode();

	/**
	 * Sets the value of the '{@link org.openiaml.verification.model.validation.SecureNode#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(EObject value);

} // SecureNode
