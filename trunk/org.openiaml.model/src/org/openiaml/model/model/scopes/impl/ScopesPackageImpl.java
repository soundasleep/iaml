/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes.impl;

import org.eclipse.emf.ecore.EAttribute;
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
import org.openiaml.model.model.scopes.Email;
import org.openiaml.model.model.scopes.ScopesFactory;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.users.impl.UsersPackageImpl;
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
public class ScopesPackageImpl extends EPackageImpl implements ScopesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sessionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emailEClass = null;

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
	 * @see org.openiaml.model.model.scopes.ScopesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ScopesPackageImpl() {
		super(eNS_URI, ScopesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ScopesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ScopesPackage init() {
		if (isInited) return (ScopesPackage)EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI);

		// Obtain or create and register package
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ScopesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XSDPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI) : VisualPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) : UsersPackage.eINSTANCE);

		// Create package meta-data objects
		theScopesPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theVisualPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theUsersPackage.createPackageContents();

		// Initialize created meta-data
		theScopesPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theVisualPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theUsersPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theScopesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ScopesPackage.eNS_URI, theScopesPackage);
		return theScopesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSession() {
		return sessionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmail() {
		return emailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmail_To() {
		return (EAttribute)emailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmail_Subject() {
		return (EAttribute)emailEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmail_ToName() {
		return (EAttribute)emailEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmail_From() {
		return (EAttribute)emailEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmail_FromName() {
		return (EAttribute)emailEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEmail_OnSent() {
		return (EReference)emailEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEmail_OnFailure() {
		return (EReference)emailEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEmail_Buttons() {
		return (EReference)emailEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEmail_Labels() {
		return (EReference)emailEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopesFactory getScopesFactory() {
		return (ScopesFactory)getEFactoryInstance();
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
		sessionEClass = createEClass(SESSION);

		emailEClass = createEClass(EMAIL);
		createEAttribute(emailEClass, EMAIL__TO);
		createEAttribute(emailEClass, EMAIL__SUBJECT);
		createEAttribute(emailEClass, EMAIL__TO_NAME);
		createEAttribute(emailEClass, EMAIL__FROM);
		createEAttribute(emailEClass, EMAIL__FROM_NAME);
		createEReference(emailEClass, EMAIL__ON_SENT);
		createEReference(emailEClass, EMAIL__ON_FAILURE);
		createEReference(emailEClass, EMAIL__BUTTONS);
		createEReference(emailEClass, EMAIL__LABELS);
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
		VisualPackage theVisualPackage = (VisualPackage)EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		sessionEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		sessionEClass.getESuperTypes().add(theModelPackage.getContainsOperations());
		sessionEClass.getESuperTypes().add(theModelPackage.getScope());
		sessionEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		sessionEClass.getESuperTypes().add(theModelPackage.getWireSource());
		sessionEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		sessionEClass.getESuperTypes().add(theModelPackage.getContainsConditions());
		emailEClass.getESuperTypes().add(theModelPackage.getNamedElement());
		emailEClass.getESuperTypes().add(theModelPackage.getContainsOperations());
		emailEClass.getESuperTypes().add(theModelPackage.getScope());
		emailEClass.getESuperTypes().add(theModelPackage.getContainsWires());
		emailEClass.getESuperTypes().add(theModelPackage.getWireSource());
		emailEClass.getESuperTypes().add(theModelPackage.getWireDestination());
		emailEClass.getESuperTypes().add(theModelPackage.getContainsConditions());
		emailEClass.getESuperTypes().add(theModelPackage.getContainsProperties());
		emailEClass.getESuperTypes().add(theModelPackage.getCanBeSynced());

		// Initialize classes and features; add operations and parameters
		initEClass(sessionEClass, Session.class, "Session", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(emailEClass, Email.class, "Email", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEmail_To(), ecorePackage.getEString(), "to", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEmail_Subject(), ecorePackage.getEString(), "subject", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEmail_ToName(), ecorePackage.getEString(), "toName", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEmail_From(), ecorePackage.getEString(), "from", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEmail_FromName(), ecorePackage.getEString(), "fromName", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEmail_OnSent(), theModelPackage.getEventTrigger(), null, "onSent", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEmail_OnFailure(), theModelPackage.getEventTrigger(), null, "onFailure", null, 0, 1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEmail_Buttons(), theVisualPackage.getButton(), null, "buttons", null, 0, -1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEmail_Labels(), theVisualPackage.getLabel(), null, "labels", null, 0, -1, Email.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
			 "added", "0.2",
			 "comment", "other scopes: iaml:InternetApplication, [...], iaml.visual:Page, iaml.visual:Frame, iaml:ApplicationElement, iaml:Operation",
			 "comment2", "(it makes sense to put these scopes in here, because they are scoped in terms of visual terms)",
			 "comment3", "some scopes aren\'t here: Host, Organisation, we can add these in later."
		   });		
		addAnnotation
		  (sessionEClass, 
		   source, 
		   new String[] {
			 "changed", "0.4: no longer extends VisibleThing; now extends ContainsEventTriggers, ContainsConditions, WireEdgesSource and WireEdgeDestination\r\n0.4.2 removed \'agents\' reference"
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
		  (sessionEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a user session; contained data is normally not accessible by other users."
		   });		
		addAnnotation
		  (emailEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents an e-mail. The lifecycle is as follows: {@model PrimitiveOperation send} -> {@model EventTrigger sent} or {@model EventTrigger failed}."
		   });
	}

} //ScopesPackageImpl
