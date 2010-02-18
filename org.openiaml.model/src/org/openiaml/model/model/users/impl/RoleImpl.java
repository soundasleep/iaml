/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.impl.DomainObjectImpl;
import org.openiaml.model.model.users.ProvidesEdgesSource;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.users.impl.RoleImpl#getInRequiresEdges <em>In Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.users.impl.RoleImpl#getOutProvidesEdges <em>Out Provides Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends DomainObjectImpl implements Role {
	/**
	 * The cached value of the '{@link #getInRequiresEdges() <em>In Requires Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInRequiresEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiresEdge> inRequiresEdges;
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
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiresEdge> getInRequiresEdges() {
		if (inRequiresEdges == null) {
			inRequiresEdges = new EObjectWithInverseResolvingEList<RequiresEdge>(RequiresEdge.class, this, UsersPackage.ROLE__IN_REQUIRES_EDGES, WiresPackage.REQUIRES_EDGE__TO);
		}
		return inRequiresEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidesEdge> getOutProvidesEdges() {
		if (outProvidesEdges == null) {
			outProvidesEdges = new EObjectWithInverseResolvingEList<ProvidesEdge>(ProvidesEdge.class, this, UsersPackage.ROLE__OUT_PROVIDES_EDGES, WiresPackage.PROVIDES_EDGE__FROM);
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInRequiresEdges()).basicAdd(otherEnd, msgs);
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				return ((InternalEList<?>)getInRequiresEdges()).basicRemove(otherEnd, msgs);
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				return getInRequiresEdges();
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				getInRequiresEdges().clear();
				getInRequiresEdges().addAll((Collection<? extends RequiresEdge>)newValue);
				return;
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				getInRequiresEdges().clear();
				return;
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
			case UsersPackage.ROLE__IN_REQUIRES_EDGES:
				return inRequiresEdges != null && !inRequiresEdges.isEmpty();
			case UsersPackage.ROLE__OUT_PROVIDES_EDGES:
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
		if (baseClass == RequiresEdgeDestination.class) {
			switch (derivedFeatureID) {
				case UsersPackage.ROLE__IN_REQUIRES_EDGES: return UsersPackage.REQUIRES_EDGE_DESTINATION__IN_REQUIRES_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ProvidesEdgesSource.class) {
			switch (derivedFeatureID) {
				case UsersPackage.ROLE__OUT_PROVIDES_EDGES: return UsersPackage.PROVIDES_EDGES_SOURCE__OUT_PROVIDES_EDGES;
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
		if (baseClass == RequiresEdgeDestination.class) {
			switch (baseFeatureID) {
				case UsersPackage.REQUIRES_EDGE_DESTINATION__IN_REQUIRES_EDGES: return UsersPackage.ROLE__IN_REQUIRES_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ProvidesEdgesSource.class) {
			switch (baseFeatureID) {
				case UsersPackage.PROVIDES_EDGES_SOURCE__OUT_PROVIDES_EDGES: return UsersPackage.ROLE__OUT_PROVIDES_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RoleImpl
