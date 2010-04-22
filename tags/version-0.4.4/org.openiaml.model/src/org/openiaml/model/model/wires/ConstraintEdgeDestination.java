/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Edge Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the destination of a {@model ConstraintEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ConstraintEdgeDestination#getInConstraintEdges <em>In Constraint Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdgeDestination()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface ConstraintEdgeDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Constraint Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ConstraintEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ConstraintEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Constraint Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Constraint Edges</em>' reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getConstraintEdgeDestination_InConstraintEdges()
	 * @see org.openiaml.model.model.wires.ConstraintEdge#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<ConstraintEdge> getInConstraintEdges();

} // ConstraintEdgeDestination