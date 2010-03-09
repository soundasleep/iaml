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
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getGeneratedBy <em>Generated By</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#isIsGenerated <em>Is Generated</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getGeneratedRule <em>Generated Rule</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getExtendsEdges <em>Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getRequiresEdges <em>Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getProvidesEdges <em>Provides Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getConstraintEdges <em>Constraint Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getConditionEdges <em>Condition Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getOutWires <em>Out Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getOutFlows <em>Out Flows</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getOutParameterEdges <em>Out Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.StaticValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StaticValueImpl extends EObjectImpl implements StaticValue {
	/**
	 * The cached value of the '{@link #getGeneratedBy() <em>Generated By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratesElements> generatedBy;

	/**
	 * The default value of the '{@link #isIsGenerated() <em>Is Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGenerated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_GENERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsGenerated() <em>Is Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGenerated()
	 * @generated
	 * @ordered
	 */
	protected boolean isGenerated = IS_GENERATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratedRule() <em>Generated Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedRule()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATED_RULE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedRule() <em>Generated Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedRule()
	 * @generated
	 * @ordered
	 */
	protected String generatedRule = GENERATED_RULE_EDEFAULT;

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
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<Wire> wires;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> actions;

	/**
	 * The cached value of the '{@link #getParameterEdges() <em>Parameter Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterEdge> parameterEdges;

	/**
	 * The cached value of the '{@link #getExtendsEdges() <em>Extends Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendsEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendsEdge> extendsEdges;

	/**
	 * The cached value of the '{@link #getRequiresEdges() <em>Requires Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiresEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiresEdge> requiresEdges;

	/**
	 * The cached value of the '{@link #getProvidesEdges() <em>Provides Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidesEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidesEdge> providesEdges;

	/**
	 * The cached value of the '{@link #getConstraintEdges() <em>Constraint Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintEdge> constraintEdges;

	/**
	 * The cached value of the '{@link #getConditionEdges() <em>Condition Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ConditionEdge> conditionEdges;

	/**
	 * The cached value of the '{@link #getOutWires() <em>Out Wires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutWires()
	 * @generated
	 * @ordered
	 */
	protected EList<Wire> outWires;

	/**
	 * The cached value of the '{@link #getOutFlows() <em>Out Flows</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutFlows()
	 * @generated
	 * @ordered
	 */
	protected EList<DataFlowEdge> outFlows;

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
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.STATIC_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratesElements> getGeneratedBy() {
		if (generatedBy == null) {
			generatedBy = new EObjectWithInverseResolvingEList.ManyInverse<GeneratesElements>(GeneratesElements.class, this, ModelPackage.STATIC_VALUE__GENERATED_BY, ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS);
		}
		return generatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsGenerated() {
		return isGenerated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsGenerated(boolean newIsGenerated) {
		boolean oldIsGenerated = isGenerated;
		isGenerated = newIsGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.STATIC_VALUE__IS_GENERATED, oldIsGenerated, isGenerated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.STATIC_VALUE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGeneratedRule() {
		return generatedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratedRule(String newGeneratedRule) {
		String oldGeneratedRule = generatedRule;
		generatedRule = newGeneratedRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.STATIC_VALUE__GENERATED_RULE, oldGeneratedRule, generatedRule));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.STATIC_VALUE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Wire> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<Wire>(Wire.class, this, ModelPackage.STATIC_VALUE__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Action> getActions() {
		if (actions == null) {
			actions = new EObjectContainmentEList<Action>(Action.class, this, ModelPackage.STATIC_VALUE__ACTIONS);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getParameterEdges() {
		if (parameterEdges == null) {
			parameterEdges = new EObjectContainmentEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.STATIC_VALUE__PARAMETER_EDGES);
		}
		return parameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getExtendsEdges() {
		if (extendsEdges == null) {
			extendsEdges = new EObjectContainmentEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.STATIC_VALUE__EXTENDS_EDGES);
		}
		return extendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiresEdge> getRequiresEdges() {
		if (requiresEdges == null) {
			requiresEdges = new EObjectContainmentEList<RequiresEdge>(RequiresEdge.class, this, ModelPackage.STATIC_VALUE__REQUIRES_EDGES);
		}
		return requiresEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidesEdge> getProvidesEdges() {
		if (providesEdges == null) {
			providesEdges = new EObjectContainmentEList<ProvidesEdge>(ProvidesEdge.class, this, ModelPackage.STATIC_VALUE__PROVIDES_EDGES);
		}
		return providesEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConstraintEdge> getConstraintEdges() {
		if (constraintEdges == null) {
			constraintEdges = new EObjectContainmentEList<ConstraintEdge>(ConstraintEdge.class, this, ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES);
		}
		return constraintEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionEdge> getConditionEdges() {
		if (conditionEdges == null) {
			conditionEdges = new EObjectContainmentEList<ConditionEdge>(ConditionEdge.class, this, ModelPackage.STATIC_VALUE__CONDITION_EDGES);
		}
		return conditionEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Wire> getOutWires() {
		if (outWires == null) {
			outWires = new EObjectWithInverseResolvingEList<Wire>(Wire.class, this, ModelPackage.STATIC_VALUE__OUT_WIRES, ModelPackage.WIRE__FROM);
		}
		return outWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataFlowEdge> getOutFlows() {
		if (outFlows == null) {
			outFlows = new EObjectWithInverseResolvingEList<DataFlowEdge>(DataFlowEdge.class, this, ModelPackage.STATIC_VALUE__OUT_FLOWS, ModelPackage.DATA_FLOW_EDGE__FROM);
		}
		return outFlows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getOutParameterEdges() {
		if (outParameterEdges == null) {
			outParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__FROM);
		}
		return outParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.STATIC_VALUE__VALUE, oldValue, value));
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedBy()).basicAdd(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutWires()).basicAdd(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutFlows()).basicAdd(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutParameterEdges()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				return ((InternalEList<?>)getGeneratedBy()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__ACTIONS:
				return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__PARAMETER_EDGES:
				return ((InternalEList<?>)getParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__EXTENDS_EDGES:
				return ((InternalEList<?>)getExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__REQUIRES_EDGES:
				return ((InternalEList<?>)getRequiresEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__PROVIDES_EDGES:
				return ((InternalEList<?>)getProvidesEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES:
				return ((InternalEList<?>)getConstraintEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__CONDITION_EDGES:
				return ((InternalEList<?>)getConditionEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				return ((InternalEList<?>)getOutWires()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				return ((InternalEList<?>)getOutFlows()).basicRemove(otherEnd, msgs);
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				return ((InternalEList<?>)getOutParameterEdges()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				return getGeneratedBy();
			case ModelPackage.STATIC_VALUE__IS_GENERATED:
				return isIsGenerated();
			case ModelPackage.STATIC_VALUE__ID:
				return getId();
			case ModelPackage.STATIC_VALUE__GENERATED_RULE:
				return getGeneratedRule();
			case ModelPackage.STATIC_VALUE__NAME:
				return getName();
			case ModelPackage.STATIC_VALUE__WIRES:
				return getWires();
			case ModelPackage.STATIC_VALUE__ACTIONS:
				return getActions();
			case ModelPackage.STATIC_VALUE__PARAMETER_EDGES:
				return getParameterEdges();
			case ModelPackage.STATIC_VALUE__EXTENDS_EDGES:
				return getExtendsEdges();
			case ModelPackage.STATIC_VALUE__REQUIRES_EDGES:
				return getRequiresEdges();
			case ModelPackage.STATIC_VALUE__PROVIDES_EDGES:
				return getProvidesEdges();
			case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES:
				return getConstraintEdges();
			case ModelPackage.STATIC_VALUE__CONDITION_EDGES:
				return getConditionEdges();
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				return getOutWires();
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				return getOutFlows();
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				return getOutParameterEdges();
			case ModelPackage.STATIC_VALUE__VALUE:
				return getValue();
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				getGeneratedBy().clear();
				getGeneratedBy().addAll((Collection<? extends GeneratesElements>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__IS_GENERATED:
				setIsGenerated((Boolean)newValue);
				return;
			case ModelPackage.STATIC_VALUE__ID:
				setId((String)newValue);
				return;
			case ModelPackage.STATIC_VALUE__GENERATED_RULE:
				setGeneratedRule((String)newValue);
				return;
			case ModelPackage.STATIC_VALUE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.STATIC_VALUE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends Wire>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__ACTIONS:
				getActions().clear();
				getActions().addAll((Collection<? extends Action>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__PARAMETER_EDGES:
				getParameterEdges().clear();
				getParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				getExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__REQUIRES_EDGES:
				getRequiresEdges().clear();
				getRequiresEdges().addAll((Collection<? extends RequiresEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__PROVIDES_EDGES:
				getProvidesEdges().clear();
				getProvidesEdges().addAll((Collection<? extends ProvidesEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				getConstraintEdges().addAll((Collection<? extends ConstraintEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__CONDITION_EDGES:
				getConditionEdges().clear();
				getConditionEdges().addAll((Collection<? extends ConditionEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				getOutWires().clear();
				getOutWires().addAll((Collection<? extends Wire>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				getOutFlows().clear();
				getOutFlows().addAll((Collection<? extends DataFlowEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				getOutParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.STATIC_VALUE__VALUE:
				setValue((String)newValue);
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				getGeneratedBy().clear();
				return;
			case ModelPackage.STATIC_VALUE__IS_GENERATED:
				setIsGenerated(IS_GENERATED_EDEFAULT);
				return;
			case ModelPackage.STATIC_VALUE__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.STATIC_VALUE__GENERATED_RULE:
				setGeneratedRule(GENERATED_RULE_EDEFAULT);
				return;
			case ModelPackage.STATIC_VALUE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.STATIC_VALUE__WIRES:
				getWires().clear();
				return;
			case ModelPackage.STATIC_VALUE__ACTIONS:
				getActions().clear();
				return;
			case ModelPackage.STATIC_VALUE__PARAMETER_EDGES:
				getParameterEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__REQUIRES_EDGES:
				getRequiresEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__PROVIDES_EDGES:
				getProvidesEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__CONDITION_EDGES:
				getConditionEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				getOutWires().clear();
				return;
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				getOutFlows().clear();
				return;
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				return;
			case ModelPackage.STATIC_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case ModelPackage.STATIC_VALUE__GENERATED_BY:
				return generatedBy != null && !generatedBy.isEmpty();
			case ModelPackage.STATIC_VALUE__IS_GENERATED:
				return isGenerated != IS_GENERATED_EDEFAULT;
			case ModelPackage.STATIC_VALUE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.STATIC_VALUE__GENERATED_RULE:
				return GENERATED_RULE_EDEFAULT == null ? generatedRule != null : !GENERATED_RULE_EDEFAULT.equals(generatedRule);
			case ModelPackage.STATIC_VALUE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.STATIC_VALUE__WIRES:
				return wires != null && !wires.isEmpty();
			case ModelPackage.STATIC_VALUE__ACTIONS:
				return actions != null && !actions.isEmpty();
			case ModelPackage.STATIC_VALUE__PARAMETER_EDGES:
				return parameterEdges != null && !parameterEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__EXTENDS_EDGES:
				return extendsEdges != null && !extendsEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__REQUIRES_EDGES:
				return requiresEdges != null && !requiresEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__PROVIDES_EDGES:
				return providesEdges != null && !providesEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES:
				return constraintEdges != null && !constraintEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__CONDITION_EDGES:
				return conditionEdges != null && !conditionEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__OUT_WIRES:
				return outWires != null && !outWires.isEmpty();
			case ModelPackage.STATIC_VALUE__OUT_FLOWS:
				return outFlows != null && !outFlows.isEmpty();
			case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES:
				return outParameterEdges != null && !outParameterEdges.isEmpty();
			case ModelPackage.STATIC_VALUE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
				case ModelPackage.STATIC_VALUE__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				case ModelPackage.STATIC_VALUE__ACTIONS: return ModelPackage.CONTAINS_WIRES__ACTIONS;
				case ModelPackage.STATIC_VALUE__PARAMETER_EDGES: return ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES;
				case ModelPackage.STATIC_VALUE__EXTENDS_EDGES: return ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES;
				case ModelPackage.STATIC_VALUE__REQUIRES_EDGES: return ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES;
				case ModelPackage.STATIC_VALUE__PROVIDES_EDGES: return ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES;
				case ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES: return ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES;
				case ModelPackage.STATIC_VALUE__CONDITION_EDGES: return ModelPackage.CONTAINS_WIRES__CONDITION_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ShouldntContainWires.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == WireSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.STATIC_VALUE__OUT_WIRES: return ModelPackage.WIRE_SOURCE__OUT_WIRES;
				default: return -1;
			}
		}
		if (baseClass == DataFlowEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.STATIC_VALUE__OUT_FLOWS: return ModelPackage.DATA_FLOW_EDGES_SOURCE__OUT_FLOWS;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES;
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
				case ModelPackage.CONTAINS_WIRES__WIRES: return ModelPackage.STATIC_VALUE__WIRES;
				case ModelPackage.CONTAINS_WIRES__ACTIONS: return ModelPackage.STATIC_VALUE__ACTIONS;
				case ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES: return ModelPackage.STATIC_VALUE__PARAMETER_EDGES;
				case ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES: return ModelPackage.STATIC_VALUE__EXTENDS_EDGES;
				case ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES: return ModelPackage.STATIC_VALUE__REQUIRES_EDGES;
				case ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES: return ModelPackage.STATIC_VALUE__PROVIDES_EDGES;
				case ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES: return ModelPackage.STATIC_VALUE__CONSTRAINT_EDGES;
				case ModelPackage.CONTAINS_WIRES__CONDITION_EDGES: return ModelPackage.STATIC_VALUE__CONDITION_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ShouldntContainWires.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == WireSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_SOURCE__OUT_WIRES: return ModelPackage.STATIC_VALUE__OUT_WIRES;
				default: return -1;
			}
		}
		if (baseClass == DataFlowEdgesSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.DATA_FLOW_EDGES_SOURCE__OUT_FLOWS: return ModelPackage.STATIC_VALUE__OUT_FLOWS;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES: return ModelPackage.STATIC_VALUE__OUT_PARAMETER_EDGES;
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
		result.append(" (isGenerated: ");
		result.append(isGenerated);
		result.append(", id: ");
		result.append(id);
		result.append(", generatedRule: ");
		result.append(generatedRule);
		result.append(", name: ");
		result.append(name);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //StaticValueImpl
