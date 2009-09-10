/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.openiaml.model.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Wire Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wire Edge</em>'.
	 * @generated
	 */
	WireEdge createWireEdge();

	/**
	 * Returns a new object of class '<em>Event Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Trigger</em>'.
	 * @generated
	 */
	EventTrigger createEventTrigger();

	/**
	 * Returns a new object of class '<em>Domain Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Object</em>'.
	 * @generated
	 */
	DomainObject createDomainObject();

	/**
	 * Returns a new object of class '<em>Domain Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Attribute</em>'.
	 * @generated
	 */
	DomainAttribute createDomainAttribute();

	/**
	 * Returns a new object of class '<em>Activity Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Node</em>'.
	 * @generated
	 */
	ActivityNode createActivityNode();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Primitive Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Operation</em>'.
	 * @generated
	 */
	PrimitiveOperation createPrimitiveOperation();

	/**
	 * Returns a new object of class '<em>Composite Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Operation</em>'.
	 * @generated
	 */
	CompositeOperation createCompositeOperation();

	/**
	 * Returns a new object of class '<em>Application Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Element</em>'.
	 * @generated
	 */
	ApplicationElement createApplicationElement();

	/**
	 * Returns a new object of class '<em>Application Element Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Element Property</em>'.
	 * @generated
	 */
	ApplicationElementProperty createApplicationElementProperty();

	/**
	 * Returns a new object of class '<em>Static Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Value</em>'.
	 * @generated
	 */
	StaticValue createStaticValue();

	/**
	 * Returns a new object of class '<em>Visible Thing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Visible Thing</em>'.
	 * @generated
	 */
	VisibleThing createVisibleThing();

	/**
	 * Returns a new object of class '<em>Internet Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Internet Application</em>'.
	 * @generated
	 */
	InternetApplication createInternetApplication();

	/**
	 * Returns a new object of class '<em>Domain Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Store</em>'.
	 * @generated
	 */
	DomainStore createDomainStore();

	/**
	 * Returns a new object of class '<em>Data Flow Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Flow Edge</em>'.
	 * @generated
	 */
	DataFlowEdge createDataFlowEdge();

	/**
	 * Returns a new object of class '<em>Temporary Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Temporary Variable</em>'.
	 * @generated
	 */
	TemporaryVariable createTemporaryVariable();

	/**
	 * Returns a new object of class '<em>Execution Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Edge</em>'.
	 * @generated
	 */
	ExecutionEdge createExecutionEdge();

	/**
	 * Returns a new object of class '<em>Conditional Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional Edge</em>'.
	 * @generated
	 */
	ConditionalEdge createConditionalEdge();

	/**
	 * Returns a new object of class '<em>Dynamic Application Element Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic Application Element Set</em>'.
	 * @generated
	 */
	DynamicApplicationElementSet createDynamicApplicationElementSet();

	/**
	 * Returns a new object of class '<em>Derived View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived View</em>'.
	 * @generated
	 */
	DerivedView createDerivedView();

	/**
	 * Returns a new object of class '<em>Domain Object Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Object Instance</em>'.
	 * @generated
	 */
	DomainObjectInstance createDomainObjectInstance();

	/**
	 * Returns a new object of class '<em>Page Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page Request</em>'.
	 * @generated
	 */
	PageRequest createPageRequest();

	/**
	 * Returns a new object of class '<em>Visitor Agent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Visitor Agent</em>'.
	 * @generated
	 */
	VisitorAgent createVisitorAgent();

	/**
	 * Returns a new object of class '<em>Composite Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Condition</em>'.
	 * @generated
	 */
	CompositeCondition createCompositeCondition();

	/**
	 * Returns a new object of class '<em>Domain Attribute Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Attribute Instance</em>'.
	 * @generated
	 */
	DomainAttributeInstance createDomainAttributeInstance();

	/**
	 * Returns a new object of class '<em>Query Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Parameter</em>'.
	 * @generated
	 */
	QueryParameter createQueryParameter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
