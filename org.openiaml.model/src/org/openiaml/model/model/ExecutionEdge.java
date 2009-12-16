/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the execution flow within an {@link Operation}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ExecutionEdge#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.ExecutionEdge#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getExecutionEdge()
 * @model
 * @generated
 */
public interface ExecutionEdge extends GeneratedElement {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ExecutionEdgesSource#getOutExecutions <em>Out Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ExecutionEdgesSource)
	 * @see org.openiaml.model.model.ModelPackage#getExecutionEdge_From()
	 * @see org.openiaml.model.model.ExecutionEdgesSource#getOutExecutions
	 * @model opposite="outExecutions" required="true"
	 * @generated
	 */
	ExecutionEdgesSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.ExecutionEdge#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ExecutionEdgesSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ExecutionEdgeDestination#getInExecutions <em>In Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ExecutionEdgeDestination)
	 * @see org.openiaml.model.model.ModelPackage#getExecutionEdge_To()
	 * @see org.openiaml.model.model.ExecutionEdgeDestination#getInExecutions
	 * @model opposite="inExecutions" required="true"
	 * @generated
	 */
	ExecutionEdgeDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.ExecutionEdge#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ExecutionEdgeDestination value);

} // ExecutionEdge
