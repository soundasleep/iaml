/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.impl.ModelPackageImpl;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.impl.OperationsPackageImpl;
import org.openiaml.model.model.scopes.impl.PackageImpl;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.visual.impl.VisualPackageImpl;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.CreateWire;
import org.openiaml.model.model.wires.LinkWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SaveWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.SingleWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.UpdateWire;
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
	private EClass runInstanceWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass updateWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass saveWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigateWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectWireEClass = null;

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
		PackageImpl thePackage = (PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.openiaml.model.model.scopes.Package.eNS_URI) instanceof PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.openiaml.model.model.scopes.Package.eNS_URI) : org.openiaml.model.model.scopes.Package.eINSTANCE);

		// Create package meta-data objects
		theWiresPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		thePackage.createPackageContents();

		// Initialize created meta-data
		theWiresPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		thePackage.initializePackageContents();

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
	public EReference getCompositeWire_Parameters() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeWire_Values() {
		return (EReference)compositeWireEClass.getEStructuralFeatures().get(5);
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
	public EClass getRunInstanceWire() {
		return runInstanceWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterWire() {
		return parameterWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterWire_ParameterName() {
		return (EAttribute)parameterWireEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreateWire() {
		return createWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetWire() {
		return setWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUpdateWire() {
		return updateWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSaveWire() {
		return saveWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkWire() {
		return linkWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigateWire() {
		return navigateWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelectWire() {
		return selectWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelectWire_Query() {
		return (EAttribute)selectWireEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelectWire_Limit() {
		return (EAttribute)selectWireEClass.getEStructuralFeatures().get(1);
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
		createEReference(compositeWireEClass, COMPOSITE_WIRE__PARAMETERS);
		createEReference(compositeWireEClass, COMPOSITE_WIRE__VALUES);

		syncWireEClass = createEClass(SYNC_WIRE);

		runInstanceWireEClass = createEClass(RUN_INSTANCE_WIRE);

		parameterWireEClass = createEClass(PARAMETER_WIRE);
		createEAttribute(parameterWireEClass, PARAMETER_WIRE__PARAMETER_NAME);

		createWireEClass = createEClass(CREATE_WIRE);

		setWireEClass = createEClass(SET_WIRE);

		updateWireEClass = createEClass(UPDATE_WIRE);

		saveWireEClass = createEClass(SAVE_WIRE);

		linkWireEClass = createEClass(LINK_WIRE);

		navigateWireEClass = createEClass(NAVIGATE_WIRE);

		selectWireEClass = createEClass(SELECT_WIRE);
		createEAttribute(selectWireEClass, SELECT_WIRE__QUERY);
		createEAttribute(selectWireEClass, SELECT_WIRE__LIMIT);
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
		compositeWireEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		syncWireEClass.getESuperTypes().add(this.getCompositeWire());
		runInstanceWireEClass.getESuperTypes().add(this.getCompositeWire());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		parameterWireEClass.getESuperTypes().add(this.getSingleWire());
		createWireEClass.getESuperTypes().add(this.getCompositeWire());
		setWireEClass.getESuperTypes().add(this.getCompositeWire());
		updateWireEClass.getESuperTypes().add(this.getCompositeWire());
		saveWireEClass.getESuperTypes().add(this.getCompositeWire());
		linkWireEClass.getESuperTypes().add(this.getCompositeWire());
		navigateWireEClass.getESuperTypes().add(this.getCompositeWire());
		selectWireEClass.getESuperTypes().add(this.getCompositeWire());
		selectWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());

		// Initialize classes and features; add operations and parameters
		initEClass(singleWireEClass, SingleWire.class, "SingleWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeWireEClass, CompositeWire.class, "CompositeWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeWire_Children(), theModelPackage.getApplicationElement(), null, "children", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Properties(), theModelPackage.getApplicationElementProperty(), null, "properties", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Operations(), theModelPackage.getOperation(), null, "operations", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_EventTriggers(), theModelPackage.getEventTrigger(), null, "eventTriggers", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Parameters(), theModelPackage.getParameter(), null, "parameters", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Values(), theModelPackage.getStaticValue(), null, "values", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncWireEClass, SyncWire.class, "SyncWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runInstanceWireEClass, RunInstanceWire.class, "RunInstanceWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parameterWireEClass, ParameterWire.class, "ParameterWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterWire_ParameterName(), ecorePackage.getEString(), "parameterName", null, 0, 1, ParameterWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createWireEClass, CreateWire.class, "CreateWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(setWireEClass, SetWire.class, "SetWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(updateWireEClass, UpdateWire.class, "UpdateWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(saveWireEClass, SaveWire.class, "SaveWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkWireEClass, LinkWire.class, "LinkWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigateWireEClass, NavigateWire.class, "NavigateWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectWireEClass, SelectWire.class, "SelectWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectWire_Query(), ecorePackage.getEString(), "query", null, 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSelectWire_Limit(), ecorePackage.getEInt(), "limit", "1", 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
			 "comment", "why do we have SingleWire at all? (following a design pattern?)"
		   });		
		addAnnotation
		  (compositeWireEClass, 
		   source, 
		   new String[] {
			 "comment", "ideally this would be abstract; but we want to have a \"wire\" diagram editor, and the root element needs to be concrete. the other option is to have multiple diagram editors per concrete wire...",
			 "comment2", "easy visualisation: all composite wires are dashed",
			 "comment3", "only composite wires have a name now",
			 "editor", "org.openiaml.model.diagram.wire",
			 "comment4", "a wire shouldn\'t have parameters; but the operations contained within need them rendered."
		   });		
		addAnnotation
		  (runInstanceWireEClass, 
		   source, 
		   new String[] {
			 "comment", "RunWire: a composite wire that contains ExecutionWires/etc"
		   });		
		addAnnotation
		  (getParameterWire_ParameterName(), 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (createWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (setWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (updateWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (saveWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (linkWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (navigateWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });		
		addAnnotation
		  (selectWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });
	}

} //WiresPackageImpl
