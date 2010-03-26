/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMF Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getType <em>Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getContainingType <em>Containing Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFReference#getOpposite <em>Opposite</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference()
 * @model
 * @generated
 */
public interface EMFReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_LowerBound()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_UpperBound()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containment</em>' attribute.
	 * @see #setContainment(boolean)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_Containment()
	 * @model
	 * @generated
	 */
	boolean isContainment();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#isContainment <em>Containment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containment</em>' attribute.
	 * @see #isContainment()
	 * @generated
	 */
	void setContainment(boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_Type()
	 * @model
	 * @generated
	 */
	EMFClass getType();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EMFClass value);

	/**
	 * Returns the value of the '<em><b>Containing Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Type</em>' container reference.
	 * @see #setContainingType(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_ContainingType()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getReferences
	 * @model opposite="references" transient="false"
	 * @generated
	 */
	EMFClass getContainingType();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getContainingType <em>Containing Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Type</em>' container reference.
	 * @see #getContainingType()
	 * @generated
	 */
	void setContainingType(EMFClass value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_TypeName()
	 * @model
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opposite</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opposite</em>' reference.
	 * @see #setOpposite(EMFReference)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFReference_Opposite()
	 * @model
	 * @generated
	 */
	EMFReference getOpposite();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFReference#getOpposite <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite</em>' reference.
	 * @see #getOpposite()
	 * @generated
	 */
	void setOpposite(EMFReference value);

} // EMFReference
