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
 * A representation of the model object '<em><b>Data Flow Edge Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DataFlowEdgeDestination#getInFlows <em>In Flows</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdgeDestination()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DataFlowEdgeDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Flows</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DataFlowEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.DataFlowEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Flows</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdgeDestination_InFlows()
	 * @see org.openiaml.model.model.DataFlowEdge#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<DataFlowEdge> getInFlows();

} // DataFlowEdgeDestination
