/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.AbstractScope;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a user session; contained data is normally not accessible by other users.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getScopes <em>Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession()
 * @model annotation="http://openiaml.org/comment changed='0.4: no longer extends VisibleThing; now extends ContainsEventTriggers, ContainsConditions, WireEdgesSource and WireEdgeDestination\r\n0.4.2 removed \'agents\' reference'"
 * @generated
 */
public interface Session extends NamedElement, ContainsOperations, AbstractScope, ContainsWires, ContainsEventTriggers, WireEdgesSource, WireEdgeDestination, ContainsConditions {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Children()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment renamed='0.4: from \'components\' to \'children\''"
	 * @generated
	 */
	EList<ApplicationElement> getChildren();

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
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Properties()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4'"
	 * @generated
	 */
	EList<ApplicationElementProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.StaticValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Values()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4'"
	 * @generated
	 */
	EList<StaticValue> getValues();

	/**
	 * Returns the value of the '<em><b>Scopes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.scopes.Scope}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scopes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scopes</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Scopes()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<Scope> getScopes();

} // Session
