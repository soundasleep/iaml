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
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.PathToImpl <em>Path To</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.PathToImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getPathTo()
	 * @generated
	 */
	int PATH_TO = 3;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TO__FROM = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TO__TO = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Path To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TO_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.ShouldBeSecureImpl <em>Should Be Secure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.ShouldBeSecureImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getShouldBeSecure()
	 * @generated
	 */
	int SHOULD_BE_SECURE = 4;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHOULD_BE_SECURE__PAGE = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Should Be Secure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHOULD_BE_SECURE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.IsSecureImpl <em>Is Secure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.IsSecureImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getIsSecure()
	 * @generated
	 */
	int IS_SECURE = 5;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_SECURE__PAGE = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Is Secure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_SECURE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.SecurePageImpl <em>Secure Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.SecurePageImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecurePage()
	 * @generated
	 */
	int SECURE_PAGE = 6;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_PAGE__PAGE = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Secure Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_PAGE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.SecureOperationImpl <em>Secure Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.SecureOperationImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecureOperation()
	 * @generated
	 */
	int SECURE_OPERATION = 7;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OPERATION__OPERATION = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Secure Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OPERATION_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.ExecutionPathImpl <em>Execution Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.ExecutionPathImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getExecutionPath()
	 * @generated
	 */
	int EXECUTION_PATH = 8;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PATH__FROM = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PATH__TO = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Execution Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PATH_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.SecurePathImpl <em>Secure Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.SecurePathImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecurePath()
	 * @generated
	 */
	int SECURE_PATH = 9;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_PATH__FROM = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_PATH__TO = VALIDATION_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Secure Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_PATH_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.verification.model.validation.impl.SecureNodeImpl <em>Secure Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.verification.model.validation.impl.SecureNodeImpl
	 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecureNode()
	 * @generated
	 */
	int SECURE_NODE = 10;

	/**
	 * The feature id for the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_NODE__NODE = VALIDATION_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Secure Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_NODE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;


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
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.PathTo <em>Path To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path To</em>'.
	 * @see org.openiaml.verification.model.validation.PathTo
	 * @generated
	 */
	EClass getPathTo();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.PathTo#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.verification.model.validation.PathTo#getFrom()
	 * @see #getPathTo()
	 * @generated
	 */
	EReference getPathTo_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.PathTo#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.verification.model.validation.PathTo#getTo()
	 * @see #getPathTo()
	 * @generated
	 */
	EReference getPathTo_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.ShouldBeSecure <em>Should Be Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Should Be Secure</em>'.
	 * @see org.openiaml.verification.model.validation.ShouldBeSecure
	 * @generated
	 */
	EClass getShouldBeSecure();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.ShouldBeSecure#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see org.openiaml.verification.model.validation.ShouldBeSecure#getPage()
	 * @see #getShouldBeSecure()
	 * @generated
	 */
	EReference getShouldBeSecure_Page();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.IsSecure <em>Is Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is Secure</em>'.
	 * @see org.openiaml.verification.model.validation.IsSecure
	 * @generated
	 */
	EClass getIsSecure();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.IsSecure#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see org.openiaml.verification.model.validation.IsSecure#getPage()
	 * @see #getIsSecure()
	 * @generated
	 */
	EReference getIsSecure_Page();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.SecurePage <em>Secure Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Page</em>'.
	 * @see org.openiaml.verification.model.validation.SecurePage
	 * @generated
	 */
	EClass getSecurePage();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.SecurePage#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see org.openiaml.verification.model.validation.SecurePage#getPage()
	 * @see #getSecurePage()
	 * @generated
	 */
	EReference getSecurePage_Page();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.SecureOperation <em>Secure Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Operation</em>'.
	 * @see org.openiaml.verification.model.validation.SecureOperation
	 * @generated
	 */
	EClass getSecureOperation();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.SecureOperation#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.openiaml.verification.model.validation.SecureOperation#getOperation()
	 * @see #getSecureOperation()
	 * @generated
	 */
	EReference getSecureOperation_Operation();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.ExecutionPath <em>Execution Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Path</em>'.
	 * @see org.openiaml.verification.model.validation.ExecutionPath
	 * @generated
	 */
	EClass getExecutionPath();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.ExecutionPath#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.verification.model.validation.ExecutionPath#getFrom()
	 * @see #getExecutionPath()
	 * @generated
	 */
	EReference getExecutionPath_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.ExecutionPath#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.verification.model.validation.ExecutionPath#getTo()
	 * @see #getExecutionPath()
	 * @generated
	 */
	EReference getExecutionPath_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.SecurePath <em>Secure Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Path</em>'.
	 * @see org.openiaml.verification.model.validation.SecurePath
	 * @generated
	 */
	EClass getSecurePath();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.SecurePath#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.verification.model.validation.SecurePath#getFrom()
	 * @see #getSecurePath()
	 * @generated
	 */
	EReference getSecurePath_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.SecurePath#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.verification.model.validation.SecurePath#getTo()
	 * @see #getSecurePath()
	 * @generated
	 */
	EReference getSecurePath_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.verification.model.validation.SecureNode <em>Secure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Node</em>'.
	 * @see org.openiaml.verification.model.validation.SecureNode
	 * @generated
	 */
	EClass getSecureNode();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.verification.model.validation.SecureNode#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node</em>'.
	 * @see org.openiaml.verification.model.validation.SecureNode#getNode()
	 * @see #getSecureNode()
	 * @generated
	 */
	EReference getSecureNode_Node();

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

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.PathToImpl <em>Path To</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.PathToImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getPathTo()
		 * @generated
		 */
		EClass PATH_TO = eINSTANCE.getPathTo();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PATH_TO__FROM = eINSTANCE.getPathTo_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PATH_TO__TO = eINSTANCE.getPathTo_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.ShouldBeSecureImpl <em>Should Be Secure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.ShouldBeSecureImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getShouldBeSecure()
		 * @generated
		 */
		EClass SHOULD_BE_SECURE = eINSTANCE.getShouldBeSecure();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHOULD_BE_SECURE__PAGE = eINSTANCE.getShouldBeSecure_Page();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.IsSecureImpl <em>Is Secure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.IsSecureImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getIsSecure()
		 * @generated
		 */
		EClass IS_SECURE = eINSTANCE.getIsSecure();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IS_SECURE__PAGE = eINSTANCE.getIsSecure_Page();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.SecurePageImpl <em>Secure Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.SecurePageImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecurePage()
		 * @generated
		 */
		EClass SECURE_PAGE = eINSTANCE.getSecurePage();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_PAGE__PAGE = eINSTANCE.getSecurePage_Page();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.SecureOperationImpl <em>Secure Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.SecureOperationImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecureOperation()
		 * @generated
		 */
		EClass SECURE_OPERATION = eINSTANCE.getSecureOperation();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_OPERATION__OPERATION = eINSTANCE.getSecureOperation_Operation();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.ExecutionPathImpl <em>Execution Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.ExecutionPathImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getExecutionPath()
		 * @generated
		 */
		EClass EXECUTION_PATH = eINSTANCE.getExecutionPath();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_PATH__FROM = eINSTANCE.getExecutionPath_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_PATH__TO = eINSTANCE.getExecutionPath_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.SecurePathImpl <em>Secure Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.SecurePathImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecurePath()
		 * @generated
		 */
		EClass SECURE_PATH = eINSTANCE.getSecurePath();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_PATH__FROM = eINSTANCE.getSecurePath_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_PATH__TO = eINSTANCE.getSecurePath_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.verification.model.validation.impl.SecureNodeImpl <em>Secure Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.verification.model.validation.impl.SecureNodeImpl
		 * @see org.openiaml.verification.model.validation.impl.ValidationPackageImpl#getSecureNode()
		 * @generated
		 */
		EClass SECURE_NODE = eINSTANCE.getSecureNode();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_NODE__NODE = eINSTANCE.getSecureNode_Node();

	}

} //ValidationPackage
