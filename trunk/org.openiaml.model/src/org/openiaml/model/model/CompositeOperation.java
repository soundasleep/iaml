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
 * <!-- begin-model-doc -->
 * Operations which are composed of logic flows, data flows, and other operations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getDataEdges <em>Data Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getExecutionEdges <em>Execution Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation()
 * @model annotation="http://openiaml.org/comment comment='Scope supertype added in 0.2\r\n_shouldnt_properties removed in 0.4'"
 * @generated
 */
public interface CompositeOperation extends PrimitiveOperation, ContainsOperations, ContainsWires, GeneratesElements, Scope, ContainsConditions {
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
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.StaticValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<StaticValue> getValues();

} // CompositeOperation
