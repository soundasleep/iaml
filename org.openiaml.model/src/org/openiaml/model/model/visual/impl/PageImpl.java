/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.AbstractScope;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;
import org.openiaml.model.model.impl.VisibleThingImpl;
import org.openiaml.model.model.scopes.Scope;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.visual.impl.PageImpl#getEntryGate <em>Entry Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.impl.PageImpl#getExitGate <em>Exit Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.impl.PageImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.impl.PageImpl#getScopes <em>Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageImpl extends VisibleThingImpl implements Page {
	/**
	 * The cached value of the '{@link #getEntryGate() <em>Entry Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryGate()
	 * @generated
	 * @ordered
	 */
	protected EntryGate entryGate;

	/**
	 * The cached value of the '{@link #getExitGate() <em>Exit Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExitGate()
	 * @generated
	 * @ordered
	 */
	protected ExitGate exitGate;

	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScopes() <em>Scopes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopes()
	 * @generated
	 * @ordered
	 */
	protected EList<Scope> scopes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VisualPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryGate getEntryGate() {
		return entryGate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEntryGate(EntryGate newEntryGate, NotificationChain msgs) {
		EntryGate oldEntryGate = entryGate;
		entryGate = newEntryGate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VisualPackage.PAGE__ENTRY_GATE, oldEntryGate, newEntryGate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntryGate(EntryGate newEntryGate) {
		if (newEntryGate != entryGate) {
			NotificationChain msgs = null;
			if (entryGate != null)
				msgs = ((InternalEObject)entryGate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VisualPackage.PAGE__ENTRY_GATE, null, msgs);
			if (newEntryGate != null)
				msgs = ((InternalEObject)newEntryGate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VisualPackage.PAGE__ENTRY_GATE, null, msgs);
			msgs = basicSetEntryGate(newEntryGate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VisualPackage.PAGE__ENTRY_GATE, newEntryGate, newEntryGate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExitGate getExitGate() {
		return exitGate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExitGate(ExitGate newExitGate, NotificationChain msgs) {
		ExitGate oldExitGate = exitGate;
		exitGate = newExitGate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VisualPackage.PAGE__EXIT_GATE, oldExitGate, newExitGate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExitGate(ExitGate newExitGate) {
		if (newExitGate != exitGate) {
			NotificationChain msgs = null;
			if (exitGate != null)
				msgs = ((InternalEObject)exitGate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VisualPackage.PAGE__EXIT_GATE, null, msgs);
			if (newExitGate != null)
				msgs = ((InternalEObject)newExitGate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VisualPackage.PAGE__EXIT_GATE, null, msgs);
			msgs = basicSetExitGate(newExitGate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VisualPackage.PAGE__EXIT_GATE, newExitGate, newExitGate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VisualPackage.PAGE__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Scope> getScopes() {
		if (scopes == null) {
			scopes = new EObjectContainmentEList<Scope>(Scope.class, this, VisualPackage.PAGE__SCOPES);
		}
		return scopes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VisualPackage.PAGE__ENTRY_GATE:
				return basicSetEntryGate(null, msgs);
			case VisualPackage.PAGE__EXIT_GATE:
				return basicSetExitGate(null, msgs);
			case VisualPackage.PAGE__SCOPES:
				return ((InternalEList<?>)getScopes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VisualPackage.PAGE__ENTRY_GATE:
				return getEntryGate();
			case VisualPackage.PAGE__EXIT_GATE:
				return getExitGate();
			case VisualPackage.PAGE__URL:
				return getUrl();
			case VisualPackage.PAGE__SCOPES:
				return getScopes();
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
			case VisualPackage.PAGE__ENTRY_GATE:
				setEntryGate((EntryGate)newValue);
				return;
			case VisualPackage.PAGE__EXIT_GATE:
				setExitGate((ExitGate)newValue);
				return;
			case VisualPackage.PAGE__URL:
				setUrl((String)newValue);
				return;
			case VisualPackage.PAGE__SCOPES:
				getScopes().clear();
				getScopes().addAll((Collection<? extends Scope>)newValue);
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
			case VisualPackage.PAGE__ENTRY_GATE:
				setEntryGate((EntryGate)null);
				return;
			case VisualPackage.PAGE__EXIT_GATE:
				setExitGate((ExitGate)null);
				return;
			case VisualPackage.PAGE__URL:
				setUrl(URL_EDEFAULT);
				return;
			case VisualPackage.PAGE__SCOPES:
				getScopes().clear();
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
			case VisualPackage.PAGE__ENTRY_GATE:
				return entryGate != null;
			case VisualPackage.PAGE__EXIT_GATE:
				return exitGate != null;
			case VisualPackage.PAGE__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case VisualPackage.PAGE__SCOPES:
				return scopes != null && !scopes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractScope.class) {
			switch (derivedFeatureID) {
				case VisualPackage.PAGE__ENTRY_GATE: return ModelPackage.ABSTRACT_SCOPE__ENTRY_GATE;
				case VisualPackage.PAGE__EXIT_GATE: return ModelPackage.ABSTRACT_SCOPE__EXIT_GATE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractScope.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_SCOPE__ENTRY_GATE: return VisualPackage.PAGE__ENTRY_GATE;
				case ModelPackage.ABSTRACT_SCOPE__EXIT_GATE: return VisualPackage.PAGE__EXIT_GATE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (url: ");
		result.append(url);
		result.append(')');
		return result.toString();
	}

} //PageImpl
