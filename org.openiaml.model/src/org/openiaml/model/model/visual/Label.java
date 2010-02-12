/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual;

import org.openiaml.model.model.VisibleThing;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a static block of text that is not user-editable.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.visual.Label#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.visual.VisualPackage#getLabel()
 * @model annotation="http://openiaml.org/comment added='0.4.3'"
 * @generated
 */
public interface Label extends VisibleThing {

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see org.openiaml.model.model.visual.VisualPackage#getLabel_DefaultValue()
	 * @model default=""
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.visual.Label#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);
} // Label
