/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graphical Representation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getGraphicalRepresentation()
 * @model
 * @generated
 */
public interface GraphicalRepresentation extends Semantic {

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' container reference.
	 * @see #setContainingClass(EMFClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getGraphicalRepresentation_ContainingClass()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getIcon
	 * @model opposite="icon" transient="false"
	 * @generated
	 */
	EMFClass getContainingClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass <em>Containing Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' container reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EMFClass value);
} // GraphicalRepresentation
