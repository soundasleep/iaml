/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@link Scope} (including {@link Session}), all {@link Page} accesses within that Scope must first execute this {@link Gate}. If any outgoing {@link WireEdge wires} {@link Condition can execute}, then access to this {@link Scope} is denied until such time the {@link Condition} is now true.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getGate()
 * @model annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface Gate extends NamedElement, WireEdgesSource, WireEdgeDestination, GeneratedElement, GeneratesElements {
} // Gate
