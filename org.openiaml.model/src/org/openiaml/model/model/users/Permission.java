/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.ProvidesWire;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A single Permission that a user may have; may also be {@link ProvidesWire provided} by a {@link Role}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.users.UsersPackage#getPermission()
 * @model
 * @generated
 */
public interface Permission extends NamedElement, WireEdgesSource, WireEdgeDestination {
} // Permission
