/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ConstraintWire#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintWire()
 * @model annotation="http://openiaml.org/comment added='0.4'"
 * @generated
 */
public interface ConstraintWire extends SingleWire {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.openiaml.model.model.wires.ConstraintTypes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.wires.ConstraintTypes
	 * @see #setType(ConstraintTypes)
	 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintWire_Type()
	 * @model required="true"
	 * @generated
	 */
	ConstraintTypes getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.ConstraintWire#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.wires.ConstraintTypes
	 * @see #getType()
	 * @generated
	 */
	void setType(ConstraintTypes value);

} // ConstraintWire
