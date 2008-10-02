/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Flow Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DataFlowEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.DataFlowEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdge()
 * @model
 * @generated
 */
public interface DataFlowEdge extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.DataFlowEdgesSource#getOutFlows <em>Out Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(DataFlowEdgesSource)
	 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdge_From()
	 * @see org.openiaml.model.model.DataFlowEdgesSource#getOutFlows
	 * @model opposite="outFlows" required="true"
	 * @generated
	 */
	DataFlowEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DataFlowEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(DataFlowEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.DataFlowEdgeDestination#getInFlows <em>In Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(DataFlowEdgeDestination)
	 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdge_To()
	 * @see org.openiaml.model.model.DataFlowEdgeDestination#getInFlows
	 * @model opposite="inFlows" required="true"
	 * @generated
	 */
	DataFlowEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DataFlowEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(DataFlowEdgeDestination value);

} // DataFlowEdge
