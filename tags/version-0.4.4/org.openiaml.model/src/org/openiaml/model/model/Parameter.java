/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.xsd.XSDSimpleTypeDefinition;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Incoming values to an {@model Operation operation} executed by a {@model RunAction} can be accessed via Parameters.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Parameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends NamedElement, DataFlowEdgesSource {

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(XSDSimpleTypeDefinition)
	 * @see org.openiaml.model.model.ModelPackage#getParameter_Type()
	 * @model
	 * @generated
	 */
	XSDSimpleTypeDefinition getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Parameter#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(XSDSimpleTypeDefinition value);
} // Parameter
