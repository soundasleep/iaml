/**
 * 
 */
package org.openiaml.model.inference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;

/**
 * An ecore-based inference handler.
 * 
 * <b>NOTE:</b> if you are doing inference within an existing EMF
 * instance, you should be using {@link EmfInferenceHandler} instead.
 * 
 * @see EmfInferenceHandler
 * @author jmwright
 *
 */
public class EcoreInferenceHandler extends EcoreCreateElementsHelper implements ICreateElements {

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
			try {
				containerList.add(object);
			} catch (ArrayStoreException e) {
				throw new RuntimeException("Could not insert an element '" + elementType.getName() + "' into feature '" + feature.getName() + "': " + e, e);
			}
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
			try {
				containerList.add(object);
			} catch (ArrayStoreException e) {
				throw new RuntimeException("Could not insert a relationship '" + elementType.getName() + "' into container feature '" + containerFeature.getName() + "': " + e, e);
			}
		} else {
			throw new IllegalArgumentException("Cannot do anything with the structural feature: " + containerFeature);
		}

		// we now need to set its source feature
		object.eSet(sourceFeature, source);
		
		// we now need to set its target feature
		object.eSet(targetFeature, target);

		return object;
		
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#deleteElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public void deleteElement(EObject object, EObject container,
			EStructuralFeature containerFeature)
			throws InferenceException {

		Assert.isNotNull(object);
		Assert.isNotNull(container);
		Assert.isNotNull(containerFeature);
		
		// delete any contained elements first
		
		// we need to copy this to delete outside of the iterator loop
		List<EObject> containedElement = new ArrayList<EObject>();
		List<EObject> containedContainer = new ArrayList<EObject>();
		List<EStructuralFeature> containedFeature = new ArrayList<EStructuralFeature>();
		
		for (EReference containment : object.eClass().getEAllContainments()) {
			EList<EObject> contains = (EList<EObject>) object.eGet(containment);
			for (EObject c : contains) {
				containedElement.add(c);
				containedContainer.add(object);
				containedFeature.add(containment);
			}
		}
		
		for (int i = 0; i < containedElement.size(); i++) {
			// recursively delete children before we delete the parent
			deleteElement(containedElement.get(i), containedContainer.get(i), containedFeature.get(i));
		}
		
		if (containerFeature.isMany()) {
			// assume feature is list: http://www.eclipse.org/newsportal/article.php?id=36608&group=eclipse.tools.emf
			EList<Object> containerList = (EList<Object>) container.eGet(containerFeature, false);
			
			if (!containerList.contains(object)) {
				// do nothing
				// throw new InferenceException("Containment list '" + containerList + "' in container '" + container + "' does not contain object '" + object + "'");
				return;
			}
			
			containerList.remove(object);

			List<EList<Object>> toDeleteLists = new ArrayList<EList<Object>>();
			Map<EReference,List<EObject>> toDeleteReferences = new HashMap<EReference,List<EObject>>();
			
			// delete all reverse references (untested code)
			for (EContentsEList.FeatureIterator featureIterator = 
			     (EContentsEList.FeatureIterator)object.eCrossReferences().iterator();
			     featureIterator.hasNext(); ) {
				EObject target = (EObject) featureIterator.next();
				
				// EReference reference = (EReference) featureIterator.feature();
				// reference.getEOpposite() seems to return the wrong EReference for target
				// so we iterate over all references in Target to find, and delete, all references
				// to object
				
				for (EReference ref : target.eClass().getEAllReferences()) {
					if (ref.isMany()) {
						// it's a list (assumed)
						EList<Object> resolved = (EList<Object>) target.eGet(ref);
						if (resolved.contains(object)) {
							// delete the reference
							// resolved.delete(object);
							toDeleteLists.add(resolved);
						}
					} else {
						Object resolved = target.eGet(ref);
						if (object.equals(resolved)) {
							// unset the reference
							// target.eUnset(ref);
							if (toDeleteReferences.get(ref) == null) {
								toDeleteReferences.put(ref, new ArrayList<EObject>());
							}
							List<EObject> r = toDeleteReferences.get(ref);
							r.add(target);
							toDeleteReferences.put(ref, r);
						}
					}
				}
				
			}
			
			// now do all the deletes
			// (modifying them above was causing synchronisation issues)
			for (EList<Object> list : toDeleteLists) {
				// delete object from list
				list.remove(object);
			}
			for (EReference ref : toDeleteReferences.keySet()) {
				// unset reference
				for (EObject obj : toDeleteReferences.get(ref)) {
					obj.eUnset(ref);
				}
			}
			
		} else {
			throw new IllegalArgumentException("Cannot do anything with the structural feature: " + containerFeature);
		}		
		
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#addReference(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void addReference(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		
		// we will just let ecore do it
		EList<Object> list = (EList<Object>) element.eGet(reference);
		list.add(value);
	}

}
