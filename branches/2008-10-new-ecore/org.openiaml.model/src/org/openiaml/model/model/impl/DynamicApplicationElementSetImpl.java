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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Application Element Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getOutEdges <em>Out Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getInEdges <em>In Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicApplicationElementSetImpl extends EObjectImpl implements DynamicApplicationElementSet {
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
	 * The cached value of the '{@link #getEventTriggers() <em>Event Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<EventTrigger> eventTriggers;

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
	 * The default value of the '{@link #getQuery() <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuery()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQuery() <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuery()
	 * @generated
	 * @ordered
	 */
	protected String query = QUERY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicApplicationElementSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DYNAMIC_APPLICATION_ELEMENT_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS);
		}
		return operations;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventTrigger> getEventTriggers() {
		if (eventTriggers == null) {
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS);
		}
		return eventTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getOutEdges() {
		if (outEdges == null) {
			outEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES, ModelPackage.WIRE_EDGE__FROM);
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
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
		}
		return inEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuery(String newQuery) {
		String oldQuery = query;
		query = newQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__QUERY, oldQuery, query));
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				return ((InternalEList<?>)getOutEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS:
				return getOperations();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME:
				return getName();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS:
				return getEventTriggers();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				return getOutEdges();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
				return getInEdges();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__QUERY:
				return getQuery();
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				getOutEdges().clear();
				getOutEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__QUERY:
				setQuery((String)newValue);
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS:
				getOperations().clear();
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				getOutEdges().clear();
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
				getInEdges().clear();
				return;
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__QUERY:
				setQuery(QUERY_EDEFAULT);
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES:
				return outEdges != null && !outEdges.isEmpty();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__QUERY:
				return QUERY_EDEFAULT == null ? query != null : !QUERY_EDEFAULT.equals(query);
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
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME: return ModelPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS: return ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == WireEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES: return ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
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
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.NAMED_ELEMENT__NAME: return ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__NAME;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS: return ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == WireEdgesSource.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES: return ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES;
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
		result.append(", query: ");
		result.append(query);
		result.append(')');
		return result.toString();
	}

} //DynamicApplicationElementSetImpl
