/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import org.openiaml.model.FileReference;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.InferenceException;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Domain Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.FileDomainStore#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.domain.DomainPackage#getFileDomainStore()
 * @model
 * @generated
 */
public interface FileDomainStore extends AbstractDomainStore {
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

	/**
	 * Regenerate elements, based on changes in the FileReference, if needed.
	 * 
	 * @generated NOT
	 * @throws InferenceException if something bad happens
	 * @return true if the model has changed
	 */
	boolean refreshMappings(EcoreCreateElementsHelper handler) throws InferenceException;

} // FileDomainStore
