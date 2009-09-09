/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.domain.DomainFactory
 * @model kind="package"
 *        annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface DomainPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "domain";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/model/domain";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iaml.domain";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DomainPackage eINSTANCE = org.openiaml.model.model.domain.impl.DomainPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.domain.DomainStoreTypes <em>Store Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.domain.DomainStoreTypes
	 * @see org.openiaml.model.model.domain.impl.DomainPackageImpl#getDomainStoreTypes()
	 * @generated
	 */
	int DOMAIN_STORE_TYPES = 0;

	/**
	 * The meta object id for the '<em>File Reference</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.FileReference
	 * @see org.openiaml.model.model.domain.impl.DomainPackageImpl#getFileReference()
	 * @generated
	 */
	int FILE_REFERENCE = 1;


	/**
	 * Returns the meta object for enum '{@link org.openiaml.model.model.domain.DomainStoreTypes <em>Store Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Store Types</em>'.
	 * @see org.openiaml.model.model.domain.DomainStoreTypes
	 * @generated
	 */
	EEnum getDomainStoreTypes();

	/**
	 * Returns the meta object for data type '{@link org.openiaml.model.FileReference <em>File Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>File Reference</em>'.
	 * @see org.openiaml.model.FileReference
	 * @model instanceClass="org.openiaml.model.FileReference"
	 * @generated
	 */
	EDataType getFileReference();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DomainFactory getDomainFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.domain.DomainStoreTypes <em>Store Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.domain.DomainStoreTypes
		 * @see org.openiaml.model.model.domain.impl.DomainPackageImpl#getDomainStoreTypes()
		 * @generated
		 */
		EEnum DOMAIN_STORE_TYPES = eINSTANCE.getDomainStoreTypes();

		/**
		 * The meta object literal for the '<em>File Reference</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.FileReference
		 * @see org.openiaml.model.model.domain.impl.DomainPackageImpl#getFileReference()
		 * @generated
		 */
		EDataType FILE_REFERENCE = eINSTANCE.getFileReference();

	}

} //DomainPackage
