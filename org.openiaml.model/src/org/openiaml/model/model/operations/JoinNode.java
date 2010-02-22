/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations;

import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Join Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Joins multiple {@model SplitNode split} execution threads back together. Halts until all threads are complete.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getJoinNode()
 * @model annotation="http://openiaml.org/comment comment='added in 0.4'"
 * @generated
 */
public interface JoinNode extends ActivityNode, ExecutionEdgesSource, ExecutionEdgeDestination {
} // JoinNode
