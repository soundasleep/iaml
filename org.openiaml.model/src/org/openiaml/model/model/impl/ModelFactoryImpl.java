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
			case ModelPackage.OPERATION_PARAMETER: return createOperationParameter();
			case ModelPackage.SINGLE_OPERATION: return createSingleOperation();
			case ModelPackage.CHAINED_OPERATION: return createChainedOperation();
			case ModelPackage.COMPOSITE_OPERATION: return createCompositeOperation();
			case ModelPackage.COMPOSITE_CHAINED_OPERATION: return createCompositeChainedOperation();
			case ModelPackage.EVENT_AWARE_OPERATION: return createEventAwareOperation();
			case ModelPackage.APPLICATION_ELEMENT: return createApplicationElement();
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY: return createApplicationElementProperty();
			case ModelPackage.VISIBLE_THING: return createVisibleThing();
			case ModelPackage.INTERNET_APPLICATION: return createInternetApplication();
			case ModelPackage.DOMAIN_STORE: return createDomainStore();
			case ModelPackage.OPERATION_REFERENCE: return createOperationReference();
			case ModelPackage.PARAMETER_REFERENCE: return createParameterReference();
			case ModelPackage.VALUE_REFERENCE: return createValueReference();
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
	public OperationParameter createOperationParameter() {
		OperationParameterImpl operationParameter = new OperationParameterImpl();
		return operationParameter;
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
	public CompositeChainedOperation createCompositeChainedOperation() {
		CompositeChainedOperationImpl compositeChainedOperation = new CompositeChainedOperationImpl();
		return compositeChainedOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventAwareOperation createEventAwareOperation() {
		EventAwareOperationImpl eventAwareOperation = new EventAwareOperationImpl();
		return eventAwareOperation;
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
	public OperationReference createOperationReference() {
		OperationReferenceImpl operationReference = new OperationReferenceImpl();
		return operationReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterReference createParameterReference() {
		ParameterReferenceImpl parameterReference = new ParameterReferenceImpl();
		return parameterReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueReference createValueReference() {
		ValueReferenceImpl valueReference = new ValueReferenceImpl();
		return valueReference;
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
