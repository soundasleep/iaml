/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Object Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getStrQuery <em>Str Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface DomainObjectInstance extends ApplicationElement, ContainsWires {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Attributes()
	 * @model
	 * @generated
	 */
	EList<DomainAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Str Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Str Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Str Query</em>' attribute.
	 * @see #setStrQuery(String)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_StrQuery()
	 * @model
	 * @generated
	 */
	String getStrQuery();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getStrQuery <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Str Query</em>' attribute.
	 * @see #getStrQuery()
	 * @generated
	 */
	void setStrQuery(String value);

} // DomainObjectInstance
