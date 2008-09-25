/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdge;

import org.openiaml.model.model.wires.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.wires.WiresPackage
 * @generated
 */
public class WiresSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WiresPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiresSwitch() {
		if (modelPackage == null) {
			modelPackage = WiresPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case WiresPackage.SINGLE_WIRE: {
				SingleWire singleWire = (SingleWire)theEObject;
				T result = caseSingleWire(singleWire);
				if (result == null) result = caseWireEdge(singleWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.COMPOSITE_WIRE: {
				CompositeWire compositeWire = (CompositeWire)theEObject;
				T result = caseCompositeWire(compositeWire);
				if (result == null) result = caseWireEdge(compositeWire);
				if (result == null) result = caseNamedElement(compositeWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SYNC_WIRE: {
				SyncWire syncWire = (SyncWire)theEObject;
				T result = caseSyncWire(syncWire);
				if (result == null) result = caseCompositeWire(syncWire);
				if (result == null) result = caseWireEdge(syncWire);
				if (result == null) result = caseNamedElement(syncWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.RUN_WIRE: {
				RunWire runWire = (RunWire)theEObject;
				T result = caseRunWire(runWire);
				if (result == null) result = caseCompositeWire(runWire);
				if (result == null) result = caseWireEdge(runWire);
				if (result == null) result = caseNamedElement(runWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.EXECUTION_WIRE: {
				ExecutionWire executionWire = (ExecutionWire)theEObject;
				T result = caseExecutionWire(executionWire);
				if (result == null) result = caseSingleWire(executionWire);
				if (result == null) result = caseWireEdge(executionWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PROVIDED_PARAMETER_WIRE: {
				ProvidedParameterWire providedParameterWire = (ProvidedParameterWire)theEObject;
				T result = caseProvidedParameterWire(providedParameterWire);
				if (result == null) result = caseSingleWire(providedParameterWire);
				if (result == null) result = caseWireEdge(providedParameterWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PROPERTY_TO_PARAMETER_WIRE: {
				PropertyToParameterWire propertyToParameterWire = (PropertyToParameterWire)theEObject;
				T result = casePropertyToParameterWire(propertyToParameterWire);
				if (result == null) result = caseSingleWire(propertyToParameterWire);
				if (result == null) result = caseWireEdge(propertyToParameterWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PROPERTY_TO_EXECUTION_WIRE_WIRE: {
				PropertyToExecutionWireWire propertyToExecutionWireWire = (PropertyToExecutionWireWire)theEObject;
				T result = casePropertyToExecutionWireWire(propertyToExecutionWireWire);
				if (result == null) result = caseSingleWire(propertyToExecutionWireWire);
				if (result == null) result = caseWireEdge(propertyToExecutionWireWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleWire(SingleWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeWire(CompositeWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sync Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sync Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSyncWire(SyncWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunWire(RunWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionWire(ExecutionWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Parameter Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedParameterWire(ProvidedParameterWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property To Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property To Parameter Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyToParameterWire(PropertyToParameterWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property To Execution Wire Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property To Execution Wire Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyToExecutionWireWire(PropertyToExecutionWireWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdge(WireEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //WiresSwitch
