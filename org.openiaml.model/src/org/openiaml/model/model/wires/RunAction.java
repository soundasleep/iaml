/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.Action;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Run Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connects a {@model EventTrigger} to a {@model Operation}, allowing it to be executed. May have incoming {@model ConditionEdge}s.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getRunAction()
 * @model annotation="http://openiaml.org/comment comment='RunWire: a composite wire that contains ExecutionWires/etc'"
 * @generated
 */
public interface RunAction extends WireDestination, ParameterEdgeDestination, NamedElement, GeneratesElements, ConditionEdgeDestination, Action {

} // RunAction
