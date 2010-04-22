/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.verification.model.validation.ValidationPackage
 * @generated
 */
public class ValidationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ValidationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ValidationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidationSwitch<Adapter> modelSwitch =
		new ValidationSwitch<Adapter>() {
			@Override
			public Adapter caseValidationRule(ValidationRule object) {
				return createValidationRuleAdapter();
			}
			@Override
			public Adapter caseNavigatesTo(NavigatesTo object) {
				return createNavigatesToAdapter();
			}
			@Override
			public Adapter caseViolation(Violation object) {
				return createViolationAdapter();
			}
			@Override
			public Adapter casePathTo(PathTo object) {
				return createPathToAdapter();
			}
			@Override
			public Adapter caseShouldBeSecure(ShouldBeSecure object) {
				return createShouldBeSecureAdapter();
			}
			@Override
			public Adapter caseIsSecure(IsSecure object) {
				return createIsSecureAdapter();
			}
			@Override
			public Adapter caseSecurePage(SecurePage object) {
				return createSecurePageAdapter();
			}
			@Override
			public Adapter caseSecureOperation(SecureOperation object) {
				return createSecureOperationAdapter();
			}
			@Override
			public Adapter caseExecutionPath(ExecutionPath object) {
				return createExecutionPathAdapter();
			}
			@Override
			public Adapter caseSecurePath(SecurePath object) {
				return createSecurePathAdapter();
			}
			@Override
			public Adapter caseSecureNode(SecureNode object) {
				return createSecureNodeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.ValidationRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.ValidationRule
	 * @generated
	 */
	public Adapter createValidationRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.NavigatesTo <em>Navigates To</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.NavigatesTo
	 * @generated
	 */
	public Adapter createNavigatesToAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.Violation <em>Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.Violation
	 * @generated
	 */
	public Adapter createViolationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.PathTo <em>Path To</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.PathTo
	 * @generated
	 */
	public Adapter createPathToAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.ShouldBeSecure <em>Should Be Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.ShouldBeSecure
	 * @generated
	 */
	public Adapter createShouldBeSecureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.IsSecure <em>Is Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.IsSecure
	 * @generated
	 */
	public Adapter createIsSecureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.SecurePage <em>Secure Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.SecurePage
	 * @generated
	 */
	public Adapter createSecurePageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.SecureOperation <em>Secure Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.SecureOperation
	 * @generated
	 */
	public Adapter createSecureOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.ExecutionPath <em>Execution Path</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.ExecutionPath
	 * @generated
	 */
	public Adapter createExecutionPathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.SecurePath <em>Secure Path</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.SecurePath
	 * @generated
	 */
	public Adapter createSecurePathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.verification.model.validation.SecureNode <em>Secure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.verification.model.validation.SecureNode
	 * @generated
	 */
	public Adapter createSecureNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ValidationAdapterFactory