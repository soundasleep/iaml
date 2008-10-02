/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.WireEdgesSource#getEdges <em>Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWireEdgesSource()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface WireEdgesSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.WireEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getWireEdgesSource_Edges()
	 * @see org.openiaml.model.model.WireEdge#getFrom
	 * @model opposite="from" containment="true"
	 * @generated
	 */
	EList<WireEdge> getEdges();

} // WireEdgesSource
