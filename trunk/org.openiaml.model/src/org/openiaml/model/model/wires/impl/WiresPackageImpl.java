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
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ConstraintEdgeDestination;
import org.openiaml.model.model.wires.ConstraintEdgesSource;
import org.openiaml.model.model.wires.ConstraintTypes;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;
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
	private EClass parameterEdgeEClass = null;

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
	private EClass conditionEdgeEClass = null;

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
	private EClass extendsEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiresEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providesEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendsEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendsEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEdgeDestinationEClass = null;

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
	public EClass getParameterEdge() {
		return parameterEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterEdge_ParameterName() {
		return (EAttribute)parameterEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterEdge_From() {
		return (EReference)parameterEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterEdge_To() {
		return (EReference)parameterEdgeEClass.getEStructuralFeatures().get(2);
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
	public EClass getConditionEdge() {
		return conditionEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionEdge_From() {
		return (EReference)conditionEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionEdge_To() {
		return (EReference)conditionEdgeEClass.getEStructuralFeatures().get(1);
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
	public EClass getExtendsEdge() {
		return extendsEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendsEdge_From() {
		return (EReference)extendsEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendsEdge_To() {
		return (EReference)extendsEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiresEdge() {
		return requiresEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiresEdge_From() {
		return (EReference)requiresEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiresEdge_To() {
		return (EReference)requiresEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintEdge() {
		return constraintEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintEdge_From() {
		return (EReference)constraintEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintEdge_To() {
		return (EReference)constraintEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintEdge_Type() {
		return (EAttribute)constraintEdgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidesEdge() {
		return providesEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidesEdge_From() {
		return (EReference)providesEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidesEdge_To() {
		return (EReference)providesEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterEdgesSource() {
		return parameterEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterEdgesSource_OutParameterEdges() {
		return (EReference)parameterEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterEdgeDestination() {
		return parameterEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterEdgeDestination_InParameterEdges() {
		return (EReference)parameterEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendsEdgesSource() {
		return extendsEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendsEdgesSource_OutExtendsEdges() {
		return (EReference)extendsEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendsEdgeDestination() {
		return extendsEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendsEdgeDestination_InExtendsEdges() {
		return (EReference)extendsEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintEdgesSource() {
		return constraintEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintEdgesSource_OutConstraintEdges() {
		return (EReference)constraintEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintEdgeDestination() {
		return constraintEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintEdgeDestination_InConstraintEdges() {
		return (EReference)constraintEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionEdgesSource() {
		return conditionEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionEdgesSource_OutConditionEdges() {
		return (EReference)conditionEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionEdgeDestination() {
		return conditionEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionEdgeDestination_InConditionEdges() {
		return (EReference)conditionEdgeDestinationEClass.getEStructuralFeatures().get(0);
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

		parameterEdgeEClass = createEClass(PARAMETER_EDGE);
		createEAttribute(parameterEdgeEClass, PARAMETER_EDGE__PARAMETER_NAME);
		createEReference(parameterEdgeEClass, PARAMETER_EDGE__FROM);
		createEReference(parameterEdgeEClass, PARAMETER_EDGE__TO);

		setWireEClass = createEClass(SET_WIRE);

		navigateWireEClass = createEClass(NAVIGATE_WIRE);

		selectWireEClass = createEClass(SELECT_WIRE);
		createEAttribute(selectWireEClass, SELECT_WIRE__QUERY);
		createEAttribute(selectWireEClass, SELECT_WIRE__LIMIT);

		conditionEdgeEClass = createEClass(CONDITION_EDGE);
		createEReference(conditionEdgeEClass, CONDITION_EDGE__FROM);
		createEReference(conditionEdgeEClass, CONDITION_EDGE__TO);

		newInstanceWireEClass = createEClass(NEW_INSTANCE_WIRE);

		extendsEdgeEClass = createEClass(EXTENDS_EDGE);
		createEReference(extendsEdgeEClass, EXTENDS_EDGE__FROM);
		createEReference(extendsEdgeEClass, EXTENDS_EDGE__TO);

		requiresEdgeEClass = createEClass(REQUIRES_EDGE);
		createEReference(requiresEdgeEClass, REQUIRES_EDGE__FROM);
		createEReference(requiresEdgeEClass, REQUIRES_EDGE__TO);

		constraintEdgeEClass = createEClass(CONSTRAINT_EDGE);
		createEReference(constraintEdgeEClass, CONSTRAINT_EDGE__FROM);
		createEReference(constraintEdgeEClass, CONSTRAINT_EDGE__TO);
		createEAttribute(constraintEdgeEClass, CONSTRAINT_EDGE__TYPE);

		providesEdgeEClass = createEClass(PROVIDES_EDGE);
		createEReference(providesEdgeEClass, PROVIDES_EDGE__FROM);
		createEReference(providesEdgeEClass, PROVIDES_EDGE__TO);

		parameterEdgesSourceEClass = createEClass(PARAMETER_EDGES_SOURCE);
		createEReference(parameterEdgesSourceEClass, PARAMETER_EDGES_SOURCE__OUT_PARAMETER_EDGES);

		parameterEdgeDestinationEClass = createEClass(PARAMETER_EDGE_DESTINATION);
		createEReference(parameterEdgeDestinationEClass, PARAMETER_EDGE_DESTINATION__IN_PARAMETER_EDGES);

		extendsEdgesSourceEClass = createEClass(EXTENDS_EDGES_SOURCE);
		createEReference(extendsEdgesSourceEClass, EXTENDS_EDGES_SOURCE__OUT_EXTENDS_EDGES);

		extendsEdgeDestinationEClass = createEClass(EXTENDS_EDGE_DESTINATION);
		createEReference(extendsEdgeDestinationEClass, EXTENDS_EDGE_DESTINATION__IN_EXTENDS_EDGES);

		constraintEdgesSourceEClass = createEClass(CONSTRAINT_EDGES_SOURCE);
		createEReference(constraintEdgesSourceEClass, CONSTRAINT_EDGES_SOURCE__OUT_CONSTRAINT_EDGES);

		constraintEdgeDestinationEClass = createEClass(CONSTRAINT_EDGE_DESTINATION);
		createEReference(constraintEdgeDestinationEClass, CONSTRAINT_EDGE_DESTINATION__IN_CONSTRAINT_EDGES);

		conditionEdgesSourceEClass = createEClass(CONDITION_EDGES_SOURCE);
		createEReference(conditionEdgesSourceEClass, CONDITION_EDGES_SOURCE__OUT_CONDITION_EDGES);

		conditionEdgeDestinationEClass = createEClass(CONDITION_EDGE_DESTINATION);
		createEReference(conditionEdgeDestinationEClass, CONDITION_EDGE_DESTINATION__IN_CONDITION_EDGES);

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
		UsersPackage theUsersPackage = (UsersPackage)EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		singleWireEClass.getESuperTypes().add(theModelPackage.getWire());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getWire());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		compositeWireEClass.getESuperTypes().add(theModelPackage.getContainsConditions());
		syncWireEClass.getESuperTypes().add(this.getCompositeWire());
		syncWireEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		syncWireEClass.getESuperTypes().add(this.getParameterEdgeDestination());
		syncWireEClass.getESuperTypes().add(this.getConditionEdgeDestination());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		runInstanceWireEClass.getESuperTypes().add(this.getParameterEdgeDestination());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		runInstanceWireEClass.getESuperTypes().add(this.getConditionEdgeDestination());
		runInstanceWireEClass.getESuperTypes().add(theModelPackage.getAction());
		parameterEdgeEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		parameterEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratedElement());
		setWireEClass.getESuperTypes().add(this.getCompositeWire());
		setWireEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		setWireEClass.getESuperTypes().add(this.getConditionEdgeDestination());
		navigateWireEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		navigateWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		navigateWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		navigateWireEClass.getESuperTypes().add(this.getConditionEdgeDestination());
		navigateWireEClass.getESuperTypes().add(theModelPackage.getAction());
		selectWireEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		selectWireEClass.getESuperTypes().add(this.getParameterEdgeDestination());
		selectWireEClass.getESuperTypes().add(this.getSingleWire());
		selectWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		selectWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		conditionEdgeEClass.getESuperTypes().add(this.getParameterEdgeDestination());
		conditionEdgeEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		conditionEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		newInstanceWireEClass.getESuperTypes().add(this.getSingleWire());
		newInstanceWireEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		newInstanceWireEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		extendsEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratedElement());
		extendsEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratesElements());
		requiresEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratedElement());
		requiresEdgeEClass.getESuperTypes().add(this.getConstraintEdgesSource());
		requiresEdgeEClass.getESuperTypes().add(this.getConstraintEdgeDestination());
		requiresEdgeEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		constraintEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratedElement());
		providesEdgeEClass.getESuperTypes().add(theModelPackage.getGeneratedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(singleWireEClass, SingleWire.class, "SingleWire", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeWireEClass, CompositeWire.class, "CompositeWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeWire_Children(), theModelPackage.getApplicationElement(), null, "children", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Properties(), theModelPackage.getProperty(), null, "properties", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Operations(), theModelPackage.getOperation(), null, "operations", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_EventTriggers(), theModelPackage.getEventTrigger(), null, "eventTriggers", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Parameters(), theModelPackage.getParameter(), null, "parameters", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeWire_Values(), theModelPackage.getStaticValue(), null, "values", null, 0, -1, CompositeWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncWireEClass, SyncWire.class, "SyncWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runInstanceWireEClass, RunInstanceWire.class, "RunInstanceWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunInstanceWire_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, RunInstanceWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEdgeEClass, ParameterEdge.class, "ParameterEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterEdge_ParameterName(), ecorePackage.getEString(), "parameterName", null, 0, 1, ParameterEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterEdge_From(), this.getParameterEdgesSource(), this.getParameterEdgesSource_OutParameterEdges(), "from", null, 1, 1, ParameterEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterEdge_To(), this.getParameterEdgeDestination(), this.getParameterEdgeDestination_InParameterEdges(), "to", null, 1, 1, ParameterEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setWireEClass, SetWire.class, "SetWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigateWireEClass, NavigateWire.class, "NavigateWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectWireEClass, SelectWire.class, "SelectWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectWire_Query(), ecorePackage.getEString(), "query", null, 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSelectWire_Limit(), ecorePackage.getEInt(), "limit", "1", 0, 1, SelectWire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEdgeEClass, ConditionEdge.class, "ConditionEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionEdge_From(), this.getConditionEdgesSource(), this.getConditionEdgesSource_OutConditionEdges(), "from", null, 1, 1, ConditionEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionEdge_To(), this.getConditionEdgeDestination(), this.getConditionEdgeDestination_InConditionEdges(), "to", null, 1, 1, ConditionEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(newInstanceWireEClass, NewInstanceWire.class, "NewInstanceWire", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(extendsEdgeEClass, ExtendsEdge.class, "ExtendsEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtendsEdge_From(), this.getExtendsEdgesSource(), this.getExtendsEdgesSource_OutExtendsEdges(), "from", null, 1, 1, ExtendsEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtendsEdge_To(), this.getExtendsEdgeDestination(), this.getExtendsEdgeDestination_InExtendsEdges(), "to", null, 1, 1, ExtendsEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiresEdgeEClass, RequiresEdge.class, "RequiresEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequiresEdge_From(), theUsersPackage.getRequiresEdgesSource(), theUsersPackage.getRequiresEdgesSource_OutRequiresEdges(), "from", null, 1, 1, RequiresEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRequiresEdge_To(), theUsersPackage.getRequiresEdgeDestination(), theUsersPackage.getRequiresEdgeDestination_InRequiresEdges(), "to", null, 1, 1, RequiresEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintEdgeEClass, ConstraintEdge.class, "ConstraintEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstraintEdge_From(), this.getConstraintEdgesSource(), this.getConstraintEdgesSource_OutConstraintEdges(), "from", null, 1, 1, ConstraintEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintEdge_To(), this.getConstraintEdgeDestination(), this.getConstraintEdgeDestination_InConstraintEdges(), "to", null, 1, 1, ConstraintEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintEdge_Type(), this.getConstraintTypes(), "type", null, 1, 1, ConstraintEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providesEdgeEClass, ProvidesEdge.class, "ProvidesEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidesEdge_From(), theUsersPackage.getProvidesEdgesSource(), theUsersPackage.getProvidesEdgesSource_OutProvidesEdges(), "from", null, 1, 1, ProvidesEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvidesEdge_To(), theUsersPackage.getProvidesEdgeDestination(), theUsersPackage.getProvidesEdgeDestination_InProvidesEdges(), "to", null, 1, 1, ProvidesEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEdgesSourceEClass, ParameterEdgesSource.class, "ParameterEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterEdgesSource_OutParameterEdges(), this.getParameterEdge(), this.getParameterEdge_From(), "outParameterEdges", null, 0, -1, ParameterEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEdgeDestinationEClass, ParameterEdgeDestination.class, "ParameterEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterEdgeDestination_InParameterEdges(), this.getParameterEdge(), this.getParameterEdge_To(), "inParameterEdges", null, 0, -1, ParameterEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendsEdgesSourceEClass, ExtendsEdgesSource.class, "ExtendsEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtendsEdgesSource_OutExtendsEdges(), this.getExtendsEdge(), this.getExtendsEdge_From(), "outExtendsEdges", null, 0, -1, ExtendsEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendsEdgeDestinationEClass, ExtendsEdgeDestination.class, "ExtendsEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtendsEdgeDestination_InExtendsEdges(), this.getExtendsEdge(), this.getExtendsEdge_To(), "inExtendsEdges", null, 0, -1, ExtendsEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintEdgesSourceEClass, ConstraintEdgesSource.class, "ConstraintEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstraintEdgesSource_OutConstraintEdges(), this.getConstraintEdge(), this.getConstraintEdge_From(), "outConstraintEdges", null, 0, -1, ConstraintEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintEdgeDestinationEClass, ConstraintEdgeDestination.class, "ConstraintEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstraintEdgeDestination_InConstraintEdges(), this.getConstraintEdge(), this.getConstraintEdge_To(), "inConstraintEdges", null, 0, -1, ConstraintEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEdgesSourceEClass, ConditionEdgesSource.class, "ConditionEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionEdgesSource_OutConditionEdges(), this.getConditionEdge(), this.getConditionEdge_From(), "outConditionEdges", null, 0, -1, ConditionEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEdgeDestinationEClass, ConditionEdgeDestination.class, "ConditionEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionEdgeDestination_InConditionEdges(), this.getConditionEdge(), this.getConditionEdge_To(), "inConditionEdges", null, 0, -1, ConditionEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(constraintTypesEEnum, ConstraintTypes.class, "ConstraintTypes");
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.AND);
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.OR);
		addEEnumLiteral(constraintTypesEEnum, ConstraintTypes.XOR);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
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
		  (parameterEdgeEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ParameterEdge"
		   });		
		addAnnotation
		  (getParameterEdge_ParameterName(), 
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
		  (conditionEdgeEClass, 
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
		  (extendsEdgeEClass, 
		   source, 
		   new String[] {
			 "added", "0.4",
			 "changed", "0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ExtendsEdge"
		   });			
		addAnnotation
		  (requiresEdgeEClass, 
		   source, 
		   new String[] {
			 "added", "0.4",
			 "changed", "0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to RequiresEdge"
		   });			
		addAnnotation
		  (constraintEdgeEClass, 
		   source, 
		   new String[] {
			 "added", "0.4",
			 "changed", "0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ConstraintEdge"
		   });			
		addAnnotation
		  (constraintTypesEEnum, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });		
		addAnnotation
		  (providesEdgeEClass, 
		   source, 
		   new String[] {
			 "added", "0.4",
			 "changed", "0.4.3 to no longer be a Wire; added \'from\' and \'to\' references; renamed to ProvidesEdge"
		   });				
		addAnnotation
		  (parameterEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (parameterEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (extendsEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (extendsEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (constraintEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (constraintEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (conditionEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (conditionEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
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
		  (singleWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "A {@model SingleWire} represents a single piece of runtime functionality that cannot be decomposed further. A {@model CompositeWire} may ultimately be composed of many of these."
		   });				
		addAnnotation
		  (compositeWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "A {@model CompositeWire} allows for complex functionality, such as {@model SingleWire}s, to be represented as a single relationship. The decomposition is provided by model completion."
		   });		
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
			 "documentation", "Connects a {@model EventTrigger} to a {@model Operation}, allowing it to be executed. May have incoming {@model ConditionEdge}s."
		   });			
		addAnnotation
		  (parameterEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Provides incoming values for {@model Parameter Parameters} of a {@model RunInstanceWire}."
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
			 "documentation", "When the {@model EventTrigger source event} executes, the user will be navigated to the target {@model Frame}."
		   });			
		addAnnotation
		  (selectWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Allows for the selection of particular {@model DomainObjectInstance instances} from a {@model DomainStore}."
		   });			
		addAnnotation
		  (conditionEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Allows the conditional execution of {@model ConditionEdgeDestination targets} such as {@model Wire wires}. Only if the {@model ConditionEdgesSource incoming condition} is true, will the execution be permitted."
		   });			
		addAnnotation
		  (newInstanceWireEClass, 
		   source, 
		   new String[] {
			 "documentation", "Creates a new instance of a {@model DomainObjectInstance} or {@model DomainAttributeInstance} from its definition."
		   });			
		addAnnotation
		  (extendsEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the inheritance of all target elements by the source element."
		   });			
		addAnnotation
		  (requiresEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the requirements for a {@model LoginHandler}, such as {@model Role Roles} and {@model Permission Permissions}."
		   });			
		addAnnotation
		  (constraintEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Allows for the definition of complex {@model RequiresEdge requirements}."
		   });				
		addAnnotation
		  (providesEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Indicates that the source {@model Role} provides the target {@model Permission}."
		   });		
		addAnnotation
		  (parameterEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model ParameterEdge}."
		   });			
		addAnnotation
		  (parameterEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model ParameterEdge}."
		   });			
		addAnnotation
		  (extendsEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model ExtendsEdge}."
		   });			
		addAnnotation
		  (extendsEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model ExtendsEdge}."
		   });			
		addAnnotation
		  (constraintEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model ConstraintEdge}."
		   });			
		addAnnotation
		  (constraintEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model ConstraintEdge}."
		   });			
		addAnnotation
		  (conditionEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model ConditionEdge}."
		   });			
		addAnnotation
		  (conditionEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model ConditionEdge}."
		   });	
	}

} //WiresPackageImpl
