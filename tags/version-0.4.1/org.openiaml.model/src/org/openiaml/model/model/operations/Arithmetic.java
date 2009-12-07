/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations;

import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.PrimitiveOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arithmetic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An inline expression of arithmetic. Can be used as part of a {@link PrimitiveOperation set operation}, for example.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.operations.Arithmetic#getOperationType <em>Operation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getArithmetic()
 * @model annotation="http://openiaml.org/comment comment='added in 0.4.1'"
 * @generated
 */
public interface Arithmetic extends ActivityNode, DataFlowEdgeDestination, DataFlowEdgesSource {
	/**
	 * Returns the value of the '<em><b>Operation Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.openiaml.model.model.operations.ArithmeticOperationTypes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Type</em>' attribute.
	 * @see org.openiaml.model.model.operations.ArithmeticOperationTypes
	 * @see #setOperationType(ArithmeticOperationTypes)
	 * @see org.openiaml.model.model.operations.OperationsPackage#getArithmetic_OperationType()
	 * @model
	 * @generated
	 */
	ArithmeticOperationTypes getOperationType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.operations.Arithmetic#getOperationType <em>Operation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Type</em>' attribute.
	 * @see org.openiaml.model.model.operations.ArithmeticOperationTypes
	 * @see #getOperationType()
	 * @generated
	 */
	void setOperationType(ArithmeticOperationTypes value);

} // Arithmetic
