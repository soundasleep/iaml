/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.ActionDestination;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.wires.ConditionEdgeDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getGate()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface Gate extends NamedElement, WireSource, WireDestination, GeneratedElement, GeneratesElements, ConditionEdgeDestination, ActionSource, ActionDestination {
} // Gate
