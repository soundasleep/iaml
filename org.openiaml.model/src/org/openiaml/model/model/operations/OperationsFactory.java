/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.operations.OperationsPackage
 * @generated
 */
public interface OperationsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OperationsFactory eINSTANCE = org.openiaml.model.model.operations.impl.OperationsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Start Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Node</em>'.
	 * @generated
	 */
	StartNode createStartNode();

	/**
	 * Returns a new object of class '<em>Cancel Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cancel Node</em>'.
	 * @generated
	 */
	CancelNode createCancelNode();

	/**
	 * Returns a new object of class '<em>Finish Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Finish Node</em>'.
	 * @generated
	 */
	FinishNode createFinishNode();

	/**
	 * Returns a new object of class '<em>Decision Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Node</em>'.
	 * @generated
	 */
	DecisionNode createDecisionNode();

	/**
	 * Returns a new object of class '<em>Decision Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Operation</em>'.
	 * @generated
	 */
	DecisionOperation createDecisionOperation();

	/**
	 * Returns a new object of class '<em>Decision Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Condition</em>'.
	 * @generated
	 */
	DecisionCondition createDecisionCondition();

	/**
	 * Returns a new object of class '<em>Split Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Split Node</em>'.
	 * @generated
	 */
	SplitNode createSplitNode();

	/**
	 * Returns a new object of class '<em>Join Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Node</em>'.
	 * @generated
	 */
	JoinNode createJoinNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OperationsPackage getOperationsPackage();

} //OperationsFactory
