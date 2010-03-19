/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual;

import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.openiaml.model.model.VisibleThing;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Text Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A single text field, storing a single text value, which may be edited by the user.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.visual.InputTextField#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.visual.VisualPackage#getInputTextField()
 * @model
 * @generated
 */
public interface InputTextField extends VisibleThing {

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
	 * @see org.openiaml.model.model.visual.VisualPackage#getInputTextField_Type()
	 * @model
	 * @generated
	 */
	XSDSimpleTypeDefinition getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.visual.InputTextField#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(XSDSimpleTypeDefinition value);
} // InputTextField
