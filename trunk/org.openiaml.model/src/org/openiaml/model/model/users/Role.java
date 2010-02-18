/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.openiaml.model.model.DomainObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A particular Role that a user may have; may be {@link ExtendsWire inherited} from other Roles.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.users.UsersPackage#getRole()
 * @model annotation="http://openiaml.org/comment note='another option is to create \'extends : Role\' and \'extendedBy : Role\' as EOpposites, and remove ExtendsWire. but this means that this relationship cannot be used as the target or source of any wires (parameters? conditions?)'"
 * @generated
 */
public interface Role extends DomainObject, RequiresEdgeDestination, ProvidesEdgesSource {
} // Role
