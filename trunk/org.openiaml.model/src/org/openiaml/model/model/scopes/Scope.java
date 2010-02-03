/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.AbstractScope;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.visual.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents some sort of scope, which can be contained within a {@link Page}, {@link Session} or {@link InternetApplication}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Scope#getScopes <em>Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope()
 * @model annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface Scope extends AbstractScope, NamedElement, ContainsConditions, ContainsEventTriggers, ContainsOperations, ContainsWires, WireEdgesSource, WireEdgeDestination {
	/**
	 * Returns the value of the '<em><b>Scopes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.AbstractScope}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scopes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scopes</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getScope_Scopes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractScope> getScopes();

} // Scope
