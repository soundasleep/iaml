/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.domain.AbstractDomainObject;
import org.openiaml.model.model.domain.AbstractDomainStore;
import org.openiaml.model.model.domain.DomainPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Domain Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEventTriggers <em>Event Triggers</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getGeneratedElements <em>Generated Elements</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#isOverridden <em>Overridden</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAnnotations <em>EAnnotations</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getInstanceTypeName <em>Instance Type Name</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getETypeParameters <em>EType Parameters</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getESuperTypes <em>ESuper Types</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEOperations <em>EOperations</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllAttributes <em>EAll Attributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllReferences <em>EAll References</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEReferences <em>EReferences</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAttributes <em>EAttributes</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllContainments <em>EAll Containments</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllOperations <em>EAll Operations</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllStructuralFeatures <em>EAll Structural Features</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllSuperTypes <em>EAll Super Types</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEIDAttribute <em>EID Attribute</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEStructuralFeatures <em>EStructural Features</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEGenericSuperTypes <em>EGeneric Super Types</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getEAllGenericSuperTypes <em>EAll Generic Super Types</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.openiaml.model.model.domain.impl.AbstractDomainStoreImpl#getViews <em>Views</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractDomainStoreImpl extends EClassImpl implements AbstractDomainStore {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> operations;

	/**
	 * The cached value of the '{@link #getEventTriggers() <em>Event Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<EventTrigger> eventTriggers;

	/**
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireEdge> wires;

	/**
	 * The cached value of the '{@link #getGeneratedElements() <em>Generated Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratedElement> generatedElements;

	/**
	 * The default value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverridden() <em>Overridden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverridden()
	 * @generated
	 * @ordered
	 */
	protected boolean overridden = OVERRIDDEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList eAnnotations;

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
	 * The default value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getInstanceClass() <em>Instance Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClass()
	 * @generated
	 * @ordered
	 */
	protected static final Class INSTANCE_CLASS_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final Object DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getInstanceTypeName() <em>Instance Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getETypeParameters() <em>EType Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getETypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList eTypeParameters;

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
	 * The cached value of the '{@link #getESuperTypes() <em>ESuper Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getESuperTypes()
	 * @generated
	 * @ordered
	 */
	protected EList eSuperTypes;

	/**
	 * The cached value of the '{@link #getEOperations() <em>EOperations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEOperations()
	 * @generated
	 * @ordered
	 */
	protected EList eOperations;

	/**
	 * The cached value of the '{@link #getEStructuralFeatures() <em>EStructural Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEStructuralFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList eStructuralFeatures;

	/**
	 * The cached value of the '{@link #getEGenericSuperTypes() <em>EGeneric Super Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEGenericSuperTypes()
	 * @generated
	 * @ordered
	 */
	protected EList eGenericSuperTypes;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractDomainObject> children;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationElementProperty> properties;

	/**
	 * The cached value of the '{@link #getViews() <em>Views</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViews()
	 * @generated
	 * @ordered
	 */
	protected EList<DerivedView> views;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractDomainStoreImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DomainPackage.Literals.ABSTRACT_DOMAIN_STORE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventTrigger> getEventTriggers() {
		if (eventTriggers == null) {
			eventTriggers = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS);
		}
		return eventTriggers;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.ABSTRACT_DOMAIN_STORE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstanceClassName() {
		// TODO: implement this method to return the 'Instance Class Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceClassName(String newInstanceClassName) {
		// TODO: implement this method to set the 'Instance Class Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInstanceClassName() {
		// TODO: implement this method to unset the 'Instance Class Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInstanceClassName() {
		// TODO: implement this method to return whether the 'Instance Class Name' attribute is set
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Class getInstanceClass() {
		// TODO: implement this method to return the 'Instance Class' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDefaultValue() {
		// TODO: implement this method to return the 'Default Value' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstanceTypeName() {
		// TODO: implement this method to return the 'Instance Type Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceTypeName(String newInstanceTypeName) {
		// TODO: implement this method to set the 'Instance Type Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInstanceTypeName() {
		// TODO: implement this method to unset the 'Instance Type Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInstanceTypeName() {
		// TODO: implement this method to return whether the 'Instance Type Name' attribute is set
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getEPackage() {
		if (eContainerFeatureID != DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE) return null;
		return (EPackage)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getETypeParameters() {
		if (eTypeParameters == null) {
			eTypeParameters = new EObjectContainmentEList<ETypeParameter>(ETypeParameter.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS);
		}
		return eTypeParameters;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT, oldAbstract, abstract_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getESuperTypes() {
		if (eSuperTypes == null) {
			eSuperTypes = new EObjectResolvingEList.Unsettable<EClass>(EClass.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES);
		}
		return eSuperTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetESuperTypes() {
		if (eSuperTypes != null) ((InternalEList.Unsettable<?>)eSuperTypes).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetESuperTypes() {
		return eSuperTypes != null && ((InternalEList.Unsettable<?>)eSuperTypes).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEOperations() {
		if (eOperations == null) {
			eOperations = new EObjectContainmentWithInverseEList<EOperation>(EOperation.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS, EcorePackage.EOPERATION__ECONTAINING_CLASS);
		}
		return eOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllAttributes() {
		// TODO: implement this method to return the 'EAll Attributes' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllReferences() {
		// TODO: implement this method to return the 'EAll References' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEReferences() {
		// TODO: implement this method to return the 'EReferences' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAttributes() {
		// TODO: implement this method to return the 'EAttributes' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllContainments() {
		// TODO: implement this method to return the 'EAll Containments' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllOperations() {
		// TODO: implement this method to return the 'EAll Operations' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllStructuralFeatures() {
		// TODO: implement this method to return the 'EAll Structural Features' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllSuperTypes() {
		// TODO: implement this method to return the 'EAll Super Types' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEIDAttribute() {
		// TODO: implement this method to return the 'EID Attribute' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEStructuralFeatures() {
		if (eStructuralFeatures == null) {
			eStructuralFeatures = new EObjectContainmentWithInverseEList<EStructuralFeature>(EStructuralFeature.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES, EcorePackage.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS);
		}
		return eStructuralFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEGenericSuperTypes() {
		if (eGenericSuperTypes == null) {
			eGenericSuperTypes = new EObjectContainmentEList.Unsettable<EGenericType>(EGenericType.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES);
		}
		return eGenericSuperTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEGenericSuperTypes() {
		if (eGenericSuperTypes != null) ((InternalEList.Unsettable<?>)eGenericSuperTypes).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEGenericSuperTypes() {
		return eGenericSuperTypes != null && ((InternalEList.Unsettable<?>)eGenericSuperTypes).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAllGenericSuperTypes() {
		// TODO: implement this method to return the 'EAll Generic Super Types' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireEdge> getWires() {
		if (wires == null) {
			wires = new EObjectContainmentEList<WireEdge>(WireEdge.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratedElement> getGeneratedElements() {
		if (generatedElements == null) {
			generatedElements = new EObjectWithInverseResolvingEList<GeneratedElement>(GeneratedElement.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS, ModelPackage.GENERATED_ELEMENT__GENERATED_BY);
		}
		return generatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverridden() {
		return overridden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverridden(boolean newOverridden) {
		boolean oldOverridden = overridden;
		overridden = newOverridden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN, oldOverridden, overridden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEAnnotations() {
		if (eAnnotations == null) {
			eAnnotations = new EObjectContainmentWithInverseEList<EAnnotation>(EAnnotation.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS, EcorePackage.EANNOTATION__EMODEL_ELEMENT);
		}
		return eAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractDomainObject> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<AbstractDomainObject>(AbstractDomainObject.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationElementProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ApplicationElementProperty>(ApplicationElementProperty.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DerivedView> getViews() {
		if (views == null) {
			views = new EObjectResolvingEList<DerivedView>(DerivedView.class, this, DomainPackage.ABSTRACT_DOMAIN_STORE__VIEWS);
		}
		return views;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSuperTypeOf(EClass someClass) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFeatureCount() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getEStructuralFeature(int featureID) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFeatureID(EStructuralFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getEStructuralFeature(String featureName) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstance(Object object) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getClassifierID() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAnnotation getEAnnotation(String source) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneratedElements()).basicAdd(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEAnnotations()).basicAdd(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEOperations()).basicAdd(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEStructuralFeatures()).basicAdd(otherEnd, msgs);
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS:
				return ((InternalEList<?>)getEventTriggers()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES:
				return ((InternalEList<?>)getWires()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				return ((InternalEList<?>)getGeneratedElements()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				return ((InternalEList<?>)getEAnnotations()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE:
				return eBasicSetContainer(null, DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS:
				return ((InternalEList<?>)getETypeParameters()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				return ((InternalEList<?>)getEOperations()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				return ((InternalEList<?>)getEStructuralFeatures()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES:
				return ((InternalEList<?>)getEGenericSuperTypes()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
		switch (eContainerFeatureID) {
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE:
				return eInternalContainer().eInverseRemove(this, EcorePackage.EPACKAGE__ECLASSIFIERS, EPackage.class, msgs);
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS:
				return getOperations();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS:
				return getEventTriggers();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES:
				return getWires();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				return getGeneratedElements();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN:
				return isOverridden() ? Boolean.TRUE : Boolean.FALSE;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				return getEAnnotations();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__NAME:
				return getName();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS:
				return getInstanceClass();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__DEFAULT_VALUE:
				return getDefaultValue();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME:
				return getInstanceTypeName();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE:
				return getEPackage();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS:
				return getETypeParameters();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE:
				return isInterface() ? Boolean.TRUE : Boolean.FALSE;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES:
				return getESuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				return getEOperations();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_ATTRIBUTES:
				return getEAllAttributes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_REFERENCES:
				return getEAllReferences();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EREFERENCES:
				return getEReferences();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EATTRIBUTES:
				return getEAttributes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_CONTAINMENTS:
				return getEAllContainments();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_OPERATIONS:
				return getEAllOperations();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_STRUCTURAL_FEATURES:
				return getEAllStructuralFeatures();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_SUPER_TYPES:
				return getEAllSuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EID_ATTRIBUTE:
				return getEIDAttribute();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				return getEStructuralFeatures();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES:
				return getEGenericSuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_GENERIC_SUPER_TYPES:
				return getEAllGenericSuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN:
				return getChildren();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES:
				return getProperties();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__VIEWS:
				return getViews();
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				getEventTriggers().addAll((Collection<? extends EventTrigger>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireEdge>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				getGeneratedElements().addAll((Collection<? extends GeneratedElement>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN:
				setOverridden(((Boolean)newValue).booleanValue());
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection<? extends EAnnotation>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__NAME:
				setName((String)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME:
				setInstanceTypeName((String)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS:
				getETypeParameters().clear();
				getETypeParameters().addAll((Collection<? extends ETypeParameter>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT:
				setAbstract(((Boolean)newValue).booleanValue());
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE:
				setInterface(((Boolean)newValue).booleanValue());
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES:
				getESuperTypes().clear();
				getESuperTypes().addAll((Collection<? extends EClass>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				getEOperations().clear();
				getEOperations().addAll((Collection<? extends EOperation>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				getEStructuralFeatures().addAll((Collection<? extends EStructuralFeature>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES:
				getEGenericSuperTypes().clear();
				getEGenericSuperTypes().addAll((Collection<? extends EGenericType>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends AbstractDomainObject>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ApplicationElementProperty>)newValue);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__VIEWS:
				getViews().clear();
				getViews().addAll((Collection<? extends DerivedView>)newValue);
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS:
				getOperations().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS:
				getEventTriggers().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES:
				getWires().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				getGeneratedElements().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN:
				setOverridden(OVERRIDDEN_EDEFAULT);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME:
				unsetInstanceClassName();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME:
				unsetInstanceTypeName();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS:
				getETypeParameters().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES:
				unsetESuperTypes();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				getEOperations().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES:
				unsetEGenericSuperTypes();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN:
				getChildren().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES:
				getProperties().clear();
				return;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__VIEWS:
				getViews().clear();
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
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS:
				return eventTriggers != null && !eventTriggers.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES:
				return wires != null && !wires.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS:
				return generatedElements != null && !generatedElements.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN:
				return overridden != OVERRIDDEN_EDEFAULT;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME:
				return isSetInstanceClassName();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS:
				return INSTANCE_CLASS_EDEFAULT == null ? getInstanceClass() != null : !INSTANCE_CLASS_EDEFAULT.equals(getInstanceClass());
			case DomainPackage.ABSTRACT_DOMAIN_STORE__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME:
				return isSetInstanceTypeName();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE:
				return getEPackage() != null;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS:
				return eTypeParameters != null && !eTypeParameters.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE:
				return interface_ != INTERFACE_EDEFAULT;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES:
				return isSetESuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS:
				return eOperations != null && !eOperations.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_ATTRIBUTES:
				return !getEAllAttributes().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_REFERENCES:
				return !getEAllReferences().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EREFERENCES:
				return !getEReferences().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EATTRIBUTES:
				return !getEAttributes().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_CONTAINMENTS:
				return !getEAllContainments().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_OPERATIONS:
				return !getEAllOperations().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_STRUCTURAL_FEATURES:
				return !getEAllStructuralFeatures().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_SUPER_TYPES:
				return !getEAllSuperTypes().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EID_ATTRIBUTE:
				return getEIDAttribute() != null;
			case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES:
				return eStructuralFeatures != null && !eStructuralFeatures.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES:
				return isSetEGenericSuperTypes();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_GENERIC_SUPER_TYPES:
				return !getEAllGenericSuperTypes().isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__CHILDREN:
				return children != null && !children.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case DomainPackage.ABSTRACT_DOMAIN_STORE__VIEWS:
				return views != null && !views.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ContainsEventTriggers.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS: return ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES: return ModelPackage.CONTAINS_WIRES__WIRES;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS: return ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN: return ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == EObject.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EModelElement.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS: return EcorePackage.EMODEL_ELEMENT__EANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == ENamedElement.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__NAME: return EcorePackage.ENAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == EClassifier.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME: return EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS: return EcorePackage.ECLASSIFIER__INSTANCE_CLASS;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__DEFAULT_VALUE: return EcorePackage.ECLASSIFIER__DEFAULT_VALUE;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME: return EcorePackage.ECLASSIFIER__INSTANCE_TYPE_NAME;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE: return EcorePackage.ECLASSIFIER__EPACKAGE;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS: return EcorePackage.ECLASSIFIER__ETYPE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == EClass.class) {
			switch (derivedFeatureID) {
				case DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT: return EcorePackage.ECLASS__ABSTRACT;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE: return EcorePackage.ECLASS__INTERFACE;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES: return EcorePackage.ECLASS__ESUPER_TYPES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS: return EcorePackage.ECLASS__EOPERATIONS;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_ATTRIBUTES: return EcorePackage.ECLASS__EALL_ATTRIBUTES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_REFERENCES: return EcorePackage.ECLASS__EALL_REFERENCES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EREFERENCES: return EcorePackage.ECLASS__EREFERENCES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EATTRIBUTES: return EcorePackage.ECLASS__EATTRIBUTES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_CONTAINMENTS: return EcorePackage.ECLASS__EALL_CONTAINMENTS;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_OPERATIONS: return EcorePackage.ECLASS__EALL_OPERATIONS;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_STRUCTURAL_FEATURES: return EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_SUPER_TYPES: return EcorePackage.ECLASS__EALL_SUPER_TYPES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EID_ATTRIBUTE: return EcorePackage.ECLASS__EID_ATTRIBUTE;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES: return EcorePackage.ECLASS__ESTRUCTURAL_FEATURES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES: return EcorePackage.ECLASS__EGENERIC_SUPER_TYPES;
				case DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_GENERIC_SUPER_TYPES: return EcorePackage.ECLASS__EALL_GENERIC_SUPER_TYPES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ContainsEventTriggers.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS: return DomainPackage.ABSTRACT_DOMAIN_STORE__EVENT_TRIGGERS;
				default: return -1;
			}
		}
		if (baseClass == ContainsWires.class) {
			switch (baseFeatureID) {
				case ModelPackage.CONTAINS_WIRES__WIRES: return DomainPackage.ABSTRACT_DOMAIN_STORE__WIRES;
				default: return -1;
			}
		}
		if (baseClass == GeneratesElements.class) {
			switch (baseFeatureID) {
				case ModelPackage.GENERATES_ELEMENTS__GENERATED_ELEMENTS: return DomainPackage.ABSTRACT_DOMAIN_STORE__GENERATED_ELEMENTS;
				case ModelPackage.GENERATES_ELEMENTS__OVERRIDDEN: return DomainPackage.ABSTRACT_DOMAIN_STORE__OVERRIDDEN;
				default: return -1;
			}
		}
		if (baseClass == EObject.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EModelElement.class) {
			switch (baseFeatureID) {
				case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS: return DomainPackage.ABSTRACT_DOMAIN_STORE__EANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == ENamedElement.class) {
			switch (baseFeatureID) {
				case EcorePackage.ENAMED_ELEMENT__NAME: return DomainPackage.ABSTRACT_DOMAIN_STORE__NAME;
				default: return -1;
			}
		}
		if (baseClass == EClassifier.class) {
			switch (baseFeatureID) {
				case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME: return DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS_NAME;
				case EcorePackage.ECLASSIFIER__INSTANCE_CLASS: return DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_CLASS;
				case EcorePackage.ECLASSIFIER__DEFAULT_VALUE: return DomainPackage.ABSTRACT_DOMAIN_STORE__DEFAULT_VALUE;
				case EcorePackage.ECLASSIFIER__INSTANCE_TYPE_NAME: return DomainPackage.ABSTRACT_DOMAIN_STORE__INSTANCE_TYPE_NAME;
				case EcorePackage.ECLASSIFIER__EPACKAGE: return DomainPackage.ABSTRACT_DOMAIN_STORE__EPACKAGE;
				case EcorePackage.ECLASSIFIER__ETYPE_PARAMETERS: return DomainPackage.ABSTRACT_DOMAIN_STORE__ETYPE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == EClass.class) {
			switch (baseFeatureID) {
				case EcorePackage.ECLASS__ABSTRACT: return DomainPackage.ABSTRACT_DOMAIN_STORE__ABSTRACT;
				case EcorePackage.ECLASS__INTERFACE: return DomainPackage.ABSTRACT_DOMAIN_STORE__INTERFACE;
				case EcorePackage.ECLASS__ESUPER_TYPES: return DomainPackage.ABSTRACT_DOMAIN_STORE__ESUPER_TYPES;
				case EcorePackage.ECLASS__EOPERATIONS: return DomainPackage.ABSTRACT_DOMAIN_STORE__EOPERATIONS;
				case EcorePackage.ECLASS__EALL_ATTRIBUTES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_ATTRIBUTES;
				case EcorePackage.ECLASS__EALL_REFERENCES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_REFERENCES;
				case EcorePackage.ECLASS__EREFERENCES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EREFERENCES;
				case EcorePackage.ECLASS__EATTRIBUTES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EATTRIBUTES;
				case EcorePackage.ECLASS__EALL_CONTAINMENTS: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_CONTAINMENTS;
				case EcorePackage.ECLASS__EALL_OPERATIONS: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_OPERATIONS;
				case EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_STRUCTURAL_FEATURES;
				case EcorePackage.ECLASS__EALL_SUPER_TYPES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_SUPER_TYPES;
				case EcorePackage.ECLASS__EID_ATTRIBUTE: return DomainPackage.ABSTRACT_DOMAIN_STORE__EID_ATTRIBUTE;
				case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES: return DomainPackage.ABSTRACT_DOMAIN_STORE__ESTRUCTURAL_FEATURES;
				case EcorePackage.ECLASS__EGENERIC_SUPER_TYPES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EGENERIC_SUPER_TYPES;
				case EcorePackage.ECLASS__EALL_GENERIC_SUPER_TYPES: return DomainPackage.ABSTRACT_DOMAIN_STORE__EALL_GENERIC_SUPER_TYPES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (overridden: ");
		result.append(overridden);
		result.append(", name: ");
		result.append(name);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", interface: ");
		result.append(interface_);
		result.append(')');
		return result.toString();
	}

} //AbstractDomainStoreImpl
