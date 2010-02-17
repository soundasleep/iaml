/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Attribute Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an instance of a {@link DomainAttribute}, which is likely contained within a {@link DomainObjectInstance}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainAttributeInstance#isAutosave <em>Autosave</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainAttributeInstance()
 * @model annotation="http://openiaml.org/comment added='0.3'"
 * @generated
 */
public interface DomainAttributeInstance extends ApplicationElement, ExtendsEdgesSource, ExtendsEdgeDestination {

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
	 * @see org.openiaml.model.model.ModelPackage#getDomainAttributeInstance_Autosave()
	 * @model default="false"
	 *        annotation="http://openiaml.org/comment added='0.3' changed='0.4.3 to default \'false\' instead of \'true\''"
	 * @generated
	 */
	boolean isAutosave();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainAttributeInstance#isAutosave <em>Autosave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Autosave</em>' attribute.
	 * @see #isAutosave()
	 * @generated
	 */
	void setAutosave(boolean value);
} // DomainAttributeInstance
