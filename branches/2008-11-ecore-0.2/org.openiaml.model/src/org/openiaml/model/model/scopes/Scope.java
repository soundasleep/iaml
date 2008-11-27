/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Scope#getDomainObjects <em>Domain Objects</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Scope#getDomainViews <em>Domain Views</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Scope#getDomainInstances <em>Domain Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Scope extends EObject {
	/**
	 * Returns the value of the '<em><b>Domain Objects</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Objects</em>' reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope_DomainObjects()
	 * @model
	 * @generated
	 */
	EList<DomainObject> getDomainObjects();

	/**
	 * Returns the value of the '<em><b>Domain Views</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DerivedView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Views</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Views</em>' reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope_DomainViews()
	 * @model
	 * @generated
	 */
	EList<DerivedView> getDomainViews();

	/**
	 * Returns the value of the '<em><b>Domain Instances</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainObjectInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Instances</em>' reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope_DomainInstances()
	 * @model
	 * @generated
	 */
	EList<DomainObjectInstance> getDomainInstances();

} // Scope
