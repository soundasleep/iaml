/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a user session; contained data is normally not accessible by other users.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getSession()
 * @model annotation="http://openiaml.org/comment changed='0.4: no longer extends VisibleThing; now extends ContainsEventTriggers, ContainsConditions, WireEdgesSource and WireEdgeDestination\r\n0.4.2 removed \'agents\' reference'"
 * @generated
 */
public interface Session extends NamedElement, ContainsOperations, Scope, ContainsWires, WireSource, WireDestination, ContainsConditions {

} // Session
