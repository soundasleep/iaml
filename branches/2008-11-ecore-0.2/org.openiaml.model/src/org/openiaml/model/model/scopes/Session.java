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
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.VisitorAgent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getAgent <em>Agent</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession()
 * @model
 * @generated
 */
public interface Session extends NamedElement, Scope, ContainsOperations, VisibleThing {
	/**
	 * Returns the value of the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agent</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent</em>' containment reference.
	 * @see #setAgent(VisitorAgent)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Agent()
	 * @model containment="true"
	 * @generated
	 */
	VisitorAgent getAgent();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Session#getAgent <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agent</em>' containment reference.
	 * @see #getAgent()
	 * @generated
	 */
	void setAgent(VisitorAgent value);

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
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Components()
	 * @model
	 * @generated
	 */
	EList<ApplicationElement> getComponents();

} // Session
