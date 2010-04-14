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
 * A representation of the model object '<em><b>Editable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Editable#getOnChange <em>On Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getEditable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Editable extends EObject {
	/**
	 * Returns the value of the '<em><b>On Change</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Change</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Change</em>' containment reference.
	 * @see #setOnChange(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getEditable_OnChange()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnChange();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Editable#getOnChange <em>On Change</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Change</em>' containment reference.
	 * @see #getOnChange()
	 * @generated
	 */
	void setOnChange(EventTrigger value);

} // Editable
