/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ActionDestination;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;
import org.openiaml.model.model.components.Gate;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgeDestination;

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
 * @see org.openiaml.model.model.components.ComponentsPackage
 * @generated
 */
public class ComponentsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentsSwitch() {
		if (modelPackage == null) {
			modelPackage = ComponentsPackage.eINSTANCE;
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
			case ComponentsPackage.LOGIN_HANDLER: {
				LoginHandler loginHandler = (LoginHandler)theEObject;
				T result = caseLoginHandler(loginHandler);
				if (result == null) result = caseParameterEdgeDestination(loginHandler);
				if (result == null) result = caseApplicationElement(loginHandler);
				if (result == null) result = caseActionSource(loginHandler);
				if (result == null) result = caseCanBeSynced(loginHandler);
				if (result == null) result = caseGeneratesElements(loginHandler);
				if (result == null) result = caseContainsOperations(loginHandler);
				if (result == null) result = caseNamedElement(loginHandler);
				if (result == null) result = caseGeneratedElement(loginHandler);
				if (result == null) result = caseWireSource(loginHandler);
				if (result == null) result = caseShouldntContainWires(loginHandler);
				if (result == null) result = caseWireDestination(loginHandler);
				if (result == null) result = caseContainsConditions(loginHandler);
				if (result == null) result = caseContainsWires(loginHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentsPackage.ACCESS_CONTROL_HANDLER: {
				AccessControlHandler accessControlHandler = (AccessControlHandler)theEObject;
				T result = caseAccessControlHandler(accessControlHandler);
				if (result == null) result = caseParameterEdgeDestination(accessControlHandler);
				if (result == null) result = caseRequiresEdgesSource(accessControlHandler);
				if (result == null) result = caseApplicationElement(accessControlHandler);
				if (result == null) result = caseCanBeSynced(accessControlHandler);
				if (result == null) result = caseGeneratesElements(accessControlHandler);
				if (result == null) result = caseContainsOperations(accessControlHandler);
				if (result == null) result = caseNamedElement(accessControlHandler);
				if (result == null) result = caseGeneratedElement(accessControlHandler);
				if (result == null) result = caseWireSource(accessControlHandler);
				if (result == null) result = caseShouldntContainWires(accessControlHandler);
				if (result == null) result = caseWireDestination(accessControlHandler);
				if (result == null) result = caseContainsConditions(accessControlHandler);
				if (result == null) result = caseContainsWires(accessControlHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentsPackage.GATE: {
				Gate gate = (Gate)theEObject;
				T result = caseGate(gate);
				if (result == null) result = caseNamedElement(gate);
				if (result == null) result = caseWireSource(gate);
				if (result == null) result = caseWireDestination(gate);
				if (result == null) result = caseGeneratesElements(gate);
				if (result == null) result = caseConditionEdgeDestination(gate);
				if (result == null) result = caseActionSource(gate);
				if (result == null) result = caseActionDestination(gate);
				if (result == null) result = caseGeneratedElement(gate);
				if (result == null) result = caseShouldntContainWires(gate);
				if (result == null) result = caseContainsWires(gate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentsPackage.ENTRY_GATE: {
				EntryGate entryGate = (EntryGate)theEObject;
				T result = caseEntryGate(entryGate);
				if (result == null) result = caseGate(entryGate);
				if (result == null) result = caseNamedElement(entryGate);
				if (result == null) result = caseWireSource(entryGate);
				if (result == null) result = caseWireDestination(entryGate);
				if (result == null) result = caseGeneratesElements(entryGate);
				if (result == null) result = caseConditionEdgeDestination(entryGate);
				if (result == null) result = caseActionSource(entryGate);
				if (result == null) result = caseActionDestination(entryGate);
				if (result == null) result = caseGeneratedElement(entryGate);
				if (result == null) result = caseShouldntContainWires(entryGate);
				if (result == null) result = caseContainsWires(entryGate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentsPackage.EXIT_GATE: {
				ExitGate exitGate = (ExitGate)theEObject;
				T result = caseExitGate(exitGate);
				if (result == null) result = caseGate(exitGate);
				if (result == null) result = caseNamedElement(exitGate);
				if (result == null) result = caseWireSource(exitGate);
				if (result == null) result = caseWireDestination(exitGate);
				if (result == null) result = caseGeneratesElements(exitGate);
				if (result == null) result = caseConditionEdgeDestination(exitGate);
				if (result == null) result = caseActionSource(exitGate);
				if (result == null) result = caseActionDestination(exitGate);
				if (result == null) result = caseGeneratedElement(exitGate);
				if (result == null) result = caseShouldntContainWires(exitGate);
				if (result == null) result = caseContainsWires(exitGate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Login Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Login Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoginHandler(LoginHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Access Control Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Access Control Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessControlHandler(AccessControlHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGate(Gate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry Gate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry Gate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntryGate(EntryGate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exit Gate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exit Gate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExitGate(ExitGate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contains Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsOperations(ContainsOperations object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Wire Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireSource(WireSource object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Can Be Synced</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Can Be Synced</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCanBeSynced(CanBeSynced object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationElement(ApplicationElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Requires Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requires Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiresEdgesSource(RequiresEdgesSource object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Action Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionSource(ActionSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionDestination(ActionDestination object) {
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

} //ComponentsSwitch
