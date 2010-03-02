/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A {@mode Wire} represents some piece of runtime functionality, usually in terms of the {@model EventTrigger Event}-{@model Condition}-{@model Operation Action} paradigm. These are expressed in {@model SingleWire}s.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Wire#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.Wire#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWire()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment changed='0.4.2 to abstract'"
 * @generated
 */
public interface Wire extends GeneratedElement {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireSource#getOutWires <em>Out Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(WireSource)
	 * @see org.openiaml.model.model.ModelPackage#getWire_From()
	 * @see org.openiaml.model.model.WireSource#getOutWires
	 * @model opposite="outWires" required="true"
	 * @generated
	 */
	WireSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Wire#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(WireSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireDestination#getInWires <em>In Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(WireDestination)
	 * @see org.openiaml.model.model.ModelPackage#getWire_To()
	 * @see org.openiaml.model.model.WireDestination#getInWires
	 * @model opposite="inWires" required="true"
	 * @generated
	 */
	WireDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Wire#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(WireDestination value);

} // Wire
