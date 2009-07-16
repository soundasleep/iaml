/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.scopes.Session;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visible Thing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.VisibleThing#getSessions <em>Sessions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getVisibleThing()
 * @model annotation="http://openiaml.org/comment comment='this used to mean nothing; now anything that extends VisibleThing (which unforuntately needs to be concrete) has an editor' editor='org.openiaml.model.diagram.visual'"
 * @generated
 */
public interface VisibleThing extends ApplicationElementContainer {

	/**
	 * Returns the value of the '<em><b>Sessions</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.scopes.Session}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sessions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sessions</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getVisibleThing_Sessions()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	EList<Session> getSessions();
} // VisibleThing
