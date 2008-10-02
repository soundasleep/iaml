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
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.Operation#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.openiaml.model.model.Operation#getCodeBlock <em>Code Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getOperation()
 * @model abstract="true"
 * @generated
 */
public interface Operation extends WireEdgeDestination, NamedElement {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.OperationParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getOperation_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<OperationParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Code Block</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code Block</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code Block</em>' attribute.
	 * @see #setCodeBlock(String)
	 * @see org.openiaml.model.model.ModelPackage#getOperation_CodeBlock()
	 * @model
	 * @generated
	 */
	String getCodeBlock();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.Operation#getCodeBlock <em>Code Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code Block</em>' attribute.
	 * @see #getCodeBlock()
	 * @generated
	 */
	void setCodeBlock(String value);

} // Operation
