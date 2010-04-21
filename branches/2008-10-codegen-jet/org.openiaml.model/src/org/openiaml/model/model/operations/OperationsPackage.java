/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations;

import org.eclipse.emf.ecore.EClass;
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
 * @see org.openiaml.model.model.operations.OperationsFactory
 * @model kind="package"
 * @generated
 */
public interface OperationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "operations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/model/operations";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iaml.operations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OperationsPackage eINSTANCE = org.openiaml.model.model.operations.impl.OperationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.StartNodeImpl <em>Start Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.StartNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getStartNode()
	 * @generated
	 */
	int START_NODE = 0;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__OUT_EXECUTIONS = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Start Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE_FEATURE_COUNT = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.StopNodeImpl <em>Stop Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.StopNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getStopNode()
	 * @generated
	 */
	int STOP_NODE = 1;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE__IN_EXECUTIONS = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stop Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE_FEATURE_COUNT = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.FinishNodeImpl <em>Finish Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.FinishNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getFinishNode()
	 * @generated
	 */
	int FINISH_NODE = 2;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE__IN_EXECUTIONS = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Finish Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE_FEATURE_COUNT = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.DecisionNodeImpl <em>Decision Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.DecisionNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getDecisionNode()
	 * @generated
	 */
	int DECISION_NODE = 3;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE__OUT_EXECUTIONS = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE__IN_EXECUTIONS = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Decision Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_FEATURE_COUNT = ModelPackage.ACTIVITY_NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.DecisionOperationImpl <em>Decision Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.DecisionOperationImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getDecisionOperation()
	 * @generated
	 */
	int DECISION_OPERATION = 4;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__IN_EDGES = ModelPackage.CHAINED_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Generated By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__GENERATED_BY = ModelPackage.CHAINED_OPERATION__GENERATED_BY;

	/**
	 * The feature id for the '<em><b>Is Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__IS_GENERATED = ModelPackage.CHAINED_OPERATION__IS_GENERATED;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__ID = ModelPackage.CHAINED_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__NAME = ModelPackage.CHAINED_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__IN_FLOWS = ModelPackage.CHAINED_OPERATION__IN_FLOWS;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__IN_EXECUTIONS = ModelPackage.CHAINED_OPERATION__IN_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__OUT_FLOWS = ModelPackage.CHAINED_OPERATION__OUT_FLOWS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__PARAMETERS = ModelPackage.CHAINED_OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__OUT_EXECUTIONS = ModelPackage.CHAINED_OPERATION__OUT_EXECUTIONS;

	/**
	 * The number of structural features of the '<em>Decision Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION_FEATURE_COUNT = ModelPackage.CHAINED_OPERATION_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.StartNode <em>Start Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Node</em>'.
	 * @see org.openiaml.model.model.operations.StartNode
	 * @generated
	 */
	EClass getStartNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.StopNode <em>Stop Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop Node</em>'.
	 * @see org.openiaml.model.model.operations.StopNode
	 * @generated
	 */
	EClass getStopNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.FinishNode <em>Finish Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Finish Node</em>'.
	 * @see org.openiaml.model.model.operations.FinishNode
	 * @generated
	 */
	EClass getFinishNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.DecisionNode <em>Decision Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Node</em>'.
	 * @see org.openiaml.model.model.operations.DecisionNode
	 * @generated
	 */
	EClass getDecisionNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.DecisionOperation <em>Decision Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Operation</em>'.
	 * @see org.openiaml.model.model.operations.DecisionOperation
	 * @generated
	 */
	EClass getDecisionOperation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OperationsFactory getOperationsFactory();

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
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.StartNodeImpl <em>Start Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.StartNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getStartNode()
		 * @generated
		 */
		EClass START_NODE = eINSTANCE.getStartNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.StopNodeImpl <em>Stop Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.StopNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getStopNode()
		 * @generated
		 */
		EClass STOP_NODE = eINSTANCE.getStopNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.FinishNodeImpl <em>Finish Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.FinishNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getFinishNode()
		 * @generated
		 */
		EClass FINISH_NODE = eINSTANCE.getFinishNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.DecisionNodeImpl <em>Decision Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.DecisionNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getDecisionNode()
		 * @generated
		 */
		EClass DECISION_NODE = eINSTANCE.getDecisionNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.DecisionOperationImpl <em>Decision Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.DecisionOperationImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getDecisionOperation()
		 * @generated
		 */
		EClass DECISION_OPERATION = eINSTANCE.getDecisionOperation();

	}

} //OperationsPackage