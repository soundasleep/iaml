/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openiaml.model.FileReference;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.domain.AbstractDomainAttribute;
import org.openiaml.model.model.domain.AbstractDomainObject;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.FileDomainAttribute;
import org.openiaml.model.model.domain.FileDomainObject;
import org.openiaml.model.model.domain.FileDomainStore;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Domain Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.impl.FileDomainStoreImpl#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileDomainStoreImpl extends AbstractDomainStoreImpl implements FileDomainStore {
	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final FileReference FILE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected FileReference file = FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileDomainStoreImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DomainPackage.Literals.FILE_DOMAIN_STORE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileReference getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(FileReference newFile) {
		FileReference oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.FILE_DOMAIN_STORE__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DomainPackage.FILE_DOMAIN_STORE__FILE:
				return getFile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DomainPackage.FILE_DOMAIN_STORE__FILE:
				setFile((FileReference)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DomainPackage.FILE_DOMAIN_STORE__FILE:
				setFile(FILE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DomainPackage.FILE_DOMAIN_STORE__FILE:
				return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (file: ");
		result.append(file);
		result.append(')');
		return result.toString();
	}

	/**
	 * Does the real work of refreshing the mapping between elements
	 * to the source database, based on changes in the FileReference, if needed.
	 * 
	 * This file domain store is based on a properties file, which
	 * will be represented in the model like so:
	 * 
	 * <pre>
	 * FileDomainStore [foo.properties]
	 * - FileDomainObject [properties]
	 *   - FileDomainAttribute [name='prop1' value='value1']
	 *   - FileDomainAttribute [name='prop2' value='value2']
	 *   - ...
	 * </pre>
	 * 
	 * That is, there is only one DomainObject but many Attributes.
	 * 
	 * @generated NOT 
	 * @see org.openiaml.model.model.domain.FileDomainStore#refreshMappings(org.openiaml.model.inference.EcoreCreateElementsHelper)
	 */
	@Override
	public boolean refreshMappings(EcoreCreateElementsHelper handler)
			throws InferenceException {
		
		try {
			// we need to get the relative path
			URI relativePath = this.eResource().getURI();
			
			if (getFile() == null)
				throw new InferenceException("No file to map to");
			File f = getFile().toFile(relativePath);
			if (!f.exists())
				throw new InferenceException("File does not exist: " + f);
			if (!f.canRead())
				throw new InferenceException("Cannot read file: " + f);
			Properties props = new Properties();
			props.load(new FileInputStream(f));
			
			// create and load the domain object if necessary
			refreshFileDomainObject(handler, props);
			
		} catch (FileNotFoundException e) {
			throw new InferenceException(e);
		} catch (IOException e) {
			throw new InferenceException(e);
		}
		
		return true;
		
	}

	/**
	 * Create or update the FileDomainObject for this file; cycle over
	 * properties and insert FileDomainAttributes where necessary.
	 * 
	 * @param handler
	 * @param props
	 * @return
	 * @throws InferenceException
	 * @generated NOT 
	 */
	protected FileDomainObject refreshFileDomainObject(EcoreCreateElementsHelper handler, Properties props) throws InferenceException {
		FileDomainObject fdo = null;
		
		// existing one
		for (AbstractDomainObject ado : this.getChildren()) {
			if (ado instanceof FileDomainObject && 
					"properties".equals(((FileDomainObject) ado).getName())) {
				fdo = (FileDomainObject) ado;
				break;
			}
		}
		
		// create a new one
		if (fdo == null) {
			fdo = handler.generatedFileDomainObject(this, this);
		}

		// refresh it (the name)
		handler.setValue(fdo, ModelPackage.eINSTANCE.getNamedElement_Name(), "properties");

		// cycle over properties
		for (String key : props.stringPropertyNames()) {
			refreshFileDomainAttribute(fdo, handler, key);
		}
		
		return fdo;
	}

	/**
	 * Create or update the FileDomainAttribute for this attribute.
	 * 
	 * @param fdo
	 * @param handler
	 * @param key
	 * @return 
	 * @generated NOT 
	 * @throws InferenceException 
	 */
	protected FileDomainAttribute refreshFileDomainAttribute(FileDomainObject fdo,
			EcoreCreateElementsHelper handler, String key) throws InferenceException {
		
		FileDomainAttribute fda = null;
		
		// existing one
		for (AbstractDomainAttribute ado : fdo.getAttributes()) {
			if (ado instanceof AbstractDomainAttribute && "properties".equals(((AbstractDomainAttribute) ado).getName())) {
				fda = (FileDomainAttribute) ado;
				break;
			}
		}
		
		// create a new one
		if (fda == null) {
			fda = handler.generatedFileDomainAttribute(fdo, fdo);
		}
		
		// refresh it (the name)
		handler.setValue(fda, ModelPackage.eINSTANCE.getNamedElement_Name(), key);
		
		return fda;
		
	}

} //FileDomainStoreImpl
