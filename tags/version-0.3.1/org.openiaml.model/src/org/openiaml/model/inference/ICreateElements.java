/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.openiaml.model.model.GeneratedElement;

/**
 * An interface to something that creates elements.
 * 
 * Q: why not use one of the existing interfaces, e.g. ModelFactory?
 * 
 * Example implementations: {@link org.openiaml.model.inference.EcoreCreateElementsHelper}
 * 
 * @author jmwright
 *
 */
public interface ICreateElements {

	/**
	 * @param element
	 * @param reference
	 * @param value
	 */
	void setValue(EObject element, EStructuralFeature reference, Object value)
	 throws InferenceException;

	/**
	 * @param container the containing element
	 * @param elementType the element type to create
	 * @return
	 */
	EObject createElement(EObject container,
			EClass elementType, EStructuralFeature containerFeature) throws InferenceException;

	/**
	 * @param container
	 * @param parameterWire
	 * @param rwi
	 * @param parameter
	 * @return
	 */
	EObject createRelationship(EObject container, EClass elementType,
			EObject source, EObject target, EStructuralFeature containerFeature, EStructuralFeature sourceFeature, EStructuralFeature targetFeature)  throws InferenceException;

	/**
	 * Delete a given element from a container. 
	 */
	public void deleteElement(EObject object, EObject container,
			EStructuralFeature containerFeature) throws InferenceException;
	
	/**
	 * Sets the "generatedRule" property of a GeneratedElement.
	 * 
	 * @param element
	 * @param ruleName
	 * @throws InferenceException
	 */
	public void setGeneratedRule(GeneratedElement element, String ruleName) throws InferenceException;
	
}
