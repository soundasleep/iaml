/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.openiaml.model.model.ModelPackage;

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
 * @see org.openiaml.model.model.components.ComponentsFactory
 * @model kind="package"
 *        annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface ComponentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "components";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/model/components";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iaml.components";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentsPackage eINSTANCE = org.openiaml.model.model.components.impl.ComponentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.components.impl.LoginHandlerImpl <em>Login Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.components.impl.LoginHandlerImpl
	 * @see org.openiaml.model.model.components.impl.ComponentsPackageImpl#getLoginHandler()
	 * @generated
	 */
	int LOGIN_HANDLER = 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__OPERATIONS = ModelPackage.APPLICATION_ELEMENT_CONTAINER__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Generated By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__GENERATED_BY = ModelPackage.APPLICATION_ELEMENT_CONTAINER__GENERATED_BY;

	/**
	 * The feature id for the '<em><b>Is Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__IS_GENERATED = ModelPackage.APPLICATION_ELEMENT_CONTAINER__IS_GENERATED;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__ID = ModelPackage.APPLICATION_ELEMENT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Generated Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__GENERATED_RULE = ModelPackage.APPLICATION_ELEMENT_CONTAINER__GENERATED_RULE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__NAME = ModelPackage.APPLICATION_ELEMENT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__EVENT_TRIGGERS = ModelPackage.APPLICATION_ELEMENT_CONTAINER__EVENT_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__WIRES = ModelPackage.APPLICATION_ELEMENT_CONTAINER__WIRES;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__OUT_EDGES = ModelPackage.APPLICATION_ELEMENT_CONTAINER__OUT_EDGES;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__IN_EDGES = ModelPackage.APPLICATION_ELEMENT_CONTAINER__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Generated Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__GENERATED_ELEMENTS = ModelPackage.APPLICATION_ELEMENT_CONTAINER__GENERATED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Overridden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__OVERRIDDEN = ModelPackage.APPLICATION_ELEMENT_CONTAINER__OVERRIDDEN;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__CONDITIONS = ModelPackage.APPLICATION_ELEMENT_CONTAINER__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__PROPERTIES = ModelPackage.APPLICATION_ELEMENT_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__VALUES = ModelPackage.APPLICATION_ELEMENT_CONTAINER__VALUES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__CHILDREN = ModelPackage.APPLICATION_ELEMENT_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER__TYPE = ModelPackage.APPLICATION_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Login Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_HANDLER_FEATURE_COUNT = ModelPackage.APPLICATION_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.components.LoginHandlerTypes <em>Login Handler Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.components.LoginHandlerTypes
	 * @see org.openiaml.model.model.components.impl.ComponentsPackageImpl#getLoginHandlerTypes()
	 * @generated
	 */
	int LOGIN_HANDLER_TYPES = 1;

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.components.LoginHandler <em>Login Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Login Handler</em>'.
	 * @see org.openiaml.model.model.components.LoginHandler
	 * @generated
	 */
	EClass getLoginHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.model.model.components.LoginHandler#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.openiaml.model.model.components.LoginHandler#getType()
	 * @see #getLoginHandler()
	 * @generated
	 */
	EAttribute getLoginHandler_Type();

	/**
	 * Returns the meta object for enum '{@link org.openiaml.model.model.components.LoginHandlerTypes <em>Login Handler Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Login Handler Types</em>'.
	 * @see org.openiaml.model.model.components.LoginHandlerTypes
	 * @generated
	 */
	EEnum getLoginHandlerTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentsFactory getComponentsFactory();

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
		 * The meta object literal for the '{@link org.openiaml.model.model.components.impl.LoginHandlerImpl <em>Login Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.components.impl.LoginHandlerImpl
		 * @see org.openiaml.model.model.components.impl.ComponentsPackageImpl#getLoginHandler()
		 * @generated
		 */
		EClass LOGIN_HANDLER = eINSTANCE.getLoginHandler();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_HANDLER__TYPE = eINSTANCE.getLoginHandler_Type();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.components.LoginHandlerTypes <em>Login Handler Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.components.LoginHandlerTypes
		 * @see org.openiaml.model.model.components.impl.ComponentsPackageImpl#getLoginHandlerTypes()
		 * @generated
		 */
		EEnum LOGIN_HANDLER_TYPES = eINSTANCE.getLoginHandlerTypes();

	}

} //ComponentsPackage
