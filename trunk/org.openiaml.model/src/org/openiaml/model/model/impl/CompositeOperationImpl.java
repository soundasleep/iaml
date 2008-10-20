/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getDataEdges <em>Data Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getExecutionEdges <em>Execution Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#get_shouldnt_properties <em>shouldnt properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeOperationImpl extends ChainedOperationImpl implements CompositeOperation {
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
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> wires;

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNode> nodes;

	/**
	 * The cached value of the '{@link #getDataEdges() <em>Data Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<DataFlowEdge> dataEdges;

	/**
	 * The cached value of the '{@link #getExecutionEdges() <em>Execution Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionEdge> executionEdges;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<TemporaryVariable> variables;

	/**
	 * The cached value of the '{@link #get_shouldnt_properties() <em>shouldnt properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #get_shouldnt_properties()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElementProperty> _shouldnt_properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.COMPOSITE_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.COMPOSITE_OPERATION__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.COMPOSITE_OPERATION__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityNode> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<ActivityNode>(ActivityNode.class, this, ModelPackage.COMPOSITE_OPERATION__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataFlowEdge> getDataEdges() {
		if (dataEdges == null) {
			dataEdges = new EObjectContainmentEList<DataFlowEdge>(DataFlowEdge.class, this, ModelPackage.COMPOSITE_OPERATION__DATA_EDGES);
		}
		return dataEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionEdge> getExecutionEdges() {
		if (executionEdges == null) {
			executionEdges = new EObjectContainmentEList<ExecutionEdge>(ExecutionEdge.class, this, ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES);
		}
		return executionEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TemporaryVariable> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList<TemporaryVariable>(TemporaryVariable.class, this, ModelPackage.COMPOSITE_OPERATION__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> get_shouldnt_properties() {
		if (_shouldnt_properties == null) {
			_shouldnt_properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES);
		}
		return _shouldnt_properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return ((InternalEList<?>)getDataEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return ((InternalEList<?>)getExecutionEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES:
				return ((InternalEList<?>)get_shouldnt_properties()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				return getOperations();
			case ModelPackage.COMPOSITE_OPERATION__WIRES:
				return getWires();
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return getNodes();
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return getDataEdges();
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return getExecutionEdges();
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return getVariables();
			case ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES:
				return get_shouldnt_properties();
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
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends ActivityNode>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				getDataEdges().clear();
				getDataEdges().addAll((Collection<? extends DataFlowEdge>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				getExecutionEdges().clear();
				getExecutionEdges().addAll((Collection<? extends ExecutionEdge>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends TemporaryVariable>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES:
				get_shouldnt_properties().clear();
				get_shouldnt_properties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
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
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				getOperations().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__WIRES:
				getWires().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				getNodes().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				getDataEdges().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				getExecutionEdges().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				getVariables().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES:
				get_shouldnt_properties().clear();
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
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__WIRES:
				return wires != null && !wires.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return nodes != null && !nodes.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return dataEdges != null && !dataEdges.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return executionEdges != null && !executionEdges.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return variables != null && !variables.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__SHOULDNT_PROPERTIES:
				return _shouldnt_properties != null && !_shouldnt_properties.isEmpty();
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
		if (baseClass == ContainsOperations.class) {
			switch (derivedFeatureID) {
				case ModelPackage.COMPOSITE_OPERATION__OPERATIONS: return ModelPackage.CONTAINS_OPERATIONS__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case ModelPackage.COMPOSITE_OPERATION__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
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
		if (baseClass == ContainsOperations.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_OPERATIONS__OPERATIONS: return ModelPackage.COMPOSITE_OPERATION__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return ModelPackage.COMPOSITE_OPERATION__WIRES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //CompositeOperationImpl
