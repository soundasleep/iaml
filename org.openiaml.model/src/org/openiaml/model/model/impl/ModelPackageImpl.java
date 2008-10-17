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
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ConditionalEdge;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
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
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.SingleOperation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;
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
	public EReference getWireEdgesSource_OutEdges() {
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
	public EReference getCompositeOperation__shouldnt_parameters() {
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
		createEReference(wireEdgesSourceEClass, WIRE_EDGES_SOURCE__OUT_EDGES);

		eventTriggerEClass = createEClass(EVENT_TRIGGER);

		containsEventTriggersEClass = createEClass(CONTAINS_EVENT_TRIGGERS);
		createEReference(containsEventTriggersEClass, CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS);

		domainObjectEClass = createEClass(DOMAIN_OBJECT);
		createEReference(domainObjectEClass, DOMAIN_OBJECT__ATTRIBUTES);

		domainAttributeEClass = createEClass(DOMAIN_ATTRIBUTE);

		activityNodeEClass = createEClass(ACTIVITY_NODE);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__PARAMETERS);

		parameterEClass = createEClass(PARAMETER);

		singleOperationEClass = createEClass(SINGLE_OPERATION);

		chainedOperationEClass = createEClass(CHAINED_OPERATION);

		compositeOperationEClass = createEClass(COMPOSITE_OPERATION);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__NODES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__DATA_EDGES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__EXECUTION_EDGES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__VARIABLES);
		createEReference(compositeOperationEClass, COMPOSITE_OPERATION__SHOULDNT_PARAMETERS);

		containsOperationsEClass = createEClass(CONTAINS_OPERATIONS);
		createEReference(containsOperationsEClass, CONTAINS_OPERATIONS__OPERATIONS);

		applicationElementEClass = createEClass(APPLICATION_ELEMENT);
		createEReference(applicationElementEClass, APPLICATION_ELEMENT__PROPERTIES);
		createEReference(applicationElementEClass, APPLICATION_ELEMENT__VALUES);

		applicationElementContainerEClass = createEClass(APPLICATION_ELEMENT_CONTAINER);
		createEReference(applicationElementContainerEClass, APPLICATION_ELEMENT_CONTAINER__CHILDREN);

		applicationElementPropertyEClass = createEClass(APPLICATION_ELEMENT_PROPERTY);

		staticValueEClass = createEClass(STATIC_VALUE);
		createEAttribute(staticValueEClass, STATIC_VALUE__VALUE);

		visibleThingEClass = createEClass(VISIBLE_THING);

		internetApplicationEClass = createEClass(INTERNET_APPLICATION);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__PROPERTIES);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__CHILDREN);
		createEReference(internetApplicationEClass, INTERNET_APPLICATION__DOMAIN_STORES);

		domainStoreEClass = createEClass(DOMAIN_STORE);
		createEReference(domainStoreEClass, DOMAIN_STORE__CHILDREN);
		createEReference(domainStoreEClass, DOMAIN_STORE__PROPERTIES);

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

		shouldntContainWiresEClass = createEClass(SHOULDNT_CONTAIN_WIRES);

		generatedElementEClass = createEClass(GENERATED_ELEMENT);
		createEReference(generatedElementEClass, GENERATED_ELEMENT__GENERATED_BY);
		createEAttribute(generatedElementEClass, GENERATED_ELEMENT__IS_GENERATED);
		createEAttribute(generatedElementEClass, GENERATED_ELEMENT__ID);

		generatesElementsEClass = createEClass(GENERATES_ELEMENTS);
		createEReference(generatesElementsEClass, GENERATES_ELEMENTS__GENERATED_ELEMENTS);
		createEAttribute(generatesElementsEClass, GENERATES_ELEMENTS__OVERRIDDEN);
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
		namedElementEClass.getESuperTypes().add(this.getGeneratedElement());
		wireEdgeEClass.getESuperTypes().add(this.getGeneratedElement());
		wireEdgesSourceEClass.getESuperTypes().add(this.getShouldntContainWires());
		eventTriggerEClass.getESuperTypes().add(this.getNamedElement());
		eventTriggerEClass.getESuperTypes().add(this.getWireEdgesSource());
		domainObjectEClass.getESuperTypes().add(this.getApplicationElement());
		domainObjectEClass.getESuperTypes().add(this.getContainsWires());
		domainAttributeEClass.getESuperTypes().add(this.getApplicationElement());
		activityNodeEClass.getESuperTypes().add(this.getGeneratedElement());
		operationEClass.getESuperTypes().add(this.getWireEdgeDestination());
		operationEClass.getESuperTypes().add(this.getNamedElement());
		operationEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		operationEClass.getESuperTypes().add(this.getExecutionEdgeDestination());
		operationEClass.getESuperTypes().add(this.getActivityNode());
		operationEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		parameterEClass.getESuperTypes().add(this.getNamedElement());
		parameterEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		parameterEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		singleOperationEClass.getESuperTypes().add(this.getOperation());
		chainedOperationEClass.getESuperTypes().add(this.getOperation());
		chainedOperationEClass.getESuperTypes().add(this.getExecutionEdgesSource());
		compositeOperationEClass.getESuperTypes().add(this.getChainedOperation());
		compositeOperationEClass.getESuperTypes().add(this.getContainsOperations());
		compositeOperationEClass.getESuperTypes().add(this.getContainsWires());
		applicationElementEClass.getESuperTypes().add(this.getContainsOperations());
		applicationElementEClass.getESuperTypes().add(this.getNamedElement());
		applicationElementEClass.getESuperTypes().add(this.getContainsEventTriggers());
		applicationElementEClass.getESuperTypes().add(this.getWireEdgesSource());
		applicationElementEClass.getESuperTypes().add(this.getWireEdgeDestination());
		applicationElementEClass.getESuperTypes().add(this.getGeneratesElements());
		applicationElementContainerEClass.getESuperTypes().add(this.getApplicationElement());
		applicationElementContainerEClass.getESuperTypes().add(this.getContainsWires());
		applicationElementPropertyEClass.getESuperTypes().add(this.getNamedElement());
		applicationElementPropertyEClass.getESuperTypes().add(this.getWireEdgesSource());
		applicationElementPropertyEClass.getESuperTypes().add(this.getWireEdgeDestination());
		applicationElementPropertyEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		applicationElementPropertyEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		staticValueEClass.getESuperTypes().add(this.getNamedElement());
		staticValueEClass.getESuperTypes().add(this.getWireEdgesSource());
		staticValueEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		visibleThingEClass.getESuperTypes().add(this.getApplicationElementContainer());
		internetApplicationEClass.getESuperTypes().add(this.getContainsOperations());
		internetApplicationEClass.getESuperTypes().add(this.getContainsEventTriggers());
		internetApplicationEClass.getESuperTypes().add(this.getNamedElement());
		internetApplicationEClass.getESuperTypes().add(this.getContainsWires());
		internetApplicationEClass.getESuperTypes().add(this.getGeneratesElements());
		domainStoreEClass.getESuperTypes().add(this.getContainsOperations());
		domainStoreEClass.getESuperTypes().add(this.getContainsEventTriggers());
		domainStoreEClass.getESuperTypes().add(this.getNamedElement());
		domainStoreEClass.getESuperTypes().add(this.getContainsWires());
		domainStoreEClass.getESuperTypes().add(this.getGeneratesElements());
		dataFlowEdgeEClass.getESuperTypes().add(this.getGeneratedElement());
		temporaryVariableEClass.getESuperTypes().add(this.getNamedElement());
		temporaryVariableEClass.getESuperTypes().add(this.getDataFlowEdgesSource());
		temporaryVariableEClass.getESuperTypes().add(this.getDataFlowEdgeDestination());
		executionEdgeEClass.getESuperTypes().add(this.getGeneratedElement());
		conditionalEdgeEClass.getESuperTypes().add(this.getExecutionEdge());
		conditionalEdgeEClass.getESuperTypes().add(this.getNamedElement());
		dynamicApplicationElementSetEClass.getESuperTypes().add(this.getApplicationElement());
		shouldntContainWiresEClass.getESuperTypes().add(this.getContainsWires());

		// Initialize classes and features; add operations and parameters
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgeEClass, WireEdge.class, "WireEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdge_From(), this.getWireEdgesSource(), this.getWireEdgesSource_OutEdges(), "from", null, 1, 1, WireEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWireEdge_To(), this.getWireEdgeDestination(), this.getWireEdgeDestination_InEdges(), "to", null, 1, 1, WireEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgeDestinationEClass, WireEdgeDestination.class, "WireEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdgeDestination_InEdges(), this.getWireEdge(), this.getWireEdge_To(), "inEdges", null, 0, -1, WireEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireEdgesSourceEClass, WireEdgesSource.class, "WireEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWireEdgesSource_OutEdges(), this.getWireEdge(), this.getWireEdge_From(), "outEdges", null, 0, -1, WireEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTriggerEClass, EventTrigger.class, "EventTrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containsEventTriggersEClass, ContainsEventTriggers.class, "ContainsEventTriggers", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsEventTriggers_EventTriggers(), this.getEventTrigger(), null, "eventTriggers", null, 0, -1, ContainsEventTriggers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainObjectEClass, DomainObject.class, "DomainObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainObject_Attributes(), this.getDomainAttribute(), null, "attributes", null, 0, -1, DomainObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainAttributeEClass, DomainAttribute.class, "DomainAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activityNodeEClass, ActivityNode.class, "ActivityNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(singleOperationEClass, SingleOperation.class, "SingleOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(chainedOperationEClass, ChainedOperation.class, "ChainedOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeOperationEClass, CompositeOperation.class, "CompositeOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeOperation_Nodes(), this.getActivityNode(), null, "nodes", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_DataEdges(), this.getDataFlowEdge(), null, "dataEdges", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_ExecutionEdges(), this.getExecutionEdge(), null, "executionEdges", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation_Variables(), this.getTemporaryVariable(), null, "variables", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeOperation__shouldnt_parameters(), this.getApplicationElementProperty(), null, "_shouldnt_parameters", null, 0, -1, CompositeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containsOperationsEClass, ContainsOperations.class, "ContainsOperations", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainsOperations_Operations(), this.getOperation(), null, "operations", null, 0, -1, ContainsOperations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementEClass, ApplicationElement.class, "ApplicationElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElement_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, ApplicationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationElement_Values(), this.getStaticValue(), null, "values", null, 0, -1, ApplicationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementContainerEClass, ApplicationElementContainer.class, "ApplicationElementContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationElementContainer_Children(), this.getApplicationElement(), null, "children", null, 0, -1, ApplicationElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationElementPropertyEClass, ApplicationElementProperty.class, "ApplicationElementProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(staticValueEClass, StaticValue.class, "StaticValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStaticValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, StaticValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(visibleThingEClass, VisibleThing.class, "VisibleThing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internetApplicationEClass, InternetApplication.class, "InternetApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternetApplication_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_Children(), this.getApplicationElement(), null, "children", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternetApplication_DomainStores(), this.getDomainStore(), null, "domainStores", null, 0, -1, InternetApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainStoreEClass, DomainStore.class, "DomainStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainStore_Children(), this.getDomainObject(), null, "children", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainStore_Properties(), this.getApplicationElementProperty(), null, "properties", null, 0, -1, DomainStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEReference(getContainsWires_Wires(), this.getWireEdge(), null, "wires", null, 0, -1, ContainsWires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(shouldntContainWiresEClass, ShouldntContainWires.class, "ShouldntContainWires", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(generatedElementEClass, GeneratedElement.class, "GeneratedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratedElement_GeneratedBy(), this.getGeneratesElements(), this.getGeneratesElements_GeneratedElements(), "generatedBy", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratedElement_IsGenerated(), ecorePackage.getEBoolean(), "isGenerated", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratedElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, GeneratedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatesElementsEClass, GeneratesElements.class, "GeneratesElements", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratesElements_GeneratedElements(), this.getGeneratedElement(), this.getGeneratedElement_GeneratedBy(), "generatedElements", null, 0, -1, GeneratesElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratesElements_Overridden(), ecorePackage.getEBoolean(), "overridden", null, 0, 1, GeneratesElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

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
		  (wireEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "temporarily contains wires until GMF bug is fixed "
		   });		
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
		addAnnotation
		  (shouldntContainWiresEClass, 
		   source, 
		   new String[] {
			 "comment", "classes that extend this SHOULDN\'T contain wires, but GMF\'s limitations require it (see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=249717)"
		   });
	}

} //ModelPackageImpl
