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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getGeneratedElements <em>Generated Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#isOverridden <em>Overridden</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getDomainObjects <em>Domain Objects</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getDomainViews <em>Domain Views</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getDomainInstances <em>Domain Instances</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getConditions <em>Conditions</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getDataEdges <em>Data Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getExecutionEdges <em>Execution Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getValues <em>Values</em>}</li>
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
	 * The cached value of the '{@link #getGeneratedElements() <em>Generated Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratedElement> generatedElements;

	/**
	 * The default value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected boolean overridden = OVERRIDDEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDomainObjects() <em>Domain Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainObject> domainObjects;

	/**
	 * The cached value of the '{@link #getDomainViews() <em>Domain Views</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainViews()
	 * @generated
	 * @ordered
	 */
	protected EList<DerivedView> domainViews;

	/**
	 * The cached value of the '{@link #getDomainInstances() <em>Domain Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainObjectInstance> domainInstances;

	/**
	 * The cached value of the '{@link #getConditions() <em>Conditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Condition> conditions;

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
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticValue> values;

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
	public EList<GeneratedElement> getGeneratedElements() {
		if (generatedElements == null) {
			generatedElements = new EObjectWithInverseResolvingEList.ManyInverse<GeneratedElement>(GeneratedElement.class, this, ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS, ModelPackage.GENERATED_ELEMENT__GENERATED_BY);
		}
		return generatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverridden() {
		return overridden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverridden(boolean newOverridden) {
		boolean oldOverridden = overridden;
		overridden = newOverridden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN, oldOverridden, overridden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainObject> getDomainObjects() {
		if (domainObjects == null) {
			domainObjects = new EObjectContainmentEList<DomainObject>(DomainObject.class, this, ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS);
		}
		return domainObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DerivedView> getDomainViews() {
		if (domainViews == null) {
			domainViews = new EObjectContainmentEList<DerivedView>(DerivedView.class, this, ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS);
		}
		return domainViews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainObjectInstance> getDomainInstances() {
		if (domainInstances == null) {
			domainInstances = new EObjectContainmentEList<DomainObjectInstance>(DomainObjectInstance.class, this, ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES);
		}
		return domainInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Condition> getConditions() {
		if (conditions == null) {
			conditions = new EObjectContainmentEList<Condition>(Condition.class, this, ModelPackage.COMPOSITE_OPERATION__CONDITIONS);
		}
		return conditions;
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
	public EList<StaticValue> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<StaticValue>(StaticValue.class, this, ModelPackage.COMPOSITE_OPERATION__VALUES);
		}
		return values;
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
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedElements()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.COMPOSITE_OPERATION__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				return ((InternalEList<?>)getGeneratedElements()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS:
				return ((InternalEList<?>)getDomainObjects()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS:
				return ((InternalEList<?>)getDomainViews()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES:
				return ((InternalEList<?>)getDomainInstances()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__CONDITIONS:
				return ((InternalEList<?>)getConditions()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return ((InternalEList<?>)getDataEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return ((InternalEList<?>)getExecutionEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				return getGeneratedElements();
			case ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN:
				return isOverridden();
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS:
				return getDomainObjects();
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS:
				return getDomainViews();
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES:
				return getDomainInstances();
			case ModelPackage.COMPOSITE_OPERATION__CONDITIONS:
				return getConditions();
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return getNodes();
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return getDataEdges();
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return getExecutionEdges();
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return getVariables();
			case ModelPackage.COMPOSITE_OPERATION__VALUES:
				return getValues();
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
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				getGeneratedElements().addAll((Collection<? extends GeneratedElement>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN:
				setOverridden((Boolean)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				getDomainObjects().addAll((Collection<? extends DomainObject>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS:
				getDomainViews().clear();
				getDomainViews().addAll((Collection<? extends DerivedView>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES:
				getDomainInstances().clear();
				getDomainInstances().addAll((Collection<? extends DomainObjectInstance>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__CONDITIONS:
				getConditions().clear();
				getConditions().addAll((Collection<? extends Condition>)newValue);
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
			case ModelPackage.COMPOSITE_OPERATION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends StaticValue>)newValue);
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
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN:
				setOverridden(OVERRIDDEN_EDEFAULT);
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS:
				getDomainViews().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES:
				getDomainInstances().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__CONDITIONS:
				getConditions().clear();
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
			case ModelPackage.COMPOSITE_OPERATION__VALUES:
				getValues().clear();
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
			case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS:
				return generatedElements != null && !generatedElements.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN:
				return overridden != OVERRIDDEN_EDEFAULT;
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS:
				return domainObjects != null && !domainObjects.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS:
				return domainViews != null && !domainViews.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES:
				return domainInstances != null && !domainInstances.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__CONDITIONS:
				return conditions != null && !conditions.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__NODES:
				return nodes != null && !nodes.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__DATA_EDGES:
				return dataEdges != null && !dataEdges.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__EXECUTION_EDGES:
				return executionEdges != null && !executionEdges.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__VARIABLES:
				return variables != null && !variables.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__VALUES:
				return values != null && !values.isEmpty();
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
		if (baseClass == GeneratesElements.class) {
			switch (derivedFeatureID) {
				case ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS: return ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS;
				case ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN: return ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == Scope.class) {
			switch (derivedFeatureID) {
				case ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS: return ModelPackage.SCOPE__DOMAIN_OBJECTS;
				case ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS: return ModelPackage.SCOPE__DOMAIN_VIEWS;
				case ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES: return ModelPackage.SCOPE__DOMAIN_INSTANCES;
				default: return -1;
			}
		}
		if (baseClass == ContainsConditions.class) {
			switch (derivedFeatureID) {
				case ModelPackage.COMPOSITE_OPERATION__CONDITIONS: return ModelPackage.CONTAINS_CONDITIONS__CONDITIONS;
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
		if (baseClass == GeneratesElements.class) {
			switch (baseFeatureID) {
				case ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS: return ModelPackage.COMPOSITE_OPERATION__GENERATED_ELEMENTS;
				case ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN: return ModelPackage.COMPOSITE_OPERATION__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == Scope.class) {
			switch (baseFeatureID) {
				case ModelPackage.SCOPE__DOMAIN_OBJECTS: return ModelPackage.COMPOSITE_OPERATION__DOMAIN_OBJECTS;
				case ModelPackage.SCOPE__DOMAIN_VIEWS: return ModelPackage.COMPOSITE_OPERATION__DOMAIN_VIEWS;
				case ModelPackage.SCOPE__DOMAIN_INSTANCES: return ModelPackage.COMPOSITE_OPERATION__DOMAIN_INSTANCES;
				default: return -1;
			}
		}
		if (baseClass == ContainsConditions.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_CONDITIONS__CONDITIONS: return ModelPackage.COMPOSITE_OPERATION__CONDITIONS;
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
		result.append(" (overridden: ");
		result.append(overridden);
		result.append(')');
		return result.toString();
	}

} //CompositeOperationImpl
