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
	 * Returns a new object of class '<em>Parameter Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Edge</em>'.
	 * @generated
	 */
	ParameterEdge createParameterEdge();

	/**
	 * Returns a new object of class '<em>Set Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Wire</em>'.
	 * @generated
	 */
	SetWire createSetWire();

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
	 * Returns a new object of class '<em>Condition Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition Wire</em>'.
	 * @generated
	 */
	ConditionWire createConditionWire();

	/**
	 * Returns a new object of class '<em>New Instance Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Instance Wire</em>'.
	 * @generated
	 */
	NewInstanceWire createNewInstanceWire();

	/**
	 * Returns a new object of class '<em>Extends Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extends Wire</em>'.
	 * @generated
	 */
	ExtendsWire createExtendsWire();

	/**
	 * Returns a new object of class '<em>Requires Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requires Wire</em>'.
	 * @generated
	 */
	RequiresWire createRequiresWire();

	/**
	 * Returns a new object of class '<em>Constraint Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Wire</em>'.
	 * @generated
	 */
	ConstraintWire createConstraintWire();

	/**
	 * Returns a new object of class '<em>Provides Wire</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provides Wire</em>'.
	 * @generated
	 */
	ProvidesWire createProvidesWire();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WiresPackage getWiresPackage();

} //WiresFactory
