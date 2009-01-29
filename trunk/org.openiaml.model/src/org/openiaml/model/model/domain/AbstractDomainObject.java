/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import org.eclipse.emf.common.util.EList;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ContainsWires;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Domain Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.AbstractDomainObject#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainObject()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDomainObject extends ApplicationElement, ContainsWires {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.domain.AbstractDomainAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.openiaml.model.model.domain.DomainPackage#getAbstractDomainObject_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractDomainAttribute> getAttributes();

} // AbstractDomainObject
