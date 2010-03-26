/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
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
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocClassReference;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModelReference;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.OperationalSemantic;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.docs.modeldoc.Semantic;
import org.openiaml.docs.modeldoc.Template;
import org.openiaml.docs.modeldoc.TemplateFile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModeldocPackageImpl extends EPackageImpl implements ModeldocPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelDocumentationEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emfClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass additionalDocumentationEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semanticEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exampleEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationalSemanticEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicalRepresentationEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inferenceSemanticEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implementationNoteEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelExtensionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaClassEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaMethodEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass droolsRuleEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass droolsPackageEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileLineReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocFragmentEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocTagElementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocTextElementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaElementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocMethodReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocClassReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emfAttributeEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emfReferenceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateFileEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum constraintTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModeldocPackageImpl() {
		super(eNS_URI, ModeldocFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModeldocPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModeldocPackage init() {
		if (isInited) return (ModeldocPackage)EPackage.Registry.INSTANCE.getEPackage(ModeldocPackage.eNS_URI);

		// Obtain or create and register package
		ModeldocPackageImpl theModeldocPackage = (ModeldocPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModeldocPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModeldocPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theModeldocPackage.createPackageContents();

		// Initialize created meta-data
		theModeldocPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModeldocPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModeldocPackage.eNS_URI, theModeldocPackage);
		return theModeldocPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelDocumentation() {
		return modelDocumentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelDocumentation_Classes() {
		return (EReference)modelDocumentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelDocumentation_References() {
		return (EReference)modelDocumentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEMFClass() {
		return emfClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_TargetClass() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFClass_Name() {
		return (EAttribute)emfClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFClass_Abstract() {
		return (EAttribute)emfClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFClass_Interface() {
		return (EAttribute)emfClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Supertypes() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Subtypes() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_RuntimeClass() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Tagline() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_OperationalSemantics() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_InferenceSemantics() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Constraints() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Extensions() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Icon() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_GmfEditor() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Examples() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Parent() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_Attributes() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_References() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_AdditionalDocumentation() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFClass_ImplementationNotes() {
		return (EReference)emfClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdditionalDocumentation() {
		return additionalDocumentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdditionalDocumentation_Reference() {
		return (EReference)additionalDocumentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdditionalDocumentation_Description() {
		return (EReference)additionalDocumentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemantic() {
		return semanticEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemantic_Reference() {
		return (EReference)semanticEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExample() {
		return exampleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExample_ExampleModel() {
		return (EReference)exampleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExample_ContainingClass() {
		return (EReference)exampleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExample_Description() {
		return (EReference)exampleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExample_ExampleLocation() {
		return (EReference)exampleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationalSemantic() {
		return operationalSemanticEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationalSemantic_Category() {
		return (EAttribute)operationalSemanticEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemantic_Description() {
		return (EReference)operationalSemanticEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemantic_ContainingClass() {
		return (EReference)operationalSemanticEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicalRepresentation() {
		return graphicalRepresentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicalRepresentation_ContainingClass() {
		return (EReference)graphicalRepresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInferenceSemantic() {
		return inferenceSemanticEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInferenceSemantic_Category() {
		return (EAttribute)inferenceSemanticEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInferenceSemantic_Description() {
		return (EReference)inferenceSemanticEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInferenceSemantic_ContainingClass() {
		return (EReference)inferenceSemanticEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplementationNote() {
		return implementationNoteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImplementationNote_Category() {
		return (EAttribute)implementationNoteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationNote_Description() {
		return (EReference)implementationNoteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationNote_ContainingClass() {
		return (EReference)implementationNoteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_Constraint() {
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_Type() {
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_Message() {
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraint_ContainingClass() {
		return (EReference)constraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelExtension() {
		return modelExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelExtension_Name() {
		return (EAttribute)modelExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelExtension_Value() {
		return (EAttribute)modelExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelExtension_ContainingClass() {
		return (EReference)modelExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReference_Parent() {
		return (EReference)referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaClass() {
		return javaClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaClass_Plugin() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaClass_Package() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaClass_Name() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavaClass_Methods() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaMethod() {
		return javaMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaMethod_Name() {
		return (EAttribute)javaMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaMethod_Line() {
		return (EAttribute)javaMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavaMethod_JavaClass() {
		return (EReference)javaMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelReference() {
		return modelReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelReference_Plugin() {
		return (EAttribute)modelReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelReference_Package() {
		return (EAttribute)modelReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelReference_Name() {
		return (EAttribute)modelReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDroolsRule() {
		return droolsRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDroolsRule_Name() {
		return (EAttribute)droolsRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDroolsRule_Line() {
		return (EAttribute)droolsRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDroolsRule_Package() {
		return (EReference)droolsRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDroolsPackage() {
		return droolsPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDroolsPackage_Plugin() {
		return (EAttribute)droolsPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDroolsPackage_Package() {
		return (EAttribute)droolsPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDroolsPackage_Name() {
		return (EAttribute)droolsPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDroolsPackage_Rules() {
		return (EReference)droolsPackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileReference() {
		return fileReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileReference_Plugin() {
		return (EAttribute)fileReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileReference_Package() {
		return (EAttribute)fileReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileReference_Name() {
		return (EAttribute)fileReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFileReference_Lines() {
		return (EReference)fileReferenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileLineReference() {
		return fileLineReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFileLineReference_File() {
		return (EReference)fileLineReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileLineReference_Line() {
		return (EAttribute)fileLineReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadocFragment() {
		return javadocFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadocTagElement() {
		return javadocTagElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavadocTagElement_Name() {
		return (EAttribute)javadocTagElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadocTagElement_Fragments() {
		return (EReference)javadocTagElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadocTagElement_JavaParent() {
		return (EReference)javadocTagElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadocTextElement() {
		return javadocTextElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavadocTextElement_Value() {
		return (EAttribute)javadocTextElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaElement() {
		return javaElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavaElement_Javadocs() {
		return (EReference)javaElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadocMethodReference() {
		return javadocMethodReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadocMethodReference_Reference() {
		return (EReference)javadocMethodReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadocClassReference() {
		return javadocClassReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadocClassReference_Reference() {
		return (EReference)javadocClassReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEMFAttribute() {
		return emfAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_Name() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_Id() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_LowerBound() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_UpperBound() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_Type() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFAttribute_ContainingType() {
		return (EReference)emfAttributeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFAttribute_DefaultLiteral() {
		return (EAttribute)emfAttributeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEMFReference() {
		return emfReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFReference_Name() {
		return (EAttribute)emfReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFReference_LowerBound() {
		return (EAttribute)emfReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFReference_UpperBound() {
		return (EAttribute)emfReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFReference_Containment() {
		return (EAttribute)emfReferenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFReference_Type() {
		return (EReference)emfReferenceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFReference_ContainingType() {
		return (EReference)emfReferenceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEMFReference_TypeName() {
		return (EAttribute)emfReferenceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMFReference_Opposite() {
		return (EReference)emfReferenceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemplateFile() {
		return templateFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplateFile_Plugin() {
		return (EAttribute)templateFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplateFile_Package() {
		return (EAttribute)templateFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplateFile_Name() {
		return (EAttribute)templateFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTemplateFile_Templates() {
		return (EReference)templateFileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemplate() {
		return templateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplate_Name() {
		return (EAttribute)templateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplate_Type() {
		return (EAttribute)templateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplate_Line() {
		return (EAttribute)templateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTemplate_TemplateFile() {
		return (EReference)templateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConstraintType() {
		return constraintTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldocFactory getModeldocFactory() {
		return (ModeldocFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelDocumentationEClass = createEClass(MODEL_DOCUMENTATION);
		createEReference(modelDocumentationEClass, MODEL_DOCUMENTATION__CLASSES);
		createEReference(modelDocumentationEClass, MODEL_DOCUMENTATION__REFERENCES);

		emfClassEClass = createEClass(EMF_CLASS);
		createEReference(emfClassEClass, EMF_CLASS__TARGET_CLASS);
		createEAttribute(emfClassEClass, EMF_CLASS__NAME);
		createEAttribute(emfClassEClass, EMF_CLASS__ABSTRACT);
		createEAttribute(emfClassEClass, EMF_CLASS__INTERFACE);
		createEReference(emfClassEClass, EMF_CLASS__SUPERTYPES);
		createEReference(emfClassEClass, EMF_CLASS__SUBTYPES);
		createEReference(emfClassEClass, EMF_CLASS__RUNTIME_CLASS);
		createEReference(emfClassEClass, EMF_CLASS__TAGLINE);
		createEReference(emfClassEClass, EMF_CLASS__OPERATIONAL_SEMANTICS);
		createEReference(emfClassEClass, EMF_CLASS__INFERENCE_SEMANTICS);
		createEReference(emfClassEClass, EMF_CLASS__IMPLEMENTATION_NOTES);
		createEReference(emfClassEClass, EMF_CLASS__CONSTRAINTS);
		createEReference(emfClassEClass, EMF_CLASS__EXTENSIONS);
		createEReference(emfClassEClass, EMF_CLASS__ICON);
		createEReference(emfClassEClass, EMF_CLASS__GMF_EDITOR);
		createEReference(emfClassEClass, EMF_CLASS__EXAMPLES);
		createEReference(emfClassEClass, EMF_CLASS__PARENT);
		createEReference(emfClassEClass, EMF_CLASS__ATTRIBUTES);
		createEReference(emfClassEClass, EMF_CLASS__REFERENCES);
		createEReference(emfClassEClass, EMF_CLASS__ADDITIONAL_DOCUMENTATION);

		additionalDocumentationEClass = createEClass(ADDITIONAL_DOCUMENTATION);
		createEReference(additionalDocumentationEClass, ADDITIONAL_DOCUMENTATION__REFERENCE);
		createEReference(additionalDocumentationEClass, ADDITIONAL_DOCUMENTATION__DESCRIPTION);

		semanticEClass = createEClass(SEMANTIC);
		createEReference(semanticEClass, SEMANTIC__REFERENCE);

		exampleEClass = createEClass(EXAMPLE);
		createEReference(exampleEClass, EXAMPLE__EXAMPLE_MODEL);
		createEReference(exampleEClass, EXAMPLE__CONTAINING_CLASS);
		createEReference(exampleEClass, EXAMPLE__DESCRIPTION);
		createEReference(exampleEClass, EXAMPLE__EXAMPLE_LOCATION);

		operationalSemanticEClass = createEClass(OPERATIONAL_SEMANTIC);
		createEAttribute(operationalSemanticEClass, OPERATIONAL_SEMANTIC__CATEGORY);
		createEReference(operationalSemanticEClass, OPERATIONAL_SEMANTIC__DESCRIPTION);
		createEReference(operationalSemanticEClass, OPERATIONAL_SEMANTIC__CONTAINING_CLASS);

		graphicalRepresentationEClass = createEClass(GRAPHICAL_REPRESENTATION);
		createEReference(graphicalRepresentationEClass, GRAPHICAL_REPRESENTATION__CONTAINING_CLASS);

		inferenceSemanticEClass = createEClass(INFERENCE_SEMANTIC);
		createEAttribute(inferenceSemanticEClass, INFERENCE_SEMANTIC__CATEGORY);
		createEReference(inferenceSemanticEClass, INFERENCE_SEMANTIC__DESCRIPTION);
		createEReference(inferenceSemanticEClass, INFERENCE_SEMANTIC__CONTAINING_CLASS);

		implementationNoteEClass = createEClass(IMPLEMENTATION_NOTE);
		createEAttribute(implementationNoteEClass, IMPLEMENTATION_NOTE__CATEGORY);
		createEReference(implementationNoteEClass, IMPLEMENTATION_NOTE__DESCRIPTION);
		createEReference(implementationNoteEClass, IMPLEMENTATION_NOTE__CONTAINING_CLASS);

		constraintEClass = createEClass(CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__TYPE);
		createEAttribute(constraintEClass, CONSTRAINT__MESSAGE);
		createEReference(constraintEClass, CONSTRAINT__CONTAINING_CLASS);

		modelExtensionEClass = createEClass(MODEL_EXTENSION);
		createEAttribute(modelExtensionEClass, MODEL_EXTENSION__NAME);
		createEAttribute(modelExtensionEClass, MODEL_EXTENSION__VALUE);
		createEReference(modelExtensionEClass, MODEL_EXTENSION__CONTAINING_CLASS);

		referenceEClass = createEClass(REFERENCE);
		createEReference(referenceEClass, REFERENCE__PARENT);

		javaElementEClass = createEClass(JAVA_ELEMENT);
		createEReference(javaElementEClass, JAVA_ELEMENT__JAVADOCS);

		javaClassEClass = createEClass(JAVA_CLASS);
		createEAttribute(javaClassEClass, JAVA_CLASS__PLUGIN);
		createEAttribute(javaClassEClass, JAVA_CLASS__PACKAGE);
		createEAttribute(javaClassEClass, JAVA_CLASS__NAME);
		createEReference(javaClassEClass, JAVA_CLASS__METHODS);

		javaMethodEClass = createEClass(JAVA_METHOD);
		createEAttribute(javaMethodEClass, JAVA_METHOD__NAME);
		createEAttribute(javaMethodEClass, JAVA_METHOD__LINE);
		createEReference(javaMethodEClass, JAVA_METHOD__JAVA_CLASS);

		modelReferenceEClass = createEClass(MODEL_REFERENCE);
		createEAttribute(modelReferenceEClass, MODEL_REFERENCE__PLUGIN);
		createEAttribute(modelReferenceEClass, MODEL_REFERENCE__PACKAGE);
		createEAttribute(modelReferenceEClass, MODEL_REFERENCE__NAME);

		droolsPackageEClass = createEClass(DROOLS_PACKAGE);
		createEAttribute(droolsPackageEClass, DROOLS_PACKAGE__PLUGIN);
		createEAttribute(droolsPackageEClass, DROOLS_PACKAGE__PACKAGE);
		createEAttribute(droolsPackageEClass, DROOLS_PACKAGE__NAME);
		createEReference(droolsPackageEClass, DROOLS_PACKAGE__RULES);

		droolsRuleEClass = createEClass(DROOLS_RULE);
		createEAttribute(droolsRuleEClass, DROOLS_RULE__NAME);
		createEAttribute(droolsRuleEClass, DROOLS_RULE__LINE);
		createEReference(droolsRuleEClass, DROOLS_RULE__PACKAGE);

		fileReferenceEClass = createEClass(FILE_REFERENCE);
		createEAttribute(fileReferenceEClass, FILE_REFERENCE__PLUGIN);
		createEAttribute(fileReferenceEClass, FILE_REFERENCE__PACKAGE);
		createEAttribute(fileReferenceEClass, FILE_REFERENCE__NAME);
		createEReference(fileReferenceEClass, FILE_REFERENCE__LINES);

		fileLineReferenceEClass = createEClass(FILE_LINE_REFERENCE);
		createEReference(fileLineReferenceEClass, FILE_LINE_REFERENCE__FILE);
		createEAttribute(fileLineReferenceEClass, FILE_LINE_REFERENCE__LINE);

		javadocFragmentEClass = createEClass(JAVADOC_FRAGMENT);

		javadocTagElementEClass = createEClass(JAVADOC_TAG_ELEMENT);
		createEAttribute(javadocTagElementEClass, JAVADOC_TAG_ELEMENT__NAME);
		createEReference(javadocTagElementEClass, JAVADOC_TAG_ELEMENT__FRAGMENTS);
		createEReference(javadocTagElementEClass, JAVADOC_TAG_ELEMENT__JAVA_PARENT);

		javadocTextElementEClass = createEClass(JAVADOC_TEXT_ELEMENT);
		createEAttribute(javadocTextElementEClass, JAVADOC_TEXT_ELEMENT__VALUE);

		javadocMethodReferenceEClass = createEClass(JAVADOC_METHOD_REFERENCE);
		createEReference(javadocMethodReferenceEClass, JAVADOC_METHOD_REFERENCE__REFERENCE);

		javadocClassReferenceEClass = createEClass(JAVADOC_CLASS_REFERENCE);
		createEReference(javadocClassReferenceEClass, JAVADOC_CLASS_REFERENCE__REFERENCE);

		emfAttributeEClass = createEClass(EMF_ATTRIBUTE);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__NAME);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__ID);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__LOWER_BOUND);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__UPPER_BOUND);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__TYPE);
		createEReference(emfAttributeEClass, EMF_ATTRIBUTE__CONTAINING_TYPE);
		createEAttribute(emfAttributeEClass, EMF_ATTRIBUTE__DEFAULT_LITERAL);

		emfReferenceEClass = createEClass(EMF_REFERENCE);
		createEAttribute(emfReferenceEClass, EMF_REFERENCE__NAME);
		createEAttribute(emfReferenceEClass, EMF_REFERENCE__LOWER_BOUND);
		createEAttribute(emfReferenceEClass, EMF_REFERENCE__UPPER_BOUND);
		createEAttribute(emfReferenceEClass, EMF_REFERENCE__CONTAINMENT);
		createEReference(emfReferenceEClass, EMF_REFERENCE__TYPE);
		createEReference(emfReferenceEClass, EMF_REFERENCE__CONTAINING_TYPE);
		createEAttribute(emfReferenceEClass, EMF_REFERENCE__TYPE_NAME);
		createEReference(emfReferenceEClass, EMF_REFERENCE__OPPOSITE);

		templateFileEClass = createEClass(TEMPLATE_FILE);
		createEAttribute(templateFileEClass, TEMPLATE_FILE__PLUGIN);
		createEAttribute(templateFileEClass, TEMPLATE_FILE__PACKAGE);
		createEAttribute(templateFileEClass, TEMPLATE_FILE__NAME);
		createEReference(templateFileEClass, TEMPLATE_FILE__TEMPLATES);

		templateEClass = createEClass(TEMPLATE);
		createEAttribute(templateEClass, TEMPLATE__NAME);
		createEAttribute(templateEClass, TEMPLATE__TYPE);
		createEAttribute(templateEClass, TEMPLATE__LINE);
		createEReference(templateEClass, TEMPLATE__TEMPLATE_FILE);

		// Create enums
		constraintTypeEEnum = createEEnum(CONSTRAINT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		exampleEClass.getESuperTypes().add(this.getSemantic());
		operationalSemanticEClass.getESuperTypes().add(this.getSemantic());
		graphicalRepresentationEClass.getESuperTypes().add(this.getSemantic());
		inferenceSemanticEClass.getESuperTypes().add(this.getSemantic());
		implementationNoteEClass.getESuperTypes().add(this.getSemantic());
		constraintEClass.getESuperTypes().add(this.getSemantic());
		modelExtensionEClass.getESuperTypes().add(this.getSemantic());
		javaElementEClass.getESuperTypes().add(this.getReference());
		javaClassEClass.getESuperTypes().add(this.getReference());
		javaClassEClass.getESuperTypes().add(this.getJavaElement());
		javaMethodEClass.getESuperTypes().add(this.getReference());
		javaMethodEClass.getESuperTypes().add(this.getJavaElement());
		modelReferenceEClass.getESuperTypes().add(this.getReference());
		droolsPackageEClass.getESuperTypes().add(this.getReference());
		droolsRuleEClass.getESuperTypes().add(this.getReference());
		droolsRuleEClass.getESuperTypes().add(this.getJavaElement());
		fileReferenceEClass.getESuperTypes().add(this.getReference());
		fileLineReferenceEClass.getESuperTypes().add(this.getReference());
		javadocFragmentEClass.getESuperTypes().add(this.getReference());
		javadocTagElementEClass.getESuperTypes().add(this.getJavadocFragment());
		javadocTextElementEClass.getESuperTypes().add(this.getJavadocFragment());
		javadocMethodReferenceEClass.getESuperTypes().add(this.getJavadocFragment());
		javadocClassReferenceEClass.getESuperTypes().add(this.getJavadocFragment());
		templateFileEClass.getESuperTypes().add(this.getReference());
		templateEClass.getESuperTypes().add(this.getReference());
		templateEClass.getESuperTypes().add(this.getJavaElement());

		// Initialize classes and features; add operations and parameters
		initEClass(modelDocumentationEClass, ModelDocumentation.class, "ModelDocumentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelDocumentation_Classes(), this.getEMFClass(), this.getEMFClass_Parent(), "classes", null, 0, -1, ModelDocumentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelDocumentation_References(), this.getReference(), this.getReference_Parent(), "references", null, 0, -1, ModelDocumentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emfClassEClass, EMFClass.class, "EMFClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEMFClass_TargetClass(), ecorePackage.getEClass(), null, "targetClass", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFClass_Name(), ecorePackage.getEString(), "name", null, 1, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFClass_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 1, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFClass_Interface(), ecorePackage.getEBoolean(), "interface", null, 1, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Supertypes(), this.getEMFClass(), this.getEMFClass_Subtypes(), "supertypes", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Subtypes(), this.getEMFClass(), this.getEMFClass_Supertypes(), "subtypes", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_RuntimeClass(), this.getJavaClass(), null, "runtimeClass", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Tagline(), this.getJavadocTagElement(), null, "tagline", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_OperationalSemantics(), this.getOperationalSemantic(), this.getOperationalSemantic_ContainingClass(), "operationalSemantics", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_InferenceSemantics(), this.getInferenceSemantic(), this.getInferenceSemantic_ContainingClass(), "inferenceSemantics", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_ImplementationNotes(), this.getImplementationNote(), this.getImplementationNote_ContainingClass(), "implementationNotes", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Constraints(), this.getConstraint(), this.getConstraint_ContainingClass(), "constraints", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Extensions(), this.getModelExtension(), this.getModelExtension_ContainingClass(), "extensions", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Icon(), this.getGraphicalRepresentation(), this.getGraphicalRepresentation_ContainingClass(), "icon", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_GmfEditor(), this.getGraphicalRepresentation(), null, "gmfEditor", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Examples(), this.getExample(), this.getExample_ContainingClass(), "examples", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Parent(), this.getModelDocumentation(), this.getModelDocumentation_Classes(), "parent", null, 0, 1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_Attributes(), this.getEMFAttribute(), this.getEMFAttribute_ContainingType(), "attributes", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_References(), this.getEMFReference(), this.getEMFReference_ContainingType(), "references", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFClass_AdditionalDocumentation(), this.getAdditionalDocumentation(), null, "additionalDocumentation", null, 0, -1, EMFClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(additionalDocumentationEClass, AdditionalDocumentation.class, "AdditionalDocumentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdditionalDocumentation_Reference(), this.getReference(), null, "reference", null, 0, 1, AdditionalDocumentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdditionalDocumentation_Description(), this.getJavadocTagElement(), null, "description", null, 0, 1, AdditionalDocumentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(semanticEClass, Semantic.class, "Semantic", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSemantic_Reference(), this.getReference(), null, "reference", null, 0, 1, Semantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exampleEClass, Example.class, "Example", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExample_ExampleModel(), this.getModelReference(), null, "exampleModel", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExample_ContainingClass(), this.getEMFClass(), this.getEMFClass_Examples(), "containingClass", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExample_Description(), this.getJavadocTagElement(), null, "description", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExample_ExampleLocation(), this.getFileReference(), null, "exampleLocation", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationalSemanticEClass, OperationalSemantic.class, "OperationalSemantic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperationalSemantic_Category(), ecorePackage.getEString(), "category", null, 0, 1, OperationalSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemantic_Description(), this.getJavadocTagElement(), null, "description", null, 0, 1, OperationalSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemantic_ContainingClass(), this.getEMFClass(), this.getEMFClass_OperationalSemantics(), "containingClass", null, 0, 1, OperationalSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphicalRepresentationEClass, GraphicalRepresentation.class, "GraphicalRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGraphicalRepresentation_ContainingClass(), this.getEMFClass(), this.getEMFClass_Icon(), "containingClass", null, 0, 1, GraphicalRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inferenceSemanticEClass, InferenceSemantic.class, "InferenceSemantic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInferenceSemantic_Category(), ecorePackage.getEString(), "category", null, 0, 1, InferenceSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInferenceSemantic_Description(), this.getJavadocTagElement(), null, "description", null, 0, 1, InferenceSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInferenceSemantic_ContainingClass(), this.getEMFClass(), this.getEMFClass_InferenceSemantics(), "containingClass", null, 0, 1, InferenceSemantic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(implementationNoteEClass, ImplementationNote.class, "ImplementationNote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImplementationNote_Category(), ecorePackage.getEString(), "category", null, 0, 1, ImplementationNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplementationNote_Description(), this.getJavadocTagElement(), null, "description", null, 0, 1, ImplementationNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplementationNote_ContainingClass(), this.getEMFClass(), this.getEMFClass_ImplementationNotes(), "containingClass", null, 0, 1, ImplementationNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraint_Constraint(), ecorePackage.getEString(), "constraint", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraint_Type(), this.getConstraintType(), "type", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraint_Message(), ecorePackage.getEString(), "message", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraint_ContainingClass(), this.getEMFClass(), this.getEMFClass_Constraints(), "containingClass", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelExtensionEClass, ModelExtension.class, "ModelExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelExtension_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModelExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelExtension_Value(), ecorePackage.getEString(), "value", null, 0, 1, ModelExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelExtension_ContainingClass(), this.getEMFClass(), this.getEMFClass_Extensions(), "containingClass", null, 0, 1, ModelExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReference_Parent(), this.getModelDocumentation(), this.getModelDocumentation_References(), "parent", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaElementEClass, JavaElement.class, "JavaElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavaElement_Javadocs(), this.getJavadocTagElement(), this.getJavadocTagElement_JavaParent(), "javadocs", null, 0, -1, JavaElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaClassEClass, JavaClass.class, "JavaClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaClass_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_Package(), ecorePackage.getEString(), "package", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_Name(), ecorePackage.getEString(), "name", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Methods(), this.getJavaMethod(), this.getJavaMethod_JavaClass(), "methods", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaMethodEClass, JavaMethod.class, "JavaMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaMethod_Name(), ecorePackage.getEString(), "name", null, 0, 1, JavaMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaMethod_Line(), ecorePackage.getEInt(), "line", null, 0, 1, JavaMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaMethod_JavaClass(), this.getJavaClass(), this.getJavaClass_Methods(), "javaClass", null, 0, 1, JavaMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelReferenceEClass, ModelReference.class, "ModelReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelReference_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, ModelReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelReference_Package(), ecorePackage.getEString(), "package", null, 0, 1, ModelReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelReference_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModelReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(droolsPackageEClass, DroolsPackage.class, "DroolsPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDroolsPackage_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, DroolsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDroolsPackage_Package(), ecorePackage.getEString(), "package", null, 0, 1, DroolsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDroolsPackage_Name(), ecorePackage.getEString(), "name", null, 0, 1, DroolsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDroolsPackage_Rules(), this.getDroolsRule(), this.getDroolsRule_Package(), "rules", null, 0, -1, DroolsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(droolsRuleEClass, DroolsRule.class, "DroolsRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDroolsRule_Name(), ecorePackage.getEString(), "name", null, 0, 1, DroolsRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDroolsRule_Line(), ecorePackage.getEInt(), "line", null, 0, 1, DroolsRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDroolsRule_Package(), this.getDroolsPackage(), this.getDroolsPackage_Rules(), "package", null, 0, 1, DroolsRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fileReferenceEClass, FileReference.class, "FileReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileReference_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, FileReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileReference_Package(), ecorePackage.getEString(), "package", null, 0, 1, FileReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileReference_Name(), ecorePackage.getEString(), "name", null, 0, 1, FileReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFileReference_Lines(), this.getFileLineReference(), this.getFileLineReference_File(), "lines", null, 0, -1, FileReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fileLineReferenceEClass, FileLineReference.class, "FileLineReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFileLineReference_File(), this.getFileReference(), this.getFileReference_Lines(), "file", null, 0, 1, FileLineReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileLineReference_Line(), ecorePackage.getEInt(), "line", null, 0, 1, FileLineReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javadocFragmentEClass, JavadocFragment.class, "JavadocFragment", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(javadocTagElementEClass, JavadocTagElement.class, "JavadocTagElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavadocTagElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, JavadocTagElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavadocTagElement_Fragments(), this.getJavadocFragment(), null, "fragments", null, 0, -1, JavadocTagElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavadocTagElement_JavaParent(), this.getJavaElement(), this.getJavaElement_Javadocs(), "javaParent", null, 0, 1, JavadocTagElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javadocTextElementEClass, JavadocTextElement.class, "JavadocTextElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavadocTextElement_Value(), ecorePackage.getEString(), "value", null, 0, 1, JavadocTextElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javadocMethodReferenceEClass, JavadocMethodReference.class, "JavadocMethodReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavadocMethodReference_Reference(), this.getJavaMethod(), null, "reference", null, 0, 1, JavadocMethodReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javadocClassReferenceEClass, JavadocClassReference.class, "JavadocClassReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavadocClassReference_Reference(), this.getJavaClass(), null, "reference", null, 0, 1, JavadocClassReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emfAttributeEClass, EMFAttribute.class, "EMFAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEMFAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFAttribute_Id(), ecorePackage.getEBoolean(), "id", null, 0, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFAttribute_LowerBound(), ecorePackage.getEInt(), "lowerBound", "0", 1, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFAttribute_UpperBound(), ecorePackage.getEInt(), "upperBound", "1", 1, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFAttribute_Type(), ecorePackage.getEString(), "type", null, 0, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFAttribute_ContainingType(), this.getEMFClass(), this.getEMFClass_Attributes(), "containingType", null, 0, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFAttribute_DefaultLiteral(), ecorePackage.getEString(), "defaultLiteral", null, 0, 1, EMFAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emfReferenceEClass, EMFReference.class, "EMFReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEMFReference_Name(), ecorePackage.getEString(), "name", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFReference_LowerBound(), ecorePackage.getEInt(), "lowerBound", "0", 1, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFReference_UpperBound(), ecorePackage.getEInt(), "upperBound", "1", 1, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFReference_Containment(), ecorePackage.getEBoolean(), "containment", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFReference_Type(), this.getEMFClass(), null, "type", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFReference_ContainingType(), this.getEMFClass(), this.getEMFClass_References(), "containingType", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEMFReference_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMFReference_Opposite(), this.getEMFReference(), null, "opposite", null, 0, 1, EMFReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(templateFileEClass, TemplateFile.class, "TemplateFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTemplateFile_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, TemplateFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplateFile_Package(), ecorePackage.getEString(), "package", null, 0, 1, TemplateFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplateFile_Name(), ecorePackage.getEString(), "name", null, 0, 1, TemplateFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTemplateFile_Templates(), this.getTemplate(), this.getTemplate_TemplateFile(), "templates", null, 0, -1, TemplateFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(templateEClass, Template.class, "Template", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTemplate_Name(), ecorePackage.getEString(), "name", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplate_Type(), ecorePackage.getEString(), "type", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplate_Line(), ecorePackage.getEInt(), "line", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTemplate_TemplateFile(), this.getTemplateFile(), this.getTemplateFile_Templates(), "templateFile", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(constraintTypeEEnum, ConstraintType.class, "ConstraintType");
		addEEnumLiteral(constraintTypeEEnum, ConstraintType.ERROR);
		addEEnumLiteral(constraintTypeEEnum, ConstraintType.WARNING);

		// Create resource
		createResource(eNS_URI);
	}

} //ModeldocPackageImpl
