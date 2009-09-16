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
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModeldocPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Drools Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl#getJavadocs <em>Javadocs</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl#getLine <em>Line</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.DroolsRuleImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DroolsRuleImpl extends ReferenceImpl implements DroolsRule {
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
	protected DroolsRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.DROOLS_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavadocTagElement> getJavadocs() {
		if (javadocs == null) {
			javadocs = new EObjectContainmentWithInverseEList<JavadocTagElement>(JavadocTagElement.class, this, ModeldocPackage.DROOLS_RULE__JAVADOCS, ModeldocPackage.JAVADOC_TAG_ELEMENT__JAVA_PARENT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.DROOLS_RULE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.DROOLS_RULE__LINE, oldLine, line));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DroolsPackage getPackage() {
		if (eContainerFeatureID() != ModeldocPackage.DROOLS_RULE__PACKAGE) return null;
		return (DroolsPackage)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(DroolsPackage newPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPackage, ModeldocPackage.DROOLS_RULE__PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(DroolsPackage newPackage) {
		if (newPackage != eInternalContainer() || (eContainerFeatureID() != ModeldocPackage.DROOLS_RULE__PACKAGE && newPackage != null)) {
			if (EcoreUtil.isAncestor(this, newPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, ModeldocPackage.DROOLS_PACKAGE__RULES, DroolsPackage.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldocPackage.DROOLS_RULE__PACKAGE, newPackage, newPackage));
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getJavadocs()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackage((DroolsPackage)otherEnd, msgs);
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				return ((InternalEList<?>)getJavadocs()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				return basicSetPackage(null, msgs);
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
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				return eInternalContainer().eInverseRemove(this, ModeldocPackage.DROOLS_PACKAGE__RULES, DroolsPackage.class, msgs);
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				return getJavadocs();
			case ModeldocPackage.DROOLS_RULE__NAME:
				return getName();
			case ModeldocPackage.DROOLS_RULE__LINE:
				return getLine();
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				return getPackage();
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				getJavadocs().clear();
				getJavadocs().addAll((Collection<? extends JavadocTagElement>)newValue);
				return;
			case ModeldocPackage.DROOLS_RULE__NAME:
				setName((String)newValue);
				return;
			case ModeldocPackage.DROOLS_RULE__LINE:
				setLine((Integer)newValue);
				return;
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				setPackage((DroolsPackage)newValue);
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				getJavadocs().clear();
				return;
			case ModeldocPackage.DROOLS_RULE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModeldocPackage.DROOLS_RULE__LINE:
				setLine(LINE_EDEFAULT);
				return;
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				setPackage((DroolsPackage)null);
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
			case ModeldocPackage.DROOLS_RULE__JAVADOCS:
				return javadocs != null && !javadocs.isEmpty();
			case ModeldocPackage.DROOLS_RULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModeldocPackage.DROOLS_RULE__LINE:
				return line != LINE_EDEFAULT;
			case ModeldocPackage.DROOLS_RULE__PACKAGE:
				return getPackage() != null;
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
				case ModeldocPackage.DROOLS_RULE__JAVADOCS: return ModeldocPackage.JAVA_ELEMENT__JAVADOCS;
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
				case ModeldocPackage.JAVA_ELEMENT__JAVADOCS: return ModeldocPackage.DROOLS_RULE__JAVADOCS;
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
		result.append(", line: ");
		result.append(line);
		result.append(')');
		return result.toString();
	}

} //DroolsRuleImpl
