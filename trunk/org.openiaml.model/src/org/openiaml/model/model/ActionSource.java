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
 * A representation of the model object '<em><b>Action Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.ActionSource#getOutActions <em>Out Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getActionSource()
 * @model interface="true" abstract="true"
 *        annotation="http://openiaml.org/comment comment='temporarily contains wires until GMF bug is fixed '"
 * @generated
 */
public interface ActionSource extends ShouldntContainWires {
	/**
	 * Returns the value of the '<em><b>Out Actions</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Action}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.model.model.Action#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Actions</em>' reference list.
	 * @see org.openiaml.model.model.ModelPackage#getActionSource_OutActions()
	 * @see org.openiaml.model.model.Action#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Action> getOutActions();

} // ActionSource
