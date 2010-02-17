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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getOutParameterEdges <em>Out Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getOutExtendsEdges <em>Out Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getInExtendsEdges <em>In Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainObjectImpl extends ApplicationElementImpl implements DomainObject {
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
	 * The cached value of the '{@link #getOutExtendsEdges() <em>Out Extends Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutExtendsEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendsEdge> outExtendsEdges;
	/**
	 * The cached value of the '{@link #getInExtendsEdges() <em>In Extends Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInExtendsEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendsEdge> inExtendsEdges;
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainAttribute> attributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOMAIN_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getOutParameterEdges() {
		if (outParameterEdges == null) {
			outParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__FROM);
		}
		return outParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getOutExtendsEdges() {
		if (outExtendsEdges == null) {
			outExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__FROM);
		}
		return outExtendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendsEdge> getInExtendsEdges() {
		if (inExtendsEdges == null) {
			inExtendsEdges = new EObjectWithInverseResolvingEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES, WiresPackage.EXTENDS_EDGE__TO);
		}
		return inExtendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<DomainAttribute>(DomainAttribute.class, this, ModelPackage.DOMAIN_OBJECT__ATTRIBUTES);
		}
		return attributes;
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutParameterEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutExtendsEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInExtendsEdges()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				return ((InternalEList<?>)getOutParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				return ((InternalEList<?>)getOutExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				return ((InternalEList<?>)getInExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				return getOutParameterEdges();
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				return getOutExtendsEdges();
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				return getInExtendsEdges();
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return getAttributes();
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				getOutParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				getOutExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				getInExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends DomainAttribute>)newValue);
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				getOutExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				getInExtendsEdges().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				getAttributes().clear();
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
			case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES:
				return outParameterEdges != null && !outParameterEdges.isEmpty();
			case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES:
				return outExtendsEdges != null && !outExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES:
				return inExtendsEdges != null && !inExtendsEdges.isEmpty();
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
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
		if (baseClass == ParameterEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES: return WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES;
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
		if (baseClass == ParameterEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES: return ModelPackage.DOMAIN_OBJECT__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES: return ModelPackage.DOMAIN_OBJECT__OUT_EXTENDS_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ExtendsEdgeDestination.class) {
			switch (baseFeatureID) {
				case WiresPackage.EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES: return ModelPackage.DOMAIN_OBJECT__IN_EXTENDS_EDGES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //DomainObjectImpl
