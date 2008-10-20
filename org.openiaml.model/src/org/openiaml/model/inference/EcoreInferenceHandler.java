/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * an ecore-based inference handler.
 * 
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
		// check can be contained: : http://www.eclipse.org/newsportal/article.php?id=36608&group=eclipse.tools.emf
		if (!((EReference) feature).getEReferenceType().isSuperTypeOf(elementType)) {
			throw new IllegalArgumentException("Reference type '" + feature.getName() + "' (type " + ((EReference) feature).getEReferenceType().getName() + ") cannot store types of " + elementType.getName()); 
		}
		
		if (feature.isMany()) {
			// assume feature is list: http://www.eclipse.org/newsportal/article.php?id=36608&group=eclipse.tools.emf
			EList<Object> containerList = (EList<Object>) container.eGet(feature, false);
			containerList.add(object);
		} else {
			throw new IllegalArgumentException("Cannot do anything with the structural feature: " + feature);
		}

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
		// check can be contained: : http://www.eclipse.org/newsportal/article.php?id=36608&group=eclipse.tools.emf
		if (!((EReference) containerFeature).getEReferenceType().isSuperTypeOf(elementType)) {
			throw new IllegalArgumentException("Reference type '" + containerFeature.getName() + "' (type " + ((EReference) containerFeature).getEReferenceType().getName() + ") cannot store types of " + elementType.getName()); 
		}
		
		if (containerFeature.isMany()) {
			// assume feature is list: http://www.eclipse.org/newsportal/article.php?id=36608&group=eclipse.tools.emf
			EList<Object> containerList = (EList<Object>) container.eGet(containerFeature, false);
			containerList.add(object);
		} else {
			throw new IllegalArgumentException("Cannot do anything with the structural feature: " + containerFeature);
		}

		// we now need to set its source feature
		object.eSet(sourceFeature, source);
		
		// we now need to set its target feature
		object.eSet(targetFeature, target);

		return object;
		
	}


}
