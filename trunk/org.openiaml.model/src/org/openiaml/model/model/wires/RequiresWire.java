/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.components.LoginHandler;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requires Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the requirements for a {@link LoginHandler}, such as {@link Role Roles} and {@link Permission Permissions}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getRequiresWire()
 * @model annotation="http://openiaml.org/comment added='0.4'"
 * @generated
 */
public interface RequiresWire extends SingleWire, WireEdgesSource, WireEdgeDestination {
} // RequiresWire
