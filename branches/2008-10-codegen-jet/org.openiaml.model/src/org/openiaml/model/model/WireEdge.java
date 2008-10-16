/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.WireEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.WireEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWireEdge()
 * @model
 * @generated
 */
public interface WireEdge extends GeneratedElement {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireEdgesSource#getOutEdges <em>Out Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(WireEdgesSource)
	 * @see org.openiaml.model.model.ModelPackage#getWireEdge_From()
	 * @see org.openiaml.model.model.WireEdgesSource#getOutEdges
	 * @model opposite="outEdges" required="true"
	 * @generated
	 */
	WireEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.WireEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(WireEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireEdgeDestination#getInEdges <em>In Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(WireEdgeDestination)
	 * @see org.openiaml.model.model.ModelPackage#getWireEdge_To()
	 * @see org.openiaml.model.model.WireEdgeDestination#getInEdges
	 * @model opposite="inEdges" required="true"
	 * @generated
	 */
	WireEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.WireEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(WireEdgeDestination value);

} // WireEdge
