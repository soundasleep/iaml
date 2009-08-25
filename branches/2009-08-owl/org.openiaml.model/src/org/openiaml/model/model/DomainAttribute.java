/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainAttribute#isPrimaryKey <em>Primary Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainAttribute()
 * @model annotation="http://openiaml.org/comment changed='0.2 to extend the abstract counterpart\r\n0.3 to remove the abstract extension, and added \"type\" attribute'"
 * @generated
 */
public interface DomainAttribute extends ApplicationElement {

	/**
	 * Returns the value of the '<em><b>Primary Key</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key</em>' attribute.
	 * @see #setPrimaryKey(boolean)
	 * @see org.openiaml.model.model.ModelPackage#getDomainAttribute_PrimaryKey()
	 * @model default="false"
	 *        annotation="http://openiaml.org/comment comment='added in 0.4'"
	 * @generated
	 */
	boolean isPrimaryKey();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainAttribute#isPrimaryKey <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Key</em>' attribute.
	 * @see #isPrimaryKey()
	 * @generated
	 */
	void setPrimaryKey(boolean value);
} // DomainAttribute
