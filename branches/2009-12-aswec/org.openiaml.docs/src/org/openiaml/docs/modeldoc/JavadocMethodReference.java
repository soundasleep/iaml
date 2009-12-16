/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Javadoc Method Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.JavadocMethodReference#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocMethodReference()
 * @model
 * @generated
 */
public interface JavadocMethodReference extends JavadocFragment {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(JavaMethod)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getJavadocMethodReference_Reference()
	 * @model
	 * @generated
	 */
	JavaMethod getReference();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.JavadocMethodReference#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(JavaMethod value);

} // JavadocMethodReference
