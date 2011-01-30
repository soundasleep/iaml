/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelDocumentation#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelDocumentation#getReferences <em>References</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.ModelDocumentation#getMetrics <em>Metrics</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelDocumentation()
 * @model
 * @generated
 */
public interface ModelDocumentation extends EObject {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.EMFClass}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelDocumentation_Classes()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<EMFClass> getClasses();

	/**
	 * Returns the value of the '<em><b>References</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.Reference}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.Reference#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelDocumentation_References()
	 * @see org.openiaml.docs.modeldoc.Reference#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<Reference> getReferences();

	/**
	 * Returns the value of the '<em><b>Metrics</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.Metric}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metrics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metrics</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getModelDocumentation_Metrics()
	 * @model containment="true"
	 * @generated
	 */
	EList<Metric> getMetrics();

} // ModelDocumentation
