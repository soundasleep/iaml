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
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__IN_EDGES = ModelPackage.SINGLE_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__NAME = ModelPackage.SINGLE_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__PARAMETERS = ModelPackage.SINGLE_OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__EDGES = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Start Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE_FEATURE_COUNT = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE__IN_EDGES = ModelPackage.SINGLE_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE__NAME = ModelPackage.SINGLE_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE__PARAMETERS = ModelPackage.SINGLE_OPERATION__PARAMETERS;

	/**
	 * The number of structural features of the '<em>Stop Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_NODE_FEATURE_COUNT = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE__IN_EDGES = ModelPackage.SINGLE_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE__NAME = ModelPackage.SINGLE_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE__PARAMETERS = ModelPackage.SINGLE_OPERATION__PARAMETERS;

	/**
	 * The number of structural features of the '<em>Finish Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_NODE_FEATURE_COUNT = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.SplitNodeImpl <em>Split Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.SplitNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getSplitNode()
	 * @generated
	 */
	int SPLIT_NODE = 3;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_NODE__IN_EDGES = ModelPackage.SINGLE_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_NODE__NAME = ModelPackage.SINGLE_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_NODE__PARAMETERS = ModelPackage.SINGLE_OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_NODE__EDGES = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Split Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_NODE_FEATURE_COUNT = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.operations.impl.JoinNodeImpl <em>Join Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.operations.impl.JoinNodeImpl
	 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getJoinNode()
	 * @generated
	 */
	int JOIN_NODE = 4;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__IN_EDGES = ModelPackage.SINGLE_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__NAME = ModelPackage.SINGLE_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__PARAMETERS = ModelPackage.SINGLE_OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__EDGES = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Join Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE_FEATURE_COUNT = ModelPackage.SINGLE_OPERATION_FEATURE_COUNT + 1;


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
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.SplitNode <em>Split Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Split Node</em>'.
	 * @see org.openiaml.model.model.operations.SplitNode
	 * @generated
	 */
	EClass getSplitNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.operations.JoinNode <em>Join Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Node</em>'.
	 * @see org.openiaml.model.model.operations.JoinNode
	 * @generated
	 */
	EClass getJoinNode();

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
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.SplitNodeImpl <em>Split Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.SplitNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getSplitNode()
		 * @generated
		 */
		EClass SPLIT_NODE = eINSTANCE.getSplitNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.operations.impl.JoinNodeImpl <em>Join Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.operations.impl.JoinNodeImpl
		 * @see org.openiaml.model.model.operations.impl.OperationsPackageImpl#getJoinNode()
		 * @generated
		 */
		EClass JOIN_NODE = eINSTANCE.getJoinNode();

	}

} //OperationsPackage
