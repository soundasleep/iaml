/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requires Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the requirements for a {@link LoginHandler}, such as {@link Role Roles} and {@link Permission Permissions}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.RequiresEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.RequiresEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getRequiresEdge()
 * @model annotation="http://openiaml.org/comment added='0.4' changed='0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to RequiresEdge'"
 * @generated
 */
public interface RequiresEdge extends GeneratedElement, ConstraintEdgesSource, ConstraintEdgeDestination, ContainsWires {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.users.RequiresEdgesSource#getOutRequiresEdges <em>Out Requires Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(RequiresEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getRequiresEdge_From()
	 * @see org.openiaml.model.model.users.RequiresEdgesSource#getOutRequiresEdges
	 * @model opposite="outRequiresEdges" required="true"
	 * @generated
	 */
	RequiresEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.RequiresEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(RequiresEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.users.RequiresEdgeDestination#getInRequiresEdges <em>In Requires Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(RequiresEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getRequiresEdge_To()
	 * @see org.openiaml.model.model.users.RequiresEdgeDestination#getInRequiresEdges
	 * @model opposite="inRequiresEdges" required="true"
	 * @generated
	 */
	RequiresEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.RequiresEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(RequiresEdgeDestination value);

} // RequiresEdge
