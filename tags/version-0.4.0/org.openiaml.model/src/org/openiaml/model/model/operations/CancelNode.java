/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations;

import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ExecutionEdgeDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cancel Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Indicates that the current {@link Operation} did not execute successfully, or the {@link Condition} failed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.operations.CancelNode#getExceptionText <em>Exception Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getCancelNode()
 * @model
 * @generated
 */
public interface CancelNode extends ActivityNode, ExecutionEdgeDestination {

	/**
	 * Returns the value of the '<em><b>Exception Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Text</em>' attribute.
	 * @see #setExceptionText(String)
	 * @see org.openiaml.model.model.operations.OperationsPackage#getCancelNode_ExceptionText()
	 * @model
	 * @generated
	 */
	String getExceptionText();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.operations.CancelNode#getExceptionText <em>Exception Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Text</em>' attribute.
	 * @see #getExceptionText()
	 * @generated
	 */
	void setExceptionText(String value);
} // CancelNode
