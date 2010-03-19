/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.wires.ParameterEdgeDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Access Control Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@model Frame} or {@model Session}, enforces that all access contains the {@model Role roles} and {@model Permission permissions} provided by incoming {@model RequiresEdge}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getAccessControlHandler()
 * @model annotation="http://openiaml.org/comment added='0.4'"
 * @generated
 */
public interface AccessControlHandler extends GeneratesElements, ParameterEdgeDestination, RequiresEdgesSource, ApplicationElement, ContainsWires {
} // AccessControlHandler
