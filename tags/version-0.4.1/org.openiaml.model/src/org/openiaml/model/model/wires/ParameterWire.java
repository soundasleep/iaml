/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.NamedElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Provides incoming values for {@link Parameter Parameters} of a {@link RunInstanceWire}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ParameterWire#getParameterName <em>Parameter Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getParameterWire()
 * @model
 * @generated
 */
public interface ParameterWire extends SingleWire, NamedElement {

	/**
	 * Returns the value of the '<em><b>Parameter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Name</em>' attribute.
	 * @see #setParameterName(String)
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterWire_ParameterName()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	String getParameterName();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ParameterWire#getParameterName <em>Parameter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Name</em>' attribute.
	 * @see #getParameterName()
	 * @generated
	 */
	void setParameterName(String value);
} // ParameterWire
