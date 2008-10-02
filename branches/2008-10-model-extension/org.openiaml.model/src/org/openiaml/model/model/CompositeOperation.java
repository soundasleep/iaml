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
 * A representation of the model object '<em><b>Composite Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.CompositeOperation#getSubOperations <em>Sub Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation()
 * @model
 * @generated
 */
public interface CompositeOperation extends Operation {
	/**
	 * Returns the value of the '<em><b>Sub Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Operations</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getCompositeOperation_SubOperations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getSubOperations();

} // CompositeOperation
