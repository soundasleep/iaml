/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.Constraint#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.Constraint#getType <em>Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.Constraint#getMessage <em>Message</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.Constraint#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends Semantic {
	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint</em>' attribute.
	 * @see #setConstraint(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getConstraint_Constraint()
	 * @model
	 * @generated
	 */
	String getConstraint();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Constraint#getConstraint <em>Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint</em>' attribute.
	 * @see #getConstraint()
	 * @generated
	 */
	void setConstraint(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.openiaml.docs.modeldoc.ConstraintType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.docs.modeldoc.ConstraintType
	 * @see #setType(ConstraintType)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getConstraint_Type()
	 * @model
	 * @generated
	 */
	ConstraintType getType();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Constraint#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.docs.modeldoc.ConstraintType
	 * @see #getType()
	 * @generated
	 */
	void setType(ConstraintType value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getConstraint_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Constraint#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' container reference.
	 * @see #setContainingClass(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getConstraint_ContainingClass()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getConstraints
	 * @model opposite="constraints" transient="false"
	 * @generated
	 */
	EMFClass getContainingClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Constraint#getContainingClass <em>Containing Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' container reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EMFClass value);

} // Constraint
