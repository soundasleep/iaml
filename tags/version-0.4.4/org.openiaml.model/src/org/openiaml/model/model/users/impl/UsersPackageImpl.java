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
import org.eclipse.xsd.XSDPackage;
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
import org.openiaml.model.model.users.ProvidesEdgeDestination;
import org.openiaml.model.model.users.ProvidesEdgesSource;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providesEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providesEdgeDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiresEdgesSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiresEdgeDestinationEClass = null;

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

		// Initialize simple dependencies
		XSDPackage.eINSTANCE.eClass();

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
	public EClass getProvidesEdgesSource() {
		return providesEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidesEdgesSource_OutProvidesEdges() {
		return (EReference)providesEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidesEdgeDestination() {
		return providesEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidesEdgeDestination_InProvidesEdges() {
		return (EReference)providesEdgeDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiresEdgesSource() {
		return requiresEdgesSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiresEdgesSource_OutRequiresEdges() {
		return (EReference)requiresEdgesSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiresEdgeDestination() {
		return requiresEdgeDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiresEdgeDestination_InRequiresEdges() {
		return (EReference)requiresEdgeDestinationEClass.getEStructuralFeatures().get(0);
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

		providesEdgesSourceEClass = createEClass(PROVIDES_EDGES_SOURCE);
		createEReference(providesEdgesSourceEClass, PROVIDES_EDGES_SOURCE__OUT_PROVIDES_EDGES);

		providesEdgeDestinationEClass = createEClass(PROVIDES_EDGE_DESTINATION);
		createEReference(providesEdgeDestinationEClass, PROVIDES_EDGE_DESTINATION__IN_PROVIDES_EDGES);

		requiresEdgesSourceEClass = createEClass(REQUIRES_EDGES_SOURCE);
		createEReference(requiresEdgesSourceEClass, REQUIRES_EDGES_SOURCE__OUT_REQUIRES_EDGES);

		requiresEdgeDestinationEClass = createEClass(REQUIRES_EDGE_DESTINATION);
		createEReference(requiresEdgeDestinationEClass, REQUIRES_EDGE_DESTINATION__IN_REQUIRES_EDGES);
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
		WiresPackage theWiresPackage = (WiresPackage)EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		userStoreEClass.getESuperTypes().add(theModelPackage.getDomainStore());
		roleEClass.getESuperTypes().add(theModelPackage.getDomainObject());
		roleEClass.getESuperTypes().add(this.getRequiresEdgeDestination());
		roleEClass.getESuperTypes().add(this.getProvidesEdgesSource());
		permissionEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		permissionEClass.getESuperTypes().add(theWiresPackage.getParameterEdgesSource());
		permissionEClass.getESuperTypes().add(this.getRequiresEdgeDestination());
		permissionEClass.getESuperTypes().add(this.getProvidesEdgeDestination());
		permissionEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		userInstanceEClass.getESuperTypes().add(theModelPackage.getDomainObjectInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(userStoreEClass, UserStore.class, "UserStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserStore_Permissions(), this.getPermission(), null, "permissions", null, 0, -1, UserStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(permissionEClass, Permission.class, "Permission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userInstanceEClass, UserInstance.class, "UserInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providesEdgesSourceEClass, ProvidesEdgesSource.class, "ProvidesEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidesEdgesSource_OutProvidesEdges(), theWiresPackage.getProvidesEdge(), theWiresPackage.getProvidesEdge_From(), "outProvidesEdges", null, 0, -1, ProvidesEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providesEdgeDestinationEClass, ProvidesEdgeDestination.class, "ProvidesEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidesEdgeDestination_InProvidesEdges(), theWiresPackage.getProvidesEdge(), theWiresPackage.getProvidesEdge_To(), "inProvidesEdges", null, 0, -1, ProvidesEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiresEdgesSourceEClass, RequiresEdgesSource.class, "RequiresEdgesSource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequiresEdgesSource_OutRequiresEdges(), theWiresPackage.getRequiresEdge(), theWiresPackage.getRequiresEdge_From(), "outRequiresEdges", null, 0, -1, RequiresEdgesSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiresEdgeDestinationEClass, RequiresEdgeDestination.class, "RequiresEdgeDestination", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequiresEdgeDestination_InRequiresEdges(), theWiresPackage.getRequiresEdge(), theWiresPackage.getRequiresEdge_To(), "inRequiresEdges", null, 0, -1, RequiresEdgeDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create annotations
		// http://openiaml.org/comment
		createCommentAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
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
		addAnnotation
		  (roleEClass, 
		   source, 
		   new String[] {
			 "note", "another option is to create \'extends : Role\' and \'extendedBy : Role\' as EOpposites, and remove ExtendsWire. but this means that this relationship cannot be used as the target or source of any wires (parameters? conditions?)"
		   });						
		addAnnotation
		  (providesEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (providesEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (requiresEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });			
		addAnnotation
		  (requiresEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "comment", "added in 0.4.3"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";			
		addAnnotation
		  (userStoreEClass, 
		   source, 
		   new String[] {
			 "documentation", "Contains {@model Role Roles} and {@model Permission Permissions}."
		   });			
		addAnnotation
		  (roleEClass, 
		   source, 
		   new String[] {
			 "documentation", "A particular Role that a user may have; may be {@model ExtendsEdge inherited} from other Roles."
		   });		
		addAnnotation
		  (permissionEClass, 
		   source, 
		   new String[] {
			 "documentation", "A single Permission that a user may have; may also be {@model ProvidesEdge provided} by a {@model Role}."
		   });		
		addAnnotation
		  (userInstanceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents {@model DomainObjectInstance an instance} of a {@model Role user}."
		   });		
		addAnnotation
		  (providesEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model ProvidesEdge}."
		   });			
		addAnnotation
		  (providesEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model ProvidesEdge}."
		   });			
		addAnnotation
		  (requiresEdgesSourceEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the source of a {@model RequiresEdge}."
		   });			
		addAnnotation
		  (requiresEdgeDestinationEClass, 
		   source, 
		   new String[] {
			 "documentation", "Something which may be used as the destination of a {@model RequiresEdge}."
		   });	
	}

} //UsersPackageImpl
