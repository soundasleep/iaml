/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.verification.model.validation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openiaml.verification.model.validation.ValidationFactory
 * @model kind="package"
 * @generated
 */
public interface ValidationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "validation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/validation/2009/10";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iv";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ValidationPackage eINSTANCE = org.openiaml.verification.model.validation.impl.ValidationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.ValidationRuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.ValidationRuleImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getValidationRule()
	 * @generated
	 */
	int VALIDATION_RULE = 0;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_RULE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.NavigatesToImpl <em>Navigates To</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.NavigatesToImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getNavigatesTo()
	 * @generated
	 */
	int NAVIGATES_TO = 1;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATES_TO__FROM = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATES_TO__TO = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Navigates To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATES_TO_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.ViolationImpl <em>Violation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.ViolationImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getViolation()
	 * @generated
	 */
	int VIOLATION = 2;

	/**
	 * The feature id for the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION__REASON = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION__SOURCE = 1;

	/**
	 * The number of structural features of the '<em>Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.ValidationRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see org.openiaml.verification.model.validation.ValidationRule
	 * @generated
	 */
	EClass getValidationRule();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.NavigatesTo <em>Navigates To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigates To</em>'.
	 * @see org.openiaml.verification.model.validation.NavigatesTo
	 * @generated
	 */
	EClass getNavigatesTo();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.NavigatesTo#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.verification.model.validation.NavigatesTo#getFrom()
	 * @see #getNavigatesTo()
	 * @generated
	 */
	EReference getNavigatesTo_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.NavigatesTo#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.verification.model.validation.NavigatesTo#getTo()
	 * @see #getNavigatesTo()
	 * @generated
	 */
	EReference getNavigatesTo_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.Violation <em>Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Violation</em>'.
	 * @see org.openiaml.verification.model.validation.Violation
	 * @generated
	 */
	EClass getViolation();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.verification.model.validation.Violation#getReason <em>Reason</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reason</em>'.
	 * @see org.openiaml.verification.model.validation.Violation#getReason()
	 * @see #getViolation()
	 * @generated
	 */
	EAttribute getViolation_Reason();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.Violation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.openiaml.verification.model.validation.Violation#getSource()
	 * @see #getViolation()
	 * @generated
	 */
	EReference getViolation_Source();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ValidationFactory getValidationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.ValidationRuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.ValidationRuleImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getValidationRule()
		 * @generated
		 */
		EClass VALIDATION_RULE = eINSTANCE.getValidationRule();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.NavigatesToImpl <em>Navigates To</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.NavigatesToImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getNavigatesTo()
		 * @generated
		 */
		EClass NAVIGATES_TO = eINSTANCE.getNavigatesTo();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATES_TO__FROM = eINSTANCE.getNavigatesTo_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATES_TO__TO = eINSTANCE.getNavigatesTo_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.ViolationImpl <em>Violation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.ViolationImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getViolation()
		 * @generated
		 */
		EClass VIOLATION = eINSTANCE.getViolation();

		/**
		 * The meta object literal for the '<em><b>Reason</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIOLATION__REASON = eINSTANCE.getViolation_Reason();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION__SOURCE = eINSTANCE.getViolation_Source();

	}

} //ValidationPackage
