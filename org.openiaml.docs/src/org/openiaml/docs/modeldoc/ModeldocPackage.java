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
	 * The number of structural features of the '<em>Model Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_DOCUMENTATION_FEATURE_COUNT = 2;

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
	 * The feature id for the '<em><b>Runtime Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__RUNTIME_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Tagline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__TAGLINE = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Operational Semantics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__OPERATIONAL_SEMANTICS = 4;

	/**
	 * The feature id for the '<em><b>Inference Semantics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__INFERENCE_SEMANTICS = 5;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__CONSTRAINTS = 6;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__EXTENSIONS = 7;

	/**
	 * The feature id for the '<em><b>Graphical Representations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__GRAPHICAL_REPRESENTATIONS = 8;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__EXAMPLES = 9;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS__PARENT = 10;

	/**
	 * The number of structural features of the '<em>EMF Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_CLASS_FEATURE_COUNT = 11;


	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.SemanticImpl <em>Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.SemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getSemantic()
	 * @generated
	 */
	int SEMANTIC = 2;

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
	int EXAMPLE = 3;

	/**
	 * The feature id for the '<em><b>Example Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Example Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLE_TEST = 1;

	/**
	 * The number of structural features of the '<em>Example</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl <em>Operational Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.OperationalSemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getOperationalSemantic()
	 * @generated
	 */
	int OPERATIONAL_SEMANTIC = 4;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operational Semantic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTIC_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl <em>Graphical Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.GraphicalRepresentationImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getGraphicalRepresentation()
	 * @generated
	 */
	int GRAPHICAL_REPRESENTATION = 5;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_REPRESENTATION__REFERENCE = SEMANTIC__REFERENCE;

	/**
	 * The number of structural features of the '<em>Graphical Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_REPRESENTATION_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl <em>Inference Semantic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.InferenceSemanticImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getInferenceSemantic()
	 * @generated
	 */
	int INFERENCE_SEMANTIC = 6;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC__DESCRIPTION = SEMANTIC_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Inference Semantic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_SEMANTIC_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ConstraintImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 7;

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
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ModelExtensionImpl <em>Model Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ModelExtensionImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelExtension()
	 * @generated
	 */
	int MODEL_EXTENSION = 8;

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
	 * The number of structural features of the '<em>Model Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_EXTENSION_FEATURE_COUNT = SEMANTIC_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 9;

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
	int JAVA_CLASS = 10;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PLUGIN = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PACKAGE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__NAME = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__METHODS = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Java Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.JavaMethodImpl <em>Java Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.JavaMethodImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getJavaMethod()
	 * @generated
	 */
	int JAVA_METHOD = 11;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__NAME = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__LINE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Java Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD__JAVA_CLASS = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Java Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_METHOD_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.ModelReferenceImpl <em>Model Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.ModelReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getModelReference()
	 * @generated
	 */
	int MODEL_REFERENCE = 12;

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
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl <em>Drools Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.DroolsRuleImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsRule()
	 * @generated
	 */
	int DROOLS_RULE = 14;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.DroolsPackageImpl <em>Drools Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.DroolsPackageImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getDroolsPackage()
	 * @generated
	 */
	int DROOLS_PACKAGE = 13;

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
	 * The number of structural features of the '<em>Drools Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_PACKAGE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__PARENT = REFERENCE__PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__NAME = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__LINE = REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE__PACKAGE = REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Drools Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROOLS_RULE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.FileReferenceImpl <em>File Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.FileReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileReference()
	 * @generated
	 */
	int FILE_REFERENCE = 15;

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
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl <em>File Line Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.impl.FileLineReferenceImpl
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getFileLineReference()
	 * @generated
	 */
	int FILE_LINE_REFERENCE = 16;

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
	 * The meta object id for the '{@link org.openiaml.docs.modeldoc.ConstraintType <em>Constraint Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.docs.modeldoc.ConstraintType
	 * @see org.openiaml.docs.modeldoc.impl.ModeldocPackageImpl#getConstraintType()
	 * @generated
	 */
	int CONSTRAINT_TYPE = 17;


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
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFClass#getTagline <em>Tagline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tagline</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getTagline()
	 * @see #getEMFClass()
	 * @generated
	 */
	EAttribute getEMFClass_Tagline();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.EMFClass#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getDescription()
	 * @see #getEMFClass()
	 * @generated
	 */
	EAttribute getEMFClass_Description();

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
	 * Returns the meta object for the containment reference list '{@link org.openiaml.docs.modeldoc.EMFClass#getGraphicalRepresentations <em>Graphical Representations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Graphical Representations</em>'.
	 * @see org.openiaml.docs.modeldoc.EMFClass#getGraphicalRepresentations()
	 * @see #getEMFClass()
	 * @generated
	 */
	EReference getEMFClass_GraphicalRepresentations();

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
	 * Returns the meta object for the reference '{@link org.openiaml.docs.modeldoc.Example#getExampleTest <em>Example Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Example Test</em>'.
	 * @see org.openiaml.docs.modeldoc.Example#getExampleTest()
	 * @see #getExample()
	 * @generated
	 */
	EReference getExample_ExampleTest();

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
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.OperationalSemantic#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.OperationalSemantic#getDescription()
	 * @see #getOperationalSemantic()
	 * @generated
	 */
	EAttribute getOperationalSemantic_Description();

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
	 * Returns the meta object for the attribute '{@link org.openiaml.docs.modeldoc.InferenceSemantic#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.openiaml.docs.modeldoc.InferenceSemantic#getDescription()
	 * @see #getInferenceSemantic()
	 * @generated
	 */
	EAttribute getInferenceSemantic_Description();

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
		 * The meta object literal for the '<em><b>Runtime Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__RUNTIME_CLASS = eINSTANCE.getEMFClass_RuntimeClass();

		/**
		 * The meta object literal for the '<em><b>Tagline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_CLASS__TAGLINE = eINSTANCE.getEMFClass_Tagline();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_CLASS__DESCRIPTION = eINSTANCE.getEMFClass_Description();

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
		 * The meta object literal for the '<em><b>Graphical Representations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMF_CLASS__GRAPHICAL_REPRESENTATIONS = eINSTANCE.getEMFClass_GraphicalRepresentations();

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
		 * The meta object literal for the '<em><b>Example Test</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAMPLE__EXAMPLE_TEST = eINSTANCE.getExample_ExampleTest();

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
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATIONAL_SEMANTIC__DESCRIPTION = eINSTANCE.getOperationalSemantic_Description();

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
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFERENCE_SEMANTIC__DESCRIPTION = eINSTANCE.getInferenceSemantic_Description();

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
