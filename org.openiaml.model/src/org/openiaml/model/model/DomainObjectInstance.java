/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.common.util.EList;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Object Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an instance of a {@model DomainObject}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getStrQuery <em>Str Query</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#isAutosave <em>Autosave</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface DomainObjectInstance extends ApplicationElement, ContainsWires, ParameterEdgesSource, ParameterEdgeDestination {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.model.model.DomainAttributeInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Attributes()
	 * @model containment="true"
	 *        annotation="http://openiaml.org/comment changed='0.3: changed from DomainAttribute to DomainAttributeInstance'"
	 * @generated
	 */
	EList<DomainAttributeInstance> getAttributes();

	/**
	 * Returns the value of the '<em><b>Str Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Str Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Str Query</em>' attribute.
	 * @see #setStrQuery(String)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_StrQuery()
	 * @model
	 * @generated
	 */
	String getStrQuery();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getStrQuery <em>Str Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Str Query</em>' attribute.
	 * @see #getStrQuery()
	 * @generated
	 */
	void setStrQuery(String value);

	/**
	 * Returns the value of the '<em><b>Autosave</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Autosave</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Autosave</em>' attribute.
	 * @see #setAutosave(boolean)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Autosave()
	 * @model default="false"
	 *        annotation="http://openiaml.org/comment added='0.3' changed='0.4.3 to default \'false\' instead of \'true\''"
	 * @generated
	 */
	boolean isAutosave();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#isAutosave <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Autosave</em>' attribute.
	 * @see #isAutosave()
	 * @generated
	 */
	void setAutosave(boolean value);

} // DomainObjectInstance
