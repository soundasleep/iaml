/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.operations.OperationsPackage
 * @generated
 */
public class OperationsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OperationsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = OperationsPackage.eINSTANCE;
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
	protected OperationsSwitch<Adapter> modelSwitch =
		new OperationsSwitch<Adapter>() {
			@Override
			public Adapter caseStartNode(StartNode object) {
				return createStartNodeAdapter();
			}
			@Override
			public Adapter caseCancelNode(CancelNode object) {
				return createCancelNodeAdapter();
			}
			@Override
			public Adapter caseFinishNode(FinishNode object) {
				return createFinishNodeAdapter();
			}
			@Override
			public Adapter caseDecisionNode(DecisionNode object) {
				return createDecisionNodeAdapter();
			}
			@Override
			public Adapter caseDecisionOperation(DecisionOperation object) {
				return createDecisionOperationAdapter();
			}
			@Override
			public Adapter caseDecisionCondition(DecisionCondition object) {
				return createDecisionConditionAdapter();
			}
			@Override
			public Adapter caseSplitNode(SplitNode object) {
				return createSplitNodeAdapter();
			}
			@Override
			public Adapter caseJoinNode(JoinNode object) {
				return createJoinNodeAdapter();
			}
			@Override
			public Adapter caseGeneratedElement(GeneratedElement object) {
				return createGeneratedElementAdapter();
			}
			@Override
			public Adapter caseActivityNode(ActivityNode object) {
				return createActivityNodeAdapter();
			}
			@Override
			public Adapter caseExecutionEdgesSource(ExecutionEdgesSource object) {
				return createExecutionEdgesSourceAdapter();
			}
			@Override
			public Adapter caseExecutionEdgeDestination(ExecutionEdgeDestination object) {
				return createExecutionEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseWireEdgeDestination(WireEdgeDestination object) {
				return createWireEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseDataFlowEdgeDestination(DataFlowEdgeDestination object) {
				return createDataFlowEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseDataFlowEdgesSource(DataFlowEdgesSource object) {
				return createDataFlowEdgesSourceAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseContainsWires(ContainsWires object) {
				return createContainsWiresAdapter();
			}
			@Override
			public Adapter caseShouldntContainWires(ShouldntContainWires object) {
				return createShouldntContainWiresAdapter();
			}
			@Override
			public Adapter caseWireEdgesSource(WireEdgesSource object) {
				return createWireEdgesSourceAdapter();
			}
			@Override
			public Adapter caseChainedOperation(ChainedOperation object) {
				return createChainedOperationAdapter();
			}
			@Override
			public Adapter caseCondition(Condition object) {
				return createConditionAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.StartNode <em>Start Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.StartNode
	 * @generated
	 */
	public Adapter createStartNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.CancelNode <em>Cancel Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.CancelNode
	 * @generated
	 */
	public Adapter createCancelNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.FinishNode <em>Finish Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.FinishNode
	 * @generated
	 */
	public Adapter createFinishNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.DecisionNode <em>Decision Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.DecisionNode
	 * @generated
	 */
	public Adapter createDecisionNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.DecisionOperation <em>Decision Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.DecisionOperation
	 * @generated
	 */
	public Adapter createDecisionOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.DecisionCondition <em>Decision Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.DecisionCondition
	 * @generated
	 */
	public Adapter createDecisionConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.SplitNode <em>Split Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.SplitNode
	 * @generated
	 */
	public Adapter createSplitNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.operations.JoinNode <em>Join Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.operations.JoinNode
	 * @generated
	 */
	public Adapter createJoinNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.WireEdgeDestination <em>Wire Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.WireEdgeDestination
	 * @generated
	 */
	public Adapter createWireEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.GeneratedElement <em>Generated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.GeneratedElement
	 * @generated
	 */
	public Adapter createGeneratedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.DataFlowEdgeDestination <em>Data Flow Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.DataFlowEdgeDestination
	 * @generated
	 */
	public Adapter createDataFlowEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ExecutionEdgeDestination <em>Execution Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ExecutionEdgeDestination
	 * @generated
	 */
	public Adapter createExecutionEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.DataFlowEdgesSource <em>Data Flow Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.DataFlowEdgesSource
	 * @generated
	 */
	public Adapter createDataFlowEdgesSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ContainsWires <em>Contains Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ContainsWires
	 * @generated
	 */
	public Adapter createContainsWiresAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ShouldntContainWires <em>Shouldnt Contain Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ShouldntContainWires
	 * @generated
	 */
	public Adapter createShouldntContainWiresAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.WireEdgesSource <em>Wire Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.WireEdgesSource
	 * @generated
	 */
	public Adapter createWireEdgesSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ExecutionEdgesSource <em>Execution Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ExecutionEdgesSource
	 * @generated
	 */
	public Adapter createExecutionEdgesSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ChainedOperation <em>Chained Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ChainedOperation
	 * @generated
	 */
	public Adapter createChainedOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.Condition
	 * @generated
	 */
	public Adapter createConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ActivityNode
	 * @generated
	 */
	public Adapter createActivityNodeAdapter() {
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

} //OperationsAdapterFactory
