/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;

import org.openiaml.model.model.OperationParameter;
import org.openiaml.model.model.impl.WireEdgeImpl;

import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Wire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.CompositeWireImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeWireImpl extends WireEdgeImpl implements CompositeWire {
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
	protected EList<ApplicationElement> children;

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
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationParameter> parameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeWireImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WiresPackage.Literals.COMPOSITE_WIRE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WiresPackage.COMPOSITE_WIRE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<ApplicationElement>(ApplicationElement.class, this, WiresPackage.COMPOSITE_WIRE__CHILDREN);
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
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, WiresPackage.COMPOSITE_WIRE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, WiresPackage.COMPOSITE_WIRE__OPERATIONS);
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
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS);
		}
		return eventTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<OperationParameter>(OperationParameter.class, this, WiresPackage.COMPOSITE_WIRE__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WiresPackage.COMPOSITE_WIRE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case WiresPackage.COMPOSITE_WIRE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case WiresPackage.COMPOSITE_WIRE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case WiresPackage.COMPOSITE_WIRE__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case WiresPackage.COMPOSITE_WIRE__NAME:
				return getName();
			case WiresPackage.COMPOSITE_WIRE__CHILDREN:
				return getChildren();
			case WiresPackage.COMPOSITE_WIRE__PROPERTIES:
				return getProperties();
			case WiresPackage.COMPOSITE_WIRE__OPERATIONS:
				return getOperations();
			case WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS:
				return getEventTriggers();
			case WiresPackage.COMPOSITE_WIRE__PARAMETERS:
				return getParameters();
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
			case WiresPackage.COMPOSITE_WIRE__NAME:
				setName((String)newValue);
				return;
			case WiresPackage.COMPOSITE_WIRE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ApplicationElement>)newValue);
				return;
			case WiresPackage.COMPOSITE_WIRE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case WiresPackage.COMPOSITE_WIRE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case WiresPackage.COMPOSITE_WIRE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends OperationParameter>)newValue);
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
			case WiresPackage.COMPOSITE_WIRE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WiresPackage.COMPOSITE_WIRE__CHILDREN:
				getChildren().clear();
				return;
			case WiresPackage.COMPOSITE_WIRE__PROPERTIES:
				getProperties().clear();
				return;
			case WiresPackage.COMPOSITE_WIRE__OPERATIONS:
				getOperations().clear();
				return;
			case WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case WiresPackage.COMPOSITE_WIRE__PARAMETERS:
				getParameters().clear();
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
			case WiresPackage.COMPOSITE_WIRE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WiresPackage.COMPOSITE_WIRE__CHILDREN:
				return children != null && !children.isEmpty();
			case WiresPackage.COMPOSITE_WIRE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case WiresPackage.COMPOSITE_WIRE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case WiresPackage.COMPOSITE_WIRE__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case WiresPackage.COMPOSITE_WIRE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
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
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case WiresPackage.COMPOSITE_WIRE__NAME: return ModelPackage.NAMED_ELEMENT__NAME;
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
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.NAMED_ELEMENT__NAME: return WiresPackage.COMPOSITE_WIRE__NAME;
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

} //CompositeWireImpl
