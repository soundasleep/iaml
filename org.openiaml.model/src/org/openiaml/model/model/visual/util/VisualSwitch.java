/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.Accessible;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.Editable;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.ParameterEdgesSource;

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
 * @see org.openiaml.model.model.visual.VisualPackage
 * @generated
 */
public class VisualSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static VisualPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisualSwitch() {
		if (modelPackage == null) {
			modelPackage = VisualPackage.eINSTANCE;
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
			case VisualPackage.FRAME: {
				Frame frame = (Frame)theEObject;
				T result = caseFrame(frame);
				if (result == null) result = caseScope(frame);
				if (result == null) result = caseParameterEdgesSource(frame);
				if (result == null) result = caseCanBeSynced(frame);
				if (result == null) result = caseAccessible(frame);
				if (result == null) result = caseGeneratesElements(frame);
				if (result == null) result = caseNamedElement(frame);
				if (result == null) result = caseGeneratedElement(frame);
				if (result == null) result = caseWireSource(frame);
				if (result == null) result = caseShouldntContainWires(frame);
				if (result == null) result = caseWireDestination(frame);
				if (result == null) result = caseContainsConditions(frame);
				if (result == null) result = caseContainsOperations(frame);
				if (result == null) result = caseContainsWires(frame);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VisualPackage.INPUT_FORM: {
				InputForm inputForm = (InputForm)theEObject;
				T result = caseInputForm(inputForm);
				if (result == null) result = caseVisibleThing(inputForm);
				if (result == null) result = caseCanBeSynced(inputForm);
				if (result == null) result = caseParameterEdgesSource(inputForm);
				if (result == null) result = caseEditable(inputForm);
				if (result == null) result = caseAccessible(inputForm);
				if (result == null) result = caseContainsConditions(inputForm);
				if (result == null) result = caseContainsOperations(inputForm);
				if (result == null) result = caseWireSource(inputForm);
				if (result == null) result = caseShouldntContainWires(inputForm);
				if (result == null) result = caseWireDestination(inputForm);
				if (result == null) result = caseNamedElement(inputForm);
				if (result == null) result = caseGeneratedElement(inputForm);
				if (result == null) result = caseGeneratesElements(inputForm);
				if (result == null) result = caseContainsWires(inputForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VisualPackage.INPUT_TEXT_FIELD: {
				InputTextField inputTextField = (InputTextField)theEObject;
				T result = caseInputTextField(inputTextField);
				if (result == null) result = caseVisibleThing(inputTextField);
				if (result == null) result = caseCanBeSynced(inputTextField);
				if (result == null) result = caseParameterEdgesSource(inputTextField);
				if (result == null) result = caseEditable(inputTextField);
				if (result == null) result = caseAccessible(inputTextField);
				if (result == null) result = caseContainsConditions(inputTextField);
				if (result == null) result = caseContainsOperations(inputTextField);
				if (result == null) result = caseWireSource(inputTextField);
				if (result == null) result = caseShouldntContainWires(inputTextField);
				if (result == null) result = caseWireDestination(inputTextField);
				if (result == null) result = caseNamedElement(inputTextField);
				if (result == null) result = caseGeneratedElement(inputTextField);
				if (result == null) result = caseGeneratesElements(inputTextField);
				if (result == null) result = caseContainsWires(inputTextField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VisualPackage.BUTTON: {
				Button button = (Button)theEObject;
				T result = caseButton(button);
				if (result == null) result = caseVisibleThing(button);
				if (result == null) result = caseCanBeSynced(button);
				if (result == null) result = caseParameterEdgesSource(button);
				if (result == null) result = caseEditable(button);
				if (result == null) result = caseAccessible(button);
				if (result == null) result = caseContainsConditions(button);
				if (result == null) result = caseContainsOperations(button);
				if (result == null) result = caseWireSource(button);
				if (result == null) result = caseShouldntContainWires(button);
				if (result == null) result = caseWireDestination(button);
				if (result == null) result = caseNamedElement(button);
				if (result == null) result = caseGeneratedElement(button);
				if (result == null) result = caseGeneratesElements(button);
				if (result == null) result = caseContainsWires(button);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VisualPackage.LABEL: {
				Label label = (Label)theEObject;
				T result = caseLabel(label);
				if (result == null) result = caseVisibleThing(label);
				if (result == null) result = caseCanBeSynced(label);
				if (result == null) result = caseParameterEdgesSource(label);
				if (result == null) result = caseEditable(label);
				if (result == null) result = caseAccessible(label);
				if (result == null) result = caseContainsConditions(label);
				if (result == null) result = caseContainsOperations(label);
				if (result == null) result = caseWireSource(label);
				if (result == null) result = caseShouldntContainWires(label);
				if (result == null) result = caseWireDestination(label);
				if (result == null) result = caseNamedElement(label);
				if (result == null) result = caseGeneratedElement(label);
				if (result == null) result = caseGeneratesElements(label);
				if (result == null) result = caseContainsWires(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Frame</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Frame</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFrame(Frame object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputForm(InputForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Text Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Text Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputTextField(InputTextField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseButton(Button object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabel(Label object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Scope</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScope(Scope object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Editable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditable(Editable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Accessible</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Accessible</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessible(Accessible object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Visible Thing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visible Thing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVisibleThing(VisibleThing object) {
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

} //VisualSwitch
