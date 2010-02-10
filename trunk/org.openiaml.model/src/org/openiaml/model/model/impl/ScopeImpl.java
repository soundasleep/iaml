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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsScopes;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getGeneratedElements <em>Generated Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#isOverridden <em>Overridden</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getScopes <em>Scopes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getEntryGate <em>Entry Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getExitGate <em>Exit Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ScopeImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ScopeImpl extends EObjectImpl implements Scope {
	/**
	 * The cached value of the '{@link #getGeneratedElements() <em>Generated Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratedElement> generatedElements;

	/**
	 * The default value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected boolean overridden = OVERRIDDEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> wires;

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
	 * The cached value of the '{@link #getScopes() <em>Scopes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopes()
	 * @generated
	 * @ordered
	 */
	protected EList<Scope> scopes;

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
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElementProperty> properties;

	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticValue> values;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<QueryParameter> parameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SCOPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratedElement> getGeneratedElements() {
		if (generatedElements == null) {
			generatedElements = new EObjectWithInverseResolvingEList.ManyInverse<GeneratedElement>(GeneratedElement.class, this, ModelPackage.SCOPE__GENERATED_ELEMENTS, ModelPackage.GENERATED_ELEMENT__GENERATED_BY);
		}
		return generatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverridden() {
		return overridden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverridden(boolean newOverridden) {
		boolean oldOverridden = overridden;
		overridden = newOverridden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPE__OVERRIDDEN, oldOverridden, overridden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.SCOPE__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.SCOPE__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Scope> getScopes() {
		if (scopes == null) {
			scopes = new EObjectContainmentEList<Scope>(Scope.class, this, ModelPackage.SCOPE__SCOPES);
		}
		return scopes;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPE__ENTRY_GATE, oldEntryGate, newEntryGate);
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
				msgs = ((InternalEObject)entryGate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SCOPE__ENTRY_GATE, null, msgs);
			if (newEntryGate != null)
				msgs = ((InternalEObject)newEntryGate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SCOPE__ENTRY_GATE, null, msgs);
			msgs = basicSetEntryGate(newEntryGate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPE__ENTRY_GATE, newEntryGate, newEntryGate));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPE__EXIT_GATE, oldExitGate, newExitGate);
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
				msgs = ((InternalEObject)exitGate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SCOPE__EXIT_GATE, null, msgs);
			if (newExitGate != null)
				msgs = ((InternalEObject)newExitGate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SCOPE__EXIT_GATE, null, msgs);
			msgs = basicSetExitGate(newExitGate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPE__EXIT_GATE, newExitGate, newExitGate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, ModelPackage.SCOPE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticValue> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<StaticValue>(StaticValue.class, this, ModelPackage.SCOPE__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QueryParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<QueryParameter>(QueryParameter.class, this, ModelPackage.SCOPE__PARAMETERS);
		}
		return parameters;
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedElements()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				return ((InternalEList<?>)getGeneratedElements()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__SCOPES:
				return ((InternalEList<?>)getScopes()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__ENTRY_GATE:
				return basicSetEntryGate(null, msgs);
			case ModelPackage.SCOPE__EXIT_GATE:
				return basicSetExitGate(null, msgs);
			case ModelPackage.SCOPE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case ModelPackage.SCOPE__PARAMETERS:
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				return getGeneratedElements();
			case ModelPackage.SCOPE__OVERRIDDEN:
				return isOverridden();
			case ModelPackage.SCOPE__WIRES:
				return getWires();
			case ModelPackage.SCOPE__OPERATIONS:
				return getOperations();
			case ModelPackage.SCOPE__SCOPES:
				return getScopes();
			case ModelPackage.SCOPE__ENTRY_GATE:
				return getEntryGate();
			case ModelPackage.SCOPE__EXIT_GATE:
				return getExitGate();
			case ModelPackage.SCOPE__PROPERTIES:
				return getProperties();
			case ModelPackage.SCOPE__VALUES:
				return getValues();
			case ModelPackage.SCOPE__PARAMETERS:
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				getGeneratedElements().addAll((Collection<? extends GeneratedElement>)newValue);
				return;
			case ModelPackage.SCOPE__OVERRIDDEN:
				setOverridden((Boolean)newValue);
				return;
			case ModelPackage.SCOPE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.SCOPE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.SCOPE__SCOPES:
				getScopes().clear();
				getScopes().addAll((Collection<? extends Scope>)newValue);
				return;
			case ModelPackage.SCOPE__ENTRY_GATE:
				setEntryGate((EntryGate)newValue);
				return;
			case ModelPackage.SCOPE__EXIT_GATE:
				setExitGate((ExitGate)newValue);
				return;
			case ModelPackage.SCOPE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case ModelPackage.SCOPE__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends StaticValue>)newValue);
				return;
			case ModelPackage.SCOPE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends QueryParameter>)newValue);
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				return;
			case ModelPackage.SCOPE__OVERRIDDEN:
				setOverridden(OVERRIDDEN_EDEFAULT);
				return;
			case ModelPackage.SCOPE__WIRES:
				getWires().clear();
				return;
			case ModelPackage.SCOPE__OPERATIONS:
				getOperations().clear();
				return;
			case ModelPackage.SCOPE__SCOPES:
				getScopes().clear();
				return;
			case ModelPackage.SCOPE__ENTRY_GATE:
				setEntryGate((EntryGate)null);
				return;
			case ModelPackage.SCOPE__EXIT_GATE:
				setExitGate((ExitGate)null);
				return;
			case ModelPackage.SCOPE__PROPERTIES:
				getProperties().clear();
				return;
			case ModelPackage.SCOPE__VALUES:
				getValues().clear();
				return;
			case ModelPackage.SCOPE__PARAMETERS:
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
			case ModelPackage.SCOPE__GENERATED_ELEMENTS:
				return generatedElements != null && !generatedElements.isEmpty();
			case ModelPackage.SCOPE__OVERRIDDEN:
				return overridden != OVERRIDDEN_EDEFAULT;
			case ModelPackage.SCOPE__WIRES:
				return wires != null && !wires.isEmpty();
			case ModelPackage.SCOPE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ModelPackage.SCOPE__SCOPES:
				return scopes != null && !scopes.isEmpty();
			case ModelPackage.SCOPE__ENTRY_GATE:
				return entryGate != null;
			case ModelPackage.SCOPE__EXIT_GATE:
				return exitGate != null;
			case ModelPackage.SCOPE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ModelPackage.SCOPE__VALUES:
				return values != null && !values.isEmpty();
			case ModelPackage.SCOPE__PARAMETERS:
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
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case ModelPackage.SCOPE__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				default: return -1;
			}
		}
		if (baseClass == ContainsOperations.class) {
			switch (derivedFeatureID) {
				case ModelPackage.SCOPE__OPERATIONS: return ModelPackage.CONTAINS_OPERATIONS__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsScopes.class) {
			switch (derivedFeatureID) {
				case ModelPackage.SCOPE__SCOPES: return ModelPackage.CONTAINS_SCOPES__SCOPES;
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
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return ModelPackage.SCOPE__WIRES;
				default: return -1;
			}
		}
		if (baseClass == ContainsOperations.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_OPERATIONS__OPERATIONS: return ModelPackage.SCOPE__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsScopes.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_SCOPES__SCOPES: return ModelPackage.SCOPE__SCOPES;
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
		result.append(" (overridden: ");
		result.append(overridden);
		result.append(')');
		return result.toString();
	}

} //ScopeImpl
