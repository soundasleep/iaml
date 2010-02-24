/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.wires.ParameterEdgesSource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visible Thing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract type representing application elements which have some sort of visible representation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getOnClick <em>On Click</em>}</li>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getOnEdit <em>On Edit</em>}</li>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getOnAccess <em>On Access</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getVisibleThing()
 * @model annotation="http://openiaml.org/comment comment='this used to mean nothing; now anything that extends VisibleThing (which unforuntately needs to be concrete) has an editor' editor='org.openiaml.model.diagram.visual' changed='0.4.2 to no longer extend ApplicationElementContainer\r\n0.4.2 extends ContainsConditions\r\n0.4.2 extends ContainsOperatons\r\n0.4.2 extends ContainsEventTriggers\r\n0.4.2 extends ContainsWires\r\n0.4.2 extends WireEdgesSource\r\n0.4.2 extends WireEdgeDestination\r\n0.4.2 removed \'sessions\' containment\r\n0.4.4 no longer ContainsEventTriggers; events inserted manually'"
 * @generated
 */
public interface VisibleThing extends ContainsConditions, ContainsOperations, ContainsWires, WireEdgesSource, WireEdgeDestination, NamedElement, GeneratedElement, GeneratesElements, CanBeSynced, ParameterEdgesSource {

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.VisibleThing}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_Children()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<VisibleThing> getChildren();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_Properties()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<Property> getProperties();

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.StaticValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_Values()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<StaticValue> getValues();

	/**
	 * Returns the value of the '<em><b>On Click</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Click</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Click</em>' containment reference.
	 * @see #setOnClick(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_OnClick()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnClick();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.VisibleThing#getOnClick <em>On Click</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Click</em>' containment reference.
	 * @see #getOnClick()
	 * @generated
	 */
	void setOnClick(EventTrigger value);

	/**
	 * Returns the value of the '<em><b>On Edit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Edit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Edit</em>' containment reference.
	 * @see #setOnEdit(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_OnEdit()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnEdit();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.VisibleThing#getOnEdit <em>On Edit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Edit</em>' containment reference.
	 * @see #getOnEdit()
	 * @generated
	 */
	void setOnEdit(EventTrigger value);

	/**
	 * Returns the value of the '<em><b>On Access</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Access</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Access</em>' containment reference.
	 * @see #setOnAccess(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_OnAccess()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnAccess();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.VisibleThing#getOnAccess <em>On Access</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Access</em>' containment reference.
	 * @see #getOnAccess()
	 * @generated
	 */
	void setOnAccess(EventTrigger value);
} // VisibleThing
