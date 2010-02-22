/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.operations.Arithmetic;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.wires.ConditionEdgesSource;

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
 * @see org.openiaml.model.model.operations.OperationsPackage
 * @generated
 */
public class OperationsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OperationsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationsSwitch() {
		if (modelPackage == null) {
			modelPackage = OperationsPackage.eINSTANCE;
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
			case OperationsPackage.START_NODE: {
				StartNode startNode = (StartNode)theEObject;
				T result = caseStartNode(startNode);
				if (result == null) result = caseActivityNode(startNode);
				if (result == null) result = caseExecutionEdgesSource(startNode);
				if (result == null) result = caseGeneratedElement(startNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.CANCEL_NODE: {
				CancelNode cancelNode = (CancelNode)theEObject;
				T result = caseCancelNode(cancelNode);
				if (result == null) result = caseActivityNode(cancelNode);
				if (result == null) result = caseExecutionEdgeDestination(cancelNode);
				if (result == null) result = caseGeneratedElement(cancelNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.FINISH_NODE: {
				FinishNode finishNode = (FinishNode)theEObject;
				T result = caseFinishNode(finishNode);
				if (result == null) result = caseActivityNode(finishNode);
				if (result == null) result = caseExecutionEdgeDestination(finishNode);
				if (result == null) result = caseGeneratedElement(finishNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.DECISION_NODE: {
				DecisionNode decisionNode = (DecisionNode)theEObject;
				T result = caseDecisionNode(decisionNode);
				if (result == null) result = caseActivityNode(decisionNode);
				if (result == null) result = caseExecutionEdgesSource(decisionNode);
				if (result == null) result = caseExecutionEdgeDestination(decisionNode);
				if (result == null) result = caseGeneratedElement(decisionNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.DECISION_OPERATION: {
				DecisionOperation decisionOperation = (DecisionOperation)theEObject;
				T result = caseDecisionOperation(decisionOperation);
				if (result == null) result = casePrimitiveOperation(decisionOperation);
				if (result == null) result = caseOperation(decisionOperation);
				if (result == null) result = caseExecutionEdgesSource(decisionOperation);
				if (result == null) result = caseWireEdgesSource(decisionOperation);
				if (result == null) result = caseWireEdgeDestination(decisionOperation);
				if (result == null) result = caseNamedElement(decisionOperation);
				if (result == null) result = caseDataFlowEdgeDestination(decisionOperation);
				if (result == null) result = caseExecutionEdgeDestination(decisionOperation);
				if (result == null) result = caseDataFlowEdgesSource(decisionOperation);
				if (result == null) result = caseShouldntContainWires(decisionOperation);
				if (result == null) result = caseGeneratedElement(decisionOperation);
				if (result == null) result = caseContainsWires(decisionOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.DECISION_CONDITION: {
				DecisionCondition decisionCondition = (DecisionCondition)theEObject;
				T result = caseDecisionCondition(decisionCondition);
				if (result == null) result = caseCondition(decisionCondition);
				if (result == null) result = caseExecutionEdgesSource(decisionCondition);
				if (result == null) result = caseExecutionEdgeDestination(decisionCondition);
				if (result == null) result = caseDataFlowEdgeDestination(decisionCondition);
				if (result == null) result = caseWireEdgesSource(decisionCondition);
				if (result == null) result = caseDataFlowEdgesSource(decisionCondition);
				if (result == null) result = caseNamedElement(decisionCondition);
				if (result == null) result = caseConditionEdgesSource(decisionCondition);
				if (result == null) result = caseShouldntContainWires(decisionCondition);
				if (result == null) result = caseGeneratedElement(decisionCondition);
				if (result == null) result = caseContainsWires(decisionCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.SPLIT_NODE: {
				SplitNode splitNode = (SplitNode)theEObject;
				T result = caseSplitNode(splitNode);
				if (result == null) result = caseActivityNode(splitNode);
				if (result == null) result = caseExecutionEdgesSource(splitNode);
				if (result == null) result = caseExecutionEdgeDestination(splitNode);
				if (result == null) result = caseGeneratedElement(splitNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.JOIN_NODE: {
				JoinNode joinNode = (JoinNode)theEObject;
				T result = caseJoinNode(joinNode);
				if (result == null) result = caseActivityNode(joinNode);
				if (result == null) result = caseExecutionEdgesSource(joinNode);
				if (result == null) result = caseExecutionEdgeDestination(joinNode);
				if (result == null) result = caseGeneratedElement(joinNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.OPERATION_CALL_NODE: {
				OperationCallNode operationCallNode = (OperationCallNode)theEObject;
				T result = caseOperationCallNode(operationCallNode);
				if (result == null) result = caseActivityNode(operationCallNode);
				if (result == null) result = caseExecutionEdgesSource(operationCallNode);
				if (result == null) result = caseExecutionEdgeDestination(operationCallNode);
				if (result == null) result = caseDataFlowEdgeDestination(operationCallNode);
				if (result == null) result = caseDataFlowEdgesSource(operationCallNode);
				if (result == null) result = caseWireEdgesSource(operationCallNode);
				if (result == null) result = caseNamedElement(operationCallNode);
				if (result == null) result = caseGeneratedElement(operationCallNode);
				if (result == null) result = caseShouldntContainWires(operationCallNode);
				if (result == null) result = caseContainsWires(operationCallNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OperationsPackage.ARITHMETIC: {
				Arithmetic arithmetic = (Arithmetic)theEObject;
				T result = caseArithmetic(arithmetic);
				if (result == null) result = caseActivityNode(arithmetic);
				if (result == null) result = caseDataFlowEdgeDestination(arithmetic);
				if (result == null) result = caseDataFlowEdgesSource(arithmetic);
				if (result == null) result = caseGeneratedElement(arithmetic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartNode(StartNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cancel Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cancel Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCancelNode(CancelNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Finish Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Finish Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFinishNode(FinishNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decision Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decision Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecisionNode(DecisionNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decision Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decision Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecisionOperation(DecisionOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decision Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decision Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecisionCondition(DecisionCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Split Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Split Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSplitNode(SplitNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoinNode(JoinNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCallNode(OperationCallNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arithmetic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arithmetic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArithmetic(Arithmetic object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgeDestination(WireEdgeDestination object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Data Flow Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Flow Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFlowEdgeDestination(DataFlowEdgeDestination object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Execution Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionEdgeDestination(ExecutionEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Flow Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Flow Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFlowEdgesSource(DataFlowEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgesSource(WireEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveOperation(PrimitiveOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionEdgesSource(ConditionEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionEdgesSource(ExecutionEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCondition(Condition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityNode(ActivityNode object) {
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

} //OperationsSwitch
