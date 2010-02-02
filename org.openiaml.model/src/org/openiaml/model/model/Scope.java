/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.openiaml.model.model.components.Gate;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Scope#getGate <em>Gate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getScope()
 * @model annotation="http://openiaml.org/comment changed='0.4: \'domainObjects\', \'domainViews\', \'domainInstances\' references removed\r\n0.4.2: no longer abstract; added \'gate\' reference'"
 * @generated
 */
public interface Scope extends GeneratesElements, ContainsWires {

	/**
	 * Returns the value of the '<em><b>Gate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gate</em>' containment reference.
	 * @see #setGate(Gate)
	 * @see org.openiaml.model.model.ModelPackage#getScope_Gate()
	 * @model containment="true"
	 * @generated
	 */
	Gate getGate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Scope#getGate <em>Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gate</em>' containment reference.
	 * @see #getGate()
	 * @generated
	 */
	void setGate(Gate value);

} // Scope
