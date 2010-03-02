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
 * <!-- begin-model-doc -->
 * The top-level type of a model instance, representing all of the information required to define an Internet application.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getDomainStores <em>Domain Stores</em>}</li>
 *   <li>{@link org.openiaml.model.model.InternetApplication#getRuntimeUrl <em>Runtime Url</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getInternetApplication()
 * @model annotation="http://openiaml.org/comment comment='we cannot have InternetApplications inside of other ElementContainers, thus we don\'t define it as an ApplicationElement' comment2='but why can we have it as an ActivityEdgeSource? I don\'t think there are any examples of InternetApplication wire --> something else; all the wires are from objects INSIDE the IA' editor='org.openiaml.model.diagram' changed='0.4.2 no longer a Scope\r\n0.4.2 removed \'sessions\' containment'"
 * @generated
 */
public interface InternetApplication extends ContainsOperations, NamedElement, ContainsWires, GeneratesElements, ContainsConditions, ContainsScopes {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.Property}.
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
	EList<Property> getProperties();

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
	 *        annotation="http://openiaml.org/comment comment='not sure if this is necessary anymore... because now objects are stored as part of their scopes' changed='0.2 to extend the abstract counterpart'"
	 * @generated
	 */
	EList<DomainStore> getDomainStores();

	/**
	 * Returns the value of the '<em><b>Runtime Url</b></em>' attribute.
	 * The default value is <code>"http://localhost:8080/output/"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Url</em>' attribute.
	 * @see #setRuntimeUrl(String)
	 * @see org.openiaml.model.model.ModelPackage#getInternetApplication_RuntimeUrl()
	 * @model default="http://localhost:8080/output/"
	 * @generated
	 */
	String getRuntimeUrl();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.InternetApplication#getRuntimeUrl <em>Runtime Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Url</em>' attribute.
	 * @see #getRuntimeUrl()
	 * @generated
	 */
	void setRuntimeUrl(String value);

} // InternetApplication
