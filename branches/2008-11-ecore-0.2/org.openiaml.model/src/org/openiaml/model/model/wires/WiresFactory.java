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
	 * Returns a new object of class '<em>Run Instance Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Instance Wire</em>'.
	 * @generated
	 */
	RunInstanceWire createRunInstanceWire();

	/**
	 * Returns a new object of class '<em>Parameter Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Wire</em>'.
	 * @generated
	 */
	ParameterWire createParameterWire();

	/**
	 * Returns a new object of class '<em>Create Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Wire</em>'.
	 * @generated
	 */
	CreateWire createCreateWire();

	/**
	 * Returns a new object of class '<em>Set Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Wire</em>'.
	 * @generated
	 */
	SetWire createSetWire();

	/**
	 * Returns a new object of class '<em>Update Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Update Wire</em>'.
	 * @generated
	 */
	UpdateWire createUpdateWire();

	/**
	 * Returns a new object of class '<em>Save Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Save Wire</em>'.
	 * @generated
	 */
	SaveWire createSaveWire();

	/**
	 * Returns a new object of class '<em>Link Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link Wire</em>'.
	 * @generated
	 */
	LinkWire createLinkWire();

	/**
	 * Returns a new object of class '<em>Navigate Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigate Wire</em>'.
	 * @generated
	 */
	NavigateWire createNavigateWire();

	/**
	 * Returns a new object of class '<em>Select Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Select Wire</em>'.
	 * @generated
	 */
	SelectWire createSelectWire();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WiresPackage getWiresPackage();

} //WiresFactory
