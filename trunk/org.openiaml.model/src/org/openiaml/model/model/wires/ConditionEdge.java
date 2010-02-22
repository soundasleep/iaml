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
 * A representation of the model object '<em><b>Condition Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows the conditional execution of {@model ConditionEdgeDestination targets} such as {@model WireEdge wires}. Only if the {@model ConditionEdgesSource incoming condition} is true, will the execution be permitted.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ConditionEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ConditionEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getConditionEdge()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface ConditionEdge extends ParameterEdgeDestination, NamedElement, GeneratesElements {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ConditionEdgesSource#getOutConditionEdges <em>Out Condition Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ConditionEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConditionEdge_From()
	 * @see org.openiaml.model.model.wires.ConditionEdgesSource#getOutConditionEdges
	 * @model opposite="outConditionEdges" required="true"
	 * @generated
	 */
	ConditionEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConditionEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ConditionEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ConditionEdgeDestination#getInConditionEdges <em>In Condition Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ConditionEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConditionEdge_To()
	 * @see org.openiaml.model.model.wires.ConditionEdgeDestination#getInConditionEdges
	 * @model opposite="inConditionEdges" required="true"
	 * @generated
	 */
	ConditionEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConditionEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ConditionEdgeDestination value);

} // ConditionEdge
