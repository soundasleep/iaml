/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Login Handler Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.components.LoginHandlerKey#getSecretKey <em>Secret Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.components.ComponentsPackage#getLoginHandlerKey()
 * @model
 * @generated
 */
public interface LoginHandlerKey extends LoginHandler {
	/**
	 * Returns the value of the '<em><b>Secret Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secret Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secret Key</em>' attribute.
	 * @see #setSecretKey(String)
	 * @see org.openiaml.model.model.components.ComponentsPackage#getLoginHandlerKey_SecretKey()
	 * @model
	 * @generated
	 */
	String getSecretKey();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.components.LoginHandlerKey#getSecretKey <em>Secret Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secret Key</em>' attribute.
	 * @see #getSecretKey()
	 * @generated
	 */
	void setSecretKey(String value);

} // LoginHandlerKey
