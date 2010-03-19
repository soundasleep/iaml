/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.openiaml.model.model.wires.ParameterEdgesSource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a single value, which is accessible at runtime but can never change.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.StaticValue#getValue <em>Value</em>}</li>
 *   <li>{@link org.openiaml.model.model.StaticValue#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getStaticValue()
 * @model
 * @generated
 */
public interface StaticValue extends NamedElement, WireSource, DataFlowEdgesSource, ParameterEdgesSource {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.openiaml.model.model.ModelPackage#getStaticValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.StaticValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

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
	 * @see org.openiaml.model.model.ModelPackage#getStaticValue_Type()
	 * @model
	 * @generated
	 */
	XSDSimpleTypeDefinition getType();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.StaticValue#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(XSDSimpleTypeDefinition value);

} // StaticValue
