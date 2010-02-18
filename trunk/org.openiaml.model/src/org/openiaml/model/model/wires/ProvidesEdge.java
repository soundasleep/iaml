/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.users.ProvidesEdgeDestination;
import org.openiaml.model.model.users.ProvidesEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Indicates that the source {@link Role} provides the target {@link Permission}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ProvidesEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.ProvidesEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getProvidesEdge()
 * @model annotation="http://openiaml.org/comment added='0.4' changed='0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ProvidesEdge'"
 * @generated
 */
public interface ProvidesEdge extends GeneratedElement {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.users.ProvidesEdgesSource#getOutProvidesEdges <em>Out Provides Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ProvidesEdgesSource)
	 * @see org.openiaml.model.model.wires.WiresPackage#getProvidesEdge_From()
	 * @see org.openiaml.model.model.users.ProvidesEdgesSource#getOutProvidesEdges
	 * @model opposite="outProvidesEdges" required="true"
	 * @generated
	 */
	ProvidesEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ProvidesEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ProvidesEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.users.ProvidesEdgeDestination#getInProvidesEdges <em>In Provides Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ProvidesEdgeDestination)
	 * @see org.openiaml.model.model.wires.WiresPackage#getProvidesEdge_To()
	 * @see org.openiaml.model.model.users.ProvidesEdgeDestination#getInProvidesEdges
	 * @model opposite="inProvidesEdges" required="true"
	 * @generated
	 */
	ProvidesEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ProvidesEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ProvidesEdgeDestination value);

} // ProvidesEdge
