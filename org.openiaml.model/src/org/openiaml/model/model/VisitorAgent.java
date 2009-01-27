/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visitor Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.VisitorAgent#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getVisitorAgent()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface VisitorAgent extends EObject {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getVisitorAgent_Properties()
	 * @model
	 * @generated
	 */
	EList<ApplicationElementProperty> getProperties();

} // VisitorAgent
