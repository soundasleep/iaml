/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exit Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When placed into a {@model Scope} (including {@model Session}), all {@model Frame} accesses within that Scope must finally execute this {@model Gate}. If all incoming {@model ConditionEdge}s are true, then access to this {@model Scope} is denied until such time any {@model ConditionEdge}s becomes false.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getExitGate()
 * @model annotation="http://openiaml.org/comment added='0.4.2'"
 * @generated
 */
public interface ExitGate extends Gate {
} // ExitGate
