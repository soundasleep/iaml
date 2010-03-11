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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cast Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows one {@model DataEdgeSource data type} to be cast to another {@model DataEdgeDestination data type}. Has an outgoing "failure" {@model DataEdge} which can be used to check for invalid casts (otherwise a failing conversion is silent).
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.operations.OperationsPackage#getCastNode()
 * @model
 * @generated
 */
public interface CastNode extends ActivityNode, DataFlowEdgesSource, DataFlowEdgeDestination {
} // CastNode
