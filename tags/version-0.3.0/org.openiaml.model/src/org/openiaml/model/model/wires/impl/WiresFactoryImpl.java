/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.wires.CommitWire;
import org.openiaml.model.model.wires.CompositeWire;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.ShowWire;
import org.openiaml.model.model.wires.SingleWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresFactory;
import org.openiaml.model.model.wires.WiresPackage;

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
			case WiresPackage.RUN_INSTANCE_WIRE: return createRunInstanceWire();
			case WiresPackage.PARAMETER_WIRE: return createParameterWire();
			case WiresPackage.SET_WIRE: return createSetWire();
			case WiresPackage.SHOW_WIRE: return createShowWire();
			case WiresPackage.COMMIT_WIRE: return createCommitWire();
			case WiresPackage.NAVIGATE_WIRE: return createNavigateWire();
			case WiresPackage.SELECT_WIRE: return createSelectWire();
			case WiresPackage.CONDITION_WIRE: return createConditionWire();
			case WiresPackage.NEW_INSTANCE_WIRE: return createNewInstanceWire();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SingleWire createSingleWire() {
		SingleWireImpl singleWire = new SingleWireImpl();
		generateID(singleWire);
		return singleWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CompositeWire createCompositeWire() {
		CompositeWireImpl compositeWire = new CompositeWireImpl();
		generateID(compositeWire);
		return compositeWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SyncWire createSyncWire() {
		SyncWireImpl syncWire = new SyncWireImpl();
		generateID(syncWire);
		return syncWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RunInstanceWire createRunInstanceWire() {
		RunInstanceWireImpl runInstanceWire = new RunInstanceWireImpl();
		generateID(runInstanceWire);
		return runInstanceWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ParameterWire createParameterWire() {
		ParameterWireImpl parameterWire = new ParameterWireImpl();
		generateID(parameterWire);
		return parameterWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SetWire createSetWire() {
		SetWireImpl setWire = new SetWireImpl();
		generateID(setWire);
		return setWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ShowWire createShowWire() {
		ShowWireImpl showWire = new ShowWireImpl();
		generateID(showWire);
		return showWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CommitWire createCommitWire() {
		CommitWireImpl commitWire = new CommitWireImpl();
		generateID(commitWire);
		return commitWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NavigateWire createNavigateWire() {
		NavigateWireImpl navigateWire = new NavigateWireImpl();
		generateID(navigateWire);
		return navigateWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SelectWire createSelectWire() {
		SelectWireImpl selectWire = new SelectWireImpl();
		generateID(selectWire);
		return selectWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ConditionWire createConditionWire() {
		ConditionWireImpl conditionWire = new ConditionWireImpl();
		generateID(conditionWire);
		return conditionWire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NewInstanceWire createNewInstanceWire() {
		NewInstanceWireImpl newInstanceWire = new NewInstanceWireImpl();
		generateID(newInstanceWire);
		return newInstanceWire;
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

	private static long generate_id_counter = 0;
	private static final String packageDate = Long.toHexString(new Date().getTime());
	
	/**
	 * We want a way to generate IDs that we know are unique between different elements
	 * in the same model, but currently we don't mind if they aren't unique
	 * between different models created at the exact same time. (For this, we
	 * would need to use UUIDs.) Applies the ID to the given element.
	 * 
	 * This is probably a really unpleasant initial implementation but can easily
	 * be changed in the future :)
	 * 
	 * Currently it sets IDs to something like "Model.12b52.42", where
	 * - the first part is the package name
	 * - the second part is the time the package factory was instantiated (in hex)
	 * - the third part is a unique ID to this factory instance (in hex)
	 * 
	 * @see GeneratedElement
	 * @param obj
	 */
	protected void generateID(EObject obj) {
		if (obj instanceof GeneratedElement) {
			GeneratedElement ge = (GeneratedElement) obj;			
			generate_id_counter++;			
			ge.setId( this.getEPackage().getName() + "." + packageDate + "." + Long.toHexString(generate_id_counter) );
		}
	}
	
} //WiresFactoryImpl
