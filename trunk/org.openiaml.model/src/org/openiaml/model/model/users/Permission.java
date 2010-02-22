/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.wires.ParameterEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A single Permission that a user may have; may also be {@model ProvidesWire provided} by a {@model Role}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.users.UsersPackage#getPermission()
 * @model
 * @generated
 */
public interface Permission extends NamedElement, ParameterEdgesSource, RequiresEdgeDestination, ProvidesEdgeDestination {
} // Permission
