/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.Metric;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.Reference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Documentation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.impl.ModelDocumentationImpl#getMetrics <em>Metrics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelDocumentationImpl extends EObjectImpl implements ModelDocumentation {
	/**
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<EMFClass> classes;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<Reference> references;

	/**
	 * The cached value of the '{@link #getMetrics() <em>Metrics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetrics()
	 * @generated
	 * @ordered
	 */
	protected EList<Metric> metrics;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelDocumentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldocPackage.Literals.MODEL_DOCUMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMFClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentWithInverseEList<EMFClass>(EMFClass.class, this, ModeldocPackage.MODEL_DOCUMENTATION__CLASSES, ModeldocPackage.EMF_CLASS__PARENT);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Reference> getReferences() {
		if (references == null) {
			references = new EObjectContainmentWithInverseEList<Reference>(Reference.class, this, ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES, ModeldocPackage.REFERENCE__PARENT);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Metric> getMetrics() {
		if (metrics == null) {
			metrics = new EObjectContainmentEList<Metric>(Metric.class, this, ModeldocPackage.MODEL_DOCUMENTATION__METRICS);
		}
		return metrics;
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
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClasses()).basicAdd(otherEnd, msgs);
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
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
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
			case ModeldocPackage.MODEL_DOCUMENTATION__METRICS:
				return ((InternalEList<?>)getMetrics()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				return getClasses();
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
				return getReferences();
			case ModeldocPackage.MODEL_DOCUMENTATION__METRICS:
				return getMetrics();
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
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends EMFClass>)newValue);
				return;
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection<? extends Reference>)newValue);
				return;
			case ModeldocPackage.MODEL_DOCUMENTATION__METRICS:
				getMetrics().clear();
				getMetrics().addAll((Collection<? extends Metric>)newValue);
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
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				getClasses().clear();
				return;
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
				getReferences().clear();
				return;
			case ModeldocPackage.MODEL_DOCUMENTATION__METRICS:
				getMetrics().clear();
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
			case ModeldocPackage.MODEL_DOCUMENTATION__CLASSES:
				return classes != null && !classes.isEmpty();
			case ModeldocPackage.MODEL_DOCUMENTATION__REFERENCES:
				return references != null && !references.isEmpty();
			case ModeldocPackage.MODEL_DOCUMENTATION__METRICS:
				return metrics != null && !metrics.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelDocumentationImpl
