/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.impl.ApplicationElementContainerImpl;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Access Control Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.components.impl.AccessControlHandlerImpl#getInParameterEdges <em>In Parameter Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AccessControlHandlerImpl extends ApplicationElementContainerImpl implements AccessControlHandler {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AccessControlHandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.ACCESS_CONTROL_HANDLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getInParameterEdges() {
		if (inParameterEdges == null) {
			inParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__TO);
		}
		return inParameterEdges;
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInParameterEdges()).basicAdd(otherEnd, msgs);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				return ((InternalEList<?>)getInParameterEdges()).basicRemove(otherEnd, msgs);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				return getInParameterEdges();
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
				getInParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES:
				return inParameterEdges != null && !inParameterEdges.isEmpty();
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
		if (baseClass == ParameterEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES;
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
		if (baseClass == ParameterEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES: return ComponentsPackage.ACCESS_CONTROL_HANDLER__IN_PARAMETER_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //AccessControlHandlerImpl
