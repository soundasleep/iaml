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

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Element Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementContainerImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.ApplicationElementContainerImpl#getApplicationElementWires <em>Application Element Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ApplicationElementContainerImpl extends ApplicationElementImpl implements ApplicationElementContainer {
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
	 * The cached value of the '{@link #getApplicationElementWires() <em>Application Element Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationElementWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> applicationElementWires;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationElementContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.APPLICATION_ELEMENT_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<ApplicationElement>(ApplicationElement.class, this, ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getApplicationElementWires() {
		if (applicationElementWires == null) {
			applicationElementWires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES);
		}
		return applicationElementWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES:
				return ((InternalEList<?>)getApplicationElementWires()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN:
				return getChildren();
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES:
				return getApplicationElementWires();
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
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ApplicationElement>)newValue);
				return;
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES:
				getApplicationElementWires().clear();
				getApplicationElementWires().addAll((Collection<? extends WireEdge>)newValue);
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
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN:
				getChildren().clear();
				return;
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES:
				getApplicationElementWires().clear();
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
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN:
				return children != null && !children.isEmpty();
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES:
				return applicationElementWires != null && !applicationElementWires.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ApplicationElementContainerImpl
