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
 * A representation of the model object '<em><b>Provides Edge Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the destination of a {@model ProvidesEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.users.ProvidesEdgeDestination#getInProvidesEdges <em>In Provides Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.users.UsersPackage#getProvidesEdgeDestination()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface ProvidesEdgeDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Provides Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ProvidesEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ProvidesEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Provides Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Provides Edges</em>' reference list.
	 * @see org.openiaml.model.model.users.UsersPackage#getProvidesEdgeDestination_InProvidesEdges()
	 * @see org.openiaml.model.model.wires.ProvidesEdge#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<ProvidesEdge> getInProvidesEdges();

} // ProvidesEdgeDestination
