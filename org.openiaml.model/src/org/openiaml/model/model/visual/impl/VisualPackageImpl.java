/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.visual.impl;

import org.eclipse.emf.ecore.EAttribute;
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
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.users.impl.UsersPackageImpl;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.visual.VisualFactory;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.WiresPackage;
import org.openiaml.model.model.wires.impl.WiresPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VisualPackageImpl extends EPackageImpl implements VisualPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass frameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputTextFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buttonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

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
	 * @see org.openiaml.model.model.visual.VisualPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VisualPackageImpl() {
		super(eNS_URI, VisualFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link VisualPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static VisualPackage init() {
		if (isInited) return (VisualPackage)EPackage.Registry.INSTANCE.getEPackage(VisualPackage.eNS_URI);

		// Obtain or create and register package
		VisualPackageImpl theVisualPackage = (VisualPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VisualPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new VisualPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		WiresPackageImpl theWiresPackage = (WiresPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) instanceof WiresPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WiresPackage.eNS_URI) : WiresPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		ScopesPackageImpl theScopesPackage = (ScopesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) instanceof ScopesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScopesPackage.eNS_URI) : ScopesPackage.eINSTANCE);
		ComponentsPackageImpl theComponentsPackage = (ComponentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) : UsersPackage.eINSTANCE);

		// Create package meta-data objects
		theVisualPackage.createPackageContents();
		theModelPackage.createPackageContents();
		theWiresPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theScopesPackage.createPackageContents();
		theComponentsPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theUsersPackage.createPackageContents();

		// Initialize created meta-data
		theVisualPackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theWiresPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theScopesPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theUsersPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVisualPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(VisualPackage.eNS_URI, theVisualPackage);
		return theVisualPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFrame() {
		return frameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFrame_Url() {
		return (EAttribute)frameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFrame_Children() {
		return (EReference)frameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputForm() {
		return inputFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputTextField() {
		return inputTextFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getButton() {
		return buttonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabel_DefaultValue() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisualFactory getVisualFactory() {
		return (VisualFactory)getEFactoryInstance();
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
		frameEClass = createEClass(FRAME);
		createEAttribute(frameEClass, FRAME__URL);
		createEReference(frameEClass, FRAME__CHILDREN);

		inputFormEClass = createEClass(INPUT_FORM);

		inputTextFieldEClass = createEClass(INPUT_TEXT_FIELD);

		buttonEClass = createEClass(BUTTON);

		labelEClass = createEClass(LABEL);
		createEAttribute(labelEClass, LABEL__DEFAULT_VALUE);
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
		frameEClass.getESuperTypes().add(theModelPackage.getScope());
		inputFormEClass.getESuperTypes().add(theModelPackage.getVisibleThing());
		inputTextFieldEClass.getESuperTypes().add(theModelPackage.getVisibleThing());
		buttonEClass.getESuperTypes().add(theModelPackage.getVisibleThing());
		labelEClass.getESuperTypes().add(theModelPackage.getVisibleThing());

		// Initialize classes and features; add operations and parameters
		initEClass(frameEClass, Frame.class, "Frame", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFrame_Url(), ecorePackage.getEString(), "url", null, 0, 1, Frame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFrame_Children(), theModelPackage.getVisibleThing(), null, "children", null, 0, -1, Frame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputFormEClass, InputForm.class, "InputForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(inputTextFieldEClass, InputTextField.class, "InputTextField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(buttonEClass, Button.class, "Button", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_DefaultValue(), ecorePackage.getEString(), "defaultValue", "", 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (frameEClass, 
		   source, 
		   new String[] {
			 "comment", "Scope supertype added in 0.2",
			 "comment2", "represents the scope Window",
			 "changed", "0.4.2 renamed from Page to Frame\r\n0.4.2 no longer extends VisibleThing"
		   });			
		addAnnotation
		  (getFrame_Children(), 
		   source, 
		   new String[] {
			 "added", "0.4.2"
		   });				
		addAnnotation
		  (buttonEClass, 
		   source, 
		   new String[] {
			 "added", "0.2"
		   });			
		addAnnotation
		  (labelEClass, 
		   source, 
		   new String[] {
			 "added", "0.4.3"
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
		  (frameEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a single browser window instance."
		   });			
		addAnnotation
		  (inputFormEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a form which may contain {@link InputTextField text fields} and {@link Button buttons}."
		   });		
		addAnnotation
		  (inputTextFieldEClass, 
		   source, 
		   new String[] {
			 "documentation", "A single text field, storing a single text value, which may be edited by the user."
		   });			
		addAnnotation
		  (buttonEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a clickable button to the user."
		   });			
		addAnnotation
		  (labelEClass, 
		   source, 
		   new String[] {
			 "documentation", "Represents a static block of text that is not user-editable."
		   });
	}

} //VisualPackageImpl
