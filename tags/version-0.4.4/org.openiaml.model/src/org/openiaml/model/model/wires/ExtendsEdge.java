/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the inheritance of all target elements by the source element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ExtendsEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ExtendsEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getExtendsEdge()
 * @model annotation="http://openiaml.org/comment added='0.4' changed='0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ExtendsEdge'"
 * @generated
 */
public interface ExtendsEdge extends GeneratedElement, GeneratesElements {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ExtendsEdgesSource#getOutExtendsEdges <em>Out Extends Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ExtendsEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getExtendsEdge_From()
	 * @see org.openiaml.model.model.wires.ExtendsEdgesSource#getOutExtendsEdges
	 * @model opposite="outExtendsEdges" required="true"
	 * @generated
	 */
	ExtendsEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ExtendsEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ExtendsEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ExtendsEdgeDestination#getInExtendsEdges <em>In Extends Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ExtendsEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getExtendsEdge_To()
	 * @see org.openiaml.model.model.wires.ExtendsEdgeDestination#getInExtendsEdges
	 * @model opposite="inExtendsEdges" required="true"
	 * @generated
	 */
	ExtendsEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ExtendsEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ExtendsEdgeDestination value);

} // ExtendsEdge
