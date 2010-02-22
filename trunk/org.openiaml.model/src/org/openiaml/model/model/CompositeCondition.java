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
 * A representation of the model object '<em><b>Composite Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A custom condition, composed in the same way as {@model CompositeOperation operations}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getDataEdges <em>Data Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getExecutionEdges <em>Execution Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.CompositeCondition#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition()
 * @model annotation="http://openiaml.org/comment comment='added in 0.2\r\n_shouldnt_properties removed in 0.4\r\nContainsOperations added in 0.4.1'"
 * @generated
 */
public interface CompositeCondition extends ContainsConditions, Condition, GeneratesElements, ContainsOperations {
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
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_Nodes()
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
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_DataEdges()
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
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_ExecutionEdges()
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
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_Variables()
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
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<StaticValue> getValues();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeCondition_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

} // CompositeCondition
