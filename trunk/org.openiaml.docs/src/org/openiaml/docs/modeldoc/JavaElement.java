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
 * A representation of the model object '<em><b>Java Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.JavaElement#getJavadocs <em>Javadocs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavaElement()
 * @model abstract="true"
 * @generated
 */
public interface JavaElement extends Reference {
	/**
	 * Returns the value of the '<em><b>Javadocs</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.JavadocTagElement}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent <em>Java Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Javadocs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadocs</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavaElement_Javadocs()
	 * @see org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent
	 * @model opposite="javaParent" containment="true"
	 * @generated
	 */
	EList<JavadocTagElement> getJavadocs();

} // JavaElement
