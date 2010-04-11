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
import org.openiaml.model.model.wires.ConditionEdgeDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decision Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Considers the {@model ConditionEdgeDestination incoming} {@model Condition}, and follows either the passing or failing {@model ExecutionEdge} as appropriate.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getDecisionNode()
 * @model
 * @generated
 */
public interface DecisionNode extends ActivityNode, ExecutionEdgesSource, ExecutionEdgeDestination, ConditionEdgeDestination {
} // DecisionNode
