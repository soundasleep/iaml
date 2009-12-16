/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMF Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#isId <em>Id</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getContainingType <em>Containing Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl#getDefaultLiteral <em>Default Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EMFAttributeImpl extends EObjectImpl implements EMFAttribute {
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
	 * The default value of the '{@link #isId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isId()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isId()
	 * @generated
	 * @ordered
	 */
	protected boolean id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultLiteral() <em>Default Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultLiteral()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_LITERAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultLiteral() <em>Default Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultLiteral()
	 * @generated
	 * @ordered
	 */
	protected String defaultLiteral = DEFAULT_LITERAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMFAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.EMF_ATTRIBUTE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(boolean newId) {
		boolean oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__LOWER_BOUND, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__UPPER_BOUND, oldUpperBound, upperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFClass getContainingType() {
		if (eContainerFeatureID() != ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE) return null;
		return (EMFClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingType(EMFClass newContainingType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingType, ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingType(EMFClass newContainingType) {
		if (newContainingType != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE && newContainingType != null)) {
			if (EcoreUtil.isAncestor(this, newContainingType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingType != null)
				msgs = ((InternalEObject)newContainingType).eInverseAdd(this, ModeldocPackage.EMF_CLASS__ATTRIBUTES, EMFClass.class, msgs);
			msgs = basicSetContainingType(newContainingType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE, newContainingType, newContainingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultLiteral() {
		return defaultLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultLiteral(String newDefaultLiteral) {
		String oldDefaultLiteral = defaultLiteral;
		defaultLiteral = newDefaultLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_ATTRIBUTE__DEFAULT_LITERAL, oldDefaultLiteral, defaultLiteral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingType((EMFClass)otherEnd, msgs);
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
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				return basicSetContainingType(null, msgs);
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
		switch (eContainerFeatureID()) {
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.EMF_CLASS__ATTRIBUTES, EMFClass.class, msgs);
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
			case ModeldocPackage.EMF_ATTRIBUTE__NAME:
				return getName();
			case ModeldocPackage.EMF_ATTRIBUTE__ID:
				return isId();
			case ModeldocPackage.EMF_ATTRIBUTE__LOWER_BOUND:
				return getLowerBound();
			case ModeldocPackage.EMF_ATTRIBUTE__UPPER_BOUND:
				return getUpperBound();
			case ModeldocPackage.EMF_ATTRIBUTE__TYPE:
				return getType();
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				return getContainingType();
			case ModeldocPackage.EMF_ATTRIBUTE__DEFAULT_LITERAL:
				return getDefaultLiteral();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModeldocPackage.EMF_ATTRIBUTE__NAME:
				setName((String)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__ID:
				setId((Boolean)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__TYPE:
				setType((String)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				setContainingType((EMFClass)newValue);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__DEFAULT_LITERAL:
				setDefaultLiteral((String)newValue);
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
			case ModeldocPackage.EMF_ATTRIBUTE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__ID:
				setId(ID_EDEFAULT);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				setContainingType((EMFClass)null);
				return;
			case ModeldocPackage.EMF_ATTRIBUTE__DEFAULT_LITERAL:
				setDefaultLiteral(DEFAULT_LITERAL_EDEFAULT);
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
			case ModeldocPackage.EMF_ATTRIBUTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModeldocPackage.EMF_ATTRIBUTE__ID:
				return id != ID_EDEFAULT;
			case ModeldocPackage.EMF_ATTRIBUTE__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case ModeldocPackage.EMF_ATTRIBUTE__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case ModeldocPackage.EMF_ATTRIBUTE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE:
				return getContainingType() != null;
			case ModeldocPackage.EMF_ATTRIBUTE__DEFAULT_LITERAL:
				return DEFAULT_LITERAL_EDEFAULT == null ? defaultLiteral != null : !DEFAULT_LITERAL_EDEFAULT.equals(defaultLiteral);
		}
		return super.eIsSet(featureID);
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
		result.append(", id: ");
		result.append(id);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(", type: ");
		result.append(type);
		result.append(", defaultLiteral: ");
		result.append(defaultLiteral);
		result.append(')');
		return result.toString();
	}

} //EMFAttributeImpl
