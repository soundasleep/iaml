/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An {@mode Action} represents the runtime feature of the {@model EventTrigger Event}-{@model Condition}-{@model Action} paradigm. Also see: {@model Wire}
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Action#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.Action#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getAction()
 * @model abstract="true"
 * @generated
 */
public interface Action extends GeneratedElement {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ActionSource#getOutActions <em>Out Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ActionSource)
	 * @see org.openiaml.model.model.ModelPackage#getAction_From()
	 * @see org.openiaml.model.model.ActionSource#getOutActions
	 * @model opposite="outActions" required="true"
	 * @generated
	 */
	ActionSource getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Action#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ActionSource value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ActionDestination#getInActions <em>In Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ActionDestination)
	 * @see org.openiaml.model.model.ModelPackage#getAction_To()
	 * @see org.openiaml.model.model.ActionDestination#getInActions
	 * @model opposite="inActions" required="true"
	 * @generated
	 */
	ActionDestination getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Action#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ActionDestination value);

} // Action
