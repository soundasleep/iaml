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
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ConstraintWire;
import org.openiaml.model.model.wires.ExtendsWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.ProvidesWire;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
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
				if (result == null) result = caseContainsConditions(compositeWire);
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
				if (result == null) result = caseContainsConditions(syncWire);
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
				if (result == null) result = caseContainsConditions(runInstanceWire);
				if (result == null) result = caseGeneratedElement(runInstanceWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PARAMETER_WIRE: {
				ParameterWire parameterWire = (ParameterWire)theEObject;
				T result = caseParameterWire(parameterWire);
				if (result == null) result = caseSingleWire(parameterWire);
				if (result == null) result = caseNamedElement(parameterWire);
				if (result == null) result = caseWireEdge(parameterWire);
				if (result == null) result = caseGeneratedElement(parameterWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SET_WIRE: {
				SetWire setWire = (SetWire)theEObject;
				T result = caseSetWire(setWire);
				if (result == null) result = caseCompositeWire(setWire);
				if (result == null) result = caseWireEdgeDestination(setWire);
				if (result == null) result = caseWireEdge(setWire);
				if (result == null) result = caseNamedElement(setWire);
				if (result == null) result = caseContainsWires(setWire);
				if (result == null) result = caseGeneratesElements(setWire);
				if (result == null) result = caseContainsConditions(setWire);
				if (result == null) result = caseGeneratedElement(setWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.NAVIGATE_WIRE: {
				NavigateWire navigateWire = (NavigateWire)theEObject;
				T result = caseNavigateWire(navigateWire);
				if (result == null) result = caseCompositeWire(navigateWire);
				if (result == null) result = caseWireEdgeDestination(navigateWire);
				if (result == null) result = caseWireEdge(navigateWire);
				if (result == null) result = caseNamedElement(navigateWire);
				if (result == null) result = caseContainsWires(navigateWire);
				if (result == null) result = caseGeneratesElements(navigateWire);
				if (result == null) result = caseContainsConditions(navigateWire);
				if (result == null) result = caseGeneratedElement(navigateWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SELECT_WIRE: {
				SelectWire selectWire = (SelectWire)theEObject;
				T result = caseSelectWire(selectWire);
				if (result == null) result = caseCompositeWire(selectWire);
				if (result == null) result = caseWireEdgeDestination(selectWire);
				if (result == null) result = caseWireEdge(selectWire);
				if (result == null) result = caseNamedElement(selectWire);
				if (result == null) result = caseContainsWires(selectWire);
				if (result == null) result = caseGeneratesElements(selectWire);
				if (result == null) result = caseContainsConditions(selectWire);
				if (result == null) result = caseGeneratedElement(selectWire);
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
				if (result == null) result = caseContainsConditions(conditionWire);
				if (result == null) result = caseGeneratedElement(conditionWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.NEW_INSTANCE_WIRE: {
				NewInstanceWire newInstanceWire = (NewInstanceWire)theEObject;
				T result = caseNewInstanceWire(newInstanceWire);
				if (result == null) result = caseCompositeWire(newInstanceWire);
				if (result == null) result = caseWireEdge(newInstanceWire);
				if (result == null) result = caseNamedElement(newInstanceWire);
				if (result == null) result = caseContainsWires(newInstanceWire);
				if (result == null) result = caseGeneratesElements(newInstanceWire);
				if (result == null) result = caseContainsConditions(newInstanceWire);
				if (result == null) result = caseGeneratedElement(newInstanceWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.EXTENDS_WIRE: {
				ExtendsWire extendsWire = (ExtendsWire)theEObject;
				T result = caseExtendsWire(extendsWire);
				if (result == null) result = caseSingleWire(extendsWire);
				if (result == null) result = caseGeneratesElements(extendsWire);
				if (result == null) result = caseWireEdge(extendsWire);
				if (result == null) result = caseGeneratedElement(extendsWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.REQUIRES_WIRE: {
				RequiresWire requiresWire = (RequiresWire)theEObject;
				T result = caseRequiresWire(requiresWire);
				if (result == null) result = caseSingleWire(requiresWire);
				if (result == null) result = caseWireEdgesSource(requiresWire);
				if (result == null) result = caseWireEdgeDestination(requiresWire);
				if (result == null) result = caseWireEdge(requiresWire);
				if (result == null) result = caseShouldntContainWires(requiresWire);
				if (result == null) result = caseGeneratedElement(requiresWire);
				if (result == null) result = caseContainsWires(requiresWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONSTRAINT_WIRE: {
				ConstraintWire constraintWire = (ConstraintWire)theEObject;
				T result = caseConstraintWire(constraintWire);
				if (result == null) result = caseSingleWire(constraintWire);
				if (result == null) result = caseWireEdge(constraintWire);
				if (result == null) result = caseGeneratedElement(constraintWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PROVIDES_WIRE: {
				ProvidesWire providesWire = (ProvidesWire)theEObject;
				T result = caseProvidesWire(providesWire);
				if (result == null) result = caseSingleWire(providesWire);
				if (result == null) result = caseWireEdge(providesWire);
				if (result == null) result = caseGeneratedElement(providesWire);
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
	 * Returns the result of interpreting the object as an instance of '<em>Set Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSetWire(SetWire object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Select Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Select Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelectWire(SelectWire object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>New Instance Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>New Instance Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNewInstanceWire(NewInstanceWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extends Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extends Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendsWire(ExtendsWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requires Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requires Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiresWire(RequiresWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintWire(ConstraintWire object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provides Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provides Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidesWire(ProvidesWire object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Contains Conditions</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Conditions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsConditions(ContainsConditions object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Shouldnt Contain Wires</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shouldnt Contain Wires</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShouldntContainWires(ShouldntContainWires object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgesSource(WireEdgesSource object) {
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
