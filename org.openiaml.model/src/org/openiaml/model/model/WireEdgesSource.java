/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.WireEdgesSource#getOutEdges <em>Out Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getWireEdgesSource()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='temporarily contains wires until GMF bug is fixed '"
 * @generated
 */
public interface WireEdgesSource extends ShouldntContainWires {
	/**
	 * Returns the value of the '<em><b>Out Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.WireEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.WireEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Edges</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getWireEdgesSource_OutEdges()
	 * @see org.openiaml.model.model.WireEdge#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<WireEdge> getOutEdges();

} // WireEdgesSource
