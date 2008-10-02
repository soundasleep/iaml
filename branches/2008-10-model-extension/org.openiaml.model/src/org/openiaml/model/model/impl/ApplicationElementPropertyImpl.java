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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.ValueReference;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Element Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl#getEdges <em>Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl#getInEdges <em>In Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl#getInValueReferences <em>In Value References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationElementPropertyImpl extends EObjectImpl implements ApplicationElementProperty {
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
	 * The cached value of the '{@link #getEdges() <em>Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> edges;

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
	protected ApplicationElementPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.APPLICATION_ELEMENT_PROPERTY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getEdges() {
		if (edges == null) {
			edges = new EObjectContainmentWithInverseEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES, ModelPackage.WIRE_EDGE__FROM);
		}
		return edges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getInEdges() {
		if (inEdges == null) {
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
		}
		return inEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueReference getInValueReferences() {
		if (eContainerFeatureID != ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES) return null;
		return (ValueReference)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInValueReferences(ValueReference newInValueReferences, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInValueReferences, ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInValueReferences(ValueReference newInValueReferences) {
		if (newInValueReferences != eInternalContainer() || (eContainerFeatureID != ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES && newInValueReferences != null)) {
			if (EcoreUtil.isAncestor(this, newInValueReferences))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueReferences != null)
				msgs = ((InternalEObject)newInValueReferences).eInverseAdd(this, ModelPackage.VALUE_REFERENCE__VALUE, ValueReference.class, msgs);
			msgs = basicSetInValueReferences(newInValueReferences, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES, newInValueReferences, newInValueReferences));
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
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInValueReferences((ValueReference)otherEnd, msgs);
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
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				return ((InternalEList<?>)getEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				return ((InternalEList<?>)getInEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				return basicSetInValueReferences(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				return eInternalContainer().eInverseRemove(this, ModelPackage.VALUE_REFERENCE__VALUE, ValueReference.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__NAME:
				return getName();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				return getEdges();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				return getInEdges();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				return getInValueReferences();
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
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				getEdges().clear();
				getEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				setInValueReferences((ValueReference)newValue);
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
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				getEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				getInEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				setInValueReferences((ValueReference)null);
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
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES:
				return edges != null && !edges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES:
				return getInValueReferences() != null;
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
		if (baseClass == WireEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES: return ModelPackage.WIRE_EDGES_SOURCE__EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
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
		if (baseClass == WireEdgesSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGES_SOURCE__EDGES: return ModelPackage.APPLICATION_ELEMENT_PROPERTY__EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return ModelPackage.APPLICATION_ELEMENT_PROPERTY__IN_EDGES;
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

} //ApplicationElementPropertyImpl
