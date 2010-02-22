/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows for the definition of complex {@model RequiresEdge requirements}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ConstraintEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ConstraintEdge#getTo <em>To</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ConstraintEdge#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdge()
 * @model annotation="http://openiaml.org/comment added='0.4' changed='0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ConstraintEdge'"
 * @generated
 */
public interface ConstraintEdge extends ParameterEdgeDestination, NamedElement, GeneratesElements {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ConstraintEdgesSource#getOutConstraintEdges <em>Out Constraint Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ConstraintEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdge_From()
	 * @see org.openiaml.model.model.wires.ConstraintEdgesSource#getOutConstraintEdges
	 * @model opposite="outConstraintEdges" required="true"
	 * @generated
	 */
	ConstraintEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConstraintEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ConstraintEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ConstraintEdgeDestination#getInConstraintEdges <em>In Constraint Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ConstraintEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdge_To()
	 * @see org.openiaml.model.model.wires.ConstraintEdgeDestination#getInConstraintEdges
	 * @model opposite="inConstraintEdges" required="true"
	 * @generated
	 */
	ConstraintEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConstraintEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ConstraintEdgeDestination value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.openiaml.model.model.wires.ConstraintTypes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.wires.ConstraintTypes
	 * @see #setType(ConstraintTypes)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdge_Type()
	 * @model required="true"
	 * @generated
	 */
	ConstraintTypes getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConstraintEdge#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.wires.ConstraintTypes
	 * @see #getType()
	 * @generated
	 */
	void setType(ConstraintTypes value);

} // ConstraintEdge
