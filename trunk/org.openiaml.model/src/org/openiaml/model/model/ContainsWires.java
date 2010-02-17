/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Wires</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getParameterEdges <em>Parameter Edges</em>}</li>
 *   <li>{@link org.openiaml.model.model.ContainsWires#getExtendsEdges <em>Extends Edges</em>}</li>
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
	 * The list contents are of type {@link org.openiaml.model.model.WireEdge}.
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
	EList<WireEdge> getWires();

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

} // ContainsWires
