/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Wires</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getActions <em>Actions</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getExtendsEdges <em>Extends Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getRequiresEdges <em>Requires Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getProvidesEdges <em>Provides Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getConstraintEdges <em>Constraint Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getConditionEdges <em>Condition Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getContainsWires()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment changed='0.4.3 added \'parameterEdges\' containment reference'"
 * @generated
 */
public interface ContainsWires extends EObject {
	/**
	 * Returns the value of the '<em><b>Wires</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Wire}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wires</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wires</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_Wires()
	 * @model containment="true"
	 * @generated
	 */
	EList<Wire> getWires();

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_Actions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getActions();

	/**
	 * Returns the value of the '<em><b>Parameter Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ParameterEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_ParameterEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterEdge> getParameterEdges();

	/**
	 * Returns the value of the '<em><b>Extends Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ExtendsEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_ExtendsEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExtendsEdge> getExtendsEdges();

	/**
	 * Returns the value of the '<em><b>Requires Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.RequiresEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_RequiresEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiresEdge> getRequiresEdges();

	/**
	 * Returns the value of the '<em><b>Provides Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ProvidesEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provides Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provides Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_ProvidesEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProvidesEdge> getProvidesEdges();

	/**
	 * Returns the value of the '<em><b>Constraint Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ConstraintEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_ConstraintEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstraintEdge> getConstraintEdges();

	/**
	 * Returns the value of the '<em><b>Condition Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ConditionEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getContainsWires_ConditionEdges()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConditionEdge> getConditionEdges();

} // ContainsWires
