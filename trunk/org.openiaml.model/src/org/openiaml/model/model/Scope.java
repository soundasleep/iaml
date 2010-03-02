/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Scope#getEntryGate <em>Entry Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getExitGate <em>Exit Gate</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getValues <em>Values</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getElements <em>Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.Scope#getOnInit <em>On Init</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getScope()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment changed='0.4: \'domainObjects\', \'domainViews\', \'domainInstances\' references removed\r\n0.4.2: renamed from \'Scope\' to \'AbstractScope\'; added \'gate\' reference'"
 * @generated
 */
public interface Scope extends GeneratesElements, ContainsWires, ContainsScopes, NamedElement, GeneratedElement, WireEdgesSource, WireEdgeDestination, ContainsConditions, CanBeSynced, Accessible {
	/**
	 * Returns the value of the '<em><b>Entry Gate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry Gate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Gate</em>' containment reference.
	 * @see #setEntryGate(EntryGate)
	 * @see org.openiaml.model.model.ModelPackage#getScope_EntryGate()
	 * @model containment="true"
	 * @generated
	 */
	EntryGate getEntryGate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Scope#getEntryGate <em>Entry Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry Gate</em>' containment reference.
	 * @see #getEntryGate()
	 * @generated
	 */
	void setEntryGate(EntryGate value);

	/**
	 * Returns the value of the '<em><b>Exit Gate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exit Gate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exit Gate</em>' containment reference.
	 * @see #setExitGate(ExitGate)
	 * @see org.openiaml.model.model.ModelPackage#getScope_ExitGate()
	 * @model containment="true"
	 * @generated
	 */
	ExitGate getExitGate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Scope#getExitGate <em>Exit Gate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exit Gate</em>' containment reference.
	 * @see #getExitGate()
	 * @generated
	 */
	void setExitGate(ExitGate value);

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
	 * @see org.openiaml.model.model.ModelPackage#getScope_Properties()
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
	 * @see org.openiaml.model.model.ModelPackage#getScope_Values()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<StaticValue> getValues();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.QueryParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getScope_Parameters()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4' about='ideally this might only be placed in Page and Session, but allowing it within VisibleThing allows us to (1) reuse our visual editor, and (2) increases modularity of components perhaps?'"
	 * @generated
	 */
	EList<QueryParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getScope_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElement> getElements();

	/**
	 * Returns the value of the '<em><b>On Init</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Init</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Init</em>' containment reference.
	 * @see #setOnInit(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getScope_OnInit()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='added in 0.4.4'"
	 * @generated
	 */
	EventTrigger getOnInit();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Scope#getOnInit <em>On Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Init</em>' containment reference.
	 * @see #getOnInit()
	 * @generated
	 */
	void setOnInit(EventTrigger value);

} // Scope
