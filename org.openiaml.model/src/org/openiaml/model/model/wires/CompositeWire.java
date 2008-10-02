/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.openiaml.model.model.wires.CompositeWire#getWireWires <em>Wire Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire()
 * @model annotation="http://openiaml.org/comment comment='ideally this would be abstract; but we want to have a \"wire\" diagram editor, and the root element needs to be concrete. the other option is to have multiple diagram editors per concrete wire...' comment2='easy visualisation: all composite wires are dashed' comment3='only composite wires have a name now' editor='org.openiaml.model.diagram.wire' comment4='a wire shouldn\'t have parameters; but the operations contained within need them rendered.'"
 * @generated
 */
public interface CompositeWire extends WireEdge, NamedElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElementProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Event Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.EventTrigger}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Triggers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Triggers</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_EventTriggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventTrigger> getEventTriggers();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Wire Wires</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.WireEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wire Wires</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wire Wires</em>' containment reference list.
	 * @see org.openiaml.model.model.wires.WiresPackage#getCompositeWire_WireWires()
	 * @model containment="true"
	 * @generated
	 */
	EList<WireEdge> getWireWires();

} // CompositeWire
