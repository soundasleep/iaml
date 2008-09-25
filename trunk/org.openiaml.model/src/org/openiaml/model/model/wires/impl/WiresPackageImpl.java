/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openiaml.model.model.ModelPackage;

import org.openiaml.model.model.impl.ModelPackageImpl;

import org.openiaml.model.model.operations.OperationsPackage;

import org.openiaml.model.model.operations.impl.OperationsPackageImpl;

import org.openiaml.model.model.visual.VisualPackage;

import org.openiaml.model.model.visual.impl.VisualPackageImpl;

import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ExecutionWire;
import org.openiaml.model.model.wires.PropertyToExecutionWireWire;
import org.openiaml.model.model.wires.PropertyToParameterWire;
import org.openiaml.model.model.wires.ProvidedParameterWire;
import org.openiaml.model.model.wires.RunWire;
import org.openiaml.model.model.wires.SingleWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresFactory;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WiresPackageImpl extends EPackageImpl implements WiresPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syncWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedParameterWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyToParameterWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyToExecutionWireWireEClass = null;

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
	 * @see org.openiaml.model.model.wires.WiresPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WiresPackageImpl() {
		super(eNS_URI, WiresFactory.eINSTANCE);
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
	public static WiresPackage init() {
		if (isInited) return (WiresPackage)EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI);

		// Obtain or create and register package
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new WiresPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);

		// Create package meta-data objects
		theWiresPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theOperationsPackage.createPackageContents();

		// Initialize created meta-data
		theWiresPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWiresPackage.freeze();

		return theWiresPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleWire() {
		return singleWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeWire() {
		return compositeWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeWire_Children() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeWire_Properties() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeWire_Operations() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeWire_EventTriggers() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSyncWire() {
		return syncWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunWire() {
		return runWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionWire() {
		return executionWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedParameterWire() {
		return providedParameterWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyToParameterWire() {
		return propertyToParameterWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyToExecutionWireWire() {
		return propertyToExecutionWireWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiresFactory getWiresFactory() {
		return (WiresFactory)getEFactoryInstance();
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
		singleWireEClass = createEClass(SINGLE_WIRE);

		compositeWireEClass = createEClass(COMPOSITE_WIRE);
		createEReference(compositeWireEClass, COMPOSITE_WIRE__CHILDREN);
		createEReference(compositeWireEClass, COMPOSITE_WIRE__PROPERTIES);
		createEReference(compositeWireEClass, COMPOSITE_WIRE__OPERATIONS);
		createEReference(compositeWireEClass, COMPOSITE_WIRE__EVENT_TRIGGERS);

		syncWireEClass = createEClass(SYNC_WIRE);

		runWireEClass = createEClass(RUN_WIRE);

		executionWireEClass = createEClass(EXECUTION_WIRE);

		providedParameterWireEClass = createEClass(PROVIDED_PARAMETER_WIRE);

		propertyToParameterWireEClass = createEClass(PROPERTY_TO_PARAMETER_WIRE);

		propertyToExecutionWireWireEClass = createEClass(PROPERTY_TO_EXECUTION_WIRE_WIRE);
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
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		singleWireEClass.getESuperTypes().add(theModelPackage.getWireEdge());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getWireEdge());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		syncWireEClass.getESuperTypes().add(this.getCompositeWire());
		runWireEClass.getESuperTypes().add(this.getCompositeWire());
		executionWireEClass.getESuperTypes().add(this.getSingleWire());
		providedParameterWireEClass.getESuperTypes().add(this.getSingleWire());
		propertyToParameterWireEClass.getESuperTypes().add(this.getSingleWire());
		propertyToExecutionWireWireEClass.getESuperTypes().add(this.getSingleWire());

		// Initialize classes and features; add operations and parameters
		initEClass(singleWireEClass, SingleWire.class, "SingleWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeWireEClass, CompositeWire.class, "CompositeWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeWire_Children(), theModelPackage.getApplicationElement(), null, "children", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Properties(), theModelPackage.getApplicationElementProperty(), null, "properties", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Operations(), theModelPackage.getOperation(), null, "operations", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_EventTriggers(), theModelPackage.getEventTrigger(), null, "eventTriggers", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncWireEClass, SyncWire.class, "SyncWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runWireEClass, RunWire.class, "RunWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(executionWireEClass, ExecutionWire.class, "ExecutionWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedParameterWireEClass, ProvidedParameterWire.class, "ProvidedParameterWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyToParameterWireEClass, PropertyToParameterWire.class, "PropertyToParameterWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyToExecutionWireWireEClass, PropertyToExecutionWireWire.class, "PropertyToExecutionWireWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create annotations
		// http://openiaml.org/comment
		createCommentAnnotations();
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
		  (singleWireEClass, 
		   source, 
		   new String[] {
			 "comment", "ideally this would be abstract; but we want to have a \"wire\" diagram editor, and the root element needs to be concrete. the other option is to have multiple diagram editors per concrete wire..."
		   });		
		addAnnotation
		  (compositeWireEClass, 
		   source, 
		   new String[] {
			 "comment", "ideally this would be abstract; but we want to have a \"wire\" diagram editor, and the root element needs to be concrete. the other option is to have multiple diagram editors per concrete wire...",
			 "comment2", "easy visualisation: all composite wires are dashed",
			 "comment3", "only composite wires have a name now"
		   });		
		addAnnotation
		  (runWireEClass, 
		   source, 
		   new String[] {
			 "comment", "RunWire: a composite wire that contains ExecutionWires/etc"
		   });
	}

} //WiresPackageImpl
