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
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Wire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.impl.SetWireImpl#getInEdges <em>In Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.SetWireImpl#getInConditionEdges <em>In Condition Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetWireImpl extends CompositeWireImpl implements SetWire {
	/**
	 * The cached value of the '{@link #getInEdges() <em>In Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> inEdges;

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
	protected SetWireImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WiresPackage.Literals.SET_WIRE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getInEdges() {
		if (inEdges == null) {
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, WiresPackage.SET_WIRE__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
		}
		return inEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionEdge> getInConditionEdges() {
		if (inConditionEdges == null) {
			inConditionEdges = new EObjectWithInverseResolvingEList<ConditionEdge>(ConditionEdge.class, this, WiresPackage.SET_WIRE__IN_CONDITION_EDGES, WiresPackage.CONDITION_EDGE__TO);
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInEdges()).basicAdd(otherEnd, msgs);
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				return ((InternalEList<?>)getInEdges()).basicRemove(otherEnd, msgs);
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				return getInEdges();
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				getInEdges().clear();
				return;
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
			case WiresPackage.SET_WIRE__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
			case WiresPackage.SET_WIRE__IN_CONDITION_EDGES:
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
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.SET_WIRE__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ConditionEdgeDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.SET_WIRE__IN_CONDITION_EDGES: return WiresPackage.CONDITION_EDGE_DESTINATION__IN_CONDITION_EDGES;
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
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return WiresPackage.SET_WIRE__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ConditionEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.CONDITION_EDGE_DESTINATION__IN_CONDITION_EDGES: return WiresPackage.SET_WIRE__IN_CONDITION_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SetWireImpl
