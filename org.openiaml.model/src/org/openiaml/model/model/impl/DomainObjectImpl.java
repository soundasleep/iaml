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
import org.eclipse.emf.ecore.util.InternalEList;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.DomainObjectImpl#getDomainObjectWires <em>Domain Object Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainObjectImpl extends ApplicationElementImpl implements DomainObject {
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
	 * The cached value of the '{@link #getDomainObjectWires() <em>Domain Object Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainObjectWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> domainObjectWires;

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
	public EList<WireEdge> getDomainObjectWires() {
		if (domainObjectWires == null) {
			domainObjectWires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES);
		}
		return domainObjectWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES:
				return ((InternalEList<?>)getDomainObjectWires()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES:
				return getDomainObjectWires();
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
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends DomainAttribute>)newValue);
				return;
			case ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES:
				getDomainObjectWires().clear();
				getDomainObjectWires().addAll((Collection<? extends WireEdge>)newValue);
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
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES:
				getDomainObjectWires().clear();
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
			case ModelPackage.DOMAIN_OBJECT__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ModelPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES:
				return domainObjectWires != null && !domainObjectWires.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DomainObjectImpl
