/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openiaml.model.model.wires.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WiresFactoryImpl extends EFactoryImpl implements WiresFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WiresFactory init() {
		try {
			WiresFactory theWiresFactory = (WiresFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/model/wires"); 
			if (theWiresFactory != null) {
				return theWiresFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WiresFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiresFactoryImpl() {
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
			case WiresPackage.SINGLE_WIRE: return createSingleWire();
			case WiresPackage.COMPOSITE_WIRE: return createCompositeWire();
			case WiresPackage.SYNC_WIRE: return createSyncWire();
			case WiresPackage.RUN_WIRE: return createRunWire();
			case WiresPackage.EXECUTION_WIRE: return createExecutionWire();
			case WiresPackage.PROVIDED_PARAMETER_WIRE: return createProvidedParameterWire();
			case WiresPackage.PROPERTY_TO_PARAMETER_WIRE: return createPropertyToParameterWire();
			case WiresPackage.PROPERTY_TO_EXECUTION_WIRE: return createPropertyToExecutionWire();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleWire createSingleWire() {
		SingleWireImpl singleWire = new SingleWireImpl();
		return singleWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeWire createCompositeWire() {
		CompositeWireImpl compositeWire = new CompositeWireImpl();
		return compositeWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyncWire createSyncWire() {
		SyncWireImpl syncWire = new SyncWireImpl();
		return syncWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunWire createRunWire() {
		RunWireImpl runWire = new RunWireImpl();
		return runWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionWire createExecutionWire() {
		ExecutionWireImpl executionWire = new ExecutionWireImpl();
		return executionWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedParameterWire createProvidedParameterWire() {
		ProvidedParameterWireImpl providedParameterWire = new ProvidedParameterWireImpl();
		return providedParameterWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyToParameterWire createPropertyToParameterWire() {
		PropertyToParameterWireImpl propertyToParameterWire = new PropertyToParameterWireImpl();
		return propertyToParameterWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyToExecutionWire createPropertyToExecutionWire() {
		PropertyToExecutionWireImpl propertyToExecutionWire = new PropertyToExecutionWireImpl();
		return propertyToExecutionWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiresPackage getWiresPackage() {
		return (WiresPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WiresPackage getPackage() {
		return WiresPackage.eINSTANCE;
	}

} //WiresFactoryImpl
