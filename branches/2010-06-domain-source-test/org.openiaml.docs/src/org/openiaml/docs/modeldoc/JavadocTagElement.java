/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Javadoc Tag Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.JavadocTagElement#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.JavadocTagElement#getFragments <em>Fragments</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent <em>Java Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocTagElement()
 * @model
 * @generated
 */
public interface JavadocTagElement extends JavadocFragment {
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocTagElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.JavadocFragment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocTagElement_Fragments()
	 * @model containment="true"
	 * @generated
	 */
	EList<JavadocFragment> getFragments();

	/**
	 * Returns the value of the '<em><b>Java Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.JavaElement#getJavadocs <em>Javadocs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Parent</em>' container reference.
	 * @see #setJavaParent(JavaElement)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocTagElement_JavaParent()
	 * @see org.openiaml.docs.modeldoc.JavaElement#getJavadocs
	 * @model opposite="javadocs" transient="false"
	 * @generated
	 */
	JavaElement getJavaParent();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent <em>Java Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Parent</em>' container reference.
	 * @see #getJavaParent()
	 * @generated
	 */
	void setJavaParent(JavaElement value);

} // JavadocTagElement
