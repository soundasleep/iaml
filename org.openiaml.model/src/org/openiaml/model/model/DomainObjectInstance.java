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
 * Represents an iterator over instances of a {@model DomainObject}. Can be {@model #next navigated}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getStrQuery <em>Str Query</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#isAutosave <em>Autosave</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getOnIterate <em>On Iterate</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getPrevious <em>Previous</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getNext <em>Next</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getReset <em>Reset</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getSkip <em>Skip</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getHasPrevious <em>Has Previous</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getHasNext <em>Has Next</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getEmpty <em>Empty</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getResults <em>Results</em>}</li>
 *   <li>{@link org.openiaml.model.model.DomainObjectInstance#getJump <em>Jump</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface DomainObjectInstance extends ApplicationElement, ContainsWires, ParameterEdgeDestination, ParameterEdgesSource, Editable {
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

	/**
	 * Returns the value of the '<em><b>On Iterate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This {@model EventTrigger event} is called whenever the {@model #current current instance} changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>On Iterate</em>' containment reference.
	 * @see #setOnIterate(EventTrigger)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_OnIterate()
	 * @model containment="true"
	 * @generated
	 */
	EventTrigger getOnIterate();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getOnIterate <em>On Iterate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Iterate</em>' containment reference.
	 * @see #getOnIterate()
	 * @generated
	 */
	void setOnIterate(EventTrigger value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Go to the previous result. Will throw an error if {@model #hasPrevious} would be false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Previous</em>' containment reference.
	 * @see #setPrevious(Operation)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Previous()
	 * @model containment="true"
	 * @generated
	 */
	Operation getPrevious();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getPrevious <em>Previous</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous</em>' containment reference.
	 * @see #getPrevious()
	 * @generated
	 */
	void setPrevious(Operation value);

	/**
	 * Returns the value of the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Go to the next result. Will throw an error if {@model #hasNext} would be false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Next</em>' containment reference.
	 * @see #setNext(Operation)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Next()
	 * @model containment="true"
	 * @generated
	 */
	Operation getNext();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getNext <em>Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' containment reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(Operation value);

	/**
	 * Returns the value of the '<em><b>Reset</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reset</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Start at result number 0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reset</em>' containment reference.
	 * @see #setReset(Operation)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Reset()
	 * @model containment="true"
	 * @generated
	 */
	Operation getReset();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getReset <em>Reset</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reset</em>' containment reference.
	 * @see #getReset()
	 * @generated
	 */
	void setReset(Operation value);

	/**
	 * Returns the value of the '<em><b>Skip</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Skip the given number of results. Can be negative to go backwards.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Skip</em>' containment reference.
	 * @see #setSkip(Operation)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Skip()
	 * @model containment="true"
	 * @generated
	 */
	Operation getSkip();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getSkip <em>Skip</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip</em>' containment reference.
	 * @see #getSkip()
	 * @generated
	 */
	void setSkip(Operation value);

	/**
	 * Returns the value of the '<em><b>Has Previous</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Previous</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if navigating to the {@model #previous} result will not be out-of-bounds.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Previous</em>' containment reference.
	 * @see #setHasPrevious(Condition)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_HasPrevious()
	 * @model containment="true"
	 * @generated
	 */
	Condition getHasPrevious();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getHasPrevious <em>Has Previous</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Previous</em>' containment reference.
	 * @see #getHasPrevious()
	 * @generated
	 */
	void setHasPrevious(Condition value);

	/**
	 * Returns the value of the '<em><b>Has Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Next</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if navigating to the {@model #next} result will not be out-of-bounds.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Next</em>' containment reference.
	 * @see #setHasNext(Condition)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_HasNext()
	 * @model containment="true"
	 * @generated
	 */
	Condition getHasNext();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getHasNext <em>Has Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Next</em>' containment reference.
	 * @see #getHasNext()
	 * @generated
	 */
	void setHasNext(Condition value);

	/**
	 * Returns the value of the '<em><b>Empty</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empty</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if the current result set is empty.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Empty</em>' containment reference.
	 * @see #setEmpty(Condition)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Empty()
	 * @model containment="true"
	 * @generated
	 */
	Condition getEmpty();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getEmpty <em>Empty</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empty</em>' containment reference.
	 * @see #getEmpty()
	 * @generated
	 */
	void setEmpty(Condition value);

	/**
	 * Returns the value of the '<em><b>Results</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of results in the current result set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Results</em>' containment reference.
	 * @see #setResults(Property)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Results()
	 * @model containment="true"
	 * @generated
	 */
	Property getResults();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getResults <em>Results</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Results</em>' containment reference.
	 * @see #getResults()
	 * @generated
	 */
	void setResults(Property value);

	/**
	 * Returns the value of the '<em><b>Jump</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Jump to the specific result number, starting from 0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jump</em>' containment reference.
	 * @see #setJump(Operation)
	 * @see org.openiaml.model.model.ModelPackage#getDomainObjectInstance_Jump()
	 * @model containment="true"
	 * @generated
	 */
	Operation getJump();

	/**
	 * Sets the value of the '{@link org.openiaml.model.model.DomainObjectInstance#getJump <em>Jump</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jump</em>' containment reference.
	 * @see #getJump()
	 * @generated
	 */
	void setJump(Operation value);

} // DomainObjectInstance
