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
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.openiaml.model.model.Changeable;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
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
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#getOnChange <em>On Change</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#isAutosave <em>Autosave</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeInstanceImpl#getType <em>Type</em>}</li>
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
	 * The cached value of the '{@link #getOnChange() <em>On Change</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnChange()
	 * @generated
	 * @ordered
	 */
	protected EventTrigger onChange;
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
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected XSDSimpleTypeDefinition type;

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
	public EventTrigger getOnChange() {
		return onChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnChange(EventTrigger newOnChange, NotificationChain msgs) {
		EventTrigger oldOnChange = onChange;
		onChange = newOnChange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE, oldOnChange, newOnChange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnChange(EventTrigger newOnChange) {
		if (newOnChange != onChange) {
			NotificationChain msgs = null;
			if (onChange != null)
				msgs = ((InternalEObject)onChange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE, null, msgs);
			if (newOnChange != null)
				msgs = ((InternalEObject)newOnChange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE, null, msgs);
			msgs = basicSetOnChange(newOnChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE, newOnChange, newOnChange));
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
	public XSDSimpleTypeDefinition getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (XSDSimpleTypeDefinition)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XSDSimpleTypeDefinition basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(XSDSimpleTypeDefinition newType) {
		XSDSimpleTypeDefinition oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE, oldType, type));
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE:
				return basicSetOnChange(null, msgs);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE:
				return getOnChange();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				return isAutosave();
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE:
				setOnChange((EventTrigger)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				setAutosave((Boolean)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE:
				setType((XSDSimpleTypeDefinition)newValue);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE:
				setOnChange((EventTrigger)null);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				setAutosave(AUTOSAVE_EDEFAULT);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE:
				setType((XSDSimpleTypeDefinition)null);
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
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE:
				return onChange != null;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE:
				return autosave != AUTOSAVE_EDEFAULT;
			case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__TYPE:
				return type != null;
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
		if (baseClass == Changeable.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE: return ModelPackage.CHANGEABLE__ON_CHANGE;
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
		if (baseClass == Changeable.class) {
			switch (baseFeatureID) {
				case ModelPackage.CHANGEABLE__ON_CHANGE: return ModelPackage.DOMAIN_ATTRIBUTE_INSTANCE__ON_CHANGE;
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
