/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.components.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.components.ComponentsFactory;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.components.LoginHandlerTypes;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentsFactoryImpl extends EFactoryImpl implements ComponentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentsFactory init() {
		try {
			ComponentsFactory theComponentsFactory = (ComponentsFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/model/components"); 
			if (theComponentsFactory != null) {
				return theComponentsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentsFactoryImpl() {
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
			case ComponentsPackage.LOGIN_HANDLER: return createLoginHandler();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ComponentsPackage.LOGIN_HANDLER_TYPES:
				return createLoginHandlerTypesFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ComponentsPackage.LOGIN_HANDLER_TYPES:
				return convertLoginHandlerTypesToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LoginHandler createLoginHandler() {
		LoginHandlerImpl loginHandler = new LoginHandlerImpl();
		generateID(loginHandler);
		return loginHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoginHandlerTypes createLoginHandlerTypesFromString(EDataType eDataType, String initialValue) {
		LoginHandlerTypes result = LoginHandlerTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoginHandlerTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentsPackage getComponentsPackage() {
		return (ComponentsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentsPackage getPackage() {
		return ComponentsPackage.eINSTANCE;
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
} //ComponentsFactoryImpl
