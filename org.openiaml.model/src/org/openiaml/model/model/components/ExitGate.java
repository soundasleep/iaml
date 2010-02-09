/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Page;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exit Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@link Scope} (including {@link Session}), all {@link Page} accesses within that Scope must finally execute this {@link Gate}. If any outgoing {@link WireEdge wires} {@link Condition cannot execute}, then access out of this {@link Scope} is denied until such time the {@link Condition} is now false.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getExitGate()
 * @model annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface ExitGate extends Gate {
} // ExitGate
