/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.RequiresWire;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Access Control Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@link Page} or {@link Session}, enforces that all access contains the {@link Role roles} and {@link Permission permissions} provided by incoming {@link RequiresWire}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getAccessControlHandler()
 * @model annotation="http://openiaml.org/comment added='0.4'"
 * @generated
 */
public interface AccessControlHandler extends ApplicationElementContainer, GeneratesElements, ParameterEdgeDestination {
} // AccessControlHandler
