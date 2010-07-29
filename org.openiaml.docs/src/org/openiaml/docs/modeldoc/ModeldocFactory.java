/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.docs.modeldoc.ModeldocPackage
 * @generated
 */
public interface ModeldocFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModeldocFactory eINSTANCE = org.openiaml.docs.modeldoc.impl.ModeldocFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model Documentation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Documentation</em>'.
	 * @generated
	 */
	ModelDocumentation createModelDocumentation();

	/**
	 * Returns a new object of class '<em>EMF Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EMF Class</em>'.
	 * @generated
	 */
	EMFClass createEMFClass();

	/**
	 * Returns a new object of class '<em>Additional Documentation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Additional Documentation</em>'.
	 * @generated
	 */
	AdditionalDocumentation createAdditionalDocumentation();

	/**
	 * Returns a new object of class '<em>Example</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Example</em>'.
	 * @generated
	 */
	Example createExample();

	/**
	 * Returns a new object of class '<em>Operational Semantic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operational Semantic</em>'.
	 * @generated
	 */
	OperationalSemantic createOperationalSemantic();

	/**
	 * Returns a new object of class '<em>Graphical Representation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Graphical Representation</em>'.
	 * @generated
	 */
	GraphicalRepresentation createGraphicalRepresentation();

	/**
	 * Returns a new object of class '<em>Inference Semantic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inference Semantic</em>'.
	 * @generated
	 */
	InferenceSemantic createInferenceSemantic();

	/**
	 * Returns a new object of class '<em>Implementation Note</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implementation Note</em>'.
	 * @generated
	 */
	ImplementationNote createImplementationNote();

	/**
	 * Returns a new object of class '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint</em>'.
	 * @generated
	 */
	Constraint createConstraint();

	/**
	 * Returns a new object of class '<em>Model Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Extension</em>'.
	 * @generated
	 */
	ModelExtension createModelExtension();

	/**
	 * Returns a new object of class '<em>Java Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Java Class</em>'.
	 * @generated
	 */
	JavaClass createJavaClass();

	/**
	 * Returns a new object of class '<em>Java Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Java Method</em>'.
	 * @generated
	 */
	JavaMethod createJavaMethod();

	/**
	 * Returns a new object of class '<em>Model Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Reference</em>'.
	 * @generated
	 */
	ModelReference createModelReference();

	/**
	 * Returns a new object of class '<em>Drools Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Drools Rule</em>'.
	 * @generated
	 */
	DroolsRule createDroolsRule();

	/**
	 * Returns a new object of class '<em>Drools Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Drools Package</em>'.
	 * @generated
	 */
	DroolsPackage createDroolsPackage();

	/**
	 * Returns a new object of class '<em>File Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Reference</em>'.
	 * @generated
	 */
	FileReference createFileReference();

	/**
	 * Returns a new object of class '<em>File Line Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Line Reference</em>'.
	 * @generated
	 */
	FileLineReference createFileLineReference();

	/**
	 * Returns a new object of class '<em>Javadoc Tag Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Javadoc Tag Element</em>'.
	 * @generated
	 */
	JavadocTagElement createJavadocTagElement();

	/**
	 * Returns a new object of class '<em>Javadoc Text Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Javadoc Text Element</em>'.
	 * @generated
	 */
	JavadocTextElement createJavadocTextElement();

	/**
	 * Returns a new object of class '<em>Javadoc Method Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Javadoc Method Reference</em>'.
	 * @generated
	 */
	JavadocMethodReference createJavadocMethodReference();

	/**
	 * Returns a new object of class '<em>Javadoc Class Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Javadoc Class Reference</em>'.
	 * @generated
	 */
	JavadocClassReference createJavadocClassReference();

	/**
	 * Returns a new object of class '<em>EMF Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EMF Attribute</em>'.
	 * @generated
	 */
	EMFAttribute createEMFAttribute();

	/**
	 * Returns a new object of class '<em>EMF Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EMF Reference</em>'.
	 * @generated
	 */
	EMFReference createEMFReference();

	/**
	 * Returns a new object of class '<em>Template File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template File</em>'.
	 * @generated
	 */
	TemplateFile createTemplateFile();

	/**
	 * Returns a new object of class '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template</em>'.
	 * @generated
	 */
	Template createTemplate();

	/**
	 * Returns a new object of class '<em>Metric</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metric</em>'.
	 * @generated
	 */
	Metric createMetric();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModeldocPackage getModeldocPackage();

} //ModeldocFactory
