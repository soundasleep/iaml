/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.ModelReference;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Example</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.ExampleImpl#getExampleModel <em>Example Model</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.ExampleImpl#getExampleTest <em>Example Test</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExampleImpl extends EObjectImpl implements Example {
	/**
	 * The cached value of the '{@link #getExampleModel() <em>Example Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleModel()
	 * @generated
	 * @ordered
	 */
	protected ModelReference exampleModel;

	/**
	 * The cached value of the '{@link #getExampleTest() <em>Example Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleTest()
	 * @generated
	 * @ordered
	 */
	protected JavaClass exampleTest;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExampleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.EXAMPLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelReference getExampleModel() {
		if (exampleModel != null && exampleModel.eIsProxy()) {
			InternalEObject oldExampleModel = (InternalEObject)exampleModel;
			exampleModel = (ModelReference)eResolveProxy(oldExampleModel);
			if (exampleModel != oldExampleModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.EXAMPLE__EXAMPLE_MODEL, oldExampleModel, exampleModel));
			}
		}
		return exampleModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelReference basicGetExampleModel() {
		return exampleModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExampleModel(ModelReference newExampleModel) {
		ModelReference oldExampleModel = exampleModel;
		exampleModel = newExampleModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EXAMPLE__EXAMPLE_MODEL, oldExampleModel, exampleModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getExampleTest() {
		if (exampleTest != null && exampleTest.eIsProxy()) {
			InternalEObject oldExampleTest = (InternalEObject)exampleTest;
			exampleTest = (JavaClass)eResolveProxy(oldExampleTest);
			if (exampleTest != oldExampleTest) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.EXAMPLE__EXAMPLE_TEST, oldExampleTest, exampleTest));
			}
		}
		return exampleTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetExampleTest() {
		return exampleTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExampleTest(JavaClass newExampleTest) {
		JavaClass oldExampleTest = exampleTest;
		exampleTest = newExampleTest;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EXAMPLE__EXAMPLE_TEST, oldExampleTest, exampleTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldocPackage.EXAMPLE__EXAMPLE_MODEL:
				if (resolve) return getExampleModel();
				return basicGetExampleModel();
			case ModeldocPackage.EXAMPLE__EXAMPLE_TEST:
				if (resolve) return getExampleTest();
				return basicGetExampleTest();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModeldocPackage.EXAMPLE__EXAMPLE_MODEL:
				setExampleModel((ModelReference)newValue);
				return;
			case ModeldocPackage.EXAMPLE__EXAMPLE_TEST:
				setExampleTest((JavaClass)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModeldocPackage.EXAMPLE__EXAMPLE_MODEL:
				setExampleModel((ModelReference)null);
				return;
			case ModeldocPackage.EXAMPLE__EXAMPLE_TEST:
				setExampleTest((JavaClass)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModeldocPackage.EXAMPLE__EXAMPLE_MODEL:
				return exampleModel != null;
			case ModeldocPackage.EXAMPLE__EXAMPLE_TEST:
				return exampleTest != null;
		}
		return super.eIsSet(featureID);
	}

} //ExampleImpl
