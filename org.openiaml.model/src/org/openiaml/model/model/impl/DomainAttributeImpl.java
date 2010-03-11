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
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.Editable;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#getOutParameterEdges <em>Out Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#getOutExtendsEdges <em>Out Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#getInExtendsEdges <em>In Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#getOnEdit <em>On Edit</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#isPrimaryKey <em>Primary Key</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainAttributeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainAttributeImpl extends ApplicationElementImpl implements DomainAttribute {
	/**
	 * The cached value of the '{@link #getOutParameterEdges() <em>Out Parameter Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameterEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterEdge> outParameterEdges;
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
	 * The cached value of the '{@link #getOnEdit() <em>On Edit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnEdit()
	 * @generated
	 * @ordered
	 */
	protected EventTrigger onEdit;
	/**
	 * The default value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKey()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIMARY_KEY_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKey()
	 * @generated
	 * @ordered
	 */
	protected boolean primaryKey = PRIMARY_KEY_EDEFAULT;

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
	protected DomainAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOMAIN_ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getOutParameterEdges() {
		if (outParameterEdges == null) {
			outParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__FROM);
		}
		return outParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getOutExtendsEdges() {
		if (outExtendsEdges == null) {
			outExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__FROM);
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
			inExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__TO);
		}
		return inExtendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventTrigger getOnEdit() {
		return onEdit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnEdit(EventTrigger newOnEdit, NotificationChain msgs) {
		EventTrigger oldOnEdit = onEdit;
		onEdit = newOnEdit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT, oldOnEdit, newOnEdit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnEdit(EventTrigger newOnEdit) {
		if (newOnEdit != onEdit) {
			NotificationChain msgs = null;
			if (onEdit != null)
				msgs = ((InternalEObject)onEdit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT, null, msgs);
			if (newOnEdit != null)
				msgs = ((InternalEObject)newOnEdit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT, null, msgs);
			msgs = basicSetOnEdit(newOnEdit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT, newOnEdit, newOnEdit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryKey(boolean newPrimaryKey) {
		boolean oldPrimaryKey = primaryKey;
		primaryKey = newPrimaryKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE__PRIMARY_KEY, oldPrimaryKey, primaryKey));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.DOMAIN_ATTRIBUTE__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_ATTRIBUTE__TYPE, oldType, type));
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutParameterEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutExtendsEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				return ((InternalEList<?>)getOutParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				return ((InternalEList<?>)getOutExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
				return ((InternalEList<?>)getInExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT:
				return basicSetOnEdit(null, msgs);
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				return getOutParameterEdges();
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				return getOutExtendsEdges();
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
				return getInExtendsEdges();
			case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT:
				return getOnEdit();
			case ModelPackage.DOMAIN_ATTRIBUTE__PRIMARY_KEY:
				return isPrimaryKey();
			case ModelPackage.DOMAIN_ATTRIBUTE__TYPE:
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				getOutParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				getOutExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				getInExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT:
				setOnEdit((EventTrigger)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__PRIMARY_KEY:
				setPrimaryKey((Boolean)newValue);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__TYPE:
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT:
				setOnEdit((EventTrigger)null);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__PRIMARY_KEY:
				setPrimaryKey(PRIMARY_KEY_EDEFAULT);
				return;
			case ModelPackage.DOMAIN_ATTRIBUTE__TYPE:
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
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES:
				return outParameterEdges != null && !outParameterEdges.isEmpty();
			case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES:
				return outExtendsEdges != null && !outExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES:
				return inExtendsEdges != null && !inExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT:
				return onEdit != null;
			case ModelPackage.DOMAIN_ATTRIBUTE__PRIMARY_KEY:
				return primaryKey != PRIMARY_KEY_EDEFAULT;
			case ModelPackage.DOMAIN_ATTRIBUTE__TYPE:
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
		if (baseClass == ParameterEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT: return ModelPackage.EDITABLE__ON_EDIT;
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
		if (baseClass == ParameterEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES: return ModelPackage.DOMAIN_ATTRIBUTE__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES: return ModelPackage.DOMAIN_ATTRIBUTE__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES: return ModelPackage.DOMAIN_ATTRIBUTE__IN_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (baseFeatureID) {
				case ModelPackage.EDITABLE__ON_EDIT: return ModelPackage.DOMAIN_ATTRIBUTE__ON_EDIT;
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
		result.append(" (primaryKey: ");
		result.append(primaryKey);
		result.append(')');
		return result.toString();
	}

} //DomainAttributeImpl
