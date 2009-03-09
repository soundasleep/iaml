/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.domain.AbstractDomainObject;
import org.openiaml.model.model.impl.VisibleThingImpl;

import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Frame</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.visual.impl.FrameImpl#getDomainObjects <em>Domain Objects</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.impl.FrameImpl#getDomainViews <em>Domain Views</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.impl.FrameImpl#getDomainInstances <em>Domain Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FrameImpl extends VisibleThingImpl implements Frame {
	/**
	 * The cached value of the '{@link #getDomainObjects() <em>Domain Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractDomainObject> domainObjects;
	/**
	 * The cached value of the '{@link #getDomainViews() <em>Domain Views</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainViews()
	 * @generated
	 * @ordered
	 */
	protected EList<DerivedView> domainViews;
	/**
	 * The cached value of the '{@link #getDomainInstances() <em>Domain Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainObjectInstance> domainInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FrameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VisualPackage.Literals.FRAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractDomainObject> getDomainObjects() {
		if (domainObjects == null) {
			domainObjects = new EObjectContainmentEList<AbstractDomainObject>(AbstractDomainObject.class, this, VisualPackage.FRAME__DOMAIN_OBJECTS);
		}
		return domainObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DerivedView> getDomainViews() {
		if (domainViews == null) {
			domainViews = new EObjectContainmentEList<DerivedView>(DerivedView.class, this, VisualPackage.FRAME__DOMAIN_VIEWS);
		}
		return domainViews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainObjectInstance> getDomainInstances() {
		if (domainInstances == null) {
			domainInstances = new EObjectContainmentEList<DomainObjectInstance>(DomainObjectInstance.class, this, VisualPackage.FRAME__DOMAIN_INSTANCES);
		}
		return domainInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VisualPackage.FRAME__DOMAIN_OBJECTS:
				return ((InternalEList<?>)getDomainObjects()).basicRemove(otherEnd, msgs);
			case VisualPackage.FRAME__DOMAIN_VIEWS:
				return ((InternalEList<?>)getDomainViews()).basicRemove(otherEnd, msgs);
			case VisualPackage.FRAME__DOMAIN_INSTANCES:
				return ((InternalEList<?>)getDomainInstances()).basicRemove(otherEnd, msgs);
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
			case VisualPackage.FRAME__DOMAIN_OBJECTS:
				return getDomainObjects();
			case VisualPackage.FRAME__DOMAIN_VIEWS:
				return getDomainViews();
			case VisualPackage.FRAME__DOMAIN_INSTANCES:
				return getDomainInstances();
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
			case VisualPackage.FRAME__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				getDomainObjects().addAll((Collection<? extends AbstractDomainObject>)newValue);
				return;
			case VisualPackage.FRAME__DOMAIN_VIEWS:
				getDomainViews().clear();
				getDomainViews().addAll((Collection<? extends DerivedView>)newValue);
				return;
			case VisualPackage.FRAME__DOMAIN_INSTANCES:
				getDomainInstances().clear();
				getDomainInstances().addAll((Collection<? extends DomainObjectInstance>)newValue);
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
			case VisualPackage.FRAME__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				return;
			case VisualPackage.FRAME__DOMAIN_VIEWS:
				getDomainViews().clear();
				return;
			case VisualPackage.FRAME__DOMAIN_INSTANCES:
				getDomainInstances().clear();
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
			case VisualPackage.FRAME__DOMAIN_OBJECTS:
				return domainObjects != null && !domainObjects.isEmpty();
			case VisualPackage.FRAME__DOMAIN_VIEWS:
				return domainViews != null && !domainViews.isEmpty();
			case VisualPackage.FRAME__DOMAIN_INSTANCES:
				return domainInstances != null && !domainInstances.isEmpty();
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
		if (baseClass == Scope.class) {
			switch (derivedFeatureID) {
				case VisualPackage.FRAME__DOMAIN_OBJECTS: return ModelPackage.SCOPE__DOMAIN_OBJECTS;
				case VisualPackage.FRAME__DOMAIN_VIEWS: return ModelPackage.SCOPE__DOMAIN_VIEWS;
				case VisualPackage.FRAME__DOMAIN_INSTANCES: return ModelPackage.SCOPE__DOMAIN_INSTANCES;
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
		if (baseClass == Scope.class) {
			switch (baseFeatureID) {
				case ModelPackage.SCOPE__DOMAIN_OBJECTS: return VisualPackage.FRAME__DOMAIN_OBJECTS;
				case ModelPackage.SCOPE__DOMAIN_VIEWS: return VisualPackage.FRAME__DOMAIN_VIEWS;
				case ModelPackage.SCOPE__DOMAIN_INSTANCES: return VisualPackage.FRAME__DOMAIN_INSTANCES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //FrameImpl
