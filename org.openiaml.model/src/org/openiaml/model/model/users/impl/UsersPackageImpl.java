/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.users.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.impl.ComponentsPackageImpl;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.impl.DomainPackageImpl;
import org.openiaml.model.model.impl.ModelPackageImpl;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.impl.OperationsPackageImpl;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.impl.ScopesPackageImpl;
import org.openiaml.model.model.users.Permission;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.users.UsersFactory;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.visual.impl.VisualPackageImpl;
import org.openiaml.model.model.wires.WiresPackage;
import org.openiaml.model.model.wires.impl.WiresPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UsersPackageImpl extends EPackageImpl implements UsersPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userStoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass permissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInstanceEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.openiaml.model.model.users.UsersPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UsersPackageImpl() {
		super(eNS_URI, UsersFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UsersPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UsersPackage init() {
		if (isInited) return (UsersPackage)EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI);

		// Obtain or create and register package
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UsersPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) : ScopesPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);

		// Create package meta-data objects
		theUsersPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theScopesPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();

		// Initialize created meta-data
		theUsersPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theScopesPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUsersPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UsersPackage.eNS_URI, theUsersPackage);
		return theUsersPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserStore() {
		return userStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserStore_Permissions() {
		return (EReference)userStoreEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPermission() {
		return permissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInstance() {
		return userInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsersFactory getUsersFactory() {
		return (UsersFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		userStoreEClass = createEClass(USER_STORE);
		createEReference(userStoreEClass, USER_STORE__PERMISSIONS);

		roleEClass = createEClass(ROLE);

		permissionEClass = createEClass(PERMISSION);

		userInstanceEClass = createEClass(USER_INSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		userStoreEClass.getESuperTypes().add(theModelPackage.getDomainStore());
		roleEClass.getESuperTypes().add(theModelPackage.getDomainObject());
		permissionEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		permissionEClass.getESuperTypes().add(theModelPackage.getWireEdgesSource());
		permissionEClass.getESuperTypes().add(theModelPackage.getWireEdgeDestination());
		userInstanceEClass.getESuperTypes().add(theModelPackage.getDomainObjectInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(userStoreEClass, UserStore.class, "UserStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserStore_Permissions(), this.getPermission(), null, "permissions", null, 0, -1, UserStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(permissionEClass, Permission.class, "Permission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userInstanceEClass, UserInstance.class, "UserInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create annotations
		// http://openiaml.org/comment
		createCommentAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://openiaml.org/comment</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCommentAnnotations() {
		String source = "http://openiaml.org/comment";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "added", "0.4"
		   });
	}

} //UsersPackageImpl
