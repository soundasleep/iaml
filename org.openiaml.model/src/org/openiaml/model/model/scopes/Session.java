/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisitorAgent;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getAgents <em>Agents</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Session#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession()
 * @model annotation="http://openiaml.org/comment changed='0.4: no longer extends VisibleThing; now extends ContainsEventTriggers, ContainsConditions, WireEdgesSource and WireEdgeDestination'"
 * @generated
 */
public interface Session extends NamedElement, ContainsOperations, Scope, ContainsWires, ContainsEventTriggers, WireEdgesSource, WireEdgeDestination, ContainsConditions {
	/**
	 * Returns the value of the '<em><b>Agents</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.VisitorAgent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agents</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession_Agents()
	 * @model containment="true"
	 * @generated
	 */
	EList<VisitorAgent> getAgents();

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

} // Session
