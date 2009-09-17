/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelExtension#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelExtension#getValue <em>Value</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelExtension#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelExtension()
 * @model
 * @generated
 */
public interface ModelExtension extends Semantic {
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelExtension_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.ModelExtension#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelExtension_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.ModelExtension#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' container reference.
	 * @see #setContainingClass(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelExtension_ContainingClass()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getExtensions
	 * @model opposite="extensions" transient="false"
	 * @generated
	 */
	EMFClass getContainingClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.ModelExtension#getContainingClass <em>Containing Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' container reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EMFClass value);

} // ModelExtension
