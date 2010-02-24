/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.ExtendedProperties;
import org.openiaml.model.FileReference;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.domain.DomainStoreTypes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Stores many instances of {@model DomainObject DomainObjects} in some storage device.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainStore#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainStore#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainStore#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainStore#getFile <em>File</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainStore#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainStore()
 * @model annotation="http://openiaml.org/comment editor='org.openiaml.model.diagram.domain_store' changed='0.2 to extend the abstract counterpart\r\n0.3 to remove the abstract extension, and added \"type\" attribute\r\n0.4.2 removed \'views\' reference'"
 * @generated
 */
public interface DomainStore extends ContainsOperations, ContainsEventTriggers, NamedElement, ContainsWires, ContainsConditions, GeneratesElements, CanBeSynced {

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainStore_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<DomainObject> getChildren();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainStore_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getProperties();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainStore_Attributes()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.3' reason='why can a data store only contain tables? why can\'t they also contain single values?'"
	 * @generated
	 */
	EList<DomainAttribute> getAttributes();

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
	 * @see org.openiaml.model.model.ModelPackage#getDomainStore_File()
	 * @model dataType="org.openiaml.model.model.domain.FileReference" required="true"
	 *        annotation="http://openiaml.org/comment added='0.3' comment='originally from FileDomainStore'"
	 * @generated
	 */
	FileReference getFile();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainStore#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(FileReference value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.openiaml.model.model.domain.DomainStoreTypes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.domain.DomainStoreTypes
	 * @see #setType(DomainStoreTypes)
	 * @see org.openiaml.model.model.ModelPackage#getDomainStore_Type()
	 * @model required="true"
	 *        annotation="http://openiaml.org/comment added='0.3'"
	 * @generated
	 */
	DomainStoreTypes getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainStore#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.openiaml.model.model.domain.DomainStoreTypes
	 * @see #getType()
	 * @generated
	 */
	void setType(DomainStoreTypes value);

	/**
	 * Get the properties file referenced by the FileReference.
	 * If the file does not exist, return null.
	 * 
	 * @see #getFile()
	 * @return
	 * @throws InferenceException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @generated NOT
	 */
	ExtendedProperties getPropertiesFile() throws InferenceException,
			FileNotFoundException, IOException;
	
} // DomainStore
