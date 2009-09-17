/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inference Semantic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.InferenceSemantic#getCategory <em>Category</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.InferenceSemantic#getDescription <em>Description</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getInferenceSemantic()
 * @model
 * @generated
 */
public interface InferenceSemantic extends Semantic {
	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see #setCategory(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getInferenceSemantic_Category()
	 * @model
	 * @generated
	 */
	String getCategory();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' reference.
	 * @see #setDescription(JavadocTagElement)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getInferenceSemantic_Description()
	 * @model
	 * @generated
	 */
	JavadocTagElement getDescription();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getDescription <em>Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(JavadocTagElement value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getInferenceSemantics <em>Inference Semantics</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' container reference.
	 * @see #setContainingClass(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getInferenceSemantic_ContainingClass()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getInferenceSemantics
	 * @model opposite="inferenceSemantics" transient="false"
	 * @generated
	 */
	EMFClass getContainingClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass <em>Containing Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' container reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EMFClass value);

} // InferenceSemantic
