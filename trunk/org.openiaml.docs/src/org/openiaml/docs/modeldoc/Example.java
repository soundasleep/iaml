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
 * A representation of the model object '<em><b>Example</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.Example#getExampleModel <em>Example Model</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.Example#getExampleTest <em>Example Test</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.Example#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getExample()
 * @model
 * @generated
 */
public interface Example extends EObject {
	/**
	 * Returns the value of the '<em><b>Example Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example Model</em>' reference.
	 * @see #setExampleModel(ModelReference)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getExample_ExampleModel()
	 * @model
	 * @generated
	 */
	ModelReference getExampleModel();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Example#getExampleModel <em>Example Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example Model</em>' reference.
	 * @see #getExampleModel()
	 * @generated
	 */
	void setExampleModel(ModelReference value);

	/**
	 * Returns the value of the '<em><b>Example Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example Test</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example Test</em>' reference.
	 * @see #setExampleTest(JavaClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getExample_ExampleTest()
	 * @model
	 * @generated
	 */
	JavaClass getExampleTest();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Example#getExampleTest <em>Example Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example Test</em>' reference.
	 * @see #getExampleTest()
	 * @generated
	 */
	void setExampleTest(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getExamples <em>Examples</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' container reference.
	 * @see #setContainingClass(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getExample_ContainingClass()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getExamples
	 * @model opposite="examples" transient="false"
	 * @generated
	 */
	EMFClass getContainingClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Example#getContainingClass <em>Containing Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' container reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EMFClass value);

} // Example
