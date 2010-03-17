/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sync Wire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.impl.SyncWireImpl#getInWires <em>In Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.SyncWireImpl#getInParameterEdges <em>In Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.SyncWireImpl#getInConditionEdges <em>In Condition Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SyncWireImpl extends CompositeWireImpl implements SyncWire {
	/**
	 * The cached value of the '{@link #getInWires() <em>In Wires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInWires()
	 * @generated
	 * @ordered
	 */
	protected EList<Wire> inWires;

	/**
	 * The cached value of the '{@link #getInParameterEdges() <em>In Parameter Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInParameterEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterEdge> inParameterEdges;

	/**
	 * The cached value of the '{@link #getInConditionEdges() <em>In Condition Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInConditionEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ConditionEdge> inConditionEdges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SyncWireImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WiresPackage.Literals.SYNC_WIRE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Wire> getInWires() {
		if (inWires == null) {
			inWires = new EObjectWithInverseResolvingEList<Wire>(Wire.class, this, WiresPackage.SYNC_WIRE__IN_WIRES, ModelPackage.WIRE__TO);
		}
		return inWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getInParameterEdges() {
		if (inParameterEdges == null) {
			inParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__TO);
		}
		return inParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionEdge> getInConditionEdges() {
		if (inConditionEdges == null) {
			inConditionEdges = new EObjectWithInverseResolvingEList<ConditionEdge>(ConditionEdge.class, this, WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES, WiresPackage.CONDITION_EDGE__TO);
		}
		return inConditionEdges;
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInWires()).basicAdd(otherEnd, msgs);
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInParameterEdges()).basicAdd(otherEnd, msgs);
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInConditionEdges()).basicAdd(otherEnd, msgs);
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				return ((InternalEList<?>)getInWires()).basicRemove(otherEnd, msgs);
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				return ((InternalEList<?>)getInParameterEdges()).basicRemove(otherEnd, msgs);
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				return ((InternalEList<?>)getInConditionEdges()).basicRemove(otherEnd, msgs);
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				return getInWires();
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				return getInParameterEdges();
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				return getInConditionEdges();
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				getInWires().clear();
				getInWires().addAll((Collection<? extends Wire>)newValue);
				return;
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
				getInParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				getInConditionEdges().clear();
				getInConditionEdges().addAll((Collection<? extends ConditionEdge>)newValue);
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				getInWires().clear();
				return;
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
				return;
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				getInConditionEdges().clear();
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
			case WiresPackage.SYNC_WIRE__IN_WIRES:
				return inWires != null && !inWires.isEmpty();
			case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES:
				return inParameterEdges != null && !inParameterEdges.isEmpty();
			case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES:
				return inConditionEdges != null && !inConditionEdges.isEmpty();
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
		if (baseClass == WireDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.SYNC_WIRE__IN_WIRES: return ModelPackage.WIRE_DESTINATION__IN_WIRES;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgeDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ConditionEdgeDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES: return WiresPackage.CONDITION_EDGE_DESTINATION__IN_CONDITION_EDGES;
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
		if (baseClass == WireDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_DESTINATION__IN_WIRES: return WiresPackage.SYNC_WIRE__IN_WIRES;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES: return WiresPackage.SYNC_WIRE__IN_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ConditionEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.CONDITION_EDGE_DESTINATION__IN_CONDITION_EDGES: return WiresPackage.SYNC_WIRE__IN_CONDITION_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SyncWireImpl