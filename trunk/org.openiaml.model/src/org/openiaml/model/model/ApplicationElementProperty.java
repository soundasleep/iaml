/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.openiaml.model.model.wires.ParameterEdgesSource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Element Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a single value, accessible and modifiable at runtime.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ApplicationElementProperty#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getApplicationElementProperty()
 * @model
 * @generated
 */
public interface ApplicationElementProperty extends NamedElement, WireEdgesSource, WireEdgeDestination, DataFlowEdgesSource, DataFlowEdgeDestination, ParameterEdgesSource {

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see org.openiaml.model.model.ModelPackage#getApplicationElementProperty_DefaultValue()
	 * @model annotation="http://openiaml.org/comment added='0.2'"
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.ApplicationElementProperty#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);
} // ApplicationElementProperty
