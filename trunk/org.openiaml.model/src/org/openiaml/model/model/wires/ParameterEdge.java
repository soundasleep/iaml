/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Provides incoming values for {@link Parameter Parameters} of a {@link RunInstanceWire}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ParameterEdge#getParameterName <em>Parameter Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ParameterEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ParameterEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdge()
 * @model annotation="http://openiaml.org/comment changed='0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ParameterEdge'"
 * @generated
 */
public interface ParameterEdge extends NamedElement, GeneratedElement {
	/**
	 * Returns the value of the '<em><b>Parameter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Name</em>' attribute.
	 * @see #setParameterName(String)
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdge_ParameterName()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	String getParameterName();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ParameterEdge#getParameterName <em>Parameter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Name</em>' attribute.
	 * @see #getParameterName()
	 * @generated
	 */
	void setParameterName(String value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ParameterEdgesSource#getOutParameterEdges <em>Out Parameter Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ParameterEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdge_From()
	 * @see org.openiaml.model.model.wires.ParameterEdgesSource#getOutParameterEdges
	 * @model opposite="outParameterEdges" required="true"
	 * @generated
	 */
	ParameterEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ParameterEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ParameterEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ParameterEdgeDestination#getInParameterEdges <em>In Parameter Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ParameterEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdge_To()
	 * @see org.openiaml.model.model.wires.ParameterEdgeDestination#getInParameterEdges
	 * @model opposite="inParameterEdges" required="true"
	 * @generated
	 */
	ParameterEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ParameterEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ParameterEdgeDestination value);

} // ParameterEdge
