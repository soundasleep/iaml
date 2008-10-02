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
 * A representation of the model object '<em><b>Application Element Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ApplicationElementProperty#getInValueReferences <em>In Value References</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getApplicationElementProperty()
 * @model annotation="http://openiaml.org/todo todo='maybe rename? delete subclasses?'"
 * @generated
 */
public interface ApplicationElementProperty extends NamedElement, WireEdgesSource, WireEdgeDestination {

	/**
	 * Returns the value of the '<em><b>In Value References</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.ValueReference#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Value References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Value References</em>' container reference.
	 * @see #setInValueReferences(ValueReference)
	 * @see org.openiaml.model.model.ModelPackage#getApplicationElementProperty_InValueReferences()
	 * @see org.openiaml.model.model.ValueReference#getValue
	 * @model opposite="value" transient="false"
	 * @generated
	 */
	ValueReference getInValueReferences();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.ApplicationElementProperty#getInValueReferences <em>In Value References</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Value References</em>' container reference.
	 * @see #getInValueReferences()
	 * @generated
	 */
	void setInValueReferences(ValueReference value);
} // ApplicationElementProperty
