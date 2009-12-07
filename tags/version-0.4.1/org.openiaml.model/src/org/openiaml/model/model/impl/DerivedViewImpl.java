/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DerivedViewImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DerivedViewImpl#getStrQuery <em>Str Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DerivedViewImpl extends ApplicationElementImpl implements DerivedView {
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainAttribute> attributes;

	/**
	 * The default value of the '{@link #getStrQuery() <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrQuery()
	 * @generated
	 * @ordered
	 */
	protected static final String STR_QUERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStrQuery() <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrQuery()
	 * @generated
	 * @ordered
	 */
	protected String strQuery = STR_QUERY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivedViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DERIVED_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectResolvingEList<DomainAttribute>(DomainAttribute.class, this, ModelPackage.DERIVED_VIEW__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStrQuery() {
		return strQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrQuery(String newStrQuery) {
		String oldStrQuery = strQuery;
		strQuery = newStrQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DERIVED_VIEW__STR_QUERY, oldStrQuery, strQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.DERIVED_VIEW__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.DERIVED_VIEW__STR_QUERY:
				return getStrQuery();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.DERIVED_VIEW__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends DomainAttribute>)newValue);
				return;
			case ModelPackage.DERIVED_VIEW__STR_QUERY:
				setStrQuery((String)newValue);
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
			case ModelPackage.DERIVED_VIEW__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.DERIVED_VIEW__STR_QUERY:
				setStrQuery(STR_QUERY_EDEFAULT);
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
			case ModelPackage.DERIVED_VIEW__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ModelPackage.DERIVED_VIEW__STR_QUERY:
				return STR_QUERY_EDEFAULT == null ? strQuery != null : !STR_QUERY_EDEFAULT.equals(strQuery);
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
		result.append(" (strQuery: ");
		result.append(strQuery);
		result.append(')');
		return result.toString();
	}

} //DerivedViewImpl
