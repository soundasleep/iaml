/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.verification.model.validation.ExecutionPath;
import org.openiaml.verification.model.validation.IsSecure;
import org.openiaml.verification.model.validation.NavigatesTo;
import org.openiaml.verification.model.validation.PathTo;
import org.openiaml.verification.model.validation.SecureNode;
import org.openiaml.verification.model.validation.SecureOperation;
import org.openiaml.verification.model.validation.SecurePage;
import org.openiaml.verification.model.validation.SecurePath;
import org.openiaml.verification.model.validation.ShouldBeSecure;
import org.openiaml.verification.model.validation.ValidationPackage;
import org.openiaml.verification.model.validation.ValidationRule;
import org.openiaml.verification.model.validation.Violation;

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
 * @see org.openiaml.verification.model.validation.ValidationPackage
 * @generated
 */
public class ValidationSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ValidationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationSwitch() {
		if (modelPackage == null) {
			modelPackage = ValidationPackage.eINSTANCE;
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
			case ValidationPackage.VALIDATION_RULE: {
				ValidationRule validationRule = (ValidationRule)theEObject;
				T result = caseValidationRule(validationRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.NAVIGATES_TO: {
				NavigatesTo navigatesTo = (NavigatesTo)theEObject;
				T result = caseNavigatesTo(navigatesTo);
				if (result == null) result = caseValidationRule(navigatesTo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.VIOLATION: {
				Violation violation = (Violation)theEObject;
				T result = caseViolation(violation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.PATH_TO: {
				PathTo pathTo = (PathTo)theEObject;
				T result = casePathTo(pathTo);
				if (result == null) result = caseValidationRule(pathTo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.SHOULD_BE_SECURE: {
				ShouldBeSecure shouldBeSecure = (ShouldBeSecure)theEObject;
				T result = caseShouldBeSecure(shouldBeSecure);
				if (result == null) result = caseValidationRule(shouldBeSecure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.IS_SECURE: {
				IsSecure isSecure = (IsSecure)theEObject;
				T result = caseIsSecure(isSecure);
				if (result == null) result = caseValidationRule(isSecure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.SECURE_PAGE: {
				SecurePage securePage = (SecurePage)theEObject;
				T result = caseSecurePage(securePage);
				if (result == null) result = caseValidationRule(securePage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.SECURE_OPERATION: {
				SecureOperation secureOperation = (SecureOperation)theEObject;
				T result = caseSecureOperation(secureOperation);
				if (result == null) result = caseValidationRule(secureOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.EXECUTION_PATH: {
				ExecutionPath executionPath = (ExecutionPath)theEObject;
				T result = caseExecutionPath(executionPath);
				if (result == null) result = caseValidationRule(executionPath);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.SECURE_PATH: {
				SecurePath securePath = (SecurePath)theEObject;
				T result = caseSecurePath(securePath);
				if (result == null) result = caseValidationRule(securePath);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValidationPackage.SECURE_NODE: {
				SecureNode secureNode = (SecureNode)theEObject;
				T result = caseSecureNode(secureNode);
				if (result == null) result = caseValidationRule(secureNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidationRule(ValidationRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigates To</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigates To</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigatesTo(NavigatesTo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Violation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Violation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViolation(Violation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path To</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path To</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathTo(PathTo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Should Be Secure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Should Be Secure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShouldBeSecure(ShouldBeSecure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Is Secure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Is Secure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIsSecure(IsSecure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurePage(SecurePage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecureOperation(SecureOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Path</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Path</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionPath(ExecutionPath object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Path</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Path</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurePath(SecurePath object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecureNode(SecureNode object) {
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

} //ValidationSwitch
