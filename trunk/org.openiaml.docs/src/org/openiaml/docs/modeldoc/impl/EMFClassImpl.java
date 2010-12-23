/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.docs.modeldoc.AdditionalDocumentation;
import org.openiaml.docs.modeldoc.AdditionalLatex;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.EMFReference;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.ImplementationNote;
import org.openiaml.docs.modeldoc.InferenceSemantic;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.OperationalSemantic;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMF Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getTargetClass <em>Target Class</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getSupertypes <em>Supertypes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getSubtypes <em>Subtypes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getRuntimeClass <em>Runtime Class</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getTagline <em>Tagline</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getOperationalSemantics <em>Operational Semantics</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getInferenceSemantics <em>Inference Semantics</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getImplementationNotes <em>Implementation Notes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getGmfEditor <em>Gmf Editor</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getExamples <em>Examples</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getAdditionalDocumentation <em>Additional Documentation</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getAdditionalLatex <em>Additional Latex</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.EMFClassImpl#getRationale <em>Rationale</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EMFClassImpl extends EObjectImpl implements EMFClass {
	/**
	 * The cached value of the '{@link #getTargetClass() <em>Target Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetClass()
	 * @generated
	 * @ordered
	 */
	protected EClass targetClass;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;
	/**
	 * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERFACE_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected boolean interface_ = INTERFACE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getSupertypes() <em>Supertypes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupertypes()
	 * @generated
	 * @ordered
	 */
	protected EList<EMFClass> supertypes;
	/**
	 * The cached value of the '{@link #getSubtypes() <em>Subtypes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubtypes()
	 * @generated
	 * @ordered
	 */
	protected EList<EMFClass> subtypes;
	/**
	 * The cached value of the '{@link #getRuntimeClass() <em>Runtime Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass runtimeClass;
	/**
	 * The cached value of the '{@link #getTagline() <em>Tagline</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagline()
	 * @generated
	 * @ordered
	 */
	protected JavadocTagElement tagline;
	/**
	 * The cached value of the '{@link #getOperationalSemantics() <em>Operational Semantics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationalSemantics()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationalSemantic> operationalSemantics;
	/**
	 * The cached value of the '{@link #getInferenceSemantics() <em>Inference Semantics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInferenceSemantics()
	 * @generated
	 * @ordered
	 */
	protected EList<InferenceSemantic> inferenceSemantics;
	/**
	 * The cached value of the '{@link #getImplementationNotes() <em>Implementation Notes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationNotes()
	 * @generated
	 * @ordered
	 */
	protected EList<ImplementationNote> implementationNotes;
	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> constraints;
	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelExtension> extensions;
	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected GraphicalRepresentation icon;
	/**
	 * The cached value of the '{@link #getGmfEditor() <em>Gmf Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGmfEditor()
	 * @generated
	 * @ordered
	 */
	protected GraphicalRepresentation gmfEditor;
	/**
	 * The cached value of the '{@link #getExamples() <em>Examples</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExamples()
	 * @generated
	 * @ordered
	 */
	protected EList<Example> examples;
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<EMFAttribute> attributes;
	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<EMFReference> references;
	/**
	 * The cached value of the '{@link #getAdditionalDocumentation() <em>Additional Documentation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalDocumentation()
	 * @generated
	 * @ordered
	 */
	protected EList<AdditionalDocumentation> additionalDocumentation;
	/**
	 * The cached value of the '{@link #getAdditionalLatex() <em>Additional Latex</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalLatex()
	 * @generated
	 * @ordered
	 */
	protected EList<AdditionalLatex> additionalLatex;
	/**
	 * The cached value of the '{@link #getRationale() <em>Rationale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRationale()
	 * @generated
	 * @ordered
	 */
	protected JavadocTagElement rationale;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMFClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.EMF_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTargetClass() {
		if (targetClass != null && targetClass.eIsProxy()) {
			InternalEObject oldTargetClass = (InternalEObject)targetClass;
			targetClass = (EClass)eResolveProxy(oldTargetClass);
			if (targetClass != oldTargetClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.EMF_CLASS__TARGET_CLASS, oldTargetClass, targetClass));
			}
		}
		return targetClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetTargetClass() {
		return targetClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetClass(EClass newTargetClass) {
		EClass oldTargetClass = targetClass;
		targetClass = newTargetClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__TARGET_CLASS, oldTargetClass, targetClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(boolean newInterface) {
		boolean oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMFClass> getSupertypes() {
		if (supertypes == null) {
			supertypes = new EObjectWithInverseResolvingEList.ManyInverse<EMFClass>(EMFClass.class, this, ModeldocPackage.EMF_CLASS__SUPERTYPES, ModeldocPackage.EMF_CLASS__SUBTYPES);
		}
		return supertypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMFClass> getSubtypes() {
		if (subtypes == null) {
			subtypes = new EObjectWithInverseResolvingEList.ManyInverse<EMFClass>(EMFClass.class, this, ModeldocPackage.EMF_CLASS__SUBTYPES, ModeldocPackage.EMF_CLASS__SUPERTYPES);
		}
		return subtypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getRuntimeClass() {
		if (runtimeClass != null && runtimeClass.eIsProxy()) {
			InternalEObject oldRuntimeClass = (InternalEObject)runtimeClass;
			runtimeClass = (JavaClass)eResolveProxy(oldRuntimeClass);
			if (runtimeClass != oldRuntimeClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldocPackage.EMF_CLASS__RUNTIME_CLASS, oldRuntimeClass, runtimeClass));
			}
		}
		return runtimeClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetRuntimeClass() {
		return runtimeClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuntimeClass(JavaClass newRuntimeClass) {
		JavaClass oldRuntimeClass = runtimeClass;
		runtimeClass = newRuntimeClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__RUNTIME_CLASS, oldRuntimeClass, runtimeClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocTagElement getTagline() {
		return tagline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTagline(JavadocTagElement newTagline, NotificationChain msgs) {
		JavadocTagElement oldTagline = tagline;
		tagline = newTagline;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__TAGLINE, oldTagline, newTagline);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagline(JavadocTagElement newTagline) {
		if (newTagline != tagline) {
			NotificationChain msgs = null;
			if (tagline != null)
				msgs = ((InternalEObject)tagline).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__TAGLINE, null, msgs);
			if (newTagline != null)
				msgs = ((InternalEObject)newTagline).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__TAGLINE, null, msgs);
			msgs = basicSetTagline(newTagline, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__TAGLINE, newTagline, newTagline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationalSemantic> getOperationalSemantics() {
		if (operationalSemantics == null) {
			operationalSemantics = new EObjectContainmentWithInverseEList<OperationalSemantic>(OperationalSemantic.class, this, ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS, ModeldocPackage.OPERATIONAL_SEMANTIC__CONTAINING_CLASS);
		}
		return operationalSemantics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InferenceSemantic> getInferenceSemantics() {
		if (inferenceSemantics == null) {
			inferenceSemantics = new EObjectContainmentWithInverseEList<InferenceSemantic>(InferenceSemantic.class, this, ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS, ModeldocPackage.INFERENCE_SEMANTIC__CONTAINING_CLASS);
		}
		return inferenceSemantics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this, ModeldocPackage.EMF_CLASS__CONSTRAINTS, ModeldocPackage.CONSTRAINT__CONTAINING_CLASS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelExtension> getExtensions() {
		if (extensions == null) {
			extensions = new EObjectContainmentWithInverseEList<ModelExtension>(ModelExtension.class, this, ModeldocPackage.EMF_CLASS__EXTENSIONS, ModeldocPackage.MODEL_EXTENSION__CONTAINING_CLASS);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicalRepresentation getIcon() {
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIcon(GraphicalRepresentation newIcon, NotificationChain msgs) {
		GraphicalRepresentation oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__ICON, oldIcon, newIcon);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(GraphicalRepresentation newIcon) {
		if (newIcon != icon) {
			NotificationChain msgs = null;
			if (icon != null)
				msgs = ((InternalEObject)icon).eInverseRemove(this, ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS, GraphicalRepresentation.class, msgs);
			if (newIcon != null)
				msgs = ((InternalEObject)newIcon).eInverseAdd(this, ModeldocPackage.GRAPHICAL_REPRESENTATION__CONTAINING_CLASS, GraphicalRepresentation.class, msgs);
			msgs = basicSetIcon(newIcon, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__ICON, newIcon, newIcon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicalRepresentation getGmfEditor() {
		return gmfEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGmfEditor(GraphicalRepresentation newGmfEditor, NotificationChain msgs) {
		GraphicalRepresentation oldGmfEditor = gmfEditor;
		gmfEditor = newGmfEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__GMF_EDITOR, oldGmfEditor, newGmfEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGmfEditor(GraphicalRepresentation newGmfEditor) {
		if (newGmfEditor != gmfEditor) {
			NotificationChain msgs = null;
			if (gmfEditor != null)
				msgs = ((InternalEObject)gmfEditor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__GMF_EDITOR, null, msgs);
			if (newGmfEditor != null)
				msgs = ((InternalEObject)newGmfEditor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__GMF_EDITOR, null, msgs);
			msgs = basicSetGmfEditor(newGmfEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__GMF_EDITOR, newGmfEditor, newGmfEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Example> getExamples() {
		if (examples == null) {
			examples = new EObjectContainmentWithInverseEList<Example>(Example.class, this, ModeldocPackage.EMF_CLASS__EXAMPLES, ModeldocPackage.EXAMPLE__CONTAINING_CLASS);
		}
		return examples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelDocumentation getParent() {
		if (eContainerFeatureID() != ModeldocPackage.EMF_CLASS__PARENT) return null;
		return (ModelDocumentation)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ModelDocumentation newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, ModeldocPackage.EMF_CLASS__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ModelDocumentation newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.EMF_CLASS__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ModeldocPackage.MODEL_DOCUMENTATION__CLASSES, ModelDocumentation.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMFAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentWithInverseEList<EMFAttribute>(EMFAttribute.class, this, ModeldocPackage.EMF_CLASS__ATTRIBUTES, ModeldocPackage.EMF_ATTRIBUTE__CONTAINING_TYPE);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMFReference> getReferences() {
		if (references == null) {
			references = new EObjectContainmentWithInverseEList<EMFReference>(EMFReference.class, this, ModeldocPackage.EMF_CLASS__REFERENCES, ModeldocPackage.EMF_REFERENCE__CONTAINING_TYPE);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AdditionalDocumentation> getAdditionalDocumentation() {
		if (additionalDocumentation == null) {
			additionalDocumentation = new EObjectContainmentEList<AdditionalDocumentation>(AdditionalDocumentation.class, this, ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION);
		}
		return additionalDocumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AdditionalLatex> getAdditionalLatex() {
		if (additionalLatex == null) {
			additionalLatex = new EObjectContainmentEList<AdditionalLatex>(AdditionalLatex.class, this, ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX);
		}
		return additionalLatex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavadocTagElement getRationale() {
		return rationale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRationale(JavadocTagElement newRationale, NotificationChain msgs) {
		JavadocTagElement oldRationale = rationale;
		rationale = newRationale;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__RATIONALE, oldRationale, newRationale);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRationale(JavadocTagElement newRationale) {
		if (newRationale != rationale) {
			NotificationChain msgs = null;
			if (rationale != null)
				msgs = ((InternalEObject)rationale).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__RATIONALE, null, msgs);
			if (newRationale != null)
				msgs = ((InternalEObject)newRationale).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__RATIONALE, null, msgs);
			msgs = basicSetRationale(newRationale, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.EMF_CLASS__RATIONALE, newRationale, newRationale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImplementationNote> getImplementationNotes() {
		if (implementationNotes == null) {
			implementationNotes = new EObjectContainmentWithInverseEList<ImplementationNote>(ImplementationNote.class, this, ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES, ModeldocPackage.IMPLEMENTATION_NOTE__CONTAINING_CLASS);
		}
		return implementationNotes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSupertypes()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubtypes()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperationalSemantics()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInferenceSemantics()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImplementationNotes()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConstraints()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__ICON:
				if (icon != null)
					msgs = ((InternalEObject)icon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeldocPackage.EMF_CLASS__ICON, null, msgs);
				return basicSetIcon((GraphicalRepresentation)otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExamples()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((ModelDocumentation)otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAttributes()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferences()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				return ((InternalEList<?>)getSupertypes()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				return ((InternalEList<?>)getSubtypes()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__TAGLINE:
				return basicSetTagline(null, msgs);
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				return ((InternalEList<?>)getOperationalSemantics()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				return ((InternalEList<?>)getInferenceSemantics()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				return ((InternalEList<?>)getImplementationNotes()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__ICON:
				return basicSetIcon(null, msgs);
			case ModeldocPackage.EMF_CLASS__GMF_EDITOR:
				return basicSetGmfEditor(null, msgs);
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				return ((InternalEList<?>)getExamples()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__PARENT:
				return basicSetParent(null, msgs);
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION:
				return ((InternalEList<?>)getAdditionalDocumentation()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX:
				return ((InternalEList<?>)getAdditionalLatex()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.EMF_CLASS__RATIONALE:
				return basicSetRationale(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ModeldocPackage.EMF_CLASS__PARENT:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.MODEL_DOCUMENTATION__CLASSES, ModelDocumentation.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldocPackage.EMF_CLASS__TARGET_CLASS:
				if (resolve) return getTargetClass();
				return basicGetTargetClass();
			case ModeldocPackage.EMF_CLASS__NAME:
				return getName();
			case ModeldocPackage.EMF_CLASS__ABSTRACT:
				return isAbstract();
			case ModeldocPackage.EMF_CLASS__INTERFACE:
				return isInterface();
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				return getSupertypes();
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				return getSubtypes();
			case ModeldocPackage.EMF_CLASS__RUNTIME_CLASS:
				if (resolve) return getRuntimeClass();
				return basicGetRuntimeClass();
			case ModeldocPackage.EMF_CLASS__TAGLINE:
				return getTagline();
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				return getOperationalSemantics();
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				return getInferenceSemantics();
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				return getImplementationNotes();
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				return getConstraints();
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				return getExtensions();
			case ModeldocPackage.EMF_CLASS__ICON:
				return getIcon();
			case ModeldocPackage.EMF_CLASS__GMF_EDITOR:
				return getGmfEditor();
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				return getExamples();
			case ModeldocPackage.EMF_CLASS__PARENT:
				return getParent();
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				return getAttributes();
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				return getReferences();
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION:
				return getAdditionalDocumentation();
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX:
				return getAdditionalLatex();
			case ModeldocPackage.EMF_CLASS__RATIONALE:
				return getRationale();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModeldocPackage.EMF_CLASS__TARGET_CLASS:
				setTargetClass((EClass)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__NAME:
				setName((String)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__INTERFACE:
				setInterface((Boolean)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				getSupertypes().clear();
				getSupertypes().addAll((Collection<? extends EMFClass>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				getSubtypes().clear();
				getSubtypes().addAll((Collection<? extends EMFClass>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__RUNTIME_CLASS:
				setRuntimeClass((JavaClass)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__TAGLINE:
				setTagline((JavadocTagElement)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				getOperationalSemantics().clear();
				getOperationalSemantics().addAll((Collection<? extends OperationalSemantic>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				getInferenceSemantics().clear();
				getInferenceSemantics().addAll((Collection<? extends InferenceSemantic>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				getImplementationNotes().clear();
				getImplementationNotes().addAll((Collection<? extends ImplementationNote>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				getExtensions().clear();
				getExtensions().addAll((Collection<? extends ModelExtension>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__ICON:
				setIcon((GraphicalRepresentation)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__GMF_EDITOR:
				setGmfEditor((GraphicalRepresentation)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				getExamples().clear();
				getExamples().addAll((Collection<? extends Example>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__PARENT:
				setParent((ModelDocumentation)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends EMFAttribute>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection<? extends EMFReference>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION:
				getAdditionalDocumentation().clear();
				getAdditionalDocumentation().addAll((Collection<? extends AdditionalDocumentation>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX:
				getAdditionalLatex().clear();
				getAdditionalLatex().addAll((Collection<? extends AdditionalLatex>)newValue);
				return;
			case ModeldocPackage.EMF_CLASS__RATIONALE:
				setRationale((JavadocTagElement)newValue);
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
			case ModeldocPackage.EMF_CLASS__TARGET_CLASS:
				setTargetClass((EClass)null);
				return;
			case ModeldocPackage.EMF_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModeldocPackage.EMF_CLASS__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case ModeldocPackage.EMF_CLASS__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				getSupertypes().clear();
				return;
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				getSubtypes().clear();
				return;
			case ModeldocPackage.EMF_CLASS__RUNTIME_CLASS:
				setRuntimeClass((JavaClass)null);
				return;
			case ModeldocPackage.EMF_CLASS__TAGLINE:
				setTagline((JavadocTagElement)null);
				return;
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				getOperationalSemantics().clear();
				return;
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				getInferenceSemantics().clear();
				return;
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				getImplementationNotes().clear();
				return;
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				getConstraints().clear();
				return;
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				getExtensions().clear();
				return;
			case ModeldocPackage.EMF_CLASS__ICON:
				setIcon((GraphicalRepresentation)null);
				return;
			case ModeldocPackage.EMF_CLASS__GMF_EDITOR:
				setGmfEditor((GraphicalRepresentation)null);
				return;
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				getExamples().clear();
				return;
			case ModeldocPackage.EMF_CLASS__PARENT:
				setParent((ModelDocumentation)null);
				return;
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				getReferences().clear();
				return;
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION:
				getAdditionalDocumentation().clear();
				return;
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX:
				getAdditionalLatex().clear();
				return;
			case ModeldocPackage.EMF_CLASS__RATIONALE:
				setRationale((JavadocTagElement)null);
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
			case ModeldocPackage.EMF_CLASS__TARGET_CLASS:
				return targetClass != null;
			case ModeldocPackage.EMF_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModeldocPackage.EMF_CLASS__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case ModeldocPackage.EMF_CLASS__INTERFACE:
				return interface_ != INTERFACE_EDEFAULT;
			case ModeldocPackage.EMF_CLASS__SUPERTYPES:
				return supertypes != null && !supertypes.isEmpty();
			case ModeldocPackage.EMF_CLASS__SUBTYPES:
				return subtypes != null && !subtypes.isEmpty();
			case ModeldocPackage.EMF_CLASS__RUNTIME_CLASS:
				return runtimeClass != null;
			case ModeldocPackage.EMF_CLASS__TAGLINE:
				return tagline != null;
			case ModeldocPackage.EMF_CLASS__OPERATIONAL_SEMANTICS:
				return operationalSemantics != null && !operationalSemantics.isEmpty();
			case ModeldocPackage.EMF_CLASS__INFERENCE_SEMANTICS:
				return inferenceSemantics != null && !inferenceSemantics.isEmpty();
			case ModeldocPackage.EMF_CLASS__IMPLEMENTATION_NOTES:
				return implementationNotes != null && !implementationNotes.isEmpty();
			case ModeldocPackage.EMF_CLASS__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case ModeldocPackage.EMF_CLASS__EXTENSIONS:
				return extensions != null && !extensions.isEmpty();
			case ModeldocPackage.EMF_CLASS__ICON:
				return icon != null;
			case ModeldocPackage.EMF_CLASS__GMF_EDITOR:
				return gmfEditor != null;
			case ModeldocPackage.EMF_CLASS__EXAMPLES:
				return examples != null && !examples.isEmpty();
			case ModeldocPackage.EMF_CLASS__PARENT:
				return getParent() != null;
			case ModeldocPackage.EMF_CLASS__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ModeldocPackage.EMF_CLASS__REFERENCES:
				return references != null && !references.isEmpty();
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_DOCUMENTATION:
				return additionalDocumentation != null && !additionalDocumentation.isEmpty();
			case ModeldocPackage.EMF_CLASS__ADDITIONAL_LATEX:
				return additionalLatex != null && !additionalLatex.isEmpty();
			case ModeldocPackage.EMF_CLASS__RATIONALE:
				return rationale != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", interface: ");
		result.append(interface_);
		result.append(')');
		return result.toString();
	}

} //EMFClassImpl
