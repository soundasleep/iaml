/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.impl.ComponentsPackageImpl;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.impl.DomainPackageImpl;
import org.openiaml.model.model.impl.ModelPackageImpl;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.impl.OperationsPackageImpl;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.impl.ScopesPackageImpl;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.users.impl.UsersPackageImpl;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.visual.impl.VisualPackageImpl;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ConstraintTypes;
import org.openiaml.model.model.wires.ConstraintWire;
import org.openiaml.model.model.wires.ExtendsWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.ProvidesWire;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
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
	private EClass setWireEClass = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass newInstanceWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendsWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiresWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providesWireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum constraintTypesEEnum = null;

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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link WiresPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WiresPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) : ScopesPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) : UsersPackage.eINSTANCE);

		// Create package meta-data objects
		theWiresPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theScopesPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theUsersPackage.createPackageContents();

		// Initialize created meta-data
		theWiresPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theScopesPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theUsersPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWiresPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WiresPackage.eNS_URI, theWiresPackage);
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
	public EAttribute getRunInstanceWire_Priority() {
		return (EAttribute)runInstanceWireEClass.getEStructuralFeatures().get(0);
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
	public EClass getSetWire() {
		return setWireEClass;
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
	public EClass getConditionWire() {
		return conditionWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNewInstanceWire() {
		return newInstanceWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendsWire() {
		return extendsWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiresWire() {
		return requiresWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintWire() {
		return constraintWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintWire_Type() {
		return (EAttribute)constraintWireEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidesWire() {
		return providesWireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConstraintTypes() {
		return constraintTypesEEnum;
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
		createEAttribute(runInstanceWireEClass, RUN_INSTANCE_WIRE__PRIORITY);

		parameterWireEClass = createEClass(PARAMETER_WIRE);
		createEAttribute(parameterWireEClass, PARAMETER_WIRE__PARAMETER_NAME);

		setWireEClass = createEClass(SET_WIRE);

		navigateWireEClass = createEClass(NAVIGATE_WIRE);

		selectWireEClass = createEClass(SELECT_WIRE);
		createEAttribute(selectWireEClass, SELECT_WIRE__QUERY);
		createEAttribute(selectWireEClass, SELECT_WIRE__LIMIT);

		conditionWireEClass = createEClass(CONDITION_WIRE);

		newInstanceWireEClass = createEClass(NEW_INSTANCE_WIRE);

		extendsWireEClass = createEClass(EXTENDS_WIRE);

		requiresWireEClass = createEClass(REQUIRES_WIRE);

		constraintWireEClass = createEClass(CONSTRAINT_WIRE);
		createEAttribute(constraintWireEClass, CONSTRAINT_WIRE__TYPE);

		providesWireEClass = createEClass(PROVIDES_WIRE);

		// Create enums
		constraintTypesEEnum = createEEnum(CONSTRAINT_TYPES);
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
		compositeWireEClass.getESuperTypes().add(theModelPackage.getContainsConditions());
		syncWireEClass.getESuperTypes().add(this.getCompositeWire());
		syncWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		runInstanceWireEClass.getESuperTypes().add(this.getCompositeWire());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		parameterWireEClass.getESuperTypes().add(this.getSingleWire());
		parameterWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		setWireEClass.getESuperTypes().add(this.getCompositeWire());
		setWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		navigateWireEClass.getESuperTypes().add(this.getCompositeWire());
		navigateWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		selectWireEClass.getESuperTypes().add(this.getCompositeWire());
		selectWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		conditionWireEClass.getESuperTypes().add(this.getCompositeWire());
		conditionWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		newInstanceWireEClass.getESuperTypes().add(this.getCompositeWire());
		extendsWireEClass.getESuperTypes().add(this.getSingleWire());
		extendsWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		requiresWireEClass.getESuperTypes().add(this.getSingleWire());
		requiresWireEClass.getESuperTypes().add(theModelPackage.getWireEdgesSource());
		requiresWireEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		constraintWireEClass.getESuperTypes().add(this.getSingleWire());
		providesWireEClass.getESuperTypes().add(this.getSingleWire());

		// Initialize classes and features; add operations and parameters
		initEClass(singleWireEClass, SingleWire.class, "SingleWire", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeWireEClass, CompositeWire.class, "CompositeWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeWire_Children(), theModelPackage.getApplicationElement(), null, "children", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Properties(), theModelPackage.getApplicationElementProperty(), null, "properties", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Operations(), theModelPackage.getOperation(), null, "operations", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_EventTriggers(), theModelPackage.getEventTrigger(), null, "eventTriggers", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Parameters(), theModelPackage.getParameter(), null, "parameters", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Values(), theModelPackage.getStaticValue(), null, "values", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncWireEClass, SyncWire.class, "SyncWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runInstanceWireEClass, RunInstanceWire.class, "RunInstanceWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunInstanceWire_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, RunInstanceWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterWireEClass, ParameterWire.class, "ParameterWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterWire_ParameterName(), ecorePackage.getEString(), "parameterName", null, 0, 1, ParameterWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setWireEClass, SetWire.class, "SetWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigateWireEClass, NavigateWire.class, "NavigateWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectWireEClass, SelectWire.class, "SelectWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectWire_Query(), ecorePackage.getEString(), "query", null, 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSelectWire_Limit(), ecorePackage.getEInt(), "limit", "1", 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionWireEClass, ConditionWire.class, "ConditionWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(newInstanceWireEClass, NewInstanceWire.class, "NewInstanceWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(extendsWireEClass, ExtendsWire.class, "ExtendsWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(requiresWireEClass, RequiresWire.class, "RequiresWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constraintWireEClass, ConstraintWire.class, "ConstraintWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintWire_Type(), this.getConstraintTypes(), "type", null, 1, 1, ConstraintWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providesWireEClass, ProvidesWire.class, "ProvidesWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(constraintTypesEEnum, ConstraintTypes.class, "ConstraintTypes");
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.AND);
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.OR);
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.XOR);

		// Create annotations
		// http://openiaml.org/comment
		createCommentAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
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
			 "comment", "why do we have SingleWire at all? (following a design pattern?)",
			 "changed", "0.4.2 to abstract"
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
		  (getRunInstanceWire_Priority(), 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (getParameterWire_ParameterName(), 
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
		  (navigateWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (selectWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2",
			 "comment", "parameters can be provided by ParameterWires"
		   });			
		addAnnotation
		  (conditionWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (newInstanceWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.3"
		   });			
		addAnnotation
		  (extendsWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });			
		addAnnotation
		  (requiresWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });			
		addAnnotation
		  (constraintWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });			
		addAnnotation
		  (constraintTypesEEnum, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });		
		addAnnotation
		  (providesWireEClass, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });	
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";				
		addAnnotation
		  (syncWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "When a connected element changes, a SyncWire will update the other connected element."
		   });			
		addAnnotation
		  (runInstanceWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the execution of an {@link Operation}."
		   });			
		addAnnotation
		  (parameterWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Provides incoming values for {@link Parameter Parameters} of a {@link RunInstanceWire}."
		   });				
		addAnnotation
		  (setWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "When the source element changes, the target element will be updated (but not vice versa)."
		   });			
		addAnnotation
		  (navigateWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Executing this wire will navigate the user to the target {@link Page}."
		   });			
		addAnnotation
		  (selectWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Allows for the selection of particular {@link DomainObjectInstance instances} from a {@link DomainStore}."
		   });			
		addAnnotation
		  (conditionWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "When connected to a {@link RunInstanceWire}, the connected {@link Condition Conditions} will be evaluated before any execution takes place."
		   });			
		addAnnotation
		  (newInstanceWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Creates a new instance of a {@model DomainObjectInstance} or {@model DomainAttributeInstance} from its definition."
		   });			
		addAnnotation
		  (extendsWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the inheritance of all target elements by the source element."
		   });			
		addAnnotation
		  (requiresWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the requirements for a {@link LoginHandler}, such as {@link Role Roles} and {@link Permission Permissions}."
		   });			
		addAnnotation
		  (constraintWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Allows for the definition of complex {@link RequiresWire requirements}."
		   });				
		addAnnotation
		  (providesWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Indicates that the source {@link Role} provides the target {@link Permission}."
		   });
	}

} //WiresPackageImpl
