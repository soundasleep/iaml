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
 * A representation of the model object '<em><b>Data Flow Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DataFlowEdgesSource#getOutFlows <em>Out Flows</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdgesSource()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DataFlowEdgesSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Out Flows</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DataFlowEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.DataFlowEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Flows</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDataFlowEdgesSource_OutFlows()
	 * @see org.openiaml.model.model.DataFlowEdge#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<DataFlowEdge> getOutFlows();

} // DataFlowEdgesSource
