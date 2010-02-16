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
 * A representation of the model object '<em><b>Parameter Edge Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the destination of a {@model ParameterEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ParameterEdgeDestination#getInParameterEdges <em>In Parameter Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdgeDestination()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface ParameterEdgeDestination extends EObject {
	/**
	 * Returns the value of the '<em><b>In Parameter Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ParameterEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ParameterEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Parameter Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Parameter Edges</em>' reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdgeDestination_InParameterEdges()
	 * @see org.openiaml.model.model.wires.ParameterEdge#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<ParameterEdge> getInParameterEdges();

} // ParameterEdgeDestination
