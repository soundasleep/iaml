/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Event Triggers</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ContainsEventTriggers#getEventTriggers <em>Event Triggers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getContainsEventTriggers()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ContainsEventTriggers extends EObject {
	/**
	 * Returns the value of the '<em><b>Event Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.EventTrigger}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Triggers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Triggers</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsEventTriggers_EventTriggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventTrigger> getEventTriggers();

} // ContainsEventTriggers
