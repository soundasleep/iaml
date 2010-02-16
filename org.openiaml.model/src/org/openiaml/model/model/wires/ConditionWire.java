/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.WireEdgeDestination;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When connected to a {@link RunInstanceWire}, the connected {@link Condition Conditions} will be evaluated before any execution takes place.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getConditionWire()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface ConditionWire extends CompositeWire, WireEdgeDestination, ParameterEdgeDestination {
} // ConditionWire
