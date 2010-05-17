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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Javadoc Tag Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.JavadocTagElementImpl#getJavaParent <em>Java Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavadocTagElementImpl extends JavadocFragmentImpl implements JavadocTagElement {
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
	 * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<JavadocFragment> fragments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavadocTagElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.JAVADOC_TAG_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.JAVADOC_TAG_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavadocFragment> getFragments() {
		if (fragments == null) {
			fragments = new EObjectContainmentEList<JavadocFragment>(JavadocFragment.class, this, ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS);
		}
		return fragments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaElement getJavaParent() {
		if (eContainerFeatureID() != ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT) return null;
		return (JavaElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavaParent(JavaElement newJavaParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newJavaParent, ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaParent(JavaElement newJavaParent) {
		if (newJavaParent != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT && newJavaParent != null)) {
			if (EcoreUtil.isAncestor(this, newJavaParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newJavaParent != null)
				msgs = ((InternalEObject)newJavaParent).eInverseAdd(this, ModeldocPackage.JAVA_ELEMENT__JAVADOCS, JavaElement.class, msgs);
			msgs = basicSetJavaParent(newJavaParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT, newJavaParent, newJavaParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetJavaParent((JavaElement)otherEnd, msgs);
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				return basicSetJavaParent(null, msgs);
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.JAVA_ELEMENT__JAVADOCS, JavaElement.class, msgs);
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__NAME:
				return getName();
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS:
				return getFragments();
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				return getJavaParent();
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends JavadocFragment>)newValue);
				return;
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				setJavaParent((JavaElement)newValue);
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS:
				getFragments().clear();
				return;
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				setJavaParent((JavaElement)null);
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
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__FRAGMENTS:
				return fragments != null && !fragments.isEmpty();
			case ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT:
				return getJavaParent() != null;
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
		result.append(')');
		return result.toString();
	}

} //JavadocTagElementImpl
