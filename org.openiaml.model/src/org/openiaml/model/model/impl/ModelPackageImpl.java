/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventAwareOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.OperationParameter;
import org.openiaml.model.model.OperationReference;
import org.openiaml.model.model.ParameterReference;
import org.openiaml.model.model.SingleOperation;
import org.openiaml.model.model.ValueReference;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

import org.openiaml.model.model.operations.OperationsPackage;

import org.openiaml.model.model.operations.impl.OperationsPackageImpl;

import org.openiaml.model.model.visual.VisualPackage;

import org.openiaml.model.model.visual.impl.VisualPackageImpl;

import org.openiaml.model.model.wires.WiresPackage;

import org.openiaml.model.model.wires.impl.WiresPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsEventTriggersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chainedOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeChainedOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventAwareOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsOperationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationElementContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationElementPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visibleThingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internetApplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainStoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueReferenceEClass = null;

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
	 * @see org.openiaml.model.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);

		// Create package meta-data objects
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theOperationsPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireEdge() {
		return wireEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireEdge_From() {
		return (EReference)wireEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireEdge_To() {
		return (EReference)wireEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireEdgeDestination() {
		return wireEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireEdgeDestination_InEdges() {
		return (EReference)wireEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireEdgesSource() {
		return wireEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireEdgesSource_Edges() {
		return (EReference)wireEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventTrigger() {
		return eventTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainsEventTriggers() {
		return containsEventTriggersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsEventTriggers_EventTriggers() {
		return (EReference)containsEventTriggersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainObject() {
		return domainObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainObject_Attributes() {
		return (EReference)domainObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainAttribute() {
		return domainAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Parameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_CodeBlock() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationParameter() {
		return operationParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleOperation() {
		return singleOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChainedOperation() {
		return chainedOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeOperation() {
		return compositeOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeOperation_SubOperations() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeChainedOperation() {
		return compositeChainedOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventAwareOperation() {
		return eventAwareOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainsOperations() {
		return containsOperationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsOperations_Operations() {
		return (EReference)containsOperationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationElement() {
		return applicationElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationElement_Properties() {
		return (EReference)applicationElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationElementContainer() {
		return applicationElementContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationElementContainer_Children() {
		return (EReference)applicationElementContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationElementProperty() {
		return applicationElementPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationElementProperty_InValueReferences() {
		return (EReference)applicationElementPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVisibleThing() {
		return visibleThingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternetApplication() {
		return internetApplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternetApplication_Properties() {
		return (EReference)internetApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternetApplication_Children() {
		return (EReference)internetApplicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternetApplication_DomainStores() {
		return (EReference)internetApplicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainStore() {
		return domainStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainStore_Children() {
		return (EReference)domainStoreEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainStore_Properties() {
		return (EReference)domainStoreEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationReference() {
		return operationReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationReference_Operation() {
		return (EReference)operationReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationReference_ParameterReferences() {
		return (EReference)operationReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterReference() {
		return parameterReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterReference_Parameter() {
		return (EReference)parameterReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterReference_ValueReference() {
		return (EReference)parameterReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueReference() {
		return valueReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueReference_Value() {
		return (EReference)valueReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
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
		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		wireEdgeEClass = createEClass(WIRE_EDGE);
		createEReference(wireEdgeEClass, WIRE_EDGE__FROM);
		createEReference(wireEdgeEClass, WIRE_EDGE__TO);

		wireEdgeDestinationEClass = createEClass(WIRE_EDGE_DESTINATION);
		createEReference(wireEdgeDestinationEClass, WIRE_EDGE_DESTINATION__IN_EDGES);

		wireEdgesSourceEClass = createEClass(WIRE_EDGES_SOURCE);
		createEReference(wireEdgesSourceEClass, WIRE_EDGES_SOURCE__EDGES);

		eventTriggerEClass = createEClass(EVENT_TRIGGER);

		containsEventTriggersEClass = createEClass(CONTAINS_EVENT_TRIGGERS);
		createEReference(containsEventTriggersEClass, CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS);

		domainObjectEClass = createEClass(DOMAIN_OBJECT);
		createEReference(domainObjectEClass, DOMAIN_OBJECT__ATTRIBUTES);

		domainAttributeEClass = createEClass(DOMAIN_ATTRIBUTE);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__PARAMETERS);
		createEAttribute(operationEClass, OPERATION__CODE_BLOCK);

		operationParameterEClass = createEClass(OPERATION_PARAMETER);

		singleOperationEClass = createEClass(SINGLE_OPERATION);

		chainedOperationEClass = createEClass(CHAINED_OPERATION);

		compositeOperationEClass = createEClass(COMPOSITE_OPERATION);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__SUB_OPERATIONS);

		compositeChainedOperationEClass = createEClass(COMPOSITE_CHAINED_OPERATION);

		eventAwareOperationEClass = createEClass(EVENT_AWARE_OPERATION);

		containsOperationsEClass = createEClass(CONTAINS_OPERATIONS);
		createEReference(containsOperationsEClass, CONTAINS_OPERATIONS__OPERATIONS);

		applicationElementEClass = createEClass(APPLICATION_ELEMENT);
		createEReference(applicationElementEClass, APPLICATION_ELEMENT__PROPERTIES);

		applicationElementContainerEClass = createEClass(APPLICATION_ELEMENT_CONTAINER);
		createEReference(applicationElementContainerEClass, APPLICATION_ELEMENT_CONTAINER__CHILDREN);

		applicationElementPropertyEClass = createEClass(APPLICATION_ELEMENT_PROPERTY);
		createEReference(applicationElementPropertyEClass, APPLICATION_ELEMENT_PROPERTY__IN_VALUE_REFERENCES);

		visibleThingEClass = createEClass(VISIBLE_THING);

		internetApplicationEClass = createEClass(INTERNET_APPLICATION);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__PROPERTIES);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__CHILDREN);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__DOMAIN_STORES);

		domainStoreEClass = createEClass(DOMAIN_STORE);
		createEReference(domainStoreEClass, DOMAIN_STORE__CHILDREN);
		createEReference(domainStoreEClass, DOMAIN_STORE__PROPERTIES);

		operationReferenceEClass = createEClass(OPERATION_REFERENCE);
		createEReference(operationReferenceEClass, OPERATION_REFERENCE__OPERATION);
		createEReference(operationReferenceEClass, OPERATION_REFERENCE__PARAMETER_REFERENCES);

		parameterReferenceEClass = createEClass(PARAMETER_REFERENCE);
		createEReference(parameterReferenceEClass, PARAMETER_REFERENCE__PARAMETER);
		createEReference(parameterReferenceEClass, PARAMETER_REFERENCE__VALUE_REFERENCE);

		valueReferenceEClass = createEClass(VALUE_REFERENCE);
		createEReference(valueReferenceEClass, VALUE_REFERENCE__VALUE);
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

		// Obtain other dependent packages
		VisualPackage theVisualPackage = (VisualPackage)EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI);
		WiresPackage theWiresPackage = (WiresPackage)EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI);
		OperationsPackage theOperationsPackage = (OperationsPackage)EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theVisualPackage);
		getESubpackages().add(theWiresPackage);
		getESubpackages().add(theOperationsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		eventTriggerEClass.getESuperTypes().add(this.getNamedElement());
		eventTriggerEClass.getESuperTypes().add(this.getWireEdgesSource());
		domainObjectEClass.getESuperTypes().add(this.getApplicationElement());
		domainAttributeEClass.getESuperTypes().add(this.getApplicationElement());
		operationEClass.getESuperTypes().add(this.getWireEdgeDestination());
		operationEClass.getESuperTypes().add(this.getNamedElement());
		operationParameterEClass.getESuperTypes().add(this.getNamedElement());
		operationParameterEClass.getESuperTypes().add(this.getWireEdgeDestination());
		singleOperationEClass.getESuperTypes().add(this.getOperation());
		chainedOperationEClass.getESuperTypes().add(this.getOperation());
		chainedOperationEClass.getESuperTypes().add(this.getWireEdgesSource());
		compositeOperationEClass.getESuperTypes().add(this.getOperation());
		compositeChainedOperationEClass.getESuperTypes().add(this.getCompositeOperation());
		compositeChainedOperationEClass.getESuperTypes().add(this.getChainedOperation());
		eventAwareOperationEClass.getESuperTypes().add(this.getCompositeOperation());
		eventAwareOperationEClass.getESuperTypes().add(this.getContainsEventTriggers());
		applicationElementEClass.getESuperTypes().add(this.getContainsOperations());
		applicationElementEClass.getESuperTypes().add(this.getNamedElement());
		applicationElementEClass.getESuperTypes().add(this.getContainsEventTriggers());
		applicationElementEClass.getESuperTypes().add(this.getWireEdgesSource());
		applicationElementEClass.getESuperTypes().add(this.getWireEdgeDestination());
		applicationElementContainerEClass.getESuperTypes().add(this.getApplicationElement());
		applicationElementPropertyEClass.getESuperTypes().add(this.getNamedElement());
		applicationElementPropertyEClass.getESuperTypes().add(this.getWireEdgesSource());
		applicationElementPropertyEClass.getESuperTypes().add(this.getWireEdgeDestination());
		visibleThingEClass.getESuperTypes().add(this.getApplicationElementContainer());
		internetApplicationEClass.getESuperTypes().add(this.getContainsOperations());
		internetApplicationEClass.getESuperTypes().add(this.getContainsEventTriggers());
		internetApplicationEClass.getESuperTypes().add(this.getNamedElement());
		domainStoreEClass.getESuperTypes().add(this.getContainsOperations());
		domainStoreEClass.getESuperTypes().add(this.getContainsEventTriggers());
		domainStoreEClass.getESuperTypes().add(this.getNamedElement());
		operationReferenceEClass.getESuperTypes().add(this.getNamedElement());
		parameterReferenceEClass.getESuperTypes().add(this.getNamedElement());
		valueReferenceEClass.getESuperTypes().add(this.getNamedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgeEClass, WireEdge.class, "WireEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdge_From(), this.getWireEdgesSource(), this.getWireEdgesSource_Edges(), "from", null, 1, 1, WireEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWireEdge_To(), this.getWireEdgeDestination(), this.getWireEdgeDestination_InEdges(), "to", null, 1, 1, WireEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgeDestinationEClass, WireEdgeDestination.class, "WireEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdgeDestination_InEdges(), this.getWireEdge(), this.getWireEdge_To(), "inEdges", null, 0, -1, WireEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgesSourceEClass, WireEdgesSource.class, "WireEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdgesSource_Edges(), this.getWireEdge(), this.getWireEdge_From(), "edges", null, 0, -1, WireEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTriggerEClass, EventTrigger.class, "EventTrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containsEventTriggersEClass, ContainsEventTriggers.class, "ContainsEventTriggers", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsEventTriggers_EventTriggers(), this.getEventTrigger(), null, "eventTriggers", null, 0, -1, ContainsEventTriggers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainObjectEClass, DomainObject.class, "DomainObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainObject_Attributes(), this.getDomainAttribute(), null, "attributes", null, 0, -1, DomainObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainAttributeEClass, DomainAttribute.class, "DomainAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperation_Parameters(), this.getOperationParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_CodeBlock(), ecorePackage.getEString(), "codeBlock", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationParameterEClass, OperationParameter.class, "OperationParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(singleOperationEClass, SingleOperation.class, "SingleOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(chainedOperationEClass, ChainedOperation.class, "ChainedOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeOperationEClass, CompositeOperation.class, "CompositeOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeOperation_SubOperations(), this.getOperation(), null, "subOperations", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeChainedOperationEClass, CompositeChainedOperation.class, "CompositeChainedOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eventAwareOperationEClass, EventAwareOperation.class, "EventAwareOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containsOperationsEClass, ContainsOperations.class, "ContainsOperations", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsOperations_Operations(), this.getOperation(), null, "operations", null, 0, -1, ContainsOperations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementEClass, ApplicationElement.class, "ApplicationElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElement_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, ApplicationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementContainerEClass, ApplicationElementContainer.class, "ApplicationElementContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElementContainer_Children(), this.getApplicationElement(), null, "children", null, 0, -1, ApplicationElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementPropertyEClass, ApplicationElementProperty.class, "ApplicationElementProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElementProperty_InValueReferences(), this.getValueReference(), this.getValueReference_Value(), "inValueReferences", null, 0, 1, ApplicationElementProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(visibleThingEClass, VisibleThing.class, "VisibleThing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internetApplicationEClass, InternetApplication.class, "InternetApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternetApplication_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_Children(), this.getApplicationElement(), null, "children", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_DomainStores(), this.getDomainStore(), null, "domainStores", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainStoreEClass, DomainStore.class, "DomainStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainStore_Children(), this.getDomainObject(), null, "children", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainStore_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationReferenceEClass, OperationReference.class, "OperationReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationReference_Operation(), this.getOperation(), null, "operation", null, 0, 1, OperationReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationReference_ParameterReferences(), this.getParameterReference(), null, "parameterReferences", null, 0, -1, OperationReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterReferenceEClass, ParameterReference.class, "ParameterReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterReference_Parameter(), this.getOperationParameter(), null, "parameter", null, 0, 1, ParameterReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterReference_ValueReference(), this.getValueReference(), null, "valueReference", null, 0, 1, ParameterReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueReferenceEClass, ValueReference.class, "ValueReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValueReference_Value(), this.getApplicationElementProperty(), this.getApplicationElementProperty_InValueReferences(), "value", null, 0, 1, ValueReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://openiaml.org/comment
		createCommentAnnotations();
		// http://openiaml.org/todo
		createTodoAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://openiaml.org/comment</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCommentAnnotations() {
		String source = "http://openiaml.org/comment";		
		addAnnotation
		  (applicationElementEClass, 
		   source, 
		   new String[] {
			 "editor", "org.openiaml.model.diagram.element"
		   });			
		addAnnotation
		  (visibleThingEClass, 
		   source, 
		   new String[] {
			 "comment", "this used to mean nothing; now anything that extends VisibleThing (which unforuntately needs to be concrete) has an editor",
			 "editor", "org.openiaml.model.diagram.visual"
		   });		
		addAnnotation
		  (internetApplicationEClass, 
		   source, 
		   new String[] {
			 "comment", "we cannot have InternetApplications inside of other ElementContainers, thus we don\'t define it as an ApplicationElement",
			 "comment2", "but why can we have it as an ActivityEdgeSource? I don\'t think there are any examples of InternetApplication wire --> something else; all the wires are from objects INSIDE the IA",
			 "editor", "org.openiaml.model.diagram"
		   });		
		addAnnotation
		  (domainStoreEClass, 
		   source, 
		   new String[] {
			 "comment", "stores database objects",
			 "editor", "org.openiaml.model.diagram.domain_store"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://openiaml.org/todo</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createTodoAnnotations() {
		String source = "http://openiaml.org/todo";			
		addAnnotation
		  (applicationElementPropertyEClass, 
		   source, 
		   new String[] {
			 "todo", "maybe rename? delete subclasses?"
		   });			
	}

} //ModelPackageImpl
