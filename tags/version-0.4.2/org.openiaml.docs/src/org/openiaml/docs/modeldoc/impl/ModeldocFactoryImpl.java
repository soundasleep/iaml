/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.docs.modeldoc.AdditionalDocumentation;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.ConstraintType;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.EMFReference;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.ImplementationNote;
import org.openiaml.docs.modeldoc.InferenceSemantic;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocClassReference;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModelReference;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.OperationalSemantic;
import org.openiaml.docs.modeldoc.Template;
import org.openiaml.docs.modeldoc.TemplateFile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModeldocFactoryImpl extends EFactoryImpl implements ModeldocFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModeldocFactory init() {
		try {
			ModeldocFactory theModeldocFactory = (ModeldocFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/modeldoc/2009/09"); 
			if (theModeldocFactory != null) {
				return theModeldocFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModeldocFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldocFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModeldocPackage.MODEL_DOCUMENTATION: return createModelDocumentation();
			case ModeldocPackage.EMF_CLASS: return createEMFClass();
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION: return createAdditionalDocumentation();
			case ModeldocPackage.EXAMPLE: return createExample();
			case ModeldocPackage.OPERATIONAL_SEMANTIC: return createOperationalSemantic();
			case ModeldocPackage.GRAPHICAL_REPRESENTATION: return createGraphicalRepresentation();
			case ModeldocPackage.INFERENCE_SEMANTIC: return createInferenceSemantic();
			case ModeldocPackage.IMPLEMENTATION_NOTE: return createImplementationNote();
			case ModeldocPackage.CONSTRAINT: return createConstraint();
			case ModeldocPackage.MODEL_EXTENSION: return createModelExtension();
			case ModeldocPackage.JAVA_CLASS: return createJavaClass();
			case ModeldocPackage.JAVA_METHOD: return createJavaMethod();
			case ModeldocPackage.MODEL_REFERENCE: return createModelReference();
			case ModeldocPackage.DROOLS_PACKAGE: return createDroolsPackage();
			case ModeldocPackage.DROOLS_RULE: return createDroolsRule();
			case ModeldocPackage.FILE_REFERENCE: return createFileReference();
			case ModeldocPackage.FILE_LINE_REFERENCE: return createFileLineReference();
			case ModeldocPackage.JAVADOC_TAG_ELEMENT: return createJavadocTagElement();
			case ModeldocPackage.JAVADOC_TEXT_ELEMENT: return createJavadocTextElement();
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE: return createJavadocMethodReference();
			case ModeldocPackage.JAVADOC_CLASS_REFERENCE: return createJavadocClassReference();
			case ModeldocPackage.EMF_ATTRIBUTE: return createEMFAttribute();
			case ModeldocPackage.EMF_REFERENCE: return createEMFReference();
			case ModeldocPackage.TEMPLATE_FILE: return createTemplateFile();
			case ModeldocPackage.TEMPLATE: return createTemplate();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModeldocPackage.CONSTRAINT_TYPE:
				return createConstraintTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModeldocPackage.CONSTRAINT_TYPE:
				return convertConstraintTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelDocumentation createModelDocumentation() {
		ModelDocumentationImpl modelDocumentation = new ModelDocumentationImpl();
		return modelDocumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFClass createEMFClass() {
		EMFClassImpl emfClass = new EMFClassImpl();
		return emfClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdditionalDocumentation createAdditionalDocumentation() {
		AdditionalDocumentationImpl additionalDocumentation = new AdditionalDocumentationImpl();
		return additionalDocumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Example createExample() {
		ExampleImpl example = new ExampleImpl();
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalSemantic createOperationalSemantic() {
		OperationalSemanticImpl operationalSemantic = new OperationalSemanticImpl();
		return operationalSemantic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicalRepresentation createGraphicalRepresentation() {
		GraphicalRepresentationImpl graphicalRepresentation = new GraphicalRepresentationImpl();
		return graphicalRepresentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InferenceSemantic createInferenceSemantic() {
		InferenceSemanticImpl inferenceSemantic = new InferenceSemanticImpl();
		return inferenceSemantic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationNote createImplementationNote() {
		ImplementationNoteImpl implementationNote = new ImplementationNoteImpl();
		return implementationNote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint createConstraint() {
		ConstraintImpl constraint = new ConstraintImpl();
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelExtension createModelExtension() {
		ModelExtensionImpl modelExtension = new ModelExtensionImpl();
		return modelExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass createJavaClass() {
		JavaClassImpl javaClass = new JavaClassImpl();
		return javaClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaMethod createJavaMethod() {
		JavaMethodImpl javaMethod = new JavaMethodImpl();
		return javaMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelReference createModelReference() {
		ModelReferenceImpl modelReference = new ModelReferenceImpl();
		return modelReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DroolsRule createDroolsRule() {
		DroolsRuleImpl droolsRule = new DroolsRuleImpl();
		return droolsRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DroolsPackage createDroolsPackage() {
		DroolsPackageImpl droolsPackage = new DroolsPackageImpl();
		return droolsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileReference createFileReference() {
		FileReferenceImpl fileReference = new FileReferenceImpl();
		return fileReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileLineReference createFileLineReference() {
		FileLineReferenceImpl fileLineReference = new FileLineReferenceImpl();
		return fileLineReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocTagElement createJavadocTagElement() {
		JavadocTagElementImpl javadocTagElement = new JavadocTagElementImpl();
		return javadocTagElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocTextElement createJavadocTextElement() {
		JavadocTextElementImpl javadocTextElement = new JavadocTextElementImpl();
		return javadocTextElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocMethodReference createJavadocMethodReference() {
		JavadocMethodReferenceImpl javadocMethodReference = new JavadocMethodReferenceImpl();
		return javadocMethodReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocClassReference createJavadocClassReference() {
		JavadocClassReferenceImpl javadocClassReference = new JavadocClassReferenceImpl();
		return javadocClassReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFAttribute createEMFAttribute() {
		EMFAttributeImpl emfAttribute = new EMFAttributeImpl();
		return emfAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFReference createEMFReference() {
		EMFReferenceImpl emfReference = new EMFReferenceImpl();
		return emfReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateFile createTemplateFile() {
		TemplateFileImpl templateFile = new TemplateFileImpl();
		return templateFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintType createConstraintTypeFromString(EDataType eDataType, String initialValue) {
		ConstraintType result = ConstraintType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConstraintTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldocPackage getModeldocPackage() {
		return (ModeldocPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModeldocPackage getPackage() {
		return ModeldocPackage.eINSTANCE;
	}

} //ModeldocFactoryImpl
