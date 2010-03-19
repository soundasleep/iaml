/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Accessible</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Accessible#getOnAccess <em>On Access</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getAccessible()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Accessible extends EObject {
	/**
	 * Returns the value of the '<em><b>On Access</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Access</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Access</em>' containment reference.
	 * @see #setOnAccess(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getAccessible_OnAccess()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnAccess();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Accessible#getOnAccess <em>On Access</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Access</em>' containment reference.
	 * @see #getOnAccess()
	 * @generated
	 */
	void setOnAccess(EventTrigger value);

} // Accessible
