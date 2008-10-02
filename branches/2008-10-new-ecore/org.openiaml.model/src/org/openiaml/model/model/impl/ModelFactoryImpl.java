/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.model.model.*;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SingleOperation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.VisibleThing;
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
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET: return createDynamicApplicationElementSet();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WireEdge createWireEdge() {
		WireEdgeImpl wireEdge = new WireEdgeImpl();
		return wireEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventTrigger createEventTrigger() {
		EventTriggerImpl eventTrigger = new EventTriggerImpl();
		return eventTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObject createDomainObject() {
		DomainObjectImpl domainObject = new DomainObjectImpl();
		return domainObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainAttribute createDomainAttribute() {
		DomainAttributeImpl domainAttribute = new DomainAttributeImpl();
		return domainAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode createActivityNode() {
		ActivityNodeImpl activityNode = new ActivityNodeImpl();
		return activityNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleOperation createSingleOperation() {
		SingleOperationImpl singleOperation = new SingleOperationImpl();
		return singleOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChainedOperation createChainedOperation() {
		ChainedOperationImpl chainedOperation = new ChainedOperationImpl();
		return chainedOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeOperation createCompositeOperation() {
		CompositeOperationImpl compositeOperation = new CompositeOperationImpl();
		return compositeOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationElement createApplicationElement() {
		ApplicationElementImpl applicationElement = new ApplicationElementImpl();
		return applicationElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationElementProperty createApplicationElementProperty() {
		ApplicationElementPropertyImpl applicationElementProperty = new ApplicationElementPropertyImpl();
		return applicationElementProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticValue createStaticValue() {
		StaticValueImpl staticValue = new StaticValueImpl();
		return staticValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibleThing createVisibleThing() {
		VisibleThingImpl visibleThing = new VisibleThingImpl();
		return visibleThing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternetApplication createInternetApplication() {
		InternetApplicationImpl internetApplication = new InternetApplicationImpl();
		return internetApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainStore createDomainStore() {
		DomainStoreImpl domainStore = new DomainStoreImpl();
		return domainStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFlowEdge createDataFlowEdge() {
		DataFlowEdgeImpl dataFlowEdge = new DataFlowEdgeImpl();
		return dataFlowEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemporaryVariable createTemporaryVariable() {
		TemporaryVariableImpl temporaryVariable = new TemporaryVariableImpl();
		return temporaryVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEdge createExecutionEdge() {
		ExecutionEdgeImpl executionEdge = new ExecutionEdgeImpl();
		return executionEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicApplicationElementSet createDynamicApplicationElementSet() {
		DynamicApplicationElementSetImpl dynamicApplicationElementSet = new DynamicApplicationElementSetImpl();
		return dynamicApplicationElementSet;
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

} //ModelFactoryImpl
