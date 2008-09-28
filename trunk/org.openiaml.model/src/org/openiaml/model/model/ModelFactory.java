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
	 * Returns a new object of class '<em>Operation Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Parameter</em>'.
	 * @generated
	 */
	OperationParameter createOperationParameter();

	/**
	 * Returns a new object of class '<em>Single Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Operation</em>'.
	 * @generated
	 */
	SingleOperation createSingleOperation();

	/**
	 * Returns a new object of class '<em>Chained Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Chained Operation</em>'.
	 * @generated
	 */
	ChainedOperation createChainedOperation();

	/**
	 * Returns a new object of class '<em>Composite Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Operation</em>'.
	 * @generated
	 */
	CompositeOperation createCompositeOperation();

	/**
	 * Returns a new object of class '<em>Event Aware Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Aware Operation</em>'.
	 * @generated
	 */
	EventAwareOperation createEventAwareOperation();

	/**
	 * Returns a new object of class '<em>Application Element Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Element Property</em>'.
	 * @generated
	 */
	ApplicationElementProperty createApplicationElementProperty();

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
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
