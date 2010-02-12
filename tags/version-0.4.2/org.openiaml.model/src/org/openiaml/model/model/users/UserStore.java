/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.DomainStore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Contains {@link Role Roles} and {@link Permission Permissions}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.users.UserStore#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.users.UsersPackage#getUserStore()
 * @model
 * @generated
 */
public interface UserStore extends DomainStore {

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.users.Permission}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' containment reference list.
	 * @see org.openiaml.model.model.users.UsersPackage#getUserStore_Permissions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Permission> getPermissions();
} // UserStore
