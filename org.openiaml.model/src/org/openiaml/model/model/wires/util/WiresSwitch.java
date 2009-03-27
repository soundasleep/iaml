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
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.wires.CommitWire;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.FilterWire;
import org.openiaml.model.model.wires.FindWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.ShowWire;
import org.openiaml.model.model.wires.SingleWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

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
				if (result == null) result = caseGeneratedElement(singleWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.COMPOSITE_WIRE: {
				CompositeWire compositeWire = (CompositeWire)theEObject;
				T result = caseCompositeWire(compositeWire);
				if (result == null) result = caseWireEdge(compositeWire);
				if (result == null) result = caseNamedElement(compositeWire);
				if (result == null) result = caseContainsWires(compositeWire);
				if (result == null) result = caseGeneratesElements(compositeWire);
				if (result == null) result = caseGeneratedElement(compositeWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SYNC_WIRE: {
				SyncWire syncWire = (SyncWire)theEObject;
				T result = caseSyncWire(syncWire);
				if (result == null) result = caseCompositeWire(syncWire);
				if (result == null) result = caseWireEdgeDestination(syncWire);
				if (result == null) result = caseWireEdge(syncWire);
				if (result == null) result = caseNamedElement(syncWire);
				if (result == null) result = caseContainsWires(syncWire);
				if (result == null) result = caseGeneratesElements(syncWire);
				if (result == null) result = caseGeneratedElement(syncWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.RUN_INSTANCE_WIRE: {
				RunInstanceWire runInstanceWire = (RunInstanceWire)theEObject;
				T result = caseRunInstanceWire(runInstanceWire);
				if (result == null) result = caseCompositeWire(runInstanceWire);
				if (result == null) result = caseWireEdgeDestination(runInstanceWire);
				if (result == null) result = caseWireEdge(runInstanceWire);
				if (result == null) result = caseNamedElement(runInstanceWire);
				if (result == null) result = caseContainsWires(runInstanceWire);
				if (result == null) result = caseGeneratesElements(runInstanceWire);
				if (result == null) result = caseGeneratedElement(runInstanceWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PARAMETER_WIRE: {
				ParameterWire parameterWire = (ParameterWire)theEObject;
				T result = caseParameterWire(parameterWire);
				if (result == null) result = caseSingleWire(parameterWire);
				if (result == null) result = caseWireEdge(parameterWire);
				if (result == null) result = caseGeneratedElement(parameterWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.FIND_WIRE: {
				FindWire findWire = (FindWire)theEObject;
				T result = caseFindWire(findWire);
				if (result == null) result = caseCompositeWire(findWire);
				if (result == null) result = caseWireEdge(findWire);
				if (result == null) result = caseNamedElement(findWire);
				if (result == null) result = caseContainsWires(findWire);
				if (result == null) result = caseGeneratesElements(findWire);
				if (result == null) result = caseGeneratedElement(findWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SHOW_WIRE: {
				ShowWire showWire = (ShowWire)theEObject;
				T result = caseShowWire(showWire);
				if (result == null) result = caseCompositeWire(showWire);
				if (result == null) result = caseWireEdge(showWire);
				if (result == null) result = caseNamedElement(showWire);
				if (result == null) result = caseContainsWires(showWire);
				if (result == null) result = caseGeneratesElements(showWire);
				if (result == null) result = caseGeneratedElement(showWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.COMMIT_WIRE: {
				CommitWire commitWire = (CommitWire)theEObject;
				T result = caseCommitWire(commitWire);
				if (result == null) result = caseCompositeWire(commitWire);
				if (result == null) result = caseWireEdge(commitWire);
				if (result == null) result = caseNamedElement(commitWire);
				if (result == null) result = caseContainsWires(commitWire);
				if (result == null) result = caseGeneratesElements(commitWire);
				if (result == null) result = caseGeneratedElement(commitWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.NAVIGATE_WIRE: {
				NavigateWire navigateWire = (NavigateWire)theEObject;
				T result = caseNavigateWire(navigateWire);
				if (result == null) result = caseCompositeWire(navigateWire);
				if (result == null) result = caseWireEdge(navigateWire);
				if (result == null) result = caseNamedElement(navigateWire);
				if (result == null) result = caseContainsWires(navigateWire);
				if (result == null) result = caseGeneratesElements(navigateWire);
				if (result == null) result = caseGeneratedElement(navigateWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.FILTER_WIRE: {
				FilterWire filterWire = (FilterWire)theEObject;
				T result = caseFilterWire(filterWire);
				if (result == null) result = caseCompositeWire(filterWire);
				if (result == null) result = caseWireEdgeDestination(filterWire);
				if (result == null) result = caseWireEdge(filterWire);
				if (result == null) result = caseNamedElement(filterWire);
				if (result == null) result = caseContainsWires(filterWire);
				if (result == null) result = caseGeneratesElements(filterWire);
				if (result == null) result = caseGeneratedElement(filterWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONDITION_WIRE: {
				ConditionWire conditionWire = (ConditionWire)theEObject;
				T result = caseConditionWire(conditionWire);
				if (result == null) result = caseCompositeWire(conditionWire);
				if (result == null) result = caseWireEdgeDestination(conditionWire);
				if (result == null) result = caseWireEdge(conditionWire);
				if (result == null) result = caseNamedElement(conditionWire);
				if (result == null) result = caseContainsWires(conditionWire);
				if (result == null) result = caseGeneratesElements(conditionWire);
				if (result == null) result = caseGeneratedElement(conditionWire);
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
	 * Returns the result of interpreting the object as an instance of '<em>Run Instance Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Instance Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunInstanceWire(RunInstanceWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterWire(ParameterWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Find Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Find Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFindWire(FindWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Show Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Show Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShowWire(ShowWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Commit Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Commit Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommitWire(CommitWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigate Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigate Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigateWire(NavigateWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterWire(FilterWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionWire(ConditionWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generated Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generated Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneratedElement(GeneratedElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Contains Wires</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Wires</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsWires(ContainsWires object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generates Elements</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generates Elements</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneratesElements(GeneratesElements object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgeDestination(WireEdgeDestination object) {
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
