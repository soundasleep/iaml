/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.scopes.Scope;
import org.openiaml.model.model.scopes.ScopesFactory;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.Session;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScopesFactoryImpl extends EFactoryImpl implements ScopesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScopesFactory init() {
		try {
			ScopesFactory theScopesFactory = (ScopesFactory)EPackage.Registry.INSTANCE.getEFactory("http://openiaml.org/model/scopes"); 
			if (theScopesFactory != null) {
				return theScopesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScopesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopesFactoryImpl() {
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
			case ScopesPackage.SESSION: return createSession();
			case ScopesPackage.SCOPE: return createScope();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Session createSession() {
		SessionImpl session = new SessionImpl();
		generateID(session);
		return session;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		generateID(scope);
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopesPackage getScopesPackage() {
		return (ScopesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ScopesPackage getPackage() {
		return ScopesPackage.eINSTANCE;
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
} //ScopesFactoryImpl
