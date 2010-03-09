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
 * A representation of the model object '<em><b>Action Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ActionDestination#getInActions <em>In Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getActionDestination()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ActionDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Actions</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Action}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.Action#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Actions</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getActionDestination_InActions()
	 * @see org.openiaml.model.model.Action#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Action> getInActions();

} // ActionDestination
