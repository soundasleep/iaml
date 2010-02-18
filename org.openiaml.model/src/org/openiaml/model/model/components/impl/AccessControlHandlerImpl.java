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
import org.openiaml.model.model.users.ProvidesEdgesSource;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Access Control Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.components.impl.AccessControlHandlerImpl#getInParameterEdges <em>In Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.components.impl.AccessControlHandlerImpl#getOutRequiresEdges <em>Out Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.components.impl.AccessControlHandlerImpl#getOutProvidesEdges <em>Out Provides Edges</em>}</li>
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
	 * The cached value of the '{@link #getOutRequiresEdges() <em>Out Requires Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutRequiresEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiresEdge> outRequiresEdges;
	/**
	 * The cached value of the '{@link #getOutProvidesEdges() <em>Out Provides Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutProvidesEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidesEdge> outProvidesEdges;

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
	public EList<RequiresEdge> getOutRequiresEdges() {
		if (outRequiresEdges == null) {
			outRequiresEdges = new EObjectWithInverseResolvingEList<RequiresEdge>(RequiresEdge.class, this, ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES, WiresPackage.REQUIRES_EDGE__FROM);
		}
		return outRequiresEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidesEdge> getOutProvidesEdges() {
		if (outProvidesEdges == null) {
			outProvidesEdges = new EObjectWithInverseResolvingEList<ProvidesEdge>(ProvidesEdge.class, this, ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES, WiresPackage.PROVIDES_EDGE__FROM);
		}
		return outProvidesEdges;
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutRequiresEdges()).basicAdd(otherEnd, msgs);
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutProvidesEdges()).basicAdd(otherEnd, msgs);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				return ((InternalEList<?>)getOutRequiresEdges()).basicRemove(otherEnd, msgs);
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				return ((InternalEList<?>)getOutProvidesEdges()).basicRemove(otherEnd, msgs);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				return getOutRequiresEdges();
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				return getOutProvidesEdges();
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				getOutRequiresEdges().clear();
				getOutRequiresEdges().addAll((Collection<? extends RequiresEdge>)newValue);
				return;
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				getOutProvidesEdges().clear();
				getOutProvidesEdges().addAll((Collection<? extends ProvidesEdge>)newValue);
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				getOutRequiresEdges().clear();
				return;
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				getOutProvidesEdges().clear();
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
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES:
				return outRequiresEdges != null && !outRequiresEdges.isEmpty();
			case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES:
				return outProvidesEdges != null && !outProvidesEdges.isEmpty();
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
		if (baseClass == RequiresEdgesSource.class) {
			switch (derivedFeatureID) {
				case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES: return UsersPackage.REQUIRES_EDGES_SOURCE__OUT_REQUIRES_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ProvidesEdgesSource.class) {
			switch (derivedFeatureID) {
				case ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES: return UsersPackage.PROVIDES_EDGES_SOURCE__OUT_PROVIDES_EDGES;
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
		if (baseClass == RequiresEdgesSource.class) {
			switch (baseFeatureID) {
				case UsersPackage.REQUIRES_EDGES_SOURCE__OUT_REQUIRES_EDGES: return ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_REQUIRES_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ProvidesEdgesSource.class) {
			switch (baseFeatureID) {
				case UsersPackage.PROVIDES_EDGES_SOURCE__OUT_PROVIDES_EDGES: return ComponentsPackage.ACCESS_CONTROL_HANDLER__OUT_PROVIDES_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //AccessControlHandlerImpl
