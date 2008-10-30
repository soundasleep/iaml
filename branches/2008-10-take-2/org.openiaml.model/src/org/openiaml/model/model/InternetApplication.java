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
 * A representation of the model object '<em><b>Internet Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getDomainStores <em>Domain Stores</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getInternetApplication()
 * @model annotation="http://openiaml.org/comment comment='we cannot have InternetApplications inside of other ElementContainers, thus we don\'t define it as an ApplicationElement' comment2='but why can we have it as an ActivityEdgeSource? I don\'t think there are any examples of InternetApplication wire --> something else; all the wires are from objects INSIDE the IA' editor='org.openiaml.model.diagram'"
 * @generated
 */
public interface InternetApplication extends ContainsOperations, ContainsEventTriggers, NamedElement, ContainsWires, GeneratesElements {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getInternetApplication_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElementProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.ApplicationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getInternetApplication_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApplicationElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Domain Stores</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainStore}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Stores</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Stores</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getInternetApplication_DomainStores()
	 * @model containment="true"
	 * @generated
	 */
	EList<DomainStore> getDomainStores();
	
	/**hack */
	String getName();

} // InternetApplication
