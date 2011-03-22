/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openiaml.docs.modeldoc.ModeldocFactory
 * @model kind="package"
 * @generated
 */
public interface ModeldocPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "modeldoc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/modeldoc/2009/09";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "md";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModeldocPackage eINSTANCE = org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl <em>Model Documentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelDocumentation()
	 * @generated
	 */
	int MODEL_DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_DOCUMENTATION__CLASSES = 0;

	/**
	 * The feature id for the '<em><b>References</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_DOCUMENTATION__REFERENCES = 1;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_DOCUMENTATION__METRICS = 2;

	/**
	 * The number of structural features of the '<em>Model Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_DOCUMENTATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl <em>EMF Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.EMFClassImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFClass()
	 * @generated
	 */
	int EMF_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Target Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__TARGET_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__NAME = 1;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__ABSTRACT = 2;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__INTERFACE = 3;

	/**
	 * The feature id for the '<em><b>Supertypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__SUPERTYPES = 4;

	/**
	 * The feature id for the '<em><b>Subtypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__SUBTYPES = 5;

	/**
	 * The feature id for the '<em><b>Runtime Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__RUNTIME_CLASS = 6;

	/**
	 * The feature id for the '<em><b>Tagline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__TAGLINE = 7;

	/**
	 * The feature id for the '<em><b>Operational Semantics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__OPERATIONAL_SEMANTICS = 8;

	/**
	 * The feature id for the '<em><b>Inference Semantics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__INFERENCE_SEMANTICS = 9;

	/**
	 * The feature id for the '<em><b>Implementation Notes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__IMPLEMENTATION_NOTES = 10;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__CONSTRAINTS = 11;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__EXTENSIONS = 12;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__ICON = 13;

	/**
	 * The feature id for the '<em><b>Gmf Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__GMF_EDITOR = 14;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__EXAMPLES = 15;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__PARENT = 16;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__ATTRIBUTES = 17;

	/**
	 * The feature id for the '<em><b>References</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__REFERENCES = 18;

	/**
	 * The feature id for the '<em><b>Additional Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__ADDITIONAL_DOCUMENTATION = 19;

	/**
	 * The feature id for the '<em><b>Additional Latex</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__ADDITIONAL_LATEX = 20;

	/**
	 * The feature id for the '<em><b>Rationale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__RATIONALE = 21;

	/**
	 * The number of structural features of the '<em>EMF Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS_FEATURE_COUNT = 22;


	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl <em>Additional Documentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getAdditionalDocumentation()
	 * @generated
	 */
	int ADDITIONAL_DOCUMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_DOCUMENTATION__REFERENCE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_DOCUMENTATION__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Additional Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_DOCUMENTATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.AdditionalLatexImpl <em>Additional Latex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.AdditionalLatexImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getAdditionalLatex()
	 * @generated
	 */
	int ADDITIONAL_LATEX = 3;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_LATEX__REFERENCE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_LATEX__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Additional Latex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_LATEX_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.SemanticImpl <em>Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.SemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getSemantic()
	 * @generated
	 */
	int SEMANTIC = 4;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC__REFERENCE = 0;

	/**
	 * The number of structural features of the '<em>Semantic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ExampleImpl <em>Example</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ExampleImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getExample()
	 * @generated
	 */
	int EXAMPLE = 5;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Example Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLE_MODEL = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Example Location</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLE_LOCATION = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Example</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl <em>Operational Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getOperationalSemantic()
	 * @generated
	 */
	int OPERATIONAL_SEMANTIC = 6;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC__CATEGORY = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operational Semantic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl <em>Graphical Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getGraphicalRepresentation()
	 * @generated
	 */
	int GRAPHICAL_REPRESENTATION = 7;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_REPRESENTATION__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_REPRESENTATION__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Graphical Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_REPRESENTATION_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl <em>Inference Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getInferenceSemantic()
	 * @generated
	 */
	int INFERENCE_SEMANTIC = 8;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC__CATEGORY = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Inference Semantic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ImplementationNoteImpl <em>Implementation Note</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ImplementationNoteImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getImplementationNote()
	 * @generated
	 */
	int IMPLEMENTATION_NOTE = 9;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_NOTE__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_NOTE__CATEGORY = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_NOTE__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_NOTE__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Implementation Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_NOTE_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ConstraintImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 10;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__CONSTRAINT = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__TYPE = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__MESSAGE = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ModelExtensionImpl <em>Model Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ModelExtensionImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelExtension()
	 * @generated
	 */
	int MODEL_EXTENSION = 11;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION__NAME = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION__VALUE = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION__CONTAINING_CLASS = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 12;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__PARENT = 0;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavaClassImpl <em>Java Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavaClassImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaClass()
	 * @generated
	 */
	int JAVA_CLASS = 14;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavaMethodImpl <em>Java Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavaMethodImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaMethod()
	 * @generated
	 */
	int JAVA_METHOD = 15;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ModelReferenceImpl <em>Model Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ModelReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelReference()
	 * @generated
	 */
	int MODEL_REFERENCE = 16;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl <em>Drools Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.DroolsRuleImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsRule()
	 * @generated
	 */
	int DROOLS_RULE = 18;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.DroolsPackageImpl <em>Drools Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.DroolsPackageImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsPackage()
	 * @generated
	 */
	int DROOLS_PACKAGE = 17;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.FileReferenceImpl <em>File Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.FileReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileReference()
	 * @generated
	 */
	int FILE_REFERENCE = 19;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl <em>File Line Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileLineReference()
	 * @generated
	 */
	int FILE_LINE_REFERENCE = 20;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavadocFragmentImpl <em>Javadoc Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavadocFragmentImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocFragment()
	 * @generated
	 */
	int JAVADOC_FRAGMENT = 21;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl <em>Javadoc Tag Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocTagElement()
	 * @generated
	 */
	int JAVADOC_TAG_ELEMENT = 22;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavadocTextElementImpl <em>Javadoc Text Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavadocTextElementImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocTextElement()
	 * @generated
	 */
	int JAVADOC_TEXT_ELEMENT = 23;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavaElementImpl <em>Java Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavaElementImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaElement()
	 * @generated
	 */
	int JAVA_ELEMENT = 13;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ELEMENT__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ELEMENT__JAVADOCS = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Java Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ELEMENT_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__JAVADOCS = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PLUGIN = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PACKAGE = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__NAME = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__METHODS = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Java Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__JAVADOCS = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__NAME = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__LINE = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Java Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__JAVA_CLASS = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Java Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_REFERENCE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_REFERENCE__PLUGIN = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_REFERENCE__PACKAGE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_REFERENCE__NAME = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_REFERENCE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__PLUGIN = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__PACKAGE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__NAME = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__RULES = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__JAVADOCS = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unique Rules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE__UNIQUE_RULES = REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Drools Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__JAVADOCS = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__NAME = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__LINE = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__PACKAGE = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Drools Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE__PLUGIN = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE__PACKAGE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE__NAME = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Lines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE__LINES = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>File Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REFERENCE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LINE_REFERENCE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>File</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LINE_REFERENCE__FILE = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LINE_REFERENCE__LINE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>File Line Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LINE_REFERENCE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_FRAGMENT__PARENT = REFERENCE__PARENT;

	/**
	 * The number of structural features of the '<em>Javadoc Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_FRAGMENT_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TAG_ELEMENT__PARENT = JAVADOC_FRAGMENT__PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TAG_ELEMENT__NAME = JAVADOC_FRAGMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TAG_ELEMENT__FRAGMENTS = JAVADOC_FRAGMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Java Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TAG_ELEMENT__JAVA_PARENT = JAVADOC_FRAGMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Javadoc Tag Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TAG_ELEMENT_FEATURE_COUNT = JAVADOC_FRAGMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TEXT_ELEMENT__PARENT = JAVADOC_FRAGMENT__PARENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TEXT_ELEMENT__VALUE = JAVADOC_FRAGMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Javadoc Text Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_TEXT_ELEMENT_FEATURE_COUNT = JAVADOC_FRAGMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavadocMethodReferenceImpl <em>Javadoc Method Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavadocMethodReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocMethodReference()
	 * @generated
	 */
	int JAVADOC_METHOD_REFERENCE = 24;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_METHOD_REFERENCE__PARENT = JAVADOC_FRAGMENT__PARENT;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_METHOD_REFERENCE__REFERENCE = JAVADOC_FRAGMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Javadoc Method Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_METHOD_REFERENCE_FEATURE_COUNT = JAVADOC_FRAGMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavadocClassReferenceImpl <em>Javadoc Class Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavadocClassReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocClassReference()
	 * @generated
	 */
	int JAVADOC_CLASS_REFERENCE = 25;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_CLASS_REFERENCE__PARENT = JAVADOC_FRAGMENT__PARENT;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_CLASS_REFERENCE__REFERENCE = JAVADOC_FRAGMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Javadoc Class Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_CLASS_REFERENCE_FEATURE_COUNT = JAVADOC_FRAGMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl <em>EMF Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.EMFAttributeImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFAttribute()
	 * @generated
	 */
	int EMF_ATTRIBUTE = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__ID = 1;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__LOWER_BOUND = 2;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__UPPER_BOUND = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Containing Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__CONTAINING_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Default Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__DEFAULT_LITERAL = 6;

	/**
	 * The feature id for the '<em><b>Tagline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__TAGLINE = 7;

	/**
	 * The feature id for the '<em><b>Accepted Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE__ACCEPTED_VALUES = 8;

	/**
	 * The number of structural features of the '<em>EMF Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_ATTRIBUTE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.EMFReferenceImpl <em>EMF Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.EMFReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFReference()
	 * @generated
	 */
	int EMF_REFERENCE = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__LOWER_BOUND = 1;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__UPPER_BOUND = 2;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__CONTAINMENT = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Containing Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__CONTAINING_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__TYPE_NAME = 6;

	/**
	 * The feature id for the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__OPPOSITE = 7;

	/**
	 * The feature id for the '<em><b>Tagline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE__TAGLINE = 8;

	/**
	 * The number of structural features of the '<em>EMF Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_REFERENCE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.TemplateFileImpl <em>Template File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.TemplateFileImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getTemplateFile()
	 * @generated
	 */
	int TEMPLATE_FILE = 28;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE__PLUGIN = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE__PACKAGE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE__NAME = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE__TEMPLATES = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Template File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FILE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.TemplateImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 29;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Javadocs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__JAVADOCS = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NAME = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__TYPE = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__LINE = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Template File</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__TEMPLATE_FILE = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.MetricImpl <em>Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.MetricImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getMetric()
	 * @generated
	 */
	int METRIC = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.ConstraintType <em>Constraint Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.ConstraintType
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraintType()
	 * @generated
	 */
	int CONSTRAINT_TYPE = 31;


	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.ModelDocumentation <em>Model Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Documentation</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation
	 * @generated
	 */
	EClass getModelDocumentation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.ModelDocumentation#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation#getClasses()
	 * @see #getModelDocumentation()
	 * @generated
	 */
	EReference getModelDocumentation_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.ModelDocumentation#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>References</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation#getReferences()
	 * @see #getModelDocumentation()
	 * @generated
	 */
	EReference getModelDocumentation_References();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.ModelDocumentation#getMetrics <em>Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Metrics</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelDocumentation#getMetrics()
	 * @see #getModelDocumentation()
	 * @generated
	 */
	EReference getModelDocumentation_Metrics();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.EMFClass <em>EMF Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMF Class</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass
	 * @generated
	 */
	EClass getEMFClass();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.EMFClass#getTargetClass <em>Target Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Class</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getTargetClass()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_TargetClass();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getName()
	 * @see #getEMFClass()
	 * @generated
	 */
	EAttribute getEMFClass_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFClass#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#isAbstract()
	 * @see #getEMFClass()
	 * @generated
	 */
	EAttribute getEMFClass_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFClass#isInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#isInterface()
	 * @see #getEMFClass()
	 * @generated
	 */
	EAttribute getEMFClass_Interface();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getSupertypes <em>Supertypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supertypes</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getSupertypes()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Supertypes();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getSubtypes <em>Subtypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subtypes</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getSubtypes()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Subtypes();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.EMFClass#getRuntimeClass <em>Runtime Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Runtime Class</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getRuntimeClass()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_RuntimeClass();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFClass#getTagline <em>Tagline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tagline</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getTagline()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Tagline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getOperationalSemantics <em>Operational Semantics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operational Semantics</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getOperationalSemantics()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_OperationalSemantics();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getInferenceSemantics <em>Inference Semantics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inference Semantics</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getInferenceSemantics()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_InferenceSemantics();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getConstraints()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Constraints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extensions</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getExtensions()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Extensions();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFClass#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Icon</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getIcon()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Icon();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFClass#getGmfEditor <em>Gmf Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Gmf Editor</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getGmfEditor()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_GmfEditor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getExamples <em>Examples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Examples</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getExamples()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Examples();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.EMFClass#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getParent()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getAttributes()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>References</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getReferences()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_References();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getAdditionalDocumentation <em>Additional Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Additional Documentation</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getAdditionalDocumentation()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_AdditionalDocumentation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getAdditionalLatex <em>Additional Latex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Additional Latex</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getAdditionalLatex()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_AdditionalLatex();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFClass#getRationale <em>Rationale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rationale</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getRationale()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_Rationale();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getImplementationNotes <em>Implementation Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Implementation Notes</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getImplementationNotes()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_ImplementationNotes();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.AdditionalDocumentation <em>Additional Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Additional Documentation</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalDocumentation
	 * @generated
	 */
	EClass getAdditionalDocumentation();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalDocumentation#getReference()
	 * @see #getAdditionalDocumentation()
	 * @generated
	 */
	EReference getAdditionalDocumentation_Reference();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.AdditionalDocumentation#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalDocumentation#getDescription()
	 * @see #getAdditionalDocumentation()
	 * @generated
	 */
	EReference getAdditionalDocumentation_Description();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.AdditionalLatex <em>Additional Latex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Additional Latex</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalLatex
	 * @generated
	 */
	EClass getAdditionalLatex();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.AdditionalLatex#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalLatex#getReference()
	 * @see #getAdditionalLatex()
	 * @generated
	 */
	EReference getAdditionalLatex_Reference();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.AdditionalLatex#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.AdditionalLatex#getDescription()
	 * @see #getAdditionalLatex()
	 * @generated
	 */
	EReference getAdditionalLatex_Description();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Semantic <em>Semantic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic</em>'.
	 * @see org.openiaml.docs.modeldoc.Semantic
	 * @generated
	 */
	EClass getSemantic();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.Semantic#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.Semantic#getReference()
	 * @see #getSemantic()
	 * @generated
	 */
	EReference getSemantic_Reference();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Example <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Example</em>'.
	 * @see org.openiaml.docs.modeldoc.Example
	 * @generated
	 */
	EClass getExample();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.Example#getExampleModel <em>Example Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Example Model</em>'.
	 * @see org.openiaml.docs.modeldoc.Example#getExampleModel()
	 * @see #getExample()
	 * @generated
	 */
	EReference getExample_ExampleModel();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.Example#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.Example#getContainingClass()
	 * @see #getExample()
	 * @generated
	 */
	EReference getExample_ContainingClass();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.Example#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.Example#getDescription()
	 * @see #getExample()
	 * @generated
	 */
	EReference getExample_Description();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.Example#getExampleLocation <em>Example Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Example Location</em>'.
	 * @see org.openiaml.docs.modeldoc.Example#getExampleLocation()
	 * @see #getExample()
	 * @generated
	 */
	EReference getExample_ExampleLocation();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.OperationalSemantic <em>Operational Semantic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operational Semantic</em>'.
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic
	 * @generated
	 */
	EClass getOperationalSemantic();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.OperationalSemantic#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic#getCategory()
	 * @see #getOperationalSemantic()
	 * @generated
	 */
	EAttribute getOperationalSemantic_Category();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.OperationalSemantic#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic#getDescription()
	 * @see #getOperationalSemantic()
	 * @generated
	 */
	EReference getOperationalSemantic_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.OperationalSemantic#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic#getContainingClass()
	 * @see #getOperationalSemantic()
	 * @generated
	 */
	EReference getOperationalSemantic_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.GraphicalRepresentation <em>Graphical Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Representation</em>'.
	 * @see org.openiaml.docs.modeldoc.GraphicalRepresentation
	 * @generated
	 */
	EClass getGraphicalRepresentation();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.GraphicalRepresentation#getContainingClass()
	 * @see #getGraphicalRepresentation()
	 * @generated
	 */
	EReference getGraphicalRepresentation_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.InferenceSemantic <em>Inference Semantic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inference Semantic</em>'.
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic
	 * @generated
	 */
	EClass getInferenceSemantic();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic#getCategory()
	 * @see #getInferenceSemantic()
	 * @generated
	 */
	EAttribute getInferenceSemantic_Category();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic#getDescription()
	 * @see #getInferenceSemantic()
	 * @generated
	 */
	EReference getInferenceSemantic_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic#getContainingClass()
	 * @see #getInferenceSemantic()
	 * @generated
	 */
	EReference getInferenceSemantic_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.ImplementationNote <em>Implementation Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation Note</em>'.
	 * @see org.openiaml.docs.modeldoc.ImplementationNote
	 * @generated
	 */
	EClass getImplementationNote();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ImplementationNote#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.openiaml.docs.modeldoc.ImplementationNote#getCategory()
	 * @see #getImplementationNote()
	 * @generated
	 */
	EAttribute getImplementationNote_Category();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.ImplementationNote#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.ImplementationNote#getDescription()
	 * @see #getImplementationNote()
	 * @generated
	 */
	EReference getImplementationNote_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.ImplementationNote#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.ImplementationNote#getContainingClass()
	 * @see #getImplementationNote()
	 * @generated
	 */
	EReference getImplementationNote_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.openiaml.docs.modeldoc.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Constraint#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraint</em>'.
	 * @see org.openiaml.docs.modeldoc.Constraint#getConstraint()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Constraint();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Constraint#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.openiaml.docs.modeldoc.Constraint#getType()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Constraint#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.openiaml.docs.modeldoc.Constraint#getMessage()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Message();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.Constraint#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.Constraint#getContainingClass()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.ModelExtension <em>Model Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Extension</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelExtension
	 * @generated
	 */
	EClass getModelExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ModelExtension#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelExtension#getName()
	 * @see #getModelExtension()
	 * @generated
	 */
	EAttribute getModelExtension_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ModelExtension#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelExtension#getValue()
	 * @see #getModelExtension()
	 * @generated
	 */
	EAttribute getModelExtension_Value();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.ModelExtension#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelExtension#getContainingClass()
	 * @see #getModelExtension()
	 * @generated
	 */
	EReference getModelExtension_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.Reference#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.openiaml.docs.modeldoc.Reference#getParent()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Parent();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavaClass <em>Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Class</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaClass
	 * @generated
	 */
	EClass getJavaClass();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavaClass#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaClass#getPlugin()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Plugin();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavaClass#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaClass#getPackage()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Package();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavaClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaClass#getName()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.JavaClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaClass#getMethods()
	 * @see #getJavaClass()
	 * @generated
	 */
	EReference getJavaClass_Methods();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavaMethod <em>Java Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Method</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaMethod
	 * @generated
	 */
	EClass getJavaMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavaMethod#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaMethod#getName()
	 * @see #getJavaMethod()
	 * @generated
	 */
	EAttribute getJavaMethod_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavaMethod#getLine <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaMethod#getLine()
	 * @see #getJavaMethod()
	 * @generated
	 */
	EAttribute getJavaMethod_Line();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.JavaMethod#getJavaClass <em>Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Java Class</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaMethod#getJavaClass()
	 * @see #getJavaMethod()
	 * @generated
	 */
	EReference getJavaMethod_JavaClass();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.ModelReference <em>Model Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelReference
	 * @generated
	 */
	EClass getModelReference();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ModelReference#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelReference#getPlugin()
	 * @see #getModelReference()
	 * @generated
	 */
	EAttribute getModelReference_Plugin();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ModelReference#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelReference#getPackage()
	 * @see #getModelReference()
	 * @generated
	 */
	EAttribute getModelReference_Package();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.ModelReference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.ModelReference#getName()
	 * @see #getModelReference()
	 * @generated
	 */
	EAttribute getModelReference_Name();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.DroolsRule <em>Drools Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drools Rule</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsRule
	 * @generated
	 */
	EClass getDroolsRule();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsRule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsRule#getName()
	 * @see #getDroolsRule()
	 * @generated
	 */
	EAttribute getDroolsRule_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsRule#getLine <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsRule#getLine()
	 * @see #getDroolsRule()
	 * @generated
	 */
	EAttribute getDroolsRule_Line();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.DroolsRule#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsRule#getPackage()
	 * @see #getDroolsRule()
	 * @generated
	 */
	EReference getDroolsRule_Package();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.DroolsPackage <em>Drools Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drools Package</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage
	 * @generated
	 */
	EClass getDroolsPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsPackage#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getPlugin()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EAttribute getDroolsPackage_Plugin();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsPackage#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getPackage()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EAttribute getDroolsPackage_Package();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsPackage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getName()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EAttribute getDroolsPackage_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.DroolsPackage#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getRules()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EReference getDroolsPackage_Rules();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.DroolsPackage#getJavadocs <em>Javadocs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Javadocs</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getJavadocs()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EReference getDroolsPackage_Javadocs();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.DroolsPackage#getUniqueRules <em>Unique Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique Rules</em>'.
	 * @see org.openiaml.docs.modeldoc.DroolsPackage#getUniqueRules()
	 * @see #getDroolsPackage()
	 * @generated
	 */
	EAttribute getDroolsPackage_UniqueRules();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.FileReference <em>File Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.FileReference
	 * @generated
	 */
	EClass getFileReference();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.FileReference#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin</em>'.
	 * @see org.openiaml.docs.modeldoc.FileReference#getPlugin()
	 * @see #getFileReference()
	 * @generated
	 */
	EAttribute getFileReference_Plugin();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.FileReference#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.FileReference#getPackage()
	 * @see #getFileReference()
	 * @generated
	 */
	EAttribute getFileReference_Package();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.FileReference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.FileReference#getName()
	 * @see #getFileReference()
	 * @generated
	 */
	EAttribute getFileReference_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.FileReference#getLines <em>Lines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lines</em>'.
	 * @see org.openiaml.docs.modeldoc.FileReference#getLines()
	 * @see #getFileReference()
	 * @generated
	 */
	EReference getFileReference_Lines();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.FileLineReference <em>File Line Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Line Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.FileLineReference
	 * @generated
	 */
	EClass getFileLineReference();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.FileLineReference#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>File</em>'.
	 * @see org.openiaml.docs.modeldoc.FileLineReference#getFile()
	 * @see #getFileLineReference()
	 * @generated
	 */
	EReference getFileLineReference_File();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.FileLineReference#getLine <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see org.openiaml.docs.modeldoc.FileLineReference#getLine()
	 * @see #getFileLineReference()
	 * @generated
	 */
	EAttribute getFileLineReference_Line();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavadocFragment <em>Javadoc Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc Fragment</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocFragment
	 * @generated
	 */
	EClass getJavadocFragment();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavadocTagElement <em>Javadoc Tag Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc Tag Element</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTagElement
	 * @generated
	 */
	EClass getJavadocTagElement();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTagElement#getName()
	 * @see #getJavadocTagElement()
	 * @generated
	 */
	EAttribute getJavadocTagElement_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTagElement#getFragments()
	 * @see #getJavadocTagElement()
	 * @generated
	 */
	EReference getJavadocTagElement_Fragments();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent <em>Java Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Java Parent</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTagElement#getJavaParent()
	 * @see #getJavadocTagElement()
	 * @generated
	 */
	EReference getJavadocTagElement_JavaParent();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavadocTextElement <em>Javadoc Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc Text Element</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTextElement
	 * @generated
	 */
	EClass getJavadocTextElement();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.JavadocTextElement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocTextElement#getValue()
	 * @see #getJavadocTextElement()
	 * @generated
	 */
	EAttribute getJavadocTextElement_Value();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavaElement <em>Java Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Element</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaElement
	 * @generated
	 */
	EClass getJavaElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.JavaElement#getJavadocs <em>Javadocs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Javadocs</em>'.
	 * @see org.openiaml.docs.modeldoc.JavaElement#getJavadocs()
	 * @see #getJavaElement()
	 * @generated
	 */
	EReference getJavaElement_Javadocs();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavadocMethodReference <em>Javadoc Method Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc Method Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocMethodReference
	 * @generated
	 */
	EClass getJavadocMethodReference();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.JavadocMethodReference#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocMethodReference#getReference()
	 * @see #getJavadocMethodReference()
	 * @generated
	 */
	EReference getJavadocMethodReference_Reference();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.JavadocClassReference <em>Javadoc Class Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc Class Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocClassReference
	 * @generated
	 */
	EClass getJavadocClassReference();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.JavadocClassReference#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.JavadocClassReference#getReference()
	 * @see #getJavadocClassReference()
	 * @generated
	 */
	EReference getJavadocClassReference_Reference();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.EMFAttribute <em>EMF Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMF Attribute</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute
	 * @generated
	 */
	EClass getEMFAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getName()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#isId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#isId()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getLowerBound()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getUpperBound()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getType()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_Type();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.EMFAttribute#getContainingType <em>Containing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Type</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getContainingType()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EReference getEMFAttribute_ContainingType();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFAttribute#getDefaultLiteral <em>Default Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Literal</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getDefaultLiteral()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_DefaultLiteral();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFAttribute#getTagline <em>Tagline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tagline</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getTagline()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EReference getEMFAttribute_Tagline();

	/**
	 * Returns the meta object for the attribute list '{@link org.openiaml.docs.modeldoc.EMFAttribute#getAcceptedValues <em>Accepted Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Accepted Values</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFAttribute#getAcceptedValues()
	 * @see #getEMFAttribute()
	 * @generated
	 */
	EAttribute getEMFAttribute_AcceptedValues();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.EMFReference <em>EMF Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMF Reference</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference
	 * @generated
	 */
	EClass getEMFReference();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFReference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getName()
	 * @see #getEMFReference()
	 * @generated
	 */
	EAttribute getEMFReference_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFReference#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getLowerBound()
	 * @see #getEMFReference()
	 * @generated
	 */
	EAttribute getEMFReference_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFReference#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getUpperBound()
	 * @see #getEMFReference()
	 * @generated
	 */
	EAttribute getEMFReference_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFReference#isContainment <em>Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containment</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#isContainment()
	 * @see #getEMFReference()
	 * @generated
	 */
	EAttribute getEMFReference_Containment();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.EMFReference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getType()
	 * @see #getEMFReference()
	 * @generated
	 */
	EReference getEMFReference_Type();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.EMFReference#getContainingType <em>Containing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Type</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getContainingType()
	 * @see #getEMFReference()
	 * @generated
	 */
	EReference getEMFReference_ContainingType();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFReference#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getTypeName()
	 * @see #getEMFReference()
	 * @generated
	 */
	EAttribute getEMFReference_TypeName();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.EMFReference#getOpposite <em>Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opposite</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getOpposite()
	 * @see #getEMFReference()
	 * @generated
	 */
	EReference getEMFReference_Opposite();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.docs.modeldoc.EMFReference#getTagline <em>Tagline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tagline</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFReference#getTagline()
	 * @see #getEMFReference()
	 * @generated
	 */
	EReference getEMFReference_Tagline();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.TemplateFile <em>Template File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template File</em>'.
	 * @see org.openiaml.docs.modeldoc.TemplateFile
	 * @generated
	 */
	EClass getTemplateFile();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.TemplateFile#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin</em>'.
	 * @see org.openiaml.docs.modeldoc.TemplateFile#getPlugin()
	 * @see #getTemplateFile()
	 * @generated
	 */
	EAttribute getTemplateFile_Plugin();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.TemplateFile#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see org.openiaml.docs.modeldoc.TemplateFile#getPackage()
	 * @see #getTemplateFile()
	 * @generated
	 */
	EAttribute getTemplateFile_Package();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.TemplateFile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.TemplateFile#getName()
	 * @see #getTemplateFile()
	 * @generated
	 */
	EAttribute getTemplateFile_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.TemplateFile#getTemplates <em>Templates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Templates</em>'.
	 * @see org.openiaml.docs.modeldoc.TemplateFile#getTemplates()
	 * @see #getTemplateFile()
	 * @generated
	 */
	EReference getTemplateFile_Templates();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.openiaml.docs.modeldoc.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Template#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.Template#getName()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Template#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.openiaml.docs.modeldoc.Template#getType()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Template#getLine <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see org.openiaml.docs.modeldoc.Template#getLine()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Line();

	/**
	 * Returns the meta object for the container reference '{@link org.openiaml.docs.modeldoc.Template#getTemplateFile <em>Template File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Template File</em>'.
	 * @see org.openiaml.docs.modeldoc.Template#getTemplateFile()
	 * @see #getTemplate()
	 * @generated
	 */
	EReference getTemplate_TemplateFile();

	/**
	 * Returns the meta object for class '{@link org.openiaml.docs.modeldoc.Metric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metric</em>'.
	 * @see org.openiaml.docs.modeldoc.Metric
	 * @generated
	 */
	EClass getMetric();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Metric#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.docs.modeldoc.Metric#getName()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.Metric#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.openiaml.docs.modeldoc.Metric#getValue()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_Value();

	/**
	 * Returns the meta object for enum '{@link org.openiaml.docs.modeldoc.ConstraintType <em>Constraint Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Constraint Type</em>'.
	 * @see org.openiaml.docs.modeldoc.ConstraintType
	 * @generated
	 */
	EEnum getConstraintType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModeldocFactory getModeldocFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl <em>Model Documentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelDocumentation()
		 * @generated
		 */
		EClass MODEL_DOCUMENTATION = eINSTANCE.getModelDocumentation();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_DOCUMENTATION__CLASSES = eINSTANCE.getModelDocumentation_Classes();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_DOCUMENTATION__REFERENCES = eINSTANCE.getModelDocumentation_References();

		/**
		 * The meta object literal for the '<em><b>Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_DOCUMENTATION__METRICS = eINSTANCE.getModelDocumentation_Metrics();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl <em>EMF Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.EMFClassImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFClass()
		 * @generated
		 */
		EClass EMF_CLASS = eINSTANCE.getEMFClass();

		/**
		 * The meta object literal for the '<em><b>Target Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__TARGET_CLASS = eINSTANCE.getEMFClass_TargetClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_CLASS__NAME = eINSTANCE.getEMFClass_Name();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_CLASS__ABSTRACT = eINSTANCE.getEMFClass_Abstract();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_CLASS__INTERFACE = eINSTANCE.getEMFClass_Interface();

		/**
		 * The meta object literal for the '<em><b>Supertypes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__SUPERTYPES = eINSTANCE.getEMFClass_Supertypes();

		/**
		 * The meta object literal for the '<em><b>Subtypes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__SUBTYPES = eINSTANCE.getEMFClass_Subtypes();

		/**
		 * The meta object literal for the '<em><b>Runtime Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__RUNTIME_CLASS = eINSTANCE.getEMFClass_RuntimeClass();

		/**
		 * The meta object literal for the '<em><b>Tagline</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__TAGLINE = eINSTANCE.getEMFClass_Tagline();

		/**
		 * The meta object literal for the '<em><b>Operational Semantics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__OPERATIONAL_SEMANTICS = eINSTANCE.getEMFClass_OperationalSemantics();

		/**
		 * The meta object literal for the '<em><b>Inference Semantics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__INFERENCE_SEMANTICS = eINSTANCE.getEMFClass_InferenceSemantics();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__CONSTRAINTS = eINSTANCE.getEMFClass_Constraints();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__EXTENSIONS = eINSTANCE.getEMFClass_Extensions();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__ICON = eINSTANCE.getEMFClass_Icon();

		/**
		 * The meta object literal for the '<em><b>Gmf Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__GMF_EDITOR = eINSTANCE.getEMFClass_GmfEditor();

		/**
		 * The meta object literal for the '<em><b>Examples</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__EXAMPLES = eINSTANCE.getEMFClass_Examples();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__PARENT = eINSTANCE.getEMFClass_Parent();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__ATTRIBUTES = eINSTANCE.getEMFClass_Attributes();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__REFERENCES = eINSTANCE.getEMFClass_References();

		/**
		 * The meta object literal for the '<em><b>Additional Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__ADDITIONAL_DOCUMENTATION = eINSTANCE.getEMFClass_AdditionalDocumentation();

		/**
		 * The meta object literal for the '<em><b>Additional Latex</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__ADDITIONAL_LATEX = eINSTANCE.getEMFClass_AdditionalLatex();

		/**
		 * The meta object literal for the '<em><b>Rationale</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__RATIONALE = eINSTANCE.getEMFClass_Rationale();

		/**
		 * The meta object literal for the '<em><b>Implementation Notes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__IMPLEMENTATION_NOTES = eINSTANCE.getEMFClass_ImplementationNotes();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl <em>Additional Documentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.AdditionalDocumentationImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getAdditionalDocumentation()
		 * @generated
		 */
		EClass ADDITIONAL_DOCUMENTATION = eINSTANCE.getAdditionalDocumentation();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADDITIONAL_DOCUMENTATION__REFERENCE = eINSTANCE.getAdditionalDocumentation_Reference();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADDITIONAL_DOCUMENTATION__DESCRIPTION = eINSTANCE.getAdditionalDocumentation_Description();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.AdditionalLatexImpl <em>Additional Latex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.AdditionalLatexImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getAdditionalLatex()
		 * @generated
		 */
		EClass ADDITIONAL_LATEX = eINSTANCE.getAdditionalLatex();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADDITIONAL_LATEX__REFERENCE = eINSTANCE.getAdditionalLatex_Reference();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADDITIONAL_LATEX__DESCRIPTION = eINSTANCE.getAdditionalLatex_Description();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.SemanticImpl <em>Semantic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.SemanticImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getSemantic()
		 * @generated
		 */
		EClass SEMANTIC = eINSTANCE.getSemantic();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEMANTIC__REFERENCE = eINSTANCE.getSemantic_Reference();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ExampleImpl <em>Example</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ExampleImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getExample()
		 * @generated
		 */
		EClass EXAMPLE = eINSTANCE.getExample();

		/**
		 * The meta object literal for the '<em><b>Example Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAMPLE__EXAMPLE_MODEL = eINSTANCE.getExample_ExampleModel();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAMPLE__CONTAINING_CLASS = eINSTANCE.getExample_ContainingClass();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAMPLE__DESCRIPTION = eINSTANCE.getExample_Description();

		/**
		 * The meta object literal for the '<em><b>Example Location</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAMPLE__EXAMPLE_LOCATION = eINSTANCE.getExample_ExampleLocation();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl <em>Operational Semantic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getOperationalSemantic()
		 * @generated
		 */
		EClass OPERATIONAL_SEMANTIC = eINSTANCE.getOperationalSemantic();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATIONAL_SEMANTIC__CATEGORY = eINSTANCE.getOperationalSemantic_Category();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTIC__DESCRIPTION = eINSTANCE.getOperationalSemantic_Description();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTIC__CONTAINING_CLASS = eINSTANCE.getOperationalSemantic_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl <em>Graphical Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getGraphicalRepresentation()
		 * @generated
		 */
		EClass GRAPHICAL_REPRESENTATION = eINSTANCE.getGraphicalRepresentation();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICAL_REPRESENTATION__CONTAINING_CLASS = eINSTANCE.getGraphicalRepresentation_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl <em>Inference Semantic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getInferenceSemantic()
		 * @generated
		 */
		EClass INFERENCE_SEMANTIC = eINSTANCE.getInferenceSemantic();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFERENCE_SEMANTIC__CATEGORY = eINSTANCE.getInferenceSemantic_Category();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFERENCE_SEMANTIC__DESCRIPTION = eINSTANCE.getInferenceSemantic_Description();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFERENCE_SEMANTIC__CONTAINING_CLASS = eINSTANCE.getInferenceSemantic_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ImplementationNoteImpl <em>Implementation Note</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ImplementationNoteImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getImplementationNote()
		 * @generated
		 */
		EClass IMPLEMENTATION_NOTE = eINSTANCE.getImplementationNote();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION_NOTE__CATEGORY = eINSTANCE.getImplementationNote_Category();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_NOTE__DESCRIPTION = eINSTANCE.getImplementationNote_Description();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_NOTE__CONTAINING_CLASS = eINSTANCE.getImplementationNote_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ConstraintImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__CONSTRAINT = eINSTANCE.getConstraint_Constraint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__TYPE = eINSTANCE.getConstraint_Type();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__MESSAGE = eINSTANCE.getConstraint_Message();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__CONTAINING_CLASS = eINSTANCE.getConstraint_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ModelExtensionImpl <em>Model Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ModelExtensionImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelExtension()
		 * @generated
		 */
		EClass MODEL_EXTENSION = eINSTANCE.getModelExtension();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_EXTENSION__NAME = eINSTANCE.getModelExtension_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_EXTENSION__VALUE = eINSTANCE.getModelExtension_Value();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_EXTENSION__CONTAINING_CLASS = eINSTANCE.getModelExtension_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__PARENT = eINSTANCE.getReference_Parent();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavaClassImpl <em>Java Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavaClassImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaClass()
		 * @generated
		 */
		EClass JAVA_CLASS = eINSTANCE.getJavaClass();

		/**
		 * The meta object literal for the '<em><b>Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_CLASS__PLUGIN = eINSTANCE.getJavaClass_Plugin();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_CLASS__PACKAGE = eINSTANCE.getJavaClass_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_CLASS__NAME = eINSTANCE.getJavaClass_Name();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVA_CLASS__METHODS = eINSTANCE.getJavaClass_Methods();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavaMethodImpl <em>Java Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavaMethodImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaMethod()
		 * @generated
		 */
		EClass JAVA_METHOD = eINSTANCE.getJavaMethod();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_METHOD__NAME = eINSTANCE.getJavaMethod_Name();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_METHOD__LINE = eINSTANCE.getJavaMethod_Line();

		/**
		 * The meta object literal for the '<em><b>Java Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVA_METHOD__JAVA_CLASS = eINSTANCE.getJavaMethod_JavaClass();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.ModelReferenceImpl <em>Model Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.ModelReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelReference()
		 * @generated
		 */
		EClass MODEL_REFERENCE = eINSTANCE.getModelReference();

		/**
		 * The meta object literal for the '<em><b>Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_REFERENCE__PLUGIN = eINSTANCE.getModelReference_Plugin();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_REFERENCE__PACKAGE = eINSTANCE.getModelReference_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_REFERENCE__NAME = eINSTANCE.getModelReference_Name();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl <em>Drools Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.DroolsRuleImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsRule()
		 * @generated
		 */
		EClass DROOLS_RULE = eINSTANCE.getDroolsRule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_RULE__NAME = eINSTANCE.getDroolsRule_Name();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_RULE__LINE = eINSTANCE.getDroolsRule_Line();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DROOLS_RULE__PACKAGE = eINSTANCE.getDroolsRule_Package();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.DroolsPackageImpl <em>Drools Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.DroolsPackageImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsPackage()
		 * @generated
		 */
		EClass DROOLS_PACKAGE = eINSTANCE.getDroolsPackage();

		/**
		 * The meta object literal for the '<em><b>Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_PACKAGE__PLUGIN = eINSTANCE.getDroolsPackage_Plugin();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_PACKAGE__PACKAGE = eINSTANCE.getDroolsPackage_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_PACKAGE__NAME = eINSTANCE.getDroolsPackage_Name();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DROOLS_PACKAGE__RULES = eINSTANCE.getDroolsPackage_Rules();

		/**
		 * The meta object literal for the '<em><b>Javadocs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DROOLS_PACKAGE__JAVADOCS = eINSTANCE.getDroolsPackage_Javadocs();

		/**
		 * The meta object literal for the '<em><b>Unique Rules</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROOLS_PACKAGE__UNIQUE_RULES = eINSTANCE.getDroolsPackage_UniqueRules();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.FileReferenceImpl <em>File Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.FileReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileReference()
		 * @generated
		 */
		EClass FILE_REFERENCE = eINSTANCE.getFileReference();

		/**
		 * The meta object literal for the '<em><b>Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REFERENCE__PLUGIN = eINSTANCE.getFileReference_Plugin();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REFERENCE__PACKAGE = eINSTANCE.getFileReference_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REFERENCE__NAME = eINSTANCE.getFileReference_Name();

		/**
		 * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_REFERENCE__LINES = eINSTANCE.getFileReference_Lines();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl <em>File Line Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileLineReference()
		 * @generated
		 */
		EClass FILE_LINE_REFERENCE = eINSTANCE.getFileLineReference();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_LINE_REFERENCE__FILE = eINSTANCE.getFileLineReference_File();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_LINE_REFERENCE__LINE = eINSTANCE.getFileLineReference_Line();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavadocFragmentImpl <em>Javadoc Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavadocFragmentImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocFragment()
		 * @generated
		 */
		EClass JAVADOC_FRAGMENT = eINSTANCE.getJavadocFragment();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl <em>Javadoc Tag Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocTagElement()
		 * @generated
		 */
		EClass JAVADOC_TAG_ELEMENT = eINSTANCE.getJavadocTagElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVADOC_TAG_ELEMENT__NAME = eINSTANCE.getJavadocTagElement_Name();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVADOC_TAG_ELEMENT__FRAGMENTS = eINSTANCE.getJavadocTagElement_Fragments();

		/**
		 * The meta object literal for the '<em><b>Java Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVADOC_TAG_ELEMENT__JAVA_PARENT = eINSTANCE.getJavadocTagElement_JavaParent();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavadocTextElementImpl <em>Javadoc Text Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavadocTextElementImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocTextElement()
		 * @generated
		 */
		EClass JAVADOC_TEXT_ELEMENT = eINSTANCE.getJavadocTextElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVADOC_TEXT_ELEMENT__VALUE = eINSTANCE.getJavadocTextElement_Value();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavaElementImpl <em>Java Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavaElementImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaElement()
		 * @generated
		 */
		EClass JAVA_ELEMENT = eINSTANCE.getJavaElement();

		/**
		 * The meta object literal for the '<em><b>Javadocs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVA_ELEMENT__JAVADOCS = eINSTANCE.getJavaElement_Javadocs();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavadocMethodReferenceImpl <em>Javadoc Method Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavadocMethodReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocMethodReference()
		 * @generated
		 */
		EClass JAVADOC_METHOD_REFERENCE = eINSTANCE.getJavadocMethodReference();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVADOC_METHOD_REFERENCE__REFERENCE = eINSTANCE.getJavadocMethodReference_Reference();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.JavadocClassReferenceImpl <em>Javadoc Class Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.JavadocClassReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavadocClassReference()
		 * @generated
		 */
		EClass JAVADOC_CLASS_REFERENCE = eINSTANCE.getJavadocClassReference();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVADOC_CLASS_REFERENCE__REFERENCE = eINSTANCE.getJavadocClassReference_Reference();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.EMFAttributeImpl <em>EMF Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.EMFAttributeImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFAttribute()
		 * @generated
		 */
		EClass EMF_ATTRIBUTE = eINSTANCE.getEMFAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__NAME = eINSTANCE.getEMFAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__ID = eINSTANCE.getEMFAttribute_Id();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__LOWER_BOUND = eINSTANCE.getEMFAttribute_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__UPPER_BOUND = eINSTANCE.getEMFAttribute_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__TYPE = eINSTANCE.getEMFAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Containing Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_ATTRIBUTE__CONTAINING_TYPE = eINSTANCE.getEMFAttribute_ContainingType();

		/**
		 * The meta object literal for the '<em><b>Default Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__DEFAULT_LITERAL = eINSTANCE.getEMFAttribute_DefaultLiteral();

		/**
		 * The meta object literal for the '<em><b>Tagline</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_ATTRIBUTE__TAGLINE = eINSTANCE.getEMFAttribute_Tagline();

		/**
		 * The meta object literal for the '<em><b>Accepted Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_ATTRIBUTE__ACCEPTED_VALUES = eINSTANCE.getEMFAttribute_AcceptedValues();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.EMFReferenceImpl <em>EMF Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.EMFReferenceImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getEMFReference()
		 * @generated
		 */
		EClass EMF_REFERENCE = eINSTANCE.getEMFReference();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_REFERENCE__NAME = eINSTANCE.getEMFReference_Name();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_REFERENCE__LOWER_BOUND = eINSTANCE.getEMFReference_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_REFERENCE__UPPER_BOUND = eINSTANCE.getEMFReference_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Containment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_REFERENCE__CONTAINMENT = eINSTANCE.getEMFReference_Containment();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_REFERENCE__TYPE = eINSTANCE.getEMFReference_Type();

		/**
		 * The meta object literal for the '<em><b>Containing Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_REFERENCE__CONTAINING_TYPE = eINSTANCE.getEMFReference_ContainingType();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_REFERENCE__TYPE_NAME = eINSTANCE.getEMFReference_TypeName();

		/**
		 * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_REFERENCE__OPPOSITE = eINSTANCE.getEMFReference_Opposite();

		/**
		 * The meta object literal for the '<em><b>Tagline</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_REFERENCE__TAGLINE = eINSTANCE.getEMFReference_Tagline();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.TemplateFileImpl <em>Template File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.TemplateFileImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getTemplateFile()
		 * @generated
		 */
		EClass TEMPLATE_FILE = eINSTANCE.getTemplateFile();

		/**
		 * The meta object literal for the '<em><b>Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_FILE__PLUGIN = eINSTANCE.getTemplateFile_Plugin();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_FILE__PACKAGE = eINSTANCE.getTemplateFile_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_FILE__NAME = eINSTANCE.getTemplateFile_Name();

		/**
		 * The meta object literal for the '<em><b>Templates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_FILE__TEMPLATES = eINSTANCE.getTemplateFile_Templates();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.TemplateImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getTemplate()
		 * @generated
		 */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE__NAME = eINSTANCE.getTemplate_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE__TYPE = eINSTANCE.getTemplate_Type();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE__LINE = eINSTANCE.getTemplate_Line();

		/**
		 * The meta object literal for the '<em><b>Template File</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE__TEMPLATE_FILE = eINSTANCE.getTemplate_TemplateFile();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.impl.MetricImpl <em>Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.impl.MetricImpl
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getMetric()
		 * @generated
		 */
		EClass METRIC = eINSTANCE.getMetric();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__NAME = eINSTANCE.getMetric_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__VALUE = eINSTANCE.getMetric_Value();

		/**
		 * The meta object literal for the '{@link org.openiaml.docs.modeldoc.ConstraintType <em>Constraint Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.docs.modeldoc.ConstraintType
		 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraintType()
		 * @generated
		 */
		EEnum CONSTRAINT_TYPE = eINSTANCE.getConstraintType();

	}

} //ModeldocPackage
