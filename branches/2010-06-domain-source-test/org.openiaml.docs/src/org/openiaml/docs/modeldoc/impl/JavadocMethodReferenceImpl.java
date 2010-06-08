/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Javadoc Method Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.JavadocMethodReferenceImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavadocMethodReferenceImpl extends JavadocFragmentImpl implements JavadocMethodReference {
	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected JavaMethod reference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavadocMethodReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.JAVADOC_METHOD_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaMethod getReference() {
		if (reference != null && reference.eIsProxy()) {
			InternalEObject oldReference = (InternalEObject)reference;
			reference = (JavaMethod)eResolveProxy(oldReference);
			if (reference != oldReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE, oldReference, reference));
			}
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaMethod basicGetReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(JavaMethod newReference) {
		JavaMethod oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE:
				if (resolve) return getReference();
				return basicGetReference();
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
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE:
				setReference((JavaMethod)newValue);
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
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE:
				setReference((JavaMethod)null);
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
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE__REFERENCE:
				return reference != null;
		}
		return super.eIsSet(featureID);
	}

} //JavadocMethodReferenceImpl
