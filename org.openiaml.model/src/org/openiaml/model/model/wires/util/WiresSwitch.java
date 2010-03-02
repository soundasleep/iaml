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
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ConstraintEdgeDestination;
import org.openiaml.model.model.wires.ConstraintEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;
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
				if (result == null) result = caseWire(singleWire);
				if (result == null) result = caseGeneratedElement(singleWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.COMPOSITE_WIRE: {
				CompositeWire compositeWire = (CompositeWire)theEObject;
				T result = caseCompositeWire(compositeWire);
				if (result == null) result = caseWire(compositeWire);
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
				if (result == null) result = caseWireDestination(syncWire);
				if (result == null) result = caseParameterEdgeDestination(syncWire);
				if (result == null) result = caseConditionEdgeDestination(syncWire);
				if (result == null) result = caseWire(syncWire);
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
				if (result == null) result = caseWireDestination(runInstanceWire);
				if (result == null) result = caseParameterEdgeDestination(runInstanceWire);
				if (result == null) result = caseSingleWire(runInstanceWire);
				if (result == null) result = caseNamedElement(runInstanceWire);
				if (result == null) result = caseGeneratesElements(runInstanceWire);
				if (result == null) result = caseConditionEdgeDestination(runInstanceWire);
				if (result == null) result = caseWire(runInstanceWire);
				if (result == null) result = caseGeneratedElement(runInstanceWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PARAMETER_EDGE: {
				ParameterEdge parameterEdge = (ParameterEdge)theEObject;
				T result = caseParameterEdge(parameterEdge);
				if (result == null) result = caseNamedElement(parameterEdge);
				if (result == null) result = caseGeneratedElement(parameterEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SET_WIRE: {
				SetWire setWire = (SetWire)theEObject;
				T result = caseSetWire(setWire);
				if (result == null) result = caseCompositeWire(setWire);
				if (result == null) result = caseWireDestination(setWire);
				if (result == null) result = caseConditionEdgeDestination(setWire);
				if (result == null) result = caseWire(setWire);
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
				if (result == null) result = caseWireDestination(navigateWire);
				if (result == null) result = caseSingleWire(navigateWire);
				if (result == null) result = caseNamedElement(navigateWire);
				if (result == null) result = caseGeneratesElements(navigateWire);
				if (result == null) result = caseConditionEdgeDestination(navigateWire);
				if (result == null) result = caseWire(navigateWire);
				if (result == null) result = caseGeneratedElement(navigateWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.SELECT_WIRE: {
				SelectWire selectWire = (SelectWire)theEObject;
				T result = caseSelectWire(selectWire);
				if (result == null) result = caseWireDestination(selectWire);
				if (result == null) result = caseParameterEdgeDestination(selectWire);
				if (result == null) result = caseSingleWire(selectWire);
				if (result == null) result = caseNamedElement(selectWire);
				if (result == null) result = caseGeneratesElements(selectWire);
				if (result == null) result = caseWire(selectWire);
				if (result == null) result = caseGeneratedElement(selectWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONDITION_EDGE: {
				ConditionEdge conditionEdge = (ConditionEdge)theEObject;
				T result = caseConditionEdge(conditionEdge);
				if (result == null) result = caseParameterEdgeDestination(conditionEdge);
				if (result == null) result = caseNamedElement(conditionEdge);
				if (result == null) result = caseGeneratesElements(conditionEdge);
				if (result == null) result = caseGeneratedElement(conditionEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.NEW_INSTANCE_WIRE: {
				NewInstanceWire newInstanceWire = (NewInstanceWire)theEObject;
				T result = caseNewInstanceWire(newInstanceWire);
				if (result == null) result = caseSingleWire(newInstanceWire);
				if (result == null) result = caseNamedElement(newInstanceWire);
				if (result == null) result = caseGeneratesElements(newInstanceWire);
				if (result == null) result = caseWire(newInstanceWire);
				if (result == null) result = caseGeneratedElement(newInstanceWire);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.EXTENDS_EDGE: {
				ExtendsEdge extendsEdge = (ExtendsEdge)theEObject;
				T result = caseExtendsEdge(extendsEdge);
				if (result == null) result = caseGeneratedElement(extendsEdge);
				if (result == null) result = caseGeneratesElements(extendsEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.REQUIRES_EDGE: {
				RequiresEdge requiresEdge = (RequiresEdge)theEObject;
				T result = caseRequiresEdge(requiresEdge);
				if (result == null) result = caseGeneratedElement(requiresEdge);
				if (result == null) result = caseConstraintEdgesSource(requiresEdge);
				if (result == null) result = caseConstraintEdgeDestination(requiresEdge);
				if (result == null) result = caseContainsWires(requiresEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONSTRAINT_EDGE: {
				ConstraintEdge constraintEdge = (ConstraintEdge)theEObject;
				T result = caseConstraintEdge(constraintEdge);
				if (result == null) result = caseGeneratedElement(constraintEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PROVIDES_EDGE: {
				ProvidesEdge providesEdge = (ProvidesEdge)theEObject;
				T result = caseProvidesEdge(providesEdge);
				if (result == null) result = caseGeneratedElement(providesEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PARAMETER_EDGES_SOURCE: {
				ParameterEdgesSource parameterEdgesSource = (ParameterEdgesSource)theEObject;
				T result = caseParameterEdgesSource(parameterEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.PARAMETER_EDGE_DESTINATION: {
				ParameterEdgeDestination parameterEdgeDestination = (ParameterEdgeDestination)theEObject;
				T result = caseParameterEdgeDestination(parameterEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.EXTENDS_EDGES_SOURCE: {
				ExtendsEdgesSource extendsEdgesSource = (ExtendsEdgesSource)theEObject;
				T result = caseExtendsEdgesSource(extendsEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.EXTENDS_EDGE_DESTINATION: {
				ExtendsEdgeDestination extendsEdgeDestination = (ExtendsEdgeDestination)theEObject;
				T result = caseExtendsEdgeDestination(extendsEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONSTRAINT_EDGES_SOURCE: {
				ConstraintEdgesSource constraintEdgesSource = (ConstraintEdgesSource)theEObject;
				T result = caseConstraintEdgesSource(constraintEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONSTRAINT_EDGE_DESTINATION: {
				ConstraintEdgeDestination constraintEdgeDestination = (ConstraintEdgeDestination)theEObject;
				T result = caseConstraintEdgeDestination(constraintEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONDITION_EDGES_SOURCE: {
				ConditionEdgesSource conditionEdgesSource = (ConditionEdgesSource)theEObject;
				T result = caseConditionEdgesSource(conditionEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WiresPackage.CONDITION_EDGE_DESTINATION: {
				ConditionEdgeDestination conditionEdgeDestination = (ConditionEdgeDestination)theEObject;
				T result = caseConditionEdgeDestination(conditionEdgeDestination);
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
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterEdge(ParameterEdge object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Condition Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionEdge(ConditionEdge object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Extends Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extends Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendsEdge(ExtendsEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requires Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requires Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiresEdge(RequiresEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintEdge(ConstraintEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provides Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provides Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidesEdge(ProvidesEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterEdgesSource(ParameterEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterEdgeDestination(ParameterEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extends Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extends Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendsEdgesSource(ExtendsEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extends Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extends Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendsEdgeDestination(ExtendsEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintEdgesSource(ConstraintEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintEdgeDestination(ConstraintEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionEdgesSource(ConditionEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionEdgeDestination(ConditionEdgeDestination object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Wire</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWire(Wire object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Wire Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireDestination(WireDestination object) {
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
