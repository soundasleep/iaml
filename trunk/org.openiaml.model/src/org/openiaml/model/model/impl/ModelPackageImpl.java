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
import org.openiaml.model.model.Accessible;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ConditionalEdge;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsScopes;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.Editable;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.impl.ComponentsPackageImpl;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.impl.DomainPackageImpl;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.impl.OperationsPackageImpl;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.impl.ScopesPackageImpl;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.users.impl.UsersPackageImpl;
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
	private EClass wireEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireSourceEClass = null;

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
	private EClass activityNodeEClass = null;

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
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveOperationEClass = null;

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
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticValueEClass = null;

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
	private EClass dataFlowEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFlowEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFlowEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass temporaryVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicApplicationElementSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsWiresEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shouldntContainWiresEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generatedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generatesElementsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainObjectInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsConditionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainAttributeInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsScopesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass canBeSyncedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass accessibleEClass = null;

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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) : ScopesPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) : UsersPackage.eINSTANCE);

		// Create package meta-data objects
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theScopesPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theUsersPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theScopesPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theUsersPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
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
	public EClass getWire() {
		return wireEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWire_From() {
		return (EReference)wireEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWire_To() {
		return (EReference)wireEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireDestination() {
		return wireDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireDestination_InWires() {
		return (EReference)wireDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireSource() {
		return wireSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWireSource_OutWires() {
		return (EReference)wireSourceEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getDomainAttribute_PrimaryKey() {
		return (EAttribute)domainAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityNode() {
		return activityNodeEClass;
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
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveOperation() {
		return primitiveOperationEClass;
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
	public EReference getCompositeOperation_Nodes() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeOperation_DataEdges() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeOperation_ExecutionEdges() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeOperation_Variables() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeOperation_Values() {
		return (EReference)compositeOperationEClass.getEStructuralFeatures().get(4);
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
	public EReference getApplicationElement_Values() {
		return (EReference)applicationElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_DefaultValue() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticValue() {
		return staticValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticValue_Value() {
		return (EAttribute)staticValueEClass.getEStructuralFeatures().get(0);
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
	public EReference getVisibleThing_Children() {
		return (EReference)visibleThingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVisibleThing_Properties() {
		return (EReference)visibleThingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVisibleThing_Values() {
		return (EReference)visibleThingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVisibleThing_OnClick() {
		return (EReference)visibleThingEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getInternetApplication_RuntimeUrl() {
		return (EAttribute)internetApplicationEClass.getEStructuralFeatures().get(3);
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
	public EReference getDomainStore_Attributes() {
		return (EReference)domainStoreEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainStore_File() {
		return (EAttribute)domainStoreEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainStore_Type() {
		return (EAttribute)domainStoreEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFlowEdge() {
		return dataFlowEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFlowEdge_From() {
		return (EReference)dataFlowEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFlowEdge_To() {
		return (EReference)dataFlowEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFlowEdgeDestination() {
		return dataFlowEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFlowEdgeDestination_InFlows() {
		return (EReference)dataFlowEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFlowEdgesSource() {
		return dataFlowEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFlowEdgesSource_OutFlows() {
		return (EReference)dataFlowEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemporaryVariable() {
		return temporaryVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionEdge() {
		return executionEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEdge_From() {
		return (EReference)executionEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEdge_To() {
		return (EReference)executionEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionEdgeDestination() {
		return executionEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEdgeDestination_InExecutions() {
		return (EReference)executionEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionEdgesSource() {
		return executionEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEdgesSource_OutExecutions() {
		return (EReference)executionEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalEdge() {
		return conditionalEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicApplicationElementSet() {
		return dynamicApplicationElementSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicApplicationElementSet_Query() {
		return (EAttribute)dynamicApplicationElementSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainsWires() {
		return containsWiresEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_Wires() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_ParameterEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_ExtendsEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_RequiresEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_ProvidesEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_ConstraintEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsWires_ConditionEdges() {
		return (EReference)containsWiresEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShouldntContainWires() {
		return shouldntContainWiresEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneratedElement() {
		return generatedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneratedElement_GeneratedBy() {
		return (EReference)generatedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratedElement_IsGenerated() {
		return (EAttribute)generatedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratedElement_Id() {
		return (EAttribute)generatedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratedElement_GeneratedRule() {
		return (EAttribute)generatedElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneratesElements() {
		return generatesElementsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneratesElements_GeneratedElements() {
		return (EReference)generatesElementsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratesElements_Overridden() {
		return (EAttribute)generatesElementsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainObjectInstance() {
		return domainObjectInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainObjectInstance_Attributes() {
		return (EReference)domainObjectInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainObjectInstance_StrQuery() {
		return (EAttribute)domainObjectInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainObjectInstance_Autosave() {
		return (EAttribute)domainObjectInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScope() {
		return scopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_EntryGate() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_ExitGate() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_Properties() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_Values() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_Parameters() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_Elements() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScope_OnInit() {
		return (EReference)scopeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCondition() {
		return conditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeCondition() {
		return compositeConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_Nodes() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_DataEdges() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_ExecutionEdges() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_Variables() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_Values() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeCondition_Parameters() {
		return (EReference)compositeConditionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainsConditions() {
		return containsConditionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsConditions_Conditions() {
		return (EReference)containsConditionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainAttributeInstance() {
		return domainAttributeInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainAttributeInstance_Autosave() {
		return (EAttribute)domainAttributeInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryParameter() {
		return queryParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryParameter_DefaultValue() {
		return (EAttribute)queryParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainsScopes() {
		return containsScopesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainsScopes_Scopes() {
		return (EReference)containsScopesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCanBeSynced() {
		return canBeSyncedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditable() {
		return editableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditable_OnEdit() {
		return (EReference)editableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAccessible() {
		return accessibleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAccessible_OnAccess() {
		return (EReference)accessibleEClass.getEStructuralFeatures().get(0);
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

		wireEClass = createEClass(WIRE);
		createEReference(wireEClass, WIRE__FROM);
		createEReference(wireEClass, WIRE__TO);

		wireDestinationEClass = createEClass(WIRE_DESTINATION);
		createEReference(wireDestinationEClass, WIRE_DESTINATION__IN_WIRES);

		wireSourceEClass = createEClass(WIRE_SOURCE);
		createEReference(wireSourceEClass, WIRE_SOURCE__OUT_WIRES);

		eventTriggerEClass = createEClass(EVENT_TRIGGER);

		containsEventTriggersEClass = createEClass(CONTAINS_EVENT_TRIGGERS);
		createEReference(containsEventTriggersEClass, CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS);

		domainObjectEClass = createEClass(DOMAIN_OBJECT);
		createEReference(domainObjectEClass, DOMAIN_OBJECT__ATTRIBUTES);

		domainAttributeEClass = createEClass(DOMAIN_ATTRIBUTE);
		createEAttribute(domainAttributeEClass, DOMAIN_ATTRIBUTE__PRIMARY_KEY);

		activityNodeEClass = createEClass(ACTIVITY_NODE);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__PARAMETERS);

		parameterEClass = createEClass(PARAMETER);

		primitiveOperationEClass = createEClass(PRIMITIVE_OPERATION);

		compositeOperationEClass = createEClass(COMPOSITE_OPERATION);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__NODES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__DATA_EDGES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__EXECUTION_EDGES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__VARIABLES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__VALUES);

		containsOperationsEClass = createEClass(CONTAINS_OPERATIONS);
		createEReference(containsOperationsEClass, CONTAINS_OPERATIONS__OPERATIONS);

		applicationElementEClass = createEClass(APPLICATION_ELEMENT);
		createEReference(applicationElementEClass, APPLICATION_ELEMENT__PROPERTIES);
		createEReference(applicationElementEClass, APPLICATION_ELEMENT__VALUES);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__DEFAULT_VALUE);

		staticValueEClass = createEClass(STATIC_VALUE);
		createEAttribute(staticValueEClass, STATIC_VALUE__VALUE);

		visibleThingEClass = createEClass(VISIBLE_THING);
		createEReference(visibleThingEClass, VISIBLE_THING__CHILDREN);
		createEReference(visibleThingEClass, VISIBLE_THING__PROPERTIES);
		createEReference(visibleThingEClass, VISIBLE_THING__VALUES);
		createEReference(visibleThingEClass, VISIBLE_THING__ON_CLICK);

		internetApplicationEClass = createEClass(INTERNET_APPLICATION);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__PROPERTIES);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__CHILDREN);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__DOMAIN_STORES);
		createEAttribute(internetApplicationEClass, INTERNET_APPLICATION__RUNTIME_URL);

		domainStoreEClass = createEClass(DOMAIN_STORE);
		createEReference(domainStoreEClass, DOMAIN_STORE__CHILDREN);
		createEReference(domainStoreEClass, DOMAIN_STORE__PROPERTIES);
		createEReference(domainStoreEClass, DOMAIN_STORE__ATTRIBUTES);
		createEAttribute(domainStoreEClass, DOMAIN_STORE__FILE);
		createEAttribute(domainStoreEClass, DOMAIN_STORE__TYPE);

		dataFlowEdgeEClass = createEClass(DATA_FLOW_EDGE);
		createEReference(dataFlowEdgeEClass, DATA_FLOW_EDGE__FROM);
		createEReference(dataFlowEdgeEClass, DATA_FLOW_EDGE__TO);

		dataFlowEdgeDestinationEClass = createEClass(DATA_FLOW_EDGE_DESTINATION);
		createEReference(dataFlowEdgeDestinationEClass, DATA_FLOW_EDGE_DESTINATION__IN_FLOWS);

		dataFlowEdgesSourceEClass = createEClass(DATA_FLOW_EDGES_SOURCE);
		createEReference(dataFlowEdgesSourceEClass, DATA_FLOW_EDGES_SOURCE__OUT_FLOWS);

		temporaryVariableEClass = createEClass(TEMPORARY_VARIABLE);

		executionEdgeEClass = createEClass(EXECUTION_EDGE);
		createEReference(executionEdgeEClass, EXECUTION_EDGE__FROM);
		createEReference(executionEdgeEClass, EXECUTION_EDGE__TO);

		executionEdgeDestinationEClass = createEClass(EXECUTION_EDGE_DESTINATION);
		createEReference(executionEdgeDestinationEClass, EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS);

		executionEdgesSourceEClass = createEClass(EXECUTION_EDGES_SOURCE);
		createEReference(executionEdgesSourceEClass, EXECUTION_EDGES_SOURCE__OUT_EXECUTIONS);

		conditionalEdgeEClass = createEClass(CONDITIONAL_EDGE);

		dynamicApplicationElementSetEClass = createEClass(DYNAMIC_APPLICATION_ELEMENT_SET);
		createEAttribute(dynamicApplicationElementSetEClass, DYNAMIC_APPLICATION_ELEMENT_SET__QUERY);

		containsWiresEClass = createEClass(CONTAINS_WIRES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__WIRES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__PARAMETER_EDGES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__EXTENDS_EDGES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__REQUIRES_EDGES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__PROVIDES_EDGES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__CONSTRAINT_EDGES);
		createEReference(containsWiresEClass, CONTAINS_WIRES__CONDITION_EDGES);

		shouldntContainWiresEClass = createEClass(SHOULDNT_CONTAIN_WIRES);

		generatedElementEClass = createEClass(GENERATED_ELEMENT);
		createEReference(generatedElementEClass, GENERATED_ELEMENT__GENERATED_BY);
		createEAttribute(generatedElementEClass, GENERATED_ELEMENT__IS_GENERATED);
		createEAttribute(generatedElementEClass, GENERATED_ELEMENT__ID);
		createEAttribute(generatedElementEClass, GENERATED_ELEMENT__GENERATED_RULE);

		generatesElementsEClass = createEClass(GENERATES_ELEMENTS);
		createEReference(generatesElementsEClass, GENERATES_ELEMENTS__GENERATED_ELEMENTS);
		createEAttribute(generatesElementsEClass, GENERATES_ELEMENTS__OVERRIDDEN);

		domainObjectInstanceEClass = createEClass(DOMAIN_OBJECT_INSTANCE);
		createEReference(domainObjectInstanceEClass, DOMAIN_OBJECT_INSTANCE__ATTRIBUTES);
		createEAttribute(domainObjectInstanceEClass, DOMAIN_OBJECT_INSTANCE__STR_QUERY);
		createEAttribute(domainObjectInstanceEClass, DOMAIN_OBJECT_INSTANCE__AUTOSAVE);

		scopeEClass = createEClass(SCOPE);
		createEReference(scopeEClass, SCOPE__ENTRY_GATE);
		createEReference(scopeEClass, SCOPE__EXIT_GATE);
		createEReference(scopeEClass, SCOPE__PROPERTIES);
		createEReference(scopeEClass, SCOPE__VALUES);
		createEReference(scopeEClass, SCOPE__PARAMETERS);
		createEReference(scopeEClass, SCOPE__ELEMENTS);
		createEReference(scopeEClass, SCOPE__ON_INIT);

		conditionEClass = createEClass(CONDITION);

		compositeConditionEClass = createEClass(COMPOSITE_CONDITION);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__NODES);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__DATA_EDGES);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__EXECUTION_EDGES);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__VARIABLES);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__VALUES);
		createEReference(compositeConditionEClass, COMPOSITE_CONDITION__PARAMETERS);

		containsConditionsEClass = createEClass(CONTAINS_CONDITIONS);
		createEReference(containsConditionsEClass, CONTAINS_CONDITIONS__CONDITIONS);

		domainAttributeInstanceEClass = createEClass(DOMAIN_ATTRIBUTE_INSTANCE);
		createEAttribute(domainAttributeInstanceEClass, DOMAIN_ATTRIBUTE_INSTANCE__AUTOSAVE);

		queryParameterEClass = createEClass(QUERY_PARAMETER);
		createEAttribute(queryParameterEClass, QUERY_PARAMETER__DEFAULT_VALUE);

		containsScopesEClass = createEClass(CONTAINS_SCOPES);
		createEReference(containsScopesEClass, CONTAINS_SCOPES__SCOPES);

		canBeSyncedEClass = createEClass(CAN_BE_SYNCED);

		editableEClass = createEClass(EDITABLE);
		createEReference(editableEClass, EDITABLE__ON_EDIT);

		accessibleEClass = createEClass(ACCESSIBLE);
		createEReference(accessibleEClass, ACCESSIBLE__ON_ACCESS);
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
		ScopesPackage theScopesPackage = (ScopesPackage)EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI);
		DomainPackage theDomainPackage = (DomainPackage)EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI);
		UsersPackage theUsersPackage = (UsersPackage)EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theVisualPackage);
		getESubpackages().add(theWiresPackage);
		getESubpackages().add(theOperationsPackage);
		getESubpackages().add(theScopesPackage);
		getESubpackages().add(theComponentsPackage);
		getESubpackages().add(theDomainPackage);
		getESubpackages().add(theUsersPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		namedElementEClass.getESuperTypes().add(this.getGeneratedElement());
		wireEClass.getESuperTypes().add(this.getGeneratedElement());
		wireSourceEClass.getESuperTypes().add(this.getShouldntContainWires());
		eventTriggerEClass.getESuperTypes().add(this.getNamedElement());
		eventTriggerEClass.getESuperTypes().add(this.getWireSource());
		domainObjectEClass.getESuperTypes().add(this.getApplicationElement());
		domainObjectEClass.getESuperTypes().add(this.getContainsWires());
		domainObjectEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		domainObjectEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgesSource());
		domainObjectEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgeDestination());
		domainObjectEClass.getESuperTypes().add(this.getEditable());
		domainAttributeEClass.getESuperTypes().add(this.getApplicationElement());
		domainAttributeEClass.getESuperTypes().add(this.getContainsWires());
		domainAttributeEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		domainAttributeEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgesSource());
		domainAttributeEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgeDestination());
		domainAttributeEClass.getESuperTypes().add(this.getEditable());
		activityNodeEClass.getESuperTypes().add(this.getGeneratedElement());
		operationEClass.getESuperTypes().add(this.getWireDestination());
		operationEClass.getESuperTypes().add(this.getNamedElement());
		operationEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		operationEClass.getESuperTypes().add(this.getExecutionEdgeDestination());
		operationEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		parameterEClass.getESuperTypes().add(this.getNamedElement());
		parameterEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		primitiveOperationEClass.getESuperTypes().add(this.getOperation());
		primitiveOperationEClass.getESuperTypes().add(this.getExecutionEdgesSource());
		primitiveOperationEClass.getESuperTypes().add(this.getWireSource());
		compositeOperationEClass.getESuperTypes().add(this.getPrimitiveOperation());
		compositeOperationEClass.getESuperTypes().add(this.getContainsOperations());
		compositeOperationEClass.getESuperTypes().add(this.getContainsWires());
		compositeOperationEClass.getESuperTypes().add(this.getGeneratesElements());
		compositeOperationEClass.getESuperTypes().add(this.getContainsConditions());
		applicationElementEClass.getESuperTypes().add(this.getContainsOperations());
		applicationElementEClass.getESuperTypes().add(this.getNamedElement());
		applicationElementEClass.getESuperTypes().add(this.getWireSource());
		applicationElementEClass.getESuperTypes().add(this.getWireDestination());
		applicationElementEClass.getESuperTypes().add(this.getGeneratesElements());
		applicationElementEClass.getESuperTypes().add(this.getContainsConditions());
		applicationElementEClass.getESuperTypes().add(this.getCanBeSynced());
		propertyEClass.getESuperTypes().add(this.getNamedElement());
		propertyEClass.getESuperTypes().add(this.getWireSource());
		propertyEClass.getESuperTypes().add(this.getWireDestination());
		propertyEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		propertyEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		propertyEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		staticValueEClass.getESuperTypes().add(this.getNamedElement());
		staticValueEClass.getESuperTypes().add(this.getWireSource());
		staticValueEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		staticValueEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		visibleThingEClass.getESuperTypes().add(this.getContainsConditions());
		visibleThingEClass.getESuperTypes().add(this.getContainsOperations());
		visibleThingEClass.getESuperTypes().add(this.getContainsWires());
		visibleThingEClass.getESuperTypes().add(this.getWireSource());
		visibleThingEClass.getESuperTypes().add(this.getWireDestination());
		visibleThingEClass.getESuperTypes().add(this.getNamedElement());
		visibleThingEClass.getESuperTypes().add(this.getGeneratedElement());
		visibleThingEClass.getESuperTypes().add(this.getGeneratesElements());
		visibleThingEClass.getESuperTypes().add(this.getCanBeSynced());
		visibleThingEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		visibleThingEClass.getESuperTypes().add(this.getEditable());
		visibleThingEClass.getESuperTypes().add(this.getAccessible());
		internetApplicationEClass.getESuperTypes().add(this.getContainsOperations());
		internetApplicationEClass.getESuperTypes().add(this.getNamedElement());
		internetApplicationEClass.getESuperTypes().add(this.getContainsWires());
		internetApplicationEClass.getESuperTypes().add(this.getGeneratesElements());
		internetApplicationEClass.getESuperTypes().add(this.getContainsConditions());
		internetApplicationEClass.getESuperTypes().add(this.getContainsScopes());
		domainStoreEClass.getESuperTypes().add(this.getContainsOperations());
		domainStoreEClass.getESuperTypes().add(this.getNamedElement());
		domainStoreEClass.getESuperTypes().add(this.getContainsWires());
		domainStoreEClass.getESuperTypes().add(this.getContainsConditions());
		domainStoreEClass.getESuperTypes().add(this.getGeneratesElements());
		domainStoreEClass.getESuperTypes().add(this.getCanBeSynced());
		dataFlowEdgeEClass.getESuperTypes().add(this.getGeneratedElement());
		temporaryVariableEClass.getESuperTypes().add(this.getNamedElement());
		temporaryVariableEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		temporaryVariableEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		executionEdgeEClass.getESuperTypes().add(this.getGeneratedElement());
		executionEdgeEClass.getESuperTypes().add(this.getNamedElement());
		conditionalEdgeEClass.getESuperTypes().add(this.getExecutionEdge());
		conditionalEdgeEClass.getESuperTypes().add(this.getNamedElement());
		dynamicApplicationElementSetEClass.getESuperTypes().add(this.getApplicationElement());
		dynamicApplicationElementSetEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		shouldntContainWiresEClass.getESuperTypes().add(this.getContainsWires());
		domainObjectInstanceEClass.getESuperTypes().add(this.getApplicationElement());
		domainObjectInstanceEClass.getESuperTypes().add(this.getContainsWires());
		domainObjectInstanceEClass.getESuperTypes().add(theWiresPackage.getParameterEdgeDestination());
		domainObjectInstanceEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		domainObjectInstanceEClass.getESuperTypes().add(this.getEditable());
		scopeEClass.getESuperTypes().add(this.getGeneratesElements());
		scopeEClass.getESuperTypes().add(this.getContainsWires());
		scopeEClass.getESuperTypes().add(this.getContainsScopes());
		scopeEClass.getESuperTypes().add(this.getNamedElement());
		scopeEClass.getESuperTypes().add(this.getGeneratedElement());
		scopeEClass.getESuperTypes().add(this.getWireSource());
		scopeEClass.getESuperTypes().add(this.getWireDestination());
		scopeEClass.getESuperTypes().add(this.getContainsConditions());
		scopeEClass.getESuperTypes().add(this.getCanBeSynced());
		scopeEClass.getESuperTypes().add(this.getAccessible());
		conditionEClass.getESuperTypes().add(this.getWireSource());
		conditionEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		conditionEClass.getESuperTypes().add(this.getNamedElement());
		conditionEClass.getESuperTypes().add(theWiresPackage.getConditionEdgesSource());
		compositeConditionEClass.getESuperTypes().add(this.getContainsConditions());
		compositeConditionEClass.getESuperTypes().add(this.getCondition());
		compositeConditionEClass.getESuperTypes().add(this.getGeneratesElements());
		compositeConditionEClass.getESuperTypes().add(this.getContainsOperations());
		domainAttributeInstanceEClass.getESuperTypes().add(this.getApplicationElement());
		domainAttributeInstanceEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgesSource());
		domainAttributeInstanceEClass.getESuperTypes().add(theWiresPackage.getExtendsEdgeDestination());
		domainAttributeInstanceEClass.getESuperTypes().add(this.getEditable());
		queryParameterEClass.getESuperTypes().add(this.getNamedElement());
		queryParameterEClass.getESuperTypes().add(this.getWireSource());
		queryParameterEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		queryParameterEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		containsScopesEClass.getESuperTypes().add(this.getContainsOperations());
		canBeSyncedEClass.getESuperTypes().add(this.getNamedElement());
		canBeSyncedEClass.getESuperTypes().add(this.getGeneratedElement());
		canBeSyncedEClass.getESuperTypes().add(this.getGeneratesElements());
		canBeSyncedEClass.getESuperTypes().add(this.getContainsWires());
		canBeSyncedEClass.getESuperTypes().add(this.getContainsOperations());
		canBeSyncedEClass.getESuperTypes().add(this.getContainsConditions());
		canBeSyncedEClass.getESuperTypes().add(this.getWireSource());
		canBeSyncedEClass.getESuperTypes().add(this.getWireDestination());

		// Initialize classes and features; add operations and parameters
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEClass, Wire.class, "Wire", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWire_From(), this.getWireSource(), this.getWireSource_OutWires(), "from", null, 1, 1, Wire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWire_To(), this.getWireDestination(), this.getWireDestination_InWires(), "to", null, 1, 1, Wire.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireDestinationEClass, WireDestination.class, "WireDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireDestination_InWires(), this.getWire(), this.getWire_To(), "inWires", null, 0, -1, WireDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireSourceEClass, WireSource.class, "WireSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireSource_OutWires(), this.getWire(), this.getWire_From(), "outWires", null, 0, -1, WireSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTriggerEClass, EventTrigger.class, "EventTrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containsEventTriggersEClass, ContainsEventTriggers.class, "ContainsEventTriggers", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsEventTriggers_EventTriggers(), this.getEventTrigger(), null, "eventTriggers", null, 0, -1, ContainsEventTriggers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainObjectEClass, DomainObject.class, "DomainObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainObject_Attributes(), this.getDomainAttribute(), null, "attributes", null, 0, -1, DomainObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainAttributeEClass, DomainAttribute.class, "DomainAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDomainAttribute_PrimaryKey(), ecorePackage.getEBoolean(), "primaryKey", "false", 0, 1, DomainAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityNodeEClass, ActivityNode.class, "ActivityNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveOperationEClass, PrimitiveOperation.class, "PrimitiveOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeOperationEClass, CompositeOperation.class, "CompositeOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeOperation_Nodes(), this.getActivityNode(), null, "nodes", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_DataEdges(), this.getDataFlowEdge(), null, "dataEdges", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_ExecutionEdges(), this.getExecutionEdge(), null, "executionEdges", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_Variables(), this.getTemporaryVariable(), null, "variables", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_Values(), this.getStaticValue(), null, "values", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containsOperationsEClass, ContainsOperations.class, "ContainsOperations", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsOperations_Operations(), this.getOperation(), null, "operations", null, 0, -1, ContainsOperations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementEClass, ApplicationElement.class, "ApplicationElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElement_Properties(), this.getProperty(), null, "properties", null, 0, -1, ApplicationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationElement_Values(), this.getStaticValue(), null, "values", null, 0, -1, ApplicationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(staticValueEClass, StaticValue.class, "StaticValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStaticValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, StaticValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(visibleThingEClass, VisibleThing.class, "VisibleThing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVisibleThing_Children(), this.getVisibleThing(), null, "children", null, 0, -1, VisibleThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVisibleThing_Properties(), this.getProperty(), null, "properties", null, 0, -1, VisibleThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVisibleThing_Values(), this.getStaticValue(), null, "values", null, 0, -1, VisibleThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVisibleThing_OnClick(), this.getEventTrigger(), null, "onClick", null, 0, 1, VisibleThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(internetApplicationEClass, InternetApplication.class, "InternetApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternetApplication_Properties(), this.getProperty(), null, "properties", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_Children(), this.getApplicationElement(), null, "children", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_DomainStores(), this.getDomainStore(), null, "domainStores", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInternetApplication_RuntimeUrl(), ecorePackage.getEString(), "runtimeUrl", "http://localhost:8080/output/", 0, 1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainStoreEClass, DomainStore.class, "DomainStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainStore_Children(), this.getDomainObject(), null, "children", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainStore_Properties(), this.getProperty(), null, "properties", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainStore_Attributes(), this.getDomainAttribute(), null, "attributes", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainStore_File(), theDomainPackage.getFileReference(), "file", null, 1, 1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainStore_Type(), theDomainPackage.getDomainStoreTypes(), "type", null, 1, 1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFlowEdgeEClass, DataFlowEdge.class, "DataFlowEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataFlowEdge_From(), this.getDataFlowEdgesSource(), this.getDataFlowEdgesSource_OutFlows(), "from", null, 1, 1, DataFlowEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataFlowEdge_To(), this.getDataFlowEdgeDestination(), this.getDataFlowEdgeDestination_InFlows(), "to", null, 1, 1, DataFlowEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFlowEdgeDestinationEClass, DataFlowEdgeDestination.class, "DataFlowEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataFlowEdgeDestination_InFlows(), this.getDataFlowEdge(), this.getDataFlowEdge_To(), "inFlows", null, 0, -1, DataFlowEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFlowEdgesSourceEClass, DataFlowEdgesSource.class, "DataFlowEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataFlowEdgesSource_OutFlows(), this.getDataFlowEdge(), this.getDataFlowEdge_From(), "outFlows", null, 0, -1, DataFlowEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(temporaryVariableEClass, TemporaryVariable.class, "TemporaryVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(executionEdgeEClass, ExecutionEdge.class, "ExecutionEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionEdge_From(), this.getExecutionEdgesSource(), this.getExecutionEdgesSource_OutExecutions(), "from", null, 1, 1, ExecutionEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionEdge_To(), this.getExecutionEdgeDestination(), this.getExecutionEdgeDestination_InExecutions(), "to", null, 1, 1, ExecutionEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionEdgeDestinationEClass, ExecutionEdgeDestination.class, "ExecutionEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionEdgeDestination_InExecutions(), this.getExecutionEdge(), this.getExecutionEdge_To(), "inExecutions", null, 0, -1, ExecutionEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionEdgesSourceEClass, ExecutionEdgesSource.class, "ExecutionEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionEdgesSource_OutExecutions(), this.getExecutionEdge(), this.getExecutionEdge_From(), "outExecutions", null, 0, -1, ExecutionEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionalEdgeEClass, ConditionalEdge.class, "ConditionalEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dynamicApplicationElementSetEClass, DynamicApplicationElementSet.class, "DynamicApplicationElementSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDynamicApplicationElementSet_Query(), ecorePackage.getEString(), "query", null, 0, 1, DynamicApplicationElementSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containsWiresEClass, ContainsWires.class, "ContainsWires", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsWires_Wires(), this.getWire(), null, "wires", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_ParameterEdges(), theWiresPackage.getParameterEdge(), null, "parameterEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_ExtendsEdges(), theWiresPackage.getExtendsEdge(), null, "extendsEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_RequiresEdges(), theWiresPackage.getRequiresEdge(), null, "requiresEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_ProvidesEdges(), theWiresPackage.getProvidesEdge(), null, "providesEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_ConstraintEdges(), theWiresPackage.getConstraintEdge(), null, "constraintEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainsWires_ConditionEdges(), theWiresPackage.getConditionEdge(), null, "conditionEdges", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(shouldntContainWiresEClass, ShouldntContainWires.class, "ShouldntContainWires", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(generatedElementEClass, GeneratedElement.class, "GeneratedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratedElement_GeneratedBy(), this.getGeneratesElements(), this.getGeneratesElements_GeneratedElements(), "generatedBy", null, 0, -1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratedElement_IsGenerated(), ecorePackage.getEBoolean(), "isGenerated", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratedElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratedElement_GeneratedRule(), ecorePackage.getEString(), "generatedRule", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatesElementsEClass, GeneratesElements.class, "GeneratesElements", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratesElements_GeneratedElements(), this.getGeneratedElement(), this.getGeneratedElement_GeneratedBy(), "generatedElements", null, 0, -1, GeneratesElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratesElements_Overridden(), ecorePackage.getEBoolean(), "overridden", null, 0, 1, GeneratesElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainObjectInstanceEClass, DomainObjectInstance.class, "DomainObjectInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainObjectInstance_Attributes(), this.getDomainAttributeInstance(), null, "attributes", null, 0, -1, DomainObjectInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainObjectInstance_StrQuery(), ecorePackage.getEString(), "strQuery", null, 0, 1, DomainObjectInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainObjectInstance_Autosave(), ecorePackage.getEBoolean(), "autosave", "false", 0, 1, DomainObjectInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeEClass, Scope.class, "Scope", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScope_EntryGate(), theComponentsPackage.getEntryGate(), null, "entryGate", null, 0, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_ExitGate(), theComponentsPackage.getExitGate(), null, "exitGate", null, 0, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_Properties(), this.getProperty(), null, "properties", null, 0, -1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_Values(), this.getStaticValue(), null, "values", null, 0, -1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_Parameters(), this.getQueryParameter(), null, "parameters", null, 0, -1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_Elements(), this.getApplicationElement(), null, "elements", null, 0, -1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScope_OnInit(), this.getEventTrigger(), null, "onInit", null, 0, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEClass, Condition.class, "Condition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeConditionEClass, CompositeCondition.class, "CompositeCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeCondition_Nodes(), this.getActivityNode(), null, "nodes", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeCondition_DataEdges(), this.getDataFlowEdge(), null, "dataEdges", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeCondition_ExecutionEdges(), this.getExecutionEdge(), null, "executionEdges", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeCondition_Variables(), this.getTemporaryVariable(), null, "variables", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeCondition_Values(), this.getStaticValue(), null, "values", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeCondition_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, CompositeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containsConditionsEClass, ContainsConditions.class, "ContainsConditions", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsConditions_Conditions(), this.getCondition(), null, "conditions", null, 0, -1, ContainsConditions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainAttributeInstanceEClass, DomainAttributeInstance.class, "DomainAttributeInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDomainAttributeInstance_Autosave(), ecorePackage.getEBoolean(), "autosave", "false", 0, 1, DomainAttributeInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(queryParameterEClass, QueryParameter.class, "QueryParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQueryParameter_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, QueryParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containsScopesEClass, ContainsScopes.class, "ContainsScopes", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsScopes_Scopes(), this.getScope(), null, "scopes", null, 0, -1, ContainsScopes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(canBeSyncedEClass, CanBeSynced.class, "CanBeSynced", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(editableEClass, Editable.class, "Editable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditable_OnEdit(), this.getEventTrigger(), null, "onEdit", null, 0, 1, Editable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(accessibleEClass, Accessible.class, "Accessible", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAccessible_OnAccess(), this.getEventTrigger(), null, "onAccess", null, 0, 1, Accessible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

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
		  (wireEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4.2 to abstract"
		   });		
		addAnnotation
		  (wireSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "temporarily contains wires until GMF bug is fixed "
		   });			
		addAnnotation
		  (domainObjectEClass, 
		   source, 
		   new String[] {
			 "changed", "0.2 to extend the abstract counterpart\r\n0.3 to remove the abstract extension, and added \"type\" attribute"
		   });			
		addAnnotation
		  (domainAttributeEClass, 
		   source, 
		   new String[] {
			 "changed", "0.2 to extend the abstract counterpart\r\n0.3 to remove the abstract extension, and added \"type\" attribute"
		   });			
		addAnnotation
		  (getDomainAttribute_PrimaryKey(), 
		   source, 
		   new String[] {
			 "comment", "added in 0.4"
		   });		
		addAnnotation
		  (activityNodeEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4.2 to abstract"
		   });				
		addAnnotation
		  (primitiveOperationEClass, 
		   source, 
		   new String[] {
			 "comment", "renamed from ChainedOperation -> PrimitiveOperation in 0.4"
		   });			
		addAnnotation
		  (compositeOperationEClass, 
		   source, 
		   new String[] {
			 "changed", "0.2 added Scope supertype\r\n0.4 removed \'_shouldnt_properties\'\r\n0.4.2 removed Scope supertype"
		   });			
		addAnnotation
		  (applicationElementEClass, 
		   source, 
		   new String[] {
			 "editor", "org.openiaml.model.diagram.element",
			 "comment", "Scope supertype added in 0.2"
		   });				
		addAnnotation
		  (getProperty_DefaultValue(), 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (visibleThingEClass, 
		   source, 
		   new String[] {
			 "comment", "this used to mean nothing; now anything that extends VisibleThing (which unforuntately needs to be concrete) has an editor",
			 "editor", "org.openiaml.model.diagram.visual",
			 "changed", "0.4.2 to no longer extend ApplicationElementContainer\r\n0.4.2 extends ContainsConditions\r\n0.4.2 extends ContainsOperatons\r\n0.4.2 extends ContainsEventTriggers\r\n0.4.2 extends ContainsWires\r\n0.4.2 extends WireEdgesSource\r\n0.4.2 extends WireEdgeDestination\r\n0.4.2 removed \'sessions\' containment\r\n0.4.4 no longer ContainsEventTriggers; events inserted manually"
		   });			
		addAnnotation
		  (getVisibleThing_Children(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });		
		addAnnotation
		  (getVisibleThing_Properties(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });		
		addAnnotation
		  (getVisibleThing_Values(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });		
		addAnnotation
		  (getVisibleThing_OnClick(), 
		   source, 
		   new String[] {
			 "changed", "added in 0.4.4"
		   });		
		addAnnotation
		  (internetApplicationEClass, 
		   source, 
		   new String[] {
			 "comment", "we cannot have InternetApplications inside of other ElementContainers, thus we don\'t define it as an ApplicationElement",
			 "comment2", "but why can we have it as an ActivityEdgeSource? I don\'t think there are any examples of InternetApplication wire --> something else; all the wires are from objects INSIDE the IA",
			 "editor", "org.openiaml.model.diagram",
			 "changed", "0.4.2 no longer a Scope\r\n0.4.2 removed \'sessions\' containment"
		   });			
		addAnnotation
		  (getInternetApplication_DomainStores(), 
		   source, 
		   new String[] {
			 "comment", "not sure if this is necessary anymore... because now objects are stored as part of their scopes",
			 "changed", "0.2 to extend the abstract counterpart"
		   });		
		addAnnotation
		  (domainStoreEClass, 
		   source, 
		   new String[] {
			 "editor", "org.openiaml.model.diagram.domain_store",
			 "changed", "0.2 to extend the abstract counterpart\r\n0.3 to remove the abstract extension, and added \"type\" attribute\r\n0.4.2 removed \'views\' reference"
		   });			
		addAnnotation
		  (getDomainStore_Attributes(), 
		   source, 
		   new String[] {
			 "added", "0.3",
			 "reason", "why can a data store only contain tables? why can\'t they also contain single values?"
		   });		
		addAnnotation
		  (getDomainStore_File(), 
		   source, 
		   new String[] {
			 "added", "0.3",
			 "comment", "originally from FileDomainStore"
		   });		
		addAnnotation
		  (getDomainStore_Type(), 
		   source, 
		   new String[] {
			 "added", "0.3"
		   });					
		addAnnotation
		  (executionEdgeEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4.1 to add NamedElement supertype"
		   });		
		addAnnotation
		  (containsWiresEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4.3 added \'parameterEdges\' containment reference"
		   });		
		addAnnotation
		  (shouldntContainWiresEClass, 
		   source, 
		   new String[] {
			 "comment", "classes that extend this SHOULDN\'T contain wires, but GMF\'s limitations require it (see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=249717)"
		   });			
		addAnnotation
		  (getGeneratedElement_GeneratedBy(), 
		   source, 
		   new String[] {
			 "changed", "0.4 from 0..1 to 0..*"
		   });		
		addAnnotation
		  (getGeneratedElement_GeneratedRule(), 
		   source, 
		   new String[] {
			 "comment", "added in 0.2. used to register the source of the rule that created this element"
		   });		
		addAnnotation
		  (domainObjectInstanceEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (getDomainObjectInstance_Attributes(), 
		   source, 
		   new String[] {
			 "changed", "0.3: changed from DomainAttribute to DomainAttributeInstance"
		   });		
		addAnnotation
		  (getDomainObjectInstance_Autosave(), 
		   source, 
		   new String[] {
			 "added", "0.3",
			 "changed", "0.4.3 to default \'false\' instead of \'true\'"
		   });		
		addAnnotation
		  (scopeEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4: \'domainObjects\', \'domainViews\', \'domainInstances\' references removed\r\n0.4.2: renamed from \'Scope\' to \'AbstractScope\'; added \'gate\' reference"
		   });		
		addAnnotation
		  (getScope_Properties(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });		
		addAnnotation
		  (getScope_Values(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });		
		addAnnotation
		  (getScope_Parameters(), 
		   source, 
		   new String[] {
			 "added", "0.4",
			 "about", "ideally this might only be placed in Page and Session, but allowing it within VisibleThing allows us to (1) reuse our visual editor, and (2) increases modularity of components perhaps?"
		   });		
		addAnnotation
		  (getScope_OnInit(), 
		   source, 
		   new String[] {
			 "changed", "added in 0.4.4"
		   });		
		addAnnotation
		  (conditionEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.2"
		   });			
		addAnnotation
		  (compositeConditionEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.2\r\n_shouldnt_properties removed in 0.4\r\nContainsOperations added in 0.4.1"
		   });			
		addAnnotation
		  (containsConditionsEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.2"
		   });		
		addAnnotation
		  (domainAttributeInstanceEClass, 
		   source, 
		   new String[] {
			 "added", "0.3"
		   });			
		addAnnotation
		  (getDomainAttributeInstance_Autosave(), 
		   source, 
		   new String[] {
			 "added", "0.3",
			 "changed", "0.4.3 to default \'false\' instead of \'true\'"
		   });		
		addAnnotation
		  (queryParameterEClass, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });			
		addAnnotation
		  (containsScopesEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.2"
		   });		
		addAnnotation
		  (getEditable_OnEdit(), 
		   source, 
		   new String[] {
			 "changed", "added in 0.4.4"
		   });		
		addAnnotation
		  (getAccessible_OnAccess(), 
		   source, 
		   new String[] {
			 "changed", "added in 0.4.4"
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
		  (namedElementEClass, 
		   source, 
		   new String[] {
			 "documentation", "An element with a name."
		   });		
		addAnnotation
		  (wireEClass, 
		   source, 
		   new String[] {
			 "documentation", "A {@mode Wire} represents some piece of runtime functionality, usually in terms of the {@model EventTrigger Event}-{@model Condition}-{@model Operation Action} paradigm. These are expressed in {@model SingleWire}s."
		   });				
		addAnnotation
		  (eventTriggerEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the source of events."
		   });			
		addAnnotation
		  (domainObjectEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a type of {@model DomainObjectInstance}."
		   });			
		addAnnotation
		  (domainAttributeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a single attribute contained by a {@model DomainObject}."
		   });				
		addAnnotation
		  (operationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents some type of executable step, which may contain logic flows, data flows or other operations."
		   });		
		addAnnotation
		  (parameterEClass, 
		   source, 
		   new String[] {
			 "documentation", "Incoming values to an {@model Operation operation} executed by a {@model RunInstanceWire} can be accessed via Parameters."
		   });			
		addAnnotation
		  (primitiveOperationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Fundamental building blocks of application functionality. Their intent is based on their name."
		   });			
		addAnnotation
		  (compositeOperationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Operations which are composed of logic flows, data flows, and other operations."
		   });			
		addAnnotation
		  (applicationElementEClass, 
		   source, 
		   new String[] {
			 "documentation", "An abstract type representing model elements that {@model ContainsConditions}, {@model ContainsOperations} and {@model ContainsProperties}. This is used primarily to reduce the number of diagram editors in the IAML environment."
		   });		
		addAnnotation
		  (propertyEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a single value, accessible and modifiable at runtime."
		   });			
		addAnnotation
		  (staticValueEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a single value, which is accessible at runtime but can never change."
		   });			
		addAnnotation
		  (visibleThingEClass, 
		   source, 
		   new String[] {
			 "documentation", "An abstract type representing application elements which have some sort of visible representation."
		   });							
		addAnnotation
		  (internetApplicationEClass, 
		   source, 
		   new String[] {
			 "documentation", "The top-level type of a model instance, representing all of the information required to define an Internet application."
		   });				
		addAnnotation
		  (domainStoreEClass, 
		   source, 
		   new String[] {
			 "documentation", "Stores many instances of {@model DomainObject DomainObjects} in some storage device."
		   });					
		addAnnotation
		  (dataFlowEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the flow of data from a {@model DataFlowEdgesSource data source} to a {@model DataFlowEdgeDestination data destination}."
		   });		
		addAnnotation
		  (temporaryVariableEClass, 
		   source, 
		   new String[] {
			 "documentation", "Temporary storage of values contained within an {@model Operation} which will be lost once the operation is completed."
		   });		
		addAnnotation
		  (executionEdgeEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents the execution flow within an {@model Operation}."
		   });					
		addAnnotation
		  (generatedElementEClass, 
		   source, 
		   new String[] {
			 "documentation", "A GeneratedElement may have been created manually, or initialised through model completion {@model GeneratesElements by another element}."
		   });					
		addAnnotation
		  (domainObjectInstanceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents an instance of a {@model DomainObject}."
		   });										
		addAnnotation
		  (conditionEClass, 
		   source, 
		   new String[] {
			 "documentation", "A Condition allows for conditional execution of {@model WireEdge wires} when {@model ConditionWire connected}."
		   });			
		addAnnotation
		  (compositeConditionEClass, 
		   source, 
		   new String[] {
			 "documentation", "A custom condition, composed in the same way as {@model CompositeOperation operations}."
		   });				
		addAnnotation
		  (domainAttributeInstanceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents an instance of a {@model DomainAttribute}, which is likely contained within a {@model DomainObjectInstance}."
		   });				
		addAnnotation
		  (queryParameterEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a query parameter from the browser\'s URI string."
		   });			
	}

} //ModelPackageImpl
