/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A {@model SingleWire} represents a single piece of runtime functionality that cannot be decomposed further. A {@model CompositeWire} may ultimately be composed of many of these.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getSingleWire()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment comment='why do we have SingleWire at all? (following a design pattern?)' changed='0.4.2 to abstract'"
 * @generated
 */
public interface SingleWire extends WireEdge {
} // SingleWire
