/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.wires.WiresPackage
 * @generated
 */
public interface WiresFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WiresFactory eINSTANCE = org.openiaml.model.model.wires.impl.WiresFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Single Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Wire</em>'.
	 * @generated
	 */
	SingleWire createSingleWire();

	/**
	 * Returns a new object of class '<em>Composite Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Wire</em>'.
	 * @generated
	 */
	CompositeWire createCompositeWire();

	/**
	 * Returns a new object of class '<em>Sync Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sync Wire</em>'.
	 * @generated
	 */
	SyncWire createSyncWire();

	/**
	 * Returns a new object of class '<em>Run Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Wire</em>'.
	 * @generated
	 */
	RunWire createRunWire();

	/**
	 * Returns a new object of class '<em>Execution Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Wire</em>'.
	 * @generated
	 */
	ExecutionWire createExecutionWire();

	/**
	 * Returns a new object of class '<em>Provided Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Parameter Wire</em>'.
	 * @generated
	 */
	ProvidedParameterWire createProvidedParameterWire();

	/**
	 * Returns a new object of class '<em>Property To Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property To Parameter Wire</em>'.
	 * @generated
	 */
	PropertyToParameterWire createPropertyToParameterWire();

	/**
	 * Returns a new object of class '<em>Property To Execution Wire Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property To Execution Wire Wire</em>'.
	 * @generated
	 */
	PropertyToExecutionWireWire createPropertyToExecutionWireWire();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WiresPackage getWiresPackage();

} //WiresFactory
