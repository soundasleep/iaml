/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.impl.ActivityNodeImpl;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getOutExecutions <em>Out Executions</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getInExecutions <em>In Executions</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getExtendsEdges <em>Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getRequiresEdges <em>Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getProvidesEdges <em>Provides Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getConstraintEdges <em>Constraint Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getConditionEdges <em>Condition Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getOutWires <em>Out Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.OperationCallNodeImpl#getOutActions <em>Out Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationCallNodeImpl extends ActivityNodeImpl implements OperationCallNode {
	/**
	 * The cached value of the '{@link #getOutExecutions() <em>Out Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionEdge> outExecutions;

	/**
	 * The cached value of the '{@link #getInExecutions() <em>In Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionEdge> inExecutions;

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
	 * The cached value of the '{@link #getOutActions() <em>Out Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> outActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OperationsPackage.Literals.OPERATION_CALL_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionEdge> getOutExecutions() {
		if (outExecutions == null) {
			outExecutions = new EObjectWithInverseResolvingEList<ExecutionEdge>(ExecutionEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS, ModelPackage.EXECUTION_EDGE__FROM);
		}
		return outExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionEdge> getInExecutions() {
		if (inExecutions == null) {
			inExecutions = new EObjectWithInverseResolvingEList<ExecutionEdge>(ExecutionEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS, ModelPackage.EXECUTION_EDGE__TO);
		}
		return inExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Wire> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<Wire>(Wire.class, this, OperationsPackage.OPERATION_CALL_NODE__WIRES);
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
			actions = new EObjectContainmentEList<Action>(Action.class, this, OperationsPackage.OPERATION_CALL_NODE__ACTIONS);
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
			parameterEdges = new EObjectContainmentEList<ParameterEdge>(ParameterEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES);
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
			extendsEdges = new EObjectContainmentEList<ExtendsEdge>(ExtendsEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES);
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
			requiresEdges = new EObjectContainmentEList<RequiresEdge>(RequiresEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES);
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
			providesEdges = new EObjectContainmentEList<ProvidesEdge>(ProvidesEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES);
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
			constraintEdges = new EObjectContainmentEList<ConstraintEdge>(ConstraintEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES);
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
			conditionEdges = new EObjectContainmentEList<ConditionEdge>(ConditionEdge.class, this, OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES);
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
			outWires = new EObjectWithInverseResolvingEList<Wire>(Wire.class, this, OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES, ModelPackage.WIRE__FROM);
		}
		return outWires;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OperationsPackage.OPERATION_CALL_NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Action> getOutActions() {
		if (outActions == null) {
			outActions = new EObjectWithInverseResolvingEList<Action>(Action.class, this, OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS, ModelPackage.ACTION__FROM);
		}
		return outActions;
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutExecutions()).basicAdd(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInExecutions()).basicAdd(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutWires()).basicAdd(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutActions()).basicAdd(otherEnd, msgs);
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				return ((InternalEList<?>)getOutExecutions()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				return ((InternalEList<?>)getInExecutions()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__ACTIONS:
				return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES:
				return ((InternalEList<?>)getParameterEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES:
				return ((InternalEList<?>)getExtendsEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES:
				return ((InternalEList<?>)getRequiresEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES:
				return ((InternalEList<?>)getProvidesEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES:
				return ((InternalEList<?>)getConstraintEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES:
				return ((InternalEList<?>)getConditionEdges()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				return ((InternalEList<?>)getOutWires()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				return ((InternalEList<?>)getOutActions()).basicRemove(otherEnd, msgs);
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				return getOutExecutions();
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				return getInExecutions();
			case OperationsPackage.OPERATION_CALL_NODE__WIRES:
				return getWires();
			case OperationsPackage.OPERATION_CALL_NODE__ACTIONS:
				return getActions();
			case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES:
				return getParameterEdges();
			case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES:
				return getExtendsEdges();
			case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES:
				return getRequiresEdges();
			case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES:
				return getProvidesEdges();
			case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES:
				return getConstraintEdges();
			case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES:
				return getConditionEdges();
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				return getOutWires();
			case OperationsPackage.OPERATION_CALL_NODE__NAME:
				return getName();
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				return getOutActions();
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				getOutExecutions().clear();
				getOutExecutions().addAll((Collection<? extends ExecutionEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				getInExecutions().clear();
				getInExecutions().addAll((Collection<? extends ExecutionEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends Wire>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__ACTIONS:
				getActions().clear();
				getActions().addAll((Collection<? extends Action>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES:
				getParameterEdges().clear();
				getParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				getExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES:
				getRequiresEdges().clear();
				getRequiresEdges().addAll((Collection<? extends RequiresEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES:
				getProvidesEdges().clear();
				getProvidesEdges().addAll((Collection<? extends ProvidesEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				getConstraintEdges().addAll((Collection<? extends ConstraintEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES:
				getConditionEdges().clear();
				getConditionEdges().addAll((Collection<? extends ConditionEdge>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				getOutWires().clear();
				getOutWires().addAll((Collection<? extends Wire>)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__NAME:
				setName((String)newValue);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				getOutActions().clear();
				getOutActions().addAll((Collection<? extends Action>)newValue);
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				getOutExecutions().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				getInExecutions().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__WIRES:
				getWires().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__ACTIONS:
				getActions().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES:
				getParameterEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES:
				getRequiresEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES:
				getProvidesEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES:
				getConditionEdges().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				getOutWires().clear();
				return;
			case OperationsPackage.OPERATION_CALL_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				getOutActions().clear();
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
			case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS:
				return outExecutions != null && !outExecutions.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS:
				return inExecutions != null && !inExecutions.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__WIRES:
				return wires != null && !wires.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__ACTIONS:
				return actions != null && !actions.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES:
				return parameterEdges != null && !parameterEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES:
				return extendsEdges != null && !extendsEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES:
				return requiresEdges != null && !requiresEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES:
				return providesEdges != null && !providesEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES:
				return constraintEdges != null && !constraintEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES:
				return conditionEdges != null && !conditionEdges.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES:
				return outWires != null && !outWires.isEmpty();
			case OperationsPackage.OPERATION_CALL_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS:
				return outActions != null && !outActions.isEmpty();
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
		if (baseClass == ExecutionEdgesSource.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS: return ModelPackage.EXECUTION_EDGES_SOURCE__OUT_EXECUTIONS;
				default: return -1;
			}
		}
		if (baseClass == ExecutionEdgeDestination.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS: return ModelPackage.EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.OPERATION_CALL_NODE__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				case OperationsPackage.OPERATION_CALL_NODE__ACTIONS: return ModelPackage.CONTAINS_WIRES__ACTIONS;
				case OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES: return ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES;
				case OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES: return ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES;
				case OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES: return ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES;
				case OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES: return ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES;
				case OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES: return ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES;
				case OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES: return ModelPackage.CONTAINS_WIRES__CONDITION_EDGES;
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
				case OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES: return ModelPackage.WIRE_SOURCE__OUT_WIRES;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.OPERATION_CALL_NODE__NAME: return ModelPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ActionSource.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS: return ModelPackage.ACTION_SOURCE__OUT_ACTIONS;
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
		if (baseClass == ExecutionEdgesSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.EXECUTION_EDGES_SOURCE__OUT_EXECUTIONS: return OperationsPackage.OPERATION_CALL_NODE__OUT_EXECUTIONS;
				default: return -1;
			}
		}
		if (baseClass == ExecutionEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS: return OperationsPackage.OPERATION_CALL_NODE__IN_EXECUTIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return OperationsPackage.OPERATION_CALL_NODE__WIRES;
				case ModelPackage.CONTAINS_WIRES__ACTIONS: return OperationsPackage.OPERATION_CALL_NODE__ACTIONS;
				case ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES: return OperationsPackage.OPERATION_CALL_NODE__PARAMETER_EDGES;
				case ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES: return OperationsPackage.OPERATION_CALL_NODE__EXTENDS_EDGES;
				case ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES: return OperationsPackage.OPERATION_CALL_NODE__REQUIRES_EDGES;
				case ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES: return OperationsPackage.OPERATION_CALL_NODE__PROVIDES_EDGES;
				case ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES: return OperationsPackage.OPERATION_CALL_NODE__CONSTRAINT_EDGES;
				case ModelPackage.CONTAINS_WIRES__CONDITION_EDGES: return OperationsPackage.OPERATION_CALL_NODE__CONDITION_EDGES;
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
				case ModelPackage.WIRE_SOURCE__OUT_WIRES: return OperationsPackage.OPERATION_CALL_NODE__OUT_WIRES;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.NAMED_ELEMENT__NAME: return OperationsPackage.OPERATION_CALL_NODE__NAME;
				default: return -1;
			}
		}
		if (baseClass == ActionSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.ACTION_SOURCE__OUT_ACTIONS: return OperationsPackage.OPERATION_CALL_NODE__OUT_ACTIONS;
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

} //OperationCallNodeImpl
