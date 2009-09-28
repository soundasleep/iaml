/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMF Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getTargetClass <em>Target Class</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getSupertypes <em>Supertypes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getSubtypes <em>Subtypes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getRuntimeClass <em>Runtime Class</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getTagline <em>Tagline</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getOperationalSemantics <em>Operational Semantics</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getInferenceSemantics <em>Inference Semantics</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getGraphicalRepresentations <em>Graphical Representations</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getExamples <em>Examples</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getParent <em>Parent</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getReferences <em>References</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.EMFClass#getAdditionalDocumentation <em>Additional Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass()
 * @model
 * @generated
 */
public interface EMFClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Target Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Class</em>' reference.
	 * @see #setTargetClass(EClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_TargetClass()
	 * @model
	 * @generated
	 */
	EClass getTargetClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#getTargetClass <em>Target Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Class</em>' reference.
	 * @see #getTargetClass()
	 * @generated
	 */
	void setTargetClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Abstract()
	 * @model required="true"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(boolean)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Interface()
	 * @model required="true"
	 * @generated
	 */
	boolean isInterface();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#isInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #isInterface()
	 * @generated
	 */
	void setInterface(boolean value);

	/**
	 * Returns the value of the '<em><b>Supertypes</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.EMFClass}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getSubtypes <em>Subtypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supertypes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supertypes</em>' reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Supertypes()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getSubtypes
	 * @model opposite="subtypes"
	 * @generated
	 */
	EList<EMFClass> getSupertypes();

	/**
	 * Returns the value of the '<em><b>Subtypes</b></em>' reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.EMFClass}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFClass#getSupertypes <em>Supertypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subtypes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subtypes</em>' reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Subtypes()
	 * @see org.openiaml.docs.modeldoc.EMFClass#getSupertypes
	 * @model opposite="supertypes"
	 * @generated
	 */
	EList<EMFClass> getSubtypes();

	/**
	 * Returns the value of the '<em><b>Runtime Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Class</em>' reference.
	 * @see #setRuntimeClass(JavaClass)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_RuntimeClass()
	 * @model
	 * @generated
	 */
	JavaClass getRuntimeClass();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#getRuntimeClass <em>Runtime Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Class</em>' reference.
	 * @see #getRuntimeClass()
	 * @generated
	 */
	void setRuntimeClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Tagline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tagline</em>' containment reference.
	 * @see #setTagline(JavadocTagElement)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Tagline()
	 * @model containment="true"
	 * @generated
	 */
	JavadocTagElement getTagline();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#getTagline <em>Tagline</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tagline</em>' containment reference.
	 * @see #getTagline()
	 * @generated
	 */
	void setTagline(JavadocTagElement value);

	/**
	 * Returns the value of the '<em><b>Operational Semantics</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.OperationalSemantic}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.OperationalSemantic#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operational Semantics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operational Semantics</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_OperationalSemantics()
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<OperationalSemantic> getOperationalSemantics();

	/**
	 * Returns the value of the '<em><b>Inference Semantics</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.InferenceSemantic}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inference Semantics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inference Semantics</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_InferenceSemantics()
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<InferenceSemantic> getInferenceSemantics();

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.Constraint}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.Constraint#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Constraints()
	 * @see org.openiaml.docs.modeldoc.Constraint#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<Constraint> getConstraints();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.ModelExtension}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.ModelExtension#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Extensions()
	 * @see org.openiaml.docs.modeldoc.ModelExtension#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<ModelExtension> getExtensions();

	/**
	 * Returns the value of the '<em><b>Graphical Representations</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.GraphicalRepresentation}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graphical Representations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graphical Representations</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_GraphicalRepresentations()
	 * @see org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<GraphicalRepresentation> getGraphicalRepresentations();

	/**
	 * Returns the value of the '<em><b>Examples</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.Example}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.Example#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Examples</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Examples</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Examples()
	 * @see org.openiaml.docs.modeldoc.Example#getContainingClass
	 * @model opposite="containingClass" containment="true"
	 * @generated
	 */
	EList<Example> getExamples();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.ModelDocumentation#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(ModelDocumentation)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Parent()
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation#getClasses
	 * @model opposite="classes" transient="false"
	 * @generated
	 */
	ModelDocumentation getParent();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.EMFClass#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ModelDocumentation value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.EMFAttribute}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFAttribute#getContainingType <em>Containing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_Attributes()
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getContainingType
	 * @model opposite="containingType" containment="true"
	 * @generated
	 */
	EList<EMFAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>References</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.EMFReference}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.EMFReference#getContainingType <em>Containing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_References()
	 * @see org.openiaml.docs.modeldoc.EMFReference#getContainingType
	 * @model opposite="containingType" containment="true"
	 * @generated
	 */
	EList<EMFReference> getReferences();

	/**
	 * Returns the value of the '<em><b>Additional Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.AdditionalDocumentation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additional Documentation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additional Documentation</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getEMFClass_AdditionalDocumentation()
	 * @model containment="true"
	 * @generated
	 */
	EList<AdditionalDocumentation> getAdditionalDocumentation();

} // EMFClass
