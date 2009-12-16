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
 * A representation of the model object '<em><b>Split Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Execution flow can split off into multiple threads, reconnected with a {@link JoinNode}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getSplitNode()
 * @model annotation="http://openiaml.org/comment comment='added in 0.4'"
 * @generated
 */
public interface SplitNode extends ActivityNode, ExecutionEdgesSource, ExecutionEdgeDestination {
} // SplitNode
