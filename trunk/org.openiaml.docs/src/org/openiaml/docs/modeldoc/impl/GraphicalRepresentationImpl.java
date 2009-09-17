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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graphical Representation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphicalRepresentationImpl extends SemanticImpl implements GraphicalRepresentation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphicalRepresentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.GRAPHICAL_REPRESENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFClass getContainingClass() {
		if (eContainerFeatureID() != ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS) return null;
		return (EMFClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingClass(EMFClass newContainingClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingClass, ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingClass(EMFClass newContainingClass) {
		if (newContainingClass != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS && newContainingClass != null)) {
			if (EcoreUtil.isAncestor(this, newContainingClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingClass != null)
				msgs = ((InternalEObject)newContainingClass).eInverseAdd(this, ModeldocPackage.EMF_CLASS__GRAPHICAL_REPRESENTATIONS, EMFClass.class, msgs);
			msgs = basicSetContainingClass(newContainingClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS, newContainingClass, newContainingClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingClass((EMFClass)otherEnd, msgs);
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				return basicSetContainingClass(null, msgs);
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.EMF_CLASS__GRAPHICAL_REPRESENTATIONS, EMFClass.class, msgs);
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				return getContainingClass();
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				setContainingClass((EMFClass)newValue);
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				setContainingClass((EMFClass)null);
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
			case ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS:
				return getContainingClass() != null;
		}
		return super.eIsSet(featureID);
	}

} //GraphicalRepresentationImpl
