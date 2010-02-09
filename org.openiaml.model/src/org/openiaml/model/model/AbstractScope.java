/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.AbstractScope#getEntryGate <em>Entry Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.AbstractScope#getExitGate <em>Exit Gate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getAbstractScope()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment changed='0.4: \'domainObjects\', \'domainViews\', \'domainInstances\' references removed\r\n0.4.2: renamed from \'Scope\' to \'AbstractScope\'; added \'gate\' reference'"
 * @generated
 */
public interface AbstractScope extends GeneratesElements, ContainsWires {
	/**
	 * Returns the value of the '<em><b>Entry Gate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry Gate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Gate</em>' containment reference.
	 * @see #setEntryGate(EntryGate)
	 * @see org.openiaml.model.model.ModelPackage#getAbstractScope_EntryGate()
	 * @model containment="true"
	 * @generated
	 */
	EntryGate getEntryGate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.AbstractScope#getEntryGate <em>Entry Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry Gate</em>' containment reference.
	 * @see #getEntryGate()
	 * @generated
	 */
	void setEntryGate(EntryGate value);

	/**
	 * Returns the value of the '<em><b>Exit Gate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exit Gate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exit Gate</em>' containment reference.
	 * @see #setExitGate(ExitGate)
	 * @see org.openiaml.model.model.ModelPackage#getAbstractScope_ExitGate()
	 * @model containment="true"
	 * @generated
	 */
	ExitGate getExitGate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.AbstractScope#getExitGate <em>Exit Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exit Gate</em>' containment reference.
	 * @see #getExitGate()
	 * @generated
	 */
	void setExitGate(ExitGate value);

} // AbstractScope
