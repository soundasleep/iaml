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
 * A representation of the model object '<em><b>Application Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract type representing model elements that {@model ContainsConditions}, {@model ContainsOperations} and {@model ContainsProperties}. This is used primarily to reduce the number of diagram editors in the IAML environment.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ApplicationElement#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getApplicationElement()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment editor='org.openiaml.model.diagram.element' comment='Scope supertype added in 0.2'"
 * @generated
 */
public interface ApplicationElement extends ContainsOperations, NamedElement, WireSource, WireDestination, GeneratesElements, ContainsConditions, CanBeSynced, ContainsProperties {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.StaticValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getApplicationElement_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<StaticValue> getValues();

} // ApplicationElement