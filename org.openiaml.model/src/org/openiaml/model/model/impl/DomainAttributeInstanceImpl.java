/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Attribute Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#getOutExtendsEdges <em>Out Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#getInExtendsEdges <em>In Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#isAutosave <em>Autosave</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainAttributeInstanceImpl extends ApplicationElementImpl implements DomainAttributeInstance {
	/**
	 * The cached value of the '{@link #getOutExtendsEdges() <em>Out Extends Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutExtendsEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendsEdge> outExtendsEdges;
	/**
	 * The cached value of the '{@link #getInExtendsEdges() <em>In Extends Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInExtendsEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendsEdge> inExtendsEdges;
	/**
	 * The default value of the '{@link #isAutosave() <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutosave()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTOSAVE_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAutosave() <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutosave()
	 * @generated
	 * @ordered
	 */
	protected boolean autosave = AUTOSAVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainAttributeInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOMAIN_ATTRIBUTE_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getOutExtendsEdges() {
		if (outExtendsEdges == null) {
			outExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__FROM);
		}
		return outExtendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getInExtendsEdges() {
		if (inExtendsEdges == null) {
			inExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__TO);
		}
		return inExtendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutosave() {
		return autosave;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutosave(boolean newAutosave) {
		boolean oldAutosave = autosave;
		autosave = newAutosave;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE, oldAutosave, autosave));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutExtendsEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInExtendsEdges()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				return ((InternalEList<?>)getOutExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				return ((InternalEList<?>)getInExtendsEdges()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				return getOutExtendsEdges();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				return getInExtendsEdges();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				return isAutosave();
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				getOutExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				getInExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				setAutosave((Boolean)newValue);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				setAutosave(AUTOSAVE_EDEFAULT);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES:
				return outExtendsEdges != null && !outExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES:
				return inExtendsEdges != null && !inExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				return autosave != AUTOSAVE_EDEFAULT;
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
		if (baseClass == ExtendsEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES;
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
		if (baseClass == ExtendsEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES: return ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES: return ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__IN_EXTENDS_EDGES;
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
		result.append(" (autosave: ");
		result.append(autosave);
		result.append(')');
		return result.toString();
	}

} //DomainAttributeInstanceImpl
