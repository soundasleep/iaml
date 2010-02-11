/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.wires.ConditionWire;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@link Scope} (including {@link Session}), all {@link Page} accesses within that Scope must first execute this {@link Gate}. If all incoming {@link ConditionWire}s are true, then access to this {@link Scope} is denied until such time any {@link ConditionWire}s becomes false.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getEntryGate()
 * @model annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface EntryGate extends Gate {
} // EntryGate
