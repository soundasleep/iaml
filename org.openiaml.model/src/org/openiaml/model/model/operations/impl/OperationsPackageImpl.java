/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.operations.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.impl.ComponentsPackageImpl;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.impl.DomainPackageImpl;
import org.openiaml.model.model.impl.ModelPackageImpl;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.OperationsFactory;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.impl.ScopesPackageImpl;
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
public class OperationsPackageImpl extends EPackageImpl implements OperationsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cancelNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finishNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass splitNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCallNodeEClass = null;

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
	 * @see org.openiaml.model.model.operations.OperationsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OperationsPackageImpl() {
		super(eNS_URI, OperationsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OperationsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OperationsPackage init() {
		if (isInited) return (OperationsPackage)EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI);

		// Obtain or create and register package
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OperationsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) : ScopesPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);

		// Create package meta-data objects
		theOperationsPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theScopesPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();

		// Initialize created meta-data
		theOperationsPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theScopesPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOperationsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OperationsPackage.eNS_URI, theOperationsPackage);
		return theOperationsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartNode() {
		return startNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCancelNode() {
		return cancelNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCancelNode_ExceptionText() {
		return (EAttribute)cancelNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFinishNode() {
		return finishNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecisionNode() {
		return decisionNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecisionOperation() {
		return decisionOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecisionCondition() {
		return decisionConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSplitNode() {
		return splitNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoinNode() {
		return joinNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationCallNode() {
		return operationCallNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationsFactory getOperationsFactory() {
		return (OperationsFactory)getEFactoryInstance();
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
		startNodeEClass = createEClass(START_NODE);

		cancelNodeEClass = createEClass(CANCEL_NODE);
		createEAttribute(cancelNodeEClass, CANCEL_NODE__EXCEPTION_TEXT);

		finishNodeEClass = createEClass(FINISH_NODE);

		decisionNodeEClass = createEClass(DECISION_NODE);

		decisionOperationEClass = createEClass(DECISION_OPERATION);

		decisionConditionEClass = createEClass(DECISION_CONDITION);

		splitNodeEClass = createEClass(SPLIT_NODE);

		joinNodeEClass = createEClass(JOIN_NODE);

		operationCallNodeEClass = createEClass(OPERATION_CALL_NODE);
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
		startNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		startNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		cancelNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		cancelNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		finishNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		finishNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		decisionNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		decisionNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		decisionNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		decisionOperationEClass.getESuperTypes().add(theModelPackage.getChainedOperation());
		decisionOperationEClass.getESuperTypes().add(theModelPackage.getDataFlowEdgeDestination());
		decisionConditionEClass.getESuperTypes().add(theModelPackage.getCondition());
		decisionConditionEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		decisionConditionEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		decisionConditionEClass.getESuperTypes().add(theModelPackage.getDataFlowEdgeDestination());
		splitNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		splitNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		splitNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		joinNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		joinNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		joinNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getActivityNode());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgesSource());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getExecutionEdgeDestination());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getDataFlowEdgeDestination());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getDataFlowEdgesSource());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getWireEdgesSource());
		operationCallNodeEClass.getESuperTypes().add(theModelPackage.getNamedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(startNodeEClass, StartNode.class, "StartNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cancelNodeEClass, CancelNode.class, "CancelNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCancelNode_ExceptionText(), ecorePackage.getEString(), "exceptionText", null, 0, 1, CancelNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(finishNodeEClass, FinishNode.class, "FinishNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(decisionNodeEClass, DecisionNode.class, "DecisionNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(decisionOperationEClass, DecisionOperation.class, "DecisionOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(decisionConditionEClass, DecisionCondition.class, "DecisionCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(splitNodeEClass, SplitNode.class, "SplitNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(joinNodeEClass, JoinNode.class, "JoinNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationCallNodeEClass, OperationCallNode.class, "OperationCallNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		  (decisionConditionEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.2"
		   });		
		addAnnotation
		  (splitNodeEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4"
		   });		
		addAnnotation
		  (joinNodeEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4"
		   });		
		addAnnotation
		  (operationCallNodeEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4"
		   });
	}

} //OperationsPackageImpl
