/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.wires.RequiresEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requires Edge Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the destination of a {@model RequiresEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.users.RequiresEdgeDestination#getInRequiresEdges <em>In Requires Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.users.UsersPackage#getRequiresEdgeDestination()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface RequiresEdgeDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Requires Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.RequiresEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.RequiresEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Requires Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Requires Edges</em>' reference list.
	 * @see org.openiaml.model.model.users.UsersPackage#getRequiresEdgeDestination_InRequiresEdges()
	 * @see org.openiaml.model.model.wires.RequiresEdge#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<RequiresEdge> getInRequiresEdges();

} // RequiresEdgeDestination
