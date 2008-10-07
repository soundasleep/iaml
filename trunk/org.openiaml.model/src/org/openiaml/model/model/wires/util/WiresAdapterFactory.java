/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SingleWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.wires.WiresPackage
 * @generated
 */
public class WiresAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WiresPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiresAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WiresPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WiresSwitch<Adapter> modelSwitch =
		new WiresSwitch<Adapter>() {
			@Override
			public Adapter caseSingleWire(SingleWire object) {
				return createSingleWireAdapter();
			}
			@Override
			public Adapter caseCompositeWire(CompositeWire object) {
				return createCompositeWireAdapter();
			}
			@Override
			public Adapter caseSyncWire(SyncWire object) {
				return createSyncWireAdapter();
			}
			@Override
			public Adapter caseRunInstanceWire(RunInstanceWire object) {
				return createRunInstanceWireAdapter();
			}
			@Override
			public Adapter caseParameterWire(ParameterWire object) {
				return createParameterWireAdapter();
			}
			@Override
			public Adapter caseWireEdge(WireEdge object) {
				return createWireEdgeAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseContainsWires(ContainsWires object) {
				return createContainsWiresAdapter();
			}
			@Override
			public Adapter caseWireEdgeDestination(WireEdgeDestination object) {
				return createWireEdgeDestinationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.SingleWire <em>Single Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.SingleWire
	 * @generated
	 */
	public Adapter createSingleWireAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.CompositeWire <em>Composite Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.CompositeWire
	 * @generated
	 */
	public Adapter createCompositeWireAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.SyncWire <em>Sync Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.SyncWire
	 * @generated
	 */
	public Adapter createSyncWireAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.RunInstanceWire <em>Run Instance Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.RunInstanceWire
	 * @generated
	 */
	public Adapter createRunInstanceWireAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.ParameterWire <em>Parameter Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.ParameterWire
	 * @generated
	 */
	public Adapter createParameterWireAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.WireEdge <em>Wire Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.WireEdge
	 * @generated
	 */
	public Adapter createWireEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ContainsWires <em>Contains Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ContainsWires
	 * @generated
	 */
	public Adapter createContainsWiresAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.WireEdgeDestination <em>Wire Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.WireEdgeDestination
	 * @generated
	 */
	public Adapter createWireEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WiresAdapterFactory
