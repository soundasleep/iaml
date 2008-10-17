/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getDataEdges <em>Data Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getExecutionEdges <em>Execution Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#get_shouldnt_parameters <em>shouldnt parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation()
 * @model
 * @generated
 */
public interface CompositeOperation extends ChainedOperation, ContainsOperations, ContainsWires {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ActivityNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ActivityNode> getNodes();

	/**
	 * Returns the value of the '<em><b>Data Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DataFlowEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_DataEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataFlowEdge> getDataEdges();

	/**
	 * Returns the value of the '<em><b>Execution Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ExecutionEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_ExecutionEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionEdge> getExecutionEdges();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.TemporaryVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<TemporaryVariable> getVariables();

	/**
	 * Returns the value of the '<em><b>shouldnt parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>shouldnt parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>shouldnt parameters</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation__shouldnt_parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElementProperty> get_shouldnt_parameters();

} // CompositeOperation
