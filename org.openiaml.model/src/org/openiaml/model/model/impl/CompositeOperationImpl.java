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

import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getSubOperations <em>Sub Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.impl.CompositeOperationImpl#getCompositeOperationWires <em>Composite Operation Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeOperationImpl extends OperationImpl implements CompositeOperation {
	/**
	 * The cached value of the '{@link #getSubOperations() <em>Sub Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> subOperations;

	/**
	 * The cached value of the '{@link #getCompositeOperationWires() <em>Composite Operation Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeOperationWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> compositeOperationWires;

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
	public EList<Operation> getSubOperations() {
		if (subOperations == null) {
			subOperations = new EObjectContainmentEList<Operation>(Operation.class, this, ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS);
		}
		return subOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getCompositeOperationWires() {
		if (compositeOperationWires == null) {
			compositeOperationWires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES);
		}
		return compositeOperationWires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS:
				return ((InternalEList<?>)getSubOperations()).basicRemove(otherEnd, msgs);
			case ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES:
				return ((InternalEList<?>)getCompositeOperationWires()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS:
				return getSubOperations();
			case ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES:
				return getCompositeOperationWires();
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
			case ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS:
				getSubOperations().clear();
				getSubOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES:
				getCompositeOperationWires().clear();
				getCompositeOperationWires().addAll((Collection<? extends WireEdge>)newValue);
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
			case ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS:
				getSubOperations().clear();
				return;
			case ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES:
				getCompositeOperationWires().clear();
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
			case ModelPackage.COMPOSITE_OPERATION__SUB_OPERATIONS:
				return subOperations != null && !subOperations.isEmpty();
			case ModelPackage.COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES:
				return compositeOperationWires != null && !compositeOperationWires.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeOperationImpl
