/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Scope#getDomainObjects <em>Domain Objects</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getDomainViews <em>Domain Views</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getDomainInstances <em>Domain Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getScope()
 * @model abstract="true"
 * @generated
 */
public interface Scope extends GeneratesElements {
	/**
	 * Returns the value of the '<em><b>Domain Objects</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Objects</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getScope_DomainObjects()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='0.2 to extend the abstract counterpart'"
	 * @generated
	 */
	EList<DomainObject> getDomainObjects();

	/**
	 * Returns the value of the '<em><b>Domain Views</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DerivedView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Views</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Views</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getScope_DomainViews()
	 * @model containment="true"
	 * @generated
	 */
	EList<DerivedView> getDomainViews();

	/**
	 * Returns the value of the '<em><b>Domain Instances</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainObjectInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Instances</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getScope_DomainInstances()
	 * @model containment="true"
	 * @generated
	 */
	EList<DomainObjectInstance> getDomainInstances();

} // Scope
