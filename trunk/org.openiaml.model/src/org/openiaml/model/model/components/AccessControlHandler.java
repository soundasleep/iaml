/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.users.ProvidesEdgesSource;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.wires.ParameterEdgeDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Access Control Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@model Page} or {@model Session}, enforces that all access contains the {@model Role roles} and {@model Permission permissions} provided by incoming {@model RequiresWire}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getAccessControlHandler()
 * @model annotation="http://openiaml.org/comment added='0.4'"
 * @generated
 */
public interface AccessControlHandler extends ApplicationElementContainer, GeneratesElements, ParameterEdgeDestination, RequiresEdgesSource, ProvidesEdgesSource {
} // AccessControlHandler
