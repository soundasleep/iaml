/**
 * 
 */
package org.openiaml.model.inference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * an ecore-based inference handler.
 * 
 * TODO I guess this should be placed in a separate package. 
 * @author jmwright
 *
 */
public class EcoreInferenceHandler implements ICreateElements {

	private Resource resource;
	
	/**
	 * @param resource
	 */
	public EcoreInferenceHandler(Resource resource) {
		this.resource = resource;
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {

		// we will just let ecore do it
		element.eSet(reference, value);		
	}

	private EObject createElement(EClass eClass) {
		EFactory ep = resource.getResourceSet().getPackageRegistry().getEFactory(eClass.getEPackage().getNsURI());
		return ep.create(eClass);
	}
	

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject createElement(EObject container, EClass elementType, EStructuralFeature feature)
			throws InferenceException {
		
		// sanity check
		if (container == null) return null;

		Assert.isNotNull(elementType);
		Assert.isNotNull(feature);
		
		EObject object = createElement(elementType);
		
		if (object == null)
			throw new IllegalArgumentException("Unknown class type '" + elementType + "' - am I missing a factory?");
		
		// we now need to set its container
		if (feature instanceof EReference) {
			Object f = container.eGet(feature, false);
			if (f instanceof EList) {
				EList<Object> containerList = (EList<Object>) f;
				try {
					containerList.add(object);
				} catch (ArrayStoreException e) {
					ClassLoader objectCL = object.getClass().getClassLoader();
					ClassLoader containerCL = container.getClass().getClassLoader();
					ClassLoader containerListCL = containerList.getClass().getClassLoader();
					if (objectCL != containerListCL) {
						// this is why they can't be stored: different classLoaders
						System.out.println("object CL: " + objectCL);
						System.out.println("container CL: " + containerCL);
						System.out.println("containerList CL: " + containerListCL);
						throw new InferenceException("the object '" + object + "' cannot be stored in '" + containerList + "' because their classloaders are different.", e);
					}
					throw e;
				}
			} else {
				throw new IllegalArgumentException("I don't know what to do with a resolved EReference of: " + f);
			}
		} else {
			throw new IllegalArgumentException("Cannot do anything with the structural feature: " + feature);
		}
		
		/*
		Object f = container.eGet(feature);
		if (f instanceof EList) {
			EList<Object> containerList = (EList<Object>) f;
			containerList.add(object);
		} else if (f instanceof EReference) {
			EReference ref = (EReference) f;
			ref.eContents().add(object);
		} else {
			throw new IllegalArgumentException("The structural feature '" + feature + "' (resolved to '" + f + "') of object '" + container + "' cannot contain anything - it isn't an EList or EReference.");
		}
		EReferenceImpl fx;
		*/
		
		return object;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createRelationship(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject createRelationship(EObject container, EClass elementType,
			EObject source, EObject target, EStructuralFeature containerFeature, EStructuralFeature sourceFeature, EStructuralFeature targetFeature) throws InferenceException {
		
		// sanity check
		if (container == null) return null;
		if (source == null) return null;
		if (target == null) return null;

		Assert.isNotNull(elementType);
		Assert.isNotNull(containerFeature);
		Assert.isNotNull(sourceFeature);
		Assert.isNotNull(targetFeature);
		
		//EObject object = EcoreUtil.create(elementType);
		EObject object = createElement(elementType);
		
		if (object == null)
			throw new IllegalArgumentException("Unknown class type '" + elementType + "' - am I missing a factory?");
		
		// we now need to set its container
		Object f = container.eGet(containerFeature, false);
		if (f instanceof EList) {
			EList<Object> containerList = (EList<Object>) f;
			containerList.add(object);
		} else if (f instanceof EReference) {
			EReference ref = (EReference) f;
			ref.eContents().add(object);
		} else {
			throw new IllegalArgumentException("The container structural feature '" + containerFeature + "' (resolved to '" + f + "') of object '" + container + "' cannot contain anything - it isn't a EList or EReference.");
		}

		// we now need to set its source feature
		object.eSet(sourceFeature, source);
		
		// we now need to set its target feature
		object.eSet(targetFeature, target);

		return object;
		
	}


}
