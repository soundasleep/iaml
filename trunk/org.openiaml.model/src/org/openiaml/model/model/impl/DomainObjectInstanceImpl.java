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
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.Editable;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Object Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getInParameterEdges <em>In Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getOutParameterEdges <em>Out Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getOnChange <em>On Change</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getStrQuery <em>Str Query</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#isAutosave <em>Autosave</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getOnIterate <em>On Iterate</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getNext <em>Next</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getReset <em>Reset</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getSkip <em>Skip</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getHasPrevious <em>Has Previous</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getHasNext <em>Has Next</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getEmpty <em>Empty</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getResults <em>Results</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectInstanceImpl#getJump <em>Jump</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainObjectInstanceImpl extends ApplicationElementImpl implements DomainObjectInstance {
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
	 * The cached value of the '{@link #getOutParameterEdges() <em>Out Parameter Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameterEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterEdge> outParameterEdges;

	/**
	 * The cached value of the '{@link #getOnChange() <em>On Change</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnChange()
	 * @generated
	 * @ordered
	 */
	protected EventTrigger onChange;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainAttributeInstance> attributes;

	/**
	 * The default value of the '{@link #getStrQuery() <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrQuery()
	 * @generated
	 * @ordered
	 */
	protected static final String STR_QUERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStrQuery() <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrQuery()
	 * @generated
	 * @ordered
	 */
	protected String strQuery = STR_QUERY_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutosave() <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutosave()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTOSAVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutosave() <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutosave()
	 * @generated
	 * @ordered
	 */
	protected boolean autosave = AUTOSAVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOnIterate() <em>On Iterate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnIterate()
	 * @generated
	 * @ordered
	 */
	protected EventTrigger onIterate;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected Operation previous;

	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected Operation next;

	/**
	 * The cached value of the '{@link #getReset() <em>Reset</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReset()
	 * @generated
	 * @ordered
	 */
	protected Operation reset;

	/**
	 * The cached value of the '{@link #getSkip() <em>Skip</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkip()
	 * @generated
	 * @ordered
	 */
	protected Operation skip;

	/**
	 * The cached value of the '{@link #getHasPrevious() <em>Has Previous</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasPrevious()
	 * @generated
	 * @ordered
	 */
	protected Condition hasPrevious;

	/**
	 * The cached value of the '{@link #getHasNext() <em>Has Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasNext()
	 * @generated
	 * @ordered
	 */
	protected Condition hasNext;

	/**
	 * The cached value of the '{@link #getEmpty() <em>Empty</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmpty()
	 * @generated
	 * @ordered
	 */
	protected Condition empty;

	/**
	 * The cached value of the '{@link #getResults() <em>Results</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResults()
	 * @generated
	 * @ordered
	 */
	protected Property results;

	/**
	 * The cached value of the '{@link #getJump() <em>Jump</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJump()
	 * @generated
	 * @ordered
	 */
	protected Operation jump;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainObjectInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOMAIN_OBJECT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getInParameterEdges() {
		if (inParameterEdges == null) {
			inParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__TO);
		}
		return inParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterEdge> getOutParameterEdges() {
		if (outParameterEdges == null) {
			outParameterEdges = new EObjectWithInverseResolvingEList<ParameterEdge>(ParameterEdge.class, this, ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES, WiresPackage.PARAMETER_EDGE__FROM);
		}
		return outParameterEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventTrigger getOnChange() {
		return onChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnChange(EventTrigger newOnChange, NotificationChain msgs) {
		EventTrigger oldOnChange = onChange;
		onChange = newOnChange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE, oldOnChange, newOnChange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnChange(EventTrigger newOnChange) {
		if (newOnChange != onChange) {
			NotificationChain msgs = null;
			if (onChange != null)
				msgs = ((InternalEObject)onChange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE, null, msgs);
			if (newOnChange != null)
				msgs = ((InternalEObject)newOnChange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE, null, msgs);
			msgs = basicSetOnChange(newOnChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE, newOnChange, newOnChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainAttributeInstance> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<DomainAttributeInstance>(DomainAttributeInstance.class, this, ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStrQuery() {
		return strQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrQuery(String newStrQuery) {
		String oldStrQuery = strQuery;
		strQuery = newStrQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__STR_QUERY, oldStrQuery, strQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutosave() {
		return autosave;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutosave(boolean newAutosave) {
		boolean oldAutosave = autosave;
		autosave = newAutosave;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__AUTOSAVE, oldAutosave, autosave));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventTrigger getOnIterate() {
		return onIterate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnIterate(EventTrigger newOnIterate, NotificationChain msgs) {
		EventTrigger oldOnIterate = onIterate;
		onIterate = newOnIterate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE, oldOnIterate, newOnIterate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnIterate(EventTrigger newOnIterate) {
		if (newOnIterate != onIterate) {
			NotificationChain msgs = null;
			if (onIterate != null)
				msgs = ((InternalEObject)onIterate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE, null, msgs);
			if (newOnIterate != null)
				msgs = ((InternalEObject)newOnIterate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE, null, msgs);
			msgs = basicSetOnIterate(newOnIterate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE, newOnIterate, newOnIterate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getPrevious() {
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrevious(Operation newPrevious, NotificationChain msgs) {
		Operation oldPrevious = previous;
		previous = newPrevious;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS, oldPrevious, newPrevious);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrevious(Operation newPrevious) {
		if (newPrevious != previous) {
			NotificationChain msgs = null;
			if (previous != null)
				msgs = ((InternalEObject)previous).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS, null, msgs);
			if (newPrevious != null)
				msgs = ((InternalEObject)newPrevious).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS, null, msgs);
			msgs = basicSetPrevious(newPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS, newPrevious, newPrevious));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getNext() {
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNext(Operation newNext, NotificationChain msgs) {
		Operation oldNext = next;
		next = newNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT, oldNext, newNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNext(Operation newNext) {
		if (newNext != next) {
			NotificationChain msgs = null;
			if (next != null)
				msgs = ((InternalEObject)next).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT, null, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT, null, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT, newNext, newNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getReset() {
		return reset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReset(Operation newReset, NotificationChain msgs) {
		Operation oldReset = reset;
		reset = newReset;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET, oldReset, newReset);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReset(Operation newReset) {
		if (newReset != reset) {
			NotificationChain msgs = null;
			if (reset != null)
				msgs = ((InternalEObject)reset).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET, null, msgs);
			if (newReset != null)
				msgs = ((InternalEObject)newReset).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET, null, msgs);
			msgs = basicSetReset(newReset, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET, newReset, newReset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getSkip() {
		return skip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSkip(Operation newSkip, NotificationChain msgs) {
		Operation oldSkip = skip;
		skip = newSkip;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP, oldSkip, newSkip);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkip(Operation newSkip) {
		if (newSkip != skip) {
			NotificationChain msgs = null;
			if (skip != null)
				msgs = ((InternalEObject)skip).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP, null, msgs);
			if (newSkip != null)
				msgs = ((InternalEObject)newSkip).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP, null, msgs);
			msgs = basicSetSkip(newSkip, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP, newSkip, newSkip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getHasPrevious() {
		return hasPrevious;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasPrevious(Condition newHasPrevious, NotificationChain msgs) {
		Condition oldHasPrevious = hasPrevious;
		hasPrevious = newHasPrevious;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS, oldHasPrevious, newHasPrevious);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasPrevious(Condition newHasPrevious) {
		if (newHasPrevious != hasPrevious) {
			NotificationChain msgs = null;
			if (hasPrevious != null)
				msgs = ((InternalEObject)hasPrevious).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS, null, msgs);
			if (newHasPrevious != null)
				msgs = ((InternalEObject)newHasPrevious).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS, null, msgs);
			msgs = basicSetHasPrevious(newHasPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS, newHasPrevious, newHasPrevious));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getHasNext() {
		return hasNext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasNext(Condition newHasNext, NotificationChain msgs) {
		Condition oldHasNext = hasNext;
		hasNext = newHasNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT, oldHasNext, newHasNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasNext(Condition newHasNext) {
		if (newHasNext != hasNext) {
			NotificationChain msgs = null;
			if (hasNext != null)
				msgs = ((InternalEObject)hasNext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT, null, msgs);
			if (newHasNext != null)
				msgs = ((InternalEObject)newHasNext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT, null, msgs);
			msgs = basicSetHasNext(newHasNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT, newHasNext, newHasNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getEmpty() {
		return empty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEmpty(Condition newEmpty, NotificationChain msgs) {
		Condition oldEmpty = empty;
		empty = newEmpty;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY, oldEmpty, newEmpty);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmpty(Condition newEmpty) {
		if (newEmpty != empty) {
			NotificationChain msgs = null;
			if (empty != null)
				msgs = ((InternalEObject)empty).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY, null, msgs);
			if (newEmpty != null)
				msgs = ((InternalEObject)newEmpty).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY, null, msgs);
			msgs = basicSetEmpty(newEmpty, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY, newEmpty, newEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getResults() {
		return results;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResults(Property newResults, NotificationChain msgs) {
		Property oldResults = results;
		results = newResults;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS, oldResults, newResults);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResults(Property newResults) {
		if (newResults != results) {
			NotificationChain msgs = null;
			if (results != null)
				msgs = ((InternalEObject)results).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS, null, msgs);
			if (newResults != null)
				msgs = ((InternalEObject)newResults).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS, null, msgs);
			msgs = basicSetResults(newResults, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS, newResults, newResults));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getJump() {
		return jump;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJump(Operation newJump, NotificationChain msgs) {
		Operation oldJump = jump;
		jump = newJump;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP, oldJump, newJump);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJump(Operation newJump) {
		if (newJump != jump) {
			NotificationChain msgs = null;
			if (jump != null)
				msgs = ((InternalEObject)jump).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP, null, msgs);
			if (newJump != null)
				msgs = ((InternalEObject)newJump).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP, null, msgs);
			msgs = basicSetJump(newJump, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP, newJump, newJump));
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInParameterEdges()).basicAdd(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutParameterEdges()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				return ((InternalEList<?>)getInParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				return ((InternalEList<?>)getOutParameterEdges()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE:
				return basicSetOnChange(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE:
				return basicSetOnIterate(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS:
				return basicSetPrevious(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT:
				return basicSetNext(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET:
				return basicSetReset(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP:
				return basicSetSkip(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS:
				return basicSetHasPrevious(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT:
				return basicSetHasNext(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY:
				return basicSetEmpty(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS:
				return basicSetResults(null, msgs);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP:
				return basicSetJump(null, msgs);
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				return getInParameterEdges();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				return getOutParameterEdges();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE:
				return getOnChange();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__STR_QUERY:
				return getStrQuery();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__AUTOSAVE:
				return isAutosave();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE:
				return getOnIterate();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS:
				return getPrevious();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT:
				return getNext();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET:
				return getReset();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP:
				return getSkip();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS:
				return getHasPrevious();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT:
				return getHasNext();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY:
				return getEmpty();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS:
				return getResults();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP:
				return getJump();
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
				getInParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				getOutParameterEdges().addAll((Collection<? extends ParameterEdge>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE:
				setOnChange((EventTrigger)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends DomainAttributeInstance>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__STR_QUERY:
				setStrQuery((String)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__AUTOSAVE:
				setAutosave((Boolean)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE:
				setOnIterate((EventTrigger)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS:
				setPrevious((Operation)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT:
				setNext((Operation)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET:
				setReset((Operation)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP:
				setSkip((Operation)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS:
				setHasPrevious((Condition)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT:
				setHasNext((Condition)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY:
				setEmpty((Condition)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS:
				setResults((Property)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP:
				setJump((Operation)newValue);
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				getInParameterEdges().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				getOutParameterEdges().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE:
				setOnChange((EventTrigger)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__STR_QUERY:
				setStrQuery(STR_QUERY_EDEFAULT);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__AUTOSAVE:
				setAutosave(AUTOSAVE_EDEFAULT);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE:
				setOnIterate((EventTrigger)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS:
				setPrevious((Operation)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT:
				setNext((Operation)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET:
				setReset((Operation)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP:
				setSkip((Operation)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS:
				setHasPrevious((Condition)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT:
				setHasNext((Condition)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY:
				setEmpty((Condition)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS:
				setResults((Property)null);
				return;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP:
				setJump((Operation)null);
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
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES:
				return inParameterEdges != null && !inParameterEdges.isEmpty();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES:
				return outParameterEdges != null && !outParameterEdges.isEmpty();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE:
				return onChange != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__STR_QUERY:
				return STR_QUERY_EDEFAULT == null ? strQuery != null : !STR_QUERY_EDEFAULT.equals(strQuery);
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__AUTOSAVE:
				return autosave != AUTOSAVE_EDEFAULT;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_ITERATE:
				return onIterate != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__PREVIOUS:
				return previous != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__NEXT:
				return next != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESET:
				return reset != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__SKIP:
				return skip != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_PREVIOUS:
				return hasPrevious != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__HAS_NEXT:
				return hasNext != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__EMPTY:
				return empty != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__RESULTS:
				return results != null;
			case ModelPackage.DOMAIN_OBJECT_INSTANCE__JUMP:
				return jump != null;
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
				case ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgesSource.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES: return WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE: return ModelPackage.EDITABLE__ON_CHANGE;
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
				case WiresPackage.PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES: return ModelPackage.DOMAIN_OBJECT_INSTANCE__IN_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == ParameterEdgesSource.class) {
			switch (baseFeatureID) {
				case WiresPackage.PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES: return ModelPackage.DOMAIN_OBJECT_INSTANCE__OUT_PARAMETER_EDGES;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (baseFeatureID) {
				case ModelPackage.EDITABLE__ON_CHANGE: return ModelPackage.DOMAIN_OBJECT_INSTANCE__ON_CHANGE;
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
		result.append(" (strQuery: ");
		result.append(strQuery);
		result.append(", autosave: ");
		result.append(autosave);
		result.append(')');
		return result.toString();
	}

} //DomainObjectInstanceImpl
