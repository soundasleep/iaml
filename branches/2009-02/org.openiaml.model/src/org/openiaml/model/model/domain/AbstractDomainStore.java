/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.GeneratesElements;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Domain Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.AbstractDomainStore#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.AbstractDomainStore#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.AbstractDomainStore#getViews <em>Views</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainStore()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDomainStore extends ContainsOperations, ContainsEventTriggers, ContainsWires, GeneratesElements, EClass {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.domain.AbstractDomainObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainStore_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractDomainObject> getChildren();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainStore_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElementProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Views</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DerivedView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Views</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Views</em>' reference list.
	 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainStore_Views()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	EList<DerivedView> getViews();

} // AbstractDomainStore
