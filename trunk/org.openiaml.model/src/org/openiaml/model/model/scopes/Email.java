/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsProperties;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Label;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Email</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an e-mail. The lifecycle is as follows: {@model PrimitiveOperation send} -> {@model EventTrigger sent} or {@model EventTrigger failed}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getTo <em>To</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getToName <em>To Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getFrom <em>From</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getFromName <em>From Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getOnSent <em>On Sent</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getOnFailure <em>On Failure</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getButtons <em>Buttons</em>}</li>
 *   <li>{@link org.openiaml.model.model.scopes.Email#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail()
 * @model
 * @generated
 */
public interface Email extends NamedElement, ContainsOperations, Scope, ContainsWires, WireSource, WireDestination, ContainsConditions, ContainsProperties, CanBeSynced {
	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_To()
	 * @model
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' attribute.
	 * @see #setSubject(String)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_Subject()
	 * @model
	 * @generated
	 */
	String getSubject();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getSubject <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' attribute.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(String value);

	/**
	 * Returns the value of the '<em><b>To Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Name</em>' attribute.
	 * @see #setToName(String)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_ToName()
	 * @model
	 * @generated
	 */
	String getToName();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getToName <em>To Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Name</em>' attribute.
	 * @see #getToName()
	 * @generated
	 */
	void setToName(String value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_From()
	 * @model
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Returns the value of the '<em><b>From Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Name</em>' attribute.
	 * @see #setFromName(String)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_FromName()
	 * @model
	 * @generated
	 */
	String getFromName();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getFromName <em>From Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Name</em>' attribute.
	 * @see #getFromName()
	 * @generated
	 */
	void setFromName(String value);

	/**
	 * Returns the value of the '<em><b>On Sent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Sent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Sent</em>' containment reference.
	 * @see #setOnSent(EventTrigger)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_OnSent()
	 * @model containment="true"
	 * @generated
	 */
	EventTrigger getOnSent();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getOnSent <em>On Sent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Sent</em>' containment reference.
	 * @see #getOnSent()
	 * @generated
	 */
	void setOnSent(EventTrigger value);

	/**
	 * Returns the value of the '<em><b>On Failure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Failure</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Failure</em>' containment reference.
	 * @see #setOnFailure(EventTrigger)
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_OnFailure()
	 * @model containment="true"
	 * @generated
	 */
	EventTrigger getOnFailure();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.scopes.Email#getOnFailure <em>On Failure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Failure</em>' containment reference.
	 * @see #getOnFailure()
	 * @generated
	 */
	void setOnFailure(EventTrigger value);

	/**
	 * Returns the value of the '<em><b>Buttons</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.visual.Button}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buttons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buttons</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_Buttons()
	 * @model containment="true"
	 * @generated
	 */
	EList<Button> getButtons();

	/**
	 * Returns the value of the '<em><b>Labels</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.visual.Label}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Labels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' containment reference list.
	 * @see org.openiaml.model.model.scopes.ScopesPackage#getEmail_Labels()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getLabels();

} // Email
