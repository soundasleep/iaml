/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.CanBeSynced;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.ShouldntContainWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.users.Permission;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.users.UsersPackage
 * @generated
 */
public class UsersAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsersPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsersAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UsersPackage.eINSTANCE;
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
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UsersSwitch<Adapter> modelSwitch =
		new UsersSwitch<Adapter>() {
			@Override
			public Adapter caseUserStore(UserStore object) {
				return createUserStoreAdapter();
			}
			@Override
			public Adapter caseRole(Role object) {
				return createRoleAdapter();
			}
			@Override
			public Adapter casePermission(Permission object) {
				return createPermissionAdapter();
			}
			@Override
			public Adapter caseUserInstance(UserInstance object) {
				return createUserInstanceAdapter();
			}
			@Override
			public Adapter caseContainsOperations(ContainsOperations object) {
				return createContainsOperationsAdapter();
			}
			@Override
			public Adapter caseContainsEventTriggers(ContainsEventTriggers object) {
				return createContainsEventTriggersAdapter();
			}
			@Override
			public Adapter caseGeneratedElement(GeneratedElement object) {
				return createGeneratedElementAdapter();
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
			public Adapter caseContainsConditions(ContainsConditions object) {
				return createContainsConditionsAdapter();
			}
			@Override
			public Adapter caseGeneratesElements(GeneratesElements object) {
				return createGeneratesElementsAdapter();
			}
			@Override
			public Adapter caseDomainStore(DomainStore object) {
				return createDomainStoreAdapter();
			}
			@Override
			public Adapter caseShouldntContainWires(ShouldntContainWires object) {
				return createShouldntContainWiresAdapter();
			}
			@Override
			public Adapter caseWireEdgesSource(WireEdgesSource object) {
				return createWireEdgesSourceAdapter();
			}
			@Override
			public Adapter caseWireEdgeDestination(WireEdgeDestination object) {
				return createWireEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseCanBeSynced(CanBeSynced object) {
				return createCanBeSyncedAdapter();
			}
			@Override
			public Adapter caseApplicationElement(ApplicationElement object) {
				return createApplicationElementAdapter();
			}
			@Override
			public Adapter caseParameterEdgesSource(ParameterEdgesSource object) {
				return createParameterEdgesSourceAdapter();
			}
			@Override
			public Adapter caseExtendsEdgesSource(ExtendsEdgesSource object) {
				return createExtendsEdgesSourceAdapter();
			}
			@Override
			public Adapter caseExtendsEdgeDestination(ExtendsEdgeDestination object) {
				return createExtendsEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseDomainObject(DomainObject object) {
				return createDomainObjectAdapter();
			}
			@Override
			public Adapter caseParameterEdgeDestination(ParameterEdgeDestination object) {
				return createParameterEdgeDestinationAdapter();
			}
			@Override
			public Adapter caseDomainObjectInstance(DomainObjectInstance object) {
				return createDomainObjectInstanceAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.users.UserStore <em>User Store</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.users.UserStore
	 * @generated
	 */
	public Adapter createUserStoreAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.users.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.users.Role
	 * @generated
	 */
	public Adapter createRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.users.Permission <em>Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.users.Permission
	 * @generated
	 */
	public Adapter createPermissionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.users.UserInstance <em>User Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.users.UserInstance
	 * @generated
	 */
	public Adapter createUserInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ContainsOperations <em>Contains Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ContainsOperations
	 * @generated
	 */
	public Adapter createContainsOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ContainsEventTriggers <em>Contains Event Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ContainsEventTriggers
	 * @generated
	 */
	public Adapter createContainsEventTriggersAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.GeneratedElement <em>Generated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.GeneratedElement
	 * @generated
	 */
	public Adapter createGeneratedElementAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ContainsConditions <em>Contains Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ContainsConditions
	 * @generated
	 */
	public Adapter createContainsConditionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.GeneratesElements <em>Generates Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.GeneratesElements
	 * @generated
	 */
	public Adapter createGeneratesElementsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.DomainStore <em>Domain Store</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.DomainStore
	 * @generated
	 */
	public Adapter createDomainStoreAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ShouldntContainWires <em>Shouldnt Contain Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ShouldntContainWires
	 * @generated
	 */
	public Adapter createShouldntContainWiresAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.WireEdgesSource <em>Wire Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.WireEdgesSource
	 * @generated
	 */
	public Adapter createWireEdgesSourceAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.CanBeSynced <em>Can Be Synced</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.CanBeSynced
	 * @generated
	 */
	public Adapter createCanBeSyncedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ApplicationElement <em>Application Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ApplicationElement
	 * @generated
	 */
	public Adapter createApplicationElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.ParameterEdgesSource <em>Parameter Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.ParameterEdgesSource
	 * @generated
	 */
	public Adapter createParameterEdgesSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.ExtendsEdgesSource <em>Extends Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.ExtendsEdgesSource
	 * @generated
	 */
	public Adapter createExtendsEdgesSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.ExtendsEdgeDestination <em>Extends Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.ExtendsEdgeDestination
	 * @generated
	 */
	public Adapter createExtendsEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.DomainObject <em>Domain Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.DomainObject
	 * @generated
	 */
	public Adapter createDomainObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.wires.ParameterEdgeDestination <em>Parameter Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.wires.ParameterEdgeDestination
	 * @generated
	 */
	public Adapter createParameterEdgeDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.DomainObjectInstance <em>Domain Object Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.DomainObjectInstance
	 * @generated
	 */
	public Adapter createDomainObjectInstanceAdapter() {
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

} //UsersAdapterFactory
