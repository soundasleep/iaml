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
 * A representation of the model object '<em><b>Parameter Edges Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something which may be used as the source of a {@model ParameterEdge}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.ParameterEdgesSource#getOutParameterEdges <em>Out Parameter Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdgesSource()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.4.3'"
 * @generated
 */
public interface ParameterEdgesSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Out Parameter Edges</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.wires.ParameterEdge}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.wires.ParameterEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Parameter Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Parameter Edges</em>' reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getParameterEdgesSource_OutParameterEdges()
	 * @see org.openiaml.model.model.wires.ParameterEdge#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<ParameterEdge> getOutParameterEdges();

} // ParameterEdgesSource
