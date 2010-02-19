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
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getGeneratedBy <em>Generated By</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#isIsGenerated <em>Is Generated</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getGeneratedRule <em>Generated Rule</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getExtendsEdges <em>Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getRequiresEdges <em>Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getProvidesEdges <em>Provides Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getConstraintEdges <em>Constraint Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getOutEdges <em>Out Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getInEdges <em>In Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getGeneratedElements <em>Generated Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#isOverridden <em>Overridden</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getConditions <em>Conditions</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationElementImpl extends EObjectImpl implements ApplicationElement {
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
	 * The cached value of the '{@link #getGeneratedBy() <em>Generated By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratesElements> generatedBy;

	/**
	 * The default value of the '{@link #isIsGenerated() <em>Is Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGenerated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_GENERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsGenerated() <em>Is Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGenerated()
	 * @generated
	 * @ordered
	 */
	protected boolean isGenerated = IS_GENERATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratedRule() <em>Generated Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedRule()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATED_RULE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedRule() <em>Generated Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedRule()
	 * @generated
	 * @ordered
	 */
	protected String generatedRule = GENERATED_RULE_EDEFAULT;

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
	 * The cached value of the '{@link #getRequiresEdges() <em>Requires Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiresEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiresEdge> requiresEdges;

	/**
	 * The cached value of the '{@link #getProvidesEdges() <em>Provides Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidesEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidesEdge> providesEdges;

	/**
	 * The cached value of the '{@link #getConstraintEdges() <em>Constraint Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintEdge> constraintEdges;

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
	 * The cached value of the '{@link #getConditions() <em>Conditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Condition> conditions;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElementProperty> properties;

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
	protected ApplicationElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.APPLICATION_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.APPLICATION_ELEMENT__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratesElements> getGeneratedBy() {
		if (generatedBy == null) {
			generatedBy = new EObjectWithInverseResolvingEList.ManyInverse<GeneratesElements>(GeneratesElements.class, this, ModelPackage.APPLICATION_ELEMENT__GENERATED_BY, ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS);
		}
		return generatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsGenerated() {
		return isGenerated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsGenerated(boolean newIsGenerated) {
		boolean oldIsGenerated = isGenerated;
		isGenerated = newIsGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT__IS_GENERATED, oldIsGenerated, isGenerated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGeneratedRule() {
		return generatedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratedRule(String newGeneratedRule) {
		String oldGeneratedRule = generatedRule;
		generatedRule = newGeneratedRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE, oldGeneratedRule, generatedRule));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventTrigger> getEventTriggers() {
		if (eventTriggers == null) {
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS);
		}
		return eventTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT__WIRES);
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
			parameterEdges = new EObjectContainmentEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES);
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
			extendsEdges = new EObjectContainmentEList<ExtendsEdge>(ExtendsEdge.class, this, ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES);
		}
		return extendsEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiresEdge> getRequiresEdges() {
		if (requiresEdges == null) {
			requiresEdges = new EObjectContainmentEList<RequiresEdge>(RequiresEdge.class, this, ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES);
		}
		return requiresEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidesEdge> getProvidesEdges() {
		if (providesEdges == null) {
			providesEdges = new EObjectContainmentEList<ProvidesEdge>(ProvidesEdge.class, this, ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES);
		}
		return providesEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConstraintEdge> getConstraintEdges() {
		if (constraintEdges == null) {
			constraintEdges = new EObjectContainmentEList<ConstraintEdge>(ConstraintEdge.class, this, ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES);
		}
		return constraintEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getOutEdges() {
		if (outEdges == null) {
			outEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT__OUT_EDGES, ModelPackage.WIRE_EDGE__FROM);
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
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
		}
		return inEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratedElement> getGeneratedElements() {
		if (generatedElements == null) {
			generatedElements = new EObjectWithInverseResolvingEList.ManyInverse<GeneratedElement>(GeneratedElement.class, this, ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS, ModelPackage.GENERATED_ELEMENT__GENERATED_BY);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN, oldOverridden, overridden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Condition> getConditions() {
		if (conditions == null) {
			conditions = new EObjectContainmentEList<Condition>(Condition.class, this, ModelPackage.APPLICATION_ELEMENT__CONDITIONS);
		}
		return conditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, ModelPackage.APPLICATION_ELEMENT__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticValue> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<StaticValue>(StaticValue.class, this, ModelPackage.APPLICATION_ELEMENT__VALUES);
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
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedBy()).basicAdd(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
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
			case ModelPackage.APPLICATION_ELEMENT__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				return ((InternalEList<?>)getGeneratedBy()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES:
				return ((InternalEList<?>)getParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES:
				return ((InternalEList<?>)getExtendsEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES:
				return ((InternalEList<?>)getRequiresEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES:
				return ((InternalEList<?>)getProvidesEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES:
				return ((InternalEList<?>)getConstraintEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				return ((InternalEList<?>)getOutEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				return ((InternalEList<?>)getInEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
				return ((InternalEList<?>)getGeneratedElements()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__CONDITIONS:
				return ((InternalEList<?>)getConditions()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT__VALUES:
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
			case ModelPackage.APPLICATION_ELEMENT__OPERATIONS:
				return getOperations();
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				return getGeneratedBy();
			case ModelPackage.APPLICATION_ELEMENT__IS_GENERATED:
				return isIsGenerated();
			case ModelPackage.APPLICATION_ELEMENT__ID:
				return getId();
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE:
				return getGeneratedRule();
			case ModelPackage.APPLICATION_ELEMENT__NAME:
				return getName();
			case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS:
				return getEventTriggers();
			case ModelPackage.APPLICATION_ELEMENT__WIRES:
				return getWires();
			case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES:
				return getParameterEdges();
			case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES:
				return getExtendsEdges();
			case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES:
				return getRequiresEdges();
			case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES:
				return getProvidesEdges();
			case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES:
				return getConstraintEdges();
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				return getOutEdges();
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				return getInEdges();
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
				return getGeneratedElements();
			case ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN:
				return isOverridden();
			case ModelPackage.APPLICATION_ELEMENT__CONDITIONS:
				return getConditions();
			case ModelPackage.APPLICATION_ELEMENT__PROPERTIES:
				return getProperties();
			case ModelPackage.APPLICATION_ELEMENT__VALUES:
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
			case ModelPackage.APPLICATION_ELEMENT__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				getGeneratedBy().clear();
				getGeneratedBy().addAll((Collection<? extends GeneratesElements>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__IS_GENERATED:
				setIsGenerated((Boolean)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__ID:
				setId((String)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE:
				setGeneratedRule((String)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES:
				getParameterEdges().clear();
				getParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES:
				getExtendsEdges().clear();
				getExtendsEdges().addAll((Collection<? extends ExtendsEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES:
				getRequiresEdges().clear();
				getRequiresEdges().addAll((Collection<? extends RequiresEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES:
				getProvidesEdges().clear();
				getProvidesEdges().addAll((Collection<? extends ProvidesEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				getConstraintEdges().addAll((Collection<? extends ConstraintEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				getOutEdges().clear();
				getOutEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				getGeneratedElements().addAll((Collection<? extends GeneratedElement>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN:
				setOverridden((Boolean)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__CONDITIONS:
				getConditions().clear();
				getConditions().addAll((Collection<? extends Condition>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT__VALUES:
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
			case ModelPackage.APPLICATION_ELEMENT__OPERATIONS:
				getOperations().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				getGeneratedBy().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__IS_GENERATED:
				setIsGenerated(IS_GENERATED_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE:
				setGeneratedRule(GENERATED_RULE_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__WIRES:
				getWires().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES:
				getParameterEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES:
				getExtendsEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES:
				getRequiresEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES:
				getProvidesEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES:
				getConstraintEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				getOutEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				getInEdges().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN:
				setOverridden(OVERRIDDEN_EDEFAULT);
				return;
			case ModelPackage.APPLICATION_ELEMENT__CONDITIONS:
				getConditions().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__PROPERTIES:
				getProperties().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT__VALUES:
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
			case ModelPackage.APPLICATION_ELEMENT__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY:
				return generatedBy != null && !generatedBy.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__IS_GENERATED:
				return isGenerated != IS_GENERATED_EDEFAULT;
			case ModelPackage.APPLICATION_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE:
				return GENERATED_RULE_EDEFAULT == null ? generatedRule != null : !GENERATED_RULE_EDEFAULT.equals(generatedRule);
			case ModelPackage.APPLICATION_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__WIRES:
				return wires != null && !wires.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES:
				return parameterEdges != null && !parameterEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES:
				return extendsEdges != null && !extendsEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES:
				return requiresEdges != null && !requiresEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES:
				return providesEdges != null && !providesEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES:
				return constraintEdges != null && !constraintEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES:
				return outEdges != null && !outEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS:
				return generatedElements != null && !generatedElements.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN:
				return overridden != OVERRIDDEN_EDEFAULT;
			case ModelPackage.APPLICATION_ELEMENT__CONDITIONS:
				return conditions != null && !conditions.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT__VALUES:
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
		if (baseClass == GeneratedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__GENERATED_BY: return ModelPackage.GENERATED_ELEMENT__GENERATED_BY;
				case ModelPackage.APPLICATION_ELEMENT__IS_GENERATED: return ModelPackage.GENERATED_ELEMENT__IS_GENERATED;
				case ModelPackage.APPLICATION_ELEMENT__ID: return ModelPackage.GENERATED_ELEMENT__ID;
				case ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE: return ModelPackage.GENERATED_ELEMENT__GENERATED_RULE;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__NAME: return ModelPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS: return ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				case ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES: return ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES;
				case ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES: return ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES;
				case ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES: return ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES;
				case ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES: return ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES;
				case ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES: return ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES;
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
				case ModelPackage.APPLICATION_ELEMENT__OUT_EDGES: return ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS: return ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS;
				case ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN: return ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == ContainsConditions.class) {
			switch (derivedFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__CONDITIONS: return ModelPackage.CONTAINS_CONDITIONS__CONDITIONS;
				default: return -1;
			}
		}
		if (baseClass == CanBeSynced.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == GeneratedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.GENERATED_ELEMENT__GENERATED_BY: return ModelPackage.APPLICATION_ELEMENT__GENERATED_BY;
				case ModelPackage.GENERATED_ELEMENT__IS_GENERATED: return ModelPackage.APPLICATION_ELEMENT__IS_GENERATED;
				case ModelPackage.GENERATED_ELEMENT__ID: return ModelPackage.APPLICATION_ELEMENT__ID;
				case ModelPackage.GENERATED_ELEMENT__GENERATED_RULE: return ModelPackage.APPLICATION_ELEMENT__GENERATED_RULE;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.NAMED_ELEMENT__NAME: return ModelPackage.APPLICATION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS: return ModelPackage.APPLICATION_ELEMENT__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return ModelPackage.APPLICATION_ELEMENT__WIRES;
				case ModelPackage.CONTAINS_WIRES__PARAMETER_EDGES: return ModelPackage.APPLICATION_ELEMENT__PARAMETER_EDGES;
				case ModelPackage.CONTAINS_WIRES__EXTENDS_EDGES: return ModelPackage.APPLICATION_ELEMENT__EXTENDS_EDGES;
				case ModelPackage.CONTAINS_WIRES__REQUIRES_EDGES: return ModelPackage.APPLICATION_ELEMENT__REQUIRES_EDGES;
				case ModelPackage.CONTAINS_WIRES__PROVIDES_EDGES: return ModelPackage.APPLICATION_ELEMENT__PROVIDES_EDGES;
				case ModelPackage.CONTAINS_WIRES__CONSTRAINT_EDGES: return ModelPackage.APPLICATION_ELEMENT__CONSTRAINT_EDGES;
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
				case ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES: return ModelPackage.APPLICATION_ELEMENT__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return ModelPackage.APPLICATION_ELEMENT__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (baseFeatureID) {
				case ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS: return ModelPackage.APPLICATION_ELEMENT__GENERATED_ELEMENTS;
				case ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN: return ModelPackage.APPLICATION_ELEMENT__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == ContainsConditions.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_CONDITIONS__CONDITIONS: return ModelPackage.APPLICATION_ELEMENT__CONDITIONS;
				default: return -1;
			}
		}
		if (baseClass == CanBeSynced.class) {
			switch (baseFeatureID) {
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
		result.append(" (isGenerated: ");
		result.append(isGenerated);
		result.append(", id: ");
		result.append(id);
		result.append(", generatedRule: ");
		result.append(generatedRule);
		result.append(", name: ");
		result.append(name);
		result.append(", overridden: ");
		result.append(overridden);
		result.append(')');
		return result.toString();
	}

} //ApplicationElementImpl
