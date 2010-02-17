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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requires Wire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.impl.RequiresWireImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.RequiresWireImpl#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.RequiresWireImpl#getExtendsEdges <em>Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.RequiresWireImpl#getOutEdges <em>Out Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.impl.RequiresWireImpl#getInEdges <em>In Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequiresWireImpl extends SingleWireImpl implements RequiresWire {
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
	 * The cached value of the '{@link #getOutEdges() <em>Out Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> outEdges;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiresWireImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WiresPackage.Literals.REQUIRES_WIRE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, WiresPackage.REQUIRES_WIRE__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getParameterEdges() {
		if (parameterEdges == null) {
			parameterEdges = new EObjectContainmentEList<ParameterEdge>(ParameterEdge.class, this, WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES);
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
			extendsEdges = new EObjectContainmentEList<ExtendsEdge>(ExtendsEdge.class, this, WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES);
		}
		return extendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getOutEdges() {
		if (outEdges == null) {
			outEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, WiresPackage.REQUIRES_WIRE__OUT_EDGES, ModelPackage.WIRE_EDGE__FROM);
		}
		return outEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getInEdges() {
		if (inEdges == null) {
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, WiresPackage.REQUIRES_WIRE__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
		}
		return inEdges;
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
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutEdges()).basicAdd(otherEnd, msgs);
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInEdges()).basicAdd(otherEnd, msgs);
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
			case WiresPackage.REQUIRES_WIRE__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES:
				return ((InternalEList<?>)getParameterEdges()).basicRemove(otherEnd, msgs);
			case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES:
				return ((InternalEList<?>)getExtendsEdges()).basicRemove(otherEnd, msgs);
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				return ((InternalEList<?>)getOutEdges()).basicRemove(otherEnd, msgs);
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				return ((InternalEList<?>)getInEdges()).basicRemove(otherEnd, msgs);
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
			case WiresPackage.REQUIRES_WIRE__WIRES:
				return getWires();
			case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES:
				return getParameterEdges();
			case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES:
				return getExtendsEdges();
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				return getOutEdges();
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				return getInEdges();
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
			case WiresPackage.REQUIRES_WIRE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES:
				getParameterEdges().clear();
				getParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				getExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				getOutEdges().clear();
				getOutEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
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
			case WiresPackage.REQUIRES_WIRE__WIRES:
				getWires().clear();
				return;
			case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES:
				getParameterEdges().clear();
				return;
			case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES:
				getExtendsEdges().clear();
				return;
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				getOutEdges().clear();
				return;
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				getInEdges().clear();
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
			case WiresPackage.REQUIRES_WIRE__WIRES:
				return wires != null && !wires.isEmpty();
			case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES:
				return parameterEdges != null && !parameterEdges.isEmpty();
			case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES:
				return extendsEdges != null && !extendsEdges.isEmpty();
			case WiresPackage.REQUIRES_WIRE__OUT_EDGES:
				return outEdges != null && !outEdges.isEmpty();
			case WiresPackage.REQUIRES_WIRE__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
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
				case WiresPackage.REQUIRES_WIRE__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				case WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES: return ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES;
				case WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES: return ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ShouldntContainWires.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == WireEdgesSource.class) {
			switch (derivedFeatureID) {
				case WiresPackage.REQUIRES_WIRE__OUT_EDGES: return ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case WiresPackage.REQUIRES_WIRE__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
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
				case ModelPackage.CONTAINS_WIRES__WIRES: return WiresPackage.REQUIRES_WIRE__WIRES;
				case ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES: return WiresPackage.REQUIRES_WIRE__PARAMETER_EDGES;
				case ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES: return WiresPackage.REQUIRES_WIRE__EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ShouldntContainWires.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == WireEdgesSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES: return WiresPackage.REQUIRES_WIRE__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return WiresPackage.REQUIRES_WIRE__IN_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RequiresWireImpl
