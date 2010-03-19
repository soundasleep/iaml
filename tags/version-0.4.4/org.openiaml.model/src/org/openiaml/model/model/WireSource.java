/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.WireSource#getOutWires <em>Out Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWireSource()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='temporarily contains wires until GMF bug is fixed '"
 * @generated
 */
public interface WireSource extends ShouldntContainWires {
	/**
	 * Returns the value of the '<em><b>Out Wires</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Wire}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.Wire#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Wires</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Wires</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getWireSource_OutWires()
	 * @see org.openiaml.model.model.Wire#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Wire> getOutWires();

} // WireSource
