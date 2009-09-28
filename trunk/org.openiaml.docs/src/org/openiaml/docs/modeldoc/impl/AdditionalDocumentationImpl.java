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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.openiaml.docs.modeldoc.AdditionalDocumentation;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.Reference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Additional Documentation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl#getDescriptionHtml <em>Description Html</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdditionalDocumentationImpl extends EObjectImpl implements AdditionalDocumentation {
	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected Reference reference;

	/**
	 * The default value of the '{@link #getDescriptionHtml() <em>Description Html</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptionHtml()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_HTML_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescriptionHtml() <em>Description Html</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptionHtml()
	 * @generated
	 * @ordered
	 */
	protected String descriptionHtml = DESCRIPTION_HTML_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdditionalDocumentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.ADDITIONAL_DOCUMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference getReference() {
		if (reference != null && reference.eIsProxy()) {
			InternalEObject oldReference = (InternalEObject)reference;
			reference = (Reference)eResolveProxy(oldReference);
			if (reference != oldReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE, oldReference, reference));
			}
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference basicGetReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(Reference newReference) {
		Reference oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptionHtml(String newDescriptionHtml) {
		String oldDescriptionHtml = descriptionHtml;
		descriptionHtml = newDescriptionHtml;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.ADDITIONAL_DOCUMENTATION__DESCRIPTION_HTML, oldDescriptionHtml, descriptionHtml));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE:
				if (resolve) return getReference();
				return basicGetReference();
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__DESCRIPTION_HTML:
				return getDescriptionHtml();
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
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE:
				setReference((Reference)newValue);
				return;
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__DESCRIPTION_HTML:
				setDescriptionHtml((String)newValue);
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
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE:
				setReference((Reference)null);
				return;
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__DESCRIPTION_HTML:
				setDescriptionHtml(DESCRIPTION_HTML_EDEFAULT);
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
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__REFERENCE:
				return reference != null;
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION__DESCRIPTION_HTML:
				return DESCRIPTION_HTML_EDEFAULT == null ? descriptionHtml != null : !DESCRIPTION_HTML_EDEFAULT.equals(descriptionHtml);
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
		result.append(" (descriptionHtml: ");
		result.append(descriptionHtml);
		result.append(')');
		return result.toString();
	}

} //AdditionalDocumentationImpl
