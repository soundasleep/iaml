/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.AbstractScope;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.scopes.Scope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a single browser window instance.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.visual.Page#getUrl <em>Url</em>}</li>
 *   <li>{@link org.openiaml.model.model.visual.Page#getScopes <em>Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.visual.VisualPackage#getPage()
 * @model annotation="http://openiaml.org/comment comment='Scope supertype added in 0.2' comment2='represents the scope Window'"
 * @generated
 */
public interface Page extends VisibleThing, AbstractScope {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.openiaml.model.model.visual.VisualPackage#getPage_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.visual.Page#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Scopes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.scopes.Scope}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scopes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scopes</em>' containment reference list.
	 * @see org.openiaml.model.model.visual.VisualPackage#getPage_Scopes()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment added='0.4.2'"
	 * @generated
	 */
	EList<Scope> getScopes();

} // Page
