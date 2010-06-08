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
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.Reference#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getReference()
 * @model abstract="true"
 * @generated
 */
public interface Reference extends EObject {

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.ModelDocumentation#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(ModelDocumentation)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getReference_Parent()
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation#getReferences
	 * @model opposite="references" transient="false"
	 * @generated
	 */
	ModelDocumentation getParent();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.Reference#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ModelDocumentation value);
} // Reference
