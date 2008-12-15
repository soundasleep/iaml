/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes.impl;

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
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.VisitorAgent;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.Session;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getGeneratedBy <em>Generated By</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#isIsGenerated <em>Is Generated</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getGeneratedElements <em>Generated Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#isOverridden <em>Overridden</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getDomainObjects <em>Domain Objects</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getDomainViews <em>Domain Views</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getDomainInstances <em>Domain Instances</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getOutEdges <em>Out Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getInEdges <em>In Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getSessions <em>Sessions</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getAgents <em>Agents</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.impl.SessionImpl#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionImpl extends EObjectImpl implements Session {
	/**
	 * The cached value of the '{@link #getGeneratedBy() <em>Generated By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedBy()
	 * @generated
	 * @ordered
	 */
	protected GeneratesElements generatedBy;

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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElement> children;

	/**
	 * The cached value of the '{@link #getSessions() <em>Sessions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessions()
	 * @generated
	 * @ordered
	 */
	protected EList<Session> sessions;

	/**
	 * The cached value of the '{@link #getAgents() <em>Agents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgents()
	 * @generated
	 * @ordered
	 */
	protected EList<VisitorAgent> agents;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElement> components;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScopesPackage.Literals.SESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratesElements getGeneratedBy() {
		if (generatedBy != null && generatedBy.eIsProxy()) {
			InternalEObject oldGeneratedBy = (InternalEObject)generatedBy;
			generatedBy = (GeneratesElements)eResolveProxy(oldGeneratedBy);
			if (generatedBy != oldGeneratedBy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopesPackage.SESSION__GENERATED_BY, oldGeneratedBy, generatedBy));
			}
		}
		return generatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratesElements basicGetGeneratedBy() {
		return generatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeneratedBy(GeneratesElements newGeneratedBy, NotificationChain msgs) {
		GeneratesElements oldGeneratedBy = generatedBy;
		generatedBy = newGeneratedBy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__GENERATED_BY, oldGeneratedBy, newGeneratedBy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratedBy(GeneratesElements newGeneratedBy) {
		if (newGeneratedBy != generatedBy) {
			NotificationChain msgs = null;
			if (generatedBy != null)
				msgs = ((InternalEObject)generatedBy).eInverseRemove(this, ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS, GeneratesElements.class, msgs);
			if (newGeneratedBy != null)
				msgs = ((InternalEObject)newGeneratedBy).eInverseAdd(this, ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS, GeneratesElements.class, msgs);
			msgs = basicSetGeneratedBy(newGeneratedBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__GENERATED_BY, newGeneratedBy, newGeneratedBy));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__IS_GENERATED, oldIsGenerated, isGenerated));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainObject> getDomainObjects() {
		if (domainObjects == null) {
			domainObjects = new EObjectContainmentEList<DomainObject>(DomainObject.class, this, ScopesPackage.SESSION__DOMAIN_OBJECTS);
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
			domainViews = new EObjectContainmentEList<DerivedView>(DerivedView.class, this, ScopesPackage.SESSION__DOMAIN_VIEWS);
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
			domainInstances = new EObjectContainmentEList<DomainObjectInstance>(DomainObjectInstance.class, this, ScopesPackage.SESSION__DOMAIN_INSTANCES);
		}
		return domainInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventTrigger> getEventTriggers() {
		if (eventTriggers == null) {
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, ScopesPackage.SESSION__EVENT_TRIGGERS);
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
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ScopesPackage.SESSION__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getOutEdges() {
		if (outEdges == null) {
			outEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ScopesPackage.SESSION__OUT_EDGES, ModelPackage.WIRE_EDGE__FROM);
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
			inEdges = new EObjectWithInverseResolvingEList<WireEdge>(WireEdge.class, this, ScopesPackage.SESSION__IN_EDGES, ModelPackage.WIRE_EDGE__TO);
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
			generatedElements = new EObjectWithInverseResolvingEList<GeneratedElement>(GeneratedElement.class, this, ScopesPackage.SESSION__GENERATED_ELEMENTS, ModelPackage.GENERATED_ELEMENT__GENERATED_BY);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScopesPackage.SESSION__OVERRIDDEN, oldOverridden, overridden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, ScopesPackage.SESSION__PROPERTIES);
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
			values = new EObjectContainmentEList<StaticValue>(StaticValue.class, this, ScopesPackage.SESSION__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<ApplicationElement>(ApplicationElement.class, this, ScopesPackage.SESSION__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Session> getSessions() {
		if (sessions == null) {
			sessions = new EObjectContainmentEList<Session>(Session.class, this, ScopesPackage.SESSION__SESSIONS);
		}
		return sessions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ScopesPackage.SESSION__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VisitorAgent> getAgents() {
		if (agents == null) {
			agents = new EObjectContainmentEList<VisitorAgent>(VisitorAgent.class, this, ScopesPackage.SESSION__AGENTS);
		}
		return agents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElement> getComponents() {
		if (components == null) {
			components = new EObjectContainmentEList<ApplicationElement>(ApplicationElement.class, this, ScopesPackage.SESSION__COMPONENTS);
		}
		return components;
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
			case ScopesPackage.SESSION__GENERATED_BY:
				if (generatedBy != null)
					msgs = ((InternalEObject)generatedBy).eInverseRemove(this, ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS, GeneratesElements.class, msgs);
				return basicSetGeneratedBy((GeneratesElements)otherEnd, msgs);
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedElements()).basicAdd(otherEnd, msgs);
			case ScopesPackage.SESSION__OUT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutEdges()).basicAdd(otherEnd, msgs);
			case ScopesPackage.SESSION__IN_EDGES:
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
			case ScopesPackage.SESSION__GENERATED_BY:
				return basicSetGeneratedBy(null, msgs);
			case ScopesPackage.SESSION__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				return ((InternalEList<?>)getGeneratedElements()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__DOMAIN_OBJECTS:
				return ((InternalEList<?>)getDomainObjects()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__DOMAIN_VIEWS:
				return ((InternalEList<?>)getDomainViews()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__DOMAIN_INSTANCES:
				return ((InternalEList<?>)getDomainInstances()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__OUT_EDGES:
				return ((InternalEList<?>)getOutEdges()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__IN_EDGES:
				return ((InternalEList<?>)getInEdges()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__SESSIONS:
				return ((InternalEList<?>)getSessions()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__AGENTS:
				return ((InternalEList<?>)getAgents()).basicRemove(otherEnd, msgs);
			case ScopesPackage.SESSION__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
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
			case ScopesPackage.SESSION__GENERATED_BY:
				if (resolve) return getGeneratedBy();
				return basicGetGeneratedBy();
			case ScopesPackage.SESSION__IS_GENERATED:
				return isIsGenerated() ? Boolean.TRUE : Boolean.FALSE;
			case ScopesPackage.SESSION__ID:
				return getId();
			case ScopesPackage.SESSION__NAME:
				return getName();
			case ScopesPackage.SESSION__OPERATIONS:
				return getOperations();
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				return getGeneratedElements();
			case ScopesPackage.SESSION__OVERRIDDEN:
				return isOverridden() ? Boolean.TRUE : Boolean.FALSE;
			case ScopesPackage.SESSION__DOMAIN_OBJECTS:
				return getDomainObjects();
			case ScopesPackage.SESSION__DOMAIN_VIEWS:
				return getDomainViews();
			case ScopesPackage.SESSION__DOMAIN_INSTANCES:
				return getDomainInstances();
			case ScopesPackage.SESSION__EVENT_TRIGGERS:
				return getEventTriggers();
			case ScopesPackage.SESSION__WIRES:
				return getWires();
			case ScopesPackage.SESSION__OUT_EDGES:
				return getOutEdges();
			case ScopesPackage.SESSION__IN_EDGES:
				return getInEdges();
			case ScopesPackage.SESSION__PROPERTIES:
				return getProperties();
			case ScopesPackage.SESSION__VALUES:
				return getValues();
			case ScopesPackage.SESSION__CHILDREN:
				return getChildren();
			case ScopesPackage.SESSION__SESSIONS:
				return getSessions();
			case ScopesPackage.SESSION__AGENTS:
				return getAgents();
			case ScopesPackage.SESSION__COMPONENTS:
				return getComponents();
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
			case ScopesPackage.SESSION__GENERATED_BY:
				setGeneratedBy((GeneratesElements)newValue);
				return;
			case ScopesPackage.SESSION__IS_GENERATED:
				setIsGenerated(((Boolean)newValue).booleanValue());
				return;
			case ScopesPackage.SESSION__ID:
				setId((String)newValue);
				return;
			case ScopesPackage.SESSION__NAME:
				setName((String)newValue);
				return;
			case ScopesPackage.SESSION__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				getGeneratedElements().addAll((Collection<? extends GeneratedElement>)newValue);
				return;
			case ScopesPackage.SESSION__OVERRIDDEN:
				setOverridden(((Boolean)newValue).booleanValue());
				return;
			case ScopesPackage.SESSION__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				getDomainObjects().addAll((Collection<? extends DomainObject>)newValue);
				return;
			case ScopesPackage.SESSION__DOMAIN_VIEWS:
				getDomainViews().clear();
				getDomainViews().addAll((Collection<? extends DerivedView>)newValue);
				return;
			case ScopesPackage.SESSION__DOMAIN_INSTANCES:
				getDomainInstances().clear();
				getDomainInstances().addAll((Collection<? extends DomainObjectInstance>)newValue);
				return;
			case ScopesPackage.SESSION__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case ScopesPackage.SESSION__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ScopesPackage.SESSION__OUT_EDGES:
				getOutEdges().clear();
				getOutEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ScopesPackage.SESSION__IN_EDGES:
				getInEdges().clear();
				getInEdges().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case ScopesPackage.SESSION__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case ScopesPackage.SESSION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends StaticValue>)newValue);
				return;
			case ScopesPackage.SESSION__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ApplicationElement>)newValue);
				return;
			case ScopesPackage.SESSION__SESSIONS:
				getSessions().clear();
				getSessions().addAll((Collection<? extends Session>)newValue);
				return;
			case ScopesPackage.SESSION__AGENTS:
				getAgents().clear();
				getAgents().addAll((Collection<? extends VisitorAgent>)newValue);
				return;
			case ScopesPackage.SESSION__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends ApplicationElement>)newValue);
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
			case ScopesPackage.SESSION__GENERATED_BY:
				setGeneratedBy((GeneratesElements)null);
				return;
			case ScopesPackage.SESSION__IS_GENERATED:
				setIsGenerated(IS_GENERATED_EDEFAULT);
				return;
			case ScopesPackage.SESSION__ID:
				setId(ID_EDEFAULT);
				return;
			case ScopesPackage.SESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScopesPackage.SESSION__OPERATIONS:
				getOperations().clear();
				return;
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				return;
			case ScopesPackage.SESSION__OVERRIDDEN:
				setOverridden(OVERRIDDEN_EDEFAULT);
				return;
			case ScopesPackage.SESSION__DOMAIN_OBJECTS:
				getDomainObjects().clear();
				return;
			case ScopesPackage.SESSION__DOMAIN_VIEWS:
				getDomainViews().clear();
				return;
			case ScopesPackage.SESSION__DOMAIN_INSTANCES:
				getDomainInstances().clear();
				return;
			case ScopesPackage.SESSION__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case ScopesPackage.SESSION__WIRES:
				getWires().clear();
				return;
			case ScopesPackage.SESSION__OUT_EDGES:
				getOutEdges().clear();
				return;
			case ScopesPackage.SESSION__IN_EDGES:
				getInEdges().clear();
				return;
			case ScopesPackage.SESSION__PROPERTIES:
				getProperties().clear();
				return;
			case ScopesPackage.SESSION__VALUES:
				getValues().clear();
				return;
			case ScopesPackage.SESSION__CHILDREN:
				getChildren().clear();
				return;
			case ScopesPackage.SESSION__SESSIONS:
				getSessions().clear();
				return;
			case ScopesPackage.SESSION__AGENTS:
				getAgents().clear();
				return;
			case ScopesPackage.SESSION__COMPONENTS:
				getComponents().clear();
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
			case ScopesPackage.SESSION__GENERATED_BY:
				return generatedBy != null;
			case ScopesPackage.SESSION__IS_GENERATED:
				return isGenerated != IS_GENERATED_EDEFAULT;
			case ScopesPackage.SESSION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ScopesPackage.SESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScopesPackage.SESSION__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ScopesPackage.SESSION__GENERATED_ELEMENTS:
				return generatedElements != null && !generatedElements.isEmpty();
			case ScopesPackage.SESSION__OVERRIDDEN:
				return overridden != OVERRIDDEN_EDEFAULT;
			case ScopesPackage.SESSION__DOMAIN_OBJECTS:
				return domainObjects != null && !domainObjects.isEmpty();
			case ScopesPackage.SESSION__DOMAIN_VIEWS:
				return domainViews != null && !domainViews.isEmpty();
			case ScopesPackage.SESSION__DOMAIN_INSTANCES:
				return domainInstances != null && !domainInstances.isEmpty();
			case ScopesPackage.SESSION__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case ScopesPackage.SESSION__WIRES:
				return wires != null && !wires.isEmpty();
			case ScopesPackage.SESSION__OUT_EDGES:
				return outEdges != null && !outEdges.isEmpty();
			case ScopesPackage.SESSION__IN_EDGES:
				return inEdges != null && !inEdges.isEmpty();
			case ScopesPackage.SESSION__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ScopesPackage.SESSION__VALUES:
				return values != null && !values.isEmpty();
			case ScopesPackage.SESSION__CHILDREN:
				return children != null && !children.isEmpty();
			case ScopesPackage.SESSION__SESSIONS:
				return sessions != null && !sessions.isEmpty();
			case ScopesPackage.SESSION__AGENTS:
				return agents != null && !agents.isEmpty();
			case ScopesPackage.SESSION__COMPONENTS:
				return components != null && !components.isEmpty();
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
				case ScopesPackage.SESSION__OPERATIONS: return ModelPackage.CONTAINS_OPERATIONS__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__GENERATED_ELEMENTS: return ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS;
				case ScopesPackage.SESSION__OVERRIDDEN: return ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == Scope.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__DOMAIN_OBJECTS: return ModelPackage.SCOPE__DOMAIN_OBJECTS;
				case ScopesPackage.SESSION__DOMAIN_VIEWS: return ModelPackage.SCOPE__DOMAIN_VIEWS;
				case ScopesPackage.SESSION__DOMAIN_INSTANCES: return ModelPackage.SCOPE__DOMAIN_INSTANCES;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__EVENT_TRIGGERS: return ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
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
				case ScopesPackage.SESSION__OUT_EDGES: return ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__IN_EDGES: return ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ApplicationElement.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__PROPERTIES: return ModelPackage.APPLICATION_ELEMENT__PROPERTIES;
				case ScopesPackage.SESSION__VALUES: return ModelPackage.APPLICATION_ELEMENT__VALUES;
				default: return -1;
			}
		}
		if (baseClass == ApplicationElementContainer.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__CHILDREN: return ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == VisibleThing.class) {
			switch (derivedFeatureID) {
				case ScopesPackage.SESSION__SESSIONS: return ModelPackage.VISIBLE_THING__SESSIONS;
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
				case ModelPackage.CONTAINS_OPERATIONS__OPERATIONS: return ScopesPackage.SESSION__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (baseFeatureID) {
				case ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS: return ScopesPackage.SESSION__GENERATED_ELEMENTS;
				case ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN: return ScopesPackage.SESSION__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == Scope.class) {
			switch (baseFeatureID) {
				case ModelPackage.SCOPE__DOMAIN_OBJECTS: return ScopesPackage.SESSION__DOMAIN_OBJECTS;
				case ModelPackage.SCOPE__DOMAIN_VIEWS: return ScopesPackage.SESSION__DOMAIN_VIEWS;
				case ModelPackage.SCOPE__DOMAIN_INSTANCES: return ScopesPackage.SESSION__DOMAIN_INSTANCES;
				default: return -1;
			}
		}
		if (baseClass == ContainsEventTriggers.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS: return ScopesPackage.SESSION__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return ScopesPackage.SESSION__WIRES;
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
				case ModelPackage.WIRE_EDGES_SOURCE__OUT_EDGES: return ScopesPackage.SESSION__OUT_EDGES;
				default: return -1;
			}
		}
		if (baseClass == WireEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.WIRE_EDGE_DESTINATION__IN_EDGES: return ScopesPackage.SESSION__IN_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ApplicationElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT__PROPERTIES: return ScopesPackage.SESSION__PROPERTIES;
				case ModelPackage.APPLICATION_ELEMENT__VALUES: return ScopesPackage.SESSION__VALUES;
				default: return -1;
			}
		}
		if (baseClass == ApplicationElementContainer.class) {
			switch (baseFeatureID) {
				case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN: return ScopesPackage.SESSION__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == VisibleThing.class) {
			switch (baseFeatureID) {
				case ModelPackage.VISIBLE_THING__SESSIONS: return ScopesPackage.SESSION__SESSIONS;
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
		result.append(", name: ");
		result.append(name);
		result.append(", overridden: ");
		result.append(overridden);
		result.append(')');
		return result.toString();
	}

} //SessionImpl
