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
 * A representation of the model object '<em><b>Execution Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ExecutionEdgesSource#getOutExecutions <em>Out Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getExecutionEdgesSource()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ExecutionEdgesSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Out Executions</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ExecutionEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ExecutionEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Executions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Executions</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getExecutionEdgesSource_OutExecutions()
	 * @see org.openiaml.model.model.ExecutionEdge#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<ExecutionEdge> getOutExecutions();

} // ExecutionEdgesSource
