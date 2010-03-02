/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Run Instance Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connects a {@model EventTrigger} to a {@model Operation}, allowing it to be executed. May have incoming {@model ConditionWire}s.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.RunInstanceWire#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getRunInstanceWire()
 * @model annotation="http://openiaml.org/comment comment='RunWire: a composite wire that contains ExecutionWires/etc'"
 * @generated
 */
public interface RunInstanceWire extends WireDestination, ParameterEdgeDestination, SingleWire, NamedElement, GeneratesElements, ConditionEdgeDestination {

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see org.openiaml.model.model.wires.WiresPackage#getRunInstanceWire_Priority()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.wires.RunInstanceWire#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);
} // RunInstanceWire
