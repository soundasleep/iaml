/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.impl.ActivityNodeImpl;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.OperationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cancel Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.operations.impl.CancelNodeImpl#getInExecutions <em>In Executions</em>}</li>
 *   <li>{@link org.openiaml.model.model.operations.impl.CancelNodeImpl#getExceptionText <em>Exception Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CancelNodeImpl extends ActivityNodeImpl implements CancelNode {
	/**
	 * The cached value of the '{@link #getInExecutions() <em>In Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionEdge> inExecutions;

	/**
	 * The default value of the '{@link #getExceptionText() <em>Exception Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionText()
	 * @generated
	 * @ordered
	 */
	protected static final String EXCEPTION_TEXT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getExceptionText() <em>Exception Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionText()
	 * @generated
	 * @ordered
	 */
	protected String exceptionText = EXCEPTION_TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CancelNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OperationsPackage.Literals.CANCEL_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionEdge> getInExecutions() {
		if (inExecutions == null) {
			inExecutions = new EObjectWithInverseResolvingEList<ExecutionEdge>(ExecutionEdge.class, this, OperationsPackage.CANCEL_NODE__IN_EXECUTIONS, ModelPackage.EXECUTION_EDGE__TO);
		}
		return inExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExceptionText() {
		return exceptionText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionText(String newExceptionText) {
		String oldExceptionText = exceptionText;
		exceptionText = newExceptionText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OperationsPackage.CANCEL_NODE__EXCEPTION_TEXT, oldExceptionText, exceptionText));
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInExecutions()).basicAdd(otherEnd, msgs);
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				return ((InternalEList<?>)getInExecutions()).basicRemove(otherEnd, msgs);
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				return getInExecutions();
			case OperationsPackage.CANCEL_NODE__EXCEPTION_TEXT:
				return getExceptionText();
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				getInExecutions().clear();
				getInExecutions().addAll((Collection<? extends ExecutionEdge>)newValue);
				return;
			case OperationsPackage.CANCEL_NODE__EXCEPTION_TEXT:
				setExceptionText((String)newValue);
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				getInExecutions().clear();
				return;
			case OperationsPackage.CANCEL_NODE__EXCEPTION_TEXT:
				setExceptionText(EXCEPTION_TEXT_EDEFAULT);
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
			case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS:
				return inExecutions != null && !inExecutions.isEmpty();
			case OperationsPackage.CANCEL_NODE__EXCEPTION_TEXT:
				return EXCEPTION_TEXT_EDEFAULT == null ? exceptionText != null : !EXCEPTION_TEXT_EDEFAULT.equals(exceptionText);
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
		if (baseClass == ExecutionEdgeDestination.class) {
			switch (derivedFeatureID) {
				case OperationsPackage.CANCEL_NODE__IN_EXECUTIONS: return ModelPackage.EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS;
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
		if (baseClass == ExecutionEdgeDestination.class) {
			switch (baseFeatureID) {
				case ModelPackage.EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS: return OperationsPackage.CANCEL_NODE__IN_EXECUTIONS;
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
		result.append(" (exceptionText: ");
		result.append(exceptionText);
		result.append(')');
		return result.toString();
	}

} //CancelNodeImpl
