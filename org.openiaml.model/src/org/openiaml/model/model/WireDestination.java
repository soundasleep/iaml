/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.WireDestination#getInWires <em>In Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWireDestination()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface WireDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Wires</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Wire}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.Wire#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Wires</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Wires</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getWireDestination_InWires()
	 * @see org.openiaml.model.model.Wire#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Wire> getInWires();

} // WireDestination
