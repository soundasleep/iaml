/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ConditionalEdge;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.PageRequest;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SingleOperation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.VisitorAgent;
import org.openiaml.model.model.WireEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/model"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.WIRE_EDGE: return createWireEdge();
			case ModelPackage.EVENT_TRIGGER: return createEventTrigger();
			case ModelPackage.DOMAIN_OBJECT: return createDomainObject();
			case ModelPackage.DOMAIN_ATTRIBUTE: return createDomainAttribute();
			case ModelPackage.ACTIVITY_NODE: return createActivityNode();
			case ModelPackage.PARAMETER: return createParameter();
			case ModelPackage.SINGLE_OPERATION: return createSingleOperation();
			case ModelPackage.CHAINED_OPERATION: return createChainedOperation();
			case ModelPackage.COMPOSITE_OPERATION: return createCompositeOperation();
			case ModelPackage.APPLICATION_ELEMENT: return createApplicationElement();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY: return createApplicationElementProperty();
			case ModelPackage.STATIC_VALUE: return createStaticValue();
			case ModelPackage.VISIBLE_THING: return createVisibleThing();
			case ModelPackage.INTERNET_APPLICATION: return createInternetApplication();
			case ModelPackage.DOMAIN_STORE: return createDomainStore();
			case ModelPackage.DATA_FLOW_EDGE: return createDataFlowEdge();
			case ModelPackage.TEMPORARY_VARIABLE: return createTemporaryVariable();
			case ModelPackage.EXECUTION_EDGE: return createExecutionEdge();
			case ModelPackage.CONDITIONAL_EDGE: return createConditionalEdge();
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET: return createDynamicApplicationElementSet();
			case ModelPackage.DERIVED_VIEW: return createDerivedView();
			case ModelPackage.DOMAIN_OBJECT_INSTANCE: return createDomainObjectInstance();
			case ModelPackage.PAGE_REQUEST: return createPageRequest();
			case ModelPackage.VISITOR_AGENT: return createVisitorAgent();
			case ModelPackage.COMPOSITE_CONDITION: return createCompositeCondition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public WireEdge createWireEdge() {
		WireEdgeImpl wireEdge = new WireEdgeImpl();
		generateID(wireEdge);
		return wireEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EventTrigger createEventTrigger() {
		EventTriggerImpl eventTrigger = new EventTriggerImpl();
		generateID(eventTrigger);
		return eventTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DomainObject createDomainObject() {
		DomainObjectImpl domainObject = new DomainObjectImpl();
		generateID(domainObject);
		return domainObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DomainAttribute createDomainAttribute() {
		DomainAttributeImpl domainAttribute = new DomainAttributeImpl();
		generateID(domainAttribute);
		return domainAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ActivityNode createActivityNode() {
		ActivityNodeImpl activityNode = new ActivityNodeImpl();
		generateID(activityNode);
		return activityNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		generateID(parameter);
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SingleOperation createSingleOperation() {
		SingleOperationImpl singleOperation = new SingleOperationImpl();
		generateID(singleOperation);
		return singleOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ChainedOperation createChainedOperation() {
		ChainedOperationImpl chainedOperation = new ChainedOperationImpl();
		generateID(chainedOperation);
		return chainedOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CompositeOperation createCompositeOperation() {
		CompositeOperationImpl compositeOperation = new CompositeOperationImpl();
		generateID(compositeOperation);
		return compositeOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ApplicationElement createApplicationElement() {
		ApplicationElementImpl applicationElement = new ApplicationElementImpl();
		generateID(applicationElement);
		return applicationElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ApplicationElementProperty createApplicationElementProperty() {
		ApplicationElementPropertyImpl applicationElementProperty = new ApplicationElementPropertyImpl();
		generateID(applicationElementProperty);
		return applicationElementProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public StaticValue createStaticValue() {
		StaticValueImpl staticValue = new StaticValueImpl();
		generateID(staticValue);
		return staticValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public VisibleThing createVisibleThing() {
		VisibleThingImpl visibleThing = new VisibleThingImpl();
		generateID(visibleThing);
		return visibleThing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public InternetApplication createInternetApplication() {
		InternetApplicationImpl internetApplication = new InternetApplicationImpl();
		generateID(internetApplication);
		return internetApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DomainStore createDomainStore() {
		DomainStoreImpl domainStore = new DomainStoreImpl();
		generateID(domainStore);
		return domainStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DataFlowEdge createDataFlowEdge() {
		DataFlowEdgeImpl dataFlowEdge = new DataFlowEdgeImpl();
		generateID(dataFlowEdge);
		return dataFlowEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TemporaryVariable createTemporaryVariable() {
		TemporaryVariableImpl temporaryVariable = new TemporaryVariableImpl();
		generateID(temporaryVariable);
		return temporaryVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutionEdge createExecutionEdge() {
		ExecutionEdgeImpl executionEdge = new ExecutionEdgeImpl();
		generateID(executionEdge);
		return executionEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ConditionalEdge createConditionalEdge() {
		ConditionalEdgeImpl conditionalEdge = new ConditionalEdgeImpl();
		generateID(conditionalEdge);
		return conditionalEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DynamicApplicationElementSet createDynamicApplicationElementSet() {
		DynamicApplicationElementSetImpl dynamicApplicationElementSet = new DynamicApplicationElementSetImpl();
		generateID(dynamicApplicationElementSet);
		return dynamicApplicationElementSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DerivedView createDerivedView() {
		DerivedViewImpl derivedView = new DerivedViewImpl();
		generateID(derivedView);
		return derivedView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DomainObjectInstance createDomainObjectInstance() {
		DomainObjectInstanceImpl domainObjectInstance = new DomainObjectInstanceImpl();
		generateID(domainObjectInstance);
		return domainObjectInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public PageRequest createPageRequest() {
		PageRequestImpl pageRequest = new PageRequestImpl();
		generateID(pageRequest);
		return pageRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public VisitorAgent createVisitorAgent() {
		VisitorAgentImpl visitorAgent = new VisitorAgentImpl();
		generateID(visitorAgent);
		return visitorAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CompositeCondition createCompositeCondition() {
		CompositeConditionImpl compositeCondition = new CompositeConditionImpl();
		generateID(compositeCondition);
		return compositeCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}
	
	private static long generate_id_counter = 0;
	private static final String packageDate = Long.toHexString(new Date().getTime());
	
	/**
	 * We want a way to generate IDs that we know are unique between different elements
	 * in the same model, but currently we don't mind if they aren't unique
	 * between different models created at the exact same time. (For this, we
	 * would need to use UUIDs.) Applies the ID to the given element.
	 * 
	 * This is probably a really unpleasant initial implementation but can easily
	 * be changed in the future :)
	 * 
	 * Currently it sets IDs to something like "Model.12b52.42", where
	 * - the first part is the package name
	 * - the second part is the time the package factory was instantiated (in hex, accurate to ms)
	 * - the third part is a unique ID to this factory instance (in hex)
	 * 
	 * @see GeneratedElement
	 * @param obj
	 */
	protected void generateID(EObject obj) {
		if (obj instanceof GeneratedElement) {
			GeneratedElement ge = (GeneratedElement) obj;			
			generate_id_counter++;			
			ge.setId( this.getEPackage().getName() + "." + packageDate + "." + Long.toHexString(generate_id_counter) );
		}
	}

} //ModelFactoryImpl
