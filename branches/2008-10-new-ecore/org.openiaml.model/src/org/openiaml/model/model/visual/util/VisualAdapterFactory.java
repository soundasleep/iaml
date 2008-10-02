/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.visual.VisualPackage
 * @generated
 */
public class VisualAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static VisualPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisualAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = VisualPackage.eINSTANCE;
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
	protected VisualSwitch<Adapter> modelSwitch =
		new VisualSwitch<Adapter>() {
			@Override
			public Adapter casePage(Page object) {
				return createPageAdapter();
			}
			@Override
			public Adapter caseInputForm(InputForm object) {
				return createInputFormAdapter();
			}
			@Override
			public Adapter caseInputTextField(InputTextField object) {
				return createInputTextFieldAdapter();
			}
			@Override
			public Adapter caseContainsOperations(ContainsOperations object) {
				return createContainsOperationsAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseContainsEventTriggers(ContainsEventTriggers object) {
				return createContainsEventTriggersAdapter();
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
			public Adapter caseApplicationElement(ApplicationElement object) {
				return createApplicationElementAdapter();
			}
			@Override
			public Adapter caseApplicationElementContainer(ApplicationElementContainer object) {
				return createApplicationElementContainerAdapter();
			}
			@Override
			public Adapter caseVisibleThing(VisibleThing object) {
				return createVisibleThingAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.visual.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.visual.Page
	 * @generated
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.visual.InputForm <em>Input Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.visual.InputForm
	 * @generated
	 */
	public Adapter createInputFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.visual.InputTextField <em>Input Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.visual.InputTextField
	 * @generated
	 */
	public Adapter createInputTextFieldAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.ApplicationElementContainer <em>Application Element Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.ApplicationElementContainer
	 * @generated
	 */
	public Adapter createApplicationElementContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.openiaml.model.model.VisibleThing <em>Visible Thing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openiaml.model.model.VisibleThing
	 * @generated
	 */
	public Adapter createVisibleThingAdapter() {
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

} //VisualAdapterFactory
