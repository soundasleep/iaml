/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Scopes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ContainsScopes#getScopes <em>Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getContainsScopes()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.2'"
 * @generated
 */
public interface ContainsScopes extends ContainsOperations {
	/**
	 * Returns the value of the '<em><b>Scopes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Scope}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scopes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scopes</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsScopes_Scopes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Scope> getScopes();

} // ContainsScopes
