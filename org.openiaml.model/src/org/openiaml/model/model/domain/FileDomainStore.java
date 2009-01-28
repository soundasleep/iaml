/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import org.openiaml.model.FileReference;
import org.openiaml.model.model.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Domain Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.FileDomainStore#getTempTypeString <em>Temp Type String</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.FileDomainStore#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.domain.DomainPackage#getFileDomainStore()
 * @model
 * @generated
 */
public interface FileDomainStore extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Temp Type String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Temp Type String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temp Type String</em>' attribute.
	 * @see #setTempTypeString(String)
	 * @see org.openiaml.model.model.domain.DomainPackage#getFileDomainStore_TempTypeString()
	 * @model
	 * @generated
	 */
	String getTempTypeString();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.domain.FileDomainStore#getTempTypeString <em>Temp Type String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Type String</em>' attribute.
	 * @see #getTempTypeString()
	 * @generated
	 */
	void setTempTypeString(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(FileReference)
	 * @see org.openiaml.model.model.domain.DomainPackage#getFileDomainStore_File()
	 * @model dataType="org.openiaml.model.model.domain.FileReference" required="true"
	 * @generated
	 */
	FileReference getFile();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.domain.FileDomainStore#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(FileReference value);

} // FileDomainStore
