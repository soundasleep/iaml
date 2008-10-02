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
 * A representation of the model object '<em><b>Domain Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainObject#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObject#getDomainObjectWires <em>Domain Object Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainObject()
 * @model
 * @generated
 */
public interface DomainObject extends ApplicationElement {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainObject_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<DomainAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Domain Object Wires</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.WireEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Object Wires</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Object Wires</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainObject_DomainObjectWires()
	 * @model containment="true"
	 * @generated
	 */
	EList<WireEdge> getDomainObjectWires();

} // DomainObject
