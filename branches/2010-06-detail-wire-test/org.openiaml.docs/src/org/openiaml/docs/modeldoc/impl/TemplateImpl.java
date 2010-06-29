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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.Template;
import org.openiaml.docs.modeldoc.TemplateFile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.TemplateImpl#getJavadocs <em>Javadocs</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.TemplateImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.TemplateImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.TemplateImpl#getLine <em>Line</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.TemplateImpl#getTemplateFile <em>Template File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateImpl extends ReferenceImpl implements Template {
	/**
	 * The cached value of the '{@link #getJavadocs() <em>Javadocs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavadocs()
	 * @generated
	 * @ordered
	 */
	protected EList<JavadocTagElement> javadocs;

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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected int line = LINE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.TEMPLATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavadocTagElement> getJavadocs() {
		if (javadocs == null) {
			javadocs = new EObjectContainmentWithInverseEList<JavadocTagElement>(JavadocTagElement.class, this, ModeldocPackage.TEMPLATE__JAVADOCS, ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT);
		}
		return javadocs;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.TEMPLATE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.TEMPLATE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLine() {
		return line;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLine(int newLine) {
		int oldLine = line;
		line = newLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.TEMPLATE__LINE, oldLine, line));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateFile getTemplateFile() {
		if (eContainerFeatureID() != ModeldocPackage.TEMPLATE__TEMPLATE_FILE) return null;
		return (TemplateFile)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTemplateFile(TemplateFile newTemplateFile, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTemplateFile, ModeldocPackage.TEMPLATE__TEMPLATE_FILE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplateFile(TemplateFile newTemplateFile) {
		if (newTemplateFile != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.TEMPLATE__TEMPLATE_FILE && newTemplateFile != null)) {
			if (EcoreUtil.isAncestor(this, newTemplateFile))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTemplateFile != null)
				msgs = ((InternalEObject)newTemplateFile).eInverseAdd(this, ModeldocPackage.TEMPLATE_FILE__TEMPLATES, TemplateFile.class, msgs);
			msgs = basicSetTemplateFile(newTemplateFile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.TEMPLATE__TEMPLATE_FILE, newTemplateFile, newTemplateFile));
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getJavadocs()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTemplateFile((TemplateFile)otherEnd, msgs);
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				return ((InternalEList<?>)getJavadocs()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				return basicSetTemplateFile(null, msgs);
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
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.TEMPLATE_FILE__TEMPLATES, TemplateFile.class, msgs);
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				return getJavadocs();
			case ModeldocPackage.TEMPLATE__NAME:
				return getName();
			case ModeldocPackage.TEMPLATE__TYPE:
				return getType();
			case ModeldocPackage.TEMPLATE__LINE:
				return getLine();
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				return getTemplateFile();
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				getJavadocs().clear();
				getJavadocs().addAll((Collection<? extends JavadocTagElement>)newValue);
				return;
			case ModeldocPackage.TEMPLATE__NAME:
				setName((String)newValue);
				return;
			case ModeldocPackage.TEMPLATE__TYPE:
				setType((String)newValue);
				return;
			case ModeldocPackage.TEMPLATE__LINE:
				setLine((Integer)newValue);
				return;
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				setTemplateFile((TemplateFile)newValue);
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				getJavadocs().clear();
				return;
			case ModeldocPackage.TEMPLATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModeldocPackage.TEMPLATE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ModeldocPackage.TEMPLATE__LINE:
				setLine(LINE_EDEFAULT);
				return;
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				setTemplateFile((TemplateFile)null);
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
			case ModeldocPackage.TEMPLATE__JAVADOCS:
				return javadocs != null && !javadocs.isEmpty();
			case ModeldocPackage.TEMPLATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModeldocPackage.TEMPLATE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ModeldocPackage.TEMPLATE__LINE:
				return line != LINE_EDEFAULT;
			case ModeldocPackage.TEMPLATE__TEMPLATE_FILE:
				return getTemplateFile() != null;
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
		if (baseClass == JavaElement.class) {
			switch (derivedFeatureID) {
				case ModeldocPackage.TEMPLATE__JAVADOCS: return ModeldocPackage.JAVA_ELEMENT__JAVADOCS;
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
		if (baseClass == JavaElement.class) {
			switch (baseFeatureID) {
				case ModeldocPackage.JAVA_ELEMENT__JAVADOCS: return ModeldocPackage.TEMPLATE__JAVADOCS;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", line: ");
		result.append(line);
		result.append(')');
		return result.toString();
	}

} //TemplateImpl
