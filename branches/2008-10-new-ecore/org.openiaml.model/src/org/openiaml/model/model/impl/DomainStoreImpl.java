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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainStoreImpl#getDomainStoreWires <em>Domain Store Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainStoreImpl extends EObjectImpl implements DomainStore {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> operations;

	/**
	 * The cached value of the '{@link #getEventTriggers() <em>Event Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<EventTrigger> eventTriggers;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainObject> children;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElementProperty> properties;

	/**
	 * The cached value of the '{@link #getDomainStoreWires() <em>Domain Store Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainStoreWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> domainStoreWires;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainStoreImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOMAIN_STORE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.DOMAIN_STORE__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventTrigger> getEventTriggers() {
		if (eventTriggers == null) {
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS);
		}
		return eventTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_STORE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainObject> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<DomainObject>(DomainObject.class, this, ModelPackage.DOMAIN_STORE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, ModelPackage.DOMAIN_STORE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getDomainStoreWires() {
		if (domainStoreWires == null) {
			domainStoreWires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES);
		}
		return domainStoreWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOMAIN_STORE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_STORE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_STORE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES:
				return ((InternalEList<?>)getDomainStoreWires()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_STORE__OPERATIONS:
				return getOperations();
			case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS:
				return getEventTriggers();
			case ModelPackage.DOMAIN_STORE__NAME:
				return getName();
			case ModelPackage.DOMAIN_STORE__CHILDREN:
				return getChildren();
			case ModelPackage.DOMAIN_STORE__PROPERTIES:
				return getProperties();
			case ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES:
				return getDomainStoreWires();
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
			case ModelPackage.DOMAIN_STORE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case ModelPackage.DOMAIN_STORE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.DOMAIN_STORE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends DomainObject>)newValue);
				return;
			case ModelPackage.DOMAIN_STORE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES:
				getDomainStoreWires().clear();
				getDomainStoreWires().addAll((Collection<? extends WireEdge>)newValue);
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
			case ModelPackage.DOMAIN_STORE__OPERATIONS:
				getOperations().clear();
				return;
			case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case ModelPackage.DOMAIN_STORE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.DOMAIN_STORE__CHILDREN:
				getChildren().clear();
				return;
			case ModelPackage.DOMAIN_STORE__PROPERTIES:
				getProperties().clear();
				return;
			case ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES:
				getDomainStoreWires().clear();
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
			case ModelPackage.DOMAIN_STORE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case ModelPackage.DOMAIN_STORE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.DOMAIN_STORE__CHILDREN:
				return children != null && !children.isEmpty();
			case ModelPackage.DOMAIN_STORE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ModelPackage.DOMAIN_STORE__DOMAIN_STORE_WIRES:
				return domainStoreWires != null && !domainStoreWires.isEmpty();
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
		if (baseClass == ContainsEventTriggers.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS: return ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_STORE__NAME: return ModelPackage.NAMED_ELEMENT__NAME;
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
		if (baseClass == ContainsEventTriggers.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS: return ModelPackage.DOMAIN_STORE__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.NAMED_ELEMENT__NAME: return ModelPackage.DOMAIN_STORE__NAME;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //DomainStoreImpl
