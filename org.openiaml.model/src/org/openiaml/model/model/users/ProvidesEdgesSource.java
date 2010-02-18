/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.wires.ProvidesEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the source of a {@model ProvidesEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.users.ProvidesEdgesSource#getOutProvidesEdges <em>Out Provides Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.users.UsersPackage#getProvidesEdgesSource()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface ProvidesEdgesSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Out Provides Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ProvidesEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ProvidesEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Provides Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Provides Edges</em>' reference list.
	 * @see org.openiaml.model.model.users.UsersPackage#getProvidesEdgesSource_OutProvidesEdges()
	 * @see org.openiaml.model.model.wires.ProvidesEdge#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<ProvidesEdge> getOutProvidesEdges();

} // ProvidesEdgesSource
