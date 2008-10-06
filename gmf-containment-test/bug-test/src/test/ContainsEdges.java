/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package test;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Edges</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link test.ContainsEdges#getEdges <em>Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see test.TestPackage#getContainsEdges()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ContainsEdges extends EObject {
	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link test.Edge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see test.TestPackage#getContainsEdges_Edges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

} // ContainsEdges
