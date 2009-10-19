/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.verification.model.validation.ExecutionPath;
import org.openiaml.verification.model.validation.IsSecure;
import org.openiaml.verification.model.validation.NavigatesTo;
import org.openiaml.verification.model.validation.PathTo;
import org.openiaml.verification.model.validation.SecureNode;
import org.openiaml.verification.model.validation.SecureOperation;
import org.openiaml.verification.model.validation.SecurePage;
import org.openiaml.verification.model.validation.SecurePath;
import org.openiaml.verification.model.validation.ShouldBeSecure;
import org.openiaml.verification.model.validation.ValidationFactory;
import org.openiaml.verification.model.validation.ValidationPackage;
import org.openiaml.verification.model.validation.Violation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValidationFactoryImpl extends EFactoryImpl implements ValidationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ValidationFactory init() {
		try {
			ValidationFactory theValidationFactory = (ValidationFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/validation/2009/10"); 
			if (theValidationFactory != null) {
				return theValidationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ValidationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ValidationPackage.NAVIGATES_TO: return createNavigatesTo();
			case ValidationPackage.VIOLATION: return createViolation();
			case ValidationPackage.PATH_TO: return createPathTo();
			case ValidationPackage.SHOULD_BE_SECURE: return createShouldBeSecure();
			case ValidationPackage.IS_SECURE: return createIsSecure();
			case ValidationPackage.SECURE_PAGE: return createSecurePage();
			case ValidationPackage.SECURE_OPERATION: return createSecureOperation();
			case ValidationPackage.EXECUTION_PATH: return createExecutionPath();
			case ValidationPackage.SECURE_PATH: return createSecurePath();
			case ValidationPackage.SECURE_NODE: return createSecureNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigatesTo createNavigatesTo() {
		NavigatesToImpl navigatesTo = new NavigatesToImpl();
		return navigatesTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Violation createViolation() {
		ViolationImpl violation = new ViolationImpl();
		return violation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathTo createPathTo() {
		PathToImpl pathTo = new PathToImpl();
		return pathTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShouldBeSecure createShouldBeSecure() {
		ShouldBeSecureImpl shouldBeSecure = new ShouldBeSecureImpl();
		return shouldBeSecure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsSecure createIsSecure() {
		IsSecureImpl isSecure = new IsSecureImpl();
		return isSecure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurePage createSecurePage() {
		SecurePageImpl securePage = new SecurePageImpl();
		return securePage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecureOperation createSecureOperation() {
		SecureOperationImpl secureOperation = new SecureOperationImpl();
		return secureOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionPath createExecutionPath() {
		ExecutionPathImpl executionPath = new ExecutionPathImpl();
		return executionPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurePath createSecurePath() {
		SecurePathImpl securePath = new SecurePathImpl();
		return securePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecureNode createSecureNode() {
		SecureNodeImpl secureNode = new SecureNodeImpl();
		return secureNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationPackage getValidationPackage() {
		return (ValidationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ValidationPackage getPackage() {
		return ValidationPackage.eINSTANCE;
	}

} //ValidationFactoryImpl
