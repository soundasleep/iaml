/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Site Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.SiteScope#getSessions <em>Sessions</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.SiteScope#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.Package#getSiteScope()
 * @model
 * @generated
 */
public interface SiteScope extends NamedElement, Scope, ContainsOperations {
	/**
	 * Returns the value of the '<em><b>Sessions</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.scopes.Session}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sessions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sessions</em>' reference list.
	 * @see org.openiaml.model.model.scopes.Package#getSiteScope_Sessions()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	EList<Session> getSessions();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' reference list.
	 * @see org.openiaml.model.model.scopes.Package#getSiteScope_Components()
	 * @model
	 * @generated
	 */
	EList<ApplicationElement> getComponents();

} // SiteScope
